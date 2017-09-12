'use strict';

var CustomContextPadProvider = require('./custom/CustomContextPadProvider');
var CustomPaletteProvider = require('./custom/CustomPaletteProvider');
var CustomReplaceMenuProvider = require('./custom/CustomReplaceMenuProvider')

function ReducedPalettePlugin(bpmnjs, contextPad, palette, create, elementFactory, spaceTool, lassoTool, handTool, globalConnect, translate, eventBus, modeling, connect, popupMenu, canvas, rules, bpmnReplace, moddle, replaceMenuProvider) {

    var customContextPad = new CustomContextPadProvider(eventBus, contextPad, modeling, elementFactory, connect, create, popupMenu, canvas, rules, translate);
    contextPad._providers = [];
    contextPad.registerProvider(customContextPad);

    var customPalette = new CustomPaletteProvider(palette, create, elementFactory, spaceTool, lassoTool, handTool, globalConnect, translate);
    palette._providers = [];
    palette.registerProvider(customPalette);

    var customReplaceMenu = new CustomReplaceMenuProvider(popupMenu, modeling, moddle, bpmnReplace, rules, translate);
    replaceMenuProvider._popupMenu.registerProvider('bpmn-replace', customReplaceMenu);
}

ReducedPalettePlugin.$inject = [ 'bpmnjs', 'contextPad', 'palette', 'create', 'elementFactory', 'spaceTool', 'lassoTool', 'handTool', 'globalConnect', 'translate', 'eventBus', 'modeling', 'connect', 'popupMenu', 'canvas', 'rules', 'bpmnReplace', 'moddle', 'replaceMenuProvider' ];

module.exports = {
    __init__: ['reducedPalettePlugin'],
    reducedPalettePlugin: ['type', ReducedPalettePlugin]
};
