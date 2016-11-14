'use strict';

var entryFactory = require('bpmn-js-properties-panel/lib/factory/EntryFactory');
var cmdHelper = require('bpmn-js-properties-panel/lib/helper/CmdHelper');
var elementHelper = require('bpmn-js-properties-panel/lib/helper/ElementHelper');
var extensionElementsHelper = require('bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper');
var extensionElements = require('bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ExtensionElements');
var is = require('bpmn-js/lib/util/ModelUtil').is;
var getBusinessObject = require('bpmn-js/lib/util/ModelUtil').getBusinessObject;
var find = require('lodash/collection/find');

function findCamundaProperty(camundaProperties, binding) {
  return find(camundaProperties.get('values'), function(p) {
    return p.name === binding.name;
  });
}
function objectWithKey(key, value) {
  var obj = {};

  obj[key] = value;

  return obj;
};
function findExtension(element, type) {
  var bo = getBusinessObject(element);

  var extensionElements;

  if (is(bo, 'bpmn:ExtensionElements')) {
    extensionElements = bo;
  } else {
    extensionElements = bo.extensionElements;
  }

  if (!extensionElements) {
    return null;
  }

  return find(extensionElements.get('values'), function(e) {
    return is(e, type);
  });
}

module.exports = function(group, element, bpmnFactory) {

  var getElementValue = function(element,node,propertyName) {
    var bo = getBusinessObject(element);
    var elements = bo.get('extensionElements');
    if (elements!=null) {
      var camundaProperties = findExtension(elements, 'camunda:Properties');
      camundaProperties = camundaProperties.values.filter(function(element) {
        if (element.name === propertyName) {
          return element;
        }
      });
    }
    if (camundaProperties!= null && camundaProperties.length>0) {
      var returnValue = {};
      returnValue[propertyName] = camundaProperties[0].value;
      return returnValue;
    } else {
      return '';
    }
  };
  var setElementValue = function(element,values,node,propertyName) {
    var bo = getBusinessObject(element);
    var entry = values;
    var extensionElements = bo.get('extensionElements');
    var updates= [];
    if (!extensionElements) {
      extensionElements = bpmnFactory.create('bpmn:ExtensionElements');
      updates.push(cmdHelper.updateBusinessObject(
        element, bo, objectWithKey('extensionElements', extensionElements)
      ));
    }

    var camundaProperties = findExtension(extensionElements, 'camunda:Properties');
    if (!camundaProperties) {
      camundaProperties = bpmnFactory.create('camunda:Properties');
      updates.push(cmdHelper.addElementsTolist(
        element, extensionElements, 'values', [ camundaProperties ]
      ));
    }
    var existingCamundaProperty = findCamundaProperty(camundaProperties,{'name':propertyName});
    if (values != null && values[propertyName] != null) {
      values = values[propertyName];
    }
    var camundaProperty = bpmnFactory.create('camunda:Property', {
      name: propertyName,
      value: values || ''
    });
    updates.push(cmdHelper.addAndRemoveElementsFromList(
      element,
      camundaProperties,
      'values',
      null,
      [ camundaProperty ],
      existingCamundaProperty ? [ existingCamundaProperty ] : []
    ));
    return updates;
  };

  var createTextField = function(id, label) {
    return entryFactory.textField({
      id : id,
      description : label,
      label : label,
      modelProperty : id,
      get: function(element,node) {
        return getElementValue(element, node, id);
      },
      set: function(element,values,node) {
        return setElementValue(element,values,node, id);
      }
    });
  };
  var createSelectBox = function(id, label, selectOptions) {
    return entryFactory.selectBox({
      id : id,
      description : label,
      label : label,
      selectOptions: selectOptions,
      modelProperty : id,
      get: function(element,node) {
        return getElementValue(element, node, id);
      },
      set: function(element,values,node) {
        return setElementValue(element,values,node, id);
      }
    });
  };
  if (is(element, 'bpmn:ServiceTask')) {
    group.entries.push(createTextField('setting0','Setting 0'));
    group.entries.push(createTextField('setting1','Setting 1'));
    group.entries.push(createTextField('setting2','Setting 2'));
    group.entries.push(createSelectBox('setting3', 'Settings 3', [ { name: 'a', value: 'b' },{ name: 'b', value: 'c' } ]));
  }
};
