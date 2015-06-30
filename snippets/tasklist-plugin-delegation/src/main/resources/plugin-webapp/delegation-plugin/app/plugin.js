define(['angular'], function(angular) {

  var DelegationController = function($scope, $http, Uri, $location) {

	$scope.users = [];
	console.log($scope);

	var formKey = $scope.task.formKey;
            
	var absoluteUrl = $location.absUrl();
	var url = $location.url();
	
	// remove everthing after '#/', e.g.:
	// '.../#/?task=abc&...' ---> '.../#/'
	absoluteUrl = absoluteUrl.replace(url, '/');
	
	// TODO: Create correct URL (maybe use a JSON as FormKey for URL name and URL target?).
	// TODO: Check why this is not updated on form in UI
	//$scope.$apply(function () {
		$scope.myExternalFormUrl  = encodeURI(formKey + '?taskId=' + $scope.task.id + '&callbackUrl=' + absoluteUrl);
	//});

	console.log(formKey);
	console.log($scope.myExternalFormUrl);
  };

  

  var Configuration = ['ViewsProvider', function(ViewsProvider) {

    ViewsProvider.registerDefaultView('tasklist.task.detail', {
      id: 'delegation-plugin',
      label: 'Delegation',
      url: 'plugin://delegation-plugin/static/app/delegate.html',
      controller: DelegationController,

      // less priority as form-detail plugin (https://github.com/camunda/camunda-tasklist-ui/blob/master/client/scripts/task/plugins/detail/cam-tasklist-task-detail-form-plugin.js) 
      priority: 800
    });
  }];

 
  var ngModule = angular.module('tasklist.plugin.delegation-plugin', []);

  ngModule.config(Configuration);

  return ngModule;
});
