'use strict';
angular.module('cockpit.plugin.kpi-overview-plugin').factory('kpiExtractor', function() {
    var extract = function(extensionElements) {
        var kpiInformation = {};
        if (extensionElements && extensionElements.values && extensionElements.values.length > 0) {
            var camundaProperties = extensionElements.values.filter(function(extensionElementValue) {
                if (extensionElementValue.$type === 'camunda:properties') {
                    if (extensionElementValue.$children && extensionElementValue.$children.length > 0) {
                        extensionElementValue.$children.filter(function(extensionProperty) {
                            if (extensionProperty.name === 'kpi') {
                                kpiInformation['kpi'] = extensionProperty.value;
                            }
                            if (extensionProperty.name === 'kpiunit') {
                                kpiInformation['kpiunit'] = extensionProperty.value;
                            }
                        });
                    }
                    return true;
                }
            });
        }
        return kpiInformation;
    };
    return {
        extract: extract
    }
});
