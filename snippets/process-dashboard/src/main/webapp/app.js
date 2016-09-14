var baseUrl = "http://localhost:8080/";


var baseUrlRest = baseUrl + "engine-rest/engine/default";
var baseUrlTasklist = baseUrl + "camunda/app/tasklist/default/#/";
var baseUrlCockpit = baseUrl + "camunda/app/cockpit/default/#/";

var recognizedBpmnTypes = ['bpmn:ServiceTask', 'bpmn:CallActivity', 'bpmn:UserTask', 'bpmn:ScriptTask'];
var variablesToShow = [{name:'media', label:'Medienname'}, {name:'mediennummer', label:'Nummer'}, {name:'priority', label:'Priorität'}];

var bpmnViewer;

var activitiesForStatusTable;
var now = new Date();

/**
 * if you want to use this served by a static file make sure to allow CORS in your server,
 see e.g. for WildFly: https://forum.camunda.org/t/enable-cors-on-wildfly/673/2
*/

function getUrlParameterByName(name) {
		    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
		    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
}				    

function init() {
	var processDefinitionKey = getUrlParameterByName('processDefinitionKey');				    

	// load process definition XML
	$.get(baseUrlRest + '/process-definition/key/' + processDefinitionKey + '/xml', function(data) {
		// render it using bpmn-js
		bpmnViewer.importXML(data.bpmn20Xml, function(err) {
			if (err) {
				console.log('error rendering', err);
			} else {
				var activityList = [];
				var activityPredecessors = {};

				renderVariables();

				// after rendering we can read the BPMN model as JSON representation to know which status columns we want to render
				bpmnViewer.get('elementRegistry').filter(function(element, gfx){
					if (element && element.businessObject && 
						$.inArray(element.businessObject.$type, recognizedBpmnTypes) >=0) { 
						activityList.push(element);

						// for all target activities we are a predecessor
						for (var i = 0; i < element.businessObject.outgoing.length; i++) {
							var targetElement = element.businessObject.outgoing[i].targetRef;
							if (!activityPredecessors[targetElement.id]) {
								activityPredecessors[targetElement.id] = [];
							}
							activityPredecessors[targetElement.id].push(element);
						}
					}
				});

				activitiesForStatusTable = [];
				while (activityList.length) {
					renderNextActivity(activityList, activityPredecessors);
				}

				loadProcessInstances(processDefinitionKey);

				// Move bpmn-js viewer to modal (can only be done AFTER rendering, model must be visible during rendering to avoud problems: https://forum.bpmn.io/t/layout-problems-when-putting-bpmn-js-in-a-modal/1003/2)
                $("#bmmn-drop-zone").appendTo("#popupPanel");				
			}
		});
	});
}

function loadProcessInstances(processDefinitionKey) {
	// load process instance information, load ALL for the moment
	// sort it by startTime descending (latest started on top!)
	
	// you might only want process instances from today:
	// let formattedDate = now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate() + 'T00:00:00';
    // and add: &startedAfter=' + formattedDate

	$.get(baseUrlRest + '/history/process-instance/?processDefinitionKey=' + processDefinitionKey + '&sortBy=startTime&sortOrder=desc', function(processInstances) {
		for (var i = 0; i < processInstances.length; i++) {
			var pi = processInstances[i];

			renderProcessInstanceRow(pi); // render immediately to not loose ordering (as the following methods might have arbritrary runtimes)
		
			// load variables and status for the PI to visualize it
			(function(processInstance) {
				$.when(
					$.get(baseUrlRest + '/history/variable-instance?processInstanceId=' + processInstance.id),
					$.get(baseUrlRest + '/history/activity-instance?processInstanceId=' + processInstance.id),
					$.get(baseUrlRest + '/incident?processInstanceId=' + processInstance.id)
				).done(function(variables, activityInstances, incidents){    
//console.log(incidents[0]);					
					renderProcessInstanceDetails(processInstance, activityInstances[0], variables[0], incidents[0]);
				});
			})(pi);
		}
	});	
}

function renderVariables() {
	for (var i = 0; i < variablesToShow.length; i++) {
		 $("#statusTableHeadline").find("th:last").before('<th>'+variablesToShow[i].label+'</th>');
	}	
}

function renderNextActivity(activityList, activityPredecessors) {
	for (var i = 0; i < activityList.length; i++) {
		if (!activityPredecessors[activityList[i].businessObject.id] || activityPredecessors[activityList[i].businessObject.id].length==0) {
			var elementToRender = activityList[i]
			// no predecessor - let's render it!
			addTableColumn(elementToRender.businessObject);

			// and remove us from the predecssors, as we are now rendered (to have a new element without predecessor)
			for (var j = 0; j < Object.keys(activityPredecessors).length; j++) {
				var elementId = Object.keys(activityPredecessors)[j];
				var predecessorList = activityPredecessors[elementId];
				for (var k = 0; k < predecessorList.length; k++) {
					if (predecessorList[k].businessObject.id == elementToRender.businessObject.id) {
						predecessorList.splice(k, 1);
					}
				}
			}
		}
		activityList.splice(i, 1);
		return;
	}
}

function addTableColumn(activity) {
	activitiesForStatusTable.push(activity);
	 //$("#scenarioTable > tbody").html("");
	 $("#statusTableHeadline").find("th:last").before('<th>'+activity.name+'</th>');
}		

function renderProcessInstanceRow(pi) {
	var tr = 
		"<tr id='"+pi.id.replace(/:/g, '-')+"'>"
		+ '<td>' 
		+	'<a label="Inspect process instance" onclick="highlightProcessInstance(\''+pi.id+'\')" data-toggle="modal" data-target="#myModal" class="btn btn-default table-row-btn"><span class="glyphicon glyphicon-eye-open"></span></a>'
		+	'<a label="Open in cockpit" href="'+baseUrlCockpit + 'process-instance/'+pi.id+'/history" target="blank_" class="btn btn-default table-row-btn"><span class="glyphicon glyphicon-info-sign"></span></a>' 
		+ '</td>'
//        + '<td><a class="btn btn-default"><span class="glyphicon glyphicon-ok"></span></a></td>'
		+ "</tr>";


	$("#statusTable > tbody").append(tr);
}

//variablesToShow
function renderProcessInstanceDetails(pi, activityInstances, variables, incidents) {
	var td = '';

	// load variable values
	for (var i = 0; i < variablesToShow.length; i++) {
		var variable = getVariable(variables, variablesToShow[i].name);
		td = td + '<td>'+variable.value+'</td>';
	}

	// load status for all relevant activities
	for (var i = 0; i < activitiesForStatusTable.length; i++) {
		var activityInstance = getActivityInstance(activityInstances, activitiesForStatusTable[i].id);
		var incident = getIncidentForActivity(incidents, activitiesForStatusTable[i].id);
		
		if (incident) { // failure
			td = td + '<td><div class="alert alert-danger status-alert"><a onclick="toggleVisibility(\'' + incident.id + '\')">Incident</a><div id="' + incident.id + '" style="display: none;">' 
			+ '<p>' + incident.incidentTimestamp +'</p>'
			+ '<p>' + incident.incidentMessage + '</p>'
			+ '<p><a href="'+baseUrlCockpit + 'process-instance/'+pi.id+'/runtime?detailsTab=incidents-tab" target="blank_" >Open in cockpit</></p>'
			+ '</div></div></td>';
		} else if (!activityInstance) { // not contained means -> not yet started.
			td = td + '<td><div class="alert alert-grey status-alert">open</div></td>';
		} else if (activityInstance.endTime) { // ended means -> done
			td = td + '<td><div class="alert alert-success status-alert">'+ getRuntimeInfo(activityInstance.durationInMillis, "Took ") +'</div></td>';
		} else if (activityInstance.startTime) {
			let durationInMillis = new Date() - new Date(Date.parse(activityInstance.startTime));
			td = td + '<td><div class="alert alert-warning status-alert">'+ getRuntimeInfo(durationInMillis, "Running or waiting since ") +'</div></td>';
		} else { //running
			// javascript treat time as ISO string as UTC, but it is local time!
			let durationInMillis = now - new Date(Date.parse("2016-09-09T11:04:30") + ( now.getTimezoneOffset() * 60000 ));
			td = td + '<td><div class="alert alert-warning status-alert">'+ getRuntimeInfo(durationInMillis, "Running or waiting since ") +'</div></td>';
		}
	}

	$("#"+pi.id.replace(/:/g, '-')).find("td:last").before(td);
}


function toggleVisibility(id) {
	$("#" + id).toggle();
}

function getVariable(variables, name) {
	for (var i = 0; i < variables.length; i++) {
		if (variables[i].name==name) {
			return variables[i];
		}
	}
	return null;
}

function getActivityInstance(activityInstances, id) {
	for (var i = 0; i < activityInstances.length; i++) {
		if (activityInstances[i].activityId==id) {
			return activityInstances[i];
		}
	}
	return null;
}

function getIncidentForActivity(incidents, activityId) {
	for (var i = 0; i < incidents.length; i++) {
		if (incidents[i].activityId==activityId) {
			return incidents[i];
		}
	}
	return null;
}

function highlightProcessInstance(processInstanceId) {

}


function getRuntimeInfo(millis, infoPrefix) {
    var seconds = Math.floor((millis / 1000) % 60);
    var minutes = Math.floor((millis / (60 * 1000)) % 60);
    var hours = Math.floor((millis / (60 * 60 * 1000)) % 60);
    var days = Math.floor((millis / (24 * 60 * 60 * 1000)) % 60);

    var timeInfo = days + " days "  + hours + " hours and " + minutes + " minutes " + seconds + " seconds";
	let result = "";
    if (days>0) {
        result = '<span title="' + infoPrefix + ' ' + timeInfo + '">' + days + ' days</span>';
    } else if (hours>0) {
        result = '<span title="' + infoPrefix + timeInfo + '">' + hours + ":" + minutes + '</span>';
    } else if (minutes>0) {
        result = '<span title="' + infoPrefix + timeInfo + '">' +  minutes + ' min</span>';
    } else {
        result = '<span title="' + infoPrefix + timeInfo + '">' +  seconds + ' sec</span>';
    }
    return result;
}


function clearDiagramJsViewer() {

	var overlays = bpmnViewer.get('overlays');		
	overlays.clear();

	$('#bpmnCanvas').find('.highlight').each(function(i, obj) {
		$(obj).removeClass('highlight');
	});

	// removeMarker does not always work reliably in this case - remove the class ourselve
	//var canvas = viewer.get('canvas');
	//canvas.removeMarker(  $(obj).attr('data-element-id'), 'highlight'  );
}


function highlightProcessInstance(processInstanceId) {
	clearDiagramJsViewer();

    // load runtime status
	$.when(
		$.get(baseUrlRest + '/process-instance/' + processInstanceId + '/activity-instances/'),
		$.get(baseUrlRest + '/history/activity-instance/?processInstanceId=' + processInstanceId)
	).done(function(actInstTree, actInstList){  
	    // // ignore 404 - ProcessInstance might be ended  
		//$("#bmmn-drop-zone").appendTo("#rootDiv");	
	   addMarkerForActivities(actInstTree[0]);									       
	   addHistoryInfoOverlay(actInstList[0]);
		//$("#bmmn-drop-zone").appendTo("#popupPanel");	
    });
							

function addMarkerForActivities(actInstTree) {
	if (actInstTree.childTransitionInstances.length==0 && actInstTree.childActivityInstances.length==0) {
		bpmnViewer.get('canvas').addMarker(actInstTree.activityId, 'highlight');	
	}
	else {
		for (index=0; index < actInstTree.childTransitionInstances.length; ++index) {
			bpmnViewer.get('canvas').addMarker(actInstTree.childTransitionInstances[index].activityId, 'highlight');	
		}
		for (index=0; index < actInstTree.childActivityInstances.length; ++index) {
		    // add recursively
			addMarkerForActivities(actInstTree.childActivityInstances[index]);	
		}
	}
}
	
function addHistoryInfoOverlay( actInstList) {
   for (index = 0; index < actInstList.length; ++index) {
		if (actInstList[index].endTime) {
			bpmnViewer.get('overlays').add(actInstList[index].activityId, {
			  position: {top: 0, right: 0},
			  show: {minZoom: 0, maxZoom: 100.0}, 
			  html: '<div class="bpmn-badge bpmn-badge-completed"><span class="glyphicon glyphicon-ok"></span><br>'+getRuntimeInfo(actInstList[index].durationInMillis, "Took ")+'</div>'
			});
		} else if (actInstList[index].startTime) {
			let durationInMillis = new Date() - new Date(Date.parse(actInstList[index].startTime));
			bpmnViewer.get('overlays').add(actInstList[index].activityId, {
				position: { top: 0, right: 0 },
				show: { minZoom: 0, maxZoom: 100.0 },
				html: '<div class="bpmn-badge bpmn-badge-running"><span class="glyphicon glyphicon-ok"></span><br>' + getRuntimeInfo(durationInMillis, "Running since ") + '</div>'
			});
		} else {
			let durationInMillis = now - new Date(Date.parse("2016-09-09T11:04:30") + ( now.getTimezoneOffset() * 60000 ));
			bpmnViewer.get('overlays').add(actInstList[index].activityId, {
			  position: {top: 0, right: 0},
			  show: {minZoom: 0, maxZoom: 100.0}, 
			  html: '<div class="bpmn-badge bpmn-badge-running"><span class="glyphicon glyphicon-time"></span><br>'+getRuntimeInfo(durationInMillis, "Running since ")+'</div>'
			});
		}					        
   }
}							
}