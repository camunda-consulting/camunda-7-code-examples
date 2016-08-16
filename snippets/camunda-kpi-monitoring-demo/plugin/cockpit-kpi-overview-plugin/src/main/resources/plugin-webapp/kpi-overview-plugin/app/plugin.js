/*global
  ngDefine,$
*/
'use strict';
ngDefine('cockpit.plugin.kpi-overview-plugin', ['module:cockpit.plugin.kpi-overview-plugin.controllers:./components/controllers'], function(module) {

    var Configuration = function Configuration(ViewsProvider) {
        ViewsProvider.registerDefaultView('cockpit.processInstance.history.tab', {
            id: 'kpi-overview',
            label: 'KPI Overview',
            url: 'plugin://kpi-overview-plugin/static/app/components/processinstance/kpiTaskView.html',
            dashboardMenuLabel: 'KPI Overview',
            controller: 'kpiTaskController',
            priority: 15
        });

        ViewsProvider.registerDefaultView('cockpit.processDefinition.history.tab', {
            id: 'kpi-overview-definition',
            label: 'KPI Overview ',
            url: 'plugin://kpi-overview-plugin/static/app/components/processdefinition/kpiProcessView.html',
            dashboardMenuLabel: 'KPI Overview',
            controller: 'kpiProcessController'
        });
    };

    Configuration.$inject = ['ViewsProvider'];

    module.config(Configuration);

    return module;
});
