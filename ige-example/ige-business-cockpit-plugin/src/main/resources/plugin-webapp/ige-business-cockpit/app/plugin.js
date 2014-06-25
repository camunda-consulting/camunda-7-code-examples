ngDefine('cockpit.plugin.ige-business-cockpit',	 
		 function(module) {

  var IGEBusinessDataController = function($scope, $http, Uri) {
	  
	  $scope.businessdatas = [];
	  
	  $scope.search = function() {
		  $http.get(Uri.appUri("plugin://ige-business-cockpit/default/business-data?titlenumber=" 
    		+ $scope.search.titleNumber))
				  .success(function(data) {
					  // Data Binding
					  $scope.businessdatas = data;
				  });
	  };
  };
  IGEBusinessDataController.$inject = ["$scope", "$http", "Uri"];

  var Configuration = function Configuration(ViewsProvider) {

	    ViewsProvider.registerDefaultView('cockpit.dashboard', {
	      id: 'ige-business-data',
	      label: 'Business Data',
	      url: 'plugin://ige-business-cockpit/static/app/BusinessData.html',
	      controller: IGEBusinessDataController,

	      // make sure we have a higher priority than the default plugin
	      priority: 12
	    });
	  };

	  Configuration.$inject = ['ViewsProvider'];
	  module.config(Configuration);
	  return module;  
});

