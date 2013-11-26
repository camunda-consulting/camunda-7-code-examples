ngDefine('cockpit.plugin.bpmn-collaboration', function(module) {

  var Resource = function ($resource, Uri) {
    return $resource(Uri.appUri('plugin://bpmn-collaboration/:engine/process-instance/:id/linked-process-instance'), {id: '@id'}, {
      query: { method: 'GET', isArray: true}
    });
  };
  module.factory('BpmnCollaborationLinkedProcessInstanceResource', Resource);
    
    
    
  function BpmnCollaborationController ($scope, BpmnCollaborationLinkedProcessInstanceResource) {

    // input: processInstance, processData

    var calledProcessInstanceData = $scope.processData.newChild($scope);
    var processInstance = $scope.processInstance;

    var filter = null;

    calledProcessInstanceData.observe([ 'filter', 'instanceIdToInstanceMap' ], function (newFilter, instanceIdToInstanceMap) {
      updateView(newFilter, instanceIdToInstanceMap);
    });

    function updateView (newFilter, instanceIdToInstanceMap) {
      filter = angular.copy(newFilter);

      delete filter.page;
      delete filter.activityIds;
      delete filter.scrollToBpmnElement;

      // fix missmatch -> activityInstanceIds -> activityInstanceIdIn
      filter.activityInstanceIdIn = filter.activityInstanceIds;
      delete filter.activityInstanceIds;

      $scope.calledProcessInstances = null;

      BpmnCollaborationLinkedProcessInstanceResource.query({id: $scope.processInstance.id}, filter).$then(function(response) {

        angular.forEach(response.data, function (linkedInstance) {
          var instance = instanceIdToInstanceMap[linkedInstance.callActivityInstanceId];
          linkedInstance.instance = instance;
        });

        $scope.calledProcessInstances = response.data;
      });      
    }
  };
  module.controller('BpmnCollaborationController', [ '$scope', 'BpmnCollaborationLinkedProcessInstanceResource', BpmnCollaborationController ]);



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
