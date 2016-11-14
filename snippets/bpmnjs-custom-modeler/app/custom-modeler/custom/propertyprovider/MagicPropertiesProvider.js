'use strict';


var inherits = require('inherits');

var CamundaPropertiesProvider = require('bpmn-js-properties-panel/lib/provider/camunda/CamundaPropertiesProvider');
var processProps = require('bpmn-js-properties-panel/lib/provider/bpmn/parts/ProcessProps'),
    eventProps = require('bpmn-js-properties-panel/lib/provider/bpmn/parts/EventProps'),
    linkProps = require('bpmn-js-properties-panel/lib/provider/bpmn/parts/LinkProps'),
    documentationProps = require('bpmn-js-properties-panel/lib/provider/bpmn/parts/DocumentationProps'),
    idProps = require('bpmn-js-properties-panel/lib/provider/bpmn/parts/IdProps'),
    nameProps = require('bpmn-js-properties-panel/lib/provider/bpmn/parts/NameProps');

var softwareSettings = require('./SoftwareSettings.js');
    // The general tab contains all bpmn relevant properties.
    // The properties are organized in groups.
    function createGeneralTabGroups(element, bpmnFactory, elementRegistry) {

      var generalGroup = {
        id: 'general',
        label: 'General',
        entries: []
      };
      idProps(generalGroup, element, elementRegistry);
      nameProps(generalGroup, element);
      processProps(generalGroup, element);

      var detailsGroup = {
        id: 'details',
        label: 'Details',
        entries: []
      };
      linkProps(detailsGroup, element);
      eventProps(detailsGroup, element, bpmnFactory, elementRegistry);

      var documentationGroup = {
        id: 'documentation',
        label: 'Documentation',
        entries: []
      };

      documentationProps(documentationGroup, element, bpmnFactory);

      // Create a group called "Black Magic".
      var softwareSettingsGroup = {
        id: 'black-magic',
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



function MagicPropertiesProvider(eventBus, bpmnFactory, elementRegistry) {
  console.log(this);
  CamundaPropertiesProvider.call(this, eventBus, bpmnFactory, elementRegistry, []);
  this.getTabs = function(element) {
    var generalTab = {
      id: 'general',
      label: 'General',
      groups: createGeneralTabGroups(element, bpmnFactory, elementRegistry)
    };

    // Show general + "softwaresetting" tab
    return [
      generalTab
    ];
  };
}

inherits(MagicPropertiesProvider, CamundaPropertiesProvider);


module.exports = MagicPropertiesProvider;
