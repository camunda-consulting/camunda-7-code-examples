ngDefine('cockpit.plugin.kpi-overview-plugin.controllers',[], function(module) {
	module.controller('kpiTaskController', ['$scope', '$http', 'Uri', 'camAPI', 'constants', 'kpiExtractor', function($scope, $http, Uri, camAPI, constants, kpiExtractor) {
    	var overlayIDs= [];
    	$scope.cssPath = constants.CONST_PLUGIN_PATH.CSS;
        $scope.typeFilters = constants.CONST_BPMN_TYPES;

        //get process instance data
        $http.get(Uri.appUri(constants.CONST_REST_URLS.historyProcessInstance + $scope.processInstance.id), {}).success(function(data) {
            if ($scope.processInst) {
                $scope.processInst.startTime = data.startTime;
            } else {
                $scope.processInst = data;
            }

            angular.element($('[cam-widget-bpmn-viewer]')).scope().$watch('processDiagram', function(data) {
                if (data != null && data.bpmnElements != null) {
                    Object.keys(data.bpmnElements).forEach(function(key) {
                        var bpmnElement = data.bpmnElements[key];
                        if (bpmnElement.$type === 'bpmn:Process' || bpmnElement.$type === 'bpmn:Collaboration') {
                        	var extensionElements = bpmnElement.extensionElements;
                        	var kpiInformation = kpiExtractor.extract(extensionElements);
                            
                        	if (kpiInformation != null && kpiInformation.kpi != null) {
                        		$scope.processInst.kpiInformation = kpiInformation;
                        		$scope.processInst = calculateProcessStatistic($scope.processInst);
                        	}
                        }
                    });
                }
            });
        });

        //get history activity instances
        $http.post(Uri.appUri(constants.CONST_REST_URLS.historyActivityInstance), {
            processInstanceId: $scope.processInstance.id,
            sorting: [{sortBy: "occurrence", sortOrder: "asc"}]
        }).success(function(activityInstances) {
        	var activityNames = [];
            var taskActivityInstances = activityInstances.filter(function(activityInstance) {
            	activityNames.push(activityInstance.activityId);
                if (constants.CONST_TASK_TYPES.indexOf(activityInstance.activityType) > -1) {
                    return true;
                } else {
                    return false;
                }
            });

            var tasks = [];
            angular.element($('[cam-widget-bpmn-viewer]')).scope().$watch('processDiagram', function(data) {
                if (data != null && data.bpmnElements != null) {
                	taskActivityInstances.forEach(function(taskActivity) {
                           	var bpmnElement = getMatchingBPMNElement(taskActivity, data.bpmnElements);
                           	bpmnElement.kpiData = kpiExtractor.extract(bpmnElement.extensionElements);
                           	taskActivity.bpmnElement = bpmnElement;
                           	taskActivity.$type = bpmnElement.$type;
                            taskActivity.link = '#/process-instance/' + $scope.processInstance.id + '/history?detailsTab=kpi-overview&activityInstanceIds=' + taskActivity.id;
                            taskActivity = calculateActivityStatistic(taskActivity);
                    });
                	
                    addActivityOverlays(taskActivityInstances);
                    taskActivityInstances = addBPMNElements(taskActivityInstances, data.bpmnElements);
                    $scope.taskActivityInstances = taskActivityInstances;
                    
                } else {
                	console.log("data null");
                }
            });
        });
        
        
        $scope.$on('$destroy', function iVeBeenDestroyed() {
        	angular.element($('[cam-widget-bpmn-viewer]')).scope().$watch('viewer', function(viewer) {
            	if (viewer) {
	                var overlays = viewer.get('overlays');
	                overlayIDs.forEach(function(id) {
	                	overlays.remove(id);
	                });
            	}
        	});
        });
        
        function calculateProcessStatistic(processInst) {
        	processInst.targetDuration = processInst.kpiInformation.kpi + processInst.kpiInformation.kpiunit;
        	
            var creationMoment = new moment(processInst.startTime);
            var currentMoment = new moment();
            
            if (processInst.endTime) {
            	currentMoment = new moment(processInst.endTime);
            }
            
            var duration = currentMoment.diff(creationMoment, processInst.kpiInformation.kpiunit);
            processInst.currentDuration = duration + processInst.kpiInformation.kpiunit;
            
            if (duration > parseInt(processInst.kpiInformation.kpi)) {
                processInst.overdue = true;
                processInst.overdueDuration = (duration - parseInt(processInst.kpiInformation.kpi)) + processInst.kpiInformation.kpiunit;
            } else {
                processInst.overdue = false;
            }
            return processInst;
        };
        
        function calculateActivityStatistic(taskActivity) {
        	var startMoment = new moment(taskActivity.startTime);
            if (taskActivity.endTime) {
                var endMoment = new moment(taskActivity.endTime);
            } else {
                var endMoment = new moment();
            }
            var diff = endMoment.diff(startMoment);
            var duration = moment.duration(diff).humanize();
            var durationInUnit = endMoment.diff(startMoment, taskActivity.bpmnElement.kpiData['kpiunit']);

            taskActivity.duration = duration;
            taskActivity.durationInUnit = durationInUnit;
            if (durationInUnit > parseInt(taskActivity.bpmnElement.kpiData['kpi'])) {
                taskActivity.overdue = true;
                taskActivity.overdueTime = durationInUnit - parseInt(taskActivity.bpmnElement.kpiData['kpi']) + taskActivity.bpmnElement.kpiData['kpiunit'];
            } else {
                taskActivity.overdue = false;
            }
            
            return taskActivity;
        };
        
        function addBPMNElements(taskActivityInstances, bpmnElements) {
        	Object.keys(bpmnElements).forEach(function(key) {
        		var element = bpmnElements[key];
        		if (element.kpiData == null) {
        			element.kpiData = kpiExtractor.extract(element.extensionElements);
        		}
        		var items = taskActivityInstances.filter(function(activity) {
        			if (activity.bpmnElement === element) {
        				return true;
        			}
        		});
        		if (element.kpiData != null && element.kpiData.kpi && constants.CONST_BPMN_TYPES.indexOf(element.$type) > -1 && items.length == 0) {
        			taskActivityInstances.push({"$type": element.$type, "bpmnElement":element});
        		}
        	});
        	return taskActivityInstances;
        };
        
        function addActivityOverlays(tasks) {
        	var taskIds = [];
        	var filteredTask= [];
        	
        	tasks.filter(function(task) {
        		var index = taskIds.indexOf(task.activityId);
        		
        		if (index < 0) {
        			filteredTask.push(task);
        			taskIds.push(task.activityId);
        		} else if (task.overdue && index > -1) {
        			filteredTask.push(task);
        			taskIds.push(task.activityId);
        		}
        	});
        	filteredTask.forEach(function(taskActivity) {
        		addOverlay(taskActivity);
        	});
        };
        
        function getMatchingBPMNElement(taskActivity, bpmnElements) {
        	var elementKey = '';
        	Object.keys(bpmnElements).forEach(function(key) {
        		if (bpmnElements[key].id === taskActivity.activityId) {
        			elementKey = key;
        		}
        	});
        	return bpmnElements[elementKey];
        };
        
        function addOverlay(taskActivity) {
            angular.element($('[cam-widget-bpmn-viewer]')).scope().$watch('viewer', function(viewer) {
            	if (viewer) {
	                var overlays = viewer.get('overlays');
	                if (taskActivity.bpmnElement.kpiData != null && taskActivity.bpmnElement.kpiData.kpi != null) {
	                    if (taskActivity != null && taskActivity.overdue) {
	                        var htmlElement = '<div class="kpi-process-overlay-large kpi-process-overlay-red"><span class="glyphicon glyphicon-exclamation-sign"></span></div>';
	                    } else if (taskActivity != null && !taskActivity.overdue) {
	                        var htmlElement = '<div class="kpi-process-overlay-large kpi-process-overlay-green"><span class="glyphicon glyphicon-ok-sign"></span></div>';
	                    } else {
	                        var htmlElement = '<div></div>';
	                    }

	                    var overlay = overlays.add(taskActivity.bpmnElement.id, {
	                        position: {
	                            top: -10,
	                            right: 8
	                        },
	                        show: {
	                        	minZoom: 0,
	                        	maxZoom: 50
	                        },
	                        html: htmlElement
	                    });
	                    overlayIDs.push(overlay);
	                }
            	}
            });
        }
	 }])});   