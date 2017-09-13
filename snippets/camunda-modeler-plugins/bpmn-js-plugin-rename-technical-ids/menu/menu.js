'use strict';

module.exports = function(electronApp, menuState) {
  return [{
    label: 'Generate IDs',
    accelerator: 'CommandOrControl+G',
    enabled: function() {
      return true;
    },
    action: function() {
      electronApp.emit('menu:action', 'generateIDs');
    }
  }];
};
