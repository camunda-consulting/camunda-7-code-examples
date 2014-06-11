ngDefine('cockpit.plugin.delinquent-instances', function(module) {

  // define Angular ressource for REST communication
  var Resource = function ($resource, Uri) {
    return $resource(Uri.appUri('plugin://delinquent-instances/:engine/process-instance'), {
      query: { method: 'GET', isArray: true}
    });
  };
  module.factory('DelinquentProcessInstanceResource', Resource);
  
  // create controller to load data for HTML
  function DelinquentProcessInstanceController ($scope, DelinquentProcessInstanceResource) {
    // input: processInstance

    $scope.delinquentProcessInstances = null;
    DelinquentProcessInstanceResource.query().$then(function(response) {
          $scope.delinquentProcessInstances = response.data;
    });      
  };
  module.controller('DelinquentProcessInstanceController', [ '$scope', 'DelinquentProcessInstanceResource', DelinquentProcessInstanceController ]);


  // register Plugin
  var Configuration = function PluginConfiguration(ViewsProvider) {

    ViewsProvider.registerDefaultView('cockpit.dashboard', {
      id: 'delinquent-process-instances',
      label: 'Delinquent Process Instances',
      url: 'plugin://delinquent-instances/static/app/delinquent-process-instance-table.html',
      controller: 'DelinquentProcessInstanceController',
      priority: 15
    });
  };

  Configuration.$inject = ['ViewsProvider'];

  module.config(Configuration);
});
