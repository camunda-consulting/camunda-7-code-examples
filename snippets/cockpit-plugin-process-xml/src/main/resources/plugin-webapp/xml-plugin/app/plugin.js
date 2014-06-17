ngDefine('cockpit.plugin.xml-plugin', function(module) {

  var ProcessXMLController = function($scope, $http, Uri) {

    $http.get(Uri.appUri("engine://engine/:engine/process-definition/"+$scope.processDefinition.id+"/xml"))
      .success(function(data) {
        $scope.processModel = data.bpmn20Xml;
      });
  };

  ProcessXMLController.$inject = ["$scope", "$http", "Uri"];

  var Configuration = function Configuration(ViewsProvider) {

    ViewsProvider.registerDefaultView('cockpit.processDefinition.runtime.tab', {
	      id: 'process-model-xml',
	      label: 'XML',
	      url: 'plugin://xml-plugin/static/app/processmodel.html',
	      controller: ProcessXMLController,

	      priority: 9
	    });
  	};

  Configuration.$inject = ['ViewsProvider'];

  module.config(Configuration);
});