define(['angular'], function(angular) {

  var NiceExternalTaskFormController = function($scope, $http, Uri, $location) {

	console.log($scope);

	var formKey = $scope.task.formKey;
            
	var absoluteUrl = $location.absUrl();
	var url = $location.url();
	
	// remove everthing after '#/', e.g.:
	// '.../#/?task=abc&...' ---> '.../#/'
	absoluteUrl = absoluteUrl.replace(url, '/');
	
	// TODO: Create correct URL (maybe use a JSON as FormKey for URL name and URL target?).

  $scope.myExternalFormUrl  = encodeURI(formKey + '?taskId=' + $scope.task.id + '&callbackUrl=' + absoluteUrl);
	

	console.log(formKey);
	console.log($scope.myExternalFormUrl);
	
	// TODO HOw to get the tasklistForm?
	// copied from cam-tasklist-form.js - but how to get started?
	/*
	       $scope.$watch('tasklistForm', function(value) {
          $scope.$loaded = false;
          if (value) {
            parseForm(value);
          }
        });

        function parseForm(form) {
          var key = form.key,
              applicationContextPath = form.contextPath;

          // structure may be [embedded:][app:]formKey

          if (!key) {
            form.type = 'generic';
            return;
          }

          if (key.indexOf(EMBEDDED_KEY) === 0) {
            key = key.substring(EMBEDDED_KEY.length);
            form.type = 'embedded';
          } else {
            form.type = 'external';
          }

          if (key.indexOf(APP_KEY) === 0) {
            if (applicationContextPath) {
              key = compact([applicationContextPath, key.substring(APP_KEY.length)])
                .join('/')
                // prevents multiple "/" in the URI
                .replace(/\/([\/]+)/, '/');
            }
          }

          if(key.indexOf(ENGINE_KEY) === 0) {
            // resolve relative prefix
            key = Uri.appUri(key);
          }

          console.log( key );
        }
	*/
  };

  

  var Configuration = ['ViewsProvider', function(ViewsProvider) {

    ViewsProvider.registerDefaultView('tasklist.task.detail', {
      id: 'nice-external-forms-plugin',
      label: 'Formular',
      url: 'plugin://nice-external-forms-plugin/static/app/form.html',
      controller: NiceExternalTaskFormController,

      // same priority as disables form-detail plugin (https://github.com/camunda/camunda-tasklist-ui/blob/master/client/scripts/task/plugins/detail/cam-tasklist-task-detail-form-plugin.js) 
      priority: 1000
    });
  }];

 
  var ngModule = angular.module('tasklist.plugin.nice-external-forms-plugin', []);

  ngModule.config(Configuration);

  return ngModule;
});
