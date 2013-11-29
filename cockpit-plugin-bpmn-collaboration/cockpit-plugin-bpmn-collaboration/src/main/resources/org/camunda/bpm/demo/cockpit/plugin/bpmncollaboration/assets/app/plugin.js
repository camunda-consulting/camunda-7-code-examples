ngDefine('cockpit.plugin.bpmn-collaboration', function(module) {

  // define Angular ressource for REST communication
  var Resource = function ($resource, Uri) {
    return $resource(Uri.appUri('plugin://bpmn-collaboration/:engine/process-instance/:id/linked-process-instance'), {id: '@id'}, {
      query: { method: 'GET', isArray: true}
    });
  };
  module.factory('BpmnCollaborationLinkedProcessInstanceResource', Resource);
  
  // create controller to load data for HTML
  function BpmnCollaborationController ($scope, BpmnCollaborationLinkedProcessInstanceResource) {
    // input: processInstance

    $scope.calledProcessInstances = null;
    BpmnCollaborationLinkedProcessInstanceResource.query({id: $scope.processInstance.id}).$then(function(response) {
          $scope.calledProcessInstances = response.data;
    });      
  };
  module.controller('BpmnCollaborationController', [ '$scope', 'BpmnCollaborationLinkedProcessInstanceResource', BpmnCollaborationController ]);


  // register Plugin
  var Configuration = function PluginConfiguration(ViewsProvider) {

    ViewsProvider.registerDefaultView('cockpit.processInstance.instanceDetails', {
      id: 'linked-process-instances-tab',
      label: 'Process Instances related via Collaboration',
      url: 'plugin://bpmn-collaboration/static/app/linked-process-instance-table.html',
      controller: 'BpmnCollaborationController',
      priority: 12
    });
  };

  Configuration.$inject = ['ViewsProvider'];

  module.config(Configuration);
});
