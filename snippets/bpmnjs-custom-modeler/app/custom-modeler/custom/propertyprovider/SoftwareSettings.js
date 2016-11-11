'use strict';

var entryFactory = require('bpmn-js-properties-panel/lib/factory/EntryFactory');

var is = require('bpmn-js/lib/util/ModelUtil').is;

module.exports = function(group, element) {

  // Only return an entry, if the currently selected
  // element is a start event.

  if (is(element, 'bpmn:ServiceTask')) {
    group.entries.push(entryFactory.textField({
      id : 'settings1',
      description : 'Test',
      label : 'Setting 1',
      modelProperty : 'setting1'
    }));
    group.entries.push(entryFactory.textField({
      id : 'settings2',
      description : 'Test',
      label : 'Setting 2',
      modelProperty : 'setting2'
    }));
    group.entries.push(entryFactory.selectBox({
      id : 'settings3',
      description : 'Test',
      label : 'Setting 3',
      selectOptions: [ { name: 'a', value: 'b' } ],
      modelProperty : 'setting3'
    }));
    group.entries.push(entryFactory.selectBox({
      id : 'settings4',
      description : 'Test',
      label : 'Setting 4',
      selectOptions: [ { name: 'a', value: 'b' } ],
      modelProperty : 'setting4'
    }));
  }
};
