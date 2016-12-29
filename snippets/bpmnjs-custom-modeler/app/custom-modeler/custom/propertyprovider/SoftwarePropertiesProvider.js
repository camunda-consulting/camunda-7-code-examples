'use strict';


var inherits = require('inherits');

var CamundaPropertiesProvider = require('bpmn-js-properties-panel/lib/provider/camunda/CamundaPropertiesProvider');
var processProps = require('bpmn-js-properties-panel/lib/provider/bpmn/parts/ProcessProps'),
    eventProps = require('bpmn-js-properties-panel/lib/provider/bpmn/parts/EventProps'),
    linkProps = require('bpmn-js-properties-panel/lib/provider/bpmn/parts/LinkProps'),
    documentationProps = require('bpmn-js-properties-panel/lib/provider/bpmn/parts/DocumentationProps'),
    idProps = require('bpmn-js-properties-panel/lib/provider/bpmn/parts/IdProps'),
    nameProps = require('bpmn-js-properties-panel/lib/provider/bpmn/parts/NameProps'),
    softwareSettings = require('./SoftwareSettings.js');

    function createGeneralTabGroups(element, bpmnFactory, elementRegistry) {
      var generalGroup = {
        id: 'general',
        label: 'General',
        entries: []
      };
      idProps(generalGroup, element, elementRegistry);
      nameProps(generalGroup, element);

      // Create a group called "Software Settings".
      var softwareSettingsGroup = {
        id: 'software-setting',
        label: 'Software Settings',
        entries: []
      };

      // Add the custom props to the softwareSettingsGroup.
      softwareSettings(softwareSettingsGroup, element, bpmnFactory);

      return[
        generalGroup,
        softwareSettingsGroup
      ];
    }

function SoftwarePropertiesProvider(eventBus, bpmnFactory, elementRegistry) {
  CamundaPropertiesProvider.call(this, eventBus, bpmnFactory, elementRegistry, []);

  this.getTabs = function(element) {
    var generalTab = {
      id: 'general',
      label: 'General',
      groups: createGeneralTabGroups(element, bpmnFactory, elementRegistry)
    };

    return [
      generalTab
    ];
  };
}

inherits(SoftwarePropertiesProvider, CamundaPropertiesProvider);

module.exports = SoftwarePropertiesProvider;
