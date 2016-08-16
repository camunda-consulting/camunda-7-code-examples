'use strict';
angular.module('cockpit.plugin.kpi-overview-plugin').constant('constants', {
	CONST_BPMN_TYPES: [
        'bpmn:UserTask',
        'bpmn:ScriptTask',
        'bpmn:ServiceTask',
        'bpmn:SendTask',
        'bpmn:ReceiveTask',
        'bpmn:BusinessRuleTask',
        'bpmn:IntermediateCatchEvent'
    ],
    CONST_TASK_TYPES: [
        'userTask', 'scriptTask', 'serviceTask', 'sendTask', 'receiveTask', 'businessRuleTask', 'intermediateMessageCatch'
    ],
    CONST_REST_URLS: {
        'historyActivityInstance': 'engine://engine/:engine/history/activity-instance',
        'historyProcessInstance': 'engine://engine/:engine/history/process-instance/',
        'historyProcessInstanceCount': 'engine://engine/:engine/history/process-instance/count',
        'historyCustomStatistic': 'plugin://kpi-overview-plugin/:engine/history/statistic/query',
        'historyDurationReport': 'engine://engine/:engine/history/process-instance/report'
    },
    CONST_PLUGIN_PATH: {
    	'CSS': '../../../api/cockpit/plugin/kpi-overview-plugin/static/app/assets/css/style.html'
    },
    QUERY_MAX_RESULTS: 50
});
