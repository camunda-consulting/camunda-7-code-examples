'use strict';

module.exports = function(electronApp, menuState) {
  return [{
    label: 'Toggle Color Picker',
    accelerator: 'CommandOrControl+P',
    enabled: function() {
      return true;
    },
    action: function() {
      electronApp.emit('menu:action', 'toggleColorPicker');
    }
  }];
};
