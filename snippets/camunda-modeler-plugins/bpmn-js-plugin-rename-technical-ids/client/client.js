var registerBpmnJSPlugin = require('camunda-modeler-plugin-helpers').registerBpmnJSPlugin;

var RenameTechnicalIDsPlugin = require('./RenameTechnicalIDsPlugin');
registerBpmnJSPlugin(RenameTechnicalIDsPlugin);
