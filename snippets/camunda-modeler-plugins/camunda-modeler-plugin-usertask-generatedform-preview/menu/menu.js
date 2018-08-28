'use strict';

module.exports = function(electronApp, menuState) {
  return [{
    label: 'Generated Forms Preview',
    enabled: function() {
      return true;
    },
    action: function() {
    }
  }];
};
