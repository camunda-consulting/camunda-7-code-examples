ngDefine('cockpit.plugin.kpi-overview-plugin.controllers', [], function(module) {
    module.controller('kpiProcessController', ['$scope', 'filterFilter', '$compile', '$http', 'Uri', 'camAPI', 'constants', 'kpiExtractor', function($scope, filterFilter, $compile, $http, Uri, camAPI, constants, kpiExtractor) {
        var overlayIDs = [];
        $scope.cssPath = constants.CONST_PLUGIN_PATH.CSS;
        $scope.pages = {current: 1, size: constants.QUERY_MAX_RESULTS};
        
        $scope.$watch('pages.current', function(newValue, oldValue) {
            if (newValue === oldValue) {
                return;
            }
            updateView();
        });
        $scope.$on('$destroy', destroy);

        
        $http.get(Uri.appUri(constants.CONST_REST_URLS.historyProcessInstanceCount)).success(function(result) {
            $scope.pages.total = result.count;
            updateView();
        });
        
        basicStatistics();
        
        function destroy() {
        	angular.element($('[cam-widget-bpmn-viewer]')).scope().$watch('viewer', function(viewer) {
                if (viewer) {
                    var overlays = viewer.get('overlays');
                    overlayIDs.forEach(function(id) {
                        overlays.remove(id);
                    });
                    overlayIDs = [];
                }
            });
        };

        function updateView() {
            //get statistis for overlay
            angular.element($('[cam-widget-bpmn-viewer]')).scope().$watch('processDiagram', function(data) {
                var statisticQuery = [];

                if (data != null && data.bpmnElements != null) {
                    Object.keys(data.bpmnElements).forEach(function(key) {
                        var bpmnElement = data.bpmnElements[key];
                        if (constants.CONST_BPMN_TYPES.indexOf(bpmnElement.$type) > -1) {
                            //add the KPI data from processDefinition
                            var kpiData = kpiExtractor.extract(bpmnElement.extensionElements);
                            var element = {
                                taskId: bpmnElement.id,
                                kpiUnit: kpiData.kpiunit,
                                kpi: kpiData.kpi
                            };
                            statisticQuery.push(element);
                        }
                    });
                }

                if (statisticQuery.length > 0) {
                    $http.get(Uri.appUri(constants.CONST_REST_URLS.historyCustomStatistic), {
                        params: {
                            'processDefinitionId': $scope.processDefinition.id,
                            'statisticData': JSON.stringify(statisticQuery)
                        }
                    }).success(function(statistic) {
                        statistic.forEach(function(statistic) {
                            if (statistic.count != null) {
                                addOverlay(statistic.activityId, statistic.count);
                            }
                        });
                    });
                }
            });


            //get history for this process to calculate average duration
            $http.get(Uri.appUri(constants.CONST_REST_URLS.historyProcessInstance), {
                params: {
                    'processDefinitionId': $scope.processDefinition.id,
                    'firstResult': ($scope.pages.current - 1) * $scope.pages.size,
                    'maxResults': $scope.pages.size,
                    'sortBy': 'startTime',
                    'sortOrder': 'desc'
                }
            }).success(function(processInstances) {
                processInstances.forEach(function(instance, index) {
                    var tasksOverdue = 0;
                    var completedTasksOverdue = 0;
                    var tasksOverdueIds = [];
                    var startTimeMoment = new moment(instance.startTime);
                    if (instance.endTime) {
                        var endTimeMoment = new moment(instance.endTime);
                    } else {
                        var endTimeMoment = new moment();
                    }

                    processInstances[index].link = '#/process-instance/' + instance.id + '/history?detailsTab=kpi-overview';

                    angular.element($('[cam-widget-bpmn-viewer]')).scope().$watch('processDiagram', function(data) {
                        if (data != null && data.bpmnElements != null) {
                            Object.keys(data.bpmnElements).forEach(function(key) {
                                var bpmnElement = data.bpmnElements[key];
                                if (bpmnElement.$type === 'bpmn:Process' || bpmnElement.$type === 'bpmn:Collaboration') {
                                    var extensionElements = bpmnElement.extensionElements;
                                    var kpiInformation = kpiExtractor.extract(extensionElements);

                                    if (kpiInformation != null && kpiInformation.kpi) {
                                        processInstances[index].targetDuration = kpiInformation.kpi + kpiInformation.kpiunit;
                                        processInstances[index].currentDuration = endTimeMoment.diff(startTimeMoment, kpiInformation.kpiunit);
                                        processInstances[index].currentDurationInUnit = processInstances[index].currentDuration + kpiInformation.kpiunit;
                                        processInstances[index].unit = kpiInformation.kpiunit;
                                        
                                        if (processInstances[index].currentDuration > parseInt(kpiInformation.kpi)) {
                                            processInstances[index].overdue = true;
                                            processInstances[index].overdueInUnit = (parseInt(processInstances[index].currentDuration) - parseInt(kpiInformation.kpi)) + kpiInformation.kpiunit;
                                        } else {
                                            processInstances[index].overdue = false;
                                        }
                                        
                                        if ($scope.processGeneral == null) {
                                            $scope.processGeneral = {
                                                unit: kpiInformation.kpiunit
                                            };
                                        }
                                    }
                                }
                            });
                        }
                    });

                    //get tasks and check overdue for them
                    $http.post(Uri.appUri(constants.CONST_REST_URLS.historyActivityInstance), {
                        processInstanceId: instance.id
                    }).success(function(activityInstances) {

                        var taskActivityInstances = activityInstances.filter(function(activityInstance) {
                            if (constants.CONST_TASK_TYPES.indexOf(activityInstance.activityType) > -1) {
                                return true;
                            } else {
                                return false;
                            }
                        });


                        angular.element($('[cam-widget-bpmn-viewer]')).scope().$watch('processDiagram', function(data) {
                            if (data != null && data.bpmnElements != null) {
                                Object.keys(data.bpmnElements).forEach(function(key) {
                                    var bpmnElement = data.bpmnElements[key];
                                    //add the KPI data from processDefinition
                                    bpmnElement.kpiData = kpiExtractor.extract(bpmnElement.extensionElements);

                                    if (constants.CONST_BPMN_TYPES.indexOf(bpmnElement.$type) > -1) {
                                        taskActivityInstances.forEach(function(taskActivity) {
                                            if (taskActivity.activityId === bpmnElement.id) {
                                                bpmnElement.taskActivity = taskActivity;

                                                var startMoment = new moment(taskActivity.startTime);
                                                if (taskActivity.endTime) {
                                                    var endMoment = new moment(taskActivity.endTime);
                                                } else {
                                                    var endMoment = new moment();
                                                }
                                                var diff = endMoment.diff(startMoment);
                                                var duration = moment.duration(diff).humanize();

                                                if (bpmnElement.kpiData) {
                                                    var durationInUnit = endMoment.diff(startMoment, bpmnElement.kpiData.kpiunit);

                                                    bpmnElement.taskActivity.duration = duration;
                                                    if (durationInUnit > parseInt(bpmnElement.kpiData.kpi)) {
                                                        bpmnElement.taskActivity.overdue = true;
                                                        if (taskActivity.endTime) {
                                                            completedTasksOverdue++;
                                                        } else {
                                                            tasksOverdue++;
                                                            tasksOverdueIds.push(bpmnElement.id);
                                                        }
                                                    } else {
                                                        bpmnElement.taskActivity.overdue = false;
                                                    }
                                                }

                                                return true;
                                            }
                                        });
                                    }
                                });

                                processInstances[index].tasksOverdueIds = tasksOverdueIds
                                processInstances[index].completedtasksoverdue = completedTasksOverdue;
                                processInstances[index].tasksoverdue = tasksOverdue;
                            }
                        });
                    });

                    $scope.processInstances = processInstances;
                    $scope.processInstancesOriginal = processInstances;

                });

            });
        };

        function basicStatistics() {
            $scope.$watch('processGeneral.unit', function(unit) {
                if (unit != null) {
                    $http.get(Uri.appUri(constants.CONST_REST_URLS.historyDurationReport), {
                        params: {
                            'processDefinitionIdIn': $scope.processDefinition.id,
                            'reportType': 'duration',
                            'periodUnit': 'quarter'
                        }
                    }).success(function(result) {
                        var durations = 0;
                        if (result.length === 0) {
                            //we do nothing probably
                        } else {
                            result.forEach(function(quarter) {
                                durations += quarter.average;
                            });
                            
                            var avgDuration = Math.round(moment.duration(durations / result.length).as(unit) * 100) / 100;
                            if ($scope.processGeneral) {
                                $scope.processGeneral.avgDuration = avgDuration;
                            } else {
                                $scope.processGeneral = {
                                    'avgDuration': avgDuration
                                };
                            }
                        }
                    });
                }
            });
        };

        function addOverlay(task, amount) {
            angular.element($('[cam-widget-bpmn-viewer]')).scope().$watch('viewer', function(viewer) {
                if (amount > 0) {
	            	var overlays = viewer.get('overlays');
	                var htmlElement = angular.element('<div class="kpi-process-overlay-info" tooltip-animation="false" tooltip-placement="top" tooltip="' + amount + ' running Activity Instances overdue"><span>' + amount + '</span></div>');
	                var $element = $(htmlElement);
	                var overlay = overlays.add(task, {
	                    position: {
	                        top: -10,
	                        right: 10
	                    },
	                    show: {
	                        minZoom: Number.NEGATIVE_INFINITY,
	                        maxZoom: Number.POSITIVE_INFINITY
	                    },
	                    html: $element
	                });
	                $compile($element[0])($scope);
	                overlayIDs.push(overlay);
                }
            });
        }

        $scope.getLink = function(id, taskId) {
            return '#/process-definition/' + $scope.processDefinition.id + '/history?detailsTab=kpi-overview-definition&activityIds=' + taskId;
        };
        $scope.getLinkInst = function(processInst) {
            var tasks = processInst.tasksOverdueIds.join(',');
            return '#/process-definition/' + $scope.processDefinition.id + '/history?detailsTab=kpi-overview-definition&activityIds=' + tasks;
        }
    }])
});
