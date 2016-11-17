module.exports = {
  __init__: [ 'customRenderer', 'paletteProvider', 'customRules', 'customUpdater', 'contextPadProvider', 'replaceMenuProvider' ],
  elementFactory: [ 'type', require('./CustomElementFactory') ],
  customRenderer: [ 'type', require('./CustomRenderer') ],
  paletteProvider: [ 'type', require('./CustomPalette') ],
  customRules: [ 'type', require('./CustomRules') ],
  customUpdater: [ 'type', require('./CustomUpdater') ],
  replaceMenuProvider: [ 'type', require('./CustomReplaceMenuProvider') ],
  contextPadProvider: [ 'type', require('./CustomContextPadProvider') ]
};
