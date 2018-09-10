var registerBpmnJSPlugin = require('camunda-modeler-plugin-helpers').registerBpmnJSPlugin;

var GeneratedFormPreviewPluginProvider = require('./GeneratedFormPreviewPluginProvider');
registerBpmnJSPlugin(GeneratedFormPreviewPluginProvider);
