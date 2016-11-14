'use strict';
var $ = require('jquery');
// inlined diagram; load it from somewhere else if you like
var diagram = require('../resources/diagram.bpmn');

// custom elements JSON; load it from somewhere else if you like
var customElements = require('./custom-elements.json');


// our custom modeler
var CustomModeler = require('./custom-modeler');
var propertiesPanelModule = require('bpmn-js-properties-panel'),
    propertiesProviderModule = require('./custom-modeler/custom/propertyprovider'),
    camundaModdleDescriptor = require('camunda-bpmn-moddle/resources/camunda');

var modeler = new CustomModeler({
  container: '#canvas',
  keyboard: { bindTo: document },
  propertiesPanel: {
    parent: '#js-properties-panel'
  },
  additionalModules: [
    propertiesPanelModule,
    propertiesProviderModule
  ],
  moddleExtensions: {
    camunda: camundaModdleDescriptor
  }
});

modeler.importXML(diagram, function(err) {

  if (err) {
    console.error('something went wrong:', err);
  }

  modeler.get('canvas').zoom('fit-viewport');

  modeler.addCustomElements(customElements);

  function saveSVG(event) {
    modeler.saveSVG(function(err,svg) {
      $(event.target).attr({
        'href': 'data:application/bpmn20-xml;charset=UTF-8,' + svg,
        'download': 'name.svg'
      });
    });
  }
  function saveDiagram(event) {
    modeler.saveXML({ format: true }, function(err, xml) {
        $(event.target).attr({
          'href': 'data:application/bpmn20-xml;charset=UTF-8,' + xml,
          'download': 'name.bpmn'
        });
    });
  }
  var downloadLink = $('#js-download-diagram');
  var downloadSvgLink = $('#js-download-svg');
  downloadLink.on('click',saveDiagram);
  downloadSvgLink.on('click',saveSVG);
});

// expose bpmnjs to window for debugging purposes
window.bpmnjs = modeler;
