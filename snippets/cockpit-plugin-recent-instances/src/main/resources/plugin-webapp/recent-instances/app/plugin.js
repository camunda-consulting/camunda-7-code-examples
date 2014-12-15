ngDefine('cockpit.plugin.recent-instances', function(module) {

  // define Angular ressource for REST communication
  var Resource = function ($resource, Uri) {
    return $resource(Uri.appUri('plugin://recent-instances/:engine/process-instance'), {
      query: { method: 'GET', isArray: true}
    });
  };
  module.factory('RecentProcessInstanceResource', Resource);
  
  // create controller to load data for HTML
  function RecentProcessInstanceController ($scope, RecentProcessInstanceResource) {
    // input: processInstance

    $scope.recentProcessInstances = null;
    RecentProcessInstanceResource.query().$promise.then(function(response) {
             $scope.recentProcessInstances = response;
    });      
  };
  module.controller('RecentProcessInstanceController', [ '$scope', 'RecentProcessInstanceResource', RecentProcessInstanceController ]);


  // register Plugin
  var Configuration = function PluginConfiguration(ViewsProvider) {

    ViewsProvider.registerDefaultView('cockpit.dashboard', {
      id: 'recent-process-instances',
      label: 'Recent Process Instances',
      url: 'plugin://recent-instances/static/app/recent-process-instance-table.html',
      controller: 'RecentProcessInstanceController',
      priority: 15
    });
  };

  Configuration.$inject = ['ViewsProvider'];

  module.config(Configuration);
});
