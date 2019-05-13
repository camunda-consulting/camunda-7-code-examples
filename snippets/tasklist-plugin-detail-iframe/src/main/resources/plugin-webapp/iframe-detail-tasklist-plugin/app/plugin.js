define(['angular'], function(angular) {

  var Configuration = ['ViewsProvider', function(ViewsProvider) {

    ViewsProvider.registerDefaultView('tasklist.task.detail', {
      id: 'iframe-detail-tasklist-plugin',
      label: 'iframe Detail',
      url: 'plugin://iframe-detail-tasklist-plugin/static/app/iframe.html',

      // less priority as form-detail plugin (https://github.com/camunda/camunda-tasklist-ui/blob/master/client/scripts/task/plugins/detail/cam-tasklist-task-detail-form-plugin.js) 
      priority: 800
    });
  }];

  var ngModule = angular.module('tasklist.plugin.iframe-detail-tasklist-plugin', []);

  ngModule.config(Configuration);

  return ngModule;
});
