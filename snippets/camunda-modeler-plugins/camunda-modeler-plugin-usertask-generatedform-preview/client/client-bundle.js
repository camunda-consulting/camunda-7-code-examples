/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = "./client/client.js");
/******/ })
/************************************************************************/
/******/ ({

/***/ "./client/GeneratedFormPreviewPluginProvider.js":
/*!******************************************************!*\
  !*** ./client/GeneratedFormPreviewPluginProvider.js ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify;
var CamundaPropertiesProvider = __webpack_require__(/*! bpmn-js-properties-panel/lib/provider/camunda/CamundaPropertiesProvider */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/CamundaPropertiesProvider.js");
var formHelper = __webpack_require__(/*! bpmn-js-properties-panel/lib/helper/FormHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/FormHelper.js");
var swal = __webpack_require__(/*! sweetalert */ "./node_modules/sweetalert/dist/sweetalert.min.js");
var copy = __webpack_require__(/*! clipboard-copy */ "./node_modules/clipboard-copy/index.js");

function GeneratedFormPreviewPluginProvider(injector) {
  var camunda = new CamundaPropertiesProvider(...CamundaPropertiesProvider.$inject.map(dependency => injector.get(dependency)));

  this.getTabs = function(element) {
    var array = camunda.getTabs(element);
    var formIndex;
    var formsTab = array.filter(function(item, index) {
      if (item.id == 'forms') {
        formIndex = index;
        return true;
      }
    });
    if (formsTab.length > 0) {
      var newFormsTab = this.getFormsTab(formsTab[0]);
      array[formIndex] = newFormsTab;
    }
    return array;
  };
};



GeneratedFormPreviewPluginProvider.prototype.getFormsTab = function(formsTab) {
  var self = this;
  if (formsTab.groups.length > 0 && formsTab.groups[0].entries.length > 0) {
    formsTab.groups[0].entries.splice(2, 0, {
      html: "<button id='preview-button' data-action='openPreview'>Preview Form</button>",
      id: "form-fields-generate-button",
      openPreview: function(element, node) {
        var formFields = formHelper.getFormFields(element);
        if (formFields != null) {
          self.generateHTML(formFields);
        }
      }
    });
  }
  return formsTab;
};

GeneratedFormPreviewPluginProvider.prototype.generateHTML = function(formFields) {
  var self = this;
  var fullHtml = '';
  var source = '';
  formFields.forEach(function(formField) {
    if (formField.type != null) {
      fullHtml += self.generateHTMLSnippet(formField);
      source += self.generateSource(formField);
    }
  });
  var fullSource = `<div>
      <p><i>Hint: You can just copy and paste this source into your embedded form.</i></p>
      <button id="copytext" class="copy-button">Copy</button>
      <textarea id="copytextarea" class="form-control" style="height:200px;overflow:auto;">
        <form class="form-horizontal">
          <div class="col-xs-12">
            ${source}
          </div>
        <script cam-script type="text/form-script">
          // custom JavaScript goes here
        </script>
        </form>
      </textarea>
    </div>`;
  var tabPanel = `<div>
    <div class="tab">
      <button class="tablinks" data-id="tabform">Form</button>
      <button class="tablinks" data-id="tabsource">Source</button>
    </div>
    <div id="tabform" class="tabcontent">
      <form>
        ${fullHtml}
      </form>
    </div>
    <div id="tabsource" class="tabcontent">
      ${fullSource}
    </div>
    </div>`;
  var domHtml = domify(tabPanel);
  swal({
    text: "Generated Forms",
    content: domHtml
  });
  var tablinks = document.querySelectorAll(".tablinks");
  tablinks.forEach(function(tablink) {
    tablink.addEventListener("click", function(event) {
      var id = event.target.getAttribute("data-id");
      self.openTab(event, id);
    });
  });
  tablinks[0].click();
  var copybutton = document.querySelector("#copytext");
  copybutton.addEventListener('click', function() {
    var text = document.querySelector("#copytextarea").value;
    copy(text);
  });
};

GeneratedFormPreviewPluginProvider.prototype.openTab = function(evt, tabName) {

  // Declare all variables
  var i, tabcontent, tablinks;

  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  // Show the current tab, and add an "active" class to the button that opened the tab
  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";
}

GeneratedFormPreviewPluginProvider.prototype.generateSource = function(formField) {
  switch (formField.type) {
    case 'string':
      return `<div class="form-group">
            <label for="${formField.id}">
              ${formField.label}
            </label>
            <input class="form-control" name="${formField.id}" cam-variable-type="String" cam-variable-name="${formField.id}" type="text">
          </div>`;
      break;
    case 'long':
      return `<div class="form-group">
            <label for="${formField.id}">
              ${formField.label}
            </label>
            <input class="form-control" name="${formField.id}" cam-variable-type="Long" cam-variable-name="${formField.id}" type="text">
          </div>`;
      break;
    case 'boolean':
      return `<div class="form-group">
            <label for="${formField.id}">
              ${formField.label}
            </label>
            <input class="form-control" name="${formField.id}" cam-variable-type="Boolean" cam-variable-name="${formField.id}" type="checkbox">
          </div>`;
      break;
    case 'date':
      return `<div class="form-group">
                  <label for="${formField.id}">
                    ${formField.label}
                  </label>
                  <p class="input-group">
                    <input type="text"
                         cam-variable-name="${formField.id}"
                         cam-variable-type="Date"
                         class="form-control"
                         datepicker-popup="yyyy-MM-dd'T'HH:mm:ss"
                         is-open="dateFieldOpened${formField.id}" />

                    <span class="input-group-btn">
                      <button type="button"
                              class="btn btn-default"
                              ng-click="open${formField.id}($event)">
                        <i class="glyphicon glyphicon-calendar"></i>
                      </button>
                    </span>
                  </p>
                  <script cam-script type="text/form-script">
                    $scope.open${formField.id} = function($event) {
                      $event.preventDefault();
                      $event.stopPropagation();

                      $scope.dateFieldOpened${formField.id} = true;
                    };
                  </script>
            </div>`;
      break;
    case 'enum':
      var options = '';
      if (formField.values && formField.values.length > 0) {
        formField.values.forEach(function(value) {
          options += `<option value="${value.id}">${value.name}</option>`;
        });
      }

      return `<div class="form-group">
          <label for="${formField.id}">
            ${formField.label}
          </label>
          <select class="form-control" cam-variable-type="String" cam-variable-name="${formField.id}">
            ${options}
          </select></div>`;
      break;
    default:
      return '';

  }
};

GeneratedFormPreviewPluginProvider.prototype.generateHTMLSnippet = function(formField) {
  switch (formField.type) {
    case 'string':
      return `<div class="form-group">
            <label for="${formField.id}">
              ${formField.label}
            </label>
            <input class="form-control" name="${formField.id}" cam-variable-type="String" cam-variable-name="${formField.id}" type="text">
          </div>`;
      break;
    case 'long':
      return `<div class="form-group">
            <label for="${formField.id}">
              ${formField.label}
            </label>
            <input class="form-control" name="${formField.id}" cam-variable-type="Long" cam-variable-name="${formField.id}" type="text">
          </div>`;
      break;
    case 'boolean':
      return `<div class="form-group">
            <label for="${formField.id}">
              ${formField.label}
            </label>
            <input class="form-control" name="${formField.id}" cam-variable-type="Boolean" cam-variable-name="${formField.id}" type="checkbox">
          </div>`;
      break;
    case 'date':
      return `<div class="form-group">
            <label for="${formField.id}">
              ${formField.label}
            </label>
            <input class="form-control" name="${formField.id}" cam-variable-type="Date" cam-variable-name="${formField.id}" type="date">
          </div>`;
      break;
    case 'enum':
      var options = '<option></option>';
      if (formField.values && formField.values.length > 0) {
        formField.values.forEach(function(value) {
          options += `<option value="${value.id}">${value.name}</option>`;
        });
      }

      return `<div class="form-group">
          <label for="${formField.id}">
            ${formField.label}
          </label>
          <select class="form-control" cam-variable-type="String" cam-variable-name="${formField.id}">
            ${options}
          </select></div>`;
      break;
    default:
      return '';

  }
};


GeneratedFormPreviewPlugin.$inject = ['injector'];

function GeneratedFormPreviewPlugin() {

};

module.exports = {
  __init__: ['generatedFormPreviewPlugin'],
  propertiesProvider: ['type', GeneratedFormPreviewPluginProvider],
  generatedFormPreviewPlugin: ['type', GeneratedFormPreviewPlugin]
};


/***/ }),

/***/ "./client/client.js":
/*!**************************!*\
  !*** ./client/client.js ***!
  \**************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var registerBpmnJSPlugin = __webpack_require__(/*! camunda-modeler-plugin-helpers */ "./node_modules/camunda-modeler-plugin-helpers/index.js").registerBpmnJSPlugin;

var GeneratedFormPreviewPluginProvider = __webpack_require__(/*! ./GeneratedFormPreviewPluginProvider */ "./client/GeneratedFormPreviewPluginProvider.js");
registerBpmnJSPlugin(GeneratedFormPreviewPluginProvider);


/***/ }),

/***/ "./node_modules/@bpmn-io/extract-process-variables/dist/index.esm.js":
/*!***************************************************************************!*\
  !*** ./node_modules/@bpmn-io/extract-process-variables/dist/index.esm.js ***!
  \***************************************************************************/
/*! exports provided: getProcessVariables, getVariablesForScope */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getProcessVariables", function() { return getProcessVariables; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getVariablesForScope", function() { return getVariablesForScope; });
/* harmony import */ var min_dash__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js");


/**
 * Get a inputOutput from the business object
 *
 * @param {ModdleElement} element
 *
 * @return {ModdleElement} the inputOutput object
 */
function getInputOutput(element) {
  return (getElements(element, 'camunda:InputOutput') || [])[0];
}

/**
 * Return all input parameters existing in the business object, and
 * an empty array if none exist.
 *
 * @param  {ModdleElement} element
 *
 * @return {Array<ModdleElement>} a list of input parameter objects
 */
function getInputParameters(element) {
  return getParameters(element, 'inputParameters');
}

/**
 * Return all output parameters existing in the business object, and
 * an empty array if none exist.
 *
 * @param  {ModdleElement} element
 * @param  {boolean} insideConnector
 *
 * @return {Array<ModdleElement>} a list of output parameter objects
 */
function getOutputParameters(element) {
  return getParameters(element, 'outputParameters');
}

/**
 * Return all form fields existing in the business object, and
 * an empty array if none exist.
 *
 * @param {ModdleElement} element
 *
 * @return {Array<ModdleElement>} a list of form fields
 */
function getFormFields(element) {
  var formData = getFormData(element);
  return (formData && formData.get('fields')) || [];
}

/**
 * Return form data existing in the business object
 *
 * @param {ModdleElement} element
 *
 * @return {ModdleElement}
 */
function getFormData(element) {
  return (getElements(element, 'camunda:FormData') || [])[0];
}

/**
 * Return out mappings existing in the business object
 *
 * @param {ModdleElement} element
 *
 * @return {Array<ModdleElement>}
 */
function getOutMappings(element) {
  return getElements(element, 'camunda:Out') || [];
}


// helpers //////////

function getElements(element, type, property) {
  var elements = getExtensionElements(element, type) || [];

  return !property ? elements : (elements[0] || {})[property] || [];
}

function getParameters(element, property) {
  var inputOutput = getInputOutput(element);

  return (inputOutput && inputOutput.get(property)) || [];
}

function getExtensionElements(element, type) {
  var extensionElements = element.get('extensionElements');

  if (typeof extensionElements !== 'undefined') {
    var extensionValues = extensionElements.get('values');

    if (typeof extensionValues !== 'undefined') {
      var elements = Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["filter"])(extensionValues, function(value) {
        return is(value, type);
      });

      if (elements.length) {
        return elements;
      }
    }
  }
}

function is(element, type) {
  return (
    element &&
    typeof element.$instanceOf === 'function' &&
    element.$instanceOf(type)
  );
}

/**
 * Get all parent elements for a given element.
 *
 * @param {ModdleElement|string} element
 *
 * @returns {Array<ModdleElement>}
 */
function getParents(element) {
  var parents = [];
  var current = element;

  while (current.$parent) {
    parents.push(current.$parent);
    current = current.$parent;
  }

  return parents;
}

/**
 * Iterate over each element in a collection, calling the iterator function `fn`
 * with (element, index, recursionDepth).
 *
 * Recurse into all elements that are returned by `fn`.
 *
 * @param  {Object|Array<Object>} elements
 * @param  {Function} fn iterator function called with (element, index, recursionDepth)
 * @param  {number} [depth] maximum recursion depth
 */
function eachElement(elements, fn, depth) {
  depth = depth || 0;

  if (!Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["isArray"])(elements)) {
    elements = [elements];
  }

  Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["forEach"])(elements, function(s, i) {
    var filter = fn(s, i, depth);

    if (Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["isArray"])(filter) && filter.length) {
      eachElement(filter, fn, depth + 1);
    }
  });
}

/**
 * Adds an element to a collection and returns true if the
 * element was added.
 *
 * @param {Array<Object>} elements
 * @param {Object} e
 * @param {boolean} unique
 */
function add(elements, e, unique) {
  var canAdd = !unique || elements.indexOf(e) === -1;

  if (canAdd) {
    elements.push(e);
  }

  return canAdd;
}

/**
 * Collects self + flow elements up to a given depth from a list of elements.
 *
 * @param  {ModdleElement|Array<ModdleElement>} elements the elements to select the flowElements from
 * @param  {boolean} unique whether to return a unique result set (no duplicates)
 * @param  {number} maxDepth the depth to search through or -1 for infinite
 *
 * @return {Array<ModdleElement>} found elements
 */
function selfAndFlowElements(elements, unique, maxDepth) {
  var result = [],
      processedFlowElements = [];

  eachElement(elements, function(element, i, depth) {
    add(result, element, unique);

    var flowElements = element.flowElements;

    // max traversal depth not reached yet
    if (maxDepth === -1 || depth < maxDepth) {

      // flowElements exist && flowElements not yet processed
      if (flowElements && add(processedFlowElements, flowElements, unique)) {
        return flowElements;
      }
    }
  });

  return result;
}

/**
 * Return self + ALL flowElements for a number of elements
 *
 * @param  {Array<ModdleElement>} elements to query
 * @param  {boolean} allowDuplicates to allow duplicates in the result set
 *
 * @return {Array<ModdleElement>} the collected elements
 */
function selfAndAllFlowElements(elements, allowDuplicates) {
  return selfAndFlowElements(elements, !allowDuplicates, -1);
}

/**
 * Return full moddle element for given element id
 *
 * @param {string} elementId
 * @param {ModdleElement} rootElement
 *
 * @returns {ModdleElement}
 */
function getElement(elementId, rootElement) {
  var allElements = selfAndAllFlowElements(rootElement);

  return Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["find"])(allElements, function(element) {
    return element.id === elementId;
  });
}

function addVariableToList(variablesList, newVariable) {
  var foundIdx = Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["findIndex"])(variablesList, function(variable) {
    return (
      variable.name === newVariable.name && variable.scope === newVariable.scope
    );
  });

  if (foundIdx >= 0) {
    variablesList[foundIdx].origin = combineArrays(
      variablesList[foundIdx].origin,
      newVariable.origin
    );
  } else {
    variablesList.push(newVariable);
  }
}

/**
 * Creates new process variable definition object
 * Identifies correct (highest) scope, in which variable is available
 *
 * @param {ModdleElement} flowElement
 * @param {String} name
 * @param {ModdleElement} defaultScope
 *
 * @returns {ProcessVariable}
 */
function createProcessVariable(flowElement, name, defaultScope) {
  var scope = getScope(flowElement, defaultScope, name);

  return {
    name: name,
    origin: [flowElement],
    scope: scope,
  };
}


// helpers ////////////////////

/**
 * Set parent container if it defines it's own scope for the variable, so
 * when it defines an input mapping for it. Otherwise returns the default global scope
 */
function getScope(element, globalScope, variableName) {
  var parents = getParents(element);

  var scopedParent = Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["find"])(parents, function(parent) {
    return (
      is$1(parent, 'bpmn:SubProcess') && hasInputParameter(parent, variableName)
    );
  });

  return scopedParent ? scopedParent : globalScope;
}

function is$1(element, type) {
  return (
    element &&
      typeof element.$instanceOf === 'function' &&
      element.$instanceOf(type)
  );
}

function hasInputParameter(element, name) {
  return Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["find"])(getInputParameters(element), function(input) {
    return input.name === name;
  });
}

function combineArrays(a, b) {
  return a.concat(b);
}

/**
 * Retrieves process variables defined in output parameters, e.g.
 *
 * <camunda:inputOutput>
 *   <camunda:outputParameter name="variable1">200</camunda:outputParameter>
 *   <camunda:outputParameter name="variable2">${myLocalVar + 20}</camunda:outputParameter>
 * </camunda:inputOutput>
 *
 * => Adds two variables "variable1" & "variable2" to the list.
 *
 */
function extractOutputParameters(options) {
  var elements = options.elements,
      containerElement = options.containerElement,
      processVariables = options.processVariables;

  if (!Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["isArray"])(elements)) {
    elements = [ elements ];
  }

  Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["forEach"])(elements, function(element) {

    // variables are created by output parameters
    var outputParameters = getOutputParameters(element);

    // extract all variables with correct scope
    Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["forEach"])(outputParameters, function(parameter) {
      var newVariable = createProcessVariable(
        element,
        parameter.name,
        containerElement
      );

      addVariableToList(processVariables, newVariable);
    });
  });

  return processVariables;
}

/**
 * Retrieves process variables defined in result variables, e.g.
 *
 * <bpmn:sendTask
 *   id="SendTask_1"
 *   camunda:expression="${myBean.ready}"
 *   camunda:resultVariable="variable1"
 * />
 *
 * => Adds one variable "variable1"to the list.
 *
 */
function extractResultVariables(options) {
  var elements = options.elements,
      containerElement = options.containerElement,
      processVariables = options.processVariables;

  if (!Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["isArray"])(elements)) {
    elements = [ elements ];
  }

  Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["forEach"])(elements, function(element) {

    var resultVariable = getResultVariable(element);

    if (resultVariable) {
      var newVariable = createProcessVariable(
        element,
        resultVariable,
        containerElement
      );

      addVariableToList(processVariables, newVariable);
    }
  });

  return processVariables;
}


// helpers ///////////////////////

function getResultVariable(element) {
  return element.get('camunda:resultVariable');
}

/**
 * Retrieves process variables defined in form fields, e.g.
 *
 * <camunda:formData>
 *   <camunda:formField id="variable1" />
 *   <camunda:formField id="variable2" />
 * </camunda:formData>
 *
 * => Adds two variables "variable1" & "variable2" to the list.
 *
 */
function extractFormFields(options) {
  var elements = options.elements,
      containerElement = options.containerElement,
      processVariables = options.processVariables;

  if (!Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["isArray"])(elements)) {
    elements = [ elements ];
  }

  Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["forEach"])(elements, function(element) {

    var formFields = getFormFields(element);

    // extract all variables with correct scope
    Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["forEach"])(formFields, function(field) {
      var newVariable = createProcessVariable(
        element,
        field.id,
        containerElement
      );

      addVariableToList(processVariables, newVariable);
    });
  });

  return processVariables;
}

/**
 * Retrieves process variables defined in output mappings and
 * ignores local variables, e.g.
 *
 * <bpmn:extensionElements>
 *   <camunda:out sourceExpression="${myBean.ready}" target="variable1" />
 *   <camunda:out source="foo" target="variableLocal" local="true" />
 * </bpmn:extensionElements>
 *
 * => Adds one variable "variable1" to the list.
 *
 */
function extractOutMappings(options) {
  var elements = options.elements,
      containerElement = options.containerElement,
      processVariables = options.processVariables;

  if (!Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["isArray"])(elements)) {
    elements = [ elements ];
  }

  Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["forEach"])(elements, function(element) {

    var outMappings = getOutMappings(element);

    // extract all variables with correct scope
    Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["forEach"])(outMappings, function(mapping) {

      // do not use variables marked as <local>
      if (mapping.local) {
        return;
      }

      var newVariable = createProcessVariable(
        element,
        mapping.target,
        containerElement
      );

      addVariableToList(processVariables, newVariable);
    });
  });

  return processVariables;
}

/**
 *
 * @param {ModdleElement} element
 * @param {string} [type] - optional
 *
 * @return {Array<ModdleElement>|undefined} collection of event definitions or none
 */
function getEventDefinitions(element, type) {
  var eventDefinitions = element.eventDefinitions;

  if (!eventDefinitions || !type) {
    return eventDefinitions;
  }

  return Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["filter"])(eventDefinitions, function(definition) {
    return is$2(definition, type);
  });
}

/**
 * Returns error event definitions for a given element.
 *
 * @param {ModdleElement} element
 *
 * @return {Array<ModdleElement>} collection of error event definitions
 */
function getErrorEventDefinitions(element) {
  return getEventDefinitions(element, 'bpmn:ErrorEventDefinition');
}

/**
 * Returns escalation event definitions for a given element.
 *
 * @param {ModdleElement} element
 *
 * @return {Array<ModdleElement>} collection of escalation event definitions
 */
function getEscalationEventDefinitions(element) {
  return getEventDefinitions(element, 'bpmn:EscalationEventDefinition');
}


// helper ////////////////

function is$2(element, type) {
  return (
    element &&
    typeof element.$instanceOf === 'function' &&
    element.$instanceOf(type)
  );
}

/**
 * Retrieves process variables defined in event definitions, e.g.
 *
 * <bpmn:escalationEventDefinition
 *   id="EscalationEventDefinition_1"
 *   escalationRef="Escalation_1"
 *   camunda:escalationCodeVariable="variable1"
 * />
 *
 * => Adds one variable "variable1" to the list.
 *
 * <bpmn:errorEventDefinition
 *   id="ErrorEventDefinition_1"
 *   errorRef="Error_1"
 *   camunda:errorCodeVariable="variable2"
 *   camunda:errorMessageVariable="variable3"
 * />
 *
 * => Adds two variables "variable2" & "variable3" to the list.
 *
 */
function extractEventDefinitionVariables(options) {
  var elements = options.elements,
      containerElement = options.containerElement,
      processVariables = options.processVariables;

  var addVariable = function(element, name) {
    var newVariable = createProcessVariable(
      element,
      name,
      containerElement
    );

    addVariableToList(processVariables, newVariable);
  };

  if (!Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["isArray"])(elements)) {
    elements = [ elements ];
  }

  Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["forEach"])(elements, function(element) {

    // (1) error event code + message variable
    var errorEventDefinitions = getErrorEventDefinitions(element);

    Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["forEach"])(errorEventDefinitions, function(definition) {

      var errorCodeVariable = definition.get('errorCodeVariable'),
          errorMessageVariable = definition.get('errorMessageVariable');

      if (errorCodeVariable) {
        addVariable(element, errorCodeVariable);
      }

      if (errorMessageVariable) {
        addVariable(element, errorMessageVariable);
      }
    });

    // (2) escalation code variable
    var escalationEventDefinitions = getEscalationEventDefinitions(element);

    Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["forEach"])(escalationEventDefinitions, function(definition) {

      var escalationCodeVariable = definition.get('escalationCodeVariable');

      if (escalationCodeVariable) {
        addVariable(element, escalationCodeVariable);
      }
    });

  });

  return processVariables;
}

var extractors = [
  extractOutputParameters,
  extractResultVariables,
  extractFormFields,
  extractOutMappings,
  extractEventDefinitionVariables
];

/**
 * @typedef {Object} ProcessVariable
 * @property {string} name
 * @property {Array<ModdleElement>} origin
 * @property {ModdleElement} scope
 */

// api /////////////////////////

/**
 * Retrieves all process variables for a given container element.
 * @param {ModdleElement} containerElement
 *
 * @returns {Array<ProcessVariable>}
 */
function getProcessVariables(containerElement) {
  var processVariables = [];

  // (1) extract all flow elements inside the container
  var elements = selfAndAllFlowElements([containerElement], false);

  // (2) extract all variables from the extractors
  Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["forEach"])(extractors, function(extractor) {
    extractor({
      elements: elements,
      containerElement: containerElement,
      processVariables: processVariables
    });
  });

  return processVariables;
}

/**
 * Retrieves all variables which are available in the given scope
 *
 * * Exclude variables which are only available in other scopes
 * * Exclude variables which are produced by the given element
 * * Include variables which are available in parent scopes
 *
 * @param {string} scope
 * @param {ModdleElement} rootElement element from where to extract all variables
 *
 * @returns {Array<ProcessVariable>}
 */
function getVariablesForScope(scope, rootElement) {

  var allVariables = getProcessVariables(rootElement);

  var scopeElement = getElement(scope, rootElement);

  // (1) get variables for given scope
  var scopeVariables = Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["filter"])(allVariables, function(variable) {
    return variable.scope.id === scopeElement.id;
  });

  // (2) get variables for parent scopes
  var parents = getParents(scopeElement);

  var parentsScopeVariables = Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["filter"])(allVariables, function(variable) {
    return Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["find"])(parents, function(parent) {
      return parent.id === variable.scope.id;
    });
  });

  return combineArrays$1(scopeVariables, parentsScopeVariables);
}

// helpers ////////////////////

function combineArrays$1(a, b) {
  return a.concat(b);
}




/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/PropertiesActivator.js":
/*!**************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/PropertiesActivator.js ***!
  \**************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var DEFAULT_PRIORITY = 1000;


/**
 * A component that decides upon the visibility / editable
 * state of properties in the properties panel.
 *
 * Implementors must subclass this component and override
 * {@link PropertiesActivator#isEntryVisible} and
 * {@link PropertiesActivator#isPropertyEditable} to provide
 * custom behavior.
 *
 * @class
 * @constructor
 *
 * @param {EventBus} eventBus
 * @param {Number} [priority] at which priority to hook into the activation
 */
function PropertiesActivator(eventBus, priority) {
  var self = this;

  priority = priority || DEFAULT_PRIORITY;

  eventBus.on('propertiesPanel.isEntryVisible', priority, function(context) {
    var element = context.element,
        entry = context.entry,
        group = context.group,
        tab = context.tab;

    return self.isEntryVisible(element, entry, group, tab);
  });

  eventBus.on('propertiesPanel.isPropertyEditable', priority, function(context) {
    var element = context.element,
        entry = context.entry,
        group = context.group,
        propertyName = context.propertyName,
        tab = context.tab;

    return self.isPropertyEditable(propertyName, element, entry, group, tab);
  });
}

PropertiesActivator.$inject = [ 'eventBus' ];

module.exports = PropertiesActivator;


/**
 * Should the given entry be visible for the specified element.
 *
 * @method  PropertiesActivator#isEntryVisible
 *
 * @param {ModdleElement} element
 * @param {Object} entry
 * @param {Object} group
 * @param {Object} tab
 *
 * @returns {boolean}
 */
PropertiesActivator.prototype.isEntryVisible = function(element, entry, group, tab) {
  return true;
};

/**
 * Should the given property be editable for the specified element
 *
 * @method  PropertiesActivator#isPropertyEditable
 *
 * @param {string} propertyName
 * @param {ModdleElement} element
 * @param {Object} entry
 * @param {Object} group
 * @param {Object} tab
 *
 * @returns {boolean}
 */
PropertiesActivator.prototype.isPropertyEditable = function(propertyName, element, entry, group, tab) {
  return true;
};

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/Utils.js":
/*!************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/Utils.js ***!
  \************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query,
    domClear = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").clear,
    domClasses = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").classes,
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    forEach = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js"),
    domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify,
    Ids = __webpack_require__(/*! ids */ "./node_modules/ids/dist/index.esm.js").default;

var SPACE_REGEX = /\s/;

// for QName validation as per http://www.w3.org/TR/REC-xml/#NT-NameChar
var QNAME_REGEX = /^([a-z][\w-.]*:)?[a-z_][\w-.]*$/i;

// for ID validation as per BPMN Schema (QName - Namespace)
var ID_REGEX = /^[a-z_][\w-.]*$/i;

var HTML_ESCAPE_MAP = {
  '&': '&amp;',
  '<': '&lt;',
  '>': '&gt;',
  '"': '&quot;',
  '\'': '&#39;'
};

function selectedOption(selectBox) {
  if (selectBox.selectedIndex >= 0) {
    return selectBox.options[selectBox.selectedIndex].value;
  }
}

module.exports.selectedOption = selectedOption;


function selectedType(elementSyntax, inputNode) {
  var typeSelect = domQuery(elementSyntax, inputNode);
  return selectedOption(typeSelect);
}

module.exports.selectedType = selectedType;


/**
 * Retrieve the root element the document this
 * business object is contained in.
 *
 * @return {ModdleElement}
 */
function getRoot(businessObject) {
  var parent = businessObject;
  while (parent.$parent) {
    parent = parent.$parent;
  }
  return parent;
}

module.exports.getRoot = getRoot;


/**
 * filters all elements in the list which have a given type.
 * removes a new list
 */
function filterElementsByType(objectList, type) {
  var list = objectList || [];
  var result = [];
  forEach(list, function(obj) {
    if (is(obj, type)) {
      result.push(obj);
    }
  });
  return result;
}

module.exports.filterElementsByType = filterElementsByType;


function findRootElementsByType(businessObject, referencedType) {
  var root = getRoot(businessObject);

  return filterElementsByType(root.rootElements, referencedType);
}

module.exports.findRootElementsByType = findRootElementsByType;


function removeAllChildren(domElement) {
  while (domElement.firstChild) {
    domElement.removeChild(domElement.firstChild);
  }
}

module.exports.removeAllChildren = removeAllChildren;


/**
 * adds an empty option to the list
 */
function addEmptyParameter(list) {
  return list.push({ 'label': '', 'value': '', 'name': '' });
}

module.exports.addEmptyParameter = addEmptyParameter;


/**
 * returns a dropdown option label depending on the defined event attributes
 */
function getOptionLabel(obj) {
  var label = obj.name || '';

  if (obj.errorCode)
    label += ' (code=' + obj.errorCode + ')';
  if (obj.escalationCode)
    label += ' (code=' + obj.escalationCode + ')';

  return label;
}

/**
 * returns a list with all root elements for the given parameter 'referencedType'
 */
function refreshOptionsModel(businessObject, referencedType) {
  var model = [];
  var referableObjects = findRootElementsByType(businessObject, referencedType);
  forEach(referableObjects, function(obj) {
    model.push({
      label: getOptionLabel(obj),
      value: obj.id,
      name: obj.name
    });
  });
  return model;
}

module.exports.refreshOptionsModel = refreshOptionsModel;


/**
 * fills the drop down with options
 */
function updateOptionsDropDown(domSelector, businessObject, referencedType, entryNode) {
  var options = refreshOptionsModel(businessObject, referencedType);
  addEmptyParameter(options);
  var selectBox = domQuery(domSelector, entryNode);
  domClear(selectBox);

  forEach(options, function(option) {
    var optionEntry = domify('<option value="' + escapeHTML(option.value) + '">' + escapeHTML(option.label) + '</option>');
    selectBox.appendChild(optionEntry);
  });
  return options;
}

module.exports.updateOptionsDropDown = updateOptionsDropDown;


/**
 * checks whether the id value is valid
 *
 * @param {ModdleElement} bo
 * @param {String} idValue
 * @param {Function} translate
 *
 * @return {String} error message
 */
function isIdValid(bo, idValue, translate) {
  var assigned = bo.$model.ids.assigned(idValue);

  var idExists = assigned && assigned !== bo;

  if (!idValue || idExists) {
    return translate('Element must have an unique id.');
  }

  return validateId(idValue, translate);
}

module.exports.isIdValid = isIdValid;


function validateId(idValue, translate) {

  if (containsSpace(idValue)) {
    return translate('Id must not contain spaces.');
  }

  if (!ID_REGEX.test(idValue)) {

    if (QNAME_REGEX.test(idValue)) {
      return translate('Id must not contain prefix.');
    }

    return translate('Id must be a valid QName.');
  }
}

module.exports.validateId = validateId;


function containsSpace(value) {
  return SPACE_REGEX.test(value);
}

module.exports.containsSpace = containsSpace;

/**
 * generate a semantic id with given prefix
 */
function nextId(prefix) {
  var ids = new Ids([32,32,1]);

  return ids.nextPrefixed(prefix);
}

module.exports.nextId = nextId;


function triggerClickEvent(element) {
  var evt;
  var eventType = 'click';

  if (document.createEvent) {
    try {

      // Chrome, Safari, Firefox
      evt = new MouseEvent((eventType), { view: window, bubbles: true, cancelable: true });
    } catch (e) {

      // IE 11, PhantomJS (wat!)
      evt = document.createEvent('MouseEvent');

      evt.initEvent((eventType), true, true);
    }
    return element.dispatchEvent(evt);
  } else {

    // Welcome IE
    evt = document.createEventObject();

    return element.fireEvent('on' + eventType, evt);
  }
}

module.exports.triggerClickEvent = triggerClickEvent;


function escapeHTML(str) {
  str = '' + str;

  return str && str.replace(/[&<>"']/g, function(match) {
    return HTML_ESCAPE_MAP[match];
  });
}

module.exports.escapeHTML = escapeHTML;

function createDropdown(dropdown) {
  var menu = dropdown.menu;

  var dropdownNode = domify(
    '<div class="group__dropdown">' +
      '<button class="group__dropdown-button">' +
      '<svg width="16" height="16" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 192 512"><path fill="currentColor" d="M96 184c39.8 0 72 32.2 72 72s-32.2 72-72 72-72-32.2-72-72 32.2-72 72-72zM24 80c0 39.8 32.2 72 72 72s72-32.2 72-72S135.8 8 96 8 24 40.2 24 80zm0 352c0 39.8 32.2 72 72 72s72-32.2 72-72-32.2-72-72-72-72 32.2-72 72z"></path></svg>' +
      '</button>' +
      '<div class="group__dropdown-menu"></div>' +
    '</div>'
  );

  var buttonNode = domQuery('.group__dropdown-button', dropdownNode),
      menuNode = domQuery('.group__dropdown-menu', dropdownNode);

  buttonNode.addEventListener('click', function(event) {
    domClasses(dropdownNode).toggle('group__dropdown--open');

    createOnGlobalClick(event);
  });

  forEach(menu, function(menuItem) {
    var menuItemNode = domify('<div class="group__dropdown-menu-item" data-dropdown-action="' +
      menuItem.id +
      '">' + escapeHTML(menuItem.label) + '</div>');

    menuItemNode.addEventListener('click', function() {
      menuItem.onClick();

      domClasses(dropdownNode).remove('group__dropdown--open');
    });

    menuNode.appendChild(menuItemNode);
  });

  var _onGlobalClick;

  function createOnGlobalClick(_event) {
    function onGlobalClick(event) {
      if (event === _event) {
        return;
      }

      var target = event.target;

      if (menuNode !== target && !menuNode.contains(target)) {
        domClasses(dropdownNode).remove('group__dropdown--open');

        document.removeEventListener('click', onGlobalClick);
      }
    }

    if (_onGlobalClick) {
      document.removeEventListener('click', _onGlobalClick);
    }

    document.addEventListener('click', onGlobalClick);

    _onGlobalClick = onGlobalClick;
  }

  return dropdownNode;
}

module.exports.createDropdown = createDropdown;

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/AutoSuggestTextBoxFactory.js":
/*!****************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/AutoSuggestTextBoxFactory.js ***!
  \****************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var domClasses = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").classes,
    domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify,
    domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

var assign = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").assign,
    find = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").find,
    forEach = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").forEach,
    debounce = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").debounce;

var escapeHTML = __webpack_require__(/*! ../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var entryFieldDescription = __webpack_require__(/*! ./EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");

var CLASS_ACTIVE = 'active';

var FOCUS_LEAVE_DELAY = '150';

var TEXT_NODE_NAME = '#text';

var SUGGESTION_LIST_BOX_THRESHOLD = 15;

var noop = function() {};


var autoSuggestTextBox = function(translate, options, defaultParameters) {

  var resource = defaultParameters,
      label = options.label || resource.id,
      canBeShown = !!options.show && typeof options.show === 'function',
      description = options.description;

  resource.html =
    domify('<label ' +
      'for="camunda-' + escapeHTML(resource.id) + '" ' +
      (canBeShown ? 'data-show="isShown"' : '') +
      '>' + label + '</label>' +
    '<div class="bpp-field-wrapper" ' +
      (canBeShown ? 'data-show="isShown"' : '') +
    '>' +
      '<div ' +
        'contenteditable="true"' +
        'id="camunda-' + escapeHTML(resource.id) + '" ' +
        'name="' + escapeHTML(options.modelProperty) + '" ' +
        'data-auto-suggest="suggestItems"' +
        'data-blur="handleFocusLeave"' +
      '></div>' +
      '<div class="bpp-autosuggest-list"></div>' +
    '</div>');

  if (description) {
    domQuery('.bpp-field-wrapper', resource.html).appendChild(entryFieldDescription(translate, description));
  }

  if (canBeShown) {
    resource.isShown = function() {
      return options.show.apply(resource, arguments);
    };
  }

  /**
   * Ensure selected item got recognized before list got hidden
   */
  resource.handleFocusLeave = debounce(function(element, entryNode) {
    clearSuggestionList(entryNode);
    hideSuggestionList(entryNode);
  }, FOCUS_LEAVE_DELAY);

  /**
   * Fill the suggestion list relative to the current word under the cursor.
   *
   * @param {djs.model.Base} element
   * @param {HTMLElement} entryNode
   * @param {Event} event
   */
  resource.suggestItems = function(element, entryNode, event) {
    var editorNode = event.delegateTarget,
        range = getSelectionRange(),
        focusNode = range.focusNode,
        caretPosition = getCaretPosition(range.range),
        canSuggest = options.canSuggest || noop,
        getItems = options.getItems;

    function updateSuggestionList(items) {
      var listNode = domQuery('.bpp-autosuggest-list', entryNode);

      // (1) clear list before
      clearSuggestionList(entryNode);

      // (2) keep list invisible if no items
      if (!items.length) {
        return;
      }

      domClasses(listNode).add(CLASS_ACTIVE);

      // (3) create new items
      forEach(items, function(item) {
        createSuggestionItem(listNode, item);
      });

      // (4) place list relative to cursor
      var position = getSuggestionListPosition(listNode, document.body).position;
      setPosition(listNode, position.x, position.y);
    }

    function createSuggestionItem(parentNode, value) {
      var itemNode = domify('<div class="bpp-autosuggest-item"></div>');
      itemNode.innerText = escapeHTML(value);

      parentNode.appendChild(itemNode);

      itemNode.addEventListener('click', handleItemClick);
    }

    function handleItemClick(event) {
      var value = event.target.innerText,
          wordIndex = currentWord.index,
          start = wordIndex,
          end = wordIndex + currentWord[0].length;

      selectRange(focusNode, start, end);

      document.execCommand('insertText', false, value);

      clearSuggestionList(entryNode);
      hideSuggestionList(entryNode);
    }

    hideSuggestionList(entryNode);

    var currentWord = (getWordUnderCursor(focusNode, caretPosition) || []);

    if (currentWord && canSuggest(currentWord, editorNode, focusNode)) {
      var items = getItems(element, entryNode),
          results = [],
          value = currentWord[0];

      // sort matches by
      // (1) item starts with value (case insensitive)
      // (2) value is inside suggested item (case insensitive)
      forEach(items, function(item) {
        var itemLowerCase = item.toLowerCase(),
            valueLowerCase = value && value.toLowerCase();

        if (itemLowerCase.indexOf(valueLowerCase) === 0) {
          results.push(item);
        }
      });

      forEach(items, function(item) {
        var itemLowerCase = item.toLowerCase(item),
            valueLowerCase = value && value.toLowerCase();

        if (itemLowerCase.indexOf(valueLowerCase) >= 1) {
          results.push(item);
        }
      });

      updateSuggestionList(results);
    }
  };

  /**
  * Calculates the position coordinates of the suggestion list,
  * dependant on position of cursor
  *
  * @return {Object} coordinates
  */
  function getSuggestionListPosition(listNode, container) {
    var range = getSelectionRange().range,
        cursorBounds = range.getBoundingClientRect(),
        clientBounds = container.getBoundingClientRect(),
        listBounds = listNode.getBoundingClientRect();

    var coordinates = {
      'top-left': {
        x: cursorBounds.right - listBounds.width,
        y: cursorBounds.top - listBounds.height
      },
      'top-right': {
        x: cursorBounds.right,
        y: cursorBounds.top - listBounds.height
      },
      'bottom-left': {
        x: cursorBounds.right - listBounds.width,
        y: cursorBounds.top + SUGGESTION_LIST_BOX_THRESHOLD
      },
      'bottom-right': {
        x: cursorBounds.right,
        y: cursorBounds.top + SUGGESTION_LIST_BOX_THRESHOLD
      }
    };

    var orientation = '';

    if (cursorBounds.top + SUGGESTION_LIST_BOX_THRESHOLD + listBounds.height > (clientBounds.height + clientBounds.top)) {
      orientation = 'top-';
    } else {
      orientation = 'bottom-';
    }

    if (cursorBounds.right + listBounds.width > (clientBounds.width + clientBounds.left)) {
      orientation += 'left';
    } else {
      orientation += 'right';
    }

    return { orientation: orientation, position: coordinates[orientation] };
  }

  resource.getSuggestionListPosition = getSuggestionListPosition;


  resource.cssClasses = ['bpp-autosuggest-textbox'];

  return resource;
};

module.exports = autoSuggestTextBox;


// helpers /////////////////////////////

function getSelectionRange() {
  var selection = document.getSelection();

  return {
    range: selection.getRangeAt(0),
    focusNode: selection.focusNode
  };
}

function getCaretPosition(range) {
  return range.startOffset;
}

function selectRange(focusNode, start, end) {
  var range = document.createRange(),
      selection = window.getSelection();

  range.setStart(focusNode, start);
  range.setEnd(focusNode, end);

  selection.removeAllRanges();

  selection.addRange(range);
}

function hideSuggestionList(entryNode) {
  var listNode = domQuery('.bpp-autosuggest-list', entryNode);
  domClasses(listNode).remove(CLASS_ACTIVE);
}

function clearSuggestionList(entryNode) {
  var listNode = domQuery('.bpp-autosuggest-list', entryNode);
  while (listNode.firstChild) {
    listNode.removeChild(listNode.firstChild);
  }
}

function getWordUnderCursor(node, currentCursorPositon) {
  var value = node.nodeName === TEXT_NODE_NAME ? node.wholeText : node.innerText,
      allWords = findWords(value);

  return find(allWords, function(word) {
    var matchValue = word[0],
        wordStart = word.index,
        wordEnd = wordStart + matchValue.length - 1;

    return (
      wordStart <= (currentCursorPositon - 1) &&
      wordEnd >= (currentCursorPositon - 1)
    );
  });
}

/**
 * Retrieves all words inside a text (also inside clauses and after operators)
 *
 * @param {string} value
 *
 * @return {Array<Object>}
 */
function findWords(value) {

  // eslint-disable-next-line no-useless-escape
  return matchAll(value, /[^\s\r\(\)\,\+\-\*\/\{\}]+/g);
}

function matchAll(value, regex) {
  var regexp = RegExp(regex),
      match,
      matches = [];

  while ((match = regexp.exec(value)) !== null) {
    matches.push(match);
  }

  return matches;
}

function setPosition(el, x, y) {
  assign(el.style, { left: x + 'px', top: y + 'px' });
}


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/CheckboxEntryFactory.js":
/*!***********************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/CheckboxEntryFactory.js ***!
  \***********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify;

var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    cmdHelper = __webpack_require__(/*! ../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    escapeHTML = __webpack_require__(/*! ../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var entryFieldDescription = __webpack_require__(/*! ./EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");


var checkbox = function(translate, options, defaultParameters) {
  var resource = defaultParameters,
      id = resource.id,
      label = options.label || id,
      canBeDisabled = !!options.disabled && typeof options.disabled === 'function',
      canBeHidden = !!options.hidden && typeof options.hidden === 'function',
      description = options.description;

  resource.html =
    domify('<input id="camunda-' + escapeHTML(id) + '" ' +
         'type="checkbox" ' +
         'name="' + escapeHTML(options.modelProperty) + '" ' +
         (canBeDisabled ? 'data-disable="isDisabled"' : '') +
         (canBeHidden ? 'data-show="isHidden"' : '') +
         ' />' +
    '<label for="camunda-' + escapeHTML(id) + '" ' +
         (canBeDisabled ? 'data-disable="isDisabled"' : '') +
         (canBeHidden ? 'data-show="isHidden"' : '') +
         '>' + escapeHTML(label) + '</label>');

  // add description below checkbox entry field
  if (description) {
    resource.html.appendChild(entryFieldDescription(translate, description, { show: canBeHidden && 'isHidden' }));
  }

  resource.get = function(element) {
    var bo = getBusinessObject(element),
        res = {};

    res[options.modelProperty] = bo.get(options.modelProperty);

    return res;
  };

  resource.set = function(element, values) {
    var res = {};

    res[options.modelProperty] = !!values[options.modelProperty];

    return cmdHelper.updateProperties(element, res);
  };

  if (typeof options.set === 'function') {
    resource.set = options.set;
  }

  if (typeof options.get === 'function') {
    resource.get = options.get;
  }

  if (canBeDisabled) {
    resource.isDisabled = function() {
      return options.disabled.apply(resource, arguments);
    };
  }

  if (canBeHidden) {
    resource.isHidden = function() {
      return !options.hidden.apply(resource, arguments);
    };
  }

  resource.cssClasses = ['bpp-checkbox'];

  return resource;
};

module.exports = checkbox;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/CollapsibleEntryFactory.js":
/*!**************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/CollapsibleEntryFactory.js ***!
  \**************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var escapeHTML = __webpack_require__(/*! ../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;
var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;


/**
 * @param  {object} options
 * @param  {string} options.id
 * @param  {string} [options.title='']
 * @param  {string} [options.description='']
 * @param  {boolean} [options.open=false]
 * @param  {Function} [options.onToggle]
 * @param  {Function} [options.onRemove]
 *
 * @return {object}
 */
function Collapsible(options) {

  var id = options.id,
      title = options.title || '',
      description = options.description || '',
      open = !!options.open || false,
      onToggle = options.onToggle || noop,
      onRemove = options.onRemove,
      cssClasses = options.cssClasses || [];


  var collapsibleEntry = {
    id: id,
    toggle: toggle,
    isOpen: isOpen,
    set: set,
    setOpen: setOpen,
    get: get
  };

  if (typeof onRemove === 'function') {
    collapsibleEntry.onRemove = function(entry, entryNode, actionId, event) {
      var commands = onRemove(entry, entryNode, actionId, event);

      if (commands) {
        scheduleCommands(commands, entryNode);
        return true;
      }
    };
  }

  function get(element, entryNode) {
    if (options.get) {
      return options.get(element, entryNode);
    }

    return {
      title: title || '',
      description: description || ''
    };
  }

  function set() {
    var commands = this._commands;

    if (commands) {
      delete this._commands;
      return commands;
    }
  }

  function toggle(element, entryNode, event, scope) {
    var value = !open;

    setOpen(value, entryNode);
    onToggle(value, entryNode);
  }

  /**
   * Set entry's open state.
   *
   * @param {boolean} value
   * @param {HTMLElement} entryNode
   */
  function setOpen(value, entryNode) {
    open = value;
    entryNode.classList.toggle('bpp-collapsible--collapsed', !value);
  }

  function isOpen() {
    return open;
  }

  /**
   * Schedule commands to be run with next `set` method call.
   *
   * @param {Array<any>} commands
   * @param {HTMLElement} entryNode
   */
  function scheduleCommands(commands, entryNode) {
    collapsibleEntry._commands = commands;

    // @barmac: hack to make properties panel call `set`
    var input = domQuery('input[type="hidden"]', entryNode);
    input.value = 1;
  }

  collapsibleEntry.html = '<div class="bpp-field-wrapper" data-action="toggle"><input name="hidden" type="hidden">' +
    '<span class="bpp-collapsible__icon"></span>' +
    '<label class="bpp-collapsible__title" data-value="title">' + escapeHTML(title) + '</label>' +
    '<label class="bpp-collapsible__description" data-value="description">' + escapeHTML(description) + '</label>' +
    (onRemove ? '<button class="bpp-collapsible__remove action-button clear" data-action="onRemove"></button>' : '') +
  '</div>';

  collapsibleEntry.cssClasses = cssClasses.concat(open ?
    [ 'bpp-collapsible' ] : [ 'bpp-collapsible', 'bpp-collapsible--collapsed' ]
  );

  return collapsibleEntry;
}

module.exports = Collapsible;

function noop() {}


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/ComboEntryFactory.js":
/*!********************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/ComboEntryFactory.js ***!
  \********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var assign = __webpack_require__(/*! lodash/assign */ "./node_modules/lodash/assign.js"),
    find = __webpack_require__(/*! lodash/find */ "./node_modules/lodash/find.js");

var domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify,
    domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

var escapeHTML = __webpack_require__(/*! ../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var selectEntryFactory = __webpack_require__(/*! ./SelectEntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/SelectEntryFactory.js"),
    entryFieldDescription = __webpack_require__(/*! ./EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");


/**
 * The combo box is a special implementation of the select entry and adds the option 'custom' to the
 * select box. If 'custom' is selected, an additional text input field is shown which allows to define
 * a custom value.
 *
 * @param  {Object} options
 * @param  {string} options.id
 * @param  {string} options.label
 * @param  {Array<Object>} options.selectOptions list of name/value pairs
 * @param  {string} options.modelProperty
 * @param  {function} options.get
 * @param  {function} options.set
 * @param  {string} [options.customValue] custom select option value (default: 'custom')
 * @param  {string} [options.customName] custom select option name visible in the select box (default: 'custom')
 *
 * @return {Object}
 */
var comboBox = function(translate, options) {

  var selectOptions = options.selectOptions,
      modelProperty = options.modelProperty,
      customValue = options.customValue || 'custom',
      customName = options.customName || 'custom ' + modelProperty,
      description = options.description;

  // check if a value is not a built in value
  var isCustomValue = function(value) {
    if (typeof value[modelProperty] === 'undefined') {
      return false;
    }

    var isCustom = !find(selectOptions, function(option) {
      return value[modelProperty] === option.value;
    });

    return isCustom;
  };

  var comboOptions = assign({}, options);

  // true if the selected value in the select box is customValue
  comboOptions.showCustomInput = function(element, node) {
    var selectBox = domQuery('[data-entry="'+ options.id +'"] select', node.parentNode);

    if (selectBox) {
      return selectBox.value === customValue;
    }

    return false;
  };

  comboOptions.get = function(element, node) {
    var value = options.get(element, node);

    var modifiedValues = {};

    if (!isCustomValue(value)) {
      modifiedValues[modelProperty] = value[modelProperty] || '';

      return modifiedValues;
    }

    modifiedValues[modelProperty] = customValue;
    modifiedValues['custom-'+modelProperty] = value[modelProperty];

    return modifiedValues;
  };

  comboOptions.set = function(element, values, node) {
    var modifiedValues = {};

    // if the custom select option has been selected
    // take the value from the text input field
    if (values[modelProperty] === customValue) {
      modifiedValues[modelProperty] = values['custom-' + modelProperty] || '';
    }
    else if (options.emptyParameter && values[modelProperty] === '') {
      modifiedValues[modelProperty] = undefined;
    } else {
      modifiedValues[modelProperty] = values[modelProperty];
    }
    return options.set(element, modifiedValues, node);
  };

  comboOptions.selectOptions.push({ name: customName, value: customValue });

  var comboBoxEntry = assign({}, selectEntryFactory(translate, comboOptions, comboOptions));

  var fragment = document.createDocumentFragment();

  fragment.appendChild(comboBoxEntry.html);

  comboBoxEntry.html = fragment;

  comboBoxEntry.html.appendChild(domify('<div class="bpp-field-wrapper bpp-combo-input" ' +
    'data-show="showCustomInput"' +
    '>' +
    '<input id="camunda-' + escapeHTML(options.id) + '-input" type="text" name="custom-' +
      escapeHTML(modelProperty) + '" ' +
    ' />' +
  '</div>'));

  // add description below combo box entry field
  if (description) {
    comboBoxEntry.html.appendChild(entryFieldDescription(translate, description, { show: 'showCustomInput' }));
  }

  return comboBoxEntry;
};

module.exports = comboBox;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js":
/*!***************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js ***!
  \***************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

// input entities
var textInputField = __webpack_require__(/*! ./TextInputEntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/TextInputEntryFactory.js"),
    checkboxField = __webpack_require__(/*! ./CheckboxEntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/CheckboxEntryFactory.js"),
    selectBoxField = __webpack_require__(/*! ./SelectEntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/SelectEntryFactory.js"),
    comboBoxField = __webpack_require__(/*! ./ComboEntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/ComboEntryFactory.js"),
    textBoxField = __webpack_require__(/*! ./TextBoxEntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/TextBoxEntryFactory.js"),
    validationAwareTextInputField = __webpack_require__(/*! ./ValidationAwareTextInput */ "./node_modules/bpmn-js-properties-panel/lib/factory/ValidationAwareTextInput.js"),
    tableField = __webpack_require__(/*! ./TableEntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/TableEntryFactory.js"),
    labelEntry = __webpack_require__(/*! ./LabelFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/LabelFactory.js"),
    link = __webpack_require__(/*! ./LinkEntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/LinkEntryFactory.js"),
    autoSuggestTextBoxField = __webpack_require__(/*! ./AutoSuggestTextBoxFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/AutoSuggestTextBoxFactory.js"),
    collapsible = __webpack_require__(/*! ./CollapsibleEntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/CollapsibleEntryFactory.js"),
    toggleSwitch = __webpack_require__(/*! ./ToggleSwitchEntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/ToggleSwitchEntryFactory.js");

var cmdHelper = __webpack_require__(/*! ../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

// helpers ////////////////////////////////////////

function ensureNotNull(prop) {
  if (!prop) {
    throw new Error(prop + ' must be set.');
  }

  return prop;
}

/**
 * sets the default parameters which are needed to create an entry
 *
 * @param options
 * @returns {{id: *, description: (*|string), get: (*|Function), set: (*|Function),
 *            validate: (*|Function), html: string}}
 */
var setDefaultParameters = function(options) {

  // default method to fetch the current value of the input field
  var defaultGet = function(element) {
    var bo = getBusinessObject(element),
        res = {},
        prop = ensureNotNull(options.modelProperty);
    res[prop] = bo.get(prop);

    return res;
  };

  // default method to set a new value to the input field
  var defaultSet = function(element, values) {
    var res = {},
        prop = ensureNotNull(options.modelProperty);
    if (values[prop] !== '') {
      res[prop] = values[prop];
    } else {
      res[prop] = undefined;
    }

    return cmdHelper.updateProperties(element, res);
  };

  // default validation method
  var defaultValidate = function() {
    return {};
  };

  return {
    id : options.id,
    description : (options.description || ''),
    get : (options.get || defaultGet),
    set : (options.set || defaultSet),
    validate : (options.validate || defaultValidate),
    html: ''
  };
};

function EntryFactory() {

}

/**
 * Generates an text input entry object for a property panel.
 * options are:
 * - id: id of the entry - String
 *
 * - description: description of the property - String
 *
 * - label: label for the input field - String
 *
 * - set: setter method - Function
 *
 * - get: getter method - Function
 *
 * - validate: validation mehtod - Function
 *
 * - modelProperty: name of the model property - String
 *
 * - buttonAction: Object which contains the following properties: - Object
 * ---- name: name of the [data-action] callback - String
 * ---- method: callback function for [data-action] - Function
 *
 * - buttonShow: Object which contains the following properties: - Object
 * ---- name: name of the [data-show] callback - String
 * ---- method: callback function for [data-show] - Function
 *
 * @param options
 * @returns the propertyPanel entry resource object
 */
EntryFactory.textField = function(translate, options) {
  return textInputField(translate, options, setDefaultParameters(options));
};

EntryFactory.validationAwareTextField = function(translate, options) {
  return validationAwareTextInputField(translate, options, setDefaultParameters(options));
};

/**
 * Generates a checkbox input entry object for a property panel.
 * options are:
 * - id: id of the entry - String
 *
 * - description: description of the property - String
 *
 * - label: label for the input field - String
 *
 * - set: setter method - Function
 *
 * - get: getter method - Function
 *
 * - validate: validation method - Function
 *
 * - modelProperty: name of the model property - String
 *
 * @param options
 * @returns the propertyPanel entry resource object
 */
EntryFactory.checkbox = function(translate, options) {
  return checkboxField(translate, options, setDefaultParameters(options));
};

EntryFactory.textBox = function(translate, options) {
  return textBoxField(translate, options, setDefaultParameters(options));
};

EntryFactory.selectBox = function(translate, options) {
  return selectBoxField(translate, options, setDefaultParameters(options));
};

EntryFactory.comboBox = function(translate, options) {
  return comboBoxField(translate, options);
};

EntryFactory.table = function(translate, options) {
  return tableField(translate, options);
};

EntryFactory.label = function(options) {
  return labelEntry(options);
};

EntryFactory.link = function(translate, options) {
  return link(translate, options);
};

EntryFactory.autoSuggest = function(translate, options) {
  return autoSuggestTextBoxField(translate, options, setDefaultParameters(options));
};

EntryFactory.collapsible = function(options) {
  return collapsible(options);
};

EntryFactory.toggleSwitch = function(translate, options) {
  return toggleSwitch(translate, options, setDefaultParameters(options));
};

module.exports = EntryFactory;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js":
/*!************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js ***!
  \************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify,
    domClasses = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").classes,
    domEvent = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").event;

var escapeHTML = __webpack_require__(/*! ../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var MAX_DESCRIPTION_LENGTH = 200;

/**
 * Create a linkified and HTML escaped entry field description.
 *
 * As a special feature, this description may contain both markdown,
 * plain <a href> links and <br />
 *
 * @param {string} description
 * @param {object} [options]
 * @param {string} [options.show] - name of callback to determine whether description is shown
 */
module.exports = function entryFieldDescription(translate, description, options) {
  var show = options && options.show;

  // we tokenize the description to extract text, HTML and markdown links
  // text, links and new lines are handled seperately

  var escaped = [];

  // match markdown [{TEXT}]({URL}) and HTML links <a href="{URL}">{TEXT}</a>
  var pattern = /(?:\[([^\]]+)\]\((https?:\/\/[^)]+)\))|(?:<a href="(https?:\/\/[^"]+)">(.+?(?=<\/))<\/a>)/gi;

  var index = 0;
  var match;
  var link, text;

  while ((match = pattern.exec(description))) {

    // escape + insert text before match
    if (match.index > index) {
      escaped.push(escapeText(description.substring(index, match.index)));
    }

    link = match[2] && encodeURI(match[2]) || match[3];
    text = match[1] || match[4];

    // insert safe link
    escaped.push('<a href="' + link + '" target="_blank">' + escapeText(text) + '</a>');

    index = match.index + match[0].length;
  }

  // escape and insert text after last match
  if (index < description.length) {
    escaped.push(escapeText(description.substring(index)));
  }

  description = escaped.join('');

  var html = domify(
    '<div class="bpp-field-description description description--expanded"' +
    (show ? 'data-show="' + show + '">' : '>') +
    '</div>'
  );

  var descriptionText = domify('<span class="description__text">' + description + '</span>');

  html.appendChild(descriptionText);

  function toggleExpanded(expanded) {
    if (expanded) {
      domClasses(html).add('description--expanded');

      descriptionText.textContent = description + ' ';

      expand.textContent = translate('Less');
    } else {
      domClasses(html).remove('description--expanded');

      descriptionText.textContent = descriptionShortened + ' ... ';

      expand.textContent = translate('More');
    }
  }

  var descriptionShortened,
      expand,
      expanded = false;

  if (description.length > MAX_DESCRIPTION_LENGTH) {
    descriptionShortened = description.slice(0, MAX_DESCRIPTION_LENGTH);

    expand = domify(
      '<span class="bpp-entry-link description__expand">' +
        translate('More') +
      '</span>'
    );

    domEvent.bind(expand, 'click', function() {
      expanded = !expanded;

      toggleExpanded(expanded);
    });

    html.appendChild(expand);

    toggleExpanded(expanded);
  }

  return html;
};

function escapeText(text) {
  var match, index = 0, escaped = [];

  // match new line <br/> <br /> <br.... /> etc.
  var pattern = /<br\s*\/?>/gi;

  while ((match = pattern.exec(text))) {

    if (match.index > index) {
      escaped.push(escapeHTML(text.substring(index, match.index)));
    }

    escaped.push('<br />');

    index = match.index + match[0].length;
  }

  if (index < text.length) {
    escaped.push(escapeHTML(text.substring(index)));
  }

  return escaped.join('');
}

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/LabelFactory.js":
/*!***************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/LabelFactory.js ***!
  \***************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


/**
 * The label factory provides a label entry. For the label text
 * it expects either a string provided by the options.labelText
 * parameter or it could be generated programmatically using a
 * function passed as the options.get parameter.
 *
 * @param  {Object} options
 * @param  {string} options.id
 * @param  {string} [options.labelText]
 * @param  {Function} [options.get]
 * @param  {Function} [options.showLabel]
 * @param  {Boolean} [options.divider] adds a divider at the top of the label if true; default: false
 */
var label = function(options) {
  return {
    id: options.id,
    html: '<label data-value="label" ' +
            'data-show="showLabel" ' +
            'class="entry-label' + (options.divider ? ' divider' : '') + '">' +
          '</label>',
    get: function(element, node) {
      if (typeof options.get === 'function') {
        return options.get(element, node);
      }
      return { label: options.labelText };
    },
    showLabel: function(element, node) {
      if (typeof options.showLabel === 'function') {
        return options.showLabel(element, node);
      }
      return true;
    }
  };
};

module.exports = label;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/LinkEntryFactory.js":
/*!*******************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/LinkEntryFactory.js ***!
  \*******************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify;

var escapeHTML = __webpack_require__(/*! ../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var entryFieldDescription = __webpack_require__(/*! ./EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");

var bind = __webpack_require__(/*! lodash/bind */ "./node_modules/lodash/bind.js");

/**
 * An entry that renders a clickable link.
 *
 * A passed {@link options#handleClick} handler is responsible
 * to process the click.
 *
 * The link may be conditionally shown or hidden. This can be
 * controlled via the {@link options.showLink}.
 *
 * @param {Object} options
 * @param {String} options.id
 * @param {String} [options.buttonLabel]
 * @param {Function} options.handleClick
 * @param {Function} [options.showLink] returning false to hide link
 * @param {String} [options.description]
 *
 * @example
 *
 * var linkEntry = link({
 *   id: 'foo',
 *   description: 'Some Description',
 *   handleClick: function(element, node, event) { ... },
 *   showLink: function(element, node) { ... }
 * });
 *
 * @return {Entry} the newly created entry
 */
function link(translate, options) {

  var id = options.id,
      buttonLabel = options.buttonLabel || id,
      showLink = options.showLink,
      handleClick = options.handleClick,
      description = options.description,
      label = options.label;

  if (showLink && typeof showLink !== 'function') {
    throw new Error('options.showLink must be a function');
  }

  if (typeof handleClick !== 'function') {
    throw new Error('options.handleClick must be a function');
  }

  var resource = {
    id: id,
    html: document.createDocumentFragment()
  };

  if (label) {
    resource.html.appendChild(domify('<label for="camunda-' + escapeHTML(id) + '" ' +
      (showLink ? 'data-show="showLink" ' : '') +
      '>'+ escapeHTML(label) +'</label>'));
  }

  resource.html.appendChild(domify('<div class="bpp-field-wrapper">' +
    '<a data-action="handleClick" ' +
    (showLink ? 'data-show="showLink" ' : '') +
    'class="bpp-entry-link' + (options.cssClasses ? ' ' + escapeHTML(options.cssClasses) : '') +
    '">' + escapeHTML(buttonLabel) + '</a></div>'));


  // add description below link entry field
  if (description) {
    resource.html.appendChild(entryFieldDescription(translate, description, { show: 'showLink' }));
  }

  resource.handleClick = bind(handleClick, resource);

  if (typeof showLink === 'function') {
    resource.showLink = function() {
      return showLink.apply(resource, arguments);
    };
  }

  return resource;
}

module.exports = link;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/SelectEntryFactory.js":
/*!*********************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/SelectEntryFactory.js ***!
  \*********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var escapeHTML = __webpack_require__(/*! ../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify,
    domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

var forEach = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js");

var entryFieldDescription = __webpack_require__(/*! ./EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");


var isList = function(list) {
  return !(!list || Object.prototype.toString.call(list) !== '[object Array]');
};

var addEmptyParameter = function(list) {
  return list.concat([ { name: '', value: '' } ]);
};

var createOption = function(option) {
  return '<option value="' + option.value + '">' + option.name + '</option>';
};

/**
 * @param  {Object} options
 * @param  {string} options.id
 * @param  {string} [options.label]
 * @param  {Array<Object>} options.selectOptions
 * @param  {string} options.modelProperty
 * @param  {boolean} options.emptyParameter
 * @param  {function} options.disabled
 * @param  {function} options.hidden
 * @param  {Object} defaultParameters
 *
 * @return {Object}
 */
var selectbox = function(translate, options, defaultParameters) {
  var resource = defaultParameters,
      label = options.label || resource.id,
      selectOptions = options.selectOptions || [ { name: '', value: '' } ],
      modelProperty = options.modelProperty,
      emptyParameter = options.emptyParameter,
      canBeDisabled = !!options.disabled && typeof options.disabled === 'function',
      canBeHidden = !!options.hidden && typeof options.hidden === 'function',
      description = options.description;


  if (emptyParameter) {
    selectOptions = addEmptyParameter(selectOptions);
  }


  resource.html =
    domify('<label for="camunda-' + escapeHTML(resource.id) + '"' +
    (canBeDisabled ? 'data-disable="isDisabled" ' : '') +
    (canBeHidden ? 'data-show="isHidden" ' : '') +
    '>' + escapeHTML(label) + '</label>' +
    '<select id="camunda-' + escapeHTML(resource.id) + '-select" name="' +
    escapeHTML(modelProperty) + '"' +
    (canBeDisabled ? 'data-disable="isDisabled" ' : '') +
    (canBeHidden ? 'data-show="isHidden" ' : '') +
    ' data-value></select>');

  var select = domQuery('select', resource.html);

  if (isList(selectOptions)) {
    forEach(selectOptions, function(option) {
      select.appendChild(
        domify(
          '<option value="' + escapeHTML(option.value) +
          (option.title ? '" title="' + escapeHTML(option.title) : '') +
          '">' +
          (option.name ? escapeHTML(option.name) : '') +
          '</option>'
        )
      );
    });
  }

  // add description below select box entry field
  if (description && typeof options.showCustomInput !== 'function') {
    resource.html.appendChild(entryFieldDescription(translate, description, { show: canBeHidden && 'isHidden' }));
  }

  /**
   * Fill the select box options dynamically.
   *
   * Calls the defined function #selectOptions in the entry to get the
   * values for the options and set the value to the inputNode.
   *
   * @param {djs.model.Base} element
   * @param {HTMLElement} entryNode
   * @param {EntryDescriptor} inputNode
   * @param {Object} inputName
   * @param {Object} newValue
   */
  resource.setControlValue = function(element, entryNode, inputNode, inputName, newValue) {
    if (typeof selectOptions === 'function') {

      var options = selectOptions(element, inputNode);

      if (options) {

        // remove existing options
        while (inputNode.firstChild) {
          inputNode.removeChild(inputNode.firstChild);
        }

        // add options
        forEach(options, function(option) {
          var template = domify(createOption(option));

          inputNode.appendChild(template);
        });


      }
    }

    // set select value
    if (newValue !== undefined) {
      inputNode.value = newValue;
    }

  };

  if (canBeDisabled) {
    resource.isDisabled = function() {
      return options.disabled.apply(resource, arguments);
    };
  }

  if (canBeHidden) {
    resource.isHidden = function() {
      return !options.hidden.apply(resource, arguments);
    };
  }

  resource.cssClasses = ['bpp-dropdown'];

  return resource;
};

module.exports = selectbox;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/TableEntryFactory.js":
/*!********************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/TableEntryFactory.js ***!
  \********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var escapeHTML = __webpack_require__(/*! ../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var cmdHelper = __webpack_require__(/*! ../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query,
    domAttr = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").attr,
    domClosest = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").closest;

var filter = __webpack_require__(/*! lodash/filter */ "./node_modules/lodash/filter.js"),
    forEach = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js"),
    keys = __webpack_require__(/*! lodash/keys */ "./node_modules/lodash/keys.js");

var domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify;

var entryFieldDescription = __webpack_require__(/*! ./EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");

var updateSelection = __webpack_require__(/*! selection-update */ "./node_modules/selection-update/index.js");

var TABLE_ROW_DIV_SNIPPET = '<div class="bpp-field-wrapper bpp-table-row">';
var DELETE_ROW_BUTTON_SNIPPET = '<button class="action-button clear" data-action="deleteElement">' +
                                  '<span>X</span>' +
                                '</button>';

function createInputRowTemplate(properties, canRemove) {
  var template = TABLE_ROW_DIV_SNIPPET;
  template += createInputTemplate(properties, canRemove);
  template += canRemove ? DELETE_ROW_BUTTON_SNIPPET : '';
  template += '</div>';

  return template;
}

function createInputTemplate(properties, canRemove) {
  var columns = properties.length;
  var template = '';
  forEach(properties, function(prop) {
    template += '<input class="bpp-table-row-columns-' + columns + ' ' +
                               (canRemove ? 'bpp-table-row-removable' : '') + '" ' +
                       'id="camunda-table-row-cell-input-value" ' +
                       'type="text" ' +
                       'name="' + escapeHTML(prop) + '" />';
  });
  return template;
}

function createLabelRowTemplate(labels) {
  var template = TABLE_ROW_DIV_SNIPPET;
  template += createLabelTemplate(labels);
  template += '</div>';

  return template;
}

function createLabelTemplate(labels) {
  var columns = labels.length;
  var template = '';
  forEach(labels, function(label) {
    template += '<label class="bpp-table-row-columns-' + columns + '">' + escapeHTML(label) + '</label>';
  });
  return template;
}

function pick(elements, properties) {
  return (elements || []).map(function(elem) {
    var newElement = {};
    forEach(properties, function(prop) {
      newElement[prop] = elem[prop] || '';
    });
    return newElement;
  });
}

function diff(element, node, values, oldValues, editable) {
  return filter(values, function(value, idx) {
    return !valueEqual(element, node, value, oldValues[idx], editable, idx);
  });
}

function valueEqual(element, node, value, oldValue, editable, idx) {
  if (value && !oldValue) {
    return false;
  }
  var allKeys = keys(value).concat(keys(oldValue));

  return allKeys.every(function(key) {
    var n = value[key] || undefined;
    var o = oldValue[key] || undefined;
    return !editable(element, node, key, idx) || n === o;
  });
}

function getEntryNode(node) {
  return domClosest(node, '[data-entry]', true);
}

function getContainer(node) {
  return domQuery('div[data-list-entry-container]', node);
}

function getSelection(node) {
  return {
    start: node.selectionStart,
    end: node.selectionEnd
  };
}

function setSelection(node, selection) {
  node.selectionStart = selection.start;
  node.selectionEnd = selection.end;
}

/**
 * @param  {Object} options
 * @param  {string} options.id
 * @param  {string} options.description
 * @param  {Array<string>} options.modelProperties
 * @param  {Array<string>} options.labels
 * @param  {Function} options.getElements - this callback function must return a list of business object items
 * @param  {Function} options.removeElement
 * @param  {Function} options.addElement
 * @param  {Function} options.updateElement
 * @param  {Function} options.editable
 * @param  {Function} options.setControlValue
 * @param  {Function} options.show
 *
 * @return {Object}
 */
module.exports = function(translate, options) {

  var id = options.id,
      modelProperties = options.modelProperties,
      labels = options.labels,
      description = options.description;

  var labelRow = createLabelRowTemplate(labels);

  var getElements = options.getElements;

  var removeElement = options.removeElement,
      canRemove = typeof removeElement === 'function';

  var addElement = options.addElement,
      canAdd = typeof addElement === 'function',
      addLabel = options.addLabel || 'Add Value';

  var updateElement = options.updateElement,
      canUpdate = typeof updateElement === 'function';

  var editable = options.editable || function() { return true; },
      setControlValue = options.setControlValue;

  var show = options.show,
      canBeShown = typeof show === 'function';

  var elements = function(element, node) {
    return pick(getElements(element, node), modelProperties);
  };

  var html = domify((canAdd ?
    '<div class="bpp-table-add-row" ' + (canBeShown ? 'data-show="show"' : '') + '>' +
          '<label>' + escapeHTML(addLabel) + '</label>' +
          '<button class="action-button add" data-action="addElement"><span>+</span></button>' +
        '</div>' : '') +
        '<div class="bpp-table" data-show="showTable">' +
          '<div class="bpp-field-wrapper bpp-table-row">' +
             labelRow +
          '</div>' +
          '<div data-list-entry-container>' +
          '</div>' +
        '</div>');

  if (description) {
    html.appendChild(entryFieldDescription(translate, description, { show: 'showTable' }));
  }

  var factory = {
    id: id,
    html: html,

    get: function(element, node) {
      var boElements = elements(element, node, this.__invalidValues);

      var invalidValues = this.__invalidValues;

      delete this.__invalidValues;

      forEach(invalidValues, function(value, idx) {
        var element = boElements[idx];

        forEach(modelProperties, function(prop) {
          element[prop] = value[prop];
        });
      });

      return boElements;
    },

    set: function(element, values, node) {
      var action = this.__action || {};
      delete this.__action;

      if (action.id === 'delete-element') {
        return removeElement(element, node, action.idx);
      }
      else if (action.id === 'add-element') {
        return addElement(element, node);
      }
      else if (canUpdate) {
        var commands = [],
            valuesToValidate = values;

        if (typeof options.validate !== 'function') {
          valuesToValidate = diff(element, node, values, elements(element, node), editable);
        }

        var self = this;

        forEach(valuesToValidate, function(value) {
          var validationError,
              idx = values.indexOf(value);

          if (typeof options.validate === 'function') {
            validationError = options.validate(element, value, node, idx);
          }

          if (!validationError) {
            var cmd = updateElement(element, value, node, idx);

            if (cmd) {
              commands.push(cmd);
            }
          } else {

            // cache invalid value in an object by index as key
            self.__invalidValues = self.__invalidValues || {};
            self.__invalidValues[idx] = value;

            // execute a command, which does not do anything
            commands.push(cmdHelper.updateProperties(element, {}));
          }
        });

        return commands;
      }
    },
    createListEntryTemplate: function(value, index, selectBox) {
      return createInputRowTemplate(modelProperties, canRemove);
    },

    addElement: function(element, node, event, scopeNode) {
      var template = domify(createInputRowTemplate(modelProperties, canRemove));

      var container = getContainer(node);
      container.appendChild(template);

      this.__action = {
        id: 'add-element'
      };

      return true;
    },

    deleteElement: function(element, node, event, scopeNode) {
      var container = getContainer(node);
      var rowToDelete = event.delegateTarget.parentNode;
      var idx = parseInt(domAttr(rowToDelete, 'data-index'), 10);

      container.removeChild(rowToDelete);

      this.__action = {
        id: 'delete-element',
        idx: idx
      };

      return true;
    },

    editable: function(element, rowNode, input, prop, value, idx) {
      var entryNode = domClosest(rowNode, '[data-entry]');
      return editable(element, entryNode, prop, idx);
    },

    show: function(element, entryNode, node, scopeNode) {
      entryNode = getEntryNode(entryNode);
      return show(element, entryNode, node, scopeNode);
    },

    showTable: function(element, entryNode, node, scopeNode) {
      entryNode = getEntryNode(entryNode);
      var elems = elements(element, entryNode);
      return elems && elems.length && (!canBeShown || show(element, entryNode, node, scopeNode));
    },

    validateListItem: function(element, value, node, idx) {
      if (typeof options.validate === 'function') {
        return options.validate(element, value, node, idx);
      }
    }

  };

  // Update/set the selection on the correct position.
  // It's the same code like for an input value in the PropertiesPanel.js.
  if (setControlValue) {
    factory.setControlValue = function(element, rowNode, input, prop, value, idx) {
      var entryNode = getEntryNode(rowNode);

      var isReadOnly = domAttr(input, 'readonly');
      var oldValue = input.value;

      var selection;

      // prevents input fields from having the value 'undefined'
      if (value === undefined) {
        value = '';
      }

      // when the attribute 'readonly' exists, ignore the comparison
      // with 'oldValue' and 'value'
      if (!!isReadOnly && oldValue === value) {
        return;
      }

      // update selection on undo/redo
      if (document.activeElement === input) {
        selection = updateSelection(getSelection(input), oldValue, value);
      }

      setControlValue(element, entryNode, input, prop, value, idx);

      if (selection) {
        setSelection(input, selection);
      }

    };
  }

  return factory;

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/TextBoxEntryFactory.js":
/*!**********************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/TextBoxEntryFactory.js ***!
  \**********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify;

var escapeHTML = __webpack_require__(/*! ../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var entryFieldDescription = __webpack_require__(/*! ./EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");


var textBox = function(translate, options, defaultParameters) {

  var resource = defaultParameters,
      label = options.label || resource.id,
      canBeShown = !!options.show && typeof options.show === 'function',
      description = options.description;

  resource.html =
    domify('<label for="camunda-' + escapeHTML(resource.id) + '" ' +
    (canBeShown ? 'data-show="isShown"' : '') +
    '>' + label + '</label>' +
    '<div class="bpp-field-wrapper" ' +
    (canBeShown ? 'data-show="isShown"' : '') +
    '>' +
      '<div contenteditable="true" id="camunda-' + escapeHTML(resource.id) + '" ' +
            'name="' + escapeHTML(options.modelProperty) + '" />' +
    '</div>');

  // add description below text box entry field
  if (description) {
    resource.html.appendChild(entryFieldDescription(translate, description, { show: canBeShown && 'isShown' }));
  }

  if (canBeShown) {
    resource.isShown = function() {
      return options.show.apply(resource, arguments);
    };
  }

  resource.cssClasses = ['bpp-textbox'];

  return resource;
};

module.exports = textBox;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/TextInputEntryFactory.js":
/*!************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/TextInputEntryFactory.js ***!
  \************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var escapeHTML = __webpack_require__(/*! ../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify,
    domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

var entryFieldDescription = __webpack_require__(/*! ./EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");


var textField = function(translate, options, defaultParameters) {

  // Default action for the button next to the input-field
  var defaultButtonAction = function(element, inputNode) {
    var input = domQuery('input[name="' + options.modelProperty + '"]', inputNode);
    input.value = '';

    return true;
  };

  // default method to determine if the button should be visible
  var defaultButtonShow = function(element, inputNode) {
    var input = domQuery('input[name="' + options.modelProperty + '"]', inputNode);

    return input.value !== '';
  };


  var resource = defaultParameters,
      label = options.label || resource.id,
      dataValueLabel = options.dataValueLabel,
      buttonLabel = (options.buttonLabel || 'X'),
      actionName = (typeof options.buttonAction != 'undefined') ? options.buttonAction.name : 'clear',
      actionMethod = (typeof options.buttonAction != 'undefined') ? options.buttonAction.method : defaultButtonAction,
      showName = (typeof options.buttonShow != 'undefined') ? options.buttonShow.name : 'canClear',
      showMethod = (typeof options.buttonShow != 'undefined') ? options.buttonShow.method : defaultButtonShow,
      canBeDisabled = !!options.disabled && typeof options.disabled === 'function',
      canBeHidden = !!options.hidden && typeof options.hidden === 'function',
      description = options.description;

  resource.html =
    domify('<label for="camunda-' + escapeHTML(resource.id) + '" ' +
      (canBeDisabled ? 'data-disable="isDisabled" ' : '') +
      (canBeHidden ? 'data-show="isHidden" ' : '') +
      (dataValueLabel ? 'data-value="' + escapeHTML(dataValueLabel) + '"' : '') + '>'+ escapeHTML(label) +'</label>' +
    '<div class="bpp-field-wrapper" ' +
      (canBeDisabled ? 'data-disable="isDisabled"' : '') +
      (canBeHidden ? 'data-show="isHidden"' : '') +
      '>' +
      '<input id="camunda-' + escapeHTML(resource.id) + '" type="text" name="' + escapeHTML(options.modelProperty) + '" ' +
        (canBeDisabled ? 'data-disable="isDisabled"' : '') +
        (canBeHidden ? 'data-show="isHidden"' : '') +
        ' />' +
      '<button class="action-button ' + escapeHTML(actionName) + '" data-action="' + escapeHTML(actionName) + '" data-show="' + escapeHTML(showName) + '" ' +
        (canBeDisabled ? 'data-disable="isDisabled"' : '') +
        (canBeHidden ? ' data-show="isHidden"' : '') + '>' +
        '<span>' + escapeHTML(buttonLabel) + '</span>' +
      '</button>' +
    '</div>');

  // add description below text input entry field
  if (description) {
    resource.html.appendChild(entryFieldDescription(translate, description, { show: canBeHidden && 'isHidden' }));
  }

  resource[actionName] = actionMethod;
  resource[showName] = showMethod;

  if (canBeDisabled) {
    resource.isDisabled = function() {
      return options.disabled.apply(resource, arguments);
    };
  }

  if (canBeHidden) {
    resource.isHidden = function() {
      return !options.hidden.apply(resource, arguments);
    };
  }

  resource.cssClasses = ['bpp-textfield'];

  return resource;
};

module.exports = textField;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/ToggleSwitchEntryFactory.js":
/*!***************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/ToggleSwitchEntryFactory.js ***!
  \***************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    cmdHelper = __webpack_require__(/*! ../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    escapeHTML = __webpack_require__(/*! ../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var entryFieldDescription = __webpack_require__(/*! ./EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");

var domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify;

var toggleSwitch = function(translate, options, defaultParameters) {
  var resource = defaultParameters,
      id = resource.id,
      label = options.label || id,
      canBeHidden = !!options.hidden && typeof options.hidden === 'function',
      isOn = options.isOn,
      descriptionOn = options.descriptionOn,
      descriptionOff = options.descriptionOff,
      labelOn = options.labelOn,
      labelOff = options.labelOff;

  resource.html = document.createDocumentFragment();

  resource.html.appendChild(domify('<label for="' + escapeHTML(id) + '" ' +
      (canBeHidden ? 'data-show="shouldShow"' : '') +
      '>' + escapeHTML(label) + '</label>' +
    '<div class="bpp-field-wrapper"' +
    (canBeHidden ? 'data-show="shouldShow"' : '') +
    '>' +
      '<label class="bpp-toggle-switch__switcher">' +
        '<input id="' + escapeHTML(id) + '" ' +
            'type="checkbox" ' +
            'name="' + escapeHTML(options.modelProperty) + '" />' +
        '<span class="bpp-toggle-switch__slider"></span>' +
      '</label>' +
      '<p class="bpp-toggle-switch__label" data-show="isOn">' +
        escapeHTML(labelOn) +
      '</p>' +
      '<p class="bpp-toggle-switch__label" data-show="isOff">' +
        escapeHTML(labelOff) +
      '</p>' +
    '</div>'));

  if (descriptionOn) {
    resource.html.appendChild(entryFieldDescription(translate, descriptionOn, { show: 'isOn' }));
  }

  if (descriptionOff) {
    resource.html.appendChild(entryFieldDescription(translate, descriptionOff, { show: 'isOff' }));
  }

  resource.get = function(element) {
    var bo = getBusinessObject(element),
        res = {};

    res[options.modelProperty] = bo.get(options.modelProperty);

    return res;
  };

  resource.set = function(element, values) {
    var res = {};

    res[options.modelProperty] = !!values[options.modelProperty];

    return cmdHelper.updateProperties(element, res);
  };

  if (typeof options.set === 'function') {
    resource.set = options.set;
  }

  if (typeof options.get === 'function') {
    resource.get = options.get;
  }

  if (canBeHidden) {
    resource.shouldShow = function() {
      return !options.hidden.apply(resource, arguments);
    };
  }

  resource.isOn = function() {
    if (canBeHidden && !resource.shouldShow()) {
      return false;
    }

    return isOn.apply(resource, arguments);
  };

  resource.isOff = function() {
    if (canBeHidden && !resource.shouldShow()) {
      return false;
    }

    return !resource.isOn();
  };

  resource.cssClasses = ['bpp-toggle-switch'];

  return resource;
};

module.exports = toggleSwitch;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/factory/ValidationAwareTextInput.js":
/*!***************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/factory/ValidationAwareTextInput.js ***!
  \***************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var textField = __webpack_require__(/*! ./TextInputEntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/TextInputEntryFactory.js");

/**
 * This function is a wrapper around TextInputEntryFactory.
 * It adds functionality to cache an invalid value entered in the
 * text input, instead of setting it on the business object.
 */
var validationAwareTextField = function(translate, options, defaultParameters) {

  var modelProperty = options.modelProperty;

  defaultParameters.get = function(element, node) {
    var value = this.__lastInvalidValue;

    delete this.__lastInvalidValue;

    var properties = {};

    properties[modelProperty] = value !== undefined ? value : options.getProperty(element, node);

    return properties;
  };

  defaultParameters.set = function(element, values, node) {
    var validationErrors = validate.apply(this, [ element, values, node ]),
        propertyValue = values[modelProperty];

    // make sure we do not update the id
    if (validationErrors && validationErrors[modelProperty]) {
      this.__lastInvalidValue = propertyValue;

      return options.setProperty(element, {}, node);
    } else {
      var properties = {};

      properties[modelProperty] = propertyValue;

      return options.setProperty(element, properties, node);
    }
  };

  var validate = defaultParameters.validate = function(element, values, node) {
    var value = values[modelProperty] || this.__lastInvalidValue;

    var property = {};
    property[modelProperty] = value;

    return options.validate(element, property, node);
  };

  return textField(translate, options, defaultParameters);
};

module.exports = validationAwareTextField;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/helper/AsyncCapableHelper.js":
/*!********************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/helper/AsyncCapableHelper.js ***!
  \********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var map = __webpack_require__(/*! lodash/map */ "./node_modules/lodash/map.js");

var extensionElementsHelper = __webpack_require__(/*! ./ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js");

/**
 * Returns true if the attribute 'camunda:asyncBefore' is set
 * to true.
 *
 * @param  {ModdleElement} bo
 *
 * @return {boolean} a boolean value
 */
function isAsyncBefore(bo) {
  return !!(bo.get('camunda:asyncBefore') || bo.get('camunda:async'));
}

module.exports.isAsyncBefore = isAsyncBefore;

/**
 * Returns true if the attribute 'camunda:asyncAfter' is set
 * to true.
 *
 * @param  {ModdleElement} bo
 *
 * @return {boolean} a boolean value
 */
function isAsyncAfter(bo) {
  return !!bo.get('camunda:asyncAfter');
}

module.exports.isAsyncAfter = isAsyncAfter;

/**
 * Returns true if the attribute 'camunda:exclusive' is set
 * to true.
 *
 * @param  {ModdleElement} bo
 *
 * @return {boolean} a boolean value
 */
function isExclusive(bo) {
  return !!bo.get('camunda:exclusive');
}

module.exports.isExclusive = isExclusive;

/**
 * Get first 'camunda:FailedJobRetryTimeCycle' from the business object.
 *
 * @param  {ModdleElement} bo
 *
 * @return {Array<ModdleElement>} a list of 'camunda:FailedJobRetryTimeCycle'
 */
function getFailedJobRetryTimeCycle(bo) {
  return extensionElementsHelper.getExtensionElements(bo, 'camunda:FailedJobRetryTimeCycle')[0];
}

module.exports.getFailedJobRetryTimeCycle = getFailedJobRetryTimeCycle;

/**
 * Removes all existing 'camunda:FailedJobRetryTimeCycle' from the business object
 *
 * @param  {ModdleElement} bo
 *
 * @return {Array<ModdleElement>} a list of 'camunda:FailedJobRetryTimeCycle'
 */
function removeFailedJobRetryTimeCycle(bo, element) {
  var retryTimeCycles = extensionElementsHelper.getExtensionElements(bo, 'camunda:FailedJobRetryTimeCycle');
  return map(retryTimeCycles, function(cycle) {
    return extensionElementsHelper.removeEntry(bo, element, cycle);
  });
}

module.exports.removeFailedJobRetryTimeCycle = removeFailedJobRetryTimeCycle;

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/helper/CategoryHelper.js":
/*!****************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/helper/CategoryHelper.js ***!
  \****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var collectionAdd = __webpack_require__(/*! diagram-js/lib/util/Collections */ "./node_modules/diagram-js/lib/util/Collections.js").add,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var CategoryHelper = {};

module.exports = CategoryHelper;

/**
 * Creates a new bpmn:CategoryValue inside a new bpmn:Category
 *
 * @param {ModdleElement} definitions
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {ModdleElement} categoryValue.
 */
CategoryHelper.createCategoryValue = function(definitions, bpmnFactory) {
  var categoryValue = bpmnFactory.create('bpmn:CategoryValue'),
      category = bpmnFactory.create('bpmn:Category', {
        categoryValue: [ categoryValue ]
      });

  // add to correct place
  collectionAdd(definitions.get('rootElements'), category);
  getBusinessObject(category).$parent = definitions;
  getBusinessObject(categoryValue).$parent = category;

  return categoryValue;

};

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js":
/*!***********************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js ***!
  \***********************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var CmdHelper = {};
module.exports = CmdHelper;

CmdHelper.updateProperties = function(element, properties) {
  return {
    cmd: 'element.updateProperties',
    context: { element: element, properties: properties }
  };
};

CmdHelper.updateBusinessObject = function(element, businessObject, newProperties) {
  return {
    cmd: 'properties-panel.update-businessobject',
    context: {
      element: element,
      businessObject: businessObject,
      properties: newProperties
    }
  };
};

CmdHelper.addElementsTolist = function(element, businessObject, listPropertyName, objectsToAdd, objectsToPrepend) {
  return {
    cmd: 'properties-panel.update-businessobject-list',
    context: {
      element: element,
      currentObject: businessObject,
      propertyName: listPropertyName,
      objectsToPrepend: objectsToPrepend,
      objectsToAdd: objectsToAdd
    }
  };
};

CmdHelper.removeElementsFromList = function(element, businessObject, listPropertyName, referencePropertyName, objectsToRemove) {

  return {
    cmd: 'properties-panel.update-businessobject-list',
    context: {
      element: element,
      currentObject: businessObject,
      propertyName: listPropertyName,
      referencePropertyName: referencePropertyName,
      objectsToRemove: objectsToRemove
    }
  };
};


CmdHelper.addAndRemoveElementsFromList = function(element, businessObject, listPropertyName, referencePropertyName, objectsToAdd, objectsToRemove) {

  return {
    cmd: 'properties-panel.update-businessobject-list',
    context: {
      element: element,
      currentObject: businessObject,
      propertyName: listPropertyName,
      referencePropertyName: referencePropertyName,
      objectsToAdd: objectsToAdd,
      objectsToRemove: objectsToRemove
    }
  };
};


CmdHelper.setList = function(element, businessObject, listPropertyName, updatedObjectList) {
  return {
    cmd: 'properties-panel.update-businessobject-list',
    context: {
      element: element,
      currentObject: businessObject,
      propertyName: listPropertyName,
      updatedObjectList: updatedObjectList
    }
  };
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js":
/*!***************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js ***!
  \***************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var ElementHelper = {};
module.exports = ElementHelper;

/**
 * Creates a new element and set the parent to it
 *
 * @method ElementHelper#createElement
 *
 * @param {String} elementType of the new element
 * @param {Object} properties of the new element in key-value pairs
 * @param {moddle.object} parent of the new element
 * @param {BpmnFactory} factory which creates the new element
 *
 * @returns {djs.model.Base} element which is created
 */
ElementHelper.createElement = function(elementType, properties, parent, factory) {
  var element = factory.create(elementType, properties);
  element.$parent = parent;

  return element;
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/helper/EventDefinitionHelper.js":
/*!***********************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/helper/EventDefinitionHelper.js ***!
  \***********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    forEach = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js");

var EventDefinitionHelper = {};

module.exports = EventDefinitionHelper;

EventDefinitionHelper.getEventDefinition = function(element, eventType) {

  var bo = getBusinessObject(element),
      eventDefinition = null;

  if (bo.eventDefinitions) {
    forEach(bo.eventDefinitions, function(event) {
      if (is(event, eventType)) {
        eventDefinition = event;
      }
    });
  }

  return eventDefinition;
};

EventDefinitionHelper.getTimerEventDefinition = function(element) {
  return this.getEventDefinition(element, 'bpmn:TimerEventDefinition');
};

EventDefinitionHelper.getMessageEventDefinition = function(element) {
  return this.getEventDefinition(element, 'bpmn:MessageEventDefinition');
};

EventDefinitionHelper.getSignalEventDefinition = function(element) {
  return this.getEventDefinition(element, 'bpmn:SignalEventDefinition');
};

EventDefinitionHelper.getErrorEventDefinition = function(element) {
  return this.getEventDefinition(element, 'bpmn:ErrorEventDefinition');
};

EventDefinitionHelper.getEscalationEventDefinition = function(element) {
  return this.getEventDefinition(element, 'bpmn:EscalationEventDefinition');
};

EventDefinitionHelper.getCompensateEventDefinition = function(element) {
  return this.getEventDefinition(element, 'bpmn:CompensateEventDefinition');
};

EventDefinitionHelper.getLinkEventDefinition = function(element) {
  return this.getEventDefinition(element, 'bpmn:LinkEventDefinition');
};

EventDefinitionHelper.getConditionalEventDefinition = function(element) {
  return this.getEventDefinition(element, 'bpmn:ConditionalEventDefinition');
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js":
/*!*************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js ***!
  \*************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var cmdHelper = __webpack_require__(/*! ./CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    elementHelper = __webpack_require__(/*! ./ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js");

var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is;

var ExtensionElementsHelper = {};

ExtensionElementsHelper.getExtensionElements = function(bo, type) {
  var elements = [];
  var extensionElements = bo.get('extensionElements');

  if (typeof extensionElements !== 'undefined') {
    var extensionValues = extensionElements.get('values');
    if (typeof extensionValues !== 'undefined') {
      elements = extensionValues.filter(function(value) {
        return is(value, type);
      });
    }
  }

  return elements;
};

ExtensionElementsHelper.addEntry = function(bo, element, entry, bpmnFactory) {
  var extensionElements = bo.get('extensionElements');

  // if there is no extensionElements list, create one
  if (!extensionElements) {

    // TODO: Ask Daniel which operation costs more
    extensionElements = elementHelper.createElement('bpmn:ExtensionElements', { values: [entry] }, bo, bpmnFactory);
    return { extensionElements : extensionElements };
  } else {

    // add new failedJobRetryExtensionElement to existing extensionElements list
    return cmdHelper.addElementsTolist(element, extensionElements, 'values', [entry]);
  }
};

ExtensionElementsHelper.removeEntry = function(bo, element, entry) {
  var extensionElements = bo.get('extensionElements');

  if (!extensionElements) {

    // return an empty command when there is no extensionElements list
    return {};
  }

  return cmdHelper.removeElementsFromList(element, extensionElements, 'values', 'extensionElements', [entry]);
};

module.exports = ExtensionElementsHelper;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/helper/FormHelper.js":
/*!************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/helper/FormHelper.js ***!
  \************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    getExtensionElements = __webpack_require__(/*! ./ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js").getExtensionElements;

var FormHelper = {};

module.exports = FormHelper;

/**
 * Return form data from business object or undefined if none exist
 *
 * @param  {djs.model.Base} element
 *
 * @return {ModdleElement|undefined} formData
 */
FormHelper.getFormData = function(element) {
  var bo = getBusinessObject(element);

  var formData = getExtensionElements(bo, 'camunda:FormData');

  return formData[0];
};


/**
 * Return all form fields existing in the business object, and
 * an empty array if none exist.
 *
 * @param  {djs.model.Base} element
 *
 * @return {Array} a list of form field objects
 */
FormHelper.getFormFields = function(element) {
  var formData = this.getFormData(element);

  if (typeof formData === 'undefined') {
    return [];
  }

  return formData.fields || [];
};


/**
 * Get a form field from the business object at given index
 *
 * @param {djs.model.Base} element
 * @param {number} idx
 *
 * @return {ModdleElement} the form field
 */
FormHelper.getFormField = function(element, idx) {

  var formFields = this.getFormFields(element);

  return formFields[idx];
};


/**
 * Get all constraints for a specific form field from the business object
 *
 * @param  {ModdleElement} formField
 *
 * @return {Array<ModdleElement>} a list of constraint objects
 */
FormHelper.getConstraints = function(formField) {
  if (formField && formField.validation && formField.validation.constraints) {
    return formField.validation.constraints;
  }
  return [];
};


/**
 * Get all camunda:value objects for a specific form field from the business object
 *
 * @param  {ModdleElement} formField
 *
 * @return {Array<ModdleElement>} a list of camunda:value objects
 */
FormHelper.getEnumValues = function(formField) {
  if (formField && formField.values) {
    return formField.values;
  }
  return [];
};



/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/helper/ImplementationTypeHelper.js":
/*!**************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/helper/ImplementationTypeHelper.js ***!
  \**************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var ModelUtil = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js"),
    is = ModelUtil.is,
    getBusinessObject = ModelUtil.getBusinessObject;

var eventDefinitionHelper = __webpack_require__(/*! ./EventDefinitionHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/EventDefinitionHelper.js");
var extensionsElementHelper = __webpack_require__(/*! ./ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js");

var ImplementationTypeHelper = {};

module.exports = ImplementationTypeHelper;

/**
 * Returns 'true' if the given element is 'camunda:ServiceTaskLike'
 *
 * @param {djs.model.Base} element
 *
 * @return {boolean} a boolean value
 */
ImplementationTypeHelper.isServiceTaskLike = function(element) {
  return is(element, 'camunda:ServiceTaskLike');
};

/**
 * Returns 'true' if the given element is 'camunda:DmnCapable'
 *
 * @param {djs.model.Base} element
 *
 * @return {boolean} a boolean value
 */
ImplementationTypeHelper.isDmnCapable = function(element) {
  return is(element, 'camunda:DmnCapable');
};

/**
 * Returns 'true' if the given element is 'camunda:ExternalCapable'
 *
 * @param {djs.model.Base} element
 *
 * @return {boolean} a boolean value
 */
ImplementationTypeHelper.isExternalCapable = function(element) {
  return is(element, 'camunda:ExternalCapable');
};

/**
 * Returns 'true' if the given element is 'camunda:TaskListener'
 *
 * @param {djs.model.Base} element
 *
 * @return {boolean} a boolean value
 */
ImplementationTypeHelper.isTaskListener = function(element) {
  return is(element, 'camunda:TaskListener');
};

/**
 * Returns 'true' if the given element is 'camunda:ExecutionListener'
 *
 * @param {djs.model.Base} element
 *
 * @return {boolean} a boolean value
 */
ImplementationTypeHelper.isExecutionListener = function(element) {
  return is(element, 'camunda:ExecutionListener');
};

/**
 * Returns 'true' if the given element is 'camunda:ExecutionListener' or
 * 'camunda:TaskListener'
 *
 * @param {djs.model.Base} element
 *
 * @return {boolean} a boolean value
 */
ImplementationTypeHelper.isListener = function(element) {
  return this.isTaskListener(element) || this.isExecutionListener(element);
};

/**
 * Returns 'true' if the given element is 'bpmn:SequenceFlow'
 *
 * @param {djs.model.Base} element
 *
 * @return {boolean} a boolean value
 */
ImplementationTypeHelper.isSequenceFlow = function(element) {
  return is(element, 'bpmn:SequenceFlow');
};

/**
 * Get a 'camunda:ServiceTaskLike' business object.
 *
 * If the given element is not a 'camunda:ServiceTaskLike', then 'false'
 * is returned.
 *
 * @param {djs.model.Base} element
 *
 * @return {ModdleElement} the 'camunda:ServiceTaskLike' business object
 */
ImplementationTypeHelper.getServiceTaskLikeBusinessObject = function(element) {

  if (is(element, 'bpmn:IntermediateThrowEvent') || is(element, 'bpmn:EndEvent')) {

    // change business object to 'messageEventDefinition' when
    // the element is a message intermediate throw event or message end event
    // because the camunda extensions (e.g. camunda:class) are in the message
    // event definition tag and not in the intermediate throw event or end event tag
    var messageEventDefinition = eventDefinitionHelper.getMessageEventDefinition(element);
    if (messageEventDefinition) {
      element = messageEventDefinition;
    }
  }

  return this.isServiceTaskLike(element) && getBusinessObject(element);

};

/**
 * Returns the implementation type of the given element.
 *
 * Possible implementation types are:
 * - dmn
 * - connector
 * - external
 * - class
 * - expression
 * - delegateExpression
 * - script
 * - or undefined, when no matching implementation type is found
 *
 * @param  {djs.model.Base} element
 *
 * @return {String} the implementation type
 */
ImplementationTypeHelper.getImplementationType = function(element) {

  var bo = this.getServiceTaskLikeBusinessObject(element);

  if (!bo) {
    if (this.isListener(element)) {
      bo = element;
    } else {
      return;
    }
  }

  if (this.isDmnCapable(bo)) {
    var decisionRef = bo.get('camunda:decisionRef');
    if (typeof decisionRef !== 'undefined') {
      return 'dmn';
    }
  }

  if (this.isServiceTaskLike(bo)) {
    var connectors = extensionsElementHelper.getExtensionElements(bo, 'camunda:Connector');
    if (connectors.length) {
      return 'connector';
    }
  }

  if (this.isExternalCapable(bo)) {
    var type = bo.get('camunda:type');
    if (type === 'external') {
      return 'external';
    }
  }

  var cls = bo.get('camunda:class');
  if (typeof cls !== 'undefined') {
    return 'class';
  }

  var expression = bo.get('camunda:expression');
  if (typeof expression !== 'undefined') {
    return 'expression';
  }

  var delegateExpression = bo.get('camunda:delegateExpression');
  if (typeof delegateExpression !== 'undefined') {
    return 'delegateExpression';
  }

  if (this.isListener(bo)) {
    var script = bo.get('script');
    if (typeof script !== 'undefined') {
      return 'script';
    }
  }

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/helper/InputOutputHelper.js":
/*!*******************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/helper/InputOutputHelper.js ***!
  \*******************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var ModelUtil = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js"),
    is = ModelUtil.is,
    getBusinessObject = ModelUtil.getBusinessObject;

var extensionElementsHelper = __webpack_require__(/*! ./ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js"),
    implementationTypeHelper = __webpack_require__(/*! ./ImplementationTypeHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ImplementationTypeHelper.js");

var InputOutputHelper = {};

module.exports = InputOutputHelper;

function getElements(bo, type, prop) {
  var elems = extensionElementsHelper.getExtensionElements(bo, type);
  return !prop ? elems : (elems[0] || {})[prop] || [];
}

function getParameters(element, prop, insideConnector) {
  var inputOutput = InputOutputHelper.getInputOutput(element, insideConnector);
  return (inputOutput && inputOutput.get(prop)) || [];
}

/**
 * Get a inputOutput from the business object
 *
 * @param {djs.model.Base} element
 * @param  {boolean} insideConnector
 *
 * @return {ModdleElement} the inputOutput object
 */
InputOutputHelper.getInputOutput = function(element, insideConnector) {
  if (!insideConnector) {
    var bo = getBusinessObject(element);
    return (getElements(bo, 'camunda:InputOutput') || [])[0];
  }
  var connector = this.getConnector(element);
  return connector && connector.get('inputOutput');
};

/**
 * Get a connector from the business object
 *
 * @param {djs.model.Base} element
 *
 * @return {ModdleElement} the connector object
 */
InputOutputHelper.getConnector = function(element) {
  var bo = implementationTypeHelper.getServiceTaskLikeBusinessObject(element);
  return bo && (getElements(bo, 'camunda:Connector') || [])[0];
};

/**
 * Return all input parameters existing in the business object, and
 * an empty array if none exist.
 *
 * @param  {djs.model.Base} element
 * @param  {boolean} insideConnector
 *
 * @return {Array} a list of input parameter objects
 */
InputOutputHelper.getInputParameters = function(element, insideConnector) {
  return getParameters.apply(this, [ element, 'inputParameters', insideConnector ]);
};

/**
 * Return all output parameters existing in the business object, and
 * an empty array if none exist.
 *
 * @param  {djs.model.Base} element
 * @param  {boolean} insideConnector
 *
 * @return {Array} a list of output parameter objects
 */
InputOutputHelper.getOutputParameters = function(element, insideConnector) {
  return getParameters.apply(this, [ element, 'outputParameters', insideConnector ]);
};

/**
 * Get a input parameter from the business object at given index
 *
 * @param {djs.model.Base} element
 * @param  {boolean} insideConnector
 * @param {number} idx
 *
 * @return {ModdleElement} input parameter
 */
InputOutputHelper.getInputParameter = function(element, insideConnector, idx) {
  return this.getInputParameters(element, insideConnector)[idx];
};

/**
 * Get a output parameter from the business object at given index
 *
 * @param {djs.model.Base} element
 * @param  {boolean} insideConnector
 * @param {number} idx
 *
 * @return {ModdleElement} output parameter
 */
InputOutputHelper.getOutputParameter = function(element, insideConnector, idx) {
  return this.getOutputParameters(element, insideConnector)[idx];
};

/**
 * Returns 'true' if the given element supports inputOutput
 *
 * @param {djs.model.Base} element
 * @param  {boolean} insideConnector
 *
 * @return {boolean} a boolean value
 */
InputOutputHelper.isInputOutputSupported = function(element, insideConnector) {

  if (insideConnector) {
    return true;
  }

  var bo = getBusinessObject(element);

  return (
    is(bo, 'bpmn:FlowNode') && !(
      is(bo, 'bpmn:StartEvent') ||
      is(bo, 'bpmn:Gateway') ||
      is(bo, 'bpmn:BoundaryEvent') ||
      (
        is(bo, 'bpmn:SubProcess') && bo.get('triggeredByEvent')
      )
    )
  );
};

/**
 * Returns 'true' if the given element supports output parameters
 *
 * @param {djs.model.Base} element
 * @param  {boolean} insideConnector
 *
 * @return {boolean} a boolean value
 */
InputOutputHelper.areOutputParametersSupported = function(element, insideConnector) {
  var bo = getBusinessObject(element);
  return insideConnector || (!is(bo, 'bpmn:EndEvent') && !bo.loopCharacteristics);
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/helper/ParticipantHelper.js":
/*!*******************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/helper/ParticipantHelper.js ***!
  \*******************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    cmdHelper = __webpack_require__(/*! ./CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");


var ParticipantHelper = {};

module.exports = ParticipantHelper;

ParticipantHelper.modifyProcessBusinessObject = function(element, property, values) {
  if (!is(element, 'bpmn:Participant')) {
    return {};
  }

  var bo = getBusinessObject(element).get('processRef'),
      properties = {};

  properties[property] = values[property];

  return cmdHelper.updateBusinessObject(element, bo, properties);
};

ParticipantHelper.getProcessBusinessObject = function(element, propertyName) {
  if (!is(element, 'bpmn:Participant')) {
    return {};
  }

  var bo = getBusinessObject(element).get('processRef'),
      properties = {};

  properties[propertyName] = bo.get(propertyName);

  return properties;
};

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/DocumentationProps.js":
/*!*********************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/DocumentationProps.js ***!
  \*********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    cmdHelper = __webpack_require__(/*! ../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

var ModelUtil = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js"),
    is = ModelUtil.is,
    getBusinessObject = ModelUtil.getBusinessObject;


module.exports = function(group, element, bpmnFactory, translate) {

  var getValue = function(businessObject) {
    return function(element) {
      var documentations = businessObject && businessObject.get('documentation'),
          text = (documentations && documentations.length > 0) ? documentations[0].text : '';

      return { documentation: text };
    };
  };

  var setValue = function(businessObject) {
    return function(element, values) {
      var newObjectList = [];

      if (typeof values.documentation !== 'undefined' && values.documentation !== '') {
        newObjectList.push(bpmnFactory.create('bpmn:Documentation', {
          text: values.documentation
        }));
      }

      return cmdHelper.setList(element, businessObject, 'documentation', newObjectList);
    };
  };

  // Element Documentation
  var elementDocuEntry = entryFactory.textBox(translate, {
    id: 'documentation',
    label: translate('Element Documentation'),
    modelProperty: 'documentation'
  });

  elementDocuEntry.set = setValue(getBusinessObject(element));

  elementDocuEntry.get = getValue(getBusinessObject(element));

  group.entries.push(elementDocuEntry);


  var processRef;

  // Process Documentation when having a Collaboration Diagram
  if (is(element, 'bpmn:Participant')) {

    processRef = getBusinessObject(element).processRef;

    // do not show for collapsed Pools/Participants
    if (processRef) {
      var processDocuEntry = entryFactory.textBox(translate, {
        id: 'process-documentation',
        label: translate('Process Documentation'),
        modelProperty: 'documentation'
      });

      processDocuEntry.set = setValue(processRef);

      processDocuEntry.get = getValue(processRef);

      group.entries.push(processDocuEntry);
    }
  }

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/EventProps.js":
/*!*************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/EventProps.js ***!
  \*************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    isAny = __webpack_require__(/*! bpmn-js/lib/features/modeling/util/ModelingUtil */ "./node_modules/bpmn-js/lib/features/modeling/util/ModelingUtil.js").isAny,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    eventDefinitionHelper = __webpack_require__(/*! ../../../helper/EventDefinitionHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/EventDefinitionHelper.js");

var forEach = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js");

var message = __webpack_require__(/*! ./implementation/MessageEventDefinition */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/MessageEventDefinition.js"),
    signal = __webpack_require__(/*! ./implementation/SignalEventDefinition */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/SignalEventDefinition.js"),
    error = __webpack_require__(/*! ./implementation/ErrorEventDefinition */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ErrorEventDefinition.js"),
    escalation = __webpack_require__(/*! ./implementation/EscalationEventDefinition */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/EscalationEventDefinition.js"),
    timer = __webpack_require__(/*! ./implementation/TimerEventDefinition */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/TimerEventDefinition.js"),
    compensation = __webpack_require__(/*! ./implementation/CompensateEventDefinition */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/CompensateEventDefinition.js"),
    condition = __webpack_require__(/*! ./implementation/ConditionalEventDefinition */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ConditionalEventDefinition.js");


module.exports = function(group, element, bpmnFactory, elementRegistry, translate) {
  var events = [
    'bpmn:StartEvent',
    'bpmn:EndEvent',
    'bpmn:IntermediateThrowEvent',
    'bpmn:BoundaryEvent',
    'bpmn:IntermediateCatchEvent'
  ];

  // Message and Signal Event Definition
  forEach(events, function(event) {
    if (is(element, event)) {

      var messageEventDefinition = eventDefinitionHelper.getMessageEventDefinition(element),
          signalEventDefinition = eventDefinitionHelper.getSignalEventDefinition(element);

      if (messageEventDefinition) {
        message(group, element, bpmnFactory, messageEventDefinition, translate);
      }

      if (signalEventDefinition) {
        signal(group, element, bpmnFactory, signalEventDefinition, translate);
      }

    }
  });

  // Special Case: Receive Task
  if (is(element, 'bpmn:ReceiveTask')) {
    message(group, element, bpmnFactory, getBusinessObject(element), translate);
  }

  // Error Event Definition
  var errorEvents = [
    'bpmn:StartEvent',
    'bpmn:BoundaryEvent',
    'bpmn:EndEvent'
  ];

  forEach(errorEvents, function(event) {
    if (is(element, event)) {

      var errorEventDefinition = eventDefinitionHelper.getErrorEventDefinition(element);

      if (errorEventDefinition) {

        error(group, element, bpmnFactory, errorEventDefinition, translate);
      }
    }
  });

  // Escalation Event Definition
  var escalationEvents = [
    'bpmn:StartEvent',
    'bpmn:BoundaryEvent',
    'bpmn:IntermediateThrowEvent',
    'bpmn:EndEvent'
  ];

  forEach(escalationEvents, function(event) {
    if (is(element, event)) {

      var showEscalationCodeVariable = is(element, 'bpmn:StartEvent') || is(element, 'bpmn:BoundaryEvent');

      // get business object
      var escalationEventDefinition = eventDefinitionHelper.getEscalationEventDefinition(element);

      if (escalationEventDefinition) {
        escalation(group, element, bpmnFactory, escalationEventDefinition, showEscalationCodeVariable,
          translate);
      }
    }

  });

  // Timer Event Definition
  var timerEvents = [
    'bpmn:StartEvent',
    'bpmn:BoundaryEvent',
    'bpmn:IntermediateCatchEvent'
  ];

  forEach(timerEvents, function(event) {
    if (is(element, event)) {

      // get business object
      var timerEventDefinition = eventDefinitionHelper.getTimerEventDefinition(element);

      if (timerEventDefinition) {
        timer(group, element, bpmnFactory, timerEventDefinition, translate);
      }
    }
  });

  // Compensate Event Definition
  var compensationEvents = [
    'bpmn:EndEvent',
    'bpmn:IntermediateThrowEvent'
  ];

  forEach(compensationEvents, function(event) {
    if (is(element, event)) {

      // get business object
      var compensateEventDefinition = eventDefinitionHelper.getCompensateEventDefinition(element);

      if (compensateEventDefinition) {
        compensation(group, element, bpmnFactory, compensateEventDefinition, elementRegistry, translate);
      }
    }
  });


  // Conditional Event Definition
  var conditionalEvents = [
    'bpmn:StartEvent',
    'bpmn:BoundaryEvent',
    'bpmn:IntermediateThrowEvent',
    'bpmn:IntermediateCatchEvent'
  ];

  if (isAny(element, conditionalEvents)) {

    // get business object
    var conditionalEventDefinition = eventDefinitionHelper.getConditionalEventDefinition(element);

    if (conditionalEventDefinition) {
      condition(group, element, bpmnFactory, conditionalEventDefinition, elementRegistry, translate);
    }
  }

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/ExecutableProps.js":
/*!******************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/ExecutableProps.js ***!
  \******************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var participantHelper = __webpack_require__(/*! ../../../helper/ParticipantHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ParticipantHelper.js");

module.exports = function(group, element, translate) {

  var bo = getBusinessObject(element);

  if (!bo) {
    return;
  }

  if (is(element, 'bpmn:Process') || (is(element, 'bpmn:Participant') && bo.get('processRef'))) {

    var executableEntry = entryFactory.checkbox(translate, {
      id: 'process-is-executable',
      label: translate('Executable'),
      modelProperty: 'isExecutable'
    });

    // in participants we have to change the default behavior of set and get
    if (is(element, 'bpmn:Participant')) {
      executableEntry.get = function(element) {
        return participantHelper.getProcessBusinessObject(element, 'isExecutable');
      };

      executableEntry.set = function(element, values) {
        return participantHelper.modifyProcessBusinessObject(element, 'isExecutable', values);
      };
    }

    group.entries.push(executableEntry);
  }

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/IdProps.js":
/*!**********************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/IdProps.js ***!
  \**********************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    utils = __webpack_require__(/*! ../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js"),
    cmdHelper = __webpack_require__(/*! ../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

module.exports = function(group, element, translate, options) {
  if (!options) {
    options = {};
  }

  // Id
  group.entries.push(entryFactory.validationAwareTextField(translate, {
    id: options.id || 'id',
    label: translate(options.label || 'Id'),
    description: options.description && translate(options.description),
    modelProperty: 'id',
    getProperty: function(element) {
      return getBusinessObject(element).id;
    },
    setProperty: function(element, properties) {

      element = element.labelTarget || element;

      return cmdHelper.updateProperties(element, properties);
    },
    validate: function(element, values) {
      var idValue = values.id;

      var bo = getBusinessObject(element);

      var idError = utils.isIdValid(bo, idValue, translate);

      return idError ? { id: idError } : {};
    }
  }));

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/LinkProps.js":
/*!************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/LinkProps.js ***!
  \************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    cmdHelper = __webpack_require__(/*! ../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

var forEach = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js");

function getLinkEventDefinition(element) {

  var bo = getBusinessObject(element);

  var linkEventDefinition = null;
  if (bo.eventDefinitions) {
    forEach(bo.eventDefinitions, function(eventDefinition) {
      if (is(eventDefinition, 'bpmn:LinkEventDefinition')) {
        linkEventDefinition = eventDefinition;
      }
    });
  }

  return linkEventDefinition;
}

module.exports = function(group, element, translate) {
  var linkEvents = [ 'bpmn:IntermediateThrowEvent', 'bpmn:IntermediateCatchEvent' ];

  forEach(linkEvents, function(event) {
    if (is(element, event)) {

      var linkEventDefinition = getLinkEventDefinition(element);

      if (linkEventDefinition) {
        var entry = entryFactory.textField(translate, {
          id: 'link-event',
          label: translate('Link Name'),
          modelProperty: 'link-name'
        });

        entry.get = function() {
          return { 'link-name': linkEventDefinition.get('name') };
        };

        entry.set = function(element, values) {
          var newProperties = {
            name: values['link-name']
          };
          return cmdHelper.updateBusinessObject(element, linkEventDefinition, newProperties);
        };

        group.entries.push(entry);
      }
    }
  });
};



/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/NameProps.js":
/*!************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/NameProps.js ***!
  \************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var nameEntryFactory = __webpack_require__(/*! ./implementation/Name */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/Name.js"),
    createCategoryValue = __webpack_require__(/*! ../../../helper/CategoryHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CategoryHelper.js").createCategoryValue,
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

module.exports = function(group, element, bpmnFactory, canvas, translate, options) {
  if (!options) {
    options = {};
  }

  function initializeCategory(semantic) {
    var rootElement = canvas.getRootElement(),
        definitions = getBusinessObject(rootElement).$parent,
        categoryValue = createCategoryValue(definitions, bpmnFactory);

    semantic.categoryValueRef = categoryValue;

  }

  function setGroupName(element, values) {
    var bo = getBusinessObject(element),
        categoryValueRef = bo.categoryValueRef;

    if (!categoryValueRef) {
      initializeCategory(bo);
    }

    // needs direct call to update categoryValue properly
    return {
      cmd: 'element.updateLabel',
      context: {
        element: element,
        newLabel: values.categoryValue
      }
    };
  }

  function getGroupName(element) {
    var bo = getBusinessObject(element),
        value = (bo.categoryValueRef || {}).value;

    return { categoryValue: value };
  }

  if (!is(element, 'bpmn:Collaboration')) {
    var nameOptions = {
      id: options.id,
      label: options.label && translate(options.label)
    };

    if (is(element, 'bpmn:TextAnnotation')) {
      nameOptions.modelProperty = 'text';
      nameOptions.label = translate('Text');
    } else if (is(element, 'bpmn:Group')) {
      nameOptions.modelProperty = 'categoryValue';
      nameOptions.label = translate('Category Value');
      nameOptions.get = getGroupName;
      nameOptions.set = setGroupName;
    }

    // name
    group.entries = group.entries.concat(nameEntryFactory(element, nameOptions, translate));
  }
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/ProcessProps.js":
/*!***************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/ProcessProps.js ***!
  \***************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    participantHelper = __webpack_require__(/*! ../../../helper/ParticipantHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ParticipantHelper.js"),
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    nameEntryFactory = __webpack_require__(/*! ./implementation/Name */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/Name.js"),
    utils = __webpack_require__(/*! ../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js");

module.exports = function(group, element, translate, options) {
  var businessObject = getBusinessObject(element);

  var processIdDescription = options && options.processIdDescription;

  if (is(element, 'bpmn:Process') || (is(element, 'bpmn:Participant') && businessObject.get('processRef'))) {

    /**
     * processId
     */
    if (is(element, 'bpmn:Participant')) {
      var idEntry = entryFactory.validationAwareTextField(translate, {
        id: 'process-id',
        label: translate('Process Id'),
        description: processIdDescription && translate(processIdDescription),
        modelProperty: 'processId'
      });

      // in participants we have to change the default behavior of set and get
      idEntry.get = function(element) {
        var properties = participantHelper.getProcessBusinessObject(element, 'id');
        return { processId: properties.id };
      };

      idEntry.set = function(element, values) {
        return participantHelper.modifyProcessBusinessObject(element, 'id', { id: values.processId });
      };

      idEntry.validate = function(element, values) {
        var idValue = values.processId;

        var bo = getBusinessObject(element);

        var processIdError = utils.isIdValid(bo.processRef, idValue, translate);

        return processIdError ? { processId: processIdError } : {};
      };

      group.entries.push(idEntry);


      /**
       * process name
       */
      var processNameEntry = nameEntryFactory(element, {
        id: 'process-name',
        label: translate('Process Name')
      })[0];

      // in participants we have to change the default behavior of set and get
      processNameEntry.get = function(element) {
        return participantHelper.getProcessBusinessObject(element, 'name');
      };

      processNameEntry.set = function(element, values) {
        return participantHelper.modifyProcessBusinessObject(element, 'name', values);
      };

      group.entries.push(processNameEntry);
    }
  }
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/CompensateEventDefinition.js":
/*!*******************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/CompensateEventDefinition.js ***!
  \*******************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    eventDefinitionHelper = __webpack_require__(/*! ../../../../helper/EventDefinitionHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/EventDefinitionHelper.js"),
    utils = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js");

var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is;

var forEach = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js"),
    find = __webpack_require__(/*! lodash/find */ "./node_modules/lodash/find.js"),
    filter = __webpack_require__(/*! lodash/filter */ "./node_modules/lodash/filter.js");


function getContainedActivities(element) {
  return getFlowElements(element, 'bpmn:Activity');
}

function getContainedBoundaryEvents(element) {
  return getFlowElements(element, 'bpmn:BoundaryEvent');
}

function getFlowElements(element, type) {
  return utils.filterElementsByType(element.flowElements, type);
}

function isCompensationEventAttachedToActivity(activity, boundaryEvents) {
  var activityId = activity.id;
  var boundaryEvent = find(boundaryEvents, function(boundaryEvent) {
    var compensateEventDefinition = eventDefinitionHelper.getCompensateEventDefinition(boundaryEvent);
    var attachedToRef = boundaryEvent.attachedToRef;
    return compensateEventDefinition && attachedToRef && attachedToRef.id === activityId;
  });
  return !!boundaryEvent;
}

// subprocess: only when it is not triggeredByEvent
// activity: only when it attach a compensation boundary event
// callActivity: no limitation
function canActivityBeCompensated(activity, boundaryEvents) {
  return (is(activity, 'bpmn:SubProcess') && !activity.triggeredByEvent) ||
          is(activity, 'bpmn:CallActivity') ||
          isCompensationEventAttachedToActivity(activity, boundaryEvents);
}

function getActivitiesForCompensation(element) {
  var boundaryEvents = getContainedBoundaryEvents(element);
  return filter(getContainedActivities(element), function(activity) {
    return canActivityBeCompensated(activity, boundaryEvents);
  });
}

function getActivitiesForActivityRef(element) {
  var bo = getBusinessObject(element);
  var parent = bo.$parent;

  var activitiesForActivityRef = getActivitiesForCompensation(parent);

  // if throwing compensation event is in an event sub process:
  // get also all activities outside of the event sub process
  if (is(parent, 'bpmn:SubProcess') && parent.triggeredByEvent) {
    parent = parent.$parent;
    if (parent) {
      activitiesForActivityRef = activitiesForActivityRef.concat(getActivitiesForCompensation(parent));
    }

  }

  return activitiesForActivityRef;
}

function createActivityRefOptions(element) {
  var options = [ { value: '' } ];

  var activities = getActivitiesForActivityRef(element);
  forEach(activities, function(activity) {
    var activityId = activity.id;
    var name = (activity.name ? (activity.name + ' ') : '') + '(id=' + activityId + ')';
    options.push({ value: activityId, name: name });
  });

  return options;
}


module.exports = function(group, element, bpmnFactory, compensateEventDefinition, elementRegistry, translate) {

  group.entries.push(entryFactory.checkbox(translate, {
    id: 'wait-for-completion',
    label: translate('Wait for Completion'),
    modelProperty: 'waitForCompletion',

    get: function(element, node) {
      return {
        waitForCompletion: compensateEventDefinition.waitForCompletion
      };
    },

    set: function(element, values) {
      values.waitForCompletion = values.waitForCompletion || false;
      return cmdHelper.updateBusinessObject(element, compensateEventDefinition, values);
    }
  }));

  group.entries.push(entryFactory.selectBox(translate, {
    id: 'activity-ref',
    label: translate('Activity Ref'),
    selectOptions: createActivityRefOptions(element),
    modelProperty: 'activityRef',

    get: function(element, node) {
      var activityRef = compensateEventDefinition.activityRef;
      activityRef = activityRef && activityRef.id;
      return {
        activityRef: activityRef || ''
      };
    },

    set: function(element, values) {
      var activityRef = values.activityRef || undefined;
      activityRef = activityRef && getBusinessObject(elementRegistry.get(activityRef));
      return cmdHelper.updateBusinessObject(element, compensateEventDefinition, {
        activityRef: activityRef
      });
    }
  }));

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ConditionalEventDefinition.js":
/*!********************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ConditionalEventDefinition.js ***!
  \********************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    isEventSubProcess = __webpack_require__(/*! bpmn-js/lib/util/DiUtil */ "./node_modules/bpmn-js/lib/util/DiUtil.js").isEventSubProcess;

module.exports = function(group, element, bpmnFactory, conditionalEventDefinition, elementRegistry, translate) {

  var getValue = function(modelProperty) {
    return function(element) {
      var modelPropertyValue = conditionalEventDefinition.get('camunda:' + modelProperty);
      var value = {};

      value[modelProperty] = modelPropertyValue;
      return value;
    };
  };

  var setValue = function(modelProperty) {
    return function(element, values) {
      var props = {};

      props['camunda:' + modelProperty] = values[modelProperty] || undefined;

      return cmdHelper.updateBusinessObject(element, conditionalEventDefinition, props);
    };
  };

  group.entries.push(entryFactory.textField(translate, {
    id: 'variableName',
    label: translate('Variable Name'),
    modelProperty : 'variableName',

    get: getValue('variableName'),
    set: setValue('variableName')
  }));

  var isConditionalStartEvent =
    is(element, 'bpmn:StartEvent') && !isEventSubProcess(element.parent);

  if (!isConditionalStartEvent) {
    group.entries.push(entryFactory.textField(translate, {
      id: 'variableEvents',
      label: translate('Variable Events'),
      description: translate('Specify more than one variable change event as a comma separated list.'),
      modelProperty : 'variableEvents',

      get: getValue('variableEvents'),
      set: setValue('variableEvents')
    }));
  }
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ElementReferenceProperty.js":
/*!******************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ElementReferenceProperty.js ***!
  \******************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

/**
 * Create an entry to modify a property of an element which
 * is referenced by a event definition.
 *
 * @param  {djs.model.Base} element
 * @param  {ModdleElement} definition
 * @param  {BpmnFactory} bpmnFactory
 * @param  {Object} options
 * @param  {string} options.id the id of the entry
 * @param  {string} options.label the label of the entry
 * @param  {string} options.referenceProperty the name of referencing property
 * @param  {string} options.modelProperty the name of property to modify
 * @param  {string} options.shouldValidate a flag indicate whether to validate or not
 *
 * @return {Array<Object>} return an array containing the entries
 */
module.exports = function(element, definition, bpmnFactory, translate, options) {

  var id = options.id || 'element-property';
  var label = options.label;
  var referenceProperty = options.referenceProperty;
  var modelProperty = options.modelProperty || 'name';
  var shouldValidate = options.shouldValidate || false;
  var description = options.description;
  var canBeHidden = !!options.hidden && typeof options.hidden === 'function';

  var entry = entryFactory.textField(translate, {
    id: id,
    label: label,
    modelProperty: modelProperty,
    description: description,

    get: function(element, node) {
      var reference = definition.get(referenceProperty);
      var props = {};
      props[modelProperty] = reference && reference.get(modelProperty);
      return props;
    },

    set: function(element, values, node) {
      var reference = definition.get(referenceProperty);
      var props = {};
      props[modelProperty] = values[modelProperty] || undefined;
      return cmdHelper.updateBusinessObject(element, reference, props);
    },

    hidden: function(element, node) {
      if (canBeHidden) {
        return options.hidden.apply(definition, arguments) || !definition.get(referenceProperty);
      }
      return !definition.get(referenceProperty);
    }
  });

  if (shouldValidate) {
    entry.validate = function(element, values, node) {
      var reference = definition.get(referenceProperty);
      if (reference && !values[modelProperty]) {
        var validationErrors = {};
        validationErrors[modelProperty] = 'Must provide a value';
        return validationErrors;
      }
    };
  }

  return [ entry ];
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ErrorEventDefinition.js":
/*!**************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ErrorEventDefinition.js ***!
  \**************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var eventDefinitionReference = __webpack_require__(/*! ./EventDefinitionReference */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/EventDefinitionReference.js"),
    elementReferenceProperty = __webpack_require__(/*! ./ElementReferenceProperty */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ElementReferenceProperty.js");


module.exports = function(group, element, bpmnFactory, errorEventDefinition, translate) {


  group.entries = group.entries.concat(eventDefinitionReference(element, errorEventDefinition, bpmnFactory, {
    label: translate('Global Error referenced'),
    elementName: 'error',
    elementType: 'bpmn:Error',
    referenceProperty: 'errorRef',
    newElementIdPrefix: 'Error_'
  }));


  group.entries = group.entries.concat(
    elementReferenceProperty(element, errorEventDefinition, bpmnFactory, translate, {
      id: 'error-element-name',
      label: translate('Name'),
      referenceProperty: 'errorRef',
      modelProperty: 'name',
      shouldValidate: true
    })
  );


  group.entries = group.entries.concat(
    elementReferenceProperty(element, errorEventDefinition, bpmnFactory, translate, {
      id: 'error-element-code',
      label: translate('Code'),
      referenceProperty: 'errorRef',
      modelProperty: 'errorCode'
    })
  );

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/EscalationEventDefinition.js":
/*!*******************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/EscalationEventDefinition.js ***!
  \*******************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    utils = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js");

var eventDefinitionReference = __webpack_require__(/*! ./EventDefinitionReference */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/EventDefinitionReference.js"),
    elementReferenceProperty = __webpack_require__(/*! ./ElementReferenceProperty */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ElementReferenceProperty.js");


module.exports = function(group, element, bpmnFactory, escalationEventDefinition, showEscalationCodeVariable, translate) {

  group.entries = group.entries.concat(eventDefinitionReference(element, escalationEventDefinition, bpmnFactory, {
    label: translate('Global Escalation referenced'),
    elementName: 'escalation',
    elementType: 'bpmn:Escalation',
    referenceProperty: 'escalationRef',
    newElementIdPrefix: 'Escalation_'
  }));


  group.entries = group.entries.concat(
    elementReferenceProperty(element, escalationEventDefinition, bpmnFactory, translate, {
      id: 'escalation-element-name',
      label: translate('Global Escalation Name'),
      referenceProperty: 'escalationRef',
      modelProperty: 'name',
      shouldValidate: true
    })
  );


  group.entries = group.entries.concat(
    elementReferenceProperty(element, escalationEventDefinition, bpmnFactory, translate, {
      id: 'escalation-element-code',
      label: translate('Global Escalation Code'),
      referenceProperty: 'escalationRef',
      modelProperty: 'escalationCode'
    })
  );


  if (showEscalationCodeVariable) {
    group.entries.push(entryFactory.validationAwareTextField(translate, {
      id : 'escalationCodeVariable',
      label : translate('Escalation Code Variable'),
      modelProperty : 'escalationCodeVariable',
      description: translate('Define the name of the variable that will contain the escalation code'),

      getProperty: function(element) {
        var codeVariable = escalationEventDefinition.get('camunda:escalationCodeVariable');

        return codeVariable;
      },

      setProperty: function(element, values) {
        if (values.escalationCodeVariable === '')
          values.escalationCodeVariable = undefined;

        return cmdHelper.updateBusinessObject(element, escalationEventDefinition, values);
      },

      validate: function(element, values) {
        var validation = {},
            targetValue = values.escalationCodeVariable;

        if (utils.containsSpace(targetValue)) {
          validation.escalationCodeVariable = translate('Escalation code variable must not contain spaces.');
        }

        return validation;
      }
    }));
  }
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/EventDefinitionReference.js":
/*!******************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/EventDefinitionReference.js ***!
  \******************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query,
    domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify,
    domAttr = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").attr;

var forEach = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js"),
    find = __webpack_require__(/*! lodash/find */ "./node_modules/lodash/find.js");

var elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js");
var utils = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js"),
    escapeHTML = utils.escapeHTML;

var selector = 'select[name=selectedElement]';

/**
 * Get select box containing all elements.
 *
 * @param {DOMElement} node
 *
 * @return {DOMElement} the select box
 */
function getSelectBox(node) {
  return domQuery(selector, node);
}

/**
 * Find element by given id.
 *
 * @param {ModdleElement} eventDefinition
 *
 * @return {ModdleElement} an element
 */
function findElementById(eventDefinition, type, id) {
  var elements = utils.findRootElementsByType(eventDefinition, type);
  return find(elements, function(element) {
    return element.id === id;
  });
}

/**
 * Create an entry to modify the reference to an element from an
 * event definition.
 *
 * @param  {djs.model.Base} element
 * @param  {ModdleElement} definition
 * @param  {BpmnFactory} bpmnFactory
 * @param  {Object} options
 * @param  {string} options.label the label of the entry
 * @param  {string} options.description the description of the entry
 * @param  {string} options.elementName the name of the element
 * @param  {string} options.elementType the type of the element
 * @param  {string} options.referenceProperty the name of referencing property
 * @param  {string} options.newElementIdPrefix the prefix of a new created element
 *
 * @return {Array<Object>} return an array containing the entries
 */
module.exports = function(element, definition, bpmnFactory, options) {

  var elementName = options.elementName || '',
      elementType = options.elementType,
      referenceProperty = options.referenceProperty;

  var newElementIdPrefix = options.newElementIdPrefix || 'elem_';

  var label = options.label || '',
      description = options.description || '';

  var entries = [];

  var canBeHidden = !!options.hidden && typeof options.hidden === 'function';

  entries.push({

    id: options.id || 'event-definitions-' + elementName,
    description: description,
    isShown: function() {
      if (canBeHidden) {
        return !options.hidden.apply(definition, arguments);
      }
      return !options.hidden;
    },
    html: '<div class="bpp-row bpp-select" data-show="isShown">' +
             '<label for="camunda-' + escapeHTML(elementName) + '">' + escapeHTML(label) + '</label>' +
             '<div class="bpp-field-wrapper">' +
               '<select id="camunda-' + escapeHTML(elementName) + '" name="selectedElement" data-value>' +
               '</select>' +
               '<button class="action-button add" id="addElement" data-action="addElement"><span>+</span></button>' +
             '</div>' +
          '</div>',

    get: function(element, entryNode) {
      utils.updateOptionsDropDown(selector, definition, elementType, entryNode);
      var reference = definition.get(referenceProperty);
      return {
        selectedElement: (reference && reference.id) || ''
      };
    },

    set: function(element, values) {
      var selection = values.selectedElement;

      var props = {};

      if (!selection || typeof selection === 'undefined') {

        // remove reference to element
        props[referenceProperty] = undefined;
        return cmdHelper.updateBusinessObject(element, definition, props);
      }

      var commands = [];

      var selectedElement = findElementById(definition, elementType, selection);
      if (!selectedElement) {
        var root = utils.getRoot(definition);

        // create a new element
        selectedElement = elementHelper.createElement(elementType, { name: selection }, root, bpmnFactory);
        commands.push(cmdHelper.addAndRemoveElementsFromList(element, root, 'rootElements', null, [ selectedElement ]));
      }

      // update reference to element
      props[referenceProperty] = selectedElement;
      commands.push(cmdHelper.updateBusinessObject(element, definition, props));

      return commands;
    },

    addElement: function(element, inputNode) {

      // note: this generated id will be used as name
      // of the element and not as id
      var id = utils.nextId(newElementIdPrefix);

      var optionTemplate = domify('<option value="' + escapeHTML(id) + '"> (id='+escapeHTML(id)+')' + '</option>');

      // add new option
      var selectBox = getSelectBox(inputNode);
      selectBox.insertBefore(optionTemplate, selectBox.firstChild);

      // select new element in the select box
      forEach(selectBox, function(option) {
        if (option.value === id) {
          domAttr(option, 'selected', 'selected');
        } else {
          domAttr(option, 'selected', null);
        }
      });

      return true;
    }

  });

  return entries;

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/MessageEventDefinition.js":
/*!****************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/MessageEventDefinition.js ***!
  \****************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var eventDefinitionReference = __webpack_require__(/*! ./EventDefinitionReference */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/EventDefinitionReference.js"),
    elementReferenceProperty = __webpack_require__(/*! ./ElementReferenceProperty */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ElementReferenceProperty.js");


module.exports = function(group, element, bpmnFactory, messageEventDefinition, translate) {

  group.entries = group.entries.concat(eventDefinitionReference(element, messageEventDefinition, bpmnFactory, {
    label: translate('Global Message referenced'),
    elementName: 'message',
    elementType: 'bpmn:Message',
    referenceProperty: 'messageRef',
    newElementIdPrefix: 'Message_'
  }));


  group.entries = group.entries.concat(
    elementReferenceProperty(element, messageEventDefinition, bpmnFactory, translate, {
      id: 'message-element-name',
      label: translate('Global Message Name'),
      referenceProperty: 'messageRef',
      modelProperty: 'name',
      shouldValidate: true
    })
  );

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/Name.js":
/*!**********************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/Name.js ***!
  \**********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

/**
 * Create an entry to modify the name of an an element.
 *
 * @param  {djs.model.Base} element
 * @param  {Object} options
 * @param  {string} options.id the id of the entry
 * @param  {string} options.label the label of the entry
 *
 * @return {Array<Object>} return an array containing
 *                         the entry to modify the name
 */
module.exports = function(element, options, translate) {

  options = options || {};
  var id = options.id || 'name',
      label = options.label || translate('Name'),
      modelProperty = options.modelProperty || 'name';

  var nameEntry = entryFactory.textBox(translate, {
    id: id,
    label: label,
    modelProperty: modelProperty,
    get: options.get,
    set: options.set
  });

  return [ nameEntry ];

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/SignalEventDefinition.js":
/*!***************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/SignalEventDefinition.js ***!
  \***************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var eventDefinitionReference = __webpack_require__(/*! ./EventDefinitionReference */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/EventDefinitionReference.js"),
    elementReferenceProperty = __webpack_require__(/*! ./ElementReferenceProperty */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ElementReferenceProperty.js");


module.exports = function(group, element, bpmnFactory, signalEventDefinition, translate) {

  group.entries = group.entries.concat(eventDefinitionReference(element, signalEventDefinition, bpmnFactory, {
    label: translate('Global Signal referenced'),
    elementName: 'signal',
    elementType: 'bpmn:Signal',
    referenceProperty: 'signalRef',
    newElementIdPrefix: 'Signal_'
  }));


  group.entries = group.entries.concat(
    elementReferenceProperty(element, signalEventDefinition, bpmnFactory, translate, {
      id: 'signal-element-name',
      label: translate('Global Signal Name'),
      referenceProperty: 'signalRef',
      modelProperty: 'name',
      shouldValidate: true
    })
  );

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/TimerEventDefinition.js":
/*!**************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/TimerEventDefinition.js ***!
  \**************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

/**
 * Get the timer definition type for a given timer event definition.
 *
 * @param {ModdleElement<bpmn:TimerEventDefinition>} timer
 *
 * @return {string|undefined} the timer definition type
 */
function getTimerDefinitionType(timer) {

  if (!timer) {
    return;
  }

  var timeDate = timer.get('timeDate');
  if (typeof timeDate !== 'undefined') {
    return 'timeDate';
  }

  var timeCycle = timer.get('timeCycle');
  if (typeof timeCycle !== 'undefined') {
    return 'timeCycle';
  }

  var timeDuration = timer.get('timeDuration');
  if (typeof timeDuration !== 'undefined') {
    return 'timeDuration';
  }
}

/**
 * Get the actual timer event definition based on option, whether it's a getter
 * to fetch the timer event definition or the exact event definition itself
 *
 * @param {ModdleElement<bpmn:TimerEventDefinition>|Function} timerOrFunction
 * @param {Shape} element
 * @param {HTMLElement} node
 *
 * @return ModdleElement<bpmn:TimerEventDefinition>
 */
function getTimerDefinition(timerOrFunction, element, node) {
  if (typeof timerOrFunction === 'function') {
    return timerOrFunction(element, node);
  }

  return timerOrFunction;
}

/**
 * Creates 'bpmn:FormalExpression' element.
 *
 * @param {ModdleElement} parent
 * @param {string} body
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {ModdleElement<bpmn:FormalExpression>} a formal expression
 */
function createFormalExpression(parent, body, bpmnFactory) {
  body = body || undefined;
  return elementHelper.createElement('bpmn:FormalExpression', { body: body }, parent, bpmnFactory);
}

function TimerEventDefinition(group, element, bpmnFactory, timerEventDefinition, translate, options) {

  var selectOptions = [
    { value: 'timeDate', name: translate('Date') },
    { value: 'timeDuration', name: translate('Duration') },
    { value: 'timeCycle', name: translate('Cycle') }
  ];

  var prefix = options && options.idPrefix,
      createTimerEventDefinition = options && options.createTimerEventDefinition;


  group.entries.push(entryFactory.selectBox(translate, {
    id: prefix + 'timer-event-definition-type',
    label: translate('Timer Definition Type'),
    selectOptions: selectOptions,
    emptyParameter: true,
    modelProperty: 'timerDefinitionType',

    get: function(element, node) {
      var timerDefinition = getTimerDefinition(timerEventDefinition, element, node);

      return {
        timerDefinitionType: getTimerDefinitionType(timerDefinition) || ''
      };
    },

    set: function(element, values, node) {
      var props = {
        timeDuration: undefined,
        timeDate: undefined,
        timeCycle: undefined
      };


      var timerDefinition = getTimerDefinition(timerEventDefinition, element, node),
          newType = values.timerDefinitionType;

      if (!timerDefinition && typeof createTimerEventDefinition === 'function') {
        timerDefinition = createTimerEventDefinition(element, node);
      }

      if (values.timerDefinitionType) {
        var oldType = getTimerDefinitionType(timerDefinition);

        var value;
        if (oldType) {
          var definition = timerDefinition.get(oldType);
          value = definition.get('body');
        }

        props[newType] = createFormalExpression(timerDefinition, value, bpmnFactory);
      }

      return cmdHelper.updateBusinessObject(element, timerDefinition, props);
    },

    hidden: function(element, node) {
      return getTimerDefinition(timerEventDefinition, element, node) === undefined;
    }

  }));


  group.entries.push(entryFactory.textField(translate, {
    id: prefix + 'timer-event-definition',
    label: translate('Timer Definition'),
    modelProperty: 'timerDefinition',

    get: function(element, node) {
      var timerDefinition = getTimerDefinition(timerEventDefinition, element, node),
          type = getTimerDefinitionType(timerDefinition),
          definition = type && timerDefinition.get(type),
          value = definition && definition.get('body');

      return {
        timerDefinition: value
      };
    },

    set: function(element, values, node) {
      var timerDefinition = getTimerDefinition(timerEventDefinition, element, node),
          type = getTimerDefinitionType(timerDefinition),
          definition = type && timerDefinition.get(type);

      if (definition) {
        return cmdHelper.updateBusinessObject(element, definition, {
          body: values.timerDefinition || undefined
        });
      }
    },

    validate: function(element, node) {
      var timerDefinition = getTimerDefinition(timerEventDefinition, element, node),
          type = getTimerDefinitionType(timerDefinition),
          definition = type && timerDefinition.get(type);

      if (definition) {
        var value = definition.get('body');
        if (!value) {
          return {
            timerDefinition: translate('Must provide a value')
          };
        }
      }
    },

    hidden: function(element, node) {
      var timerDefinition = getTimerDefinition(timerEventDefinition, element, node);

      return !getTimerDefinitionType(timerDefinition);
    }

  }));

}

module.exports = TimerEventDefinition;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/CamundaPropertiesProvider.js":
/*!*************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/CamundaPropertiesProvider.js ***!
  \*************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var inherits = __webpack_require__(/*! inherits */ "./node_modules/inherits/inherits_browser.js");

var PropertiesActivator = __webpack_require__(/*! ../../PropertiesActivator */ "./node_modules/bpmn-js-properties-panel/lib/PropertiesActivator.js");

var asyncCapableHelper = __webpack_require__(/*! ../../helper/AsyncCapableHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/AsyncCapableHelper.js"),
    ImplementationTypeHelper = __webpack_require__(/*! ../../helper/ImplementationTypeHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ImplementationTypeHelper.js");

var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is;

// bpmn properties
var processProps = __webpack_require__(/*! ../bpmn/parts/ProcessProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/ProcessProps.js"),
    eventProps = __webpack_require__(/*! ../bpmn/parts/EventProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/EventProps.js"),
    linkProps = __webpack_require__(/*! ../bpmn/parts/LinkProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/LinkProps.js"),
    documentationProps = __webpack_require__(/*! ../bpmn/parts/DocumentationProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/DocumentationProps.js"),
    idProps = __webpack_require__(/*! ../bpmn/parts/IdProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/IdProps.js"),
    nameProps = __webpack_require__(/*! ../bpmn/parts/NameProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/NameProps.js"),
    executableProps = __webpack_require__(/*! ../bpmn/parts/ExecutableProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/ExecutableProps.js");

// camunda properties
var serviceTaskDelegateProps = __webpack_require__(/*! ./parts/ServiceTaskDelegateProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ServiceTaskDelegateProps.js"),
    userTaskProps = __webpack_require__(/*! ./parts/UserTaskProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/UserTaskProps.js"),
    asynchronousContinuationProps = __webpack_require__(/*! ./parts/AsynchronousContinuationProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/AsynchronousContinuationProps.js"),
    callActivityProps = __webpack_require__(/*! ./parts/CallActivityProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/CallActivityProps.js"),
    multiInstanceProps = __webpack_require__(/*! ./parts/MultiInstanceLoopProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/MultiInstanceLoopProps.js"),
    conditionalProps = __webpack_require__(/*! ./parts/ConditionalProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ConditionalProps.js"),
    scriptProps = __webpack_require__(/*! ./parts/ScriptTaskProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ScriptTaskProps.js"),
    errorProps = __webpack_require__(/*! ./parts/ErrorEventProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ErrorEventProps.js"),
    formProps = __webpack_require__(/*! ./parts/FormProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/FormProps.js"),
    startEventInitiator = __webpack_require__(/*! ./parts/StartEventInitiator */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/StartEventInitiator.js"),
    variableMapping = __webpack_require__(/*! ./parts/VariableMappingProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/VariableMappingProps.js"),
    versionTag = __webpack_require__(/*! ./parts/VersionTagProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/VersionTagProps.js"),
    processVariablesProps = __webpack_require__(/*! ./parts/ProcessVariablesProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ProcessVariablesProps.js");

var listenerProps = __webpack_require__(/*! ./parts/ListenerProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ListenerProps.js"),
    listenerDetails = __webpack_require__(/*! ./parts/ListenerDetailProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ListenerDetailProps.js"),
    listenerFields = __webpack_require__(/*! ./parts/ListenerFieldInjectionProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ListenerFieldInjectionProps.js");

// element template properties
var elementTemplateDescriptionProps = __webpack_require__(/*! ./element-templates/parts/DescriptionProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/DescriptionProps.js"),
    elementTemplateChooserProps = __webpack_require__(/*! ./element-templates/parts/ChooserProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/ChooserProps.js"),
    elementTemplateCustomProps = __webpack_require__(/*! ./element-templates/parts/CustomProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/CustomProps.js"),
    elementTemplateInputParametersProps = __webpack_require__(/*! ./element-templates/parts/InputParametersProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/InputParametersProps.js"),
    elementTemplateOutputParametersProps = __webpack_require__(/*! ./element-templates/parts/OutputParametersProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/OutputParametersProps.js"),
    elementTemplateErrorsProps = __webpack_require__(/*! ./element-templates/parts/ErrorsProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/ErrorsProps.js"),
    getTemplateId = __webpack_require__(/*! ./element-templates/Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").getTemplateId;

// Input/Output
var inputParameters = __webpack_require__(/*! ./parts/InputParametersProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/InputParametersProps.js"),
    outputParameters = __webpack_require__(/*! ./parts/OutputParametersProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/OutputParametersProps.js"),
    errorsProps = __webpack_require__(/*! ./parts/ErrorsProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ErrorsProps.js");

// Connector
var connectorDetails = __webpack_require__(/*! ./parts/ConnectorDetailProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ConnectorDetailProps.js"),
    connectorInputParameters = __webpack_require__(/*! ./parts/ConnectorInputParametersProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ConnectorInputParametersProps.js"),
    connectorOutputParameters = __webpack_require__(/*! ./parts/ConnectorOutputParametersProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ConnectorOutputParametersProps.js");

// properties
var properties = __webpack_require__(/*! ./parts/PropertiesProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/PropertiesProps.js");

// job configuration
var jobConfiguration = __webpack_require__(/*! ./parts/JobConfigurationProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/JobConfigurationProps.js");

// history time to live
var historyTimeToLive = __webpack_require__(/*! ./parts/HistoryTimeToLiveProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/HistoryTimeToLiveProps.js");

// candidate starter groups/users
var candidateStarter = __webpack_require__(/*! ./parts/CandidateStarterProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/CandidateStarterProps.js");

// tasklist
var tasklist = __webpack_require__(/*! ./parts/TasklistProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/TasklistProps.js");

// external task configuration
var externalTaskConfiguration = __webpack_require__(/*! ./parts/ExternalTaskConfigurationProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ExternalTaskConfigurationProps.js");

// field injection
var fieldInjections = __webpack_require__(/*! ./parts/FieldInjectionProps */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/FieldInjectionProps.js");

var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    eventDefinitionHelper = __webpack_require__(/*! ../../helper/EventDefinitionHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/EventDefinitionHelper.js"),
    implementationTypeHelper = __webpack_require__(/*! ../../helper/ImplementationTypeHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ImplementationTypeHelper.js");

// helpers ////////////////////////////////////////

var isExternalTaskPriorityEnabled = function(element) {
  var businessObject = getBusinessObject(element);

  // show only if element is a process, a participant ...
  if (is(element, 'bpmn:Process') || is(element, 'bpmn:Participant') && businessObject.get('processRef')) {
    return true;
  }

  var externalBo = ImplementationTypeHelper.getServiceTaskLikeBusinessObject(element),
      isExternalTask = ImplementationTypeHelper.getImplementationType(externalBo) === 'external';

  // ... or an external task with selected external implementation type
  return !!ImplementationTypeHelper.isExternalCapable(externalBo) && isExternalTask;
};

var isJobConfigEnabled = function(element) {
  var businessObject = getBusinessObject(element);

  if (is(element, 'bpmn:Process') || is(element, 'bpmn:Participant') && businessObject.get('processRef')) {
    return true;
  }

  // async behavior
  var bo = getBusinessObject(element);
  if (asyncCapableHelper.isAsyncBefore(bo) || asyncCapableHelper.isAsyncAfter(bo)) {
    return true;
  }

  // timer definition
  if (is(element, 'bpmn:Event')) {
    return !!eventDefinitionHelper.getTimerEventDefinition(element);
  }

  return false;
};

var getListenerLabel = function(param, translate) {

  if (is(param, 'camunda:ExecutionListener')) {
    return translate('Execution Listener');
  }

  if (is(param, 'camunda:TaskListener')) {
    return translate('Task Listener');
  }

  return '';
};

var PROCESS_KEY_HINT = 'This maps to the process definition key.';
var TASK_KEY_HINT = 'This maps to the task definition key.';

function getIdOptions(element) {

  if (is(element, 'bpmn:Participant')) {
    return { id: 'participant-id', label: 'Participant Id' };
  }

  if (is(element, 'bpmn:Process')) {
    return { description: PROCESS_KEY_HINT };
  }

  if (is(element, 'bpmn:UserTask')) {
    return { description: TASK_KEY_HINT };
  }
}

function getNameOptions(element) {
  if (is(element, 'bpmn:Participant')) {
    return { id: 'participant-name', label: 'Participant Name' };
  }
}

function getProcessOptions(element) {
  if (is(element, 'bpmn:Participant')) {
    return { processIdDescription: PROCESS_KEY_HINT };
  }
}

function createElementTemplateGroups(
    element,
    bpmnFactory,
    canvas,
    commandStack,
    elementTemplates,
    modeling,
    replace,
    selection,
    translate) {
  var templateId = getTemplateId(element);

  if (!templateId) {
    return [];
  }

  var descriptionGroup = elementTemplateDescriptionProps(
    element, commandStack, elementTemplates, modeling, replace, selection, translate);

  var idOptions = getIdOptions(element) || {};

  idOptions.id = 'element-template-element-id';

  var nameOptions = { id: 'element-template-element-name' };

  idProps(descriptionGroup, element, translate, idOptions);
  nameProps(descriptionGroup, element, bpmnFactory, canvas, translate, nameOptions);
  processProps(descriptionGroup, element, translate, getProcessOptions(element));

  var elementTemplateInputParametersGroup = {
    id: 'template-inputs',
    label: translate('Input Parameters'),
    entries: []
  };
  elementTemplateInputParametersProps(elementTemplateInputParametersGroup, element, elementTemplates, bpmnFactory, translate);

  var elementTemplateOutputParametersGroup = {
    id: 'template-outputs',
    label: translate('Output Parameters'),
    entries: []
  };
  elementTemplateOutputParametersProps(elementTemplateOutputParametersGroup, element, elementTemplates, bpmnFactory, translate);


  var elementTemplateErrorsGroup = {
    id: 'template-errors',
    label: translate('Errors'),
    entries: []
  };
  elementTemplateErrorsProps(elementTemplateErrorsGroup, element, elementTemplates, bpmnFactory, translate);

  var customFieldsGroups = elementTemplateCustomProps(element, elementTemplates, bpmnFactory, translate);

  return [
    descriptionGroup,
    elementTemplateInputParametersGroup,
    elementTemplateOutputParametersGroup,
    elementTemplateErrorsGroup
  ].concat(customFieldsGroups);
}

function createGeneralTabGroups(
    element, canvas, bpmnFactory,
    elementRegistry, elementTemplates, translate) {

  // refer to target element for external labels
  element = element.labelTarget || element;

  var generalGroup = {
    id: 'general',
    label: translate('General'),
    entries: []
  };

  idProps(generalGroup, element, translate, getIdOptions(element));
  nameProps(generalGroup, element, bpmnFactory, canvas, translate, getNameOptions(element));
  processProps(generalGroup, element, translate, getProcessOptions(element));
  versionTag(generalGroup, element, translate);
  executableProps(generalGroup, element, translate);
  elementTemplateChooserProps(generalGroup, element, elementTemplates, translate);

  var detailsGroup = {
    id: 'details',
    label: translate('Details'),
    entries: []
  };
  serviceTaskDelegateProps(detailsGroup, element, bpmnFactory, translate);
  userTaskProps(detailsGroup, element, translate);
  scriptProps(detailsGroup, element, bpmnFactory, translate);
  linkProps(detailsGroup, element, translate);
  callActivityProps(detailsGroup, element, bpmnFactory, translate);
  eventProps(detailsGroup, element, bpmnFactory, elementRegistry, translate);
  errorProps(detailsGroup, element, bpmnFactory, translate);
  conditionalProps(detailsGroup, element, bpmnFactory, translate);
  startEventInitiator(detailsGroup, element, translate); // this must be the last element of the details group!

  var multiInstanceGroup = {
    id: 'multiInstance',
    label: translate('Multi Instance'),
    entries: []
  };
  multiInstanceProps(multiInstanceGroup, element, bpmnFactory, translate);

  var asyncGroup = {
    id : 'async',
    label: translate('Asynchronous Continuations'),
    entries : []
  };
  asynchronousContinuationProps(asyncGroup, element, bpmnFactory, translate);

  var jobConfigurationGroup = {
    id : 'jobConfiguration',
    label : translate('Job Configuration'),
    entries : [],
    enabled: isJobConfigEnabled
  };
  jobConfiguration(jobConfigurationGroup, element, bpmnFactory, translate);

  var externalTaskGroup = {
    id : 'externalTaskConfiguration',
    label : translate('External Task Configuration'),
    entries : [],
    enabled: isExternalTaskPriorityEnabled
  };
  externalTaskConfiguration(externalTaskGroup, element, bpmnFactory, translate);


  var candidateStarterGroup = {
    id: 'candidateStarterConfiguration',
    label: translate('Candidate Starter Configuration'),
    entries: []
  };
  candidateStarter(candidateStarterGroup, element, bpmnFactory, translate);

  var historyTimeToLiveGroup = {
    id: 'historyConfiguration',
    label: translate('History Configuration'),
    entries: []
  };
  historyTimeToLive(historyTimeToLiveGroup, element, bpmnFactory, translate);

  var tasklistGroup = {
    id: 'tasklist',
    label: translate('Tasklist Configuration'),
    entries: []
  };
  tasklist(tasklistGroup, element, bpmnFactory, translate);

  var documentationGroup = {
    id: 'documentation',
    label: translate('Documentation'),
    entries: []
  };
  documentationProps(documentationGroup, element, bpmnFactory, translate);

  var groups = [];
  groups.push(generalGroup);
  groups.push(detailsGroup);
  groups.push(externalTaskGroup);
  groups.push(multiInstanceGroup);
  groups.push(asyncGroup);
  groups.push(jobConfigurationGroup);
  groups.push(candidateStarterGroup);
  groups.push(historyTimeToLiveGroup);
  groups.push(tasklistGroup);
  groups.push(documentationGroup);

  return groups;
}

function createVariablesTabGroups(element, bpmnFactory, elementRegistry, translate) {
  var variablesGroup = {
    id : 'variables',
    label : translate('Variables'),
    entries: []
  };
  variableMapping(variablesGroup, element, bpmnFactory, translate);

  return [
    variablesGroup
  ];
}

function createProcessVariablesTabGroups(element, translate) {
  var processVariablesGroup = {
    id : 'process-variables',
    label : translate('Variables'),
    entries: []
  };

  processVariablesProps(processVariablesGroup, element, translate);

  return [
    processVariablesGroup
  ];
}

function createFormsTabGroups(element, bpmnFactory, elementRegistry, translate) {
  var formGroup = {
    id : 'forms',
    label : translate('Forms'),
    entries: []
  };
  formProps(formGroup, element, bpmnFactory, translate);

  return [
    formGroup
  ];
}

function createListenersTabGroups(element, bpmnFactory, elementRegistry, translate) {

  var listenersGroup = {
    id : 'listeners',
    label: translate('Listeners'),
    entries: []
  };

  var options = listenerProps(listenersGroup, element, bpmnFactory, translate);

  var listenerDetailsGroup = {
    id: 'listener-details',
    entries: [],
    enabled: function(element, node) {
      return options.getSelectedListener(element, node);
    },
    label: function(element, node) {
      var param = options.getSelectedListener(element, node);
      return getListenerLabel(param, translate);
    }
  };

  listenerDetails(listenerDetailsGroup, element, bpmnFactory, options, translate);

  var listenerFieldsGroup = {
    id: 'listener-fields',
    label: translate('Field Injection'),
    entries: [],
    enabled: function(element, node) {
      return options.getSelectedListener(element, node);
    }
  };

  listenerFields(listenerFieldsGroup, element, bpmnFactory, options, translate);

  return [
    listenersGroup,
    listenerDetailsGroup,
    listenerFieldsGroup
  ];
}

function createInputOutputTabGroups(element, bpmnFactory, elementTemplates, translate) {

  var inputParametersGroup = {
    id: 'input-parameters',
    label: translate('Input Parameters'),
    entries: []
  };

  inputParameters(inputParametersGroup, element, bpmnFactory, elementTemplates, translate);

  var outputParametersGroup = {
    id: 'output-parameters',
    label: translate('Output Parameters'),
    entries: []
  };

  outputParameters(outputParametersGroup, element, bpmnFactory, elementTemplates, translate);

  var errorsGroup = {
    id: 'errors',
    label: translate('Errors'),
    entries: [],

    enabled: function(element, node) {
      var businessObject = getBusinessObject(element);
      var isExternal = ImplementationTypeHelper.getImplementationType(businessObject) === 'external';

      return is(element, 'bpmn:ServiceTask') && isExternal;
    },
  };

  errorsProps(errorsGroup, element, bpmnFactory, elementTemplates, translate);

  return [
    inputParametersGroup,
    outputParametersGroup,
    errorsGroup
  ];
}

function createConnectorTabGroups(element, bpmnFactory, elementRegistry, translate) {
  var connectorDetailsGroup = {
    id: 'connector-details',
    label: translate('Details'),
    entries: []
  };

  connectorDetails(connectorDetailsGroup, element, bpmnFactory, translate);

  var connectorInputParametersGroup = {
    id: 'connector-input-parameters',
    label: translate('Input Parameters'),
    entries: []
  };

  connectorInputParameters(connectorInputParametersGroup, element, bpmnFactory, translate);

  var connectorOutputParametersGroup = {
    id: 'connector-output-parameters',
    label: translate('Output Parameters'),
    entries: []
  };

  connectorOutputParameters(connectorOutputParametersGroup, element, bpmnFactory, translate);

  return [
    connectorDetailsGroup,
    connectorInputParametersGroup,
    connectorOutputParametersGroup
  ];
}

function createFieldInjectionsTabGroups(element, bpmnFactory, elementRegistry, translate) {

  var fieldGroup = {
    id: 'field-injections-properties',
    label: translate('Field Injections'),
    entries: []
  };

  fieldInjections(fieldGroup, element, bpmnFactory, translate);

  return [
    fieldGroup
  ];
}

function createExtensionElementsGroups(element, bpmnFactory, elementRegistry, translate) {

  var propertiesGroup = {
    id : 'extensionElements-properties',
    label: translate('Properties'),
    entries: []
  };
  properties(propertiesGroup, element, bpmnFactory, translate);

  return [
    propertiesGroup
  ];
}

// Camunda Properties Provider /////////////////////////////////////


/**
 * A properties provider for Camunda related properties.
 *
 * @param {BpmnFactory} bpmnFactory
 * @param {Canvas} canvas
 * @param {ElementRegistry} elementRegistry
 * @param {ElementTemplates} elementTemplates
 * @param {EventBus} eventBus
 * @param {Modeling} modeling
 * @param {Replace} replace
 * @param {Selection} selection
 * @param {Translate} translate
 */
function CamundaPropertiesProvider(
    bpmnFactory,
    canvas,
    commandStack,
    elementRegistry,
    elementTemplates,
    eventBus,
    modeling,
    replace,
    selection,
    translate
) {
  PropertiesActivator.call(this, eventBus);

  this.getTabs = function(element) {

    var generalTab = {
      id: 'general',
      label: translate('General'),
      groups: createGeneralTabGroups(
        element, canvas, bpmnFactory,
        elementRegistry, elementTemplates, translate)
    };

    var elementTemplateTab = {
      id: 'element-template',
      label: translate('Template'),
      groups: createElementTemplateGroups(
        element,
        bpmnFactory,
        canvas,
        commandStack,
        elementTemplates,
        modeling,
        replace,
        selection,
        translate
      )
    };

    var variablesTab = {
      id: 'variables',
      label: translate('Variables'),
      groups: createVariablesTabGroups(element, bpmnFactory, elementRegistry, translate)
    };

    var processVariablesTab = {
      id: 'process-variables',
      label: translate('Variables'),
      groups: createProcessVariablesTabGroups(element, translate)
    };

    var formsTab = {
      id: 'forms',
      label: translate('Forms'),
      groups: createFormsTabGroups(element, bpmnFactory, elementRegistry, translate)
    };

    var listenersTab = {
      id: 'listeners',
      label: translate('Listeners'),
      groups: createListenersTabGroups(element, bpmnFactory, elementRegistry, translate),
      enabled: function(element) {
        return !eventDefinitionHelper.getLinkEventDefinition(element)
          || (!is(element, 'bpmn:IntermediateThrowEvent')
          && eventDefinitionHelper.getLinkEventDefinition(element));
      }
    };

    var inputOutputTab = {
      id: 'input-output',
      label: translate('Input/Output'),
      groups: createInputOutputTabGroups(element, bpmnFactory, elementTemplates, translate)
    };

    var connectorTab = {
      id: 'connector',
      label: translate('Connector'),
      groups: createConnectorTabGroups(element, bpmnFactory, elementRegistry, translate),
      enabled: function(element) {
        var bo = implementationTypeHelper.getServiceTaskLikeBusinessObject(element);
        return bo && implementationTypeHelper.getImplementationType(bo) === 'connector';
      }
    };

    var fieldInjectionsTab = {
      id: 'field-injections',
      label: translate('Field Injections'),
      groups: createFieldInjectionsTabGroups(element, bpmnFactory, elementRegistry, translate)
    };

    var extensionsTab = {
      id: 'extensionElements',
      label: translate('Extensions'),
      groups: createExtensionElementsGroups(element, bpmnFactory, elementRegistry, translate)
    };

    return [
      generalTab,
      elementTemplateTab,
      variablesTab,
      processVariablesTab,
      connectorTab,
      formsTab,
      listenersTab,
      inputOutputTab,
      fieldInjectionsTab,
      extensionsTab
    ];
  };

}

CamundaPropertiesProvider.$inject = [
  'bpmnFactory',
  'canvas',
  'commandStack',
  'elementRegistry',
  'elementTemplates',
  'eventBus',
  'modeling',
  'replace',
  'selection',
  'translate'
];

inherits(CamundaPropertiesProvider, PropertiesActivator);

module.exports = CamundaPropertiesProvider;


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js":
/*!******************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js ***!
  \******************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var assign = __webpack_require__(/*! lodash/assign */ "./node_modules/lodash/assign.js");

var nextId = __webpack_require__(/*! ../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").nextId;

/**
 * Create an input parameter representing the given
 * binding and value.
 *
 * @param {PropertyBinding} binding
 * @param {String} value
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {ModdleElement}
 */
function createInputParameter(binding, value, bpmnFactory) {
  var scriptFormat = binding.scriptFormat,
      parameterValue,
      parameterDefinition;

  if (scriptFormat) {
    parameterDefinition = bpmnFactory.create('camunda:Script', {
      scriptFormat: scriptFormat,
      value: value
    });
  } else {
    parameterValue = value;
  }

  return bpmnFactory.create('camunda:InputParameter', {
    name: binding.name,
    value: parameterValue,
    definition: parameterDefinition
  });
}

module.exports.createInputParameter = createInputParameter;


/**
 * Create an output parameter representing the given
 * binding and value.
 *
 * @param {PropertyBinding} binding
 * @param {String} value
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {ModdleElement}
 */
function createOutputParameter(binding, value, bpmnFactory) {
  var scriptFormat = binding.scriptFormat,
      parameterValue,
      parameterDefinition;

  if (scriptFormat) {
    parameterDefinition = bpmnFactory.create('camunda:Script', {
      scriptFormat: scriptFormat,
      value: binding.source
    });
  } else {
    parameterValue = binding.source;
  }

  return bpmnFactory.create('camunda:OutputParameter', {
    name: value,
    value: parameterValue,
    definition: parameterDefinition
  });
}

module.exports.createOutputParameter = createOutputParameter;


/**
 * Create camunda property from the given binding.
 *
 * @param {PropertyBinding} binding
 * @param {String} value
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {ModdleElement}
 */
function createCamundaProperty(binding, value, bpmnFactory) {
  return bpmnFactory.create('camunda:Property', {
    name: binding.name,
    value: value || ''
  });
}

module.exports.createCamundaProperty = createCamundaProperty;


/**
 * Create camunda:in element from given binding.
 *
 * @param {PropertyBinding} binding
 * @param {String} value
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {ModdleElement}
 */
function createCamundaIn(binding, value, bpmnFactory) {

  var properties = createCamundaInOutAttrs(binding, value);

  return bpmnFactory.create('camunda:In', properties);
}

module.exports.createCamundaIn = createCamundaIn;


/**
 * Create camunda:in with businessKey element from given binding.
 *
 * @param {PropertyBinding} binding
 * @param {String} value
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {ModdleElement}
 */
function createCamundaInWithBusinessKey(binding, value, bpmnFactory) {
  return bpmnFactory.create('camunda:In', {
    businessKey: value
  });
}

module.exports.createCamundaInWithBusinessKey = createCamundaInWithBusinessKey;


/**
 * Create camunda:out element from given binding.
 *
 * @param {PropertyBinding} binding
 * @param {String} value
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {ModdleElement}
 */
function createCamundaOut(binding, value, bpmnFactory) {
  var properties = createCamundaInOutAttrs(binding, value);

  return bpmnFactory.create('camunda:Out', properties);
}

module.exports.createCamundaOut = createCamundaOut;


/**
 * Create camunda:executionListener element containing an inline script from given binding.
 *
 * @param {PropertyBinding} binding
 * @param {String} value
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {ModdleElement}
 */
function createCamundaExecutionListenerScript(binding, value, bpmnFactory) {
  var scriptFormat = binding.scriptFormat,
      parameterValue,
      parameterDefinition;

  if (scriptFormat) {
    parameterDefinition = bpmnFactory.create('camunda:Script', {
      scriptFormat: scriptFormat,
      value: value
    });
  } else {
    parameterValue = value;
  }

  return bpmnFactory.create('camunda:ExecutionListener', {
    event: binding.event,
    value: parameterValue,
    script: parameterDefinition
  });
}

module.exports.createCamundaExecutionListenerScript = createCamundaExecutionListenerScript;

/**
 * Create camunda:field element containing string or expression from given binding.
 *
 * @param {PropertyBinding} binding
 * @param {String} value
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {ModdleElement}
 */
function createCamundaFieldInjection(binding, value, bpmnFactory) {
  var DEFAULT_PROPS = {
    'string': undefined,
    'expression': undefined,
    'name': undefined
  };

  var props = assign({}, DEFAULT_PROPS);

  if (!binding.expression) {
    props.string = value;
  } else {
    props.expression = value;
  }
  props.name = binding.name;

  return bpmnFactory.create('camunda:Field', props);
}

module.exports.createCamundaFieldInjection = createCamundaFieldInjection;

/**
 * Create camunda:errorEventDefinition element containing expression and errorRef
 * from given binding.
 *
 * @param {PropertyBinding} binding
 * @param {String} value
 * @param {ModdleElement} error
 * @param {ModdleElement} parent
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {ModdleElement}
 */
function createCamundaErrorEventDefinition(binding, value, error, parent, bpmnFactory) {
  var errorRef = error,
      expression = value;

  var newErrorEventDefinition = bpmnFactory.create('camunda:ErrorEventDefinition', {
    expression: expression,
    errorRef: errorRef
  });

  newErrorEventDefinition.$parent = parent;

  return newErrorEventDefinition;
}

module.exports.createCamundaErrorEventDefinition = createCamundaErrorEventDefinition;

/**
 * Create bpmn:error element containing a specific error id given by a binding.
 *
 * @param {String} bindingErrorRef
 * @param {ModdleElement} parent
 * @param {BpmnFactory} bpmnFactory
 *
 * @return { ModdleElement }
 */
function createError(bindingErrorRef, parent, bpmnFactory) {
  var error = bpmnFactory.create('bpmn:Error', {

    // we need to later retrieve the error from a binding
    id: nextId('Error_' + bindingErrorRef + '_')
  });

  error.$parent = parent;

  return error;
}

module.exports.createError = createError;

// helpers ////////////////////////////

/**
 * Create properties for camunda:in and camunda:out types.
 */
function createCamundaInOutAttrs(binding, value) {

  var properties = {};

  // camunda:in source(Expression) target
  if (binding.target) {

    properties.target = binding.target;

    if (binding.expression) {
      properties.sourceExpression = value;
    } else {
      properties.source = value;
    }
  } else

  // camunda:(in|out) variables local
  if (binding.variables) {
    properties.variables = 'all';

    if (binding.variables === 'local') {
      properties.local = true;
    }
  }

  // camunda:out source(Expression) target
  else {
    properties.target = value;

    [ 'source', 'sourceExpression' ].forEach(function(k) {
      if (binding[k]) {
        properties[k] = binding[k];
      }
    });
  }

  return properties;
}


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js":
/*!************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js ***!
  \************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    isAny = __webpack_require__(/*! bpmn-js/lib/features/modeling/util/ModelingUtil */ "./node_modules/bpmn-js/lib/features/modeling/util/ModelingUtil.js").isAny;

var find = __webpack_require__(/*! lodash/find */ "./node_modules/lodash/find.js");

var TEMPLATE_ID_ATTR = 'camunda:modelerTemplate',
    TEMPLATE_VERSION_ATTR = 'camunda:modelerTemplateVersion';

/**
 * The BPMN 2.0 extension attribute name under
 * which the element template ID is stored.
 *
 * @type {String}
 */
module.exports.TEMPLATE_ID_ATTR = TEMPLATE_ID_ATTR;

/**
 * The BPMN 2.0 extension attribute name under
 * which the element template version is stored.
 *
 * @type {String}
 */
module.exports.TEMPLATE_VERSION_ATTR = TEMPLATE_VERSION_ATTR;


/**
 * Get template id for a given diagram element.
 *
 * @param {djs.model.Base} element
 *
 * @return {String}
 */
function getTemplateId(element) {

  var bo = getBusinessObject(element);

  if (bo) {
    return bo.get(TEMPLATE_ID_ATTR);
  }
}

module.exports.getTemplateId = getTemplateId;

/**
 * Get template version for a given diagram element.
 *
 * @param {djs.model.Base} element
 *
 * @return {String}
 */
function getTemplateVersion(element) {

  var bo = getBusinessObject(element);

  if (bo) {
    return bo.get(TEMPLATE_VERSION_ATTR);
  }
}

module.exports.getTemplateVersion = getTemplateVersion;


/**
 * Find extension with given type in
 * BPMN element, diagram element or ExtensionElement.
 *
 * @param {ModdleElement|djs.model.Base} element
 * @param {String} type
 *
 * @return {ModdleElement} the extension
 */
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

module.exports.findExtension = findExtension;


function findExtensions(element, types) {
  var extensionElements = getExtensionElements(element);

  if (!extensionElements) {
    return [];
  }

  return extensionElements.get('values').filter(function(e) {
    return isAny(e, types);
  });
}

module.exports.findExtensions = findExtensions;


function findCamundaInOut(element, binding) {

  var extensionElements = getExtensionElements(element);

  if (!extensionElements) {
    return;
  }

  var matcher;

  if (binding.type === 'camunda:in') {
    matcher = function(e) {
      return is(e, 'camunda:In') && isInOut(e, binding);
    };
  } else
  if (binding.type === 'camunda:out') {
    matcher = function(e) {
      return is(e, 'camunda:Out') && isInOut(e, binding);
    };
  } else
  if (binding.type === 'camunda:in:businessKey') {
    matcher = function(e) {
      return is(e, 'camunda:In') && 'businessKey' in e;
    };
  }

  return find(extensionElements.get('values'), matcher);
}

module.exports.findCamundaInOut = findCamundaInOut;

function findCamundaProperty(camundaProperties, binding) {
  return find(camundaProperties.get('values'), function(p) {
    return p.name === binding.name;
  });
}

module.exports.findCamundaProperty = findCamundaProperty;


function findInputParameter(inputOutput, binding) {
  var parameters = inputOutput.get('inputParameters');

  return find(parameters, function(p) {
    return p.name === binding.name;
  });
}

module.exports.findInputParameter = findInputParameter;


function findOutputParameter(inputOutput, binding) {
  var parameters = inputOutput.get('outputParameters');

  return find(parameters, function(p) {
    var value = p.value;

    if (!binding.scriptFormat) {
      return value === binding.source;
    }

    var definition = p.definition;

    if (!definition || binding.scriptFormat !== definition.scriptFormat) {
      return false;
    }

    return definition.value === binding.source;
  });
}

module.exports.findOutputParameter = findOutputParameter;


function findCamundaErrorEventDefinition(element, bindingErrorRef) {
  var errorEventDefinitions = findExtensions(element, [ 'camunda:ErrorEventDefinition' ]),
      error;

  // error id has to start with <Error_${binding.errorRef}_>
  return find(errorEventDefinitions, function(definition) {
    error = definition.errorRef;

    if (error) {
      return error.id.indexOf('Error_' + bindingErrorRef) == 0;
    }
  });
}

module.exports.findCamundaErrorEventDefinition = findCamundaErrorEventDefinition;



// helpers /////////////////////////////////

function getExtensionElements(element) {
  var bo = getBusinessObject(element);

  if (is(bo, 'bpmn:ExtensionElements')) {
    return bo;
  } else {
    return bo.extensionElements;
  }
}


function isInOut(element, binding) {

  if (binding.type === 'camunda:in') {

    // find based on target attribute
    if (binding.target) {
      return element.target === binding.target;
    }
  }

  if (binding.type === 'camunda:out') {

    // find based on source / sourceExpression
    if (binding.source) {
      return element.source === binding.source;
    }

    if (binding.sourceExpression) {
      return element.sourceExpression === binding.sourceExpression;
    }
  }

  // find based variables / local combination
  if (binding.variables) {
    return element.variables === 'all' && (
      binding.variables !== 'local' || element.local
    );
  }
}


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/ChooserProps.js":
/*!************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/ChooserProps.js ***!
  \************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var getOption = __webpack_require__(/*! ./Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/Helper.js").getOption,
    getOptions = __webpack_require__(/*! ./Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/Helper.js").getOptions,
    getTemplateId = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").getTemplateId,
    ELEMENT_TEMPLATE_OPTION_EMPTY = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").ELEMENT_TEMPLATE_OPTION_EMPTY;

var isNull = __webpack_require__(/*! lodash/isNull */ "./node_modules/lodash/isNull.js"),
    isUndefined = __webpack_require__(/*! lodash/isUndefined */ "./node_modules/lodash/isUndefined.js"),
    pick = __webpack_require__(/*! lodash/pick */ "./node_modules/lodash/pick.js");

module.exports = function(group, element, elementTemplates, translate) {
  if (!isUndefined(getTemplateId(element)) && !isNull(getTemplateId(element))) {
    return;
  }

  var options = getOptions(element, elementTemplates, translate);

  // Return if empty option is only option
  if (options.length === 1) {
    return;
  }

  var selectOptions = options.map(function(option) {
    return pick(option, [ 'name', 'value' ]);
  });

  // select element template (via dropdown)
  group.entries.push(entryFactory.selectBox(translate, {
    id: 'elementTemplate-chooser',
    label: translate('Element Template'),
    modelProperty: 'elementTemplateOption',
    selectOptions: selectOptions,
    get: function(element) {
      return {
        elementTemplateOption: ELEMENT_TEMPLATE_OPTION_EMPTY
      };
    },
    set: function(element, properties) {
      var optionId = properties['elementTemplateOption'];

      var option = getOption(optionId, options);

      var id = option.id,
          version = option.version;

      var newTemplate = elementTemplates.get(id, version);

      return applyTemplate(element, newTemplate, elementTemplates);
    }
  }));

};


// helpers //////////

function applyTemplate(element, newTemplate, elementTemplates) {
  var oldTemplate = elementTemplates.get(element);

  if (oldTemplate === newTemplate) {
    return;
  }

  return {
    cmd: 'propertiesPanel.camunda.changeTemplate',
    context: {
      element: element,
      oldTemplate: oldTemplate,
      newTemplate: newTemplate
    }
  };
}

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/CustomProps.js":
/*!***********************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/CustomProps.js ***!
  \***********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var assign = __webpack_require__(/*! lodash/assign */ "./node_modules/lodash/assign.js");

var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js");

var findExtension = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findExtension,
    findExtensions = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findExtensions,
    findInputParameter = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findInputParameter,
    findOutputParameter = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findOutputParameter,
    findCamundaProperty = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findCamundaProperty,
    findCamundaInOut = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findCamundaInOut,
    findCamundaErrorEventDefinition = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findCamundaErrorEventDefinition;

var createCamundaProperty = __webpack_require__(/*! ../CreateHelper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js").createCamundaProperty,
    createInputParameter = __webpack_require__(/*! ../CreateHelper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js").createInputParameter,
    createOutputParameter = __webpack_require__(/*! ../CreateHelper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js").createOutputParameter,
    createCamundaIn = __webpack_require__(/*! ../CreateHelper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js").createCamundaIn,
    createCamundaOut = __webpack_require__(/*! ../CreateHelper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js").createCamundaOut,
    createCamundaInWithBusinessKey = __webpack_require__(/*! ../CreateHelper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js").createCamundaInWithBusinessKey,
    createCamundaFieldInjection = __webpack_require__(/*! ../CreateHelper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js").createCamundaFieldInjection,
    createCamundaErrorEventDefinition = __webpack_require__(/*! ../CreateHelper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js").createCamundaErrorEventDefinition,
    createError = __webpack_require__(/*! ../CreateHelper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js").createError;

var handleLegacyScopes = __webpack_require__(/*! ../util/handleLegacyScopes */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/util/handleLegacyScopes.js");

var getRoot = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").getRoot;

var PROPERTY_TYPE = 'property',
    CAMUNDA_PROPERTY_TYPE = 'camunda:property',
    CAMUNDA_INPUT_PARAMETER_TYPE = 'camunda:inputParameter',
    CAMUNDA_OUTPUT_PARAMETER_TYPE = 'camunda:outputParameter',
    CAMUNDA_IN_TYPE = 'camunda:in',
    CAMUNDA_OUT_TYPE = 'camunda:out',
    CAMUNDA_IN_BUSINESS_KEY_TYPE = 'camunda:in:businessKey',
    CAMUNDA_EXECUTION_LISTENER_TYPE = 'camunda:executionListener',
    CAMUNDA_FIELD = 'camunda:field',
    CAMUNDA_ERROR_EVENT_DEFINITION = 'camunda:errorEventDefinition';

var BASIC_MODDLE_TYPES = [
  'Boolean',
  'Integer',
  'String'
];

var EXTENSION_BINDING_TYPES = [
  CAMUNDA_PROPERTY_TYPE,
  CAMUNDA_INPUT_PARAMETER_TYPE,
  CAMUNDA_OUTPUT_PARAMETER_TYPE,
  CAMUNDA_IN_TYPE,
  CAMUNDA_OUT_TYPE,
  CAMUNDA_IN_BUSINESS_KEY_TYPE,
  CAMUNDA_FIELD,
  CAMUNDA_ERROR_EVENT_DEFINITION
];

var IO_BINDING_TYPES = [
  CAMUNDA_INPUT_PARAMETER_TYPE,
  CAMUNDA_OUTPUT_PARAMETER_TYPE
];

var IN_OUT_BINDING_TYPES = [
  CAMUNDA_IN_TYPE,
  CAMUNDA_OUT_TYPE,
  CAMUNDA_IN_BUSINESS_KEY_TYPE
];

/**
 * Injects custom properties into the given group.
 *
 * @param {djs.model.Base} element
 * @param {ElementTemplates} elementTemplates
 * @param {BpmnFactory} bpmnFactory
 * @param {Function} translate
 */
module.exports = function(element, elementTemplates, bpmnFactory, translate) {

  var template = elementTemplates.get(element);

  if (!template) {
    return [];
  }

  var renderCustomField = function(id, p, idx) {
    var propertyType = p.type;

    var entryOptions = {
      id: id,
      description: p.description,
      label: p.label ? translate(p.label) : p.label,
      modelProperty: id,
      get: propertyGetter(id, p),
      set: propertySetter(id, p, bpmnFactory),
      validate: propertyValidator(id, p, translate)
    };

    var entry;

    if (!propertyType) {
      propertyType = getDefaultType(p);
    }

    if (propertyType === 'Boolean') {
      entry = entryFactory.checkbox(translate, entryOptions);
    }

    if (propertyType === 'String') {
      entry = entryFactory.textField(translate, entryOptions);
    }

    if (propertyType === 'Text') {
      entry = entryFactory.textBox(translate, entryOptions);
    }

    if (propertyType === 'Dropdown') {
      entryOptions.selectOptions = p.choices;

      entry = entryFactory.selectBox(translate, entryOptions);
    }

    return entry;
  };

  var groups = [];
  var id, entry;

  var customFieldsGroup = {
    id: 'customField',
    label: translate('Custom Fields'),
    entries: []
  };
  template.properties.forEach(function(p, idx) {

    id = 'custom-' + template.id + '-' + idx;

    entry = renderCustomField(id, p, idx);
    if (entry) {
      customFieldsGroup.entries.push(entry);
    }
  });
  if (customFieldsGroup.entries.length > 0) {
    groups.push(customFieldsGroup);
  }

  if (template.scopes) {

    handleLegacyScopes(template.scopes).forEach(function(scope) {

      var scopeType = scope.type;

      var idScopeName = scopeType.replace(/:/g, '_');

      var customScopeFieldsGroup = {
        id: 'customField-' + idScopeName,
        label: translate('Custom Fields for scope: ') + scopeType,
        entries: []
      };

      scope.properties.forEach(function(p, idx) {

        var propertyId = 'custom-' + template.id + '-' + idScopeName + '-' + idx;

        var scopedProperty = propertyWithScope(p, scope);

        entry = renderCustomField(propertyId, scopedProperty, idx);
        if (entry) {
          customScopeFieldsGroup.entries.push(entry);
        }
      });

      if (customScopeFieldsGroup.entries.length > 0) {
        groups.push(customScopeFieldsGroup);
      }
    });
  }

  return groups;
};


// getters, setters and validators ///////////////


/**
 * Return a getter that retrieves the given property.
 *
 * @param {String} name
 * @param {PropertyDescriptor} property
 *
 * @return {Function}
 */
function propertyGetter(name, property) {

  /* getter */
  return function get(element) {
    var value = getPropertyValue(element, property);

    return objectWithKey(name, value);
  };
}

/**
 * Return a setter that updates the given property.
 *
 * @param {String} name
 * @param {PropertyDescriptor} property
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {Function}
 */
function propertySetter(name, property, bpmnFactory) {

  /* setter */
  return function set(element, values) {

    var value = values[name];

    return setPropertyValue(element, property, value, bpmnFactory);
  };
}

/**
 * Return a validator that ensures the property is ok.
 *
 * @param {String} name
 * @param {PropertyDescriptor} property
 * @param {Function} translate
 *
 * @return {Function}
 */
function propertyValidator(name, property, translate) {

  /* validator */
  return function validate(element, values) {
    var value = values[name];

    var error = validateValue(value, property, translate);

    if (error) {
      return objectWithKey(name, error);
    }
  };
}


// get, set and validate helpers ///////////////////

/**
 * Return the value of the specified property descriptor,
 * on the passed diagram element.
 *
 * @param {djs.model.Base} element
 * @param {PropertyDescriptor} property
 *
 * @return {Any}
 */
function getPropertyValue(element, property) {

  var bo = getBusinessObject(element);

  var binding = property.binding,
      scope = property.scope;

  var bindingType = binding.type,
      bindingName = binding.name;

  var propertyValue = property.value || '';

  if (scope) {
    bo = findScopeElement(bo, scope);
    if (!bo) {
      return propertyValue;
    }
  }

  // property
  if (bindingType === 'property') {

    var value = bo.get(bindingName);

    if (bindingName === 'conditionExpression') {
      if (value) {
        return value.body;
      } else {

        // return defined default
        return propertyValue;
      }
    } else {

      // return value; default to defined default
      return typeof value !== 'undefined' ? value : propertyValue;
    }
  }

  var camundaProperties,
      camundaProperty;

  if (bindingType === CAMUNDA_PROPERTY_TYPE) {
    if (scope) {
      camundaProperties = bo.get('properties');
    } else {
      camundaProperties = findExtension(bo, 'camunda:Properties');
    }

    if (camundaProperties) {
      camundaProperty = findCamundaProperty(camundaProperties, binding);

      if (camundaProperty) {
        return camundaProperty.value;
      }
    }

    return propertyValue;
  }

  var inputOutput,
      ioParameter;

  if (IO_BINDING_TYPES.indexOf(bindingType) !== -1) {

    if (scope) {
      inputOutput = bo.get('inputOutput');
    } else {
      inputOutput = findExtension(bo, 'camunda:InputOutput');
    }

    if (!inputOutput) {

      // ioParameter cannot exist yet, return property value
      return propertyValue;
    }
  }

  // camunda input parameter
  if (bindingType === CAMUNDA_INPUT_PARAMETER_TYPE) {
    ioParameter = findInputParameter(inputOutput, binding);

    if (ioParameter) {
      if (binding.scriptFormat) {
        if (ioParameter.definition) {
          return ioParameter.definition.value;
        }
      } else {
        return ioParameter.value || '';
      }
    }

    return propertyValue;
  }

  // camunda output parameter
  if (binding.type === CAMUNDA_OUTPUT_PARAMETER_TYPE) {
    ioParameter = findOutputParameter(inputOutput, binding);

    if (ioParameter) {
      return ioParameter.name;
    }

    return propertyValue;
  }


  var ioElement;

  if (IN_OUT_BINDING_TYPES.indexOf(bindingType) != -1) {
    ioElement = findCamundaInOut(bo, binding);

    if (ioElement) {
      if (bindingType === CAMUNDA_IN_BUSINESS_KEY_TYPE) {
        return ioElement.businessKey;
      } else
      if (bindingType === CAMUNDA_OUT_TYPE) {
        return ioElement.target;
      } else
      if (bindingType === CAMUNDA_IN_TYPE) {
        return ioElement[binding.expression ? 'sourceExpression' : 'source'];
      }
    }

    return propertyValue;
  }

  if (bindingType === CAMUNDA_EXECUTION_LISTENER_TYPE) {
    var executionListener;
    if (scope) {
      executionListener = bo.get('executionListener');
    } else {
      executionListener = findExtension(bo, 'camunda:ExecutionListener');
    }

    return executionListener.script.value;
  }

  var fieldInjection;
  if (CAMUNDA_FIELD === bindingType) {
    var fieldInjections = findExtensions(bo, [ 'camunda:Field' ]);
    fieldInjections.forEach(function(item) {
      if (item.name === binding.name) {
        fieldInjection = item;
      }
    });
    if (fieldInjection) {
      return fieldInjection.string || fieldInjection.expression;
    } else {
      return '';
    }
  }

  var errorEventDefinition;
  if (CAMUNDA_ERROR_EVENT_DEFINITION === bindingType) {
    errorEventDefinition = findCamundaErrorEventDefinition(bo, binding.errorRef);

    if (errorEventDefinition) {
      return errorEventDefinition.expression;
    } else {
      return '';
    }
  }

  throw unknownPropertyBinding(property);
}

module.exports.getPropertyValue = getPropertyValue;


/**
 * Return an update operation that changes the diagram
 * element's custom property to the given value.
 *
 * The response of this method will be processed via
 * {@link PropertiesPanel#applyChanges}.
 *
 * @param {djs.model.Base} element
 * @param {PropertyDescriptor} property
 * @param {String} value
 * @param {BpmnFactory} bpmnFactory
 *
 * @return {Object|Array<Object>} results to be processed
 */
function setPropertyValue(element, property, value, bpmnFactory) {
  var bo = getBusinessObject(element);

  var binding = property.binding,
      scope = property.scope;

  var bindingType = binding.type,
      bindingName = binding.name;

  var rootElement = getRoot(bo);

  var propertyValue;

  var updates = [];

  var extensionElements;

  if (EXTENSION_BINDING_TYPES.indexOf(bindingType) !== -1) {
    extensionElements = bo.get('extensionElements');

    // create extension elements, if they do not exist (yet)
    if (!extensionElements) {
      extensionElements = elementHelper.createElement('bpmn:ExtensionElements', null, element, bpmnFactory);

      updates.push(cmdHelper.updateBusinessObject(
        element, bo, objectWithKey('extensionElements', extensionElements)
      ));
    }
  }

  if (scope) {
    bo = findScopeElement(bo, scope);
    if (!bo) {

      // bpmn:Error
      if (scope.name === 'bpmn:Error') {
        bo = createError(scope.id, rootElement, bpmnFactory);

        updates.push(cmdHelper.addElementsTolist(
          bo, rootElement, 'rootElements', [ bo ]
        ));
      } else {
        bo = elementHelper.createElement(scope.name, null, element, bpmnFactory);

        updates.push(cmdHelper.addElementsTolist(
          bo, extensionElements, 'values', [ bo ]
        ));
      }
    }
  }

  // property
  if (bindingType === 'property') {

    if (bindingName === 'conditionExpression') {

      propertyValue = elementHelper.createElement('bpmn:FormalExpression', {
        body: value,
        language: binding.scriptFormat
      }, bo, bpmnFactory);
    } else {

      var moddlePropertyDescriptor = bo.$descriptor.propertiesByName[bindingName];

      var moddleType = moddlePropertyDescriptor.type;

      // make sure we only update String, Integer, Real and
      // Boolean properties (do not accidentally override complex objects...)
      if (BASIC_MODDLE_TYPES.indexOf(moddleType) === -1) {
        throw new Error('cannot set moddle type <' + moddleType + '>');
      }

      if (moddleType === 'Boolean') {
        propertyValue = !!value;
      } else
      if (moddleType === 'Integer') {
        propertyValue = parseInt(value, 10);

        if (isNaN(propertyValue)) {

          // do not write NaN value
          propertyValue = undefined;
        }
      } else {
        propertyValue = value;
      }
    }

    if (propertyValue !== undefined) {
      updates.push(cmdHelper.updateBusinessObject(
        element, bo, objectWithKey(bindingName, propertyValue)
      ));
    }
  }

  // camunda:property
  var camundaProperties,
      existingCamundaProperty,
      newCamundaProperty;

  if (bindingType === CAMUNDA_PROPERTY_TYPE) {

    if (scope) {
      camundaProperties = bo.get('properties');
    } else {
      camundaProperties = findExtension(extensionElements, 'camunda:Properties');
    }

    if (!camundaProperties) {
      camundaProperties = elementHelper.createElement('camunda:Properties', null, bo, bpmnFactory);

      if (scope) {
        updates.push(cmdHelper.updateBusinessObject(
          element, bo, { properties: camundaProperties }
        ));
      }
      else {
        updates.push(cmdHelper.addElementsTolist(
          element, extensionElements, 'values', [ camundaProperties ]
        ));
      }
    }

    existingCamundaProperty = findCamundaProperty(camundaProperties, binding);

    newCamundaProperty = createCamundaProperty(binding, value, bpmnFactory);

    updates.push(cmdHelper.addAndRemoveElementsFromList(
      element,
      camundaProperties,
      'values',
      null,
      [ newCamundaProperty ],
      existingCamundaProperty ? [ existingCamundaProperty ] : []
    ));
  }

  // camunda:inputParameter
  // camunda:outputParameter
  var inputOutput,
      existingIoParameter,
      newIoParameter;

  if (IO_BINDING_TYPES.indexOf(bindingType) !== -1) {

    if (scope) {
      inputOutput = bo.get('inputOutput');
    } else {
      inputOutput = findExtension(extensionElements, 'camunda:InputOutput');
    }

    // create inputOutput element, if it do not exist (yet)
    if (!inputOutput) {
      inputOutput = elementHelper.createElement('camunda:InputOutput', null, bo, bpmnFactory);

      if (scope) {
        updates.push(cmdHelper.updateBusinessObject(
          element, bo, { inputOutput: inputOutput }
        ));
      }
      else {
        updates.push(cmdHelper.addElementsTolist(
          element, extensionElements, 'values', inputOutput
        ));
      }
    }
  }

  if (bindingType === CAMUNDA_INPUT_PARAMETER_TYPE) {

    existingIoParameter = findInputParameter(inputOutput, binding);

    newIoParameter = createInputParameter(binding, value, bpmnFactory);

    updates.push(cmdHelper.addAndRemoveElementsFromList(
      element,
      inputOutput,
      'inputParameters',
      null,
      [ newIoParameter ],
      existingIoParameter ? [ existingIoParameter ] : []
    ));
  }

  if (bindingType === CAMUNDA_OUTPUT_PARAMETER_TYPE) {

    existingIoParameter = findOutputParameter(inputOutput, binding);

    newIoParameter = createOutputParameter(binding, value, bpmnFactory);

    updates.push(cmdHelper.addAndRemoveElementsFromList(
      element,
      inputOutput,
      'outputParameters',
      null,
      [ newIoParameter ],
      existingIoParameter ? [ existingIoParameter ] : []
    ));
  }


  // camunda:in
  // camunda:out
  // camunda:in:businessKey
  var existingInOut,
      newInOut;

  if (IN_OUT_BINDING_TYPES.indexOf(bindingType) !== -1) {

    existingInOut = findCamundaInOut(bo, binding);

    if (bindingType === CAMUNDA_IN_TYPE) {
      newInOut = createCamundaIn(binding, value, bpmnFactory);
    } else
    if (bindingType === CAMUNDA_OUT_TYPE) {
      newInOut = createCamundaOut(binding, value, bpmnFactory);
    } else {
      newInOut = createCamundaInWithBusinessKey(binding, value, bpmnFactory);
    }

    updates.push(cmdHelper.addAndRemoveElementsFromList(
      element,
      extensionElements,
      'values',
      null,
      [ newInOut ],
      existingInOut ? [ existingInOut ] : []
    ));
  }

  if (bindingType === CAMUNDA_FIELD) {
    var existingFieldInjections = findExtensions(bo, [ 'camunda:Field' ]);
    var newFieldInjections = [];

    if (existingFieldInjections.length > 0) {
      existingFieldInjections.forEach(function(item) {
        if (item.name === binding.name) {
          newFieldInjections.push(createCamundaFieldInjection(binding, value, bpmnFactory));
        } else {
          newFieldInjections.push(item);
        }
      });
    } else {
      newFieldInjections.push(createCamundaFieldInjection(binding, value, bpmnFactory));
    }

    updates.push(cmdHelper.addAndRemoveElementsFromList(
      element,
      extensionElements,
      'values',
      null,
      newFieldInjections,
      existingFieldInjections ? existingFieldInjections : []
    ));
  }

  // camunda:errorEventDefinition
  if (bindingType === CAMUNDA_ERROR_EVENT_DEFINITION) {
    var existingErrorEventDefinition = findCamundaErrorEventDefinition(bo, binding.errorRef);

    if (existingErrorEventDefinition) {
      updates.push(cmdHelper.updateBusinessObject(
        element, existingErrorEventDefinition, { expression: value }
      ));
    } else {

      var newError = createError(binding.errorRef, rootElement, bpmnFactory),
          newEventDefinition =
            createCamundaErrorEventDefinition(binding, value, newError, extensionElements, bpmnFactory);

      updates.push(cmdHelper.addAndRemoveElementsFromList(
        element,
        rootElement,
        'rootElements',
        null,
        [ newError ],
        []
      ));

      updates.push(cmdHelper.addAndRemoveElementsFromList(
        element,
        extensionElements,
        'values',
        null,
        [ newEventDefinition ],
        []
      ));
    }

  }

  if (updates.length) {
    return updates;
  }

  // quick warning for better debugging
  console.warn('no update', element, property, value);
}

module.exports.setPropertyValue = setPropertyValue;

/**
 * Validate value of a given property.
 *
 * @param {String} value
 * @param {PropertyDescriptor} property
 * @param {Function} translate
 *
 * @return {Object} with validation errors
 */
function validateValue(value, property, translate) {

  var constraints = property.constraints || {};

  if (constraints.notEmpty && isEmpty(value)) {
    return translate('Must not be empty');
  }

  if (constraints.maxLength && value.length > constraints.maxLength) {
    return translate('Must have max length {length}', { length: constraints.maxLength });
  }

  if (constraints.minLength && value.length < constraints.minLength) {
    return translate('Must have min length {length}', { length: constraints.minLength });
  }

  var pattern = constraints.pattern,
      message;

  if (pattern) {

    if (typeof pattern !== 'string') {
      message = pattern.message;
      pattern = pattern.value;
    }

    if (!matchesPattern(value, pattern)) {
      return message || translate('Must match pattern {pattern}', { pattern: pattern });
    }
  }
}


// misc helpers ///////////////////////////////

function propertyWithScope(property, scope) {
  var scopeName = scope.type,
      scopeId = scope.id;

  if (!scopeName) {
    return property;
  }

  return assign({}, property, {
    scope: {
      name: scopeName,
      id: scopeId
    }
  });
}

/**
 * Return an object with a single key -> value association.
 *
 * @param {String} key
 * @param {Any} value
 *
 * @return {Object}
 */
function objectWithKey(key, value) {
  var obj = {};

  obj[key] = value;

  return obj;
}

/**
 * Does the given string match the specified pattern?
 *
 * @param {String} str
 * @param {String} pattern
 *
 * @return {Boolean}
 */
function matchesPattern(str, pattern) {
  var regexp = new RegExp(pattern);

  return regexp.test(str);
}

function isEmpty(str) {
  return !str || /^\s*$/.test(str);
}

/**
 * Create a new {@link Error} indicating an unknown
 * property binding.
 *
 * @param {PropertyDescriptor} property
 *
 * @return {Error}
 */
function unknownPropertyBinding(property) {
  var binding = property.binding;

  return new Error('unknown binding: <' + binding.type + '>');
}

function getDefaultType(property) {
  var binding = property.binding,
      bindingType = binding.type;

  if (bindingType === PROPERTY_TYPE ||
      bindingType === CAMUNDA_PROPERTY_TYPE ||
      bindingType === CAMUNDA_IN_TYPE ||
      bindingType === CAMUNDA_IN_BUSINESS_KEY_TYPE ||
      bindingType === CAMUNDA_OUT_TYPE ||
      bindingType === CAMUNDA_FIELD) {
    return 'String';
  }

  if (bindingType === CAMUNDA_EXECUTION_LISTENER_TYPE) {
    return 'Hidden';
  }
}

function findScopeElement(businessObject, scope) {

  var scopeName = scope.name,
      scopeId = scope.id;

  if (scopeName === 'bpmn:Error') {

    // retrieve error over referenced error event definition
    var errorEventDefinition = findCamundaErrorEventDefinition(businessObject, scopeId);

    if (errorEventDefinition) {
      return errorEventDefinition.errorRef;
    }
  }

  return findExtension(businessObject, scopeName);
}

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/DescriptionProps.js":
/*!****************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/DescriptionProps.js ***!
  \****************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var escapeHTML = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var getTemplateId = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").getTemplateId;

var domClasses = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").classes,
    domEvent = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").event,
    domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify,
    domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

var isUndefined = __webpack_require__(/*! lodash/isUndefined */ "./node_modules/lodash/isUndefined.js");

var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var getVersionOrDateFromTemplate = __webpack_require__(/*! ./Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/Helper.js").getVersionOrDateFromTemplate;

var MAX_DESCRIPTION_LENGTH = 200;

module.exports = function(
    element,
    commandStack,
    elementTemplates,
    modeling,
    replace,
    selection,
    translate) {
  var currentElementTemplate = elementTemplates.get(element);

  var entries = [];

  var description,
      newestElementTemplate;

  if (currentElementTemplate) {
    newestElementTemplate = findNewestElementTemplate(elementTemplates, currentElementTemplate);

    if (newestElementTemplate) {
      entries.push({
        id: 'element-template-update',
        cssClasses: [ 'bpp-entry--warning' ],
        html: createUpdateTemplateEntry(element, currentElementTemplate, newestElementTemplate, commandStack, translate)
      });
    }

    description = currentElementTemplate.description;

    if (description) {
      entries.push(createDescriptionEntry(description, translate));
    }
  } else {
    entries.push({
      id: 'element-template-not-found',
      cssClasses: [ 'bpp-entry--warning' ],
      html: createTemplateNotFoundEntry(element, modeling, translate)
    });
  }

  if (currentElementTemplate && currentElementTemplate.version) {
    entries.push({
      id: 'element-template-version',
      html: '<p>' + getVersionOrDateFromTemplate(currentElementTemplate) + '</p>'
    });
  }

  return {
    id: 'elementTemplateDescription',
    label: currentElementTemplate ? currentElementTemplate.name : translate('Missing Template'),
    dropdown: {
      menu: [
        {
          id: 'element-template-unlink',
          label: translate('Unlink'),
          onClick: function() {
            modeling.updateProperties(element, {
              'camunda:modelerTemplate': null,
              'camunda:modelerTemplateVersion': null
            });
          }
        },
        {
          id: 'element-template-remove',
          label: translate('Remove'),
          onClick: function() {
            var businessObject = getBusinessObject(element);

            var type = businessObject.$type,
                eventDefinitionType = getEventDefinitionType(businessObject);

            var newElement = replace.replaceElement(element, {
              type: type,
              eventDefinitionType: eventDefinitionType
            });

            selection.select(newElement);
          }
        }
      ]
    },
    entries: entries
  };
};

// helpers //////////

function createDescriptionEntry(description, translate) {
  description = escapeHTML(description);

  var html = domify('<p class="description description--expanded"></p>');

  var descriptionText = domify('<span class="description__text">' + description + '</span>');

  html.appendChild(descriptionText);

  function toggleExpanded(expanded) {
    if (expanded) {
      domClasses(html).add('description--expanded');

      descriptionText.textContent = description + ' ';

      expand.textContent = translate('Less');
    } else {
      domClasses(html).remove('description--expanded');

      descriptionText.textContent = descriptionShortened + ' ... ';

      expand.textContent = translate('More');
    }
  }

  var descriptionShortened,
      expand,
      expanded = false;

  if (description.length > MAX_DESCRIPTION_LENGTH) {
    descriptionShortened = description.slice(0, MAX_DESCRIPTION_LENGTH);

    expand = domify(
      '<span class="bpp-entry-link description__expand">' +
        translate('More') +
      '</span>'
    );

    domEvent.bind(expand, 'click', function() {
      expanded = !expanded;

      toggleExpanded(expanded);
    });

    html.appendChild(expand);

    toggleExpanded(expanded);
  }

  return {
    id: 'element-template-description',
    html: html
  };
}

function getEventDefinitionType(businessObject) {
  if (!businessObject.eventDefinitions) {
    return null;
  }

  var eventDefinition = businessObject.eventDefinitions[ 0 ];

  if (!eventDefinition) {
    return null;
  }

  return eventDefinition.$type;
}

function createTemplateNotFoundEntry(element, modeling, translate) {
  var templateId = getTemplateId(element);

  var html = domify(
    '<p>' +
    translate(
      'The applied Template &lt;{templateId}&gt; was not found and therefore its data is not accessible.' +
      '<br />' +
      '<br />' +
      'Unlink in order to access the data.', { templateId: templateId }
    ) +
    '</p>' +
    '<p style="text-align: right;">' +
    '<a class="bpp-entry-link bpp-entry-link-button">Unlink</a>' +
    '</p>'
  );

  domQuery('.bpp-entry-link', html).addEventListener('click', function() {
    modeling.updateProperties(element, {
      'camunda:modelerTemplate': null
    });
  });

  return html;
}

function createUpdateTemplateEntry(element, oldElementTemplate, newElementTemplate, commandStack, translate) {
  var newElementTemplateVersion = getVersionOrDateFromTemplate(newElementTemplate);

  var html = domify(
    '<p>' +
    translate(
      'A new version of the Template ({newElementTemplateVersion}) is available.',
      { newElementTemplateVersion: newElementTemplateVersion }
    ) +
    '</p>' +
    '<p style="text-align: right;">' +
    '<a class="bpp-entry-link bpp-entry-link-button">Update</a>' +
    '</p>'
  );

  domQuery('.bpp-entry-link', html).addEventListener('click', function() {
    commandStack.execute('propertiesPanel.camunda.changeTemplate', {
      element: element,
      newTemplate: newElementTemplate,
      oldTemplate: oldElementTemplate
    });
  });

  return html;
}

function findNewestElementTemplate(elementTemplates, currentElementTemplate) {
  if (isUndefined(currentElementTemplate.version)) {
    return null;
  }

  return elementTemplates
    .getAll()
    .filter(function(elementTemplate) {
      return currentElementTemplate.id === elementTemplate.id && !isUndefined(elementTemplate.version);
    })
    .reduce(function(newestElementTemplate, elementTemplate) {
      if (currentElementTemplate.version < elementTemplate.version) {
        return elementTemplate;
      }

      if (newestElementTemplate && newestElementTemplate.version < elementTemplate.version) {
        return elementTemplate;
      }

      return newestElementTemplate;
    }, null);
}


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/ErrorsProps.js":
/*!***********************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/ErrorsProps.js ***!
  \***********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var forEach = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").forEach,
    filter = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").filter,
    flatten = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").flatten,
    findIndex = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").findIndex;

var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var findExtensions = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findExtensions,
    findCamundaErrorEventDefinition = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findCamundaErrorEventDefinition;

var ErrorEntries = __webpack_require__(/*! ../../parts/implementation/ErrorsEntries */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ErrorsEntries.js");

var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

var CAMUNDA_ERROR_EVENT_DEFINITION_TYPE = 'camunda:errorEventDefinition';

var EMPTY_ERROR = {
  get: function() {},
  set: function() {},
  errorRef: {}
};

/**
 * Injects element template errors into the given group.
 *
 * @param {Object} group
 * @param {djs.model.Base} element
 * @param {ElementTemplates} elementTemplates
 * @param {BpmnFactory} bpmnFactory
 * @param {Function} translate
 */
module.exports = function(group, element, elementTemplates, bpmnFactory, translate) {
  var template = elementTemplates.get(element);

  if (!template) {
    return [];
  }

  var errorEntries = [];

  function onToggle(value, entryNode) {
    if (!value) {
      return;
    }

    var currentEntryId = entryNode.dataset.entry;

    // collapse all other items
    errorEntries.forEach(function(entries) {
      var collapsible = entries[0];

      if (collapsible.id === currentEntryId) {
        return;
      }

      var entryNode = domQuery('[data-entry="' + collapsible.id + '"]');
      collapsible.setOpen(false, entryNode);
    });
  }


  function renderError(id, templateProperty) {
    var binding = templateProperty.binding,
        bindingErrorRef = binding.errorRef,
        errorEntries = [],
        collapsibleEntry;

    // find error event definition first
    var bo = getBusinessObject(element),
        errorEventDefinitions = findExtensions(bo, [ 'camunda:ErrorEventDefinition' ]);

    if (!errorEventDefinitions) {
      return errorEntries;
    }

    var getError = function() {
      var definition = findCamundaErrorEventDefinition(element, bindingErrorRef);

      if (!definition) {
        return EMPTY_ERROR;
      }

      return definition;
    };

    var error = getError();

    var isOpen = function() {
      return collapsibleEntry.isOpen();
    };

    var options = {
      idPrefix: id + '-',
      onToggle: onToggle,
      getError: getError,
      isOpen: function() {
        return isOpen();
      }
    };

    // (1) use errors implementation
    var errorImplementation = ErrorEntries(error, bpmnFactory, element, options, translate);
    errorEntries = errorImplementation.entries;

    var errorReferenceIdx = findEntry(errorEntries, id + '-error-reference');

    collapsibleEntry = errorEntries[findEntry(errorEntries, id + '-collapsible')];

    // (2) replace validated expression entry by a simple, disabled entry
    var expressionIdx = findEntry(errorEntries, id + '-error-expression');
    removeEntry(errorEntries, expressionIdx);

    var expressionEntry = entryFactory.textField(translate, {
      id: id + '-error-expression',
      label: translate('Throw Expression'),
      modelProperty: 'expression',

      get: function() {
        return { expression: getError().expression };
      },

      buttonShow: {
        method: function() {
          return false;
        }
      },

      hidden: function() {
        return !isOpen();
      },
      disabled: function() {
        return true;
      }
    });

    errorEntries.splice(expressionIdx, 0, expressionEntry);

    // (3) remove error selection
    removeEntry(errorEntries, errorReferenceIdx);

    return errorEntries;
  }

  // filter specific errors from template
  var errors = filter(template.properties, function(p) {
    return !p.type && p.binding.type === CAMUNDA_ERROR_EVENT_DEFINITION_TYPE;
  });

  forEach(errors, function(property, idx) {
    var id = 'template-errors-' + template.id + '-' + idx;
    errorEntries.push(renderError(id, property));
  });

  group.entries = group.entries.concat(flatten(errorEntries));
};


// helper //////////////////////////

function findEntry(entries, id) {
  return findIndex(entries, function(entry) {
    return entry.id === id;
  });
}

function removeEntry(entries, idx) {
  entries.splice(idx, 1);
}

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/Helper.js":
/*!******************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/Helper.js ***!
  \******************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var find = __webpack_require__(/*! lodash/find */ "./node_modules/lodash/find.js"),
    isUndefined = __webpack_require__(/*! lodash/isUndefined */ "./node_modules/lodash/isUndefined.js");

var isAny = __webpack_require__(/*! bpmn-js/lib/features/modeling/util/ModelingUtil */ "./node_modules/bpmn-js/lib/features/modeling/util/ModelingUtil.js").isAny;

var ELEMENT_TEMPLATE_OPTION_EMPTY = 'element-template-option-empty';

module.exports.ELEMENT_TEMPLATE_OPTION_EMPTY = ELEMENT_TEMPLATE_OPTION_EMPTY;

var emptyOption = {
  name: '',
  value: ELEMENT_TEMPLATE_OPTION_EMPTY
};

/**
 * Get options for given element and templates.
 *
 * @param {dj.model.Base} element
 * @param {ElementTemplates} elementTemplates
 * @param {Translate} translate
 */
function getOptions(element, elementTemplates, translate) {
  var options = [ emptyOption ];

  var defaultTemplate = elementTemplates.getDefault(element);

  if (defaultTemplate) {
    options.push(option('element-template-option-0', defaultTemplate, translate));

    return options;
  }

  var index = 0;

  elementTemplates.getAll().forEach(function(template) {
    if (!isAny(element, template.appliesTo)) {
      return;
    }

    options.push(option('element-template-option-' + index, template, translate));

    index++;
  });

  return options;
}

module.exports.getOptions = getOptions;

function getOption(optionId, options) {
  return find(options, function(option) {
    return optionId === option.value;
  });
}

module.exports.getOption = getOption;

function option(value, template, translate) {
  var name = translate(template.name);

  if (template.version) {
    name += ' (v' + template.version + ')';
  }

  var option = {
    id: template.id,
    name: name,
    value: value
  };

  if (template.version) {
    option.version = template.version;
  }

  return option;
}

module.exports.getVersionOrDateFromTemplate = function(template) {
  var metadata = template.metadata,
      version = template.version;

  if (metadata) {
    if (!isUndefined(metadata.created)) {
      return 'Version ' + toDateString(metadata.created);
    } else if (!isUndefined(metadata.updated)) {
      return 'Version ' + toDateString(metadata.updated);
    }
  }

  if (isUndefined(version)) {
    return null;
  }

  return 'Version ' + version;
};

function toDateString(timestamp) {
  var date = new Date(timestamp);

  var year = date.getFullYear();

  var month = leftPad(String(date.getMonth() + 1), 2, '0');

  var day = leftPad(String(date.getDate()), 2, '0');

  return day + '.' + month + '.' + year;
}

function leftPad(string, length, character) {
  while (string.length < length) {
    string = character + string;
  }

  return string;
}

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/InputParametersProps.js":
/*!********************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/InputParametersProps.js ***!
  \********************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var assign = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").assign,
    flatten = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").flatten,
    filter = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").filter,
    findIndex = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").findIndex,
    forEach = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").forEach;

var domClasses = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").classes,
    domEvent = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").event,
    domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify,
    domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var findExtension = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findExtension,
    findInputParameter = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findInputParameter;

var createInputParameter = __webpack_require__(/*! ../CreateHelper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js").createInputParameter;

var escapeHTML = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js");

var InputOutputParameter = __webpack_require__(/*! ../../parts/implementation/InputOutputParameter */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/InputOutputParameter.js");

var CAMUNDA_INPUT_PARAMETER_TYPE = 'camunda:inputParameter';

var MAX_DESCRIPTION_LENGTH = 200;

var EMPTY_PARAMETER = {
  get: function() {},
  set: function() {}
};


/**
 * Injects element template input parameters into the given group.
 *
 * @param {Object} group
 * @param {djs.model.Base} element
 * @param {ElementTemplates} elementTemplates
 * @param {BpmnFactory} bpmnFactory
 * @param {Function} translate
 */
module.exports = function(group, element, elementTemplates, bpmnFactory, translate) {

  var template = elementTemplates.get(element);

  if (!template) {
    return [];
  }

  var inputEntries = [];


  function onToggle(value, entryNode) {
    if (!value) {
      return;
    }

    var currentEntryId = entryNode.dataset.entry;

    // collapse all other items
    inputEntries.forEach(function(entries) {
      var collapsible = entries[0];

      if (collapsible.id === currentEntryId) {
        return;
      }

      var entryNode = domQuery('[data-entry="' + collapsible.id + '"]');
      collapsible.setOpen(false, entryNode);
    });
  }

  function renderInputParameter(id, templateProperty) {

    var parameterEntries = [],
        collapsibleEntry;

    var bo = getBusinessObject(element),
        inputOutput = findExtension(bo, 'camunda:InputOutput');

    if (!inputOutput) {
      return parameterEntries;
    }

    var getParameter = function() {
      return findInputParameter(inputOutput, templateProperty.binding) || EMPTY_PARAMETER;
    };

    var parameter = getParameter();

    var isOpen = function() {
      return collapsibleEntry.isOpen();
    };

    var assignmentIsOn = function() {
      var inputOutput = findExtension(getBusinessObject(element), 'camunda:InputOutput'),
          parameter = findInputParameter(inputOutput, templateProperty.binding);

      return !!parameter;
    };

    var options = {
      idPrefix: id + '-',
      onToggle: onToggle,
      getParameter: getParameter,
      isOpen: function() {
        return isOpen() && assignmentIsOn();
      }
    };


    // (1) use input parameter implementation
    var inputImplementation = InputOutputParameter(parameter, bpmnFactory, options, translate);
    parameterEntries = inputImplementation.entries;

    var nameIdx = findEntry(parameterEntries, id + '-parameterName');

    collapsibleEntry = parameterEntries[findEntry(parameterEntries, id + '-collapsible')];

    // (2) update title getter
    var defaultGet = collapsibleEntry.get;
    collapsibleEntry.get = function() {
      return assign(defaultGet(), {
        title: templateProperty.label ?
          translate(templateProperty.label) :
          templateProperty.binding.name
      });
    };

    // (3) remove name property entry
    removeEntry(parameterEntries, nameIdx);

    // (4) add description entry
    if (templateProperty.description) {
      parameterEntries.splice(1, 0, createDescriptionEntry(
        templateProperty.description,
        id,
        collapsibleEntry.isOpen,
        translate
      ));
    }

    // (5) add parameter toggle
    parameterEntries.splice(templateProperty.description ? 2 : 1, 0, entryFactory.toggleSwitch(translate, {
      id: id + '-assignment-toggle',
      label: translate('Local Variable Assignment'),
      modelProperty: 'isActive',
      labelOn: translate('On'),
      labelOff: translate('Off'),
      descriptionOff: translate('The parameter won\'t be created as local variable.'),
      isOn: assignmentIsOn,
      get: function(element, node) {
        return { isActive: assignmentIsOn() };
      },
      set: function(element, values, node) {
        var isActive = values.isActive || false;

        if (isActive) {
          return createNewInputParameter(element, templateProperty.binding, bpmnFactory);
        } else {
          return removeInputParameter(element, templateProperty.binding);
        }

      },
      hidden: function(element, node) {
        return !isOpen();
      }
    }));

    return parameterEntries;
  }


  // filter specific input parameters from template
  var inputParameters = filter(template.properties, function(p) {
    return !p.type && p.binding.type === CAMUNDA_INPUT_PARAMETER_TYPE;
  });

  forEach(inputParameters, function(property, idx) {
    var id = 'template-inputs-' + template.id + '-' + idx;
    inputEntries.push(renderInputParameter(id, property));
  });

  group.entries = group.entries.concat(flatten(inputEntries));
};


// helper ///////////////////////

function findEntry(entries, id) {
  return findIndex(entries, function(entry) {
    return entry.id === id;
  });
}

function removeEntry(entries, idx) {
  entries.splice(idx, 1);
}

function createDescriptionEntry(description, id, show, translate) {
  description = escapeHTML(description);

  var html = domify('<p class="description description--expanded" data-show="show"></p>');

  var descriptionText = domify('<span class="description__text">' + description + '</span>');

  html.appendChild(descriptionText);

  function toggleExpanded(expanded) {
    if (expanded) {
      domClasses(html).add('description--expanded');

      descriptionText.textContent = description + ' ';

      expand.textContent = translate('Less');
    } else {
      domClasses(html).remove('description--expanded');

      descriptionText.textContent = descriptionShortened + ' ... ';

      expand.textContent = translate('More');
    }
  }

  var descriptionShortened,
      expand,
      expanded = false;

  if (description.length > MAX_DESCRIPTION_LENGTH) {
    descriptionShortened = description.slice(0, MAX_DESCRIPTION_LENGTH);

    expand = domify(
      '<span class="bpp-entry-link description__expand">' +
        translate('More') +
      '</span>'
    );

    domEvent.bind(expand, 'click', function() {
      expanded = !expanded;

      toggleExpanded(expanded);
    });

    html.appendChild(expand);

    toggleExpanded(expanded);
  }

  return {
    id: id + '-description',
    html: html,
    show: show
  };
}

function removeInputParameter(element, binding) {
  var bo = getBusinessObject(element),
      updates = [],
      extensionElements = bo.get('extensionElements');

  if (!extensionElements) {
    return updates;
  }

  var inputOutput = findExtension(extensionElements, 'camunda:InputOutput');

  if (!inputOutput) {
    return updates;
  }

  var inputParameter = findInputParameter(inputOutput, binding);

  if (!inputParameter) {
    return updates;
  }

  updates.push(cmdHelper.removeElementsFromList(element, inputOutput, 'inputParameters', null, [inputParameter]));

  return updates;
}

function createNewInputParameter(element, binding, bpmnFactory) {
  var bo = getBusinessObject(element),
      updates = [],
      extensionElements = bo.get('extensionElements');

  // (1) ensure extension elements
  if (!extensionElements) {
    extensionElements = elementHelper.createElement('bpmn:ExtensionElements', null, element, bpmnFactory);

    updates.push(cmdHelper.updateBusinessObject(
      element, bo, { extensionElements: extensionElements }
    ));
  }

  var inputOutput = findExtension(extensionElements, 'camunda:InputOutput');

  // (2) ensure inputOutput element
  if (!inputOutput) {
    inputOutput = elementHelper.createElement('camunda:InputOutput', null, bo, bpmnFactory);

    updates.push(cmdHelper.addElementsTolist(
      element, extensionElements, 'values', inputOutput
    ));
  }

  // (3) create input parameter
  var inputParameter = createInputParameter(binding, null, bpmnFactory);

  updates.push(cmdHelper.addAndRemoveElementsFromList(
    element,
    inputOutput,
    'inputParameters',
    null,
    [ inputParameter ],
    [ ]
  ));

  return updates;
}




/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/OutputParametersProps.js":
/*!*********************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/parts/OutputParametersProps.js ***!
  \*********************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var flatten = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").flatten,
    filter = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").filter,
    forEach = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").forEach;

var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js");

var findExtension = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findExtension,
    findOutputParameter = __webpack_require__(/*! ../Helper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/Helper.js").findOutputParameter;

var createOutputParameter = __webpack_require__(/*! ../CreateHelper */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/CreateHelper.js").createOutputParameter;

var domClasses = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").classes,
    domEvent = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").event,
    domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify,
    domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

var escapeHTML = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var utils = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js");

var CAMUNDA_OUTPUT_PARAMETER_TYPE = 'camunda:outputParameter';

var MAX_DESCRIPTION_LENGTH = 200;

var EMPTY_PARAMETER = {
  get: function() {},
  set: function() {}
};

/**
 * Injects element template output parameters into the given group.
 *
 * @param {Object} group
 * @param {djs.model.Base} element
 * @param {ElementTemplates} elementTemplates
 * @param {BpmnFactory} bpmnFactory
 * @param {Function} translate
 */
module.exports = function(group, element, elementTemplates, bpmnFactory, translate) {

  var template = elementTemplates.get(element);

  if (!template) {
    return [];
  }

  var outputEntries = [];


  function onToggle(value, entryNode) {
    if (!value) {
      return;
    }

    var currentEntryId = entryNode.dataset.entry;

    // collapse all other items
    outputEntries.forEach(function(entries) {
      var collapsible = entries[0];

      if (collapsible.id === currentEntryId) {
        return;
      }

      var entryNode = domQuery('[data-entry="' + collapsible.id + '"]');
      collapsible.setOpen(false, entryNode);
    });
  }

  function renderOutputParameter(id, templateProperty) {

    var parameterEntries = [];

    // find input parameter first
    var bo = getBusinessObject(element),
        inputOutput = findExtension(bo, 'camunda:InputOutput');

    if (!inputOutput) {
      return parameterEntries;
    }

    var getParameter = function() {
      return findOutputParameter(inputOutput, templateProperty.binding) || EMPTY_PARAMETER;
    };

    // (1) add collapsible header
    var collapsible = entryFactory.collapsible({
      id: id + '-collapsible',
      title: translate(templateProperty.label),
      cssClasses: [
        'bpp-collapsible--with-mapping',
        'bpp-collapsible--with-template-out'
      ],
      onToggle: onToggle,
      open: false,
      get: function() {
        return {
          title: translate(templateProperty.label),
          description: getParameter().name
        };
      }
    });
    parameterEntries.push(collapsible);

    var isOpen = collapsible.isOpen;

    var assignmentIsOn = function() {
      var inputOutput = findExtension(getBusinessObject(element), 'camunda:InputOutput'),
          parameter = findOutputParameter(inputOutput, templateProperty.binding);

      return !!parameter;
    };

    // (2) add description
    if (templateProperty.description) {
      parameterEntries.push(createDescriptionEntry(
        templateProperty.description,
        id,
        isOpen,
        translate
      ));
    }

    // (3) add parameter toggle
    parameterEntries.splice(templateProperty.description ? 2 : 1, 0, entryFactory.toggleSwitch(translate, {
      id: id + '-assignment-toggle',
      label: translate('Process Variable Assignment'),
      modelProperty: 'isActive',
      labelOn: translate('On'),
      labelOff: translate('Off'),
      descriptionOff: translate('The parameter won\'t be available in the process scope.'),
      isOn: assignmentIsOn,
      get: function(element, node) {
        return { isActive: assignmentIsOn() };
      },
      set: function(element, values, node) {
        var isActive = values.isActive || false;

        if (isActive) {
          return createNewOutputParameter(element, templateProperty.binding, bpmnFactory);
        } else {
          return removeOutputParameter(element, templateProperty.binding);
        }

      },
      hidden: function(element, node) {
        return !isOpen();
      }
    }));

    // (4) add process variable name field
    parameterEntries.push(entryFactory.validationAwareTextField(translate, {
      id: id + '-variableName',
      label: translate('Assign to Process Variable'),
      modelProperty: 'variableName',
      getProperty: function(element) {
        return getParameter().name;
      },
      setProperty: function(element, values) {
        return cmdHelper.updateBusinessObject(element, getParameter(), { name: values.variableName });
      },
      validate: function(element, values) {
        var validation = {},
            nameValue = values.variableName;

        if (nameValue) {
          if (utils.containsSpace(nameValue)) {
            validation.variableName = translate('Process Variable Name must not contain spaces.');
          }
        } else {
          validation.variableName = translate('Process Variable Name must not be empty.');
        }

        return validation;
      },
      hidden: function(element, node) {
        return !isOpen() || !assignmentIsOn();
      }
    }));

    return parameterEntries;
  }

  // filter specific output parameters from template
  var outputParameters = filter(template.properties, function(p) {
    return !p.type && p.binding.type === CAMUNDA_OUTPUT_PARAMETER_TYPE;
  });

  forEach(outputParameters, function(property, idx) {
    var id = 'template-outputs-' + template.id + '-' + idx;
    outputEntries.push(renderOutputParameter(id, property));
  });

  group.entries = group.entries.concat(flatten(outputEntries));
};

// helpers ///////////////

function createDescriptionEntry(description, id, show, translate) {
  description = escapeHTML(description);

  var html = domify('<p class="description description--expanded" data-show="show"></p>');

  var descriptionText = domify('<span class="description__text">' + description + '</span>');

  html.appendChild(descriptionText);

  function toggleExpanded(expanded) {
    if (expanded) {
      domClasses(html).add('description--expanded');

      descriptionText.textContent = description + ' ';

      expand.textContent = translate('Less');
    } else {
      domClasses(html).remove('description--expanded');

      descriptionText.textContent = descriptionShortened + ' ... ';

      expand.textContent = translate('More');
    }
  }

  var descriptionShortened,
      expand,
      expanded = false;

  if (description.length > MAX_DESCRIPTION_LENGTH) {
    descriptionShortened = description.slice(0, MAX_DESCRIPTION_LENGTH);

    expand = domify(
      '<span class="bpp-entry-link description__expand">' +
        translate('More') +
      '</span>'
    );

    domEvent.bind(expand, 'click', function() {
      expanded = !expanded;

      toggleExpanded(expanded);
    });

    html.appendChild(expand);

    toggleExpanded(expanded);
  }

  return {
    id: id + '-description',
    html: html,
    show: show
  };
}

function removeOutputParameter(element, binding) {
  var bo = getBusinessObject(element),
      updates = [],
      extensionElements = bo.get('extensionElements');

  if (!extensionElements) {
    return updates;
  }

  var inputOutput = findExtension(extensionElements, 'camunda:InputOutput');

  if (!inputOutput) {
    return updates;
  }

  var outputParameter = findOutputParameter(inputOutput, binding);

  if (!outputParameter) {
    return updates;
  }

  updates.push(cmdHelper.removeElementsFromList(element, inputOutput, 'outputParameters', null, [outputParameter]));

  return updates;
}

function createNewOutputParameter(element, binding, bpmnFactory) {
  var bo = getBusinessObject(element),
      updates = [],
      extensionElements = bo.get('extensionElements');

  // (1) ensure extension elements
  if (!extensionElements) {
    extensionElements = elementHelper.createElement('bpmn:ExtensionElements', null, element, bpmnFactory);

    updates.push(cmdHelper.updateBusinessObject(
      element, bo, { extensionElements: extensionElements }
    ));
  }

  var inputOutput = findExtension(extensionElements, 'camunda:InputOutput');

  // (2) ensure inputOutput element
  if (!inputOutput) {
    inputOutput = elementHelper.createElement('camunda:InputOutput', null, bo, bpmnFactory);

    updates.push(cmdHelper.addElementsTolist(
      element, extensionElements, 'values', inputOutput
    ));
  }

  // (3) create output parameter
  var outputParameter = createOutputParameter(binding, null, bpmnFactory);

  updates.push(cmdHelper.addAndRemoveElementsFromList(
    element,
    inputOutput,
    'outputParameters',
    null,
    [ outputParameter ],
    [ ]
  ));

  return updates;
}

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/util/handleLegacyScopes.js":
/*!*****************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/element-templates/util/handleLegacyScopes.js ***!
  \*****************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var assign = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").assign,
    forEach = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").forEach,
    keys = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").keys,
    isObject = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").isObject;

/**
 * Converts legacy scopes descriptor to newer supported array structure.
 *
 * For example, it transforms
 *
 * scopes: {
 *   'camunda:Connector':
 *     { properties: []
 *   }
 * }
 *
 * to
 *
 * scopes: [
 *   {
 *     type: 'camunda:Connector',
 *     properties: []
 *   }
 * ]
 *
 * @param {ScopesDescriptor} scopes
 *
 * @returns {Array}
 */
module.exports = function handleLegacyScopes(scopes) {
  var scopesAsArray = [];

  if (!isObject(scopes)) {
    return scopes;
  }

  forEach(keys(scopes), function(scopeName) {
    scopesAsArray.push(assign({
      type: scopeName
    }, scopes[scopeName]));
  });

  return scopesAsArray;
};

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/AsynchronousContinuationProps.js":
/*!***********************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/AsynchronousContinuationProps.js ***!
  \***********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    asyncContinuation = __webpack_require__(/*! ./implementation/AsyncContinuation */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/AsyncContinuation.js");

module.exports = function(group, element, bpmnFactory, translate) {

  if (is(element, 'camunda:AsyncCapable')) {

    group.entries = group.entries.concat(asyncContinuation(element, bpmnFactory, {
      getBusinessObject: getBusinessObject
    }, translate));

  }
};

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/CallActivityProps.js":
/*!***********************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/CallActivityProps.js ***!
  \***********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is;

var entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var callable = __webpack_require__(/*! ./implementation/Callable */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Callable.js");

var cmdHelper = __webpack_require__(/*! ../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

var flattenDeep = __webpack_require__(/*! lodash/flattenDeep */ "./node_modules/lodash/flattenDeep.js");
var assign = __webpack_require__(/*! lodash/assign */ "./node_modules/lodash/assign.js");

function getCallableType(element) {
  var bo = getBusinessObject(element);

  var boCalledElement = bo.get('calledElement'),
      boCaseRef = bo.get('camunda:caseRef');

  var callActivityType = '';
  if (typeof boCalledElement !== 'undefined') {
    callActivityType = 'bpmn';
  } else

  if (typeof boCaseRef !== 'undefined') {
    callActivityType = 'cmmn';
  }

  return callActivityType;
}

var DEFAULT_PROPS = {
  calledElement: undefined,
  'camunda:calledElementBinding': 'latest',
  'camunda:calledElementVersion': undefined,
  'camunda:calledElementTenantId': undefined,
  'camunda:variableMappingClass' : undefined,
  'camunda:variableMappingDelegateExpression' : undefined,
  'camunda:caseRef': undefined,
  'camunda:caseBinding': 'latest',
  'camunda:caseVersion': undefined,
  'camunda:caseTenantId': undefined
};

module.exports = function(group, element, bpmnFactory, translate) {

  if (!is(element, 'camunda:CallActivity')) {
    return;
  }

  group.entries.push(entryFactory.selectBox(translate, {
    id : 'callActivity',
    label: translate('CallActivity Type'),
    selectOptions: [
      { name: 'BPMN', value: 'bpmn' },
      { name: 'CMMN', value: 'cmmn' }
    ],
    emptyParameter: true,
    modelProperty: 'callActivityType',

    get: function(element, node) {
      return {
        callActivityType: getCallableType(element)
      };
    },

    set: function(element, values, node) {
      var type = values.callActivityType;

      var props = assign({}, DEFAULT_PROPS);

      if (type === 'bpmn') {
        props.calledElement = '';
      }
      else if (type === 'cmmn') {
        props['camunda:caseRef'] = '';
      }

      return cmdHelper.updateProperties(element, props);
    }

  }));

  group.entries.push(callable(element, bpmnFactory, {
    getCallableType: getCallableType
  }, translate));

  group.entries = flattenDeep(group.entries);
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/CandidateStarterProps.js":
/*!***************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/CandidateStarterProps.js ***!
  \***************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var candidateStarter = __webpack_require__(/*! ./implementation/CandidateStarter */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/CandidateStarter.js");

module.exports = function(group, element, bpmnFactory, translate) {
  var businessObject = getBusinessObject(element);

  if (is(element, 'camunda:Process') ||
      is(element, 'bpmn:Participant') && businessObject.get('processRef')) {

    group.entries = group.entries.concat(candidateStarter(element, bpmnFactory, {
      getBusinessObject: function(element) {
        var bo = getBusinessObject(element);

        if (!is(bo, 'bpmn:Participant')) {
          return bo;
        }

        return bo.get('processRef');
      }
    }, translate));

  }
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ConditionalProps.js":
/*!**********************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ConditionalProps.js ***!
  \**********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    isAny = __webpack_require__(/*! bpmn-js/lib/features/modeling/util/ModelingUtil */ "./node_modules/bpmn-js/lib/features/modeling/util/ModelingUtil.js").isAny,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    escapeHTML = __webpack_require__(/*! ../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML,
    domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query,
    cmdHelper = __webpack_require__(/*! ../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    elementHelper = __webpack_require__(/*! ../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    eventDefinitionHelper = __webpack_require__(/*! ../../../helper/EventDefinitionHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/EventDefinitionHelper.js"),
    scriptImplementation = __webpack_require__(/*! ./implementation/Script */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Script.js");


module.exports = function(group, element, bpmnFactory, translate) {
  var bo = getBusinessObject(element);

  if (!bo) {
    return;
  }

  var conditionalEventDefinition = eventDefinitionHelper.getConditionalEventDefinition(element);

  if (!(is(element, 'bpmn:SequenceFlow') && isConditionalSource(element.source))
    && !conditionalEventDefinition) {
    return;
  }

  var script = scriptImplementation('language', 'body', true, translate);
  group.entries.push({
    id: 'condition',
    label: translate('Condition'),
    html: '<div class="bpp-row">' +
              '<label for="cam-condition-type">'+ escapeHTML(translate('Condition Type')) + '</label>' +
              '<div class="bpp-field-wrapper">' +
                '<select id="cam-condition-type" name="conditionType" data-value>' +
                  '<option value="expression">'+ escapeHTML(translate('Expression')) + '</option>' +
                  '<option value="script">'+ escapeHTML(translate('Script')) + '</option>' +
                  '<option value="" selected></option>' +
                '</select>' +
              '</div>' +
            '</div>' +

            // expression
            '<div class="bpp-row">' +
              '<label for="cam-condition" data-show="isExpression">' + escapeHTML(translate('Expression')) + '</label>' +
              '<div class="bpp-field-wrapper" data-show="isExpression">' +
                '<input id="cam-condition" type="text" name="condition" />' +
                '<button class="action-button clear" data-action="clear" data-show="canClear">' +
                  '<span>X</span>' +
                '</button>' +
              '</div>' +
              '<div data-show="isScript">' +
                script.template +
              '</div>' +
            '</div>',

    get: function(element, propertyName) {
      var conditionalEventDefinition = eventDefinitionHelper.getConditionalEventDefinition(element);

      var conditionExpression = conditionalEventDefinition
        ? conditionalEventDefinition.condition
        : bo.conditionExpression;

      var values = {},
          conditionType = '';

      if (conditionExpression) {
        var conditionLanguage = conditionExpression.language;
        if (typeof conditionLanguage !== 'undefined') {
          conditionType = 'script';
          values = script.get(element, conditionExpression);
        } else {
          conditionType = 'expression';
          values.condition = conditionExpression.get('body');
        }
      }

      values.conditionType = conditionType;

      return values;

    },

    set: function(element, values, containerElement) {
      var conditionType = values.conditionType;
      var commands = [];

      var conditionProps = {
        body: undefined
      };

      if (conditionType === 'script') {
        conditionProps = script.set(element, values, containerElement);
      } else {
        var condition = values.condition;

        conditionProps.body = condition;
      }

      var conditionOrConditionExpression;

      if (conditionType) {
        conditionOrConditionExpression = elementHelper.createElement(
          'bpmn:FormalExpression',
          conditionProps,
          conditionalEventDefinition || bo,
          bpmnFactory
        );

        var source = element.source;

        // if default-flow, remove default-property from source
        if (source && source.businessObject.default === bo) {
          commands.push(cmdHelper.updateProperties(source, { 'default': undefined }));
        }
      }

      var update = conditionalEventDefinition
        ? { condition: conditionOrConditionExpression }
        : { conditionExpression: conditionOrConditionExpression };

      commands.push(cmdHelper.updateBusinessObject(element, conditionalEventDefinition || bo, update));

      return commands;
    },

    validate: function(element, values) {
      var validationResult = {};

      if (!values.condition && values.conditionType === 'expression') {
        validationResult.condition = translate('Must provide a value');
      }
      else if (values.conditionType === 'script') {
        validationResult = script.validate(element, values);
      }

      return validationResult;
    },

    isExpression: function(element, inputNode) {
      var conditionType = domQuery('select[name=conditionType]', inputNode);
      if (conditionType.selectedIndex >= 0) {
        return conditionType.options[conditionType.selectedIndex].value === 'expression';
      }
    },

    isScript: function(element, inputNode) {
      var conditionType = domQuery('select[name=conditionType]', inputNode);
      if (conditionType.selectedIndex >= 0) {
        return conditionType.options[conditionType.selectedIndex].value === 'script';
      }
    },

    clear: function(element, inputNode) {

      // clear text input
      domQuery('input[name=condition]', inputNode).value='';

      return true;
    },

    canClear: function(element, inputNode) {
      var input = domQuery('input[name=condition]', inputNode);

      return input.value !== '';
    },

    script : script,

    cssClasses: [ 'bpp-textfield' ]
  });
};


// utilities //////////////////////////

var CONDITIONAL_SOURCES = [
  'bpmn:Activity',
  'bpmn:ExclusiveGateway',
  'bpmn:InclusiveGateway',
  'bpmn:ComplexGateway'
];

function isConditionalSource(element) {
  return isAny(element, CONDITIONAL_SOURCES);
}


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ConnectorDetailProps.js":
/*!**************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ConnectorDetailProps.js ***!
  \**************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var ImplementationTypeHelper = __webpack_require__(/*! ../../../helper/ImplementationTypeHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ImplementationTypeHelper.js"),
    InputOutputHelper = __webpack_require__(/*! ../../../helper/InputOutputHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/InputOutputHelper.js");

var entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    cmdHelper = __webpack_require__(/*! ../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

function getImplementationType(element) {
  return ImplementationTypeHelper.getImplementationType(element);
}

function getBusinessObject(element) {
  return ImplementationTypeHelper.getServiceTaskLikeBusinessObject(element);
}

function getConnector(bo) {
  return InputOutputHelper.getConnector(bo);
}

function isConnector(element) {
  return getImplementationType(element) === 'connector';
}

module.exports = function(group, element, bpmnFactory, translate) {

  group.entries.push(entryFactory.textField(translate, {
    id: 'connectorId',
    label: translate('Connector Id'),
    modelProperty: 'connectorId',

    get: function(element, node) {
      var bo = getBusinessObject(element);
      var connector = bo && getConnector(bo);
      var value = connector && connector.get('connectorId');
      return { connectorId: value };
    },

    set: function(element, values, node) {
      var bo = getBusinessObject(element);
      var connector = getConnector(bo);
      return cmdHelper.updateBusinessObject(element, connector, {
        connectorId: values.connectorId || undefined
      });
    },

    validate: function(element, values, node) {
      return isConnector(element) && !values.connectorId ? { connectorId: translate('Must provide a value') } : {};
    },

    hidden: function(element, node) {
      return !isConnector(element);
    }

  }));

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ConnectorInputParametersProps.js":
/*!***********************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ConnectorInputParametersProps.js ***!
  \***********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var inputParameters = __webpack_require__(/*! ./implementation/InputParameters */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/InputParameters.js");

module.exports = function(group, element, bpmnFactory, translate) {

  var inputParametersEntry = inputParameters(element, bpmnFactory, {
    idPrefix: 'connector-',
    insideConnector: true
  }, translate);

  group.entries = group.entries.concat(inputParametersEntry.entries);
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ConnectorOutputParametersProps.js":
/*!************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ConnectorOutputParametersProps.js ***!
  \************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var outputParameters = __webpack_require__(/*! ./implementation/OutputParameters */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/OutputParameters.js");

module.exports = function(group, element, bpmnFactory, translate) {

  var outputParametersEntry = outputParameters(element, bpmnFactory, {
    idPrefix: 'connector-',
    insideConnector: true
  }, translate);

  group.entries = group.entries.concat(outputParametersEntry.entries);
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ErrorEventProps.js":
/*!*********************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ErrorEventProps.js ***!
  \*********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    eventDefinitionHelper = __webpack_require__(/*! ../../../helper/EventDefinitionHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/EventDefinitionHelper.js"),
    error = __webpack_require__(/*! ./implementation/ErrorEventDefinition */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ErrorEventDefinition.js");

var forEach = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js");


module.exports = function(group, element, bpmnFactory, translate) {

  var errorEvents = [
    'bpmn:StartEvent',
    'bpmn:BoundaryEvent',
    'bpmn:EndEvent'
  ];

  forEach(errorEvents, function(event) {
    if (is(element, event)) {

      var errorEventDefinition = eventDefinitionHelper.getErrorEventDefinition(element);

      if (errorEventDefinition) {
        var isCatchingErrorEvent = is(element, 'bpmn:StartEvent') || is (element, 'bpmn:BoundaryEvent');

        var showErrorCodeVariable = isCatchingErrorEvent,
            showErrorMessageVariable = isCatchingErrorEvent;

        error(
          group,
          element,
          bpmnFactory,
          errorEventDefinition,
          showErrorCodeVariable,
          showErrorMessageVariable,
          translate);
      }
    }
  });
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ErrorsProps.js":
/*!*****************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ErrorsProps.js ***!
  \*****************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var errors = __webpack_require__(/*! ./implementation/Errors */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Errors.js");

module.exports = function(group, element, bpmnFactory, elementTemplates, translate) {

  var template = elementTemplates.get(element);

  if (template) {
    return;
  }

  var errorsEntry = errors(element, bpmnFactory, {}, translate);

  group.entries = group.entries.concat(errorsEntry.entries);
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ExternalTaskConfigurationProps.js":
/*!************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ExternalTaskConfigurationProps.js ***!
  \************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var ImplementationTypeHelper = __webpack_require__(/*! ../../../helper/ImplementationTypeHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ImplementationTypeHelper.js");

var externalTaskPriority = __webpack_require__(/*! ./implementation/ExternalTaskPriority */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ExternalTaskPriority.js");

function getServiceTaskLikeBusinessObject(element) {
  var bo = ImplementationTypeHelper.getServiceTaskLikeBusinessObject(element);

  // if the element is not a serviceTaskLike element, fetch the normal business object
  // This avoids the loss of the process / participant business object
  if (!bo) {
    bo = getBusinessObject(element);
  }

  return bo;
}

module.exports = function(group, element, bpmnFactory, translate) {

  var bo = getServiceTaskLikeBusinessObject(element);

  if (!bo) {
    return;
  }

  if (is(bo, 'camunda:TaskPriorized') || (is(bo, 'bpmn:Participant')) && bo.get('processRef')) {
    group.entries = group.entries.concat(externalTaskPriority(element, bpmnFactory, {
      getBusinessObject: function(element) {
        if (!is(bo, 'bpmn:Participant')) {
          return bo;
        }
        return bo.get('processRef');
      }
    }, translate));
  }
};

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/FieldInjectionProps.js":
/*!*************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/FieldInjectionProps.js ***!
  \*************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var ImplementationTypeHelper = __webpack_require__(/*! ../../../helper/ImplementationTypeHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ImplementationTypeHelper.js");

var fieldInjection = __webpack_require__(/*! ./implementation/FieldInjection */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/FieldInjection.js");

module.exports = function(group, element, bpmnFactory, translate) {

  var bo = ImplementationTypeHelper.getServiceTaskLikeBusinessObject(element);

  if (!bo) {
    return;
  }

  var fieldInjectionEntry = fieldInjection(element, bpmnFactory, translate, { businessObject: bo });

  if (fieldInjectionEntry && fieldInjectionEntry.length > 0) {
    group.entries = group.entries.concat(fieldInjectionEntry);
  }

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/FormProps.js":
/*!***************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/FormProps.js ***!
  \***************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    getExtensionElements = __webpack_require__(/*! ../../../helper/ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js").getExtensionElements,
    removeEntry = __webpack_require__(/*! ../../../helper/ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js").removeEntry,
    extensionElements = __webpack_require__(/*! ./implementation/ExtensionElements */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ExtensionElements.js"),
    properties = __webpack_require__(/*! ./implementation/Properties */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Properties.js"),
    entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    elementHelper = __webpack_require__(/*! ../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    formHelper = __webpack_require__(/*! ../../../helper/FormHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/FormHelper.js"),
    utils = __webpack_require__(/*! ../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js"),
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    find = __webpack_require__(/*! lodash/find */ "./node_modules/lodash/find.js"),
    each = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js");

function generateValueId() {
  return utils.nextId('Value_');
}

/**
 * Generate a form field specific textField using entryFactory.
 *
 * @param {Function} translate
 * @param {string} options.id
 * @param {string} options.label
 * @param {string} options.modelProperty
 * @param {Function} options.validate
 *
 * @return {Object} an entryFactory.textField object
 */
function formFieldTextField(translate, options, getSelectedFormField) {

  var id = options.id,
      label = options.label,
      modelProperty = options.modelProperty,
      validate = options.validate;

  return entryFactory.textField(translate, {
    id: id,
    label: label,
    modelProperty: modelProperty,
    get: function(element, node) {
      var selectedFormField = getSelectedFormField(element, node) || {},
          values = {};

      values[modelProperty] = selectedFormField[modelProperty];

      return values;
    },

    set: function(element, values, node) {
      var commands = [];

      if (typeof options.set === 'function') {
        var cmd = options.set(element, values, node);

        if (cmd) {
          commands.push(cmd);
        }
      }

      var formField = getSelectedFormField(element, node),
          properties = {};

      properties[modelProperty] = values[modelProperty] || undefined;

      commands.push(cmdHelper.updateBusinessObject(element, formField, properties));

      return commands;
    },
    hidden: function(element, node) {
      return !getSelectedFormField(element, node);
    },
    validate: validate
  });
}

function ensureFormKeyAndDataSupported(element) {
  return (
    is(element, 'bpmn:StartEvent') && !is(element.parent, 'bpmn:SubProcess')
  ) || is(element, 'bpmn:UserTask');
}

module.exports = function(group, element, bpmnFactory, translate) {

  if (!ensureFormKeyAndDataSupported(element)) {
    return;
  }


  /**
   * Return the currently selected form field querying the form field select box
   * from the DOM.
   *
   * @param  {djs.model.Base} element
   * @param  {DOMElement} node - DOM element of any form field text input
   *
   * @return {ModdleElement} the currently selected form field
   */
  function getSelectedFormField(element, node) {
    var selected = formFieldsEntry.getSelected(element, node.parentNode);

    if (selected.idx === -1) {
      return;
    }

    return formHelper.getFormField(element, selected.idx);
  }

  // [FormKey] form key text input field
  group.entries.push(entryFactory.textField(translate, {
    id : 'form-key',
    label : translate('Form Key'),
    modelProperty: 'formKey',
    get: function(element, node) {
      var bo = getBusinessObject(element);

      return {
        formKey: bo.get('camunda:formKey')
      };
    },
    set: function(element, values, node) {
      var bo = getBusinessObject(element),
          formKey = values.formKey || undefined;

      return cmdHelper.updateBusinessObject(element, bo, { 'camunda:formKey': formKey });
    }
  }));

  // [FormData] form field select box
  var formFieldsEntry = extensionElements(element, bpmnFactory, {
    id: 'form-fields',
    label: translate('Form Fields'),
    modelProperty: 'id',
    prefix: 'FormField',
    createExtensionElement: function(element, extensionElements, value) {
      var bo = getBusinessObject(element), commands = [];

      if (!extensionElements) {
        extensionElements = elementHelper.createElement('bpmn:ExtensionElements', { values: [] }, bo, bpmnFactory);
        commands.push(cmdHelper.updateProperties(element, { extensionElements: extensionElements }));
      }

      var formData = formHelper.getFormData(element);

      if (!formData) {
        formData = elementHelper.createElement('camunda:FormData', { fields: [] }, extensionElements, bpmnFactory);
        commands.push(cmdHelper.addAndRemoveElementsFromList(
          element,
          extensionElements,
          'values',
          'extensionElements',
          [formData],
          []
        ));
      }

      var field = elementHelper.createElement('camunda:FormField', { id: value }, formData, bpmnFactory);
      if (typeof formData.fields !== 'undefined') {
        commands.push(cmdHelper.addElementsTolist(element, formData, 'fields', [ field ]));
      } else {
        commands.push(cmdHelper.updateBusinessObject(element, formData, {
          fields: [ field ]
        }));
      }
      return commands;
    },
    removeExtensionElement: function(element, extensionElements, value, idx) {
      var formData = getExtensionElements(getBusinessObject(element), 'camunda:FormData')[0],
          entry = formData.fields[idx],
          commands = [];

      if (formData.fields.length < 2) {
        commands.push(removeEntry(getBusinessObject(element), element, formData));
      } else {
        commands.push(cmdHelper.removeElementsFromList(element, formData, 'fields', null, [entry]));

        if (entry.id === formData.get('businessKey')) {
          commands.push(cmdHelper.updateBusinessObject(element, formData, { 'businessKey': undefined }));
        }
      }

      return commands;
    },
    getExtensionElements: function(element) {
      return formHelper.getFormFields(element);
    },
    hideExtensionElements: function(element, node) {
      return false;
    }
  });
  group.entries.push(formFieldsEntry);

  // [FormData] business key form field select box
  var formBusinessKeyFormFieldEntry = entryFactory.selectBox(translate, {
    id: 'form-business-key',
    label: translate('Business Key'),
    modelProperty: 'businessKey',
    selectOptions: function(element, inputNode) {
      var selectOptions = [{ name: '', value: '' }];
      var formFields = formHelper.getFormFields(element);
      each(formFields, function(field) {
        if (field.type !== 'boolean') {
          selectOptions.push({ name: field.id, value: field.id });
        }
      });
      return selectOptions;
    },
    get: function(element, node) {
      var result = { businessKey: '' };
      var bo = getBusinessObject(element);
      var formDataExtension = getExtensionElements(bo, 'camunda:FormData');
      if (formDataExtension.length) {
        var formData = formDataExtension[0];
        var storedValue = formData.get('businessKey');
        result = { businessKey: storedValue };
      }
      return result;
    },
    set: function(element, values, node) {
      var formData = getExtensionElements(getBusinessObject(element), 'camunda:FormData')[0];
      return cmdHelper.updateBusinessObject(element, formData, { 'businessKey': values.businessKey || undefined });
    },
    hidden: function(element, node) {
      var isStartEvent = is(element,'bpmn:StartEvent');
      return !(isStartEvent && formHelper.getFormFields(element).length > 0);
    }
  });
  group.entries.push(formBusinessKeyFormFieldEntry);

  // [FormData] Form Field label
  group.entries.push(entryFactory.label({
    id: 'form-field-header',
    labelText: translate('Form Field'),
    showLabel: function(element, node) {
      return !!getSelectedFormField(element, node);
    }
  }));

  // [FormData] form field id text input field
  group.entries.push(entryFactory.validationAwareTextField(translate, {
    id: 'form-field-id',
    label: translate('ID (process variable name)'),
    modelProperty: 'id',

    getProperty: function(element, node) {
      var selectedFormField = getSelectedFormField(element, node) || {};

      return selectedFormField.id;
    },

    setProperty: function(element, properties, node) {
      var formField = getSelectedFormField(element, node);

      return cmdHelper.updateBusinessObject(element, formField, properties);
    },

    hidden: function(element, node) {
      return !getSelectedFormField(element, node);
    },

    validate: function(element, values, node) {

      var formField = getSelectedFormField(element, node);

      if (formField) {

        var idValue = values.id;

        if (!idValue || idValue.trim() === '') {
          return { id: 'Form field id must not be empty' };
        }

        var formFields = formHelper.getFormFields(element);

        var existingFormField = find(formFields, function(f) {
          return f !== formField && f.id === idValue;
        });

        if (existingFormField) {
          return { id: 'Form field id already used in form data.' };
        }
      }
    }
  }));

  // [FormData] form field type combo box
  group.entries.push(entryFactory.comboBox(translate, {
    id: 'form-field-type',
    label: translate('Type'),
    selectOptions: [
      { name: 'string', value: 'string' },
      { name: 'long', value: 'long' },
      { name: 'boolean', value: 'boolean' },
      { name: 'date', value: 'date' },
      { name: 'enum', value: 'enum' }
    ],
    modelProperty: 'type',
    emptyParameter: true,

    get: function(element, node) {
      var selectedFormField = getSelectedFormField(element, node);

      if (selectedFormField) {
        return { type: selectedFormField.type };
      } else {
        return {};
      }
    },
    set: function(element, values, node) {
      var selectedFormField = getSelectedFormField(element, node),
          formData = getExtensionElements(getBusinessObject(element), 'camunda:FormData')[0],
          commands = [];

      if (selectedFormField.type === 'enum' && values.type !== 'enum') {

        // delete camunda:value objects from formField.values when switching from type enum
        commands.push(cmdHelper.updateBusinessObject(element, selectedFormField, { values: undefined }));
      }
      if (values.type === 'boolean' && selectedFormField.get('id') === formData.get('businessKey')) {
        commands.push(cmdHelper.updateBusinessObject(element, formData, { 'businessKey': undefined }));
      }
      commands.push(cmdHelper.updateBusinessObject(element, selectedFormField, values));

      return commands;
    },
    hidden: function(element, node) {
      return !getSelectedFormField(element, node);
    }
  }));

  // [FormData] form field label text input field
  group.entries.push(formFieldTextField(translate, {
    id: 'form-field-label',
    label: translate('Label'),
    modelProperty: 'label'
  }, getSelectedFormField));

  // [FormData] form field defaultValue text input field
  group.entries.push(formFieldTextField(translate, {
    id: 'form-field-defaultValue',
    label: translate('Default Value'),
    modelProperty: 'defaultValue'
  }, getSelectedFormField));


  // [FormData] form field enum values label
  group.entries.push(entryFactory.label({
    id: 'form-field-enum-values-header',
    labelText: translate('Values'),
    divider: true,
    showLabel: function(element, node) {
      var selectedFormField = getSelectedFormField(element, node);

      return selectedFormField && selectedFormField.type === 'enum';
    }
  }));

  // [FormData] form field enum values table
  group.entries.push(entryFactory.table(translate, {
    id: 'form-field-enum-values',
    labels: [ translate('Id'), translate('Name') ],
    modelProperties: [ 'id', 'name' ],
    show: function(element, node) {
      var selectedFormField = getSelectedFormField(element, node);

      return selectedFormField && selectedFormField.type === 'enum';
    },
    getElements: function(element, node) {
      var selectedFormField = getSelectedFormField(element, node);

      return formHelper.getEnumValues(selectedFormField);
    },
    addElement: function(element, node) {
      var selectedFormField = getSelectedFormField(element, node),
          id = generateValueId();

      var enumValue = elementHelper.createElement(
        'camunda:Value',
        { id: id, name: undefined },
        getBusinessObject(element),
        bpmnFactory
      );

      return cmdHelper.addElementsTolist(element, selectedFormField, 'values', [enumValue]);
    },
    removeElement: function(element, node, idx) {
      var selectedFormField = getSelectedFormField(element, node),
          enumValue = selectedFormField.values[idx];

      return cmdHelper.removeElementsFromList(element, selectedFormField, 'values', null, [enumValue]);
    },
    updateElement: function(element, value, node, idx) {
      var selectedFormField = getSelectedFormField(element, node),
          enumValue = selectedFormField.values[idx];

      value.name = value.name || undefined;
      return cmdHelper.updateBusinessObject(element, enumValue, value);
    },
    validate: function(element, value, node, idx) {

      var selectedFormField = getSelectedFormField(element, node),
          enumValue = selectedFormField.values[idx];

      if (enumValue) {

        // check if id is valid
        var validationError = utils.isIdValid(enumValue, value.id, translate);

        if (validationError) {
          return { id: validationError };
        }
      }
    }
  }));

  // [FormData] Validation label
  group.entries.push(entryFactory.label({
    id: 'form-field-validation-header',
    labelText: translate('Validation'),
    divider: true,
    showLabel: function(element, node) {
      return !!getSelectedFormField(element, node);
    }
  }));

  // [FormData] form field constraints table
  group.entries.push(entryFactory.table(translate, {
    id: 'constraints-list',
    modelProperties: [ 'name', 'config' ],
    labels: [ translate('Name'), translate('Config') ],
    addLabel: translate('Add Constraint'),
    getElements: function(element, node) {
      var formField = getSelectedFormField(element, node);

      return formHelper.getConstraints(formField);
    },
    addElement: function(element, node) {

      var commands = [],
          formField = getSelectedFormField(element, node),
          validation = formField.validation;

      if (!validation) {

        // create validation business object and add it to form data, if it doesn't exist
        validation = elementHelper.createElement('camunda:Validation', {}, getBusinessObject(element), bpmnFactory);

        commands.push(cmdHelper.updateBusinessObject(element, formField, { 'validation': validation }));
      }

      var newConstraint = elementHelper.createElement(
        'camunda:Constraint',
        { name: undefined, config: undefined },
        validation,
        bpmnFactory
      );

      commands.push(cmdHelper.addElementsTolist(element, validation, 'constraints', [ newConstraint ]));

      return commands;
    },
    updateElement: function(element, value, node, idx) {
      var formField = getSelectedFormField(element, node),
          constraint = formHelper.getConstraints(formField)[idx];

      value.name = value.name || undefined;
      value.config = value.config || undefined;

      return cmdHelper.updateBusinessObject(element, constraint, value);
    },
    removeElement: function(element, node, idx) {
      var commands = [],
          formField = getSelectedFormField(element, node),
          constraints = formHelper.getConstraints(formField),
          currentConstraint = constraints[idx];

      commands.push(cmdHelper.removeElementsFromList(
        element,
        formField.validation,
        'constraints',
        null,
        [ currentConstraint ]
      ));

      if (constraints.length === 1) {

        // remove camunda:validation if the last existing constraint has been removed
        commands.push(cmdHelper.updateBusinessObject(element, formField, { validation: undefined }));
      }

      return commands;
    },
    show: function(element, node) {
      return !!getSelectedFormField(element, node);
    }
  }));

  // [FormData] Properties label
  group.entries.push(entryFactory.label({
    id: 'form-field-properties-header',
    labelText: translate('Properties'),
    divider: true,
    showLabel: function(element, node) {
      return !!getSelectedFormField(element, node);
    }
  }));

  // [FormData] camunda:properties table
  group.entries.push(properties(element, bpmnFactory, {
    id: 'form-field-properties',
    modelProperties: [ 'id', 'value' ],
    labels: [ translate('Id'), translate('Value') ],
    getParent: function(element, node) {
      return getSelectedFormField(element, node);
    },
    show: function(element, node) {
      return !!getSelectedFormField(element, node);
    }
  }, translate));
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/HistoryTimeToLiveProps.js":
/*!****************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/HistoryTimeToLiveProps.js ***!
  \****************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var historyTimeToLive = __webpack_require__(/*! ./implementation/HistoryTimeToLive */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/HistoryTimeToLive.js");

module.exports = function(group, element, bpmnFactory, translate) {
  var businessObject = getBusinessObject(element);

  if (is(element, 'camunda:Process') ||
      is(element, 'bpmn:Participant') && businessObject.get('processRef')) {

    group.entries = group.entries.concat(historyTimeToLive(element, bpmnFactory, {
      getBusinessObject: function(element) {
        var bo = getBusinessObject(element);

        if (!is(bo, 'bpmn:Participant')) {
          return bo;
        }

        return bo.get('processRef');
      }
    }, translate));

  }
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/InputParametersProps.js":
/*!**************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/InputParametersProps.js ***!
  \**************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var inputParameters = __webpack_require__(/*! ./implementation/InputParameters */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/InputParameters.js");

module.exports = function(group, element, bpmnFactory, elementTemplates, translate) {

  var template = elementTemplates.get(element);

  if (template) {
    return;
  }

  var inputParametersEntry = inputParameters(element, bpmnFactory, {}, translate);

  group.entries = group.entries.concat(inputParametersEntry.entries);
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/JobConfigurationProps.js":
/*!***************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/JobConfigurationProps.js ***!
  \***************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var jobPriority = __webpack_require__(/*! ./implementation/JobPriority */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/JobPriority.js"),
    jobRetryTimeCycle = __webpack_require__(/*! ./implementation/JobRetryTimeCycle */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/JobRetryTimeCycle.js");

module.exports = function(group, element, bpmnFactory, translate) {
  var businessObject = getBusinessObject(element);

  if (is(element, 'camunda:JobPriorized') ||
      is(element, 'bpmn:Participant') && businessObject.get('processRef')) {

    group.entries = group.entries.concat(jobPriority(element, bpmnFactory, {
      getBusinessObject: function(element) {
        var bo = getBusinessObject(element);

        if (!is(bo, 'bpmn:Participant')) {
          return bo;
        }

        return bo.get('processRef');
      }
    }, translate));
  }

  if (is(element, 'camunda:AsyncCapable')) {
    group.entries = group.entries.concat(jobRetryTimeCycle(element, bpmnFactory, {
      getBusinessObject: getBusinessObject
    }, translate));
  }

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ListenerDetailProps.js":
/*!*************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ListenerDetailProps.js ***!
  \*************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is;

var find = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").find;

var entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var cmdHelper = __webpack_require__(/*! ../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    ImplementationTypeHelper = __webpack_require__(/*! ../../../helper/ImplementationTypeHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ImplementationTypeHelper.js"),
    scriptImplementation = __webpack_require__(/*! ./implementation/Script */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Script.js"),
    timerImplementation = __webpack_require__(/*! ../../bpmn/parts/implementation/TimerEventDefinition */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/TimerEventDefinition.js");

module.exports = function(group, element, bpmnFactory, options, translate) {

  var LISTENER_TYPE_LABEL = {
    class: translate('Java Class'),
    expression: translate('Expression'),
    delegateExpression: translate('Delegate Expression'),
    script: translate('Script')
  };

  options = options || {};

  var getSelectedListener = options.getSelectedListener;

  var classProp = 'class',
      expressionProp = 'expression',
      delegateExpressionProp = 'delegateExpression',
      scriptProp = 'script';

  var executionListenerEventTypeOptions = ImplementationTypeHelper.isSequenceFlow(element) ? [
    { name: translate('take'), value: 'take' }
  ] : [
    { name: translate('start'), value: 'start' },
    { name: translate('end'), value: 'end' }
  ];

  var taskListenerEventTypeOptions = [
    { name: translate('create'), value: 'create' },
    { name: translate('assignment'), value: 'assignment' },
    { name: translate('complete'), value: 'complete' },
    { name: translate('delete'), value: 'delete' },
    { name: translate('update'), value: 'update' },
    { name: translate('timeout'), value: 'timeout' }
  ];

  var isSelected = function(element, node) {
    return getSelectedListener(element, node);
  };


  // eventType ////////////////
  group.entries.push(entryFactory.selectBox(translate, {
    id: 'listener-event-type',
    label: translate('Event Type'),
    modelProperty: 'eventType',
    emptyParameter: false,

    get: function(element, node) {

      var listener = getSelectedListener(element, node);

      var eventType = listener && listener.get('event');

      return {
        eventType: eventType
      };
    },

    set: function(element, values, node) {
      var eventType = values.eventType,
          listener = getSelectedListener(element, node),
          eventDefinitions = listener && listener.eventDefinitions;

      // ensure only timeout events can have timer event definitions
      if (eventDefinitions && eventType !== 'timeout') {
        eventDefinitions = [];
      }

      return cmdHelper.updateBusinessObject(element, listener,
        {
          event: eventType,
          eventDefinitions: eventDefinitions
        }
      );
    },

    selectOptions: function(element, node) {
      var eventTypeOptions;

      var selectedListener = getSelectedListener(element, node);
      if (ImplementationTypeHelper.isTaskListener(selectedListener)) {
        eventTypeOptions = taskListenerEventTypeOptions;
      } else if (ImplementationTypeHelper.isExecutionListener(selectedListener)) {
        eventTypeOptions = executionListenerEventTypeOptions;
      }

      return eventTypeOptions;

    },

    hidden: function(element, node) {
      return !isSelected(element, node);
    }

  }));


  // listenerId ///////////////
  group.entries.push(entryFactory.textField(translate, {
    id: 'listener-id',
    label: translate('Listener Id'),
    modelProperty: 'listenerId',

    get: function(element, node) {
      var value = {},
          listener = getSelectedListener(element, node);

      value.listenerId = (listener && listener.get('id')) || undefined;

      return value;
    },

    set: function(element, values, node) {
      var update = {},
          listener = getSelectedListener(element, node);

      update['id'] = values.listenerId || '';

      return cmdHelper.updateBusinessObject(element, listener, update);
    },

    hidden: function(element, node) {
      var listener = getSelectedListener(element, node);

      return !ImplementationTypeHelper.isTaskListener(listener);
    },

    validate: function(element, values, node) {
      var value = values.listenerId,
          listener = getSelectedListener(element, node),
          validate = {};

      if (!value && isTimeoutTaskListener(listener)) {
        validate.listenerId = translate('Must provide a value for timeout task listener');
      }

      return validate;
    }

  }));


  // listenerType ///////////////
  group.entries.push(entryFactory.selectBox(translate, {
    id: 'listener-type',
    label: translate('Listener Type'),
    selectOptions: [
      { value: classProp, name: translate('Java Class') },
      { value: expressionProp, name: translate('Expression') },
      { value: delegateExpressionProp, name: translate('Delegate Expression') },
      { value: scriptProp, name: translate('Script') }
    ],
    modelProperty: 'listenerType',
    emptyParameter: false,

    get: function(element, node) {
      var listener = getSelectedListener(element, node);
      return {
        listenerType: ImplementationTypeHelper.getImplementationType(listener)
      };
    },

    set: function(element, values, node) {
      var listener = getSelectedListener(element, node),
          listenerType = values.listenerType || undefined,
          update = {};

      update[classProp] = listenerType === classProp ? '' : undefined;
      update[expressionProp] = listenerType === expressionProp ? '' : undefined;
      update[delegateExpressionProp] = listenerType === delegateExpressionProp ? '' : undefined;
      update[scriptProp] = listenerType === scriptProp ? bpmnFactory.create('camunda:Script') : undefined;

      return cmdHelper.updateBusinessObject(element, listener, update);
    },

    hidden: function(element, node) {
      return !isSelected(element, node);
    }

  }));


  // listenerValue //////////////
  group.entries.push(entryFactory.textField(translate, {
    id: 'listener-value',
    dataValueLabel: 'listenerValueLabel',
    modelProperty: 'listenerValue',

    get: function(element, node) {
      var value = {},
          listener = getSelectedListener(element, node),
          listenerType = ImplementationTypeHelper.getImplementationType(listener);

      value.listenerValueLabel = LISTENER_TYPE_LABEL[listenerType] || '';
      value.listenerValue = (listener && listener.get(listenerType)) || undefined;

      return value;
    },

    set: function(element, values, node) {
      var update = {},
          listener = getSelectedListener(element, node),
          listenerType = ImplementationTypeHelper.getImplementationType(listener);

      update[listenerType] = values.listenerValue || '';

      return cmdHelper.updateBusinessObject(element, listener, update);
    },

    hidden: function(element, node) {
      var listener = getSelectedListener(element, node);
      return !listener || listener.script;
    },

    validate: function(element, values) {
      var value = values.listenerValue,
          validate = {};

      if (!value) {
        validate.listenerValue = translate('Must provide a value');
      }

      return validate;
    }

  }));


  // script ////////////////////
  var script = scriptImplementation('scriptFormat', 'value', true, translate);

  group.entries.push({
    id: 'listener-script-value',
    html: '<div data-show="isScript">' +
            script.template +
          '</div>',

    get: function(element, node) {
      var listener = getSelectedListener(element, node);
      return listener && listener.script ? script.get(element, listener.script) : {};
    },

    set: function(element, values, node) {
      var listener = getSelectedListener(element, node);
      var update = script.set(element, values, listener);
      return cmdHelper.updateBusinessObject(element, listener.script, update);
    },

    validate: function(element, values, node) {
      var listener = getSelectedListener(element, node);
      return listener && listener.script ? script.validate(element, values) : {};
    },

    isScript: function(element, node) {
      var listener = getSelectedListener(element, node);
      return listener && listener.script;
    },

    script: script

  });


  // timerEventDefinition //////
  var timerEventDefinitionHandler = function(element, node) {
    var listener = getSelectedListener(element, node);

    if (!listener || !isTimeoutTaskListener(listener)) {
      return;
    }

    var timerEventDefinition = getTimerEventDefinition(listener);

    if (!timerEventDefinition) {
      return false;
    }

    return timerEventDefinition;
  };

  function createTimerEventDefinition(element, node) {

    var listener = getSelectedListener(element, node);

    if (!listener || !isTimeoutTaskListener(listener)) {
      return;
    }

    var eventDefinitions = listener.get('eventDefinitions') || [],
        timerEventDefinition = bpmnFactory.create('bpmn:TimerEventDefinition');

    eventDefinitions.push(timerEventDefinition);

    listener.eventDefinitions = eventDefinitions;

    return timerEventDefinition;
  }

  var timerOptions = {
    idPrefix: 'listener-',
    createTimerEventDefinition: createTimerEventDefinition
  };

  timerImplementation(group, element, bpmnFactory, timerEventDefinitionHandler, translate, timerOptions);

};


// helpers //////////////

function isTimeoutTaskListener(listener) {
  var eventType = listener && listener.event;
  return eventType === 'timeout';
}

function getTimerEventDefinition(bo) {
  var eventDefinitions = bo.eventDefinitions || [];

  return find(eventDefinitions, function(event) {
    return is(event, 'bpmn:TimerEventDefinition');
  });

}


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ListenerFieldInjectionProps.js":
/*!*********************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ListenerFieldInjectionProps.js ***!
  \*********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var assign = __webpack_require__(/*! lodash/assign */ "./node_modules/lodash/assign.js");

var fieldInjection = __webpack_require__(/*! ./implementation/FieldInjection */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/FieldInjection.js");

module.exports = function(group, element, bpmnFactory, options, translate) {

  options = assign({
    idPrefix: 'listener-',
    insideListener: true
  }, options);

  var fieldInjectionEntry = fieldInjection(element, bpmnFactory, translate, options);

  if (fieldInjectionEntry && fieldInjectionEntry.length > 0) {
    group.entries = group.entries.concat(fieldInjectionEntry);
  }

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ListenerProps.js":
/*!*******************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ListenerProps.js ***!
  \*******************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var listener = __webpack_require__(/*! ./implementation/Listener */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Listener.js");

module.exports = function(group, element, bpmnFactory, translate) {

  var listenerEntry = listener(element, bpmnFactory, {}, translate);

  group.entries = group.entries.concat(listenerEntry.entries);

  return {
    getSelectedListener: listenerEntry.getSelectedListener
  };

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/MultiInstanceLoopProps.js":
/*!****************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/MultiInstanceLoopProps.js ***!
  \****************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is;

var multiInstanceLoopCharacteristics = __webpack_require__(/*! ./implementation/MultiInstanceLoopCharacteristics */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/MultiInstanceLoopCharacteristics.js");

var jobRetryTimeCycle = __webpack_require__(/*! ./implementation/JobRetryTimeCycle */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/JobRetryTimeCycle.js"),
    asyncContinuation = __webpack_require__(/*! ./implementation/AsyncContinuation */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/AsyncContinuation.js");


function getLoopCharacteristics(element) {
  var bo = getBusinessObject(element);
  return bo.loopCharacteristics;
}


function ensureMultiInstanceSupported(element) {
  var loopCharacteristics = getLoopCharacteristics(element);
  return !!loopCharacteristics && is(loopCharacteristics, 'camunda:Collectable');
}

module.exports = function(group, element, bpmnFactory, translate) {

  if (!ensureMultiInstanceSupported(element)) {
    return;
  }

  // multi instance properties
  group.entries = group.entries.concat(multiInstanceLoopCharacteristics(element, bpmnFactory, translate));

  // async continuation ///////////////////////////////////////////////////////
  group.entries = group.entries.concat(asyncContinuation(element, bpmnFactory, {
    getBusinessObject: getLoopCharacteristics,
    idPrefix: 'multiInstance-',
    labelPrefix: translate('Multi Instance ')
  }, translate));


  // retry time cycle //////////////////////////////////////////////////////////
  group.entries = group.entries.concat(jobRetryTimeCycle(element, bpmnFactory, {
    getBusinessObject: getLoopCharacteristics,
    idPrefix: 'multiInstance-',
    labelPrefix: translate('Multi Instance ')
  }, translate));
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/OutputParametersProps.js":
/*!***************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/OutputParametersProps.js ***!
  \***************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var outputParameters = __webpack_require__(/*! ./implementation/OutputParameters */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/OutputParameters.js");

module.exports = function(group, element, bpmnFactory, elementTemplates, translate) {

  var template = elementTemplates.get(element);

  if (template) {
    return;
  }

  var outputParametersEntry = outputParameters(element, bpmnFactory, {}, translate);

  group.entries = group.entries.concat(outputParametersEntry.entries);
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ProcessVariablesProps.js":
/*!***************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ProcessVariablesProps.js ***!
  \***************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var isAny = __webpack_require__(/*! bpmn-js/lib/features/modeling/util/ModelingUtil */ "./node_modules/bpmn-js/lib/features/modeling/util/ModelingUtil.js").isAny,
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var processVariables = __webpack_require__(/*! ./implementation/ProcessVariables */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ProcessVariables.js");

module.exports = function(group, element, translate) {
  if (canHaveOverview(element)) {
    var processVariablesEntries = processVariables(element, translate);

    group.entries = group.entries.concat(processVariablesEntries);
  }
};


// helpers //////////

function canHaveOverview(element) {
  var businessObject = getBusinessObject(element);

  return (
    isAny(element, ['bpmn:Process', 'bpmn:SubProcess']) ||
    (is(element, 'bpmn:Participant') && businessObject.get('processRef'))
  );
}


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/PropertiesProps.js":
/*!*********************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/PropertiesProps.js ***!
  \*********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var properties = __webpack_require__(/*! ./implementation/Properties */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Properties.js"),
    elementHelper = __webpack_require__(/*! ../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");


module.exports = function(group, element, bpmnFactory, translate) {

  var propertiesEntry = properties(element, bpmnFactory, {
    id: 'properties',
    modelProperties: [ 'name', 'value' ],
    labels: [ translate('Name'), translate('Value') ],

    getParent: function(element, node, bo) {
      return bo.extensionElements;
    },

    createParent: function(element, bo) {
      var parent = elementHelper.createElement('bpmn:ExtensionElements', { values: [] }, bo, bpmnFactory);
      var cmd = cmdHelper.updateBusinessObject(element, bo, { extensionElements: parent });
      return {
        cmd: cmd,
        parent: parent
      };
    }
  }, translate);

  if (propertiesEntry) {
    group.entries.push(propertiesEntry);
  }

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ScriptTaskProps.js":
/*!*********************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ScriptTaskProps.js ***!
  \*********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    cmdHelper = __webpack_require__(/*! ../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    scriptImplementation = __webpack_require__(/*! ./implementation/Script */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Script.js");


module.exports = function(group, element, bpmnFactory, translate) {
  var bo;

  if (is(element, 'bpmn:ScriptTask')) {
    bo = getBusinessObject(element);
  }

  if (!bo) {
    return;
  }

  var script = scriptImplementation('scriptFormat', 'script', false, translate);
  group.entries.push({
    id: 'script-implementation',
    label: translate('Script'),
    html: script.template,

    get: function(element) {
      return script.get(element, bo);
    },

    set: function(element, values, containerElement) {
      var properties = script.set(element, values, containerElement);

      return cmdHelper.updateProperties(element, properties);
    },

    validate: function(element, values) {
      return script.validate(element, values);
    },

    script : script,

    cssClasses: ['bpp-textfield']

  });

  group.entries.push(entryFactory.textField(translate, {
    id : 'scriptResultVariable',
    label : translate('Result Variable'),
    modelProperty : 'scriptResultVariable',

    get: function(element, propertyName) {
      var boResultVariable = bo.get('camunda:resultVariable');

      return { scriptResultVariable : boResultVariable };
    },

    set: function(element, values, containerElement) {
      return cmdHelper.updateProperties(element, {
        'camunda:resultVariable': values.scriptResultVariable.length
          ? values.scriptResultVariable
          : undefined
      });
    }

  }));

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ServiceTaskDelegateProps.js":
/*!******************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/ServiceTaskDelegateProps.js ***!
  \******************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var ImplementationTypeHelper = __webpack_require__(/*! ../../../helper/ImplementationTypeHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ImplementationTypeHelper.js"),
    InputOutputHelper = __webpack_require__(/*! ../../../helper/InputOutputHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/InputOutputHelper.js");

var utils = __webpack_require__(/*! ../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js"),
    escapeHTML = utils.escapeHTML,
    triggerClickEvent = utils.triggerClickEvent;

var implementationType = __webpack_require__(/*! ./implementation/ImplementationType */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ImplementationType.js"),
    delegate = __webpack_require__(/*! ./implementation/Delegate */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Delegate.js"),
    external = __webpack_require__(/*! ./implementation/External */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/External.js"),
    callable = __webpack_require__(/*! ./implementation/Callable */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Callable.js"),
    resultVariable = __webpack_require__(/*! ./implementation/ResultVariable */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ResultVariable.js");

var entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query,
    domClosest = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").closest,
    domClasses = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").classes;

function getImplementationType(element) {
  return ImplementationTypeHelper.getImplementationType(element);
}

function getBusinessObject(element) {
  return ImplementationTypeHelper.getServiceTaskLikeBusinessObject(element);
}

function isDmnCapable(element) {
  return ImplementationTypeHelper.isDmnCapable(element);
}

function isExternalCapable(element) {
  return ImplementationTypeHelper.isExternalCapable(element);
}

function isServiceTaskLike(element) {
  return ImplementationTypeHelper.isServiceTaskLike(element);
}

module.exports = function(group, element, bpmnFactory, translate) {

  if (!isServiceTaskLike(getBusinessObject(element))) {
    return;
  }

  var hasDmnSupport = isDmnCapable(element);
  var hasExternalSupport = isExternalCapable(getBusinessObject(element));

  // implementation type ////////////////////////////////////

  group.entries = group.entries.concat(implementationType(element, bpmnFactory, {
    getBusinessObject: getBusinessObject,
    getImplementationType: getImplementationType,
    hasDmnSupport: hasDmnSupport,
    hasExternalSupport: hasExternalSupport,
    hasServiceTaskLikeSupport: true
  }, translate));


  // delegate (class, expression, delegateExpression) //////////

  group.entries = group.entries.concat(delegate(element, bpmnFactory, {
    getBusinessObject: getBusinessObject,
    getImplementationType: getImplementationType
  }, translate));


  // result variable /////////////////////////////////////////

  group.entries = group.entries.concat(resultVariable(element, bpmnFactory, {
    getBusinessObject: getBusinessObject,
    getImplementationType: getImplementationType,
    hideResultVariable: function(element, node) {
      return getImplementationType(element) !== 'expression';
    }
  }, translate));

  // external //////////////////////////////////////////////////

  if (hasExternalSupport) {
    group.entries = group.entries.concat(external(element, bpmnFactory, {
      getBusinessObject: getBusinessObject,
      getImplementationType: getImplementationType
    }, translate));
  }


  // dmn ////////////////////////////////////////////////////////

  if (hasDmnSupport) {
    group.entries = group.entries.concat(callable(element, bpmnFactory, {
      getCallableType: getImplementationType
    }, translate));
  }


  // connector ////////////////////////////////////////////////

  var isConnector = function(element) {
    return getImplementationType(element) === 'connector';
  };

  group.entries.push(entryFactory.link(translate, {
    id: 'configureConnectorLink',
    buttonLabel: translate('Configure Connector'),
    handleClick: function(element, node, event) {

      var connectorTabEl = getTabNode(node, 'connector');

      if (connectorTabEl) {
        triggerClickEvent(connectorTabEl);
      }

      // suppress actual link click
      return false;
    },
    showLink: function(element, node) {
      var link = domQuery('a', node);
      link.textContent = '';

      domClasses(link).remove('bpp-error-message');

      if (isConnector(element)) {
        var connectorId = InputOutputHelper.getConnector(element).get('connectorId');
        if (connectorId) {
          link.textContent = translate('Configure Connector');
        } else {
          link.innerHTML = '<span class="bpp-icon-warning"></span> ' + escapeHTML(translate('Must configure Connector'));
          domClasses(link).add('bpp-error-message');
        }

        return true;
      }

      return false;
    }
  }));

};



// helpers ///////////////////////////

function getTabNode(el, id) {
  var containerEl = domClosest(el, '.bpp-properties-panel');

  return domQuery('a[data-tab-target="' + id + '"]', containerEl);
}

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/StartEventInitiator.js":
/*!*************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/StartEventInitiator.js ***!
  \*************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;


module.exports = function(group, element, translate) {

  var bo = getBusinessObject(element);

  if (!bo) {
    return;
  }

  if (is(element, 'camunda:Initiator') && !is(element.parent, 'bpmn:SubProcess')) {
    group.entries.push(entryFactory.textField(translate, {
      id: 'initiator',
      label: translate('Initiator'),
      modelProperty: 'initiator'
    }));
  }
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/TasklistProps.js":
/*!*******************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/TasklistProps.js ***!
  \*******************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var tasklist = __webpack_require__(/*! ./implementation/Tasklist */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Tasklist.js");

module.exports = function(group, element, bpmnFactory, translate) {
  var businessObject = getBusinessObject(element);

  if (is(element, 'camunda:Process') ||
      is(element, 'bpmn:Participant') && businessObject.get('processRef')) {

    group.entries = group.entries.concat(tasklist(element, bpmnFactory, {
      getBusinessObject: function(element) {
        var bo = getBusinessObject(element);

        if (!is(bo, 'bpmn:Participant')) {
          return bo;
        }

        return bo.get('processRef');
      }
    }, translate));

  }
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/UserTaskProps.js":
/*!*******************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/UserTaskProps.js ***!
  \*******************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");


module.exports = function(group, element, translate) {
  if (is(element, 'camunda:Assignable')) {

    // Assignee
    group.entries.push(entryFactory.textField(translate, {
      id : 'assignee',
      label : translate('Assignee'),
      modelProperty : 'assignee'
    }));

    // Candidate Users
    group.entries.push(entryFactory.textField(translate, {
      id : 'candidateUsers',
      label : translate('Candidate Users'),
      modelProperty : 'candidateUsers'
    }));

    // Candidate Groups
    group.entries.push(entryFactory.textField(translate, {
      id : 'candidateGroups',
      label : translate('Candidate Groups'),
      modelProperty : 'candidateGroups'
    }));

    // Due Date
    group.entries.push(entryFactory.textField(translate, {
      id : 'dueDate',
      description : translate('The due date as an EL expression (e.g. ${someDate} or an ISO date (e.g. 2015-06-26T09:54:00)'),
      label : translate('Due Date'),
      modelProperty : 'dueDate'
    }));

    // FollowUp Date
    group.entries.push(entryFactory.textField(translate, {
      id : 'followUpDate',
      description : translate('The follow up date as an EL expression (e.g. ${someDate} or an ' +
                    'ISO date (e.g. 2015-06-26T09:54:00)'),
      label : translate('Follow Up Date'),
      modelProperty : 'followUpDate'
    }));

    // priority
    group.entries.push(entryFactory.textField(translate, {
      id : 'priority',
      label : translate('Priority'),
      modelProperty : 'priority'
    }));
  }
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/VariableMappingProps.js":
/*!**************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/VariableMappingProps.js ***!
  \**************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    isAny = __webpack_require__(/*! bpmn-js/lib/features/modeling/util/ModelingUtil */ "./node_modules/bpmn-js/lib/features/modeling/util/ModelingUtil.js").isAny,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var filter = __webpack_require__(/*! lodash/filter */ "./node_modules/lodash/filter.js");

var extensionElementsHelper = __webpack_require__(/*! ../../../helper/ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    elementHelper = __webpack_require__(/*! ../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    eventDefinitionHelper = __webpack_require__(/*! ../../../helper/EventDefinitionHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/EventDefinitionHelper.js");

var extensionElementsEntry = __webpack_require__(/*! ./implementation/ExtensionElements */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ExtensionElements.js");

var entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

/**
  * return depend on parameter 'type' camunda:in or camunda:out extension elements
  */
function getCamundaInOutMappings(element, type) {
  var bo = getBusinessObject(element);

  var signalEventDefinition = eventDefinitionHelper.getSignalEventDefinition(bo);

  return extensionElementsHelper.getExtensionElements(signalEventDefinition || bo, type);
}

/**
  * return depend on parameter 'type' camunda:in or camunda:out extension elements
  * with source or sourceExpression attribute
  */
function getVariableMappings(element, type) {
  var camundaMappings = getCamundaInOutMappings(element, type);

  return filter(camundaMappings, function(mapping) {
    return !mapping.businessKey;
  });
}

function getInOutType(mapping) {
  var inOutType = 'source';

  if (mapping.variables === 'all') {
    inOutType = 'variables';
  }
  else if (typeof mapping.source !== 'undefined') {
    inOutType = 'source';
  }
  else if (typeof mapping.sourceExpression !== 'undefined') {
    inOutType = 'sourceExpression';
  }

  return inOutType;
}

var CAMUNDA_IN_EXTENSION_ELEMENT = 'camunda:In',
    CAMUNDA_OUT_EXTENSION_ELEMENT = 'camunda:Out';

var WHITESPACE_REGEX = /\s/;


module.exports = function(group, element, bpmnFactory, translate) {

  var inOutTypeOptions = [
    {
      name: translate('Source'),
      value: 'source'
    },
    {
      name: translate('Source Expression'),
      value: 'sourceExpression'
    },
    {
      name: translate('All'),
      value: 'variables'
    }
  ];

  var signalEventDefinition = eventDefinitionHelper.getSignalEventDefinition(element);

  if (!is(element, 'camunda:CallActivity') && !signalEventDefinition) {
    return;
  }

  if (signalEventDefinition && !(isAny(element, [
    'bpmn:IntermediateThrowEvent',
    'bpmn:EndEvent'
  ]))) {
    return;
  }

  var isSelected = function(element, node) {
    return !!getSelected(element, node);
  };

  var getSelected = function(element, node) {
    var parentNode = node.parentNode;
    var selection = inEntry.getSelected(element, parentNode);

    var parameter = getVariableMappings(element, CAMUNDA_IN_EXTENSION_ELEMENT)[selection.idx];

    if (!parameter && outEntry) {
      selection = outEntry.getSelected(element, parentNode);
      parameter = getVariableMappings(element, CAMUNDA_OUT_EXTENSION_ELEMENT)[selection.idx];
    }

    return parameter;
  };

  var setOptionLabelValue = function(type) {
    return function(element, node, option, property, value, idx) {
      var variableMappings = getVariableMappings(element, type);
      var mappingValue = variableMappings[idx];
      var label = (mappingValue.target || '<undefined>') + ' := ';
      var mappingType = getInOutType(mappingValue);

      if (mappingType === 'variables') {
        label = 'all';
      }
      else if (mappingType === 'source') {
        label = label + (mappingValue.source || '<empty>');
      }
      else if (mappingType === 'sourceExpression') {
        label = label + (mappingValue.sourceExpression || '<empty>');
      } else {
        label = label + '<empty>';
      }

      option.text = label;
    };
  };

  var newElement = function(type) {
    return function(element, extensionElements, value) {
      var newElem = elementHelper.createElement(type, { source: '' }, extensionElements, bpmnFactory);

      return cmdHelper.addElementsTolist(element, extensionElements, 'values', [ newElem ]);
    };
  };

  var removeElement = function(type) {
    return function(element, extensionElements, value, idx) {
      var variablesMappings= getVariableMappings(element, type);
      var mapping = variablesMappings[idx];

      if (mapping) {
        return extensionElementsHelper
          .removeEntry(signalEventDefinition || getBusinessObject(element), element, mapping);
      }
    };
  };

  // in mapping for source and sourceExpression ///////////////////////////////////////////////////////////////

  var inEntry = extensionElementsEntry(element, bpmnFactory, {
    id: 'variableMapping-in',
    label: translate('In Mapping'),
    modelProperty: 'source',
    prefix: 'In',
    idGeneration: false,
    resizable: true,
    businessObject: signalEventDefinition || getBusinessObject(element),

    createExtensionElement: newElement(CAMUNDA_IN_EXTENSION_ELEMENT),
    removeExtensionElement: removeElement(CAMUNDA_IN_EXTENSION_ELEMENT),

    getExtensionElements: function(element) {
      return getVariableMappings(element, CAMUNDA_IN_EXTENSION_ELEMENT);
    },

    onSelectionChange: function(element, node, event, scope) {
      outEntry && outEntry.deselect(element, node.parentNode);
    },

    setOptionLabelValue: setOptionLabelValue(CAMUNDA_IN_EXTENSION_ELEMENT)
  });
  group.entries.push(inEntry);

  // out mapping for source and sourceExpression ///////////////////////////////////////////////////////

  if (!signalEventDefinition) {
    var outEntry = extensionElementsEntry(element, bpmnFactory, {
      id: 'variableMapping-out',
      label: translate('Out Mapping'),
      modelProperty: 'source',
      prefix: 'Out',
      idGeneration: false,
      resizable: true,

      createExtensionElement: newElement(CAMUNDA_OUT_EXTENSION_ELEMENT),
      removeExtensionElement: removeElement(CAMUNDA_OUT_EXTENSION_ELEMENT),

      getExtensionElements: function(element) {
        return getVariableMappings(element, CAMUNDA_OUT_EXTENSION_ELEMENT);
      },

      onSelectionChange: function(element, node, event, scope) {
        inEntry.deselect(element, node.parentNode);
      },

      setOptionLabelValue: setOptionLabelValue(CAMUNDA_OUT_EXTENSION_ELEMENT)
    });
    group.entries.push(outEntry);
  }

  // label for selected mapping ///////////////////////////////////////////////////////

  group.entries.push(entryFactory.label({
    id: 'variableMapping-typeLabel',
    get: function(element, node) {
      var mapping = getSelected(element, node);

      var value = '';
      if (is(mapping, CAMUNDA_IN_EXTENSION_ELEMENT)) {
        value = translate('In Mapping');
      }
      else if (is(mapping, CAMUNDA_OUT_EXTENSION_ELEMENT)) {
        value = translate('Out Mapping');
      }

      return {
        label: value
      };
    },

    showLabel: function(element, node) {
      return isSelected(element, node);
    }
  }));


  group.entries.push(entryFactory.selectBox(translate, {
    id: 'variableMapping-inOutType',
    label: translate('Type'),
    selectOptions: inOutTypeOptions,
    modelProperty: 'inOutType',
    get: function(element, node) {
      var mapping = getSelected(element, node) || {};
      return {
        inOutType: getInOutType(mapping)
      };
    },
    set: function(element, values, node) {
      var inOutType = values.inOutType;

      var props = {
        'source' : undefined,
        'sourceExpression' : undefined,
        'variables' : undefined
      };

      if (inOutType === 'source') {
        props.source = '';
      }
      else if (inOutType === 'sourceExpression') {
        props.sourceExpression = '';
      }
      else if (inOutType === 'variables') {
        props.variables = 'all';
        props.target = undefined;
      }

      var mapping = getSelected(element, node);
      return cmdHelper.updateBusinessObject(element, mapping, props);
    },
    hidden: function(element, node) {
      return !isSelected(element, node);
    }

  }));


  group.entries.push(entryFactory.textField(translate, {
    id: 'variableMapping-source',
    dataValueLabel: 'sourceLabel',
    modelProperty: 'source',
    get: function(element, node) {
      var mapping = getSelected(element, node) || {};

      var label = '';
      var inOutType = getInOutType(mapping);
      if (inOutType === 'source') {
        label = translate('Source');
      }
      else if (inOutType === 'sourceExpression') {
        label = translate('Source Expression');
      }

      return {
        source: mapping[inOutType],
        sourceLabel: label
      };
    },
    set: function(element, values, node) {
      values.source = values.source || undefined;

      var mapping = getSelected(element, node);
      var inOutType = getInOutType(mapping);

      var props = {};
      props[inOutType] = values.source || '';

      return cmdHelper.updateBusinessObject(element, mapping, props);
    },

    // one of both (source or sourceExpression) must have a value to make
    // the configuration easier and more understandable
    // it is not engine conform
    validate: function(element, values, node) {
      var mapping = getSelected(element, node);

      var validation = {};
      if (mapping) {
        if (!values.source) {
          validation.source =
          validation.source = values.sourceLabel ?
            translate('Mapping must have a {value}', { value: values.sourceLabel.toLowerCase() }) :
            translate('Mapping must have a value');
        }

        var inOutType = getInOutType(mapping);

        if (WHITESPACE_REGEX.test(values.source) && inOutType !== 'sourceExpression') {
          validation.source = translate('{label} must not contain whitespace', { label: values.sourceLabel });
        }
      }

      return validation;
    },
    hidden: function(element, node) {
      var selectedMapping = getSelected(element, node);
      return !selectedMapping || (selectedMapping && selectedMapping.variables);
    }
  }));


  group.entries.push(entryFactory.textField(translate, {
    id: 'variableMapping-target',
    label: translate('Target'),
    modelProperty: 'target',
    get: function(element, node) {
      return {
        target: (getSelected(element, node) || {}).target
      };
    },
    set: function(element, values, node) {
      values.target = values.target || undefined;
      var mapping = getSelected(element, node);
      return cmdHelper.updateBusinessObject(element, mapping, values);
    },
    validate: function(element, values, node) {
      var mapping = getSelected(element, node);

      var validation = {};
      if (mapping) {
        var mappingType = getInOutType(mapping);

        if (!values.target && mappingType !== 'variables') {
          validation.target = translate('Mapping must have a target');
        }

        if (values.target
          && WHITESPACE_REGEX.test(values.target)
          && mappingType !== 'variables') {
          validation.target = translate('Target must not contain whitespace');
        }
      }

      return validation;
    },
    hidden: function(element, node) {
      var selectedMapping = getSelected(element, node);
      return !selectedMapping || (selectedMapping && selectedMapping.variables);
    }
  }));


  group.entries.push(entryFactory.checkbox(translate, {
    id: 'variableMapping-local',
    label: translate('Local'),
    modelProperty: 'local',
    get: function(element, node) {
      return {
        local: (getSelected(element, node) || {}).local
      };
    },
    set: function(element, values, node) {
      values.local = values.local || false;
      var mapping = getSelected(element, node);
      return cmdHelper.updateBusinessObject(element, mapping, values);
    },
    hidden: function(element, node) {
      return !isSelected(element, node);
    }
  }));

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/VersionTagProps.js":
/*!*********************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/VersionTagProps.js ***!
  \*********************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    cmdHelper = __webpack_require__(/*! ../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

module.exports = function(group, element, translate) {

  var bo = getBusinessObject(element);

  if (!bo) {
    return;
  }

  if (is(element, 'bpmn:Process') || is(element, 'bpmn:Participant') && bo.get('processRef')) {
    var versionTagEntry = entryFactory.textField(translate, {
      id: 'versionTag',
      label: translate('Version Tag'),
      modelProperty: 'versionTag'
    });

    // in participants we have to change the default behavior of set and get
    if (is(element, 'bpmn:Participant')) {
      versionTagEntry.get = function(element) {
        var processBo = bo.get('processRef');

        return {
          versionTag: processBo.get('camunda:versionTag')
        };
      };

      versionTagEntry.set = function(element, values) {
        var processBo = bo.get('processRef');

        return cmdHelper.updateBusinessObject(element, processBo, {
          'camunda:versionTag': values.versionTag || undefined
        });
      };
    }

    group.entries.push(versionTagEntry);

  }
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/AsyncContinuation.js":
/*!**************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/AsyncContinuation.js ***!
  \**************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var assign = __webpack_require__(/*! lodash/assign */ "./node_modules/lodash/assign.js");

var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var asyncCapableHelper = __webpack_require__(/*! ../../../../helper/AsyncCapableHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/AsyncCapableHelper.js"),
    eventDefinitionHelper = __webpack_require__(/*! ../../../../helper/EventDefinitionHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/EventDefinitionHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

function isAsyncBefore(bo) {
  return asyncCapableHelper.isAsyncBefore(bo);
}

function isAsyncAfter(bo) {
  return asyncCapableHelper.isAsyncAfter(bo);
}

function isExclusive(bo) {
  return asyncCapableHelper.isExclusive(bo);
}

function removeFailedJobRetryTimeCycle(bo, element) {
  return asyncCapableHelper.removeFailedJobRetryTimeCycle(bo, element);
}

function canRemoveFailedJobRetryTimeCycle(element) {
  return !eventDefinitionHelper.getTimerEventDefinition(element);
}

module.exports = function(element, bpmnFactory, options, translate) {

  var getBusinessObject = options.getBusinessObject;

  var idPrefix = options.idPrefix || '',
      labelPrefix = options.labelPrefix || '';


  var asyncBeforeEntry = entryFactory.checkbox(translate, {
    id: idPrefix + 'asyncBefore',
    label: labelPrefix + translate('Asynchronous Before'),
    modelProperty: 'asyncBefore',

    get: function(element, node) {
      var bo = getBusinessObject(element);
      return {
        asyncBefore: isAsyncBefore(bo)
      };
    },

    set: function(element, values) {
      var bo = getBusinessObject(element);
      var asyncBefore = !!values.asyncBefore;

      var props = {
        'camunda:asyncBefore': asyncBefore,
        'camunda:async': false
      };

      var commands = [];
      if (!isAsyncAfter(bo) && !asyncBefore) {
        props = assign({ 'camunda:exclusive' : true }, props);
        if (canRemoveFailedJobRetryTimeCycle(element)) {
          commands.push(removeFailedJobRetryTimeCycle(bo, element));
        }
      }

      commands.push(cmdHelper.updateBusinessObject(element, bo, props));
      return commands;
    }
  });


  var asyncAfterEntry = entryFactory.checkbox(translate, {
    id: idPrefix + 'asyncAfter',
    label: labelPrefix + translate('Asynchronous After'),
    modelProperty: 'asyncAfter',

    get: function(element, node) {
      var bo = getBusinessObject(element);
      return {
        asyncAfter: isAsyncAfter(bo)
      };
    },

    set: function(element, values) {
      var bo = getBusinessObject(element);
      var asyncAfter = !!values.asyncAfter;

      var props = {
        'camunda:asyncAfter': asyncAfter
      };

      var commands = [];
      if (!isAsyncBefore(bo) && !asyncAfter) {
        props = assign({ 'camunda:exclusive' : true }, props);
        if (canRemoveFailedJobRetryTimeCycle(element)) {
          commands.push(removeFailedJobRetryTimeCycle(bo, element));
        }
      }

      commands.push(cmdHelper.updateBusinessObject(element, bo, props));
      return commands;
    }
  });


  var exclusiveEntry = entryFactory.checkbox(translate, {
    id: idPrefix + 'exclusive',
    label: labelPrefix + translate('Exclusive'),
    modelProperty: 'exclusive',

    get: function(element, node) {
      var bo = getBusinessObject(element);
      return { exclusive: isExclusive(bo) };
    },

    set: function(element, values) {
      var bo = getBusinessObject(element);
      return cmdHelper.updateBusinessObject(element, bo, { 'camunda:exclusive': !!values.exclusive });
    },

    hidden: function(element) {
      var bo = getBusinessObject(element);
      return bo && !isAsyncAfter(bo) && !isAsyncBefore(bo);
    }
  });

  return [ asyncBeforeEntry, asyncAfterEntry, exclusiveEntry ];
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Callable.js":
/*!*****************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Callable.js ***!
  \*****************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    extensionElementsHelper = __webpack_require__(/*! ../../../../helper/ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js");


var resultVariable = __webpack_require__(/*! ./ResultVariable */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ResultVariable.js");

var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;
var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is;

var forEach = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js");

var attributeInfo = {
  bpmn: {
    element: 'calledElement',
    binding: 'camunda:calledElementBinding',
    version: 'camunda:calledElementVersion',
    versionTag: 'camunda:calledElementVersionTag',
    tenantId: 'camunda:calledElementTenantId'
  },

  cmmn: {
    element: 'camunda:caseRef',
    binding: 'camunda:caseBinding',
    version: 'camunda:caseVersion',
    tenantId: 'camunda:caseTenantId'
  },

  dmn: {
    element: 'camunda:decisionRef',
    binding: 'camunda:decisionRefBinding',
    version: 'camunda:decisionRefVersion',
    versionTag: 'camunda:decisionRefVersionTag',
    tenantId: 'camunda:decisionRefTenantId'
  }
};

var mapDecisionResultOptions = [
  {
    name: 'singleEntry (TypedValue)',
    value: 'singleEntry'
  },
  {
    name:'singleResult (Map<String, Object>)',
    value:'singleResult'
  },
  {
    name:'collectEntries (List<Object>)',
    value:'collectEntries'
  },
  {
    name:'resultList (List<Map<String, Object>>)',
    value:'resultList'
  }
];

var delegateVariableMappingOptions = [
  {
    name: 'variableMappingClass',
    value: 'variableMappingClass'
  },
  {
    name: 'variableMappingDelegateExpression',
    value: 'variableMappingDelegateExpression'
  }
];

function getCamundaInWithBusinessKey(element) {
  var camundaIn = [],
      bo = getBusinessObject(element);

  var camundaInParams = extensionElementsHelper.getExtensionElements(bo, 'camunda:In');

  forEach(camundaInParams, function(param) {
    if (param.businessKey !== undefined) {
      camundaIn.push(param);
    }
  });

  return camundaIn;
}

function setBusinessKey(element, text, bpmnFactory) {
  var commands = [];

  var camundaInWithBusinessKey = getCamundaInWithBusinessKey(element);

  if (camundaInWithBusinessKey.length) {
    commands.push(cmdHelper.updateBusinessObject(element, camundaInWithBusinessKey[0], {
      businessKey: text
    }));
  } else {
    var bo = getBusinessObject(element),
        extensionElements = bo.extensionElements;

    if (!extensionElements) {
      extensionElements = elementHelper.createElement('bpmn:ExtensionElements', { values: [] }, bo, bpmnFactory);
      commands.push(cmdHelper.updateProperties(element, { extensionElements: extensionElements }));
    }

    var camundaIn = elementHelper.createElement(
      'camunda:In',
      { 'businessKey': text },
      extensionElements,
      bpmnFactory
    );

    commands.push(cmdHelper.addAndRemoveElementsFromList(
      element,
      extensionElements,
      'values',
      'extensionElements',
      [ camundaIn ],[]
    ));
  }

  return commands;
}

function deleteBusinessKey(element) {
  var camundaInExtensions = getCamundaInWithBusinessKey(element);
  var commands = [];
  forEach(camundaInExtensions, function(elem) {
    commands.push(extensionElementsHelper.removeEntry(getBusinessObject(element), element, elem));
  });
  return commands;
}

function isSupportedCallableType(type) {
  return [ 'bpmn', 'cmmn', 'dmn' ].indexOf(type) !== -1;
}

module.exports = function(element, bpmnFactory, options, translate) {

  var bindingOptions = [
    {
      name: translate('latest'),
      value: 'latest'
    },
    {
      name: translate('deployment'),
      value: 'deployment'
    },
    {
      name: translate('version'),
      value: 'version'
    },
    {
      name: translate('versionTag'),
      value: 'versionTag'
    }
  ];

  var getCallableType = options.getCallableType;

  var entries = [];

  function getAttribute(element, prop) {
    var type = getCallableType(element);
    return (attributeInfo[type] || {})[prop];
  }

  function getCallActivityBindingValue(element) {
    var type = getCallableType(element);
    var bo = getBusinessObject(element);
    var attr = (attributeInfo[type] || {}).binding;
    return bo.get(attr);
  }

  function getDelegateVariableMappingType(element) {
    var bo = getBusinessObject(element);

    var boVariableMappingClass = bo.get('camunda:variableMappingClass'),
        boVariableMappingDelegateExpression = bo.get('camunda:variableMappingDelegateExpression');

    var delegateVariableMappingType = '';
    if (typeof boVariableMappingClass !== 'undefined') {
      delegateVariableMappingType = 'variableMappingClass';
    } else

    if (typeof boVariableMappingDelegateExpression !== 'undefined') {
      delegateVariableMappingType = 'variableMappingDelegateExpression';
    }

    return delegateVariableMappingType;
  }


  entries.push(entryFactory.textField(translate, {
    id: 'callable-element-ref',
    dataValueLabel: 'callableElementLabel',
    modelProperty: 'callableElementRef',

    get: function(element, node) {
      var callableElementRef;

      var attr = getAttribute(element, 'element');
      if (attr) {
        var bo = getBusinessObject(element);
        callableElementRef = bo.get(attr);
      }

      var label = '';
      var type = getCallableType(element);
      if (type === 'bpmn') {
        label = translate('Called Element');
      }
      else if (type === 'cmmn') {
        label = translate('Case Ref');
      }
      else if (type === 'dmn') {
        label = translate('Decision Ref');
      }

      return {
        callableElementRef: callableElementRef,
        callableElementLabel: label
      };
    },

    set: function(element, values, node) {
      var newCallableElementRef = values.callableElementRef;
      var attr = getAttribute(element, 'element');

      var props = {};
      props[attr] = newCallableElementRef || '';

      return cmdHelper.updateProperties(element, props);
    },

    validate: function(element, values, node) {
      var elementRef = values.callableElementRef;
      var type = getCallableType(element);
      return isSupportedCallableType(type) && !elementRef ? { callableElementRef: translate('Must provide a value') } : {};
    },

    hidden: function(element, node) {
      return !isSupportedCallableType(getCallableType(element));
    }

  }));

  entries.push(entryFactory.selectBox(translate, {
    id: 'callable-binding',
    label: translate('Binding'),
    selectOptions: function(element) {
      var type = getCallableType(element);
      var options;

      if (type === 'cmmn') {
        options = bindingOptions.filter(function(bindingOption) {
          return bindingOption.value !== 'versionTag';
        });
      } else {
        options = bindingOptions;
      }
      return options;
    },
    modelProperty: 'callableBinding',

    get: function(element, node) {
      var callableBinding;

      var attr = getAttribute(element, 'binding');
      if (attr) {
        var bo = getBusinessObject(element);
        callableBinding = bo.get(attr) || 'latest';
      }

      return {
        callableBinding: callableBinding
      };
    },

    set: function(element, values, node) {
      var binding = values.callableBinding;
      var attr = getAttribute(element, 'binding'),
          attrVer = getAttribute(element, 'version'),
          attrVerTag = getAttribute(element, 'versionTag');

      var props = {};
      props[attr] = binding;

      // set version and versionTag values always to undefined to delete the existing value
      props[attrVer] = undefined;
      props[attrVerTag] = undefined;

      return cmdHelper.updateProperties(element, props);
    },

    hidden: function(element, node) {
      return !isSupportedCallableType(getCallableType(element));
    }

  }));

  entries.push(entryFactory.textField(translate, {
    id: 'callable-version',
    label: translate('Version'),
    modelProperty: 'callableVersion',

    get: function(element, node) {
      var callableVersion;

      var attr = getAttribute(element, 'version');
      if (attr) {
        var bo = getBusinessObject(element);
        callableVersion = bo.get(attr);
      }

      return {
        callableVersion: callableVersion
      };
    },

    set: function(element, values, node) {
      var version = values.callableVersion;
      var attr = getAttribute(element, 'version');

      var props = {};
      props[attr] = version || undefined;

      return cmdHelper.updateProperties(element, props);
    },

    validate: function(element, values, node) {
      var version = values.callableVersion;

      var type = getCallableType(element);
      return (
        isSupportedCallableType(type) &&
        getCallActivityBindingValue(element) === 'version' && (
          !version ? { callableVersion: translate('Must provide a value') } : {}
        )
      );
    },

    hidden: function(element, node) {
      var type = getCallableType(element);
      return !isSupportedCallableType(type) || getCallActivityBindingValue(element) !== 'version';
    }

  }));

  entries.push(entryFactory.textField(translate, {
    id: 'callable-version-tag',
    label: translate('Version Tag'),
    modelProperty: 'versionTag',

    get: function(element, node) {
      var versionTag;

      var attr = getAttribute(element, 'versionTag');

      if (attr) {
        var bo = getBusinessObject(element);

        versionTag = bo.get(attr);
      }

      return {
        versionTag: versionTag
      };
    },

    set: function(element, values, node) {
      var versionTag = values.versionTag;

      var attr = getAttribute(element, 'versionTag');

      var props = {};

      props[attr] = versionTag || undefined;

      return cmdHelper.updateProperties(element, props);
    },

    validate: function(element, values, node) {
      var versionTag = values.versionTag;

      var type = getCallableType(element);

      return (
        isSupportedCallableType(type) &&
        getCallActivityBindingValue(element) === 'versionTag' && (
          !versionTag ? { versionTag: translate('Must provide a value') } : {}
        )
      );
    },

    hidden: function(element, node) {
      var type = getCallableType(element);

      return !isSupportedCallableType(type) || getCallActivityBindingValue(element) !== 'versionTag';
    }

  }));

  entries.push(entryFactory.textField(translate, {
    id: 'tenant-id',
    label: translate('Tenant Id'),
    modelProperty: 'tenantId',

    get: function(element, node) {
      var tenantId;

      var attr = getAttribute(element, 'tenantId');
      if (attr) {
        var bo = getBusinessObject(element);
        tenantId = bo.get(attr);
      }

      return {
        tenantId: tenantId
      };
    },

    set: function(element, values, node) {
      var tenantId = values.tenantId;
      var attr = getAttribute(element, 'tenantId');

      var props = {};
      props[attr] = tenantId || undefined;

      return cmdHelper.updateProperties(element, props);
    },

    hidden: function(element, node) {
      var type = getCallableType(element);
      return !isSupportedCallableType(type);
    }

  }));

  if (is(getBusinessObject(element), 'bpmn:CallActivity')) {
    entries.push(entryFactory.checkbox(translate, {
      id: 'callable-business-key',
      label: translate('Business Key'),
      modelProperty: 'callableBusinessKey',

      get: function(element, node) {
        var camundaIn = getCamundaInWithBusinessKey(element);

        return {
          callableBusinessKey: !!(camundaIn && camundaIn.length > 0)
        };
      },

      set: function(element, values, node) {
        if (values.callableBusinessKey) {
          return setBusinessKey(element, '#{execution.processBusinessKey}', bpmnFactory);
        } else {
          return deleteBusinessKey(element);
        }
      }
    }));
  }

  entries.push(entryFactory.textField(translate, {
    id: 'business-key-expression',
    label: translate('Business Key Expression'),
    modelProperty: 'businessKey',

    get: function(element, node) {
      var camundaInWithBusinessKey = getCamundaInWithBusinessKey(element);

      return {
        businessKey: (
          camundaInWithBusinessKey.length ?
            camundaInWithBusinessKey[0].get('camunda:businessKey') :
            undefined
        )
      };
    },

    set: function(element, values, node) {
      var businessKey = values.businessKey;

      return setBusinessKey(element, businessKey, bpmnFactory);
    },

    validate: function(element, values, node) {
      var businessKey = values.businessKey;

      return businessKey === '' ? { businessKey: translate('Must provide a value') } : {};
    },

    hidden: function(element, node) {
      return !getCamundaInWithBusinessKey(element).length;
    }

  }));

  entries = entries.concat(resultVariable(element, bpmnFactory, {
    id: 'dmn-resultVariable',
    getBusinessObject: getBusinessObject,
    getImplementationType: getCallableType,
    hideResultVariable: function(element, node) {
      return getCallableType(element) !== 'dmn';
    }
  }, translate));

  entries.push(entryFactory.selectBox(translate, {
    id: 'dmn-map-decision-result',
    label: translate('Map Decision Result'),
    selectOptions: mapDecisionResultOptions,
    modelProperty: 'mapDecisionResult',

    get: function(element, node) {
      var bo = getBusinessObject(element);
      return {
        mapDecisionResult: bo.get('camunda:mapDecisionResult') || 'resultList'
      };
    },

    set: function(element, values, node) {
      return cmdHelper.updateProperties(element, {
        'camunda:mapDecisionResult': values.mapDecisionResult || 'resultList'
      });
    },

    hidden: function(element, node) {
      var bo = getBusinessObject(element);
      var resultVariable = bo.get('camunda:resultVariable');
      return !(getCallableType(element) === 'dmn' && typeof resultVariable !== 'undefined');
    }

  }));


  entries.push(entryFactory.selectBox(translate, {
    id: 'delegateVariableMappingType',
    label: translate('Delegate Variable Mapping'),
    selectOptions: delegateVariableMappingOptions,
    emptyParameter: true,
    modelProperty: 'delegateVariableMappingType',

    get: function(element, node) {
      return {
        delegateVariableMappingType : getDelegateVariableMappingType(element)
      };
    },

    set: function(element, values, node) {
      var delegateVariableMappingType = values.delegateVariableMappingType;

      var props = {
        'camunda:variableMappingClass' : undefined,
        'camunda:variableMappingDelegateExpression' : undefined
      };

      if (delegateVariableMappingType === 'variableMappingClass') {
        props['camunda:variableMappingClass'] = '';
      }
      else if (delegateVariableMappingType === 'variableMappingDelegateExpression') {
        props['camunda:variableMappingDelegateExpression'] = '';
      }

      return cmdHelper.updateProperties(element, props);
    },

    hidden: function(element, node) {
      return (getCallableType(element) !== 'bpmn');
    }

  }));

  entries.push(entryFactory.textField(translate, {
    id: 'delegateVariableMapping',
    dataValueLabel: 'delegateVariableMappingLabel',
    modelProperty: 'delegateVariableMapping',

    get: function(element, node) {
      var bo = getBusinessObject(element);

      var label = '';
      var delegateVariableMapping = undefined;
      var type = getDelegateVariableMappingType(element);

      if (type === 'variableMappingClass') {
        label = translate('Class');
        delegateVariableMapping = bo.get('camunda:variableMappingClass');
      }
      else if (type === 'variableMappingDelegateExpression') {
        label = translate('Delegate Expression');
        delegateVariableMapping = bo.get('camunda:variableMappingDelegateExpression');
      }

      return {
        delegateVariableMapping: delegateVariableMapping,
        delegateVariableMappingLabel: label
      };
    },

    set: function(element, values, node) {
      var delegateVariableMapping = values.delegateVariableMapping;

      var attr = 'camunda:' + getDelegateVariableMappingType(element);

      var props = {};
      props[attr] = delegateVariableMapping || undefined;

      return cmdHelper.updateProperties(element, props);
    },

    validate: function(element, values, node) {
      var delegateVariableMapping = values.delegateVariableMapping;
      return (
        getCallableType(element) === 'bpmn' && (
          !delegateVariableMapping ? { delegateVariableMapping: translate('Must provide a value') } : {}
        )
      );
    },

    hidden: function(element, node) {
      return !(getCallableType(element) === 'bpmn' && getDelegateVariableMappingType(element) !== '');
    }

  }));

  return entries;
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/CandidateStarter.js":
/*!*************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/CandidateStarter.js ***!
  \*************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

module.exports = function(element, bpmnFactory, options, translate) {

  var getBusinessObject = options.getBusinessObject;

  var candidateStarterGroupsEntry = entryFactory.textField(translate, {
    id: 'candidateStarterGroups',
    label: translate('Candidate Starter Groups'),
    modelProperty: 'candidateStarterGroups',
    description: translate('Specify more than one group as a comma separated list.'),

    get: function(element, node) {
      var bo = getBusinessObject(element);
      var candidateStarterGroups = bo.get('camunda:candidateStarterGroups');

      return {
        candidateStarterGroups: candidateStarterGroups ? candidateStarterGroups : ''
      };
    },

    set: function(element, values) {
      var bo = getBusinessObject(element);
      return cmdHelper.updateBusinessObject(element, bo, {
        'camunda:candidateStarterGroups': values.candidateStarterGroups || undefined
      });
    }

  });

  var candidateStarterUsersEntry = entryFactory.textField(translate, {
    id: 'candidateStarterUsers',
    label: translate('Candidate Starter Users'),
    modelProperty: 'candidateStarterUsers',
    description: translate('Specify more than one user as a comma separated list.'),

    get: function(element, node) {
      var bo = getBusinessObject(element);
      var candidateStarterUsers = bo.get('camunda:candidateStarterUsers');

      return {
        candidateStarterUsers: candidateStarterUsers ? candidateStarterUsers : ''
      };
    },

    set: function(element, values) {
      var bo = getBusinessObject(element);
      return cmdHelper.updateBusinessObject(element, bo, {
        'camunda:candidateStarterUsers': values.candidateStarterUsers || undefined
      });
    }

  });

  return [
    candidateStarterGroupsEntry,
    candidateStarterUsersEntry
  ];
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Delegate.js":
/*!*****************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Delegate.js ***!
  \*****************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

var DELEGATE_TYPES = [
  'class',
  'expression',
  'delegateExpression'
];

var PROPERTIES = {
  class: 'camunda:class',
  expression: 'camunda:expression',
  delegateExpression: 'camunda:delegateExpression'
};

function isDelegate(type) {
  return DELEGATE_TYPES.indexOf(type) !== -1;
}

function getAttribute(type) {
  return PROPERTIES[type];
}


module.exports = function(element, bpmnFactory, options, translate) {

  var getImplementationType = options.getImplementationType,
      getBusinessObject = options.getBusinessObject;

  function getDelegationLabel(type) {
    switch (type) {
    case 'class':
      return translate('Java Class');
    case 'expression':
      return translate('Expression');
    case 'delegateExpression':
      return translate('Delegate Expression');
    default:
      return '';
    }
  }

  var delegateEntry = entryFactory.textField(translate, {
    id: 'delegate',
    label: translate('Value'),
    dataValueLabel: 'delegationLabel',
    modelProperty: 'delegate',

    get: function(element, node) {
      var bo = getBusinessObject(element);
      var type = getImplementationType(element);
      var attr = getAttribute(type);
      var label = getDelegationLabel(type);
      return {
        delegate: bo.get(attr),
        delegationLabel: label
      };
    },

    set: function(element, values, node) {
      var bo = getBusinessObject(element);
      var type = getImplementationType(element);
      var attr = getAttribute(type);
      var prop = {};
      prop[attr] = values.delegate || '';
      return cmdHelper.updateBusinessObject(element, bo, prop);
    },

    validate: function(element, values, node) {
      return isDelegate(getImplementationType(element)) && !values.delegate ? { delegate: translate('Must provide a value') } : {};
    },

    hidden: function(element, node) {
      return !isDelegate(getImplementationType(element));
    }

  });

  return [ delegateEntry ];

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ErrorEventDefinition.js":
/*!*****************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ErrorEventDefinition.js ***!
  \*****************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    elementReferenceProperty = __webpack_require__(/*! ../../../bpmn/parts/implementation/ElementReferenceProperty */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ElementReferenceProperty.js"),
    utils = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js");

module.exports = function(
    group, element, bpmnFactory, errorEventDefinition,
    showErrorCodeVariable, showErrorMessageVariable, translate
) {


  var getValue = function(modelProperty) {
    return function(element) {
      var modelPropertyValue = errorEventDefinition.get('camunda:' + modelProperty);

      return modelPropertyValue;
    };
  };

  var setValue = function(modelProperty) {
    return function(element, values) {
      if (values[modelProperty] === '')
        values[modelProperty] = undefined;

      return cmdHelper.updateBusinessObject(element, errorEventDefinition, values);
    };
  };


  group.entries = group.entries.concat(
    elementReferenceProperty(element, errorEventDefinition, bpmnFactory, translate, {
      id: 'error-element-message',
      label: translate('Message'),
      referenceProperty: 'errorRef',
      modelProperty: 'errorMessage'
    })
  );

  if (showErrorCodeVariable) {
    group.entries.push(entryFactory.validationAwareTextField(translate, {
      id: 'errorCodeVariable',
      label: translate('Code Variable'),
      modelProperty : 'errorCodeVariable',
      description: translate('Define the name of the variable that will contain the error code'),

      getProperty: getValue('errorCodeVariable'),
      setProperty: setValue('errorCodeVariable'),

      validate: function(element, values) {
        var validation = {},
            targetValue = values.errorCodeVariable;

        if (utils.containsSpace(targetValue)) {
          validation.errorCodeVariable = translate('Error code variable must not contain spaces.');
        }

        return validation;
      }
    }));
  }

  if (showErrorMessageVariable) {
    group.entries.push(entryFactory.validationAwareTextField(translate, {
      id: 'errorMessageVariable',
      label: translate('Message Variable'),
      modelProperty: 'errorMessageVariable',

      getProperty: getValue('errorMessageVariable'),
      setProperty: setValue('errorMessageVariable'),
      description: translate('Define the name of the variable that will contain the error message'),

      validate: function(element, values) {
        var validation = {},
            targetValue = values.errorMessageVariable;

        if (utils.containsSpace(targetValue)) {
          validation.errorMessageVariable = translate('Error message variable must not contain spaces.');
        }

        return validation;
      }
    }));
  }

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Errors.js":
/*!***************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Errors.js ***!
  \***************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;


var entryFieldDescription = __webpack_require__(/*! ../../../../factory/EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");

var elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    extensionElementsHelper = __webpack_require__(/*! ../../../../helper/ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

var ErrorsEntries = __webpack_require__(/*! ./ErrorsEntries */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ErrorsEntries.js");

var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

module.exports = function(element, bpmnFactory, options, translate) {

  options = options || {};

  var result = {};

  var entries = result.entries = [];

  entries.push(
    getErrorsHeading(element, bpmnFactory, {
      type: 'camunda:ErrorEventDefinition',
      prop: 'errorEventDefinition',
      prefix: 'Error'
    }));

  append(entries,
    getErrorsEntries(element, bpmnFactory, {}, translate)
  );

  return result;
};

function getErrorsHeading(element, bpmnFactory, options) {
  var prefix = options.prefix;

  var entry = {
    id: prefix + '-heading',
    cssClasses: [ 'bpp-error' ],
    html: '<div class="bpp-field-wrapper">' +
            '<button type="button" class="bpp-error__add add action-button" ' + 'data-action="createElement">' +
            '</button><input name="hidden" type="hidden">' +
          '</div>'
  };

  entry.createElement = function(_, entryNode) {
    var commands = createElement();

    if (commands) {
      scheduleCommands(commands, entryNode);
      return true;
    }
  };

  entry.set = function() {
    var commands = entry._commands;

    if (commands) {
      delete entry._commands;
      return commands;
    }
  };

  function createElement() {
    var commands = [];
    var bo = getBusinessObject(element);
    var extensionElements = bo.get('extensionElements');

    if (!extensionElements) {
      extensionElements = elementHelper.createElement('bpmn:ExtensionElements', { values: [] }, bo, bpmnFactory);
      commands.push(cmdHelper.updateBusinessObject(element, bo, { extensionElements: extensionElements }));
    }
    var newElem = elementHelper.createElement('camunda:ErrorEventDefinition', {}, extensionElements, bpmnFactory);
    commands.push(cmdHelper.addElementsTolist(element, extensionElements, 'values', [ newElem ]));

    return commands;
  }

  /**
   * Schedule commands to be run with next `set` method call.
   *
   * @param {Array<any>} commands
   * @param {HTMLElement} entryNode
   */
  function scheduleCommands(commands, entryNode) {
    entry._commands = commands;

    // @barmac: hack to make properties panel call `set`
    var input = domQuery('input[type="hidden"]', entryNode);
    input.value = 1;
  }

  return entry;
}

function getErrors(bo) {
  return extensionElementsHelper.getExtensionElements(bo, 'camunda:ErrorEventDefinition') || [];
}


function getErrorsEntries(element, bpmnFactory, options, translate) {
  var idPrefix = options.idPrefix || '',
      bo = getBusinessObject(element),
      errorEventDefinitions = getErrors(bo),
      extensionElements = bo.get('extensionElements'),
      entries;

  if (errorEventDefinitions && !errorEventDefinitions.length) {
    var description = entryFieldDescription(translate, translate('No errors defined.'));

    return [{
      id: idPrefix + 'error-placeholder',
      cssClasses: [ 'bpp-error-placeholder' ],
      html: description
    }];
  }

  var errorsEntries = errorEventDefinitions.map(function(definition, index) {

    function onRemove() {
      var commands = [];

      commands.push(cmdHelper.removeElementsFromList(element, extensionElements, 'values', 'extensionElements', [definition]));
      return commands;
    }

    return ErrorsEntries(definition, bpmnFactory, element,
      {
        idPrefix: idPrefix + 'error-' + index,
        onRemove: onRemove,
        onToggle: onToggle
      }, translate);

    /**
     * Close remaining collapsible entries within group.
     *
     * @param {boolean} value
     * @param {HTMLElement} entryNode
     */
    function onToggle(value, entryNode) {
      if (!value) {
        return;
      }

      var currentEntryId = entryNode.dataset.entry;

      errorsEntries.forEach(function(entry) {
        if (entry.entries[0].id === currentEntryId) {
          return;
        }

        entry.setOpen(false);
      });

    }
  });

  entries = errorsEntries.map(function(input) {
    return input.entries;
  });

  return flatten(entries);
}

function flatten(arrays) {
  return Array.prototype.concat.apply([], arrays);
}

function append(array, items) {
  Array.prototype.push.apply(array, items);
}


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ErrorsEntries.js":
/*!**********************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ErrorsEntries.js ***!
  \**********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var eventDefinitionReference = __webpack_require__(/*! ../../../bpmn/parts/implementation/EventDefinitionReference */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/EventDefinitionReference.js"),
    elementReferenceProperty = __webpack_require__(/*! ../../../bpmn/parts/implementation/ElementReferenceProperty */ "./node_modules/bpmn-js-properties-panel/lib/provider/bpmn/parts/implementation/ElementReferenceProperty.js");

var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

module.exports = function(error, bpmnFactory, element, options, translate) {

  options = options || {};

  var idPrefix = options.idPrefix || '';

  var getError =
    (options.getError && typeof options.getError === 'function') ?
      function() {
        return options.getError();
      } :
      function() {
        return error;
      };

  var result = {},
      entries = [];

  result.entries = entries;

  var getCollapsibleTitle = function() {
    var error = getError();
    var title = 'No Error referenced';

    if (error.errorRef) {
      title = error.errorRef.name;
      if (error.errorRef.errorCode) {
        title += ' (code = ' + error.errorRef.errorCode + ')';
      }
    }
    return title;
  };

  // heading ////////////////////////////////////////////////////////
  var collapsible = entryFactory.collapsible({
    id: idPrefix + 'collapsible',
    title: getCollapsibleTitle(),
    description: getError().expression || '',
    cssClasses: [ 'bpp-collapsible-error' ],
    open: false,
    onRemove: options.onRemove,
    onToggle: options.onToggle,
    get: function() {
      return {
        title: getCollapsibleTitle(),
        description: getError().expression || '',
      };
    }
  });

  var isOpen = options.isOpen || collapsible.isOpen;

  result.setOpen = function(value) {
    var entryNode = domQuery('[data-entry="' + collapsible.id + '"]');
    collapsible.setOpen(value, entryNode);
  };

  entries.push(collapsible);

  entries.push(entryFactory.validationAwareTextField(translate, {
    id: idPrefix + 'error-expression',
    label: translate('Throw Expression'),
    modelProperty: 'expression',

    getProperty: function(element, node) {
      return error.expression;
    },

    setProperty: function(element, values, node) {
      return cmdHelper.updateBusinessObject(element, error, values);
    },

    validate: function(element, values, node) {
      var validation = {};
      var expressionValue = values.expression;

      if (!expressionValue) {
        validation.expression = translate('Error must have an expression');
      }

      return validation;
    },

    hidden: function(element, node) {
      return !isOpen();
    }
  }));


  entries.push.apply(entries, eventDefinitionReference(element, error, bpmnFactory, {
    id: idPrefix + 'error-reference',
    label: translate('Global Error referenced'),
    elementName: 'error',
    elementType: 'bpmn:Error',
    referenceProperty: 'errorRef',
    newElementIdPrefix: 'Error_',

    hidden: function(element, node) {
      return !isOpen();
    }
  }));


  entries.push.apply(entries, elementReferenceProperty(element, error, bpmnFactory, translate, {
    id: idPrefix + 'error-element-name',
    label: translate('Name'),
    referenceProperty: 'errorRef',
    modelProperty: 'name',
    shouldValidate: true,

    hidden: function(element, node) {
      return !isOpen();
    }
  }));


  entries.push.apply(entries, elementReferenceProperty(element, error, bpmnFactory, translate, {
    id: idPrefix + 'error-element-code',
    label: translate('Code'),
    referenceProperty: 'errorRef',
    modelProperty: 'errorCode',
    shouldValidate: true,

    hidden: function(element, node) {
      return !isOpen();
    }
  }));


  entries.push.apply(entries, elementReferenceProperty(element, error, bpmnFactory, translate, {
    id: idPrefix + 'error-element-message',
    label: translate('Message'),
    referenceProperty: 'errorRef',
    modelProperty: 'errorMessage',

    hidden: function(element, node) {
      return !isOpen();
    }
  }));

  return result;
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ExtensionElements.js":
/*!**************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ExtensionElements.js ***!
  \**************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query,
    domClosest = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").closest,
    domify = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").domify,
    forEach = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js");

var elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    utils = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js"),
    escapeHTML = utils.escapeHTML;

function getSelectBox(node, id) {
  var currentTab = domClosest(node, 'div.bpp-properties-tab');
  var query = 'select[name=selectedExtensionElement]' + (id ? '[id=cam-extensionElements-' + id + ']' : '');
  return domQuery(query, currentTab);
}

function getSelected(node, id) {
  var selectBox = getSelectBox(node, id);
  return {
    value: (selectBox || {}).value,
    idx: (selectBox || {}).selectedIndex
  };
}

function generateElementId(prefix) {
  prefix = prefix + '_';
  return utils.nextId(prefix);
}

var CREATE_EXTENSION_ELEMENT_ACTION = 'create-extension-element',
    REMOVE_EXTENSION_ELEMENT_ACTION = 'remove-extension-element';

module.exports = function(element, bpmnFactory, options, translate) {

  var id = options.id,
      prefix = options.prefix || 'elem',
      label = options.label || id,
      idGeneration = (options.idGeneration === false) ? options.idGeneration : true,
      businessObject = options.businessObject || getBusinessObject(element);

  var modelProperty = options.modelProperty || 'id';

  var getElements = options.getExtensionElements;

  var createElement = options.createExtensionElement,
      canCreate = typeof createElement === 'function';

  var removeElement = options.removeExtensionElement,
      canRemove = typeof removeElement === 'function';

  var onSelectionChange = options.onSelectionChange;

  var hideElements = options.hideExtensionElements,
      canBeHidden = typeof hideElements === 'function';

  var setOptionLabelValue = options.setOptionLabelValue;

  var defaultSize = options.size || 5,
      resizable = options.resizable;

  var reference = options.reference || undefined;

  var selectionChanged = function(element, node, event, scope) {
    if (typeof onSelectionChange === 'function') {
      return onSelectionChange(element, node, event, scope);
    }
  };

  var createOption = function(value) {
    return '<option value="' + escapeHTML(value) + '" data-value data-name="extensionElementValue">' + escapeHTML(value) + '</option>';
  };

  var initSelectionSize = function(selectBox, optionsLength) {
    if (resizable) {
      selectBox.size = optionsLength > defaultSize ? optionsLength : defaultSize;
    }
  };

  return {
    id: id,
    html: '<div class="bpp-row bpp-element-list" ' +
            (canBeHidden ? 'data-show="hideElements"' : '') + '>' +
            '<label for="cam-extensionElements-' + escapeHTML(id) + '">' + escapeHTML(label) + '</label>' +
            '<div class="bpp-field-wrapper">' +
              '<select id="cam-extensionElements-' + escapeHTML(id) + '"' +
                      'name="selectedExtensionElement" ' +
                      'size="' + escapeHTML(defaultSize) + '" ' +
                      'data-list-entry-container ' +
                      'data-on-change="selectElement">' +
              '</select>' +
              (canCreate ? '<button class="action-button add" ' +
                                   'id="cam-extensionElements-create-' + escapeHTML(id) + '" ' +
                                   'data-action="createElement">' +
                             '<span>+</span>' +
                           '</button>' : '') +
              (canRemove ? '<button class="action-button clear" ' +
                                   'id="cam-extensionElements-remove-' + escapeHTML(id) + '" ' +
                                   'data-action="removeElement" ' +
                                   'data-disable="disableRemove">' +
                             '<span>-</span>' +
                           '</button>' : '') +
            '</div>' +
          '</div>',

    get: function(element, node) {
      var elements = getElements(element, node);

      var result = [];
      forEach(elements, function(elem) {
        result.push({
          extensionElementValue: elem.get(modelProperty)
        });
      });

      var selectBox = getSelectBox(node.parentNode, id);
      initSelectionSize(selectBox, result.length);

      return result;
    },

    set: function(element, values, node) {
      var action = this.__action;
      delete this.__action;

      businessObject = businessObject || getBusinessObject(element);

      var bo =
        (reference && businessObject.get(reference))
          ? businessObject.get(reference)
          : businessObject;

      var extensionElements = bo.get('extensionElements');

      if (action.id === CREATE_EXTENSION_ELEMENT_ACTION) {
        var commands = [];
        if (!extensionElements) {
          extensionElements = elementHelper.createElement('bpmn:ExtensionElements', { values: [] }, bo, bpmnFactory);
          commands.push(cmdHelper.updateBusinessObject(element, bo, { extensionElements: extensionElements }));
        }
        commands.push(createElement(element, extensionElements, action.value, node));
        return commands;

      }
      else if (action.id === REMOVE_EXTENSION_ELEMENT_ACTION) {
        return removeElement(element, extensionElements, action.value, action.idx, node);
      }

    },

    createListEntryTemplate: function(value, index, selectBox) {
      initSelectionSize(selectBox, selectBox.options.length + 1);
      return createOption(value.extensionElementValue);
    },

    deselect: function(element, node) {
      var selectBox = getSelectBox(node, id);
      selectBox.selectedIndex = -1;
    },

    getSelected: function(element, node) {
      return getSelected(node, id);
    },

    setControlValue: function(element, node, option, property, value, idx) {
      node.value = value;

      if (!setOptionLabelValue) {
        node.text = value;
      } else {
        setOptionLabelValue(element, node, option, property, value, idx);
      }
    },

    createElement: function(element, node) {

      // create option template
      var generatedId;
      if (idGeneration) {
        generatedId = generateElementId(prefix);
      }

      var selectBox = getSelectBox(node, id);
      var template = domify(createOption(generatedId));

      // add new empty option as last child element
      selectBox.appendChild(template);

      // select last child element
      selectBox.lastChild.selected = 'selected';
      selectionChanged(element, node);

      // update select box size
      initSelectionSize(selectBox, selectBox.options.length);

      this.__action = {
        id: CREATE_EXTENSION_ELEMENT_ACTION,
        value: generatedId
      };

      return true;
    },

    removeElement: function(element, node) {
      var selection = getSelected(node, id);

      var selectBox = getSelectBox(node, id);
      selectBox.removeChild(selectBox.options[selection.idx]);

      // update select box size
      initSelectionSize(selectBox, selectBox.options.length);

      this.__action = {
        id: REMOVE_EXTENSION_ELEMENT_ACTION,
        value: selection.value,
        idx: selection.idx
      };

      return true;
    },

    hideElements: function(element, entryNode, node, scopeNode) {
      return !hideElements(element, entryNode, node, scopeNode);
    },

    disableRemove: function(element, entryNode, node, scopeNode) {
      return (getSelected(entryNode, id) || {}).idx < 0;
    },

    selectElement: selectionChanged
  };

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/External.js":
/*!*****************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/External.js ***!
  \*****************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

module.exports = function(element, bpmnFactory, options, translate) {

  var getImplementationType = options.getImplementationType,
      getBusinessObject = options.getBusinessObject;

  function isExternal(element) {
    return getImplementationType(element) === 'external';
  }

  var topicEntry = entryFactory.textField(translate, {
    id: 'externalTopic',
    label: translate('Topic'),
    modelProperty: 'externalTopic',

    get: function(element, node) {
      var bo = getBusinessObject(element);
      return { externalTopic: bo.get('camunda:topic') };
    },

    set: function(element, values, node) {
      var bo = getBusinessObject(element);
      return cmdHelper.updateBusinessObject(element, bo, {
        'camunda:topic': values.externalTopic
      });
    },

    validate: function(element, values, node) {
      return isExternal(element) && !values.externalTopic ? { externalTopic: translate('Must provide a value') } : {};
    },

    hidden: function(element, node) {
      return !isExternal(element);
    }

  });

  return [ topicEntry ];

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ExternalTaskPriority.js":
/*!*****************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ExternalTaskPriority.js ***!
  \*****************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

module.exports = function(element, bpmnFactory, options, translate) {

  var getBusinessObject = options.getBusinessObject;

  var externalTaskPriorityEntry = entryFactory.textField(translate, {
    id: 'externalTaskPriority',
    label: translate('Task Priority'),
    modelProperty: 'taskPriority',

    get: function(element, node) {
      var bo = getBusinessObject(element);
      return {
        taskPriority: bo.get('camunda:taskPriority')
      };
    },

    set: function(element, values) {
      var bo = getBusinessObject(element);
      return cmdHelper.updateBusinessObject(element, bo, {
        'camunda:taskPriority': values.taskPriority || undefined
      });
    }

  });

  return [ externalTaskPriorityEntry ];

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/FieldInjection.js":
/*!***********************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/FieldInjection.js ***!
  \***********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var extensionElementsHelper = __webpack_require__(/*! ../../../../helper/ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js"),
    elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

var utils = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js");

var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var extensionElementsEntry = __webpack_require__(/*! ./ExtensionElements */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ExtensionElements.js");

var ModelUtil = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js"),
    getBusinessObject = ModelUtil.getBusinessObject;

var assign = __webpack_require__(/*! lodash/assign */ "./node_modules/lodash/assign.js");


var DEFAULT_PROPS = {
  'stringValue': undefined,
  'string': undefined,
  'expression': undefined
};

var CAMUNDA_FIELD_EXTENSION_ELEMENT = 'camunda:Field';

module.exports = function(element, bpmnFactory, translate, options) {

  options = options || {};

  var insideListener = !!options.insideListener,
      idPrefix = options.idPrefix || '',
      getSelectedListener = options.getSelectedListener,
      businessObject = options.businessObject || getBusinessObject(element);

  var entries = [];

  var isSelected = function(element, node) {
    return getSelectedField(element, node);
  };

  function getSelectedField(element, node) {
    var selected = fieldEntry.getSelected(element, node.parentNode);

    if (selected.idx === -1) {
      return;
    }

    var fields = getCamundaFields(element, node);

    return fields[selected.idx];
  }

  function getCamundaFields(element, node) {
    if (!insideListener) {
      return (
        businessObject &&
        extensionElementsHelper.getExtensionElements(businessObject, CAMUNDA_FIELD_EXTENSION_ELEMENT)
      );
    }
    return getCamundaListenerFields(element, node);
  }

  function getCamundaListenerFields(element, node) {
    var selectedListener = getSelectedListener(element, node);
    return selectedListener && selectedListener.fields || [];
  }

  function getFieldType(bo) {
    var fieldType = 'string';

    var expressionValue = bo && bo.expression;
    var stringValue = bo && (bo.string || bo.stringValue);

    if (typeof stringValue !== 'undefined') {
      fieldType = 'string';
    } else if (typeof expressionValue !== 'undefined') {
      fieldType = 'expression';
    }

    return fieldType;
  }

  var setOptionLabelValue = function() {
    return function(element, node, option, property, value, idx) {
      var camundaFields = getCamundaFields(element, node);
      var field = camundaFields[idx];

      value = (field.name) ? field.name : '<empty>';

      var label = idx + ' : ' + value;

      option.text = label;
    };
  };

  var newElement = function() {
    return function(element, extensionElements, value, node) {

      var props = {
        name: '',
        string: ''
      };

      var newFieldElem;

      if (!insideListener) {

        newFieldElem = elementHelper.createElement(CAMUNDA_FIELD_EXTENSION_ELEMENT, props, extensionElements, bpmnFactory);
        return cmdHelper.addElementsTolist(element, extensionElements, 'values', [ newFieldElem ]);

      } else {

        var selectedListener = getSelectedListener(element, node);
        newFieldElem = elementHelper.createElement(CAMUNDA_FIELD_EXTENSION_ELEMENT, props, selectedListener, bpmnFactory);
        return cmdHelper.addElementsTolist(element, selectedListener, 'fields', [ newFieldElem ]);

      }

    };
  };

  var removeElement = function() {
    return function(element, extensionElements, value, idx, node) {
      var camundaFields= getCamundaFields(element, node);
      var field = camundaFields[idx];
      if (field) {
        if (!insideListener) {
          return extensionElementsHelper.removeEntry(businessObject, element, field);
        }
        var selectedListener = getSelectedListener(element, node);
        return cmdHelper.removeElementsFromList(element, selectedListener, 'fields', null, [ field ]);
      }
    };
  };


  var fieldEntry = extensionElementsEntry(element, bpmnFactory, {
    id : idPrefix + 'fields',
    label : translate('Fields'),
    modelProperty: 'fieldName',
    idGeneration: 'false',

    businessObject: businessObject,

    createExtensionElement: newElement(),
    removeExtensionElement: removeElement(),

    getExtensionElements: function(element, node) {
      return getCamundaFields(element, node);
    },

    setOptionLabelValue: setOptionLabelValue()

  });
  entries.push(fieldEntry);


  entries.push(entryFactory.validationAwareTextField(translate, {
    id: idPrefix + 'field-name',
    label: translate('Name'),
    modelProperty: 'fieldName',

    getProperty: function(element, node) {
      return (getSelectedField(element, node) || {}).name;
    },

    setProperty: function(element, values, node) {
      var selectedField = getSelectedField(element, node);
      return cmdHelper.updateBusinessObject(element, selectedField, { name : values.fieldName });
    },

    validate: function(element, values, node) {
      var bo = getSelectedField(element, node);

      var validation = {};
      if (bo) {
        var nameValue = values.fieldName;

        if (nameValue) {
          if (utils.containsSpace(nameValue)) {
            validation.fieldName = translate('Name must not contain spaces');
          }
        } else {
          validation.fieldName = translate('Parameter must have a name');
        }
      }

      return validation;
    },

    hidden: function(element, node) {
      return !isSelected(element, node);
    }

  }));

  var fieldTypeOptions = [
    {
      name: translate('String'),
      value: 'string'
    },
    {
      name: translate('Expression'),
      value: 'expression'
    }
  ];

  entries.push(entryFactory.selectBox(translate, {
    id: idPrefix + 'field-type',
    label: translate('Type'),
    selectOptions: fieldTypeOptions,
    modelProperty: 'fieldType',

    get: function(element, node) {
      var bo = getSelectedField(element, node);

      var fieldType = getFieldType(bo);

      return {
        fieldType: fieldType
      };
    },

    set: function(element, values, node) {
      var props = assign({}, DEFAULT_PROPS);

      var fieldType = values.fieldType;

      if (fieldType === 'string') {
        props.string = '';
      }
      else if (fieldType === 'expression') {
        props.expression = '';
      }

      return cmdHelper.updateBusinessObject(element, getSelectedField(element, node), props);
    },

    hidden: function(element, node) {
      return !isSelected(element, node);
    }

  }));


  entries.push(entryFactory.textBox(translate, {
    id: idPrefix + 'field-value',
    label: translate('Value'),
    modelProperty: 'fieldValue',

    get: function(element, node) {
      var bo = getSelectedField(element, node);
      var fieldType = getFieldType(bo);

      var fieldValue;

      if (fieldType === 'string') {
        fieldValue = bo && (bo.string || bo.stringValue);
      }
      else if (fieldType === 'expression') {
        fieldValue = bo && bo.expression;
      }

      return {
        fieldValue: fieldValue
      };
    },

    set: function(element, values, node) {
      var bo = getSelectedField(element, node);
      var fieldType = getFieldType(bo);

      var props = assign({}, DEFAULT_PROPS);

      var fieldValue = values.fieldValue || undefined;

      if (fieldType === 'string') {
        props.string = fieldValue;
      }
      else if (fieldType === 'expression') {
        props.expression = fieldValue;
      }

      return cmdHelper.updateBusinessObject(element, bo, props);

    },

    validate: function(element, values, node) {
      var bo = getSelectedField(element, node);

      var validation = {};
      if (bo) {
        if (!values.fieldValue) {
          validation.fieldValue = translate('Must provide a value');
        }
      }

      return validation;
    },

    show: function(element, node) {
      return isSelected(element, node);
    }

  }));

  return entries;

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/HistoryTimeToLive.js":
/*!**************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/HistoryTimeToLive.js ***!
  \**************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

module.exports = function(element, bpmnFactory, options, translate) {

  var getBusinessObject = options.getBusinessObject;

  var historyTimeToLiveEntry = entryFactory.textField(translate, {
    id: 'historyTimeToLive',
    label: translate('History Time To Live'),
    modelProperty: 'historyTimeToLive',

    get: function(element, node) {
      var bo = getBusinessObject(element);
      var historyTimeToLive = bo.get('camunda:historyTimeToLive');

      return {
        historyTimeToLive: historyTimeToLive ? historyTimeToLive : ''
      };
    },

    set: function(element, values) {
      var bo = getBusinessObject(element);
      return cmdHelper.updateBusinessObject(element, bo, {
        'camunda:historyTimeToLive': values.historyTimeToLive || undefined
      });
    }

  });

  return [ historyTimeToLiveEntry ];
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ImplementationType.js":
/*!***************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ImplementationType.js ***!
  \***************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    extensionElementsHelper = __webpack_require__(/*! ../../../../helper/ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js"),
    elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js");

var assign = __webpack_require__(/*! lodash/assign */ "./node_modules/lodash/assign.js");
var map = __webpack_require__(/*! lodash/map */ "./node_modules/lodash/map.js");

var DEFAULT_DELEGATE_PROPS = [ 'class', 'expression', 'delegateExpression' ];

var DELEGATE_PROPS = {
  'camunda:class': undefined,
  'camunda:expression': undefined,
  'camunda:delegateExpression': undefined,
  'camunda:resultVariable': undefined
};

var DMN_CAPABLE_PROPS = {
  'camunda:decisionRef': undefined,
  'camunda:decisionRefBinding': 'latest',
  'camunda:decisionRefVersion': undefined,
  'camunda:mapDecisionResult': 'resultList',
  'camunda:decisionRefTenantId': undefined
};


var EXTERNAL_CAPABLE_PROPS = {
  'camunda:type': undefined,
  'camunda:topic': undefined
};

module.exports = function(element, bpmnFactory, options, translate) {

  var DEFAULT_OPTIONS = [
    { value: 'class', name: translate('Java Class') },
    { value: 'expression', name: translate('Expression') },
    { value: 'delegateExpression', name: translate('Delegate Expression') }
  ];

  var DMN_OPTION = [
    { value: 'dmn', name: translate('DMN') }
  ];

  var EXTERNAL_OPTION = [
    { value: 'external', name: translate('External') }
  ];

  var CONNECTOR_OPTION = [
    { value: 'connector', name: translate('Connector') }
  ];

  var SCRIPT_OPTION = [
    { value: 'script', name: translate('Script') }
  ];

  var getType = options.getImplementationType,
      getBusinessObject = options.getBusinessObject;

  var hasDmnSupport = options.hasDmnSupport,
      hasExternalSupport = options.hasExternalSupport,
      hasServiceTaskLikeSupport = options.hasServiceTaskLikeSupport,
      hasScriptSupport = options.hasScriptSupport;

  var entries = [];

  var selectOptions = DEFAULT_OPTIONS.concat([]);

  if (hasDmnSupport) {
    selectOptions = selectOptions.concat(DMN_OPTION);
  }

  if (hasExternalSupport) {
    selectOptions = selectOptions.concat(EXTERNAL_OPTION);
  }

  if (hasServiceTaskLikeSupport) {
    selectOptions = selectOptions.concat(CONNECTOR_OPTION);
  }

  if (hasScriptSupport) {
    selectOptions = selectOptions.concat(SCRIPT_OPTION);
  }

  selectOptions.push({ value: '' });

  entries.push(entryFactory.selectBox(translate, {
    id : 'implementation',
    label: translate('Implementation'),
    selectOptions: selectOptions,
    modelProperty: 'implType',

    get: function(element, node) {
      return {
        implType: getType(element) || ''
      };
    },

    set: function(element, values, node) {
      var bo = getBusinessObject(element);
      var oldType = getType(element);
      var newType = values.implType;

      var props = assign({}, DELEGATE_PROPS);

      if (DEFAULT_DELEGATE_PROPS.indexOf(newType) !== -1) {

        var newValue = '';
        if (DEFAULT_DELEGATE_PROPS.indexOf(oldType) !== -1) {
          newValue = bo.get('camunda:' + oldType);
        }
        props['camunda:' + newType] = newValue;
      }

      if (hasDmnSupport) {
        props = assign(props, DMN_CAPABLE_PROPS);
        if (newType === 'dmn') {
          props['camunda:decisionRef'] = '';
        }
      }

      if (hasExternalSupport) {
        props = assign(props, EXTERNAL_CAPABLE_PROPS);
        if (newType === 'external') {
          props['camunda:type'] = 'external';
          props['camunda:topic'] = '';
        }
      }

      if (hasScriptSupport) {
        props['camunda:script'] = undefined;

        if (newType === 'script') {
          props['camunda:script'] = elementHelper.createElement('camunda:Script', {}, bo, bpmnFactory);
        }
      }

      var commands = [];
      commands.push(cmdHelper.updateBusinessObject(element, bo, props));

      if (oldType === 'external' && newType !== 'external') {
        var errorEventDefinitions = extensionElementsHelper.getExtensionElements(bo, 'camunda:ErrorEventDefinition');
        commands.push(map(errorEventDefinitions, function(errorEventDefinition) {
          return extensionElementsHelper.removeEntry(bo, element, errorEventDefinition);
        }));
      }

      if (hasServiceTaskLikeSupport) {
        var connectors = extensionElementsHelper.getExtensionElements(bo, 'camunda:Connector');
        commands.push(map(connectors, function(connector) {
          return extensionElementsHelper.removeEntry(bo, element, connector);
        }));

        if (newType === 'connector') {
          var extensionElements = bo.get('extensionElements');
          if (!extensionElements) {
            extensionElements = elementHelper.createElement('bpmn:ExtensionElements', { values: [] }, bo, bpmnFactory);
            commands.push(cmdHelper.updateBusinessObject(element, bo, { extensionElements: extensionElements }));
          }
          var connector = elementHelper.createElement('camunda:Connector', {}, extensionElements, bpmnFactory);
          commands.push(cmdHelper.addAndRemoveElementsFromList(
            element,
            extensionElements,
            'values',
            'extensionElements',
            [ connector ],
            []
          ));
        }
      }

      return commands;

    }
  }));

  return entries;

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/InputOutputParameter.js":
/*!*****************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/InputOutputParameter.js ***!
  \*****************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var isAny = __webpack_require__(/*! bpmn-js/lib/features/modeling/util/ModelingUtil */ "./node_modules/bpmn-js/lib/features/modeling/util/ModelingUtil.js").isAny;

var getVariablesForScope = __webpack_require__(/*! @bpmn-io/extract-process-variables */ "./node_modules/@bpmn-io/extract-process-variables/dist/index.esm.js").getVariablesForScope;

var filter = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").filter,
    map = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").map,
    sortBy = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").sortBy;

var elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    utils = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js");

var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    scriptImplementation = __webpack_require__(/*! ./Script */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Script.js");

var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

module.exports = function(parameter, bpmnFactory, options, translate) {

  var typeInfo = {
    'camunda:Map': {
      value: 'map',
      label: translate('Map')
    },
    'camunda:List': {
      value: 'list',
      label: translate('List')
    },
    'camunda:Script': {
      value: 'script',
      label: translate('Script')
    }
  };

  options = options || {};

  var idPrefix = options.idPrefix || '';

  var getParameter =
    (options.getParameter && typeof options.getParameter === 'function') ?
      function() {
        return options.getParameter();
      } :
      function() {
        return parameter;
      };

  var result = {},
      entries = [];

  result.entries = entries;

  // heading ////////////////////////////////////////////////////////
  var collapsible = entryFactory.collapsible({
    id: idPrefix + 'collapsible',
    title: parameter.name,
    description: getDescription(parameter),
    cssClasses: [ 'bpp-collapsible--with-mapping' ],
    open: false,
    onRemove: options.onRemove,
    onToggle: options.onToggle,
    get: function() {
      return {
        title: getParameter().name,
        description: getDescription(getParameter())
      };
    }
  });

  var isOpen = options.isOpen || collapsible.isOpen;

  result.setOpen = function(value) {
    var entryNode = domQuery('[data-entry="' + collapsible.id + '"]');
    collapsible.setOpen(value, entryNode);
  };

  entries.push(collapsible);

  // parameter name ////////////////////////////////////////////////////////
  entries.push(entryFactory.validationAwareTextField(translate, {
    id: idPrefix + 'parameterName',
    label: is(parameter, 'camunda:InputParameter') ?
      translate('Local Variable Name') : translate('Process Variable Name'),
    modelProperty: 'name',

    getProperty: function(element, node) {
      return parameter.name;
    },

    setProperty: function(element, values, node) {
      return cmdHelper.updateBusinessObject(element, parameter, values);
    },

    validate: function(element, values, node) {
      var validation = {};
      var nameValue = values.name;

      if (nameValue) {
        if (utils.containsSpace(nameValue)) {
          validation.name = translate('Name must not contain spaces');
        }
      } else {
        validation.name = translate('Parameter must have a name');
      }

      return validation;
    },

    hidden: function(element, node) {
      return !isOpen();
    }
  }));


  // parameter type //////////////////////////////////////////////////////

  var selectOptions = [
    { value: 'text', name: translate('String or Expression') },
    { value: 'script', name: translate('Script') },
    { value: 'list', name: translate('List') },
    { value: 'map', name: translate('Map') }
  ];

  entries.push(entryFactory.selectBox(translate, {
    id : idPrefix + 'parameterType',
    label: translate('Variable Assignment Type'),
    selectOptions: selectOptions,
    modelProperty: 'parameterType',

    get: function(element, node) {
      var parameterType = 'text';

      var definition = getParameter().get('definition');
      if (typeof definition !== 'undefined') {
        var type = definition.$type;
        parameterType = typeInfo[type].value;
      }

      return {
        parameterType: parameterType
      };
    },

    set: function(element, values, node) {
      var properties = {
        value: undefined,
        definition: undefined
      };

      var createParameterTypeElem = function(type) {
        return createElement(type, getParameter(), bpmnFactory);
      };

      var parameterType = values.parameterType;

      if (parameterType === 'script') {
        properties.definition = createParameterTypeElem('camunda:Script');
      }
      else if (parameterType === 'list') {
        properties.definition = createParameterTypeElem('camunda:List');
      }
      else if (parameterType === 'map') {
        properties.definition = createParameterTypeElem('camunda:Map');
      }

      return cmdHelper.updateBusinessObject(element, getParameter(), properties);
    },

    hidden: function(element, node) {
      return !isOpen();
    }

  }));


  // parameter value (type = text) ///////////////////////////////////////////////////////

  entries.push(entryFactory.autoSuggest(translate, {
    id : idPrefix + 'parameterType-text',
    label : translate('Variable Assignment Value'),
    description: translate('Start typing "${}" to create an expression.'),
    modelProperty: 'value',
    get: function(element, node) {
      return {
        value: getParameter().value
      };
    },

    set: function(element, values, node) {
      values.value = values.value || undefined;
      return cmdHelper.updateBusinessObject(element, getParameter(), values);
    },

    show: function(element, node) {
      return isOpen() && !getParameter().definition;
    },

    getItems: function(element) {
      var scope = getScope(element),
          rootElement = getRootElement(element);

      // (1) get all available variables for the current scope
      var variables = getVariablesForScope(scope, rootElement);

      // (2) ignore all variables which are (only) written in the current element
      variables = filter(variables, function(variable) {
        var origin = variable.origin,
            withOutCurrent = filter(origin, function(o) {
              return o.id !== element.id;
            });

        return !!withOutCurrent.length;
      });

      // (3) sort by name
      var sorted = sortByName(variables);

      // (4) retrieve names as suggestion items
      return map(sorted, function(variable) {
        return variable.name;
      });
    },

    canSuggest: function(word, editorNode, focusNode) {
      var globalIndex = findWordInContentEditable(word, editorNode, focusNode);

      if (isInsideExpression(editorNode.innerText, globalIndex)) {
        return true;
      }

      if (isInsideUnclosedExpression(editorNode.innerText, globalIndex)) {
        return true;
      }

      return false;
    }

  }));


  // parameter value (type = script) ///////////////////////////////////////////////////////
  var script = scriptImplementation('scriptFormat', 'value', true, translate, { idPrefix: idPrefix });
  entries.push({
    id: idPrefix + 'parameterType-script',
    html: '<div data-show="show">' +
            script.template +
          '</div>',
    get: function(element, node) {
      return isScript(getParameter().definition) ? script.get(element, getParameter().definition) : {};
    },

    set: function(element, values, node) {
      var update = script.set(element, values);
      return cmdHelper.updateBusinessObject(element, getParameter().definition, update);
    },

    validate: function(element, values, node) {
      return isScript(getParameter().definition) ? script.validate(element, getParameter().definition) : {};
    },

    script: script,
    show: function(element, node) {
      return isOpen() && getParameter().definition && isScript(getParameter().definition);
    }
  });


  // parameter value (type = list) ///////////////////////////////////////////////////////

  entries.push(entryFactory.table(translate, {
    id: idPrefix + 'parameterType-list',
    modelProperties: [ 'value' ],
    labels: [ translate('Value') ],
    addLabel: translate('Add Value'),

    getElements: function(element, node) {

      if (isList(getParameter().definition)) {
        return getParameter().definition.items;
      }

      return [];
    },

    updateElement: function(element, values, node, idx) {
      var item = getParameter().definition.items[idx];
      return cmdHelper.updateBusinessObject(element, item, values);
    },

    addElement: function(element, node) {
      var newValue = createElement('camunda:Value', getParameter().definition, bpmnFactory, { value: undefined });
      return cmdHelper.addElementsTolist(element, getParameter().definition, 'items', [ newValue ]);
    },

    removeElement: function(element, node, idx) {
      return cmdHelper.removeElementsFromList(element, getParameter().definition, 'items', null, [ getParameter().definition.items[idx] ]);
    },

    editable: function(element, node, prop, idx) {
      var item = getParameter().definition.items[idx];
      return !isMap(item) && !isList(item) && !isScript(item);
    },

    setControlValue: function(element, node, input, prop, value, idx) {
      var item = getParameter().definition.items[idx];

      if (!isMap(item) && !isList(item) && !isScript(item)) {
        input.value = value;
      } else {
        input.value = typeInfo[item.$type].label;
      }
    },

    show: function(element, node) {
      return isOpen() && getParameter().definition && isList(getParameter().definition);
    }

  }));


  // parameter value (type = map) ///////////////////////////////////////////////////////

  entries.push(entryFactory.table(translate, {
    id: idPrefix + 'parameterType-map',
    modelProperties: [ 'key', 'value' ],
    labels: [ translate('Key'), translate('Value') ],
    addLabel: translate('Add Entry'),

    getElements: function(element, node) {

      if (getParameter() && isMap(getParameter().definition)) {
        return getParameter().definition.entries;
      }

      return [];
    },

    updateElement: function(element, values, node, idx) {
      var entry = getParameter().definition.entries[idx];

      if (isMap(entry.definition) || isList(entry.definition) || isScript(entry.definition)) {
        values = {
          key: values.key
        };
      }

      return cmdHelper.updateBusinessObject(element, entry, values);
    },

    addElement: function(element, node) {
      var newEntry = createElement('camunda:Entry', getParameter().definition, bpmnFactory, { key: undefined, value: undefined });
      return cmdHelper.addElementsTolist(element, getParameter().definition, 'entries', [ newEntry ]);
    },

    removeElement: function(element, node, idx) {
      return cmdHelper.removeElementsFromList(element, getParameter().definition, 'entries', null, [ getParameter().definition.entries[idx] ]);
    },

    editable: function(element, node, prop, idx) {
      var entry = getParameter().definition.entries[idx];
      return prop === 'key' || (!isMap(entry.definition) && !isList(entry.definition) && !isScript(entry.definition));
    },

    setControlValue: function(element, node, input, prop, value, idx) {
      var entry = getParameter().definition.entries[idx];

      if (prop === 'key' || (!isMap(entry.definition) && !isList(entry.definition) && !isScript(entry.definition))) {
        input.value = value;
      } else {
        input.value = typeInfo[entry.definition.$type].label;
      }
    },

    show: function(element, node) {
      return isOpen() && getParameter().definition && isMap(getParameter().definition);
    }

  }));

  return result;
};



// helper /////////////////////

function createElement(type, parent, factory, properties) {
  return elementHelper.createElement(type, properties, parent, factory);
}

function isScript(elem) {
  return is(elem, 'camunda:Script');
}

function isList(elem) {
  return is(elem, 'camunda:List');
}

function isMap(elem) {
  return is(elem, 'camunda:Map');
}

function sortByName(variables) {
  return sortBy(variables, function(variable) {
    return variable.name;
  });
}

function getScope(element) {
  var businessObject = getBusinessObject(element);

  if (isAny(businessObject, [ 'bpmn:Process', 'bpmn:SubProcess' ])) {
    return businessObject.id;
  }

  // look for processes or sub process in parents
  var parent = businessObject;

  while (parent.$parent && !isAny(parent, [ 'bpmn:Process', 'bpmn:SubProcess' ])) {
    parent = parent.$parent;
  }

  return parent.id;
}

function getRootElement(element) {
  var businessObject = getBusinessObject(element),
      parent = businessObject;

  while (parent.$parent && !is(parent, 'bpmn:Process')) {
    parent = parent.$parent;
  }

  return parent;
}

function isInsideExpression(value, index) {
  var openIndex = value.indexOf('${'),
      closeIndex = value.indexOf('}');

  return (
    openIndex > -1 && openIndex <= index &&
    closeIndex > -1 && index < closeIndex
  );
}

function isInsideUnclosedExpression(value, index) {
  var closeIndex = value.lastIndexOf('}', index),
      openIndex = value.indexOf('${', closeIndex + 1);

  return (
    openIndex > -1 && openIndex <= index
  );
}

function findWordInContentEditable(word, editorNode, focusNode) {

  // retrieve value before focusNode (row)
  var children = editorNode.childNodes,
      textBefore = '';

  for (var i = 0; i <= children.length - 1; i++) {
    var child = children[i];

    if (child.contains(focusNode)) {
      break;
    }

    textBefore += (child.innerText || child.wholeText) + '\n';
  }

  return textBefore.length + (word.index || 0);
}

function getDescription(parameter) {
  var definition = parameter.get('definition');

  if (!definition) {
    return parameter.value;
  }

  if (isScript(definition)) {
    return definition.value;
  } else if (isList(definition)) {
    return 'List';
  } else if (isMap(definition)) {
    return 'Map';
  }
}


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/InputParameters.js":
/*!************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/InputParameters.js ***!
  \************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    extensionElementsHelper = __webpack_require__(/*! ../../../../helper/ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js"),
    inputOutputHelper = __webpack_require__(/*! ../../../../helper/InputOutputHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/InputOutputHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    utils = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js");

var entryFieldDescription = __webpack_require__(/*! ../../../../factory/EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");

var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

var InputOutputParameter = __webpack_require__(/*! ./InputOutputParameter */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/InputOutputParameter.js");

module.exports = function(element, bpmnFactory, options, translate) {

  options = options || {};

  var insideConnector = !!options.insideConnector,
      idPrefix = options.idPrefix || '';

  var result = {};

  var entries = result.entries = [];

  if (!inputOutputHelper.isInputOutputSupported(element, insideConnector)) {
    return result;
  }

  var parametersOptions = {
    insideConnector: insideConnector,
    idPrefix: idPrefix
  };

  // input parameters ///////////////////////////////////////////////////////////////
  entries.push(
    getParametersHeading(element, bpmnFactory, {
      idPrefix: idPrefix,
      insideConnector: insideConnector,
      type: 'camunda:InputParameter',
      prop: 'inputParameters',
      prefix: 'Input'
    }));

  append(entries,
    getInputParameterEntries(element, bpmnFactory, parametersOptions, translate)
  );

  return result;
};

function getParametersHeading(element, bpmnFactory, options) {
  var idPrefix = options.idPrefix || '',
      prefix = options.prefix,
      type = options.type,
      prop = options.prop,
      insideConnector = options.insideConnector;

  var entry = {
    id: idPrefix + prefix + '-heading',
    cssClasses: [ 'bpp-input-output' ],
    html: '<div class="bpp-field-wrapper">' +
      '<button type="button" class="bpp-input-output__add add action-button" ' + 'data-action="createElement">' +
      '</button><input name="hidden" type="hidden">' +
      '</div>'
  };

  entry.createElement = function(_, entryNode) {
    var commands = createElement();

    if (commands) {
      scheduleCommands(commands, entryNode);
      return true;
    }
  };

  entry.set = function() {
    var commands = entry._commands;

    if (commands) {
      delete entry._commands;
      return commands;
    }
  };

  function createElement() {
    var commands = [];
    var bo = getBusinessObject(element);
    var extensionElements = bo.get('extensionElements');

    if (!extensionElements) {
      extensionElements = elementHelper.createElement('bpmn:ExtensionElements', { values: [] }, bo, bpmnFactory);
      commands.push(cmdHelper.updateBusinessObject(element, bo, { extensionElements: extensionElements }));
    }

    var inputOutput = getInputOutput(element, insideConnector);
    if (!inputOutput) {
      var parent = !insideConnector ? extensionElements : getConnector(element);
      inputOutput = createInputOutput(parent, bpmnFactory, {
        inputParameters: [],
        outputParameters: []
      });

      if (!insideConnector) {
        commands.push(cmdHelper.addAndRemoveElementsFromList(
          element,
          extensionElements,
          'values',
          'extensionElements',
          [ inputOutput ],
          []
        ));
      } else {
        commands.push(cmdHelper.updateBusinessObject(element, parent, { inputOutput: inputOutput }));
      }
    }

    var newElem = createParameter(type, inputOutput, bpmnFactory, { name: generateElementId(prefix) });
    commands.push(cmdHelper.addElementsTolist(element, inputOutput, prop, [], [ newElem ]));

    return commands;
  }

  /**
   * Schedule commands to be run with next `set` method call.
   *
   * @param {Array<any>} commands
   * @param {HTMLElement} entryNode
   */
  function scheduleCommands(commands, entryNode) {
    entry._commands = commands;

    // @barmac: hack to make properties panel call `set`
    var input = domQuery('input[type="hidden"]', entryNode);
    input.value = 1;
  }

  return entry;
}

function getInputParameterEntries(element, bpmnFactory, options, translate) {
  var idPrefix = options.idPrefix,
      insideConnector = options.insideConnector,
      inputOutput = getInputOutput(element, insideConnector),
      params = getInputParameters(element, insideConnector),
      entries;

  if (!params.length) {
    var description = entryFieldDescription(translate, translate('No variables defined.'));

    return [{
      id: idPrefix + 'input-parameter' + '-placeholder',
      cssClasses: [ 'bpp-input-output-placeholder' ],
      html: description
    }];
  }

  var inputParameters = params.map(function(param, index) {
    function onRemove() {
      var commands = [];
      commands.push(cmdHelper.removeElementsFromList(element, inputOutput, 'inputParameters', null, [param]));

      // remove inputOutput if there are no input/output parameters anymore
      if (inputOutput.get('inputParameters').length === 1 && (inputOutput.get('outputParameters') || []).length === 0) {

        if (!insideConnector) {
          commands.push(extensionElementsHelper.removeEntry(getBusinessObject(element), element, inputOutput));
        }
        else {
          var connector = getConnector(element);
          commands.push(cmdHelper.updateBusinessObject(element, connector, { inputOutput: undefined }));
        }
      }

      return commands;
    }

    return InputOutputParameter(param, bpmnFactory,
      {
        idPrefix: idPrefix + 'input-parameter-' + index,
        onRemove: onRemove,
        onToggle: onToggle
      }, translate);
  });

  /**
   * Close remaining collapsible entries within group.
   *
   * @param {boolean} value
   * @param {HTMLElement} entryNode
   */
  function onToggle(value, entryNode) {
    if (!value) {
      return;
    }

    var currentEntryId = entryNode.dataset.entry;

    inputParameters.forEach(function(inputParameter) {
      if (inputParameter.entries[0].id === currentEntryId) {
        return;
      }

      inputParameter.setOpen(false);
    });
  }

  entries = inputParameters.map(function(input) {
    return input.entries;
  });

  return flatten(entries);
}


function append(array, items) {
  Array.prototype.push.apply(array, items);
}

function flatten(arrays) {
  return Array.prototype.concat.apply([], arrays);
}

function generateElementId(prefix) {
  prefix = prefix + '_';
  return utils.nextId(prefix);
}

function getInputOutput(element, insideConnector) {
  return inputOutputHelper.getInputOutput(element, insideConnector);
}

function getConnector(element) {
  return inputOutputHelper.getConnector(element);
}

function getInputParameters(element, insideConnector) {
  return inputOutputHelper.getInputParameters(element, insideConnector);
}

function createElement(type, parent, factory, properties) {
  return elementHelper.createElement(type, properties, parent, factory);
}

function createInputOutput(parent, bpmnFactory, properties) {
  return createElement('camunda:InputOutput', parent, bpmnFactory, properties);
}

function createParameter(type, parent, bpmnFactory, properties) {
  return createElement(type, parent, bpmnFactory, properties);
}


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/JobPriority.js":
/*!********************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/JobPriority.js ***!
  \********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

module.exports = function(element, bpmnFactory, options, translate) {

  var getBusinessObject = options.getBusinessObject;

  var jobPriorityEntry = entryFactory.textField(translate, {
    id: 'jobPriority',
    label: translate('Job Priority'),
    modelProperty: 'jobPriority',

    get: function(element, node) {
      var bo = getBusinessObject(element);
      return {
        jobPriority: bo.get('camunda:jobPriority')
      };
    },

    set: function(element, values) {
      var bo = getBusinessObject(element);
      return cmdHelper.updateBusinessObject(element, bo, {
        'camunda:jobPriority': values.jobPriority || undefined
      });
    }

  });

  return [ jobPriorityEntry ];

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/JobRetryTimeCycle.js":
/*!**************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/JobRetryTimeCycle.js ***!
  \**************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is;

var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var asyncCapableHelper = __webpack_require__(/*! ../../../../helper/AsyncCapableHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/AsyncCapableHelper.js");

var elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    eventDefinitionHelper = __webpack_require__(/*! ../../../../helper/EventDefinitionHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/EventDefinitionHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

function isAsyncBefore(bo) {
  return asyncCapableHelper.isAsyncBefore(bo);
}

function isAsyncAfter(bo) {
  return asyncCapableHelper.isAsyncAfter(bo);
}

function getFailedJobRetryTimeCycle(bo) {
  return asyncCapableHelper.getFailedJobRetryTimeCycle(bo);
}

function removeFailedJobRetryTimeCycle(bo, element) {
  return asyncCapableHelper.removeFailedJobRetryTimeCycle(bo, element);
}

function createExtensionElements(parent, bpmnFactory) {
  return elementHelper.createElement('bpmn:ExtensionElements', { values: [] }, parent, bpmnFactory);
}

function createFailedJobRetryTimeCycle(parent, bpmnFactory, cycle) {
  return elementHelper.createElement('camunda:FailedJobRetryTimeCycle', { body: cycle }, parent, bpmnFactory);
}

module.exports = function(element, bpmnFactory, options, translate) {

  var getBusinessObject = options.getBusinessObject;

  var idPrefix = options.idPrefix || '',
      labelPrefix = options.labelPrefix || '';

  var retryTimeCycleEntry = entryFactory.textField(translate, {
    id: idPrefix + 'retryTimeCycle',
    label: labelPrefix + translate('Retry Time Cycle'),
    modelProperty: 'cycle',

    get: function(element, node) {
      var retryTimeCycle = getFailedJobRetryTimeCycle(getBusinessObject(element));
      var value = retryTimeCycle && retryTimeCycle.get('body');
      return {
        cycle: value
      };
    },

    set: function(element, values, node) {
      var newCycle = values.cycle;
      var bo = getBusinessObject(element);

      if (newCycle === '' || typeof newCycle === 'undefined') {

        // remove retry time cycle element(s)
        return removeFailedJobRetryTimeCycle(bo, element);
      }

      var retryTimeCycle = getFailedJobRetryTimeCycle(bo);

      if (!retryTimeCycle) {

        // add new retry time cycle element
        var commands = [];

        var extensionElements = bo.get('extensionElements');
        if (!extensionElements) {
          extensionElements = createExtensionElements(bo, bpmnFactory);
          commands.push(cmdHelper.updateBusinessObject(element, bo, { extensionElements: extensionElements }));
        }

        retryTimeCycle = createFailedJobRetryTimeCycle(extensionElements, bpmnFactory, newCycle);
        commands.push(cmdHelper.addAndRemoveElementsFromList(
          element,
          extensionElements,
          'values',
          'extensionElements',
          [ retryTimeCycle ],
          []
        ));

        return commands;
      }

      // update existing retry time cycle element
      return cmdHelper.updateBusinessObject(element, retryTimeCycle, { body: newCycle });
    },

    hidden: function(element) {
      var bo = getBusinessObject(element);

      if (bo && (isAsyncBefore(bo) || isAsyncAfter(bo))) {
        return false;
      }

      if (is(element, 'bpmn:Event')) {
        return !eventDefinitionHelper.getTimerEventDefinition(element);
      }

      return true;
    }

  });

  return [ retryTimeCycleEntry ];

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Listener.js":
/*!*****************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Listener.js ***!
  \*****************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var extensionElementsEntry = __webpack_require__(/*! ./ExtensionElements */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ExtensionElements.js"),
    extensionElementsHelper = __webpack_require__(/*! ../../../../helper/ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    ImplementationTypeHelper = __webpack_require__(/*! ../../../../helper/ImplementationTypeHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ImplementationTypeHelper.js");


function getListeners(bo, type) {
  return bo && extensionElementsHelper.getExtensionElements(bo, type) || [];
}

var CAMUNDA_EXECUTION_LISTENER_ELEMENT = 'camunda:ExecutionListener';
var CAMUNDA_TASK_LISTENER_ELEMENT = 'camunda:TaskListener';

module.exports = function(element, bpmnFactory, options, translate) {

  var LISTENER_TYPE_LABEL = {
    class: translate('Java Class'),
    expression: translate('Expression'),
    delegateExpression: translate('Delegate Expression'),
    script: translate('Script')
  };

  var bo;

  var result = {
    getSelectedListener: getSelectedListener
  };

  var entries = result.entries = [];

  var isSequenceFlow = ImplementationTypeHelper.isSequenceFlow(element);

  function getSelectedListener(element, node) {
    var selection = (executionListenerEntry && executionListenerEntry.getSelected(element, node)) || { idx: -1 };

    var listener = getListeners(bo, CAMUNDA_EXECUTION_LISTENER_ELEMENT)[selection.idx];
    if (!listener && taskListenerEntry) {
      selection = taskListenerEntry.getSelected(element, node);
      listener = getListeners(bo, CAMUNDA_TASK_LISTENER_ELEMENT)[selection.idx];
    }
    return listener;
  }

  var setOptionLabelValue = function(type) {
    return function(element, node, option, property, value, idx) {
      var listeners = getListeners(bo, type);
      var listener = listeners[idx];
      var listenerType = ImplementationTypeHelper.getImplementationType(listener);

      var event = (listener.get('event')) ? listener.get('event') : '<empty>';

      var label = (event || '*') + ' : ' + (LISTENER_TYPE_LABEL[listenerType] || '');

      option.text = label;
    };
  };

  var newElement = function(element, type, initialEvent) {
    return function(element, extensionElements, value) {
      var props = {
        event: initialEvent,
        class: ''
      };

      var newElem = elementHelper.createElement(type, props, extensionElements, bpmnFactory);

      return cmdHelper.addElementsTolist(element, extensionElements, 'values', [ newElem ]);
    };
  };

  var removeElement = function(element, type) {
    return function(element, extensionElements, value, idx) {
      var listeners = getListeners(bo, type);
      var listener = listeners[idx];
      if (listener) {
        return extensionElementsHelper.removeEntry(bo, element, listener);
      }
    };
  };


  // Execution Listener

  if (is(element, 'bpmn:FlowElement') || is(element, 'bpmn:Process') || is(element, 'bpmn:Participant')) {
    bo = getBusinessObject(element);
    if (is(element, 'bpmn:Participant')) {
      element = element.processRef;
      bo = bo.get('processRef');
    }

    if (bo) {

      var executionListenerEntry = extensionElementsEntry(element, bpmnFactory, {
        id : 'executionListeners',
        label : translate('Execution Listener'),
        modelProperty: 'name',
        idGeneration: 'false',
        reference: 'processRef',

        createExtensionElement: newElement(element, CAMUNDA_EXECUTION_LISTENER_ELEMENT, (isSequenceFlow) ? 'take' : 'start'),
        removeExtensionElement: removeElement(element, CAMUNDA_EXECUTION_LISTENER_ELEMENT),

        getExtensionElements: function(element) {
          return getListeners(bo, CAMUNDA_EXECUTION_LISTENER_ELEMENT);
        },

        onSelectionChange: function(element, node, event, scope) {
          taskListenerEntry && taskListenerEntry.deselect(element, node);
        },

        setOptionLabelValue: setOptionLabelValue(CAMUNDA_EXECUTION_LISTENER_ELEMENT)

      });
      entries.push(executionListenerEntry);

    }
  }


  // Task Listener

  if (is(element, 'bpmn:UserTask')) {
    bo = getBusinessObject(element);

    var taskListenerEntry = extensionElementsEntry(element, bpmnFactory, {
      id : 'taskListeners',
      label : translate('Task Listener'),
      modelProperty: 'name',
      idGeneration: 'false',

      createExtensionElement: newElement(element, CAMUNDA_TASK_LISTENER_ELEMENT, 'create'),
      removeExtensionElement: removeElement(element, CAMUNDA_TASK_LISTENER_ELEMENT),

      getExtensionElements: function(element) {
        return getListeners(bo, CAMUNDA_TASK_LISTENER_ELEMENT);
      },

      onSelectionChange: function(element, node, event, scope) {
        executionListenerEntry.deselect(element, node);
      },

      setOptionLabelValue: setOptionLabelValue(CAMUNDA_TASK_LISTENER_ELEMENT)

    });
    entries.push(taskListenerEntry);
  }

  return result;

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/MultiInstanceLoopCharacteristics.js":
/*!*****************************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/MultiInstanceLoopCharacteristics.js ***!
  \*****************************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    escapeHTML = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

var domClasses = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").classes;

/**
 * Get a property value of the loop characteristics.
 *
 * @param {djs.model.Base} element
 * @param {string} propertyName
 *
 * @return {any} the property value
 */
function getProperty(element, propertyName) {
  var loopCharacteristics = getLoopCharacteristics(element);
  return loopCharacteristics && loopCharacteristics.get(propertyName);
}

/**
 * Get the body of a given expression.
 *
 * @param {ModdleElement<bpmn:FormalExpression>} expression
 *
 * @return {string} the body (value) of the expression
 */
function getBody(expression) {
  return expression && expression.get('body');
}


/**
 * Get the loop characteristics of an element.
 *
 * @param {djs.model.Base} element
 *
 * @return {ModdleElement<bpmn:MultiInstanceLoopCharacteristics>} the loop characteristics
 */
function getLoopCharacteristics(element) {
  var bo = getBusinessObject(element);
  return bo.loopCharacteristics;
}

/**
 * Get the loop cardinality of the loop characteristics.
 *
 * @param {djs.model.Base} element
 *
 * @return {ModdleElement<bpmn:FormalExpression>} an expression representing the loop cardinality
 */
function getLoopCardinality(element) {
  return getProperty(element, 'loopCardinality');
}

/**
 * Get the loop cardinality value of the loop characteristics.
 *
 * @param {djs.model.Base} element
 *
 * @return {string} the loop cardinality value
 */
function getLoopCardinalityValue(element) {
  var loopCardinality = getLoopCardinality(element);
  return getBody(loopCardinality);
}

/**
 * Get the completion condition of the loop characteristics.
 *
 * @param {djs.model.Base} element
 *
 * @return {ModdleElement<bpmn:FormalExpression>} an expression representing the completion condition
 */
function getCompletionCondition(element) {
  return getProperty(element, 'completionCondition');
}

/**
 * Get the completion condition value of the loop characteristics.
 *
 * @param {djs.model.Base} element
 *
 * @return {string} the completion condition value
 */
function getCompletionConditionValue(element) {
  var completionCondition = getCompletionCondition(element);
  return getBody(completionCondition);
}

/**
 * Get the 'camunda:collection' attribute value of the loop characteristics.
 *
 * @param {djs.model.Base} element
 *
 * @return {string} the 'camunda:collection' value
 */
function getCollection(element) {
  return getProperty(element, 'camunda:collection');
}

/**
 * Get the 'camunda:elementVariable' attribute value of the loop characteristics.
 *
 * @param {djs.model.Base} element
 *
 * @return {string} the 'camunda:elementVariable' value
 */
function getElementVariable(element) {
  return getProperty(element, 'camunda:elementVariable');
}


/**
 * Creates 'bpmn:FormalExpression' element.
 *
 * @param {ModdleElement} parent
 * @param {string} body
 * @param {BpmnFactory} bpmnFactory
 *
 * @result {ModdleElement<bpmn:FormalExpression>} a formal expression
 */
function createFormalExpression(parent, body, bpmnFactory) {
  return elementHelper.createElement('bpmn:FormalExpression', { body: body }, parent, bpmnFactory);
}

/**
 * Updates a specific formal expression of the loop characteristics.
 *
 * @param {djs.model.Base} element
 * @param {string} propertyName
 * @param {string} newValue
 * @param {BpmnFactory} bpmnFactory
 */
function updateFormalExpression(element, propertyName, newValue, bpmnFactory) {
  var loopCharacteristics = getLoopCharacteristics(element);

  var expressionProps = {};

  if (!newValue) {

    // remove formal expression
    expressionProps[propertyName] = undefined;
    return cmdHelper.updateBusinessObject(element, loopCharacteristics, expressionProps);
  }

  var existingExpression = loopCharacteristics.get(propertyName);

  if (!existingExpression) {

    // add formal expression
    expressionProps[propertyName] = createFormalExpression(loopCharacteristics, newValue, bpmnFactory);
    return cmdHelper.updateBusinessObject(element, loopCharacteristics, expressionProps);
  }

  // edit existing formal expression
  return cmdHelper.updateBusinessObject(element, existingExpression, {
    body: newValue
  });
}


module.exports = function(element, bpmnFactory, translate) {

  var entries = [];

  // error message /////////////////////////////////////////////////////////////////

  entries.push({
    id: 'multiInstance-errorMessage',
    html: '<div data-show="isValid">' +
             '<span class="bpp-icon-warning"></span> ' +
             escapeHTML(translate('Must provide either loop cardinality or collection')) +
          '</div>',

    isValid: function(element, node, notification, scope) {
      var loopCharacteristics = getLoopCharacteristics(element);

      var isValid = true;
      if (loopCharacteristics) {
        var loopCardinality = getLoopCardinalityValue(element);
        var collection = getCollection(element);

        isValid = !loopCardinality && !collection;
      }

      domClasses(node).toggle('bpp-hidden', !isValid);
      domClasses(notification).toggle('bpp-error-message', isValid);

      return isValid;
    }
  });

  // loop cardinality //////////////////////////////////////////////////////////////

  entries.push(entryFactory.textField(translate, {
    id: 'multiInstance-loopCardinality',
    label: translate('Loop Cardinality'),
    modelProperty: 'loopCardinality',

    get: function(element, node) {
      return {
        loopCardinality: getLoopCardinalityValue(element)
      };
    },

    set: function(element, values) {
      return updateFormalExpression(element, 'loopCardinality', values.loopCardinality, bpmnFactory);
    }
  }));


  // collection //////////////////////////////////////////////////////////////////

  entries.push(entryFactory.textField(translate, {
    id: 'multiInstance-collection',
    label: translate('Collection'),
    modelProperty: 'collection',

    get: function(element, node) {
      return {
        collection: getCollection(element)
      };
    },

    set: function(element, values) {
      var loopCharacteristics = getLoopCharacteristics(element);
      return cmdHelper.updateBusinessObject(element, loopCharacteristics, {
        'camunda:collection': values.collection || undefined
      });
    },

    validate: function(element, values, node) {
      var collection = getCollection(element);
      var elementVariable = getElementVariable(element);

      if (!collection && elementVariable) {
        return { collection : 'Must provide a value' };
      }
    }
  }));


  // element variable ////////////////////////////////////////////////////////////

  entries.push(entryFactory.textField(translate, {
    id: 'multiInstance-elementVariable',
    label: translate('Element Variable'),
    modelProperty: 'elementVariable',

    get: function(element, node) {
      return {
        elementVariable: getElementVariable(element)
      };
    },

    set: function(element, values) {
      var loopCharacteristics = getLoopCharacteristics(element);
      return cmdHelper.updateBusinessObject(element, loopCharacteristics, {
        'camunda:elementVariable': values.elementVariable || undefined
      });
    }
  }));


  // Completion Condition //////////////////////////////////////////////////////

  entries.push(entryFactory.textField(translate, {
    id: 'multiInstance-completionCondition',
    label: translate('Completion Condition'),
    modelProperty: 'completionCondition',

    get: function(element) {
      return {
        completionCondition: getCompletionConditionValue(element)
      };
    },

    set: function(element, values) {
      return updateFormalExpression(element, 'completionCondition', values.completionCondition, bpmnFactory);
    }
  }));

  return entries;

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/OutputParameters.js":
/*!*************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/OutputParameters.js ***!
  \*************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject;

var elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    extensionElementsHelper = __webpack_require__(/*! ../../../../helper/ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js"),
    inputOutputHelper = __webpack_require__(/*! ../../../../helper/InputOutputHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/InputOutputHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    utils = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js");

var entryFieldDescription = __webpack_require__(/*! ../../../../factory/EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");

var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query;

var InputOutputParameter = __webpack_require__(/*! ./InputOutputParameter */ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/InputOutputParameter.js");

module.exports = function(element, bpmnFactory, options, translate) {

  options = options || {};

  var insideConnector = !!options.insideConnector,
      idPrefix = options.idPrefix || '';

  var result = {};

  var entries = result.entries = [];

  if (!inputOutputHelper.isInputOutputSupported(element, insideConnector) ||
    !inputOutputHelper.areOutputParametersSupported(element, insideConnector)) {
    return result;
  }

  var parametersOptions = {
    insideConnector: insideConnector,
    idPrefix: idPrefix
  };

  // output parameters ///////////////////////////////////////////////////////
  entries.push(
    getParametersHeading(element, bpmnFactory, {
      idPrefix: idPrefix,
      insideConnector: insideConnector,
      type: 'camunda:OutputParameter',
      prop: 'outputParameters',
      prefix: 'Output'
    }));

  append(entries,
    getOutputParameterEntries(element, bpmnFactory, parametersOptions, translate)
  );

  return result;
};

function getParametersHeading(element, bpmnFactory, options) {
  var idPrefix = options.idPrefix || '',
      prefix = options.prefix,
      type = options.type,
      prop = options.prop,
      insideConnector = options.insideConnector;

  var entry = {
    id: idPrefix + prefix + '-heading',
    cssClasses: [ 'bpp-input-output' ],
    html: '<div class="bpp-field-wrapper">' +
      '<button type="button" class="bpp-input-output__add add action-button" ' + 'data-action="createElement">' +
      '</button><input name="hidden" type="hidden">' +
      '</div>'
  };

  entry.createElement = function(_, entryNode) {
    var commands = createElement();

    if (commands) {
      scheduleCommands(commands, entryNode);
      return true;
    }
  };

  entry.set = function() {
    var commands = entry._commands;

    if (commands) {
      delete entry._commands;
      return commands;
    }
  };

  function createElement() {
    var commands = [];
    var bo = getBusinessObject(element);
    var extensionElements = bo.get('extensionElements');

    if (!extensionElements) {
      extensionElements = elementHelper.createElement('bpmn:ExtensionElements', { values: [] }, bo, bpmnFactory);
      commands.push(cmdHelper.updateBusinessObject(element, bo, { extensionElements: extensionElements }));
    }

    var inputOutput = getInputOutput(element, insideConnector);
    if (!inputOutput) {
      var parent = !insideConnector ? extensionElements : getConnector(element);
      inputOutput = createInputOutput(parent, bpmnFactory, {
        inputParameters: [],
        outputParameters: []
      });

      if (!insideConnector) {
        commands.push(cmdHelper.addAndRemoveElementsFromList(
          element,
          extensionElements,
          'values',
          'extensionElements',
          [ inputOutput ],
          []
        ));
      } else {
        commands.push(cmdHelper.updateBusinessObject(element, parent, { inputOutput: inputOutput }));
      }
    }

    var newElem = createParameter(type, inputOutput, bpmnFactory, { name: generateElementId(prefix) });
    commands.push(cmdHelper.addElementsTolist(element, inputOutput, prop, [], [ newElem ]));

    return commands;
  }

  /**
   * Schedule commands to be run with next `set` method call.
   *
   * @param {Array<any>} commands
   * @param {HTMLElement} entryNode
   */
  function scheduleCommands(commands, entryNode) {
    entry._commands = commands;

    // @barmac: hack to make properties panel call `set`
    var input = domQuery('input[type="hidden"]', entryNode);
    input.value = 1;
  }

  return entry;
}

function getOutputParameterEntries(element, bpmnFactory, options, translate) {
  var idPrefix = options.idPrefix,
      insideConnector = options.insideConnector,
      inputOutput = getInputOutput(element, insideConnector),
      params = getOutputParameters(element, insideConnector),
      entries;

  if (!params.length) {
    var description = entryFieldDescription(translate, translate('No variables defined.'));

    return [{
      id: idPrefix + 'output-parameter' + '-placeholder',
      cssClasses: [ 'bpp-input-output-placeholder' ],
      html: description
    }];
  }

  var outputParameters = params.map(function(param, index) {
    function onRemove() {
      var commands = [];
      commands.push(cmdHelper.removeElementsFromList(element, inputOutput, 'outputParameters', null, [param]));

      // remove inputOutput if there are no input/output parameters anymore
      if (inputOutput.get('outputParameters').length === 1 && (inputOutput.get('inputParameters') || []).length === 0) {

        if (!insideConnector) {
          commands.push(extensionElementsHelper.removeEntry(getBusinessObject(element), element, inputOutput));
        }
        else {
          var connector = getConnector(element);
          commands.push(cmdHelper.updateBusinessObject(element, connector, { inputOutput: undefined }));
        }
      }

      return commands;
    }

    return InputOutputParameter(param, bpmnFactory,
      {
        idPrefix: idPrefix + 'output-parameter-' + index,
        onRemove: onRemove,
        onToggle: onToggle
      }, translate);
  });

  /**
   * Close remaining collapsible entries within group.
   *
   * @param {boolean} value
   * @param {HTMLElement} entryNode
   */
  function onToggle(value, entryNode) {
    if (!value) {
      return;
    }

    var currentEntryId = entryNode.dataset.entry;

    outputParameters.forEach(function(outputParameter) {
      if (outputParameter.entries[0].id === currentEntryId) {
        return;
      }

      outputParameter.setOpen(false);
    });
  }

  entries = outputParameters.map(function(input) {
    return input.entries;
  });

  return flatten(entries);
}

function append(array, items) {
  Array.prototype.push.apply(array, items);
}

function flatten(arrays) {
  return Array.prototype.concat.apply([], arrays);
}

function generateElementId(prefix) {
  prefix = prefix + '_';
  return utils.nextId(prefix);
}

function getInputOutput(element, insideConnector) {
  return inputOutputHelper.getInputOutput(element, insideConnector);
}

function getConnector(element) {
  return inputOutputHelper.getConnector(element);
}

function getOutputParameters(element, insideConnector) {
  return inputOutputHelper.getOutputParameters(element, insideConnector);
}

function createElement(type, parent, factory, properties) {
  return elementHelper.createElement(type, properties, parent, factory);
}

function createInputOutput(parent, bpmnFactory, properties) {
  return createElement('camunda:InputOutput', parent, bpmnFactory, properties);
}

function createParameter(type, parent, bpmnFactory, properties) {
  return createElement(type, parent, bpmnFactory, properties);
}


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ProcessVariables.js":
/*!*************************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ProcessVariables.js ***!
  \*************************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getVariablesForScope = __webpack_require__(/*! @bpmn-io/extract-process-variables */ "./node_modules/@bpmn-io/extract-process-variables/dist/index.esm.js").getVariablesForScope;

var groupBy = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").groupBy,
    flatten = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").flatten,
    forEach = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").forEach,
    keys = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").keys,
    map = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").map,
    sortBy = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js").sortBy;

var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is,
    isAny = __webpack_require__(/*! bpmn-js/lib/features/modeling/util/ModelingUtil */ "./node_modules/bpmn-js/lib/features/modeling/util/ModelingUtil.js").isAny;

var escapeHTML = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML;

var factory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var entryFieldDescription = __webpack_require__(/*! ../../../../factory/EntryFieldDescription */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFieldDescription.js");


module.exports = function(element, translate) {

  var entries = [];

  function createVariablesList(variables, scope) {
    var scopePrefix = scope ? scope + '-' : '';

    return flatten(map(variables, function(variable, idx) {

      var name = variable.name,
          origin = variable.origin,
          variableEntries = [];

      // title ///////////////////

      var collapsible = factory.collapsible({
        id: scopePrefix + 'variable- ' + idx + '-collapsible',
        title: escapeHTML(name),
        description: origin.toString(),
        open: false,
        get: function() {
          return {
            title: escapeHTML(name),
            description: origin.toString()
          };
        }
      });

      var isOpen = collapsible.isOpen;

      variableEntries.push(collapsible);

      // created in //////////////////

      var createdInHtml = '<div data-show="show">' +
        '<b>' + escapeHTML(translate('Created in')) + '</b>' +
        createdInList(origin) +
      '</div>';

      variableEntries.push({
        id: scopePrefix + 'variable- ' + idx + '-created-in',
        html: createdInHtml,
        cssClasses: [
          'bpp-process-variables',
          'bpp-process-variables__created-in'
        ],
        show: function() {
          return isOpen();
        }
      });

      return variableEntries;
    }));
  }


  if (!canHaveProcessVariables(element)) {
    return entries;
  }

  var businessObject = getBusinessObject(element),
      rootElement = getRootElement(businessObject),
      scope = getScope(element);

  var variables = getVariablesForScope(scope, rootElement),
      sorted = sortByName(variables),
      withNames = populateElementNames(sorted),
      byScope = groupByScope(withNames);

  // (1) tab description entry
  var description = entryFieldDescription(translate, translate('Available process variables, identified in the diagram.'));

  entries.push({
    id: 'process-variables-description',
    html: description,
    cssClasses: [
      'bpp-process-variables',
      'bpp-process-variables__description'
    ]
  });

  // (2) empty list placeholder
  if (!withNames.length) {
    var placeholder = entryFieldDescription(translate, translate('No variables found.'));

    entries.push({
      id: 'process-variables-placeholder',
      html: placeholder
    });

    return entries;
  }

  if (keys(byScope).length > 1) {

    // (3a) multiple scopes variables lists

    // assumption: variables extractor fetches parent variables first
    forEach(reverse(keys(byScope)), function(scope) {
      var variables = byScope[scope];

      entries.push({
        id: scope + '-scope-title',
        html: '<div>' + escapeHTML(translate('Scope: ')) + scope + '</div>',
        cssClasses: [
          'bpp-process-variables',
          'bpp-process-variables__scope-title',
          'bpp-collapsible-break'
        ]
      });

      entries = entries.concat(createVariablesList(variables, scope));
    });
  } else {

    // (3b) single scope variables list
    entries = entries.concat(createVariablesList(withNames));
  }


  return entries;
};


// helpers //////////

function getRootElement(element) {
  var businessObject = getBusinessObject(element);

  if (is(businessObject, 'bpmn:Participant')) {
    return businessObject.processRef;
  }

  if (is(businessObject, 'bpmn:Process')) {
    return businessObject;
  }

  var parent = businessObject;

  while (parent.$parent && !is(parent, 'bpmn:Process')) {
    parent = parent.$parent;
  }

  return parent;
}

function getScope(element) {
  if (is(element, 'bpmn:Participant')) {
    return getBusinessObject(element).processRef.id;
  }

  return element.id;
}

function sortByName(variables) {
  return sortBy(variables, function(variable) {
    return variable.name;
  });
}

function populateElementNames(variables) {
  forEach(variables, function(variable) {
    var names = map(variable.origin, function(element) {
      return element.name || element.id;
    });

    variable.origin = names;
    variable.scope = variable.scope.name || variable.scope.id;
  });

  return variables;
}

function canHaveProcessVariables(element) {
  var businessObject = getBusinessObject(element);

  return (
    isAny(element, ['bpmn:Process', 'bpmn:SubProcess']) ||
      (is(element, 'bpmn:Participant') && businessObject.get('processRef'))
  );
}

function groupByScope(variables) {
  return groupBy(variables, 'scope');
}

function createdInList(origin) {
  var html = '';

  forEach(origin, function(o) {
    html += '<p class="bpp-process-variables__created-in-item">' + o + '</p>';
  });
  return html;
}

function reverse(array) {
  return map(array, function(a, i) {
    return array[array.length - 1 - i];
  });
}

/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Properties.js":
/*!*******************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Properties.js ***!
  \*******************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var getBusinessObject = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").getBusinessObject,
    is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is;

var factory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var elementHelper = __webpack_require__(/*! ../../../../helper/ElementHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ElementHelper.js"),
    extensionElementsHelper = __webpack_require__(/*! ../../../../helper/ExtensionElementsHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/ExtensionElementsHelper.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js"),
    utils = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js");

var assign = __webpack_require__(/*! lodash/assign */ "./node_modules/lodash/assign.js"),
    forEach = __webpack_require__(/*! lodash/forEach */ "./node_modules/lodash/forEach.js"),
    find = __webpack_require__(/*! lodash/find */ "./node_modules/lodash/find.js");

function generatePropertyId() {
  return utils.nextId('Property_');
}

/**
 * Get all camunda:property objects for a specific business object
 *
 * @param  {ModdleElement} parent
 *
 * @return {Array<ModdleElement>} a list of camunda:property objects
 */
function getPropertyValues(parent) {
  var properties = parent && getPropertiesElement(parent);
  if (properties && properties.values) {
    return properties.values;
  }
  return [];
}

/**
 * Get all camunda:Properties object for a specific business object
 *
 * @param  {ModdleElement} parent
 *
 * @return {ModdleElement} a camunda:Properties object
 */
function getPropertiesElement(element) {
  if (!isExtensionElements(element)) {
    return element.properties;
  } else {
    return getPropertiesElementInsideExtensionElements(element);
  }
}

/**
 * Get first camunda:Properties object for a specific bpmn:ExtensionElements
 * business object.
 *
 * @param {ModdleElement} extensionElements
 *
 * @return {ModdleElement} a camunda:Properties object
 */
function getPropertiesElementInsideExtensionElements(extensionElements) {
  return find(extensionElements.values, function(elem) {
    return is(elem, 'camunda:Properties');
  });
}

/**
 * Returns true, if the given business object is a bpmn:ExtensionElements.
 *
 * @param {ModdleElement} element
 *
 * @return {boolean} a boolean value
 */
function isExtensionElements(element) {
  return is(element, 'bpmn:ExtensionElements');
}

/**
 * Create a camunda:property entry using tableEntryFactory
 *
 * @param  {djs.model.Base} element
 * @param  {BpmnFactory} bpmnFactory
 * @param  {Object} options
 * @param  {string} options.id
 * @param  {Array<string>} options.modelProperties
 * @param  {Array<string>} options.labels
 * @param  {function} options.getParent Gets the parent business object
 * @param  {function} options.show Indicate when the entry will be shown, should return boolean
 */
module.exports = function(element, bpmnFactory, options, translate) {

  var getParent = options.getParent;

  var modelProperties = options.modelProperties,
      createParent = options.createParent;

  var bo = getBusinessObject(element);
  if (is(element, 'bpmn:Participant')) {
    bo = bo.get('processRef');
  }

  // build properties group only when the participant have a processRef
  if (!bo) {
    return;
  }

  assign(options, {
    addLabel: translate('Add Property'),
    getElements: function(element, node) {
      var parent = getParent(element, node, bo);
      return getPropertyValues(parent);
    },
    addElement: function(element, node) {
      var commands = [],
          parent = getParent(element, node, bo);

      if (!parent && typeof createParent === 'function') {
        var result = createParent(element, bo);
        parent = result.parent;
        commands.push(result.cmd);
      }

      var properties = getPropertiesElement(parent);
      if (!properties) {
        properties = elementHelper.createElement('camunda:Properties', {}, parent, bpmnFactory);

        if (!isExtensionElements(parent)) {
          commands.push(cmdHelper.updateBusinessObject(element, parent, { 'properties': properties }));
        } else {
          commands.push(cmdHelper.addAndRemoveElementsFromList(
            element,
            parent,
            'values',
            'extensionElements',
            [ properties ],
            []
          ));
        }
      }

      var propertyProps = {};
      forEach(modelProperties, function(prop) {
        propertyProps[prop] = undefined;
      });

      // create id if necessary
      if (modelProperties.indexOf('id') >= 0) {
        propertyProps.id = generatePropertyId();
      }

      var property = elementHelper.createElement('camunda:Property', propertyProps, properties, bpmnFactory);
      commands.push(cmdHelper.addElementsTolist(element, properties, 'values', [ property ]));

      return commands;
    },
    updateElement: function(element, value, node, idx) {
      var parent = getParent(element, node, bo),
          property = getPropertyValues(parent)[idx];

      forEach(modelProperties, function(prop) {
        value[prop] = value[prop] || undefined;
      });

      return cmdHelper.updateBusinessObject(element, property, value);
    },
    validate: function(element, value, node, idx) {

      // validate id if necessary
      if (modelProperties.indexOf('id') >= 0) {

        var parent = getParent(element, node, bo),
            properties = getPropertyValues(parent),
            property = properties[idx];

        if (property) {

          // check if id is valid
          var validationError = utils.isIdValid(property, value.id, translate);

          if (validationError) {
            return { id: validationError };
          }
        }
      }
    },
    removeElement: function(element, node, idx) {
      var commands = [],
          parent = getParent(element, node, bo),
          properties = getPropertiesElement(parent),
          propertyValues = getPropertyValues(parent),
          currentProperty = propertyValues[idx];

      commands.push(cmdHelper.removeElementsFromList(element, properties, 'values', null, [ currentProperty ]));

      if (propertyValues.length === 1) {

        // remove camunda:properties if the last existing property has been removed
        if (!isExtensionElements(parent)) {
          commands.push(cmdHelper.updateBusinessObject(element, parent, { properties: undefined }));
        } else {
          forEach(parent.values, function(value) {
            if (is(value, 'camunda:Properties')) {
              commands.push(extensionElementsHelper.removeEntry(bo, element, value));
            }
          });
        }
      }

      return commands;
    }
  });

  return factory.table(translate, options);
};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ResultVariable.js":
/*!***********************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/ResultVariable.js ***!
  \***********************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var is = __webpack_require__(/*! bpmn-js/lib/util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js").is;

var assign = __webpack_require__(/*! lodash/assign */ "./node_modules/lodash/assign.js");

var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js"),
    cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

module.exports = function(element, bpmnFactory, options, translate) {

  var getBusinessObject = options.getBusinessObject,
      hideResultVariable = options.hideResultVariable,
      id = options.id || 'resultVariable';


  var resultVariableEntry = entryFactory.textField(translate, {
    id: id,
    label: translate('Result Variable'),
    modelProperty: 'resultVariable',

    get: function(element, node) {
      var bo = getBusinessObject(element);
      return { resultVariable: bo.get('camunda:resultVariable') };
    },

    set: function(element, values, node) {
      var bo = getBusinessObject(element);

      var resultVariable = values.resultVariable || undefined;

      var props = {
        'camunda:resultVariable': resultVariable
      };

      if (is(bo, 'camunda:DmnCapable') && !resultVariable) {
        props = assign({ 'camunda:mapDecisionResult': 'resultList' }, props);
      }

      return cmdHelper.updateBusinessObject(element, bo, props);
    },

    hidden: function(element, node) {
      if (typeof hideResultVariable === 'function') {
        return hideResultVariable.apply(resultVariableEntry, arguments);
      }
    }

  });

  return [ resultVariableEntry ];

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Script.js":
/*!***************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Script.js ***!
  \***************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var domQuery = __webpack_require__(/*! min-dom */ "./node_modules/min-dom/dist/index.esm.js").query,
    escapeHTML = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js").escapeHTML,
    utils = __webpack_require__(/*! ../../../../Utils */ "./node_modules/bpmn-js-properties-panel/lib/Utils.js");


function getScriptType(node, idPrefix) {

  // if we have an idPrefix, work with specific selector
  var selector;

  if (idPrefix && idPrefix != '') {
    selector = 'select[id="' + idPrefix + 'cam-script-type"]';
  } else {
    selector = 'select[name="scriptType"]';
  }

  return utils.selectedType(selector, node.parentElement);
}


module.exports = function(scriptLanguagePropName, scriptValuePropName, isFormatRequired, translate, options) {
  var idPrefix = options && options.idPrefix || '';

  return {
    template:
    '<div class="bpp-row bpp-textfield">' +
      '<label for="' + idPrefix + 'cam-script-format">' + escapeHTML(translate('Script Format')) + '</label>' +
      '<div class="bpp-field-wrapper">' +
        '<input id="' + idPrefix + 'cam-script-format" type="text" name="scriptFormat" />' +
        '<button class="action-button clear" data-action="script.clearScriptFormat" data-show="script.canClearScriptFormat">' +
          '<span>X</span>' +
        '</button>' +
      '</div>' +
    '</div>' +

    '<div class="bpp-row">' +
      '<label for="' + idPrefix + 'cam-script-type">' + escapeHTML(translate('Script Type')) + '</label>' +
      '<div class="bpp-field-wrapper">' +
        '<select id="' + idPrefix + 'cam-script-type" name="scriptType" data-value>' +
          '<option value="script" selected>' + escapeHTML(translate('Inline Script')) + '</option>' +
          '<option value="scriptResource">' + escapeHTML(translate('External Resource')) + '</option>' +
        '</select>' +
      '</div>' +
    '</div>' +

    '<div class="bpp-row bpp-textfield">' +
      '<label for="' + idPrefix + 'cam-script-resource-val" data-show="script.isScriptResource">' + escapeHTML(translate('Resource')) + '</label>' +
      '<div class="bpp-field-wrapper" data-show="script.isScriptResource">' +
        '<input id="' + idPrefix + 'cam-script-resource-val" type="text" name="scriptResourceValue" />' +
        '<button class="action-button clear" data-action="script.clearScriptResource" data-show="script.canClearScriptResource">' +
          '<span>X</span>' +
        '</button>' +
      '</div>' +
    '</div>' +

    '<div class="bpp-row">' +
      '<label for="' + idPrefix + 'cam-script-val" data-show="script.isScript">' + escapeHTML(translate('Script')) + '</label>' +
      '<div class="bpp-field-wrapper" data-show="script.isScript">' +
        '<textarea id="' + idPrefix + 'cam-script-val" type="text" name="scriptValue"></textarea>' +
      '</div>'+
    '</div>',

    get: function(element, bo) {
      var values = {};

      // read values from xml:
      var boScriptResource = bo.get('camunda:resource'),
          boScript = bo.get(scriptValuePropName),
          boScriptFormat = bo.get(scriptLanguagePropName);

      if (typeof boScriptResource !== 'undefined') {
        values.scriptResourceValue = boScriptResource;
        values.scriptType = 'scriptResource';
      } else {
        values.scriptValue = boScript;
        values.scriptType = 'script';
      }

      values.scriptFormat = boScriptFormat;

      return values;
    },

    set: function(element, values, containerElement) {
      var scriptFormat = values.scriptFormat,
          scriptType = values.scriptType,
          scriptResourceValue = values.scriptResourceValue,
          scriptValue = values.scriptValue;

      // init update
      var update = {
        'camunda:resource': undefined
      };
      update[scriptValuePropName] = undefined;
      update[scriptLanguagePropName] = undefined;

      if (isFormatRequired) {

        // always set language
        update[scriptLanguagePropName] = scriptFormat || '';
      } else

      // set language only when scriptFormat has a value
      if (scriptFormat !== '') {
        update[scriptLanguagePropName] = scriptFormat;
      }

      // set either inline script or resource
      if ('scriptResource' === scriptType) {
        update['camunda:resource'] = scriptResourceValue || '';
      } else {
        update[scriptValuePropName] = scriptValue || '';
      }

      return update;
    },

    validate: function(element, values) {
      var validationResult = {};

      if (values.scriptType === 'script' && !values.scriptValue) {
        validationResult.scriptValue = translate('Must provide a value');
      }

      if (values.scriptType === 'scriptResource' && !values.scriptResourceValue) {
        validationResult.scriptResourceValue = translate('Must provide a value');
      }

      if (isFormatRequired && (!values.scriptFormat || values.scriptFormat.length === 0)) {
        validationResult.scriptFormat = translate('Must provide a value');
      }

      return validationResult;
    },

    clearScriptFormat: function(element, inputNode, btnNode, scopeNode) {
      domQuery('input[name=scriptFormat]', scopeNode).value='';

      return true;
    },

    canClearScriptFormat: function(element, inputNode, btnNode, scopeNode) {
      var input = domQuery('input[name=scriptFormat]', scopeNode);

      return input.value !== '';
    },

    clearScriptResource: function(element, inputNode, btnNode, scopeNode) {
      domQuery('input[name=scriptResourceValue]', scopeNode).value='';

      return true;
    },

    canClearScriptResource: function(element, inputNode, btnNode, scopeNode) {
      var input = domQuery('input[name=scriptResourceValue]', scopeNode);

      return input.value !== '';
    },

    clearScript: function(element, inputNode, btnNode, scopeNode) {
      domQuery('textarea[name=scriptValue]', scopeNode).value='';

      return true;
    },

    canClearScript: function(element, inputNode, btnNode, scopeNode) {
      var input = domQuery('textarea[name=scriptValue]', scopeNode);

      return input.value !== '';
    },

    isScriptResource: function(element, inputNode, btnNode, scopeNode) {
      var scriptType = getScriptType(scopeNode, idPrefix);
      return scriptType === 'scriptResource';
    },

    isScript: function(element, inputNode, btnNode, scopeNode) {
      var scriptType = getScriptType(scopeNode, idPrefix);
      return scriptType === 'script';
    }

  };

};


/***/ }),

/***/ "./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Tasklist.js":
/*!*****************************************************************************************************!*\
  !*** ./node_modules/bpmn-js-properties-panel/lib/provider/camunda/parts/implementation/Tasklist.js ***!
  \*****************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


var entryFactory = __webpack_require__(/*! ../../../../factory/EntryFactory */ "./node_modules/bpmn-js-properties-panel/lib/factory/EntryFactory.js");

var cmdHelper = __webpack_require__(/*! ../../../../helper/CmdHelper */ "./node_modules/bpmn-js-properties-panel/lib/helper/CmdHelper.js");

module.exports = function(element, bpmnFactory, options, translate) {

  var getBusinessObject = options.getBusinessObject;

  var isStartableInTasklistEntry = entryFactory.checkbox(translate, {
    id: 'isStartableInTasklist',
    label: translate('Startable'),
    modelProperty: 'isStartableInTasklist',

    get: function(element, node) {
      var bo = getBusinessObject(element);
      var isStartableInTasklist = bo.get('camunda:isStartableInTasklist');

      return {
        isStartableInTasklist: isStartableInTasklist ? isStartableInTasklist : ''
      };
    },

    set: function(element, values) {
      var bo = getBusinessObject(element);
      return cmdHelper.updateBusinessObject(element, bo, {
        'camunda:isStartableInTasklist': !!values.isStartableInTasklist
      });
    }

  });

  return [
    isStartableInTasklistEntry
  ];
};


/***/ }),

/***/ "./node_modules/bpmn-js/lib/features/modeling/util/ModelingUtil.js":
/*!*************************************************************************!*\
  !*** ./node_modules/bpmn-js/lib/features/modeling/util/ModelingUtil.js ***!
  \*************************************************************************/
/*! exports provided: isAny, getParent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "isAny", function() { return isAny; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getParent", function() { return getParent; });
/* harmony import */ var min_dash__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js");
/* harmony import */ var _util_ModelUtil__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../../util/ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js");





/**
 * Return true if element has any of the given types.
 *
 * @param {djs.model.Base} element
 * @param {Array<string>} types
 *
 * @return {boolean}
 */
function isAny(element, types) {
  return Object(min_dash__WEBPACK_IMPORTED_MODULE_0__["some"])(types, function(t) {
    return Object(_util_ModelUtil__WEBPACK_IMPORTED_MODULE_1__["is"])(element, t);
  });
}


/**
 * Return the parent of the element with any of the given types.
 *
 * @param {djs.model.Base} element
 * @param {string|Array<string>} anyType
 *
 * @return {djs.model.Base}
 */
function getParent(element, anyType) {

  if (typeof anyType === 'string') {
    anyType = [ anyType ];
  }

  while ((element = element.parent)) {
    if (isAny(element, anyType)) {
      return element;
    }
  }

  return null;
}

/***/ }),

/***/ "./node_modules/bpmn-js/lib/util/DiUtil.js":
/*!*************************************************!*\
  !*** ./node_modules/bpmn-js/lib/util/DiUtil.js ***!
  \*************************************************/
/*! exports provided: isExpanded, isInterrupting, isEventSubProcess, hasEventDefinition, hasErrorEventDefinition, hasEscalationEventDefinition, hasCompensateEventDefinition */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "isExpanded", function() { return isExpanded; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "isInterrupting", function() { return isInterrupting; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "isEventSubProcess", function() { return isEventSubProcess; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "hasEventDefinition", function() { return hasEventDefinition; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "hasErrorEventDefinition", function() { return hasErrorEventDefinition; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "hasEscalationEventDefinition", function() { return hasEscalationEventDefinition; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "hasCompensateEventDefinition", function() { return hasCompensateEventDefinition; });
/* harmony import */ var _ModelUtil__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./ModelUtil */ "./node_modules/bpmn-js/lib/util/ModelUtil.js");
/* harmony import */ var min_dash__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! min-dash */ "./node_modules/min-dash/dist/index.esm.js");





function isExpanded(element) {

  if (Object(_ModelUtil__WEBPACK_IMPORTED_MODULE_0__["is"])(element, 'bpmn:CallActivity')) {
    return false;
  }

  if (Object(_ModelUtil__WEBPACK_IMPORTED_MODULE_0__["is"])(element, 'bpmn:SubProcess')) {
    return Object(_ModelUtil__WEBPACK_IMPORTED_MODULE_0__["getBusinessObject"])(element).di && !!Object(_ModelUtil__WEBPACK_IMPORTED_MODULE_0__["getBusinessObject"])(element).di.isExpanded;
  }

  if (Object(_ModelUtil__WEBPACK_IMPORTED_MODULE_0__["is"])(element, 'bpmn:Participant')) {
    return !!Object(_ModelUtil__WEBPACK_IMPORTED_MODULE_0__["getBusinessObject"])(element).processRef;
  }

  return true;
}

function isInterrupting(element) {
  return element && Object(_ModelUtil__WEBPACK_IMPORTED_MODULE_0__["getBusinessObject"])(element).isInterrupting !== false;
}

function isEventSubProcess(element) {
  return element && !!Object(_ModelUtil__WEBPACK_IMPORTED_MODULE_0__["getBusinessObject"])(element).triggeredByEvent;
}

function hasEventDefinition(element, eventType) {
  var bo = Object(_ModelUtil__WEBPACK_IMPORTED_MODULE_0__["getBusinessObject"])(element),
      hasEventDefinition = false;

  if (bo.eventDefinitions) {
    Object(min_dash__WEBPACK_IMPORTED_MODULE_1__["forEach"])(bo.eventDefinitions, function(event) {
      if (Object(_ModelUtil__WEBPACK_IMPORTED_MODULE_0__["is"])(event, eventType)) {
        hasEventDefinition = true;
      }
    });
  }

  return hasEventDefinition;
}

function hasErrorEventDefinition(element) {
  return hasEventDefinition(element, 'bpmn:ErrorEventDefinition');
}

function hasEscalationEventDefinition(element) {
  return hasEventDefinition(element, 'bpmn:EscalationEventDefinition');
}

function hasCompensateEventDefinition(element) {
  return hasEventDefinition(element, 'bpmn:CompensateEventDefinition');
}


/***/ }),

/***/ "./node_modules/bpmn-js/lib/util/ModelUtil.js":
/*!****************************************************!*\
  !*** ./node_modules/bpmn-js/lib/util/ModelUtil.js ***!
  \****************************************************/
/*! exports provided: is, getBusinessObject */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "is", function() { return is; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getBusinessObject", function() { return getBusinessObject; });
/**
 * Is an element of the given BPMN type?
 *
 * @param  {djs.model.Base|ModdleElement} element
 * @param  {string} type
 *
 * @return {boolean}
 */
function is(element, type) {
  var bo = getBusinessObject(element);

  return bo && (typeof bo.$instanceOf === 'function') && bo.$instanceOf(type);
}


/**
 * Return the business object for a given element.
 *
 * @param  {djs.model.Base|ModdleElement} element
 *
 * @return {ModdleElement}
 */
function getBusinessObject(element) {
  return (element && element.businessObject) || element;
}

/***/ }),

/***/ "./node_modules/camunda-modeler-plugin-helpers/index.js":
/*!**************************************************************!*\
  !*** ./node_modules/camunda-modeler-plugin-helpers/index.js ***!
  \**************************************************************/
/*! exports provided: registerClientPlugin, registerBpmnJSPlugin, registerBpmnJSModdleExtension, getModelerDirectory, getPluginsDirectory */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "registerClientPlugin", function() { return registerClientPlugin; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "registerBpmnJSPlugin", function() { return registerBpmnJSPlugin; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "registerBpmnJSModdleExtension", function() { return registerBpmnJSModdleExtension; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getModelerDirectory", function() { return getModelerDirectory; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getPluginsDirectory", function() { return getPluginsDirectory; });
/**
 * Validate and register a client plugin.
 *
 * @param {Object} plugin
 * @param {String} type
 */
function registerClientPlugin(plugin, type) {
  var plugins = window.plugins || [];
  window.plugins = plugins;

  if (!plugin) {
    throw new Error('plugin not specified');
  }

  if (!type) {
    throw new Error('type not specified');
  }

  plugins.push({
    plugin: plugin,
    type: type
  });
}

/**
 * Validate and register a bpmn-js plugin.
 *
 * @param {Object} module
 *
 * @example
 *
 * import {
 *   registerBpmnJSPlugin
 * } from 'camunda-modeler-plugin-helpers';
 *
 * const BpmnJSModule = {
 *   __init__: [ 'myService' ],
 *   myService: [ 'type', ... ]
 * };
 *
 * registerBpmnJSPlugin(BpmnJSModule);
 */
function registerBpmnJSPlugin(module) {
  registerClientPlugin(module, 'bpmn.modeler.additionalModules');
}

/**
 * Validate and register a bpmn-moddle extension plugin.
 *
 * @param {Object} descriptor
 *
 * @example
 * import {
 *   registerBpmnJSModdleExtension
 * } from 'camunda-modeler-plugin-helpers';
 *
 * var moddleDescriptor = {
 *   name: 'my descriptor',
 *   uri: 'http://example.my.company.localhost/schema/my-descriptor/1.0',
 *   prefix: 'mydesc',
 *
 *   ...
 * };
 *
 * registerBpmnJSModdleExtension(moddleDescriptor);
 */
function registerBpmnJSModdleExtension(descriptor) {
  registerClientPlugin(descriptor, 'bpmn.modeler.moddleExtension');
}

/**
 * Return the modeler directory, as a string.
 *
 * @deprecated Will be removed in future Camunda Modeler versions without replacement.
 *
 * @return {String}
 */
function getModelerDirectory() {
  return window.getModelerDirectory();
}

/**
 * Return the modeler plugin directory, as a string.
 *
 * @deprecated Will be removed in future Camunda Modeler versions without replacement.
 *
 * @return {String}
 */
function getPluginsDirectory() {
  return window.getPluginsDirectory();
}

/***/ }),

/***/ "./node_modules/clipboard-copy/index.js":
/*!**********************************************!*\
  !*** ./node_modules/clipboard-copy/index.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/* global DOMException */

module.exports = clipboardCopy

function clipboardCopy (text) {
  // Use the Async Clipboard API when available. Requires a secure browing
  // context (i.e. HTTPS)
  if (navigator.clipboard) {
    return navigator.clipboard.writeText(text).catch(function (err) {
      throw (err !== undefined ? err : new DOMException('The request is not allowed', 'NotAllowedError'))
    })
  }

  // ...Otherwise, use document.execCommand() fallback

  // Put the text to copy into a <span>
  var span = document.createElement('span')
  span.textContent = text

  // Preserve consecutive spaces and newlines
  span.style.whiteSpace = 'pre'

  // Add the <span> to the page
  document.body.appendChild(span)

  // Make a selection object representing the range of text selected by the user
  var selection = window.getSelection()
  var range = window.document.createRange()
  selection.removeAllRanges()
  range.selectNode(span)
  selection.addRange(range)

  // Copy text to the clipboard
  var success = false
  try {
    success = window.document.execCommand('copy')
  } catch (err) {
    console.log('error', err)
  }

  // Cleanup
  selection.removeAllRanges()
  window.document.body.removeChild(span)

  return success
    ? Promise.resolve()
    : Promise.reject(new DOMException('The request is not allowed', 'NotAllowedError'))
}


/***/ }),

/***/ "./node_modules/diagram-js/lib/util/Collections.js":
/*!*********************************************************!*\
  !*** ./node_modules/diagram-js/lib/util/Collections.js ***!
  \*********************************************************/
/*! exports provided: remove, add, indexOf */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "remove", function() { return remove; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "add", function() { return add; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "indexOf", function() { return indexOf; });
/**
 * Failsafe remove an element from a collection
 *
 * @param  {Array<Object>} [collection]
 * @param  {Object} [element]
 *
 * @return {number} the previous index of the element
 */
function remove(collection, element) {

  if (!collection || !element) {
    return -1;
  }

  var idx = collection.indexOf(element);

  if (idx !== -1) {
    collection.splice(idx, 1);
  }

  return idx;
}

/**
 * Fail save add an element to the given connection, ensuring
 * it does not yet exist.
 *
 * @param {Array<Object>} collection
 * @param {Object} element
 * @param {number} idx
 */
function add(collection, element, idx) {

  if (!collection || !element) {
    return;
  }

  if (typeof idx !== 'number') {
    idx = -1;
  }

  var currentIdx = collection.indexOf(element);

  if (currentIdx !== -1) {

    if (currentIdx === idx) {

      // nothing to do, position has not changed
      return;
    } else {

      if (idx !== -1) {

        // remove from current position
        collection.splice(currentIdx, 1);
      } else {

        // already exists in collection
        return;
      }
    }
  }

  if (idx !== -1) {

    // insert at specified position
    collection.splice(idx, 0, element);
  } else {

    // push to end
    collection.push(element);
  }
}


/**
 * Fail save get the index of an element in a collection.
 *
 * @param {Array<Object>} collection
 * @param {Object} element
 *
 * @return {number} the index or -1 if collection or element do
 *                  not exist or the element is not contained.
 */
function indexOf(collection, element) {

  if (!collection || !element) {
    return -1;
  }

  return collection.indexOf(element);
}


/***/ }),

/***/ "./node_modules/ids/dist/index.esm.js":
/*!********************************************!*\
  !*** ./node_modules/ids/dist/index.esm.js ***!
  \********************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
function createCommonjsModule(fn, module) {
	return module = { exports: {} }, fn(module, module.exports), module.exports;
}

var hat_1 = createCommonjsModule(function (module) {
var hat = module.exports = function (bits, base) {
    if (!base) base = 16;
    if (bits === undefined) bits = 128;
    if (bits <= 0) return '0';
    
    var digits = Math.log(Math.pow(2, bits)) / Math.log(base);
    for (var i = 2; digits === Infinity; i *= 2) {
        digits = Math.log(Math.pow(2, bits / i)) / Math.log(base) * i;
    }
    
    var rem = digits - Math.floor(digits);
    
    var res = '';
    
    for (var i = 0; i < Math.floor(digits); i++) {
        var x = Math.floor(Math.random() * base).toString(base);
        res = x + res;
    }
    
    if (rem) {
        var b = Math.pow(base, rem);
        var x = Math.floor(Math.random() * b).toString(base);
        res = x + res;
    }
    
    var parsed = parseInt(res, base);
    if (parsed !== Infinity && parsed >= Math.pow(2, bits)) {
        return hat(bits, base)
    }
    else return res;
};

hat.rack = function (bits, base, expandBy) {
    var fn = function (data) {
        var iters = 0;
        do {
            if (iters ++ > 10) {
                if (expandBy) bits += expandBy;
                else throw new Error('too many ID collisions, use more bits')
            }
            
            var id = hat(bits, base);
        } while (Object.hasOwnProperty.call(hats, id));
        
        hats[id] = data;
        return id;
    };
    var hats = fn.hats = {};
    
    fn.get = function (id) {
        return fn.hats[id];
    };
    
    fn.set = function (id, value) {
        fn.hats[id] = value;
        return fn;
    };
    
    fn.bits = bits || 128;
    fn.base = base || 16;
    return fn;
};
});

/**
 * Create a new id generator / cache instance.
 *
 * You may optionally provide a seed that is used internally.
 *
 * @param {Seed} seed
 */

function Ids(seed) {
  if (!(this instanceof Ids)) {
    return new Ids(seed);
  }

  seed = seed || [128, 36, 1];
  this._seed = seed.length ? hat_1.rack(seed[0], seed[1], seed[2]) : seed;
}
/**
 * Generate a next id.
 *
 * @param {Object} [element] element to bind the id to
 *
 * @return {String} id
 */

Ids.prototype.next = function (element) {
  return this._seed(element || true);
};
/**
 * Generate a next id with a given prefix.
 *
 * @param {Object} [element] element to bind the id to
 *
 * @return {String} id
 */


Ids.prototype.nextPrefixed = function (prefix, element) {
  var id;

  do {
    id = prefix + this.next(true);
  } while (this.assigned(id)); // claim {prefix}{random}


  this.claim(id, element); // return

  return id;
};
/**
 * Manually claim an existing id.
 *
 * @param {String} id
 * @param {String} [element] element the id is claimed by
 */


Ids.prototype.claim = function (id, element) {
  this._seed.set(id, element || true);
};
/**
 * Returns true if the given id has already been assigned.
 *
 * @param  {String} id
 * @return {Boolean}
 */


Ids.prototype.assigned = function (id) {
  return this._seed.get(id) || false;
};
/**
 * Unclaim an id.
 *
 * @param  {String} id the id to unclaim
 */


Ids.prototype.unclaim = function (id) {
  delete this._seed.hats[id];
};
/**
 * Clear all claimed ids.
 */


Ids.prototype.clear = function () {
  var hats = this._seed.hats,
      id;

  for (id in hats) {
    this.unclaim(id);
  }
};

/* harmony default export */ __webpack_exports__["default"] = (Ids);
//# sourceMappingURL=index.esm.js.map


/***/ }),

/***/ "./node_modules/inherits/inherits_browser.js":
/*!***************************************************!*\
  !*** ./node_modules/inherits/inherits_browser.js ***!
  \***************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

if (typeof Object.create === 'function') {
  // implementation from standard node.js 'util' module
  module.exports = function inherits(ctor, superCtor) {
    if (superCtor) {
      ctor.super_ = superCtor
      ctor.prototype = Object.create(superCtor.prototype, {
        constructor: {
          value: ctor,
          enumerable: false,
          writable: true,
          configurable: true
        }
      })
    }
  };
} else {
  // old school shim for old browsers
  module.exports = function inherits(ctor, superCtor) {
    if (superCtor) {
      ctor.super_ = superCtor
      var TempCtor = function () {}
      TempCtor.prototype = superCtor.prototype
      ctor.prototype = new TempCtor()
      ctor.prototype.constructor = ctor
    }
  }
}


/***/ }),

/***/ "./node_modules/lodash/_DataView.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_DataView.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getNative = __webpack_require__(/*! ./_getNative */ "./node_modules/lodash/_getNative.js"),
    root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js");

/* Built-in method references that are verified to be native. */
var DataView = getNative(root, 'DataView');

module.exports = DataView;


/***/ }),

/***/ "./node_modules/lodash/_Hash.js":
/*!**************************************!*\
  !*** ./node_modules/lodash/_Hash.js ***!
  \**************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var hashClear = __webpack_require__(/*! ./_hashClear */ "./node_modules/lodash/_hashClear.js"),
    hashDelete = __webpack_require__(/*! ./_hashDelete */ "./node_modules/lodash/_hashDelete.js"),
    hashGet = __webpack_require__(/*! ./_hashGet */ "./node_modules/lodash/_hashGet.js"),
    hashHas = __webpack_require__(/*! ./_hashHas */ "./node_modules/lodash/_hashHas.js"),
    hashSet = __webpack_require__(/*! ./_hashSet */ "./node_modules/lodash/_hashSet.js");

/**
 * Creates a hash object.
 *
 * @private
 * @constructor
 * @param {Array} [entries] The key-value pairs to cache.
 */
function Hash(entries) {
  var index = -1,
      length = entries == null ? 0 : entries.length;

  this.clear();
  while (++index < length) {
    var entry = entries[index];
    this.set(entry[0], entry[1]);
  }
}

// Add methods to `Hash`.
Hash.prototype.clear = hashClear;
Hash.prototype['delete'] = hashDelete;
Hash.prototype.get = hashGet;
Hash.prototype.has = hashHas;
Hash.prototype.set = hashSet;

module.exports = Hash;


/***/ }),

/***/ "./node_modules/lodash/_LazyWrapper.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_LazyWrapper.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseCreate = __webpack_require__(/*! ./_baseCreate */ "./node_modules/lodash/_baseCreate.js"),
    baseLodash = __webpack_require__(/*! ./_baseLodash */ "./node_modules/lodash/_baseLodash.js");

/** Used as references for the maximum length and index of an array. */
var MAX_ARRAY_LENGTH = 4294967295;

/**
 * Creates a lazy wrapper object which wraps `value` to enable lazy evaluation.
 *
 * @private
 * @constructor
 * @param {*} value The value to wrap.
 */
function LazyWrapper(value) {
  this.__wrapped__ = value;
  this.__actions__ = [];
  this.__dir__ = 1;
  this.__filtered__ = false;
  this.__iteratees__ = [];
  this.__takeCount__ = MAX_ARRAY_LENGTH;
  this.__views__ = [];
}

// Ensure `LazyWrapper` is an instance of `baseLodash`.
LazyWrapper.prototype = baseCreate(baseLodash.prototype);
LazyWrapper.prototype.constructor = LazyWrapper;

module.exports = LazyWrapper;


/***/ }),

/***/ "./node_modules/lodash/_ListCache.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_ListCache.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var listCacheClear = __webpack_require__(/*! ./_listCacheClear */ "./node_modules/lodash/_listCacheClear.js"),
    listCacheDelete = __webpack_require__(/*! ./_listCacheDelete */ "./node_modules/lodash/_listCacheDelete.js"),
    listCacheGet = __webpack_require__(/*! ./_listCacheGet */ "./node_modules/lodash/_listCacheGet.js"),
    listCacheHas = __webpack_require__(/*! ./_listCacheHas */ "./node_modules/lodash/_listCacheHas.js"),
    listCacheSet = __webpack_require__(/*! ./_listCacheSet */ "./node_modules/lodash/_listCacheSet.js");

/**
 * Creates an list cache object.
 *
 * @private
 * @constructor
 * @param {Array} [entries] The key-value pairs to cache.
 */
function ListCache(entries) {
  var index = -1,
      length = entries == null ? 0 : entries.length;

  this.clear();
  while (++index < length) {
    var entry = entries[index];
    this.set(entry[0], entry[1]);
  }
}

// Add methods to `ListCache`.
ListCache.prototype.clear = listCacheClear;
ListCache.prototype['delete'] = listCacheDelete;
ListCache.prototype.get = listCacheGet;
ListCache.prototype.has = listCacheHas;
ListCache.prototype.set = listCacheSet;

module.exports = ListCache;


/***/ }),

/***/ "./node_modules/lodash/_LodashWrapper.js":
/*!***********************************************!*\
  !*** ./node_modules/lodash/_LodashWrapper.js ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseCreate = __webpack_require__(/*! ./_baseCreate */ "./node_modules/lodash/_baseCreate.js"),
    baseLodash = __webpack_require__(/*! ./_baseLodash */ "./node_modules/lodash/_baseLodash.js");

/**
 * The base constructor for creating `lodash` wrapper objects.
 *
 * @private
 * @param {*} value The value to wrap.
 * @param {boolean} [chainAll] Enable explicit method chain sequences.
 */
function LodashWrapper(value, chainAll) {
  this.__wrapped__ = value;
  this.__actions__ = [];
  this.__chain__ = !!chainAll;
  this.__index__ = 0;
  this.__values__ = undefined;
}

LodashWrapper.prototype = baseCreate(baseLodash.prototype);
LodashWrapper.prototype.constructor = LodashWrapper;

module.exports = LodashWrapper;


/***/ }),

/***/ "./node_modules/lodash/_Map.js":
/*!*************************************!*\
  !*** ./node_modules/lodash/_Map.js ***!
  \*************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getNative = __webpack_require__(/*! ./_getNative */ "./node_modules/lodash/_getNative.js"),
    root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js");

/* Built-in method references that are verified to be native. */
var Map = getNative(root, 'Map');

module.exports = Map;


/***/ }),

/***/ "./node_modules/lodash/_MapCache.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_MapCache.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var mapCacheClear = __webpack_require__(/*! ./_mapCacheClear */ "./node_modules/lodash/_mapCacheClear.js"),
    mapCacheDelete = __webpack_require__(/*! ./_mapCacheDelete */ "./node_modules/lodash/_mapCacheDelete.js"),
    mapCacheGet = __webpack_require__(/*! ./_mapCacheGet */ "./node_modules/lodash/_mapCacheGet.js"),
    mapCacheHas = __webpack_require__(/*! ./_mapCacheHas */ "./node_modules/lodash/_mapCacheHas.js"),
    mapCacheSet = __webpack_require__(/*! ./_mapCacheSet */ "./node_modules/lodash/_mapCacheSet.js");

/**
 * Creates a map cache object to store key-value pairs.
 *
 * @private
 * @constructor
 * @param {Array} [entries] The key-value pairs to cache.
 */
function MapCache(entries) {
  var index = -1,
      length = entries == null ? 0 : entries.length;

  this.clear();
  while (++index < length) {
    var entry = entries[index];
    this.set(entry[0], entry[1]);
  }
}

// Add methods to `MapCache`.
MapCache.prototype.clear = mapCacheClear;
MapCache.prototype['delete'] = mapCacheDelete;
MapCache.prototype.get = mapCacheGet;
MapCache.prototype.has = mapCacheHas;
MapCache.prototype.set = mapCacheSet;

module.exports = MapCache;


/***/ }),

/***/ "./node_modules/lodash/_Promise.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_Promise.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getNative = __webpack_require__(/*! ./_getNative */ "./node_modules/lodash/_getNative.js"),
    root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js");

/* Built-in method references that are verified to be native. */
var Promise = getNative(root, 'Promise');

module.exports = Promise;


/***/ }),

/***/ "./node_modules/lodash/_Set.js":
/*!*************************************!*\
  !*** ./node_modules/lodash/_Set.js ***!
  \*************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getNative = __webpack_require__(/*! ./_getNative */ "./node_modules/lodash/_getNative.js"),
    root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js");

/* Built-in method references that are verified to be native. */
var Set = getNative(root, 'Set');

module.exports = Set;


/***/ }),

/***/ "./node_modules/lodash/_SetCache.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_SetCache.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var MapCache = __webpack_require__(/*! ./_MapCache */ "./node_modules/lodash/_MapCache.js"),
    setCacheAdd = __webpack_require__(/*! ./_setCacheAdd */ "./node_modules/lodash/_setCacheAdd.js"),
    setCacheHas = __webpack_require__(/*! ./_setCacheHas */ "./node_modules/lodash/_setCacheHas.js");

/**
 *
 * Creates an array cache object to store unique values.
 *
 * @private
 * @constructor
 * @param {Array} [values] The values to cache.
 */
function SetCache(values) {
  var index = -1,
      length = values == null ? 0 : values.length;

  this.__data__ = new MapCache;
  while (++index < length) {
    this.add(values[index]);
  }
}

// Add methods to `SetCache`.
SetCache.prototype.add = SetCache.prototype.push = setCacheAdd;
SetCache.prototype.has = setCacheHas;

module.exports = SetCache;


/***/ }),

/***/ "./node_modules/lodash/_Stack.js":
/*!***************************************!*\
  !*** ./node_modules/lodash/_Stack.js ***!
  \***************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var ListCache = __webpack_require__(/*! ./_ListCache */ "./node_modules/lodash/_ListCache.js"),
    stackClear = __webpack_require__(/*! ./_stackClear */ "./node_modules/lodash/_stackClear.js"),
    stackDelete = __webpack_require__(/*! ./_stackDelete */ "./node_modules/lodash/_stackDelete.js"),
    stackGet = __webpack_require__(/*! ./_stackGet */ "./node_modules/lodash/_stackGet.js"),
    stackHas = __webpack_require__(/*! ./_stackHas */ "./node_modules/lodash/_stackHas.js"),
    stackSet = __webpack_require__(/*! ./_stackSet */ "./node_modules/lodash/_stackSet.js");

/**
 * Creates a stack cache object to store key-value pairs.
 *
 * @private
 * @constructor
 * @param {Array} [entries] The key-value pairs to cache.
 */
function Stack(entries) {
  var data = this.__data__ = new ListCache(entries);
  this.size = data.size;
}

// Add methods to `Stack`.
Stack.prototype.clear = stackClear;
Stack.prototype['delete'] = stackDelete;
Stack.prototype.get = stackGet;
Stack.prototype.has = stackHas;
Stack.prototype.set = stackSet;

module.exports = Stack;


/***/ }),

/***/ "./node_modules/lodash/_Symbol.js":
/*!****************************************!*\
  !*** ./node_modules/lodash/_Symbol.js ***!
  \****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js");

/** Built-in value references. */
var Symbol = root.Symbol;

module.exports = Symbol;


/***/ }),

/***/ "./node_modules/lodash/_Uint8Array.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_Uint8Array.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js");

/** Built-in value references. */
var Uint8Array = root.Uint8Array;

module.exports = Uint8Array;


/***/ }),

/***/ "./node_modules/lodash/_WeakMap.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_WeakMap.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getNative = __webpack_require__(/*! ./_getNative */ "./node_modules/lodash/_getNative.js"),
    root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js");

/* Built-in method references that are verified to be native. */
var WeakMap = getNative(root, 'WeakMap');

module.exports = WeakMap;


/***/ }),

/***/ "./node_modules/lodash/_apply.js":
/*!***************************************!*\
  !*** ./node_modules/lodash/_apply.js ***!
  \***************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * A faster alternative to `Function#apply`, this function invokes `func`
 * with the `this` binding of `thisArg` and the arguments of `args`.
 *
 * @private
 * @param {Function} func The function to invoke.
 * @param {*} thisArg The `this` binding of `func`.
 * @param {Array} args The arguments to invoke `func` with.
 * @returns {*} Returns the result of `func`.
 */
function apply(func, thisArg, args) {
  switch (args.length) {
    case 0: return func.call(thisArg);
    case 1: return func.call(thisArg, args[0]);
    case 2: return func.call(thisArg, args[0], args[1]);
    case 3: return func.call(thisArg, args[0], args[1], args[2]);
  }
  return func.apply(thisArg, args);
}

module.exports = apply;


/***/ }),

/***/ "./node_modules/lodash/_arrayEach.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_arrayEach.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * A specialized version of `_.forEach` for arrays without support for
 * iteratee shorthands.
 *
 * @private
 * @param {Array} [array] The array to iterate over.
 * @param {Function} iteratee The function invoked per iteration.
 * @returns {Array} Returns `array`.
 */
function arrayEach(array, iteratee) {
  var index = -1,
      length = array == null ? 0 : array.length;

  while (++index < length) {
    if (iteratee(array[index], index, array) === false) {
      break;
    }
  }
  return array;
}

module.exports = arrayEach;


/***/ }),

/***/ "./node_modules/lodash/_arrayFilter.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_arrayFilter.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * A specialized version of `_.filter` for arrays without support for
 * iteratee shorthands.
 *
 * @private
 * @param {Array} [array] The array to iterate over.
 * @param {Function} predicate The function invoked per iteration.
 * @returns {Array} Returns the new filtered array.
 */
function arrayFilter(array, predicate) {
  var index = -1,
      length = array == null ? 0 : array.length,
      resIndex = 0,
      result = [];

  while (++index < length) {
    var value = array[index];
    if (predicate(value, index, array)) {
      result[resIndex++] = value;
    }
  }
  return result;
}

module.exports = arrayFilter;


/***/ }),

/***/ "./node_modules/lodash/_arrayIncludes.js":
/*!***********************************************!*\
  !*** ./node_modules/lodash/_arrayIncludes.js ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseIndexOf = __webpack_require__(/*! ./_baseIndexOf */ "./node_modules/lodash/_baseIndexOf.js");

/**
 * A specialized version of `_.includes` for arrays without support for
 * specifying an index to search from.
 *
 * @private
 * @param {Array} [array] The array to inspect.
 * @param {*} target The value to search for.
 * @returns {boolean} Returns `true` if `target` is found, else `false`.
 */
function arrayIncludes(array, value) {
  var length = array == null ? 0 : array.length;
  return !!length && baseIndexOf(array, value, 0) > -1;
}

module.exports = arrayIncludes;


/***/ }),

/***/ "./node_modules/lodash/_arrayLikeKeys.js":
/*!***********************************************!*\
  !*** ./node_modules/lodash/_arrayLikeKeys.js ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseTimes = __webpack_require__(/*! ./_baseTimes */ "./node_modules/lodash/_baseTimes.js"),
    isArguments = __webpack_require__(/*! ./isArguments */ "./node_modules/lodash/isArguments.js"),
    isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js"),
    isBuffer = __webpack_require__(/*! ./isBuffer */ "./node_modules/lodash/isBuffer.js"),
    isIndex = __webpack_require__(/*! ./_isIndex */ "./node_modules/lodash/_isIndex.js"),
    isTypedArray = __webpack_require__(/*! ./isTypedArray */ "./node_modules/lodash/isTypedArray.js");

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/**
 * Creates an array of the enumerable property names of the array-like `value`.
 *
 * @private
 * @param {*} value The value to query.
 * @param {boolean} inherited Specify returning inherited property names.
 * @returns {Array} Returns the array of property names.
 */
function arrayLikeKeys(value, inherited) {
  var isArr = isArray(value),
      isArg = !isArr && isArguments(value),
      isBuff = !isArr && !isArg && isBuffer(value),
      isType = !isArr && !isArg && !isBuff && isTypedArray(value),
      skipIndexes = isArr || isArg || isBuff || isType,
      result = skipIndexes ? baseTimes(value.length, String) : [],
      length = result.length;

  for (var key in value) {
    if ((inherited || hasOwnProperty.call(value, key)) &&
        !(skipIndexes && (
           // Safari 9 has enumerable `arguments.length` in strict mode.
           key == 'length' ||
           // Node.js 0.10 has enumerable non-index properties on buffers.
           (isBuff && (key == 'offset' || key == 'parent')) ||
           // PhantomJS 2 has enumerable non-index properties on typed arrays.
           (isType && (key == 'buffer' || key == 'byteLength' || key == 'byteOffset')) ||
           // Skip index properties.
           isIndex(key, length)
        ))) {
      result.push(key);
    }
  }
  return result;
}

module.exports = arrayLikeKeys;


/***/ }),

/***/ "./node_modules/lodash/_arrayMap.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_arrayMap.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * A specialized version of `_.map` for arrays without support for iteratee
 * shorthands.
 *
 * @private
 * @param {Array} [array] The array to iterate over.
 * @param {Function} iteratee The function invoked per iteration.
 * @returns {Array} Returns the new mapped array.
 */
function arrayMap(array, iteratee) {
  var index = -1,
      length = array == null ? 0 : array.length,
      result = Array(length);

  while (++index < length) {
    result[index] = iteratee(array[index], index, array);
  }
  return result;
}

module.exports = arrayMap;


/***/ }),

/***/ "./node_modules/lodash/_arrayPush.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_arrayPush.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Appends the elements of `values` to `array`.
 *
 * @private
 * @param {Array} array The array to modify.
 * @param {Array} values The values to append.
 * @returns {Array} Returns `array`.
 */
function arrayPush(array, values) {
  var index = -1,
      length = values.length,
      offset = array.length;

  while (++index < length) {
    array[offset + index] = values[index];
  }
  return array;
}

module.exports = arrayPush;


/***/ }),

/***/ "./node_modules/lodash/_arraySome.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_arraySome.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * A specialized version of `_.some` for arrays without support for iteratee
 * shorthands.
 *
 * @private
 * @param {Array} [array] The array to iterate over.
 * @param {Function} predicate The function invoked per iteration.
 * @returns {boolean} Returns `true` if any element passes the predicate check,
 *  else `false`.
 */
function arraySome(array, predicate) {
  var index = -1,
      length = array == null ? 0 : array.length;

  while (++index < length) {
    if (predicate(array[index], index, array)) {
      return true;
    }
  }
  return false;
}

module.exports = arraySome;


/***/ }),

/***/ "./node_modules/lodash/_assignValue.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_assignValue.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseAssignValue = __webpack_require__(/*! ./_baseAssignValue */ "./node_modules/lodash/_baseAssignValue.js"),
    eq = __webpack_require__(/*! ./eq */ "./node_modules/lodash/eq.js");

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/**
 * Assigns `value` to `key` of `object` if the existing value is not equivalent
 * using [`SameValueZero`](http://ecma-international.org/ecma-262/7.0/#sec-samevaluezero)
 * for equality comparisons.
 *
 * @private
 * @param {Object} object The object to modify.
 * @param {string} key The key of the property to assign.
 * @param {*} value The value to assign.
 */
function assignValue(object, key, value) {
  var objValue = object[key];
  if (!(hasOwnProperty.call(object, key) && eq(objValue, value)) ||
      (value === undefined && !(key in object))) {
    baseAssignValue(object, key, value);
  }
}

module.exports = assignValue;


/***/ }),

/***/ "./node_modules/lodash/_assocIndexOf.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_assocIndexOf.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var eq = __webpack_require__(/*! ./eq */ "./node_modules/lodash/eq.js");

/**
 * Gets the index at which the `key` is found in `array` of key-value pairs.
 *
 * @private
 * @param {Array} array The array to inspect.
 * @param {*} key The key to search for.
 * @returns {number} Returns the index of the matched value, else `-1`.
 */
function assocIndexOf(array, key) {
  var length = array.length;
  while (length--) {
    if (eq(array[length][0], key)) {
      return length;
    }
  }
  return -1;
}

module.exports = assocIndexOf;


/***/ }),

/***/ "./node_modules/lodash/_baseAssignValue.js":
/*!*************************************************!*\
  !*** ./node_modules/lodash/_baseAssignValue.js ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var defineProperty = __webpack_require__(/*! ./_defineProperty */ "./node_modules/lodash/_defineProperty.js");

/**
 * The base implementation of `assignValue` and `assignMergeValue` without
 * value checks.
 *
 * @private
 * @param {Object} object The object to modify.
 * @param {string} key The key of the property to assign.
 * @param {*} value The value to assign.
 */
function baseAssignValue(object, key, value) {
  if (key == '__proto__' && defineProperty) {
    defineProperty(object, key, {
      'configurable': true,
      'enumerable': true,
      'value': value,
      'writable': true
    });
  } else {
    object[key] = value;
  }
}

module.exports = baseAssignValue;


/***/ }),

/***/ "./node_modules/lodash/_baseCreate.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_baseCreate.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var isObject = __webpack_require__(/*! ./isObject */ "./node_modules/lodash/isObject.js");

/** Built-in value references. */
var objectCreate = Object.create;

/**
 * The base implementation of `_.create` without support for assigning
 * properties to the created object.
 *
 * @private
 * @param {Object} proto The object to inherit from.
 * @returns {Object} Returns the new object.
 */
var baseCreate = (function() {
  function object() {}
  return function(proto) {
    if (!isObject(proto)) {
      return {};
    }
    if (objectCreate) {
      return objectCreate(proto);
    }
    object.prototype = proto;
    var result = new object;
    object.prototype = undefined;
    return result;
  };
}());

module.exports = baseCreate;


/***/ }),

/***/ "./node_modules/lodash/_baseEach.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_baseEach.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseForOwn = __webpack_require__(/*! ./_baseForOwn */ "./node_modules/lodash/_baseForOwn.js"),
    createBaseEach = __webpack_require__(/*! ./_createBaseEach */ "./node_modules/lodash/_createBaseEach.js");

/**
 * The base implementation of `_.forEach` without support for iteratee shorthands.
 *
 * @private
 * @param {Array|Object} collection The collection to iterate over.
 * @param {Function} iteratee The function invoked per iteration.
 * @returns {Array|Object} Returns `collection`.
 */
var baseEach = createBaseEach(baseForOwn);

module.exports = baseEach;


/***/ }),

/***/ "./node_modules/lodash/_baseFilter.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_baseFilter.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseEach = __webpack_require__(/*! ./_baseEach */ "./node_modules/lodash/_baseEach.js");

/**
 * The base implementation of `_.filter` without support for iteratee shorthands.
 *
 * @private
 * @param {Array|Object} collection The collection to iterate over.
 * @param {Function} predicate The function invoked per iteration.
 * @returns {Array} Returns the new filtered array.
 */
function baseFilter(collection, predicate) {
  var result = [];
  baseEach(collection, function(value, index, collection) {
    if (predicate(value, index, collection)) {
      result.push(value);
    }
  });
  return result;
}

module.exports = baseFilter;


/***/ }),

/***/ "./node_modules/lodash/_baseFindIndex.js":
/*!***********************************************!*\
  !*** ./node_modules/lodash/_baseFindIndex.js ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * The base implementation of `_.findIndex` and `_.findLastIndex` without
 * support for iteratee shorthands.
 *
 * @private
 * @param {Array} array The array to inspect.
 * @param {Function} predicate The function invoked per iteration.
 * @param {number} fromIndex The index to search from.
 * @param {boolean} [fromRight] Specify iterating from right to left.
 * @returns {number} Returns the index of the matched value, else `-1`.
 */
function baseFindIndex(array, predicate, fromIndex, fromRight) {
  var length = array.length,
      index = fromIndex + (fromRight ? 1 : -1);

  while ((fromRight ? index-- : ++index < length)) {
    if (predicate(array[index], index, array)) {
      return index;
    }
  }
  return -1;
}

module.exports = baseFindIndex;


/***/ }),

/***/ "./node_modules/lodash/_baseFlatten.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_baseFlatten.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var arrayPush = __webpack_require__(/*! ./_arrayPush */ "./node_modules/lodash/_arrayPush.js"),
    isFlattenable = __webpack_require__(/*! ./_isFlattenable */ "./node_modules/lodash/_isFlattenable.js");

/**
 * The base implementation of `_.flatten` with support for restricting flattening.
 *
 * @private
 * @param {Array} array The array to flatten.
 * @param {number} depth The maximum recursion depth.
 * @param {boolean} [predicate=isFlattenable] The function invoked per iteration.
 * @param {boolean} [isStrict] Restrict to values that pass `predicate` checks.
 * @param {Array} [result=[]] The initial result value.
 * @returns {Array} Returns the new flattened array.
 */
function baseFlatten(array, depth, predicate, isStrict, result) {
  var index = -1,
      length = array.length;

  predicate || (predicate = isFlattenable);
  result || (result = []);

  while (++index < length) {
    var value = array[index];
    if (depth > 0 && predicate(value)) {
      if (depth > 1) {
        // Recursively flatten arrays (susceptible to call stack limits).
        baseFlatten(value, depth - 1, predicate, isStrict, result);
      } else {
        arrayPush(result, value);
      }
    } else if (!isStrict) {
      result[result.length] = value;
    }
  }
  return result;
}

module.exports = baseFlatten;


/***/ }),

/***/ "./node_modules/lodash/_baseFor.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_baseFor.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var createBaseFor = __webpack_require__(/*! ./_createBaseFor */ "./node_modules/lodash/_createBaseFor.js");

/**
 * The base implementation of `baseForOwn` which iterates over `object`
 * properties returned by `keysFunc` and invokes `iteratee` for each property.
 * Iteratee functions may exit iteration early by explicitly returning `false`.
 *
 * @private
 * @param {Object} object The object to iterate over.
 * @param {Function} iteratee The function invoked per iteration.
 * @param {Function} keysFunc The function to get the keys of `object`.
 * @returns {Object} Returns `object`.
 */
var baseFor = createBaseFor();

module.exports = baseFor;


/***/ }),

/***/ "./node_modules/lodash/_baseForOwn.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_baseForOwn.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseFor = __webpack_require__(/*! ./_baseFor */ "./node_modules/lodash/_baseFor.js"),
    keys = __webpack_require__(/*! ./keys */ "./node_modules/lodash/keys.js");

/**
 * The base implementation of `_.forOwn` without support for iteratee shorthands.
 *
 * @private
 * @param {Object} object The object to iterate over.
 * @param {Function} iteratee The function invoked per iteration.
 * @returns {Object} Returns `object`.
 */
function baseForOwn(object, iteratee) {
  return object && baseFor(object, iteratee, keys);
}

module.exports = baseForOwn;


/***/ }),

/***/ "./node_modules/lodash/_baseGet.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_baseGet.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var castPath = __webpack_require__(/*! ./_castPath */ "./node_modules/lodash/_castPath.js"),
    toKey = __webpack_require__(/*! ./_toKey */ "./node_modules/lodash/_toKey.js");

/**
 * The base implementation of `_.get` without support for default values.
 *
 * @private
 * @param {Object} object The object to query.
 * @param {Array|string} path The path of the property to get.
 * @returns {*} Returns the resolved value.
 */
function baseGet(object, path) {
  path = castPath(path, object);

  var index = 0,
      length = path.length;

  while (object != null && index < length) {
    object = object[toKey(path[index++])];
  }
  return (index && index == length) ? object : undefined;
}

module.exports = baseGet;


/***/ }),

/***/ "./node_modules/lodash/_baseGetAllKeys.js":
/*!************************************************!*\
  !*** ./node_modules/lodash/_baseGetAllKeys.js ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var arrayPush = __webpack_require__(/*! ./_arrayPush */ "./node_modules/lodash/_arrayPush.js"),
    isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js");

/**
 * The base implementation of `getAllKeys` and `getAllKeysIn` which uses
 * `keysFunc` and `symbolsFunc` to get the enumerable property names and
 * symbols of `object`.
 *
 * @private
 * @param {Object} object The object to query.
 * @param {Function} keysFunc The function to get the keys of `object`.
 * @param {Function} symbolsFunc The function to get the symbols of `object`.
 * @returns {Array} Returns the array of property names and symbols.
 */
function baseGetAllKeys(object, keysFunc, symbolsFunc) {
  var result = keysFunc(object);
  return isArray(object) ? result : arrayPush(result, symbolsFunc(object));
}

module.exports = baseGetAllKeys;


/***/ }),

/***/ "./node_modules/lodash/_baseGetTag.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_baseGetTag.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var Symbol = __webpack_require__(/*! ./_Symbol */ "./node_modules/lodash/_Symbol.js"),
    getRawTag = __webpack_require__(/*! ./_getRawTag */ "./node_modules/lodash/_getRawTag.js"),
    objectToString = __webpack_require__(/*! ./_objectToString */ "./node_modules/lodash/_objectToString.js");

/** `Object#toString` result references. */
var nullTag = '[object Null]',
    undefinedTag = '[object Undefined]';

/** Built-in value references. */
var symToStringTag = Symbol ? Symbol.toStringTag : undefined;

/**
 * The base implementation of `getTag` without fallbacks for buggy environments.
 *
 * @private
 * @param {*} value The value to query.
 * @returns {string} Returns the `toStringTag`.
 */
function baseGetTag(value) {
  if (value == null) {
    return value === undefined ? undefinedTag : nullTag;
  }
  return (symToStringTag && symToStringTag in Object(value))
    ? getRawTag(value)
    : objectToString(value);
}

module.exports = baseGetTag;


/***/ }),

/***/ "./node_modules/lodash/_baseHasIn.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_baseHasIn.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * The base implementation of `_.hasIn` without support for deep paths.
 *
 * @private
 * @param {Object} [object] The object to query.
 * @param {Array|string} key The key to check.
 * @returns {boolean} Returns `true` if `key` exists, else `false`.
 */
function baseHasIn(object, key) {
  return object != null && key in Object(object);
}

module.exports = baseHasIn;


/***/ }),

/***/ "./node_modules/lodash/_baseIndexOf.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_baseIndexOf.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseFindIndex = __webpack_require__(/*! ./_baseFindIndex */ "./node_modules/lodash/_baseFindIndex.js"),
    baseIsNaN = __webpack_require__(/*! ./_baseIsNaN */ "./node_modules/lodash/_baseIsNaN.js"),
    strictIndexOf = __webpack_require__(/*! ./_strictIndexOf */ "./node_modules/lodash/_strictIndexOf.js");

/**
 * The base implementation of `_.indexOf` without `fromIndex` bounds checks.
 *
 * @private
 * @param {Array} array The array to inspect.
 * @param {*} value The value to search for.
 * @param {number} fromIndex The index to search from.
 * @returns {number} Returns the index of the matched value, else `-1`.
 */
function baseIndexOf(array, value, fromIndex) {
  return value === value
    ? strictIndexOf(array, value, fromIndex)
    : baseFindIndex(array, baseIsNaN, fromIndex);
}

module.exports = baseIndexOf;


/***/ }),

/***/ "./node_modules/lodash/_baseIsArguments.js":
/*!*************************************************!*\
  !*** ./node_modules/lodash/_baseIsArguments.js ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseGetTag = __webpack_require__(/*! ./_baseGetTag */ "./node_modules/lodash/_baseGetTag.js"),
    isObjectLike = __webpack_require__(/*! ./isObjectLike */ "./node_modules/lodash/isObjectLike.js");

/** `Object#toString` result references. */
var argsTag = '[object Arguments]';

/**
 * The base implementation of `_.isArguments`.
 *
 * @private
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is an `arguments` object,
 */
function baseIsArguments(value) {
  return isObjectLike(value) && baseGetTag(value) == argsTag;
}

module.exports = baseIsArguments;


/***/ }),

/***/ "./node_modules/lodash/_baseIsEqual.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_baseIsEqual.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseIsEqualDeep = __webpack_require__(/*! ./_baseIsEqualDeep */ "./node_modules/lodash/_baseIsEqualDeep.js"),
    isObjectLike = __webpack_require__(/*! ./isObjectLike */ "./node_modules/lodash/isObjectLike.js");

/**
 * The base implementation of `_.isEqual` which supports partial comparisons
 * and tracks traversed objects.
 *
 * @private
 * @param {*} value The value to compare.
 * @param {*} other The other value to compare.
 * @param {boolean} bitmask The bitmask flags.
 *  1 - Unordered comparison
 *  2 - Partial comparison
 * @param {Function} [customizer] The function to customize comparisons.
 * @param {Object} [stack] Tracks traversed `value` and `other` objects.
 * @returns {boolean} Returns `true` if the values are equivalent, else `false`.
 */
function baseIsEqual(value, other, bitmask, customizer, stack) {
  if (value === other) {
    return true;
  }
  if (value == null || other == null || (!isObjectLike(value) && !isObjectLike(other))) {
    return value !== value && other !== other;
  }
  return baseIsEqualDeep(value, other, bitmask, customizer, baseIsEqual, stack);
}

module.exports = baseIsEqual;


/***/ }),

/***/ "./node_modules/lodash/_baseIsEqualDeep.js":
/*!*************************************************!*\
  !*** ./node_modules/lodash/_baseIsEqualDeep.js ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var Stack = __webpack_require__(/*! ./_Stack */ "./node_modules/lodash/_Stack.js"),
    equalArrays = __webpack_require__(/*! ./_equalArrays */ "./node_modules/lodash/_equalArrays.js"),
    equalByTag = __webpack_require__(/*! ./_equalByTag */ "./node_modules/lodash/_equalByTag.js"),
    equalObjects = __webpack_require__(/*! ./_equalObjects */ "./node_modules/lodash/_equalObjects.js"),
    getTag = __webpack_require__(/*! ./_getTag */ "./node_modules/lodash/_getTag.js"),
    isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js"),
    isBuffer = __webpack_require__(/*! ./isBuffer */ "./node_modules/lodash/isBuffer.js"),
    isTypedArray = __webpack_require__(/*! ./isTypedArray */ "./node_modules/lodash/isTypedArray.js");

/** Used to compose bitmasks for value comparisons. */
var COMPARE_PARTIAL_FLAG = 1;

/** `Object#toString` result references. */
var argsTag = '[object Arguments]',
    arrayTag = '[object Array]',
    objectTag = '[object Object]';

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/**
 * A specialized version of `baseIsEqual` for arrays and objects which performs
 * deep comparisons and tracks traversed objects enabling objects with circular
 * references to be compared.
 *
 * @private
 * @param {Object} object The object to compare.
 * @param {Object} other The other object to compare.
 * @param {number} bitmask The bitmask flags. See `baseIsEqual` for more details.
 * @param {Function} customizer The function to customize comparisons.
 * @param {Function} equalFunc The function to determine equivalents of values.
 * @param {Object} [stack] Tracks traversed `object` and `other` objects.
 * @returns {boolean} Returns `true` if the objects are equivalent, else `false`.
 */
function baseIsEqualDeep(object, other, bitmask, customizer, equalFunc, stack) {
  var objIsArr = isArray(object),
      othIsArr = isArray(other),
      objTag = objIsArr ? arrayTag : getTag(object),
      othTag = othIsArr ? arrayTag : getTag(other);

  objTag = objTag == argsTag ? objectTag : objTag;
  othTag = othTag == argsTag ? objectTag : othTag;

  var objIsObj = objTag == objectTag,
      othIsObj = othTag == objectTag,
      isSameTag = objTag == othTag;

  if (isSameTag && isBuffer(object)) {
    if (!isBuffer(other)) {
      return false;
    }
    objIsArr = true;
    objIsObj = false;
  }
  if (isSameTag && !objIsObj) {
    stack || (stack = new Stack);
    return (objIsArr || isTypedArray(object))
      ? equalArrays(object, other, bitmask, customizer, equalFunc, stack)
      : equalByTag(object, other, objTag, bitmask, customizer, equalFunc, stack);
  }
  if (!(bitmask & COMPARE_PARTIAL_FLAG)) {
    var objIsWrapped = objIsObj && hasOwnProperty.call(object, '__wrapped__'),
        othIsWrapped = othIsObj && hasOwnProperty.call(other, '__wrapped__');

    if (objIsWrapped || othIsWrapped) {
      var objUnwrapped = objIsWrapped ? object.value() : object,
          othUnwrapped = othIsWrapped ? other.value() : other;

      stack || (stack = new Stack);
      return equalFunc(objUnwrapped, othUnwrapped, bitmask, customizer, stack);
    }
  }
  if (!isSameTag) {
    return false;
  }
  stack || (stack = new Stack);
  return equalObjects(object, other, bitmask, customizer, equalFunc, stack);
}

module.exports = baseIsEqualDeep;


/***/ }),

/***/ "./node_modules/lodash/_baseIsMatch.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_baseIsMatch.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var Stack = __webpack_require__(/*! ./_Stack */ "./node_modules/lodash/_Stack.js"),
    baseIsEqual = __webpack_require__(/*! ./_baseIsEqual */ "./node_modules/lodash/_baseIsEqual.js");

/** Used to compose bitmasks for value comparisons. */
var COMPARE_PARTIAL_FLAG = 1,
    COMPARE_UNORDERED_FLAG = 2;

/**
 * The base implementation of `_.isMatch` without support for iteratee shorthands.
 *
 * @private
 * @param {Object} object The object to inspect.
 * @param {Object} source The object of property values to match.
 * @param {Array} matchData The property names, values, and compare flags to match.
 * @param {Function} [customizer] The function to customize comparisons.
 * @returns {boolean} Returns `true` if `object` is a match, else `false`.
 */
function baseIsMatch(object, source, matchData, customizer) {
  var index = matchData.length,
      length = index,
      noCustomizer = !customizer;

  if (object == null) {
    return !length;
  }
  object = Object(object);
  while (index--) {
    var data = matchData[index];
    if ((noCustomizer && data[2])
          ? data[1] !== object[data[0]]
          : !(data[0] in object)
        ) {
      return false;
    }
  }
  while (++index < length) {
    data = matchData[index];
    var key = data[0],
        objValue = object[key],
        srcValue = data[1];

    if (noCustomizer && data[2]) {
      if (objValue === undefined && !(key in object)) {
        return false;
      }
    } else {
      var stack = new Stack;
      if (customizer) {
        var result = customizer(objValue, srcValue, key, object, source, stack);
      }
      if (!(result === undefined
            ? baseIsEqual(srcValue, objValue, COMPARE_PARTIAL_FLAG | COMPARE_UNORDERED_FLAG, customizer, stack)
            : result
          )) {
        return false;
      }
    }
  }
  return true;
}

module.exports = baseIsMatch;


/***/ }),

/***/ "./node_modules/lodash/_baseIsNaN.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_baseIsNaN.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * The base implementation of `_.isNaN` without support for number objects.
 *
 * @private
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is `NaN`, else `false`.
 */
function baseIsNaN(value) {
  return value !== value;
}

module.exports = baseIsNaN;


/***/ }),

/***/ "./node_modules/lodash/_baseIsNative.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_baseIsNative.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var isFunction = __webpack_require__(/*! ./isFunction */ "./node_modules/lodash/isFunction.js"),
    isMasked = __webpack_require__(/*! ./_isMasked */ "./node_modules/lodash/_isMasked.js"),
    isObject = __webpack_require__(/*! ./isObject */ "./node_modules/lodash/isObject.js"),
    toSource = __webpack_require__(/*! ./_toSource */ "./node_modules/lodash/_toSource.js");

/**
 * Used to match `RegExp`
 * [syntax characters](http://ecma-international.org/ecma-262/7.0/#sec-patterns).
 */
var reRegExpChar = /[\\^$.*+?()[\]{}|]/g;

/** Used to detect host constructors (Safari). */
var reIsHostCtor = /^\[object .+?Constructor\]$/;

/** Used for built-in method references. */
var funcProto = Function.prototype,
    objectProto = Object.prototype;

/** Used to resolve the decompiled source of functions. */
var funcToString = funcProto.toString;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/** Used to detect if a method is native. */
var reIsNative = RegExp('^' +
  funcToString.call(hasOwnProperty).replace(reRegExpChar, '\\$&')
  .replace(/hasOwnProperty|(function).*?(?=\\\()| for .+?(?=\\\])/g, '$1.*?') + '$'
);

/**
 * The base implementation of `_.isNative` without bad shim checks.
 *
 * @private
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is a native function,
 *  else `false`.
 */
function baseIsNative(value) {
  if (!isObject(value) || isMasked(value)) {
    return false;
  }
  var pattern = isFunction(value) ? reIsNative : reIsHostCtor;
  return pattern.test(toSource(value));
}

module.exports = baseIsNative;


/***/ }),

/***/ "./node_modules/lodash/_baseIsTypedArray.js":
/*!**************************************************!*\
  !*** ./node_modules/lodash/_baseIsTypedArray.js ***!
  \**************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseGetTag = __webpack_require__(/*! ./_baseGetTag */ "./node_modules/lodash/_baseGetTag.js"),
    isLength = __webpack_require__(/*! ./isLength */ "./node_modules/lodash/isLength.js"),
    isObjectLike = __webpack_require__(/*! ./isObjectLike */ "./node_modules/lodash/isObjectLike.js");

/** `Object#toString` result references. */
var argsTag = '[object Arguments]',
    arrayTag = '[object Array]',
    boolTag = '[object Boolean]',
    dateTag = '[object Date]',
    errorTag = '[object Error]',
    funcTag = '[object Function]',
    mapTag = '[object Map]',
    numberTag = '[object Number]',
    objectTag = '[object Object]',
    regexpTag = '[object RegExp]',
    setTag = '[object Set]',
    stringTag = '[object String]',
    weakMapTag = '[object WeakMap]';

var arrayBufferTag = '[object ArrayBuffer]',
    dataViewTag = '[object DataView]',
    float32Tag = '[object Float32Array]',
    float64Tag = '[object Float64Array]',
    int8Tag = '[object Int8Array]',
    int16Tag = '[object Int16Array]',
    int32Tag = '[object Int32Array]',
    uint8Tag = '[object Uint8Array]',
    uint8ClampedTag = '[object Uint8ClampedArray]',
    uint16Tag = '[object Uint16Array]',
    uint32Tag = '[object Uint32Array]';

/** Used to identify `toStringTag` values of typed arrays. */
var typedArrayTags = {};
typedArrayTags[float32Tag] = typedArrayTags[float64Tag] =
typedArrayTags[int8Tag] = typedArrayTags[int16Tag] =
typedArrayTags[int32Tag] = typedArrayTags[uint8Tag] =
typedArrayTags[uint8ClampedTag] = typedArrayTags[uint16Tag] =
typedArrayTags[uint32Tag] = true;
typedArrayTags[argsTag] = typedArrayTags[arrayTag] =
typedArrayTags[arrayBufferTag] = typedArrayTags[boolTag] =
typedArrayTags[dataViewTag] = typedArrayTags[dateTag] =
typedArrayTags[errorTag] = typedArrayTags[funcTag] =
typedArrayTags[mapTag] = typedArrayTags[numberTag] =
typedArrayTags[objectTag] = typedArrayTags[regexpTag] =
typedArrayTags[setTag] = typedArrayTags[stringTag] =
typedArrayTags[weakMapTag] = false;

/**
 * The base implementation of `_.isTypedArray` without Node.js optimizations.
 *
 * @private
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is a typed array, else `false`.
 */
function baseIsTypedArray(value) {
  return isObjectLike(value) &&
    isLength(value.length) && !!typedArrayTags[baseGetTag(value)];
}

module.exports = baseIsTypedArray;


/***/ }),

/***/ "./node_modules/lodash/_baseIteratee.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_baseIteratee.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseMatches = __webpack_require__(/*! ./_baseMatches */ "./node_modules/lodash/_baseMatches.js"),
    baseMatchesProperty = __webpack_require__(/*! ./_baseMatchesProperty */ "./node_modules/lodash/_baseMatchesProperty.js"),
    identity = __webpack_require__(/*! ./identity */ "./node_modules/lodash/identity.js"),
    isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js"),
    property = __webpack_require__(/*! ./property */ "./node_modules/lodash/property.js");

/**
 * The base implementation of `_.iteratee`.
 *
 * @private
 * @param {*} [value=_.identity] The value to convert to an iteratee.
 * @returns {Function} Returns the iteratee.
 */
function baseIteratee(value) {
  // Don't store the `typeof` result in a variable to avoid a JIT bug in Safari 9.
  // See https://bugs.webkit.org/show_bug.cgi?id=156034 for more details.
  if (typeof value == 'function') {
    return value;
  }
  if (value == null) {
    return identity;
  }
  if (typeof value == 'object') {
    return isArray(value)
      ? baseMatchesProperty(value[0], value[1])
      : baseMatches(value);
  }
  return property(value);
}

module.exports = baseIteratee;


/***/ }),

/***/ "./node_modules/lodash/_baseKeys.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_baseKeys.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var isPrototype = __webpack_require__(/*! ./_isPrototype */ "./node_modules/lodash/_isPrototype.js"),
    nativeKeys = __webpack_require__(/*! ./_nativeKeys */ "./node_modules/lodash/_nativeKeys.js");

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/**
 * The base implementation of `_.keys` which doesn't treat sparse arrays as dense.
 *
 * @private
 * @param {Object} object The object to query.
 * @returns {Array} Returns the array of property names.
 */
function baseKeys(object) {
  if (!isPrototype(object)) {
    return nativeKeys(object);
  }
  var result = [];
  for (var key in Object(object)) {
    if (hasOwnProperty.call(object, key) && key != 'constructor') {
      result.push(key);
    }
  }
  return result;
}

module.exports = baseKeys;


/***/ }),

/***/ "./node_modules/lodash/_baseLodash.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_baseLodash.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * The function whose prototype chain sequence wrappers inherit from.
 *
 * @private
 */
function baseLodash() {
  // No operation performed.
}

module.exports = baseLodash;


/***/ }),

/***/ "./node_modules/lodash/_baseMap.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_baseMap.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseEach = __webpack_require__(/*! ./_baseEach */ "./node_modules/lodash/_baseEach.js"),
    isArrayLike = __webpack_require__(/*! ./isArrayLike */ "./node_modules/lodash/isArrayLike.js");

/**
 * The base implementation of `_.map` without support for iteratee shorthands.
 *
 * @private
 * @param {Array|Object} collection The collection to iterate over.
 * @param {Function} iteratee The function invoked per iteration.
 * @returns {Array} Returns the new mapped array.
 */
function baseMap(collection, iteratee) {
  var index = -1,
      result = isArrayLike(collection) ? Array(collection.length) : [];

  baseEach(collection, function(value, key, collection) {
    result[++index] = iteratee(value, key, collection);
  });
  return result;
}

module.exports = baseMap;


/***/ }),

/***/ "./node_modules/lodash/_baseMatches.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_baseMatches.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseIsMatch = __webpack_require__(/*! ./_baseIsMatch */ "./node_modules/lodash/_baseIsMatch.js"),
    getMatchData = __webpack_require__(/*! ./_getMatchData */ "./node_modules/lodash/_getMatchData.js"),
    matchesStrictComparable = __webpack_require__(/*! ./_matchesStrictComparable */ "./node_modules/lodash/_matchesStrictComparable.js");

/**
 * The base implementation of `_.matches` which doesn't clone `source`.
 *
 * @private
 * @param {Object} source The object of property values to match.
 * @returns {Function} Returns the new spec function.
 */
function baseMatches(source) {
  var matchData = getMatchData(source);
  if (matchData.length == 1 && matchData[0][2]) {
    return matchesStrictComparable(matchData[0][0], matchData[0][1]);
  }
  return function(object) {
    return object === source || baseIsMatch(object, source, matchData);
  };
}

module.exports = baseMatches;


/***/ }),

/***/ "./node_modules/lodash/_baseMatchesProperty.js":
/*!*****************************************************!*\
  !*** ./node_modules/lodash/_baseMatchesProperty.js ***!
  \*****************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseIsEqual = __webpack_require__(/*! ./_baseIsEqual */ "./node_modules/lodash/_baseIsEqual.js"),
    get = __webpack_require__(/*! ./get */ "./node_modules/lodash/get.js"),
    hasIn = __webpack_require__(/*! ./hasIn */ "./node_modules/lodash/hasIn.js"),
    isKey = __webpack_require__(/*! ./_isKey */ "./node_modules/lodash/_isKey.js"),
    isStrictComparable = __webpack_require__(/*! ./_isStrictComparable */ "./node_modules/lodash/_isStrictComparable.js"),
    matchesStrictComparable = __webpack_require__(/*! ./_matchesStrictComparable */ "./node_modules/lodash/_matchesStrictComparable.js"),
    toKey = __webpack_require__(/*! ./_toKey */ "./node_modules/lodash/_toKey.js");

/** Used to compose bitmasks for value comparisons. */
var COMPARE_PARTIAL_FLAG = 1,
    COMPARE_UNORDERED_FLAG = 2;

/**
 * The base implementation of `_.matchesProperty` which doesn't clone `srcValue`.
 *
 * @private
 * @param {string} path The path of the property to get.
 * @param {*} srcValue The value to match.
 * @returns {Function} Returns the new spec function.
 */
function baseMatchesProperty(path, srcValue) {
  if (isKey(path) && isStrictComparable(srcValue)) {
    return matchesStrictComparable(toKey(path), srcValue);
  }
  return function(object) {
    var objValue = get(object, path);
    return (objValue === undefined && objValue === srcValue)
      ? hasIn(object, path)
      : baseIsEqual(srcValue, objValue, COMPARE_PARTIAL_FLAG | COMPARE_UNORDERED_FLAG);
  };
}

module.exports = baseMatchesProperty;


/***/ }),

/***/ "./node_modules/lodash/_basePick.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_basePick.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var basePickBy = __webpack_require__(/*! ./_basePickBy */ "./node_modules/lodash/_basePickBy.js"),
    hasIn = __webpack_require__(/*! ./hasIn */ "./node_modules/lodash/hasIn.js");

/**
 * The base implementation of `_.pick` without support for individual
 * property identifiers.
 *
 * @private
 * @param {Object} object The source object.
 * @param {string[]} paths The property paths to pick.
 * @returns {Object} Returns the new object.
 */
function basePick(object, paths) {
  return basePickBy(object, paths, function(value, path) {
    return hasIn(object, path);
  });
}

module.exports = basePick;


/***/ }),

/***/ "./node_modules/lodash/_basePickBy.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_basePickBy.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseGet = __webpack_require__(/*! ./_baseGet */ "./node_modules/lodash/_baseGet.js"),
    baseSet = __webpack_require__(/*! ./_baseSet */ "./node_modules/lodash/_baseSet.js"),
    castPath = __webpack_require__(/*! ./_castPath */ "./node_modules/lodash/_castPath.js");

/**
 * The base implementation of  `_.pickBy` without support for iteratee shorthands.
 *
 * @private
 * @param {Object} object The source object.
 * @param {string[]} paths The property paths to pick.
 * @param {Function} predicate The function invoked per property.
 * @returns {Object} Returns the new object.
 */
function basePickBy(object, paths, predicate) {
  var index = -1,
      length = paths.length,
      result = {};

  while (++index < length) {
    var path = paths[index],
        value = baseGet(object, path);

    if (predicate(value, path)) {
      baseSet(result, castPath(path, object), value);
    }
  }
  return result;
}

module.exports = basePickBy;


/***/ }),

/***/ "./node_modules/lodash/_baseProperty.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_baseProperty.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * The base implementation of `_.property` without support for deep paths.
 *
 * @private
 * @param {string} key The key of the property to get.
 * @returns {Function} Returns the new accessor function.
 */
function baseProperty(key) {
  return function(object) {
    return object == null ? undefined : object[key];
  };
}

module.exports = baseProperty;


/***/ }),

/***/ "./node_modules/lodash/_basePropertyDeep.js":
/*!**************************************************!*\
  !*** ./node_modules/lodash/_basePropertyDeep.js ***!
  \**************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseGet = __webpack_require__(/*! ./_baseGet */ "./node_modules/lodash/_baseGet.js");

/**
 * A specialized version of `baseProperty` which supports deep paths.
 *
 * @private
 * @param {Array|string} path The path of the property to get.
 * @returns {Function} Returns the new accessor function.
 */
function basePropertyDeep(path) {
  return function(object) {
    return baseGet(object, path);
  };
}

module.exports = basePropertyDeep;


/***/ }),

/***/ "./node_modules/lodash/_baseRest.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_baseRest.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var identity = __webpack_require__(/*! ./identity */ "./node_modules/lodash/identity.js"),
    overRest = __webpack_require__(/*! ./_overRest */ "./node_modules/lodash/_overRest.js"),
    setToString = __webpack_require__(/*! ./_setToString */ "./node_modules/lodash/_setToString.js");

/**
 * The base implementation of `_.rest` which doesn't validate or coerce arguments.
 *
 * @private
 * @param {Function} func The function to apply a rest parameter to.
 * @param {number} [start=func.length-1] The start position of the rest parameter.
 * @returns {Function} Returns the new function.
 */
function baseRest(func, start) {
  return setToString(overRest(func, start, identity), func + '');
}

module.exports = baseRest;


/***/ }),

/***/ "./node_modules/lodash/_baseSet.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_baseSet.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var assignValue = __webpack_require__(/*! ./_assignValue */ "./node_modules/lodash/_assignValue.js"),
    castPath = __webpack_require__(/*! ./_castPath */ "./node_modules/lodash/_castPath.js"),
    isIndex = __webpack_require__(/*! ./_isIndex */ "./node_modules/lodash/_isIndex.js"),
    isObject = __webpack_require__(/*! ./isObject */ "./node_modules/lodash/isObject.js"),
    toKey = __webpack_require__(/*! ./_toKey */ "./node_modules/lodash/_toKey.js");

/**
 * The base implementation of `_.set`.
 *
 * @private
 * @param {Object} object The object to modify.
 * @param {Array|string} path The path of the property to set.
 * @param {*} value The value to set.
 * @param {Function} [customizer] The function to customize path creation.
 * @returns {Object} Returns `object`.
 */
function baseSet(object, path, value, customizer) {
  if (!isObject(object)) {
    return object;
  }
  path = castPath(path, object);

  var index = -1,
      length = path.length,
      lastIndex = length - 1,
      nested = object;

  while (nested != null && ++index < length) {
    var key = toKey(path[index]),
        newValue = value;

    if (key === '__proto__' || key === 'constructor' || key === 'prototype') {
      return object;
    }

    if (index != lastIndex) {
      var objValue = nested[key];
      newValue = customizer ? customizer(objValue, key, nested) : undefined;
      if (newValue === undefined) {
        newValue = isObject(objValue)
          ? objValue
          : (isIndex(path[index + 1]) ? [] : {});
      }
    }
    assignValue(nested, key, newValue);
    nested = nested[key];
  }
  return object;
}

module.exports = baseSet;


/***/ }),

/***/ "./node_modules/lodash/_baseSetData.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_baseSetData.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var identity = __webpack_require__(/*! ./identity */ "./node_modules/lodash/identity.js"),
    metaMap = __webpack_require__(/*! ./_metaMap */ "./node_modules/lodash/_metaMap.js");

/**
 * The base implementation of `setData` without support for hot loop shorting.
 *
 * @private
 * @param {Function} func The function to associate metadata with.
 * @param {*} data The metadata.
 * @returns {Function} Returns `func`.
 */
var baseSetData = !metaMap ? identity : function(func, data) {
  metaMap.set(func, data);
  return func;
};

module.exports = baseSetData;


/***/ }),

/***/ "./node_modules/lodash/_baseSetToString.js":
/*!*************************************************!*\
  !*** ./node_modules/lodash/_baseSetToString.js ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var constant = __webpack_require__(/*! ./constant */ "./node_modules/lodash/constant.js"),
    defineProperty = __webpack_require__(/*! ./_defineProperty */ "./node_modules/lodash/_defineProperty.js"),
    identity = __webpack_require__(/*! ./identity */ "./node_modules/lodash/identity.js");

/**
 * The base implementation of `setToString` without support for hot loop shorting.
 *
 * @private
 * @param {Function} func The function to modify.
 * @param {Function} string The `toString` result.
 * @returns {Function} Returns `func`.
 */
var baseSetToString = !defineProperty ? identity : function(func, string) {
  return defineProperty(func, 'toString', {
    'configurable': true,
    'enumerable': false,
    'value': constant(string),
    'writable': true
  });
};

module.exports = baseSetToString;


/***/ }),

/***/ "./node_modules/lodash/_baseTimes.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_baseTimes.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * The base implementation of `_.times` without support for iteratee shorthands
 * or max array length checks.
 *
 * @private
 * @param {number} n The number of times to invoke `iteratee`.
 * @param {Function} iteratee The function invoked per iteration.
 * @returns {Array} Returns the array of results.
 */
function baseTimes(n, iteratee) {
  var index = -1,
      result = Array(n);

  while (++index < n) {
    result[index] = iteratee(index);
  }
  return result;
}

module.exports = baseTimes;


/***/ }),

/***/ "./node_modules/lodash/_baseToString.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_baseToString.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var Symbol = __webpack_require__(/*! ./_Symbol */ "./node_modules/lodash/_Symbol.js"),
    arrayMap = __webpack_require__(/*! ./_arrayMap */ "./node_modules/lodash/_arrayMap.js"),
    isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js"),
    isSymbol = __webpack_require__(/*! ./isSymbol */ "./node_modules/lodash/isSymbol.js");

/** Used as references for various `Number` constants. */
var INFINITY = 1 / 0;

/** Used to convert symbols to primitives and strings. */
var symbolProto = Symbol ? Symbol.prototype : undefined,
    symbolToString = symbolProto ? symbolProto.toString : undefined;

/**
 * The base implementation of `_.toString` which doesn't convert nullish
 * values to empty strings.
 *
 * @private
 * @param {*} value The value to process.
 * @returns {string} Returns the string.
 */
function baseToString(value) {
  // Exit early for strings to avoid a performance hit in some environments.
  if (typeof value == 'string') {
    return value;
  }
  if (isArray(value)) {
    // Recursively convert values (susceptible to call stack limits).
    return arrayMap(value, baseToString) + '';
  }
  if (isSymbol(value)) {
    return symbolToString ? symbolToString.call(value) : '';
  }
  var result = (value + '');
  return (result == '0' && (1 / value) == -INFINITY) ? '-0' : result;
}

module.exports = baseToString;


/***/ }),

/***/ "./node_modules/lodash/_baseTrim.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_baseTrim.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var trimmedEndIndex = __webpack_require__(/*! ./_trimmedEndIndex */ "./node_modules/lodash/_trimmedEndIndex.js");

/** Used to match leading whitespace. */
var reTrimStart = /^\s+/;

/**
 * The base implementation of `_.trim`.
 *
 * @private
 * @param {string} string The string to trim.
 * @returns {string} Returns the trimmed string.
 */
function baseTrim(string) {
  return string
    ? string.slice(0, trimmedEndIndex(string) + 1).replace(reTrimStart, '')
    : string;
}

module.exports = baseTrim;


/***/ }),

/***/ "./node_modules/lodash/_baseUnary.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_baseUnary.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * The base implementation of `_.unary` without support for storing metadata.
 *
 * @private
 * @param {Function} func The function to cap arguments for.
 * @returns {Function} Returns the new capped function.
 */
function baseUnary(func) {
  return function(value) {
    return func(value);
  };
}

module.exports = baseUnary;


/***/ }),

/***/ "./node_modules/lodash/_cacheHas.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_cacheHas.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Checks if a `cache` value for `key` exists.
 *
 * @private
 * @param {Object} cache The cache to query.
 * @param {string} key The key of the entry to check.
 * @returns {boolean} Returns `true` if an entry for `key` exists, else `false`.
 */
function cacheHas(cache, key) {
  return cache.has(key);
}

module.exports = cacheHas;


/***/ }),

/***/ "./node_modules/lodash/_castFunction.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_castFunction.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var identity = __webpack_require__(/*! ./identity */ "./node_modules/lodash/identity.js");

/**
 * Casts `value` to `identity` if it's not a function.
 *
 * @private
 * @param {*} value The value to inspect.
 * @returns {Function} Returns cast function.
 */
function castFunction(value) {
  return typeof value == 'function' ? value : identity;
}

module.exports = castFunction;


/***/ }),

/***/ "./node_modules/lodash/_castPath.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_castPath.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js"),
    isKey = __webpack_require__(/*! ./_isKey */ "./node_modules/lodash/_isKey.js"),
    stringToPath = __webpack_require__(/*! ./_stringToPath */ "./node_modules/lodash/_stringToPath.js"),
    toString = __webpack_require__(/*! ./toString */ "./node_modules/lodash/toString.js");

/**
 * Casts `value` to a path array if it's not one.
 *
 * @private
 * @param {*} value The value to inspect.
 * @param {Object} [object] The object to query keys on.
 * @returns {Array} Returns the cast property path array.
 */
function castPath(value, object) {
  if (isArray(value)) {
    return value;
  }
  return isKey(value, object) ? [value] : stringToPath(toString(value));
}

module.exports = castPath;


/***/ }),

/***/ "./node_modules/lodash/_composeArgs.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_composeArgs.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/* Built-in method references for those with the same name as other `lodash` methods. */
var nativeMax = Math.max;

/**
 * Creates an array that is the composition of partially applied arguments,
 * placeholders, and provided arguments into a single array of arguments.
 *
 * @private
 * @param {Array} args The provided arguments.
 * @param {Array} partials The arguments to prepend to those provided.
 * @param {Array} holders The `partials` placeholder indexes.
 * @params {boolean} [isCurried] Specify composing for a curried function.
 * @returns {Array} Returns the new array of composed arguments.
 */
function composeArgs(args, partials, holders, isCurried) {
  var argsIndex = -1,
      argsLength = args.length,
      holdersLength = holders.length,
      leftIndex = -1,
      leftLength = partials.length,
      rangeLength = nativeMax(argsLength - holdersLength, 0),
      result = Array(leftLength + rangeLength),
      isUncurried = !isCurried;

  while (++leftIndex < leftLength) {
    result[leftIndex] = partials[leftIndex];
  }
  while (++argsIndex < holdersLength) {
    if (isUncurried || argsIndex < argsLength) {
      result[holders[argsIndex]] = args[argsIndex];
    }
  }
  while (rangeLength--) {
    result[leftIndex++] = args[argsIndex++];
  }
  return result;
}

module.exports = composeArgs;


/***/ }),

/***/ "./node_modules/lodash/_composeArgsRight.js":
/*!**************************************************!*\
  !*** ./node_modules/lodash/_composeArgsRight.js ***!
  \**************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/* Built-in method references for those with the same name as other `lodash` methods. */
var nativeMax = Math.max;

/**
 * This function is like `composeArgs` except that the arguments composition
 * is tailored for `_.partialRight`.
 *
 * @private
 * @param {Array} args The provided arguments.
 * @param {Array} partials The arguments to append to those provided.
 * @param {Array} holders The `partials` placeholder indexes.
 * @params {boolean} [isCurried] Specify composing for a curried function.
 * @returns {Array} Returns the new array of composed arguments.
 */
function composeArgsRight(args, partials, holders, isCurried) {
  var argsIndex = -1,
      argsLength = args.length,
      holdersIndex = -1,
      holdersLength = holders.length,
      rightIndex = -1,
      rightLength = partials.length,
      rangeLength = nativeMax(argsLength - holdersLength, 0),
      result = Array(rangeLength + rightLength),
      isUncurried = !isCurried;

  while (++argsIndex < rangeLength) {
    result[argsIndex] = args[argsIndex];
  }
  var offset = argsIndex;
  while (++rightIndex < rightLength) {
    result[offset + rightIndex] = partials[rightIndex];
  }
  while (++holdersIndex < holdersLength) {
    if (isUncurried || argsIndex < argsLength) {
      result[offset + holders[holdersIndex]] = args[argsIndex++];
    }
  }
  return result;
}

module.exports = composeArgsRight;


/***/ }),

/***/ "./node_modules/lodash/_copyArray.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_copyArray.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Copies the values of `source` to `array`.
 *
 * @private
 * @param {Array} source The array to copy values from.
 * @param {Array} [array=[]] The array to copy values to.
 * @returns {Array} Returns `array`.
 */
function copyArray(source, array) {
  var index = -1,
      length = source.length;

  array || (array = Array(length));
  while (++index < length) {
    array[index] = source[index];
  }
  return array;
}

module.exports = copyArray;


/***/ }),

/***/ "./node_modules/lodash/_copyObject.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_copyObject.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var assignValue = __webpack_require__(/*! ./_assignValue */ "./node_modules/lodash/_assignValue.js"),
    baseAssignValue = __webpack_require__(/*! ./_baseAssignValue */ "./node_modules/lodash/_baseAssignValue.js");

/**
 * Copies properties of `source` to `object`.
 *
 * @private
 * @param {Object} source The object to copy properties from.
 * @param {Array} props The property identifiers to copy.
 * @param {Object} [object={}] The object to copy properties to.
 * @param {Function} [customizer] The function to customize copied values.
 * @returns {Object} Returns `object`.
 */
function copyObject(source, props, object, customizer) {
  var isNew = !object;
  object || (object = {});

  var index = -1,
      length = props.length;

  while (++index < length) {
    var key = props[index];

    var newValue = customizer
      ? customizer(object[key], source[key], key, object, source)
      : undefined;

    if (newValue === undefined) {
      newValue = source[key];
    }
    if (isNew) {
      baseAssignValue(object, key, newValue);
    } else {
      assignValue(object, key, newValue);
    }
  }
  return object;
}

module.exports = copyObject;


/***/ }),

/***/ "./node_modules/lodash/_coreJsData.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_coreJsData.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js");

/** Used to detect overreaching core-js shims. */
var coreJsData = root['__core-js_shared__'];

module.exports = coreJsData;


/***/ }),

/***/ "./node_modules/lodash/_countHolders.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_countHolders.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Gets the number of `placeholder` occurrences in `array`.
 *
 * @private
 * @param {Array} array The array to inspect.
 * @param {*} placeholder The placeholder to search for.
 * @returns {number} Returns the placeholder count.
 */
function countHolders(array, placeholder) {
  var length = array.length,
      result = 0;

  while (length--) {
    if (array[length] === placeholder) {
      ++result;
    }
  }
  return result;
}

module.exports = countHolders;


/***/ }),

/***/ "./node_modules/lodash/_createAssigner.js":
/*!************************************************!*\
  !*** ./node_modules/lodash/_createAssigner.js ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseRest = __webpack_require__(/*! ./_baseRest */ "./node_modules/lodash/_baseRest.js"),
    isIterateeCall = __webpack_require__(/*! ./_isIterateeCall */ "./node_modules/lodash/_isIterateeCall.js");

/**
 * Creates a function like `_.assign`.
 *
 * @private
 * @param {Function} assigner The function to assign values.
 * @returns {Function} Returns the new assigner function.
 */
function createAssigner(assigner) {
  return baseRest(function(object, sources) {
    var index = -1,
        length = sources.length,
        customizer = length > 1 ? sources[length - 1] : undefined,
        guard = length > 2 ? sources[2] : undefined;

    customizer = (assigner.length > 3 && typeof customizer == 'function')
      ? (length--, customizer)
      : undefined;

    if (guard && isIterateeCall(sources[0], sources[1], guard)) {
      customizer = length < 3 ? undefined : customizer;
      length = 1;
    }
    object = Object(object);
    while (++index < length) {
      var source = sources[index];
      if (source) {
        assigner(object, source, index, customizer);
      }
    }
    return object;
  });
}

module.exports = createAssigner;


/***/ }),

/***/ "./node_modules/lodash/_createBaseEach.js":
/*!************************************************!*\
  !*** ./node_modules/lodash/_createBaseEach.js ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var isArrayLike = __webpack_require__(/*! ./isArrayLike */ "./node_modules/lodash/isArrayLike.js");

/**
 * Creates a `baseEach` or `baseEachRight` function.
 *
 * @private
 * @param {Function} eachFunc The function to iterate over a collection.
 * @param {boolean} [fromRight] Specify iterating from right to left.
 * @returns {Function} Returns the new base function.
 */
function createBaseEach(eachFunc, fromRight) {
  return function(collection, iteratee) {
    if (collection == null) {
      return collection;
    }
    if (!isArrayLike(collection)) {
      return eachFunc(collection, iteratee);
    }
    var length = collection.length,
        index = fromRight ? length : -1,
        iterable = Object(collection);

    while ((fromRight ? index-- : ++index < length)) {
      if (iteratee(iterable[index], index, iterable) === false) {
        break;
      }
    }
    return collection;
  };
}

module.exports = createBaseEach;


/***/ }),

/***/ "./node_modules/lodash/_createBaseFor.js":
/*!***********************************************!*\
  !*** ./node_modules/lodash/_createBaseFor.js ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Creates a base function for methods like `_.forIn` and `_.forOwn`.
 *
 * @private
 * @param {boolean} [fromRight] Specify iterating from right to left.
 * @returns {Function} Returns the new base function.
 */
function createBaseFor(fromRight) {
  return function(object, iteratee, keysFunc) {
    var index = -1,
        iterable = Object(object),
        props = keysFunc(object),
        length = props.length;

    while (length--) {
      var key = props[fromRight ? length : ++index];
      if (iteratee(iterable[key], key, iterable) === false) {
        break;
      }
    }
    return object;
  };
}

module.exports = createBaseFor;


/***/ }),

/***/ "./node_modules/lodash/_createBind.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_createBind.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var createCtor = __webpack_require__(/*! ./_createCtor */ "./node_modules/lodash/_createCtor.js"),
    root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js");

/** Used to compose bitmasks for function metadata. */
var WRAP_BIND_FLAG = 1;

/**
 * Creates a function that wraps `func` to invoke it with the optional `this`
 * binding of `thisArg`.
 *
 * @private
 * @param {Function} func The function to wrap.
 * @param {number} bitmask The bitmask flags. See `createWrap` for more details.
 * @param {*} [thisArg] The `this` binding of `func`.
 * @returns {Function} Returns the new wrapped function.
 */
function createBind(func, bitmask, thisArg) {
  var isBind = bitmask & WRAP_BIND_FLAG,
      Ctor = createCtor(func);

  function wrapper() {
    var fn = (this && this !== root && this instanceof wrapper) ? Ctor : func;
    return fn.apply(isBind ? thisArg : this, arguments);
  }
  return wrapper;
}

module.exports = createBind;


/***/ }),

/***/ "./node_modules/lodash/_createCtor.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_createCtor.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseCreate = __webpack_require__(/*! ./_baseCreate */ "./node_modules/lodash/_baseCreate.js"),
    isObject = __webpack_require__(/*! ./isObject */ "./node_modules/lodash/isObject.js");

/**
 * Creates a function that produces an instance of `Ctor` regardless of
 * whether it was invoked as part of a `new` expression or by `call` or `apply`.
 *
 * @private
 * @param {Function} Ctor The constructor to wrap.
 * @returns {Function} Returns the new wrapped function.
 */
function createCtor(Ctor) {
  return function() {
    // Use a `switch` statement to work with class constructors. See
    // http://ecma-international.org/ecma-262/7.0/#sec-ecmascript-function-objects-call-thisargument-argumentslist
    // for more details.
    var args = arguments;
    switch (args.length) {
      case 0: return new Ctor;
      case 1: return new Ctor(args[0]);
      case 2: return new Ctor(args[0], args[1]);
      case 3: return new Ctor(args[0], args[1], args[2]);
      case 4: return new Ctor(args[0], args[1], args[2], args[3]);
      case 5: return new Ctor(args[0], args[1], args[2], args[3], args[4]);
      case 6: return new Ctor(args[0], args[1], args[2], args[3], args[4], args[5]);
      case 7: return new Ctor(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
    }
    var thisBinding = baseCreate(Ctor.prototype),
        result = Ctor.apply(thisBinding, args);

    // Mimic the constructor's `return` behavior.
    // See https://es5.github.io/#x13.2.2 for more details.
    return isObject(result) ? result : thisBinding;
  };
}

module.exports = createCtor;


/***/ }),

/***/ "./node_modules/lodash/_createCurry.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_createCurry.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var apply = __webpack_require__(/*! ./_apply */ "./node_modules/lodash/_apply.js"),
    createCtor = __webpack_require__(/*! ./_createCtor */ "./node_modules/lodash/_createCtor.js"),
    createHybrid = __webpack_require__(/*! ./_createHybrid */ "./node_modules/lodash/_createHybrid.js"),
    createRecurry = __webpack_require__(/*! ./_createRecurry */ "./node_modules/lodash/_createRecurry.js"),
    getHolder = __webpack_require__(/*! ./_getHolder */ "./node_modules/lodash/_getHolder.js"),
    replaceHolders = __webpack_require__(/*! ./_replaceHolders */ "./node_modules/lodash/_replaceHolders.js"),
    root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js");

/**
 * Creates a function that wraps `func` to enable currying.
 *
 * @private
 * @param {Function} func The function to wrap.
 * @param {number} bitmask The bitmask flags. See `createWrap` for more details.
 * @param {number} arity The arity of `func`.
 * @returns {Function} Returns the new wrapped function.
 */
function createCurry(func, bitmask, arity) {
  var Ctor = createCtor(func);

  function wrapper() {
    var length = arguments.length,
        args = Array(length),
        index = length,
        placeholder = getHolder(wrapper);

    while (index--) {
      args[index] = arguments[index];
    }
    var holders = (length < 3 && args[0] !== placeholder && args[length - 1] !== placeholder)
      ? []
      : replaceHolders(args, placeholder);

    length -= holders.length;
    if (length < arity) {
      return createRecurry(
        func, bitmask, createHybrid, wrapper.placeholder, undefined,
        args, holders, undefined, undefined, arity - length);
    }
    var fn = (this && this !== root && this instanceof wrapper) ? Ctor : func;
    return apply(fn, this, args);
  }
  return wrapper;
}

module.exports = createCurry;


/***/ }),

/***/ "./node_modules/lodash/_createFind.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_createFind.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseIteratee = __webpack_require__(/*! ./_baseIteratee */ "./node_modules/lodash/_baseIteratee.js"),
    isArrayLike = __webpack_require__(/*! ./isArrayLike */ "./node_modules/lodash/isArrayLike.js"),
    keys = __webpack_require__(/*! ./keys */ "./node_modules/lodash/keys.js");

/**
 * Creates a `_.find` or `_.findLast` function.
 *
 * @private
 * @param {Function} findIndexFunc The function to find the collection index.
 * @returns {Function} Returns the new find function.
 */
function createFind(findIndexFunc) {
  return function(collection, predicate, fromIndex) {
    var iterable = Object(collection);
    if (!isArrayLike(collection)) {
      var iteratee = baseIteratee(predicate, 3);
      collection = keys(collection);
      predicate = function(key) { return iteratee(iterable[key], key, iterable); };
    }
    var index = findIndexFunc(collection, predicate, fromIndex);
    return index > -1 ? iterable[iteratee ? collection[index] : index] : undefined;
  };
}

module.exports = createFind;


/***/ }),

/***/ "./node_modules/lodash/_createHybrid.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_createHybrid.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var composeArgs = __webpack_require__(/*! ./_composeArgs */ "./node_modules/lodash/_composeArgs.js"),
    composeArgsRight = __webpack_require__(/*! ./_composeArgsRight */ "./node_modules/lodash/_composeArgsRight.js"),
    countHolders = __webpack_require__(/*! ./_countHolders */ "./node_modules/lodash/_countHolders.js"),
    createCtor = __webpack_require__(/*! ./_createCtor */ "./node_modules/lodash/_createCtor.js"),
    createRecurry = __webpack_require__(/*! ./_createRecurry */ "./node_modules/lodash/_createRecurry.js"),
    getHolder = __webpack_require__(/*! ./_getHolder */ "./node_modules/lodash/_getHolder.js"),
    reorder = __webpack_require__(/*! ./_reorder */ "./node_modules/lodash/_reorder.js"),
    replaceHolders = __webpack_require__(/*! ./_replaceHolders */ "./node_modules/lodash/_replaceHolders.js"),
    root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js");

/** Used to compose bitmasks for function metadata. */
var WRAP_BIND_FLAG = 1,
    WRAP_BIND_KEY_FLAG = 2,
    WRAP_CURRY_FLAG = 8,
    WRAP_CURRY_RIGHT_FLAG = 16,
    WRAP_ARY_FLAG = 128,
    WRAP_FLIP_FLAG = 512;

/**
 * Creates a function that wraps `func` to invoke it with optional `this`
 * binding of `thisArg`, partial application, and currying.
 *
 * @private
 * @param {Function|string} func The function or method name to wrap.
 * @param {number} bitmask The bitmask flags. See `createWrap` for more details.
 * @param {*} [thisArg] The `this` binding of `func`.
 * @param {Array} [partials] The arguments to prepend to those provided to
 *  the new function.
 * @param {Array} [holders] The `partials` placeholder indexes.
 * @param {Array} [partialsRight] The arguments to append to those provided
 *  to the new function.
 * @param {Array} [holdersRight] The `partialsRight` placeholder indexes.
 * @param {Array} [argPos] The argument positions of the new function.
 * @param {number} [ary] The arity cap of `func`.
 * @param {number} [arity] The arity of `func`.
 * @returns {Function} Returns the new wrapped function.
 */
function createHybrid(func, bitmask, thisArg, partials, holders, partialsRight, holdersRight, argPos, ary, arity) {
  var isAry = bitmask & WRAP_ARY_FLAG,
      isBind = bitmask & WRAP_BIND_FLAG,
      isBindKey = bitmask & WRAP_BIND_KEY_FLAG,
      isCurried = bitmask & (WRAP_CURRY_FLAG | WRAP_CURRY_RIGHT_FLAG),
      isFlip = bitmask & WRAP_FLIP_FLAG,
      Ctor = isBindKey ? undefined : createCtor(func);

  function wrapper() {
    var length = arguments.length,
        args = Array(length),
        index = length;

    while (index--) {
      args[index] = arguments[index];
    }
    if (isCurried) {
      var placeholder = getHolder(wrapper),
          holdersCount = countHolders(args, placeholder);
    }
    if (partials) {
      args = composeArgs(args, partials, holders, isCurried);
    }
    if (partialsRight) {
      args = composeArgsRight(args, partialsRight, holdersRight, isCurried);
    }
    length -= holdersCount;
    if (isCurried && length < arity) {
      var newHolders = replaceHolders(args, placeholder);
      return createRecurry(
        func, bitmask, createHybrid, wrapper.placeholder, thisArg,
        args, newHolders, argPos, ary, arity - length
      );
    }
    var thisBinding = isBind ? thisArg : this,
        fn = isBindKey ? thisBinding[func] : func;

    length = args.length;
    if (argPos) {
      args = reorder(args, argPos);
    } else if (isFlip && length > 1) {
      args.reverse();
    }
    if (isAry && ary < length) {
      args.length = ary;
    }
    if (this && this !== root && this instanceof wrapper) {
      fn = Ctor || createCtor(fn);
    }
    return fn.apply(thisBinding, args);
  }
  return wrapper;
}

module.exports = createHybrid;


/***/ }),

/***/ "./node_modules/lodash/_createPartial.js":
/*!***********************************************!*\
  !*** ./node_modules/lodash/_createPartial.js ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var apply = __webpack_require__(/*! ./_apply */ "./node_modules/lodash/_apply.js"),
    createCtor = __webpack_require__(/*! ./_createCtor */ "./node_modules/lodash/_createCtor.js"),
    root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js");

/** Used to compose bitmasks for function metadata. */
var WRAP_BIND_FLAG = 1;

/**
 * Creates a function that wraps `func` to invoke it with the `this` binding
 * of `thisArg` and `partials` prepended to the arguments it receives.
 *
 * @private
 * @param {Function} func The function to wrap.
 * @param {number} bitmask The bitmask flags. See `createWrap` for more details.
 * @param {*} thisArg The `this` binding of `func`.
 * @param {Array} partials The arguments to prepend to those provided to
 *  the new function.
 * @returns {Function} Returns the new wrapped function.
 */
function createPartial(func, bitmask, thisArg, partials) {
  var isBind = bitmask & WRAP_BIND_FLAG,
      Ctor = createCtor(func);

  function wrapper() {
    var argsIndex = -1,
        argsLength = arguments.length,
        leftIndex = -1,
        leftLength = partials.length,
        args = Array(leftLength + argsLength),
        fn = (this && this !== root && this instanceof wrapper) ? Ctor : func;

    while (++leftIndex < leftLength) {
      args[leftIndex] = partials[leftIndex];
    }
    while (argsLength--) {
      args[leftIndex++] = arguments[++argsIndex];
    }
    return apply(fn, isBind ? thisArg : this, args);
  }
  return wrapper;
}

module.exports = createPartial;


/***/ }),

/***/ "./node_modules/lodash/_createRecurry.js":
/*!***********************************************!*\
  !*** ./node_modules/lodash/_createRecurry.js ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var isLaziable = __webpack_require__(/*! ./_isLaziable */ "./node_modules/lodash/_isLaziable.js"),
    setData = __webpack_require__(/*! ./_setData */ "./node_modules/lodash/_setData.js"),
    setWrapToString = __webpack_require__(/*! ./_setWrapToString */ "./node_modules/lodash/_setWrapToString.js");

/** Used to compose bitmasks for function metadata. */
var WRAP_BIND_FLAG = 1,
    WRAP_BIND_KEY_FLAG = 2,
    WRAP_CURRY_BOUND_FLAG = 4,
    WRAP_CURRY_FLAG = 8,
    WRAP_PARTIAL_FLAG = 32,
    WRAP_PARTIAL_RIGHT_FLAG = 64;

/**
 * Creates a function that wraps `func` to continue currying.
 *
 * @private
 * @param {Function} func The function to wrap.
 * @param {number} bitmask The bitmask flags. See `createWrap` for more details.
 * @param {Function} wrapFunc The function to create the `func` wrapper.
 * @param {*} placeholder The placeholder value.
 * @param {*} [thisArg] The `this` binding of `func`.
 * @param {Array} [partials] The arguments to prepend to those provided to
 *  the new function.
 * @param {Array} [holders] The `partials` placeholder indexes.
 * @param {Array} [argPos] The argument positions of the new function.
 * @param {number} [ary] The arity cap of `func`.
 * @param {number} [arity] The arity of `func`.
 * @returns {Function} Returns the new wrapped function.
 */
function createRecurry(func, bitmask, wrapFunc, placeholder, thisArg, partials, holders, argPos, ary, arity) {
  var isCurry = bitmask & WRAP_CURRY_FLAG,
      newHolders = isCurry ? holders : undefined,
      newHoldersRight = isCurry ? undefined : holders,
      newPartials = isCurry ? partials : undefined,
      newPartialsRight = isCurry ? undefined : partials;

  bitmask |= (isCurry ? WRAP_PARTIAL_FLAG : WRAP_PARTIAL_RIGHT_FLAG);
  bitmask &= ~(isCurry ? WRAP_PARTIAL_RIGHT_FLAG : WRAP_PARTIAL_FLAG);

  if (!(bitmask & WRAP_CURRY_BOUND_FLAG)) {
    bitmask &= ~(WRAP_BIND_FLAG | WRAP_BIND_KEY_FLAG);
  }
  var newData = [
    func, bitmask, thisArg, newPartials, newHolders, newPartialsRight,
    newHoldersRight, argPos, ary, arity
  ];

  var result = wrapFunc.apply(undefined, newData);
  if (isLaziable(func)) {
    setData(result, newData);
  }
  result.placeholder = placeholder;
  return setWrapToString(result, func, bitmask);
}

module.exports = createRecurry;


/***/ }),

/***/ "./node_modules/lodash/_createWrap.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_createWrap.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseSetData = __webpack_require__(/*! ./_baseSetData */ "./node_modules/lodash/_baseSetData.js"),
    createBind = __webpack_require__(/*! ./_createBind */ "./node_modules/lodash/_createBind.js"),
    createCurry = __webpack_require__(/*! ./_createCurry */ "./node_modules/lodash/_createCurry.js"),
    createHybrid = __webpack_require__(/*! ./_createHybrid */ "./node_modules/lodash/_createHybrid.js"),
    createPartial = __webpack_require__(/*! ./_createPartial */ "./node_modules/lodash/_createPartial.js"),
    getData = __webpack_require__(/*! ./_getData */ "./node_modules/lodash/_getData.js"),
    mergeData = __webpack_require__(/*! ./_mergeData */ "./node_modules/lodash/_mergeData.js"),
    setData = __webpack_require__(/*! ./_setData */ "./node_modules/lodash/_setData.js"),
    setWrapToString = __webpack_require__(/*! ./_setWrapToString */ "./node_modules/lodash/_setWrapToString.js"),
    toInteger = __webpack_require__(/*! ./toInteger */ "./node_modules/lodash/toInteger.js");

/** Error message constants. */
var FUNC_ERROR_TEXT = 'Expected a function';

/** Used to compose bitmasks for function metadata. */
var WRAP_BIND_FLAG = 1,
    WRAP_BIND_KEY_FLAG = 2,
    WRAP_CURRY_FLAG = 8,
    WRAP_CURRY_RIGHT_FLAG = 16,
    WRAP_PARTIAL_FLAG = 32,
    WRAP_PARTIAL_RIGHT_FLAG = 64;

/* Built-in method references for those with the same name as other `lodash` methods. */
var nativeMax = Math.max;

/**
 * Creates a function that either curries or invokes `func` with optional
 * `this` binding and partially applied arguments.
 *
 * @private
 * @param {Function|string} func The function or method name to wrap.
 * @param {number} bitmask The bitmask flags.
 *    1 - `_.bind`
 *    2 - `_.bindKey`
 *    4 - `_.curry` or `_.curryRight` of a bound function
 *    8 - `_.curry`
 *   16 - `_.curryRight`
 *   32 - `_.partial`
 *   64 - `_.partialRight`
 *  128 - `_.rearg`
 *  256 - `_.ary`
 *  512 - `_.flip`
 * @param {*} [thisArg] The `this` binding of `func`.
 * @param {Array} [partials] The arguments to be partially applied.
 * @param {Array} [holders] The `partials` placeholder indexes.
 * @param {Array} [argPos] The argument positions of the new function.
 * @param {number} [ary] The arity cap of `func`.
 * @param {number} [arity] The arity of `func`.
 * @returns {Function} Returns the new wrapped function.
 */
function createWrap(func, bitmask, thisArg, partials, holders, argPos, ary, arity) {
  var isBindKey = bitmask & WRAP_BIND_KEY_FLAG;
  if (!isBindKey && typeof func != 'function') {
    throw new TypeError(FUNC_ERROR_TEXT);
  }
  var length = partials ? partials.length : 0;
  if (!length) {
    bitmask &= ~(WRAP_PARTIAL_FLAG | WRAP_PARTIAL_RIGHT_FLAG);
    partials = holders = undefined;
  }
  ary = ary === undefined ? ary : nativeMax(toInteger(ary), 0);
  arity = arity === undefined ? arity : toInteger(arity);
  length -= holders ? holders.length : 0;

  if (bitmask & WRAP_PARTIAL_RIGHT_FLAG) {
    var partialsRight = partials,
        holdersRight = holders;

    partials = holders = undefined;
  }
  var data = isBindKey ? undefined : getData(func);

  var newData = [
    func, bitmask, thisArg, partials, holders, partialsRight, holdersRight,
    argPos, ary, arity
  ];

  if (data) {
    mergeData(newData, data);
  }
  func = newData[0];
  bitmask = newData[1];
  thisArg = newData[2];
  partials = newData[3];
  holders = newData[4];
  arity = newData[9] = newData[9] === undefined
    ? (isBindKey ? 0 : func.length)
    : nativeMax(newData[9] - length, 0);

  if (!arity && bitmask & (WRAP_CURRY_FLAG | WRAP_CURRY_RIGHT_FLAG)) {
    bitmask &= ~(WRAP_CURRY_FLAG | WRAP_CURRY_RIGHT_FLAG);
  }
  if (!bitmask || bitmask == WRAP_BIND_FLAG) {
    var result = createBind(func, bitmask, thisArg);
  } else if (bitmask == WRAP_CURRY_FLAG || bitmask == WRAP_CURRY_RIGHT_FLAG) {
    result = createCurry(func, bitmask, arity);
  } else if ((bitmask == WRAP_PARTIAL_FLAG || bitmask == (WRAP_BIND_FLAG | WRAP_PARTIAL_FLAG)) && !holders.length) {
    result = createPartial(func, bitmask, thisArg, partials);
  } else {
    result = createHybrid.apply(undefined, newData);
  }
  var setter = data ? baseSetData : setData;
  return setWrapToString(setter(result, newData), func, bitmask);
}

module.exports = createWrap;


/***/ }),

/***/ "./node_modules/lodash/_defineProperty.js":
/*!************************************************!*\
  !*** ./node_modules/lodash/_defineProperty.js ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getNative = __webpack_require__(/*! ./_getNative */ "./node_modules/lodash/_getNative.js");

var defineProperty = (function() {
  try {
    var func = getNative(Object, 'defineProperty');
    func({}, '', {});
    return func;
  } catch (e) {}
}());

module.exports = defineProperty;


/***/ }),

/***/ "./node_modules/lodash/_equalArrays.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_equalArrays.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var SetCache = __webpack_require__(/*! ./_SetCache */ "./node_modules/lodash/_SetCache.js"),
    arraySome = __webpack_require__(/*! ./_arraySome */ "./node_modules/lodash/_arraySome.js"),
    cacheHas = __webpack_require__(/*! ./_cacheHas */ "./node_modules/lodash/_cacheHas.js");

/** Used to compose bitmasks for value comparisons. */
var COMPARE_PARTIAL_FLAG = 1,
    COMPARE_UNORDERED_FLAG = 2;

/**
 * A specialized version of `baseIsEqualDeep` for arrays with support for
 * partial deep comparisons.
 *
 * @private
 * @param {Array} array The array to compare.
 * @param {Array} other The other array to compare.
 * @param {number} bitmask The bitmask flags. See `baseIsEqual` for more details.
 * @param {Function} customizer The function to customize comparisons.
 * @param {Function} equalFunc The function to determine equivalents of values.
 * @param {Object} stack Tracks traversed `array` and `other` objects.
 * @returns {boolean} Returns `true` if the arrays are equivalent, else `false`.
 */
function equalArrays(array, other, bitmask, customizer, equalFunc, stack) {
  var isPartial = bitmask & COMPARE_PARTIAL_FLAG,
      arrLength = array.length,
      othLength = other.length;

  if (arrLength != othLength && !(isPartial && othLength > arrLength)) {
    return false;
  }
  // Check that cyclic values are equal.
  var arrStacked = stack.get(array);
  var othStacked = stack.get(other);
  if (arrStacked && othStacked) {
    return arrStacked == other && othStacked == array;
  }
  var index = -1,
      result = true,
      seen = (bitmask & COMPARE_UNORDERED_FLAG) ? new SetCache : undefined;

  stack.set(array, other);
  stack.set(other, array);

  // Ignore non-index properties.
  while (++index < arrLength) {
    var arrValue = array[index],
        othValue = other[index];

    if (customizer) {
      var compared = isPartial
        ? customizer(othValue, arrValue, index, other, array, stack)
        : customizer(arrValue, othValue, index, array, other, stack);
    }
    if (compared !== undefined) {
      if (compared) {
        continue;
      }
      result = false;
      break;
    }
    // Recursively compare arrays (susceptible to call stack limits).
    if (seen) {
      if (!arraySome(other, function(othValue, othIndex) {
            if (!cacheHas(seen, othIndex) &&
                (arrValue === othValue || equalFunc(arrValue, othValue, bitmask, customizer, stack))) {
              return seen.push(othIndex);
            }
          })) {
        result = false;
        break;
      }
    } else if (!(
          arrValue === othValue ||
            equalFunc(arrValue, othValue, bitmask, customizer, stack)
        )) {
      result = false;
      break;
    }
  }
  stack['delete'](array);
  stack['delete'](other);
  return result;
}

module.exports = equalArrays;


/***/ }),

/***/ "./node_modules/lodash/_equalByTag.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_equalByTag.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var Symbol = __webpack_require__(/*! ./_Symbol */ "./node_modules/lodash/_Symbol.js"),
    Uint8Array = __webpack_require__(/*! ./_Uint8Array */ "./node_modules/lodash/_Uint8Array.js"),
    eq = __webpack_require__(/*! ./eq */ "./node_modules/lodash/eq.js"),
    equalArrays = __webpack_require__(/*! ./_equalArrays */ "./node_modules/lodash/_equalArrays.js"),
    mapToArray = __webpack_require__(/*! ./_mapToArray */ "./node_modules/lodash/_mapToArray.js"),
    setToArray = __webpack_require__(/*! ./_setToArray */ "./node_modules/lodash/_setToArray.js");

/** Used to compose bitmasks for value comparisons. */
var COMPARE_PARTIAL_FLAG = 1,
    COMPARE_UNORDERED_FLAG = 2;

/** `Object#toString` result references. */
var boolTag = '[object Boolean]',
    dateTag = '[object Date]',
    errorTag = '[object Error]',
    mapTag = '[object Map]',
    numberTag = '[object Number]',
    regexpTag = '[object RegExp]',
    setTag = '[object Set]',
    stringTag = '[object String]',
    symbolTag = '[object Symbol]';

var arrayBufferTag = '[object ArrayBuffer]',
    dataViewTag = '[object DataView]';

/** Used to convert symbols to primitives and strings. */
var symbolProto = Symbol ? Symbol.prototype : undefined,
    symbolValueOf = symbolProto ? symbolProto.valueOf : undefined;

/**
 * A specialized version of `baseIsEqualDeep` for comparing objects of
 * the same `toStringTag`.
 *
 * **Note:** This function only supports comparing values with tags of
 * `Boolean`, `Date`, `Error`, `Number`, `RegExp`, or `String`.
 *
 * @private
 * @param {Object} object The object to compare.
 * @param {Object} other The other object to compare.
 * @param {string} tag The `toStringTag` of the objects to compare.
 * @param {number} bitmask The bitmask flags. See `baseIsEqual` for more details.
 * @param {Function} customizer The function to customize comparisons.
 * @param {Function} equalFunc The function to determine equivalents of values.
 * @param {Object} stack Tracks traversed `object` and `other` objects.
 * @returns {boolean} Returns `true` if the objects are equivalent, else `false`.
 */
function equalByTag(object, other, tag, bitmask, customizer, equalFunc, stack) {
  switch (tag) {
    case dataViewTag:
      if ((object.byteLength != other.byteLength) ||
          (object.byteOffset != other.byteOffset)) {
        return false;
      }
      object = object.buffer;
      other = other.buffer;

    case arrayBufferTag:
      if ((object.byteLength != other.byteLength) ||
          !equalFunc(new Uint8Array(object), new Uint8Array(other))) {
        return false;
      }
      return true;

    case boolTag:
    case dateTag:
    case numberTag:
      // Coerce booleans to `1` or `0` and dates to milliseconds.
      // Invalid dates are coerced to `NaN`.
      return eq(+object, +other);

    case errorTag:
      return object.name == other.name && object.message == other.message;

    case regexpTag:
    case stringTag:
      // Coerce regexes to strings and treat strings, primitives and objects,
      // as equal. See http://www.ecma-international.org/ecma-262/7.0/#sec-regexp.prototype.tostring
      // for more details.
      return object == (other + '');

    case mapTag:
      var convert = mapToArray;

    case setTag:
      var isPartial = bitmask & COMPARE_PARTIAL_FLAG;
      convert || (convert = setToArray);

      if (object.size != other.size && !isPartial) {
        return false;
      }
      // Assume cyclic values are equal.
      var stacked = stack.get(object);
      if (stacked) {
        return stacked == other;
      }
      bitmask |= COMPARE_UNORDERED_FLAG;

      // Recursively compare objects (susceptible to call stack limits).
      stack.set(object, other);
      var result = equalArrays(convert(object), convert(other), bitmask, customizer, equalFunc, stack);
      stack['delete'](object);
      return result;

    case symbolTag:
      if (symbolValueOf) {
        return symbolValueOf.call(object) == symbolValueOf.call(other);
      }
  }
  return false;
}

module.exports = equalByTag;


/***/ }),

/***/ "./node_modules/lodash/_equalObjects.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_equalObjects.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getAllKeys = __webpack_require__(/*! ./_getAllKeys */ "./node_modules/lodash/_getAllKeys.js");

/** Used to compose bitmasks for value comparisons. */
var COMPARE_PARTIAL_FLAG = 1;

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/**
 * A specialized version of `baseIsEqualDeep` for objects with support for
 * partial deep comparisons.
 *
 * @private
 * @param {Object} object The object to compare.
 * @param {Object} other The other object to compare.
 * @param {number} bitmask The bitmask flags. See `baseIsEqual` for more details.
 * @param {Function} customizer The function to customize comparisons.
 * @param {Function} equalFunc The function to determine equivalents of values.
 * @param {Object} stack Tracks traversed `object` and `other` objects.
 * @returns {boolean} Returns `true` if the objects are equivalent, else `false`.
 */
function equalObjects(object, other, bitmask, customizer, equalFunc, stack) {
  var isPartial = bitmask & COMPARE_PARTIAL_FLAG,
      objProps = getAllKeys(object),
      objLength = objProps.length,
      othProps = getAllKeys(other),
      othLength = othProps.length;

  if (objLength != othLength && !isPartial) {
    return false;
  }
  var index = objLength;
  while (index--) {
    var key = objProps[index];
    if (!(isPartial ? key in other : hasOwnProperty.call(other, key))) {
      return false;
    }
  }
  // Check that cyclic values are equal.
  var objStacked = stack.get(object);
  var othStacked = stack.get(other);
  if (objStacked && othStacked) {
    return objStacked == other && othStacked == object;
  }
  var result = true;
  stack.set(object, other);
  stack.set(other, object);

  var skipCtor = isPartial;
  while (++index < objLength) {
    key = objProps[index];
    var objValue = object[key],
        othValue = other[key];

    if (customizer) {
      var compared = isPartial
        ? customizer(othValue, objValue, key, other, object, stack)
        : customizer(objValue, othValue, key, object, other, stack);
    }
    // Recursively compare objects (susceptible to call stack limits).
    if (!(compared === undefined
          ? (objValue === othValue || equalFunc(objValue, othValue, bitmask, customizer, stack))
          : compared
        )) {
      result = false;
      break;
    }
    skipCtor || (skipCtor = key == 'constructor');
  }
  if (result && !skipCtor) {
    var objCtor = object.constructor,
        othCtor = other.constructor;

    // Non `Object` object instances with different constructors are not equal.
    if (objCtor != othCtor &&
        ('constructor' in object && 'constructor' in other) &&
        !(typeof objCtor == 'function' && objCtor instanceof objCtor &&
          typeof othCtor == 'function' && othCtor instanceof othCtor)) {
      result = false;
    }
  }
  stack['delete'](object);
  stack['delete'](other);
  return result;
}

module.exports = equalObjects;


/***/ }),

/***/ "./node_modules/lodash/_flatRest.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_flatRest.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var flatten = __webpack_require__(/*! ./flatten */ "./node_modules/lodash/flatten.js"),
    overRest = __webpack_require__(/*! ./_overRest */ "./node_modules/lodash/_overRest.js"),
    setToString = __webpack_require__(/*! ./_setToString */ "./node_modules/lodash/_setToString.js");

/**
 * A specialized version of `baseRest` which flattens the rest array.
 *
 * @private
 * @param {Function} func The function to apply a rest parameter to.
 * @returns {Function} Returns the new function.
 */
function flatRest(func) {
  return setToString(overRest(func, undefined, flatten), func + '');
}

module.exports = flatRest;


/***/ }),

/***/ "./node_modules/lodash/_freeGlobal.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_freeGlobal.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

/* WEBPACK VAR INJECTION */(function(global) {/** Detect free variable `global` from Node.js. */
var freeGlobal = typeof global == 'object' && global && global.Object === Object && global;

module.exports = freeGlobal;

/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./../webpack/buildin/global.js */ "./node_modules/webpack/buildin/global.js")))

/***/ }),

/***/ "./node_modules/lodash/_getAllKeys.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_getAllKeys.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseGetAllKeys = __webpack_require__(/*! ./_baseGetAllKeys */ "./node_modules/lodash/_baseGetAllKeys.js"),
    getSymbols = __webpack_require__(/*! ./_getSymbols */ "./node_modules/lodash/_getSymbols.js"),
    keys = __webpack_require__(/*! ./keys */ "./node_modules/lodash/keys.js");

/**
 * Creates an array of own enumerable property names and symbols of `object`.
 *
 * @private
 * @param {Object} object The object to query.
 * @returns {Array} Returns the array of property names and symbols.
 */
function getAllKeys(object) {
  return baseGetAllKeys(object, keys, getSymbols);
}

module.exports = getAllKeys;


/***/ }),

/***/ "./node_modules/lodash/_getData.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_getData.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var metaMap = __webpack_require__(/*! ./_metaMap */ "./node_modules/lodash/_metaMap.js"),
    noop = __webpack_require__(/*! ./noop */ "./node_modules/lodash/noop.js");

/**
 * Gets metadata for `func`.
 *
 * @private
 * @param {Function} func The function to query.
 * @returns {*} Returns the metadata for `func`.
 */
var getData = !metaMap ? noop : function(func) {
  return metaMap.get(func);
};

module.exports = getData;


/***/ }),

/***/ "./node_modules/lodash/_getFuncName.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_getFuncName.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var realNames = __webpack_require__(/*! ./_realNames */ "./node_modules/lodash/_realNames.js");

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/**
 * Gets the name of `func`.
 *
 * @private
 * @param {Function} func The function to query.
 * @returns {string} Returns the function name.
 */
function getFuncName(func) {
  var result = (func.name + ''),
      array = realNames[result],
      length = hasOwnProperty.call(realNames, result) ? array.length : 0;

  while (length--) {
    var data = array[length],
        otherFunc = data.func;
    if (otherFunc == null || otherFunc == func) {
      return data.name;
    }
  }
  return result;
}

module.exports = getFuncName;


/***/ }),

/***/ "./node_modules/lodash/_getHolder.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_getHolder.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Gets the argument placeholder value for `func`.
 *
 * @private
 * @param {Function} func The function to inspect.
 * @returns {*} Returns the placeholder value.
 */
function getHolder(func) {
  var object = func;
  return object.placeholder;
}

module.exports = getHolder;


/***/ }),

/***/ "./node_modules/lodash/_getMapData.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_getMapData.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var isKeyable = __webpack_require__(/*! ./_isKeyable */ "./node_modules/lodash/_isKeyable.js");

/**
 * Gets the data for `map`.
 *
 * @private
 * @param {Object} map The map to query.
 * @param {string} key The reference key.
 * @returns {*} Returns the map data.
 */
function getMapData(map, key) {
  var data = map.__data__;
  return isKeyable(key)
    ? data[typeof key == 'string' ? 'string' : 'hash']
    : data.map;
}

module.exports = getMapData;


/***/ }),

/***/ "./node_modules/lodash/_getMatchData.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_getMatchData.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var isStrictComparable = __webpack_require__(/*! ./_isStrictComparable */ "./node_modules/lodash/_isStrictComparable.js"),
    keys = __webpack_require__(/*! ./keys */ "./node_modules/lodash/keys.js");

/**
 * Gets the property names, values, and compare flags of `object`.
 *
 * @private
 * @param {Object} object The object to query.
 * @returns {Array} Returns the match data of `object`.
 */
function getMatchData(object) {
  var result = keys(object),
      length = result.length;

  while (length--) {
    var key = result[length],
        value = object[key];

    result[length] = [key, value, isStrictComparable(value)];
  }
  return result;
}

module.exports = getMatchData;


/***/ }),

/***/ "./node_modules/lodash/_getNative.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_getNative.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseIsNative = __webpack_require__(/*! ./_baseIsNative */ "./node_modules/lodash/_baseIsNative.js"),
    getValue = __webpack_require__(/*! ./_getValue */ "./node_modules/lodash/_getValue.js");

/**
 * Gets the native function at `key` of `object`.
 *
 * @private
 * @param {Object} object The object to query.
 * @param {string} key The key of the method to get.
 * @returns {*} Returns the function if it's native, else `undefined`.
 */
function getNative(object, key) {
  var value = getValue(object, key);
  return baseIsNative(value) ? value : undefined;
}

module.exports = getNative;


/***/ }),

/***/ "./node_modules/lodash/_getRawTag.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_getRawTag.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var Symbol = __webpack_require__(/*! ./_Symbol */ "./node_modules/lodash/_Symbol.js");

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/**
 * Used to resolve the
 * [`toStringTag`](http://ecma-international.org/ecma-262/7.0/#sec-object.prototype.tostring)
 * of values.
 */
var nativeObjectToString = objectProto.toString;

/** Built-in value references. */
var symToStringTag = Symbol ? Symbol.toStringTag : undefined;

/**
 * A specialized version of `baseGetTag` which ignores `Symbol.toStringTag` values.
 *
 * @private
 * @param {*} value The value to query.
 * @returns {string} Returns the raw `toStringTag`.
 */
function getRawTag(value) {
  var isOwn = hasOwnProperty.call(value, symToStringTag),
      tag = value[symToStringTag];

  try {
    value[symToStringTag] = undefined;
    var unmasked = true;
  } catch (e) {}

  var result = nativeObjectToString.call(value);
  if (unmasked) {
    if (isOwn) {
      value[symToStringTag] = tag;
    } else {
      delete value[symToStringTag];
    }
  }
  return result;
}

module.exports = getRawTag;


/***/ }),

/***/ "./node_modules/lodash/_getSymbols.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_getSymbols.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var arrayFilter = __webpack_require__(/*! ./_arrayFilter */ "./node_modules/lodash/_arrayFilter.js"),
    stubArray = __webpack_require__(/*! ./stubArray */ "./node_modules/lodash/stubArray.js");

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Built-in value references. */
var propertyIsEnumerable = objectProto.propertyIsEnumerable;

/* Built-in method references for those with the same name as other `lodash` methods. */
var nativeGetSymbols = Object.getOwnPropertySymbols;

/**
 * Creates an array of the own enumerable symbols of `object`.
 *
 * @private
 * @param {Object} object The object to query.
 * @returns {Array} Returns the array of symbols.
 */
var getSymbols = !nativeGetSymbols ? stubArray : function(object) {
  if (object == null) {
    return [];
  }
  object = Object(object);
  return arrayFilter(nativeGetSymbols(object), function(symbol) {
    return propertyIsEnumerable.call(object, symbol);
  });
};

module.exports = getSymbols;


/***/ }),

/***/ "./node_modules/lodash/_getTag.js":
/*!****************************************!*\
  !*** ./node_modules/lodash/_getTag.js ***!
  \****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var DataView = __webpack_require__(/*! ./_DataView */ "./node_modules/lodash/_DataView.js"),
    Map = __webpack_require__(/*! ./_Map */ "./node_modules/lodash/_Map.js"),
    Promise = __webpack_require__(/*! ./_Promise */ "./node_modules/lodash/_Promise.js"),
    Set = __webpack_require__(/*! ./_Set */ "./node_modules/lodash/_Set.js"),
    WeakMap = __webpack_require__(/*! ./_WeakMap */ "./node_modules/lodash/_WeakMap.js"),
    baseGetTag = __webpack_require__(/*! ./_baseGetTag */ "./node_modules/lodash/_baseGetTag.js"),
    toSource = __webpack_require__(/*! ./_toSource */ "./node_modules/lodash/_toSource.js");

/** `Object#toString` result references. */
var mapTag = '[object Map]',
    objectTag = '[object Object]',
    promiseTag = '[object Promise]',
    setTag = '[object Set]',
    weakMapTag = '[object WeakMap]';

var dataViewTag = '[object DataView]';

/** Used to detect maps, sets, and weakmaps. */
var dataViewCtorString = toSource(DataView),
    mapCtorString = toSource(Map),
    promiseCtorString = toSource(Promise),
    setCtorString = toSource(Set),
    weakMapCtorString = toSource(WeakMap);

/**
 * Gets the `toStringTag` of `value`.
 *
 * @private
 * @param {*} value The value to query.
 * @returns {string} Returns the `toStringTag`.
 */
var getTag = baseGetTag;

// Fallback for data views, maps, sets, and weak maps in IE 11 and promises in Node.js < 6.
if ((DataView && getTag(new DataView(new ArrayBuffer(1))) != dataViewTag) ||
    (Map && getTag(new Map) != mapTag) ||
    (Promise && getTag(Promise.resolve()) != promiseTag) ||
    (Set && getTag(new Set) != setTag) ||
    (WeakMap && getTag(new WeakMap) != weakMapTag)) {
  getTag = function(value) {
    var result = baseGetTag(value),
        Ctor = result == objectTag ? value.constructor : undefined,
        ctorString = Ctor ? toSource(Ctor) : '';

    if (ctorString) {
      switch (ctorString) {
        case dataViewCtorString: return dataViewTag;
        case mapCtorString: return mapTag;
        case promiseCtorString: return promiseTag;
        case setCtorString: return setTag;
        case weakMapCtorString: return weakMapTag;
      }
    }
    return result;
  };
}

module.exports = getTag;


/***/ }),

/***/ "./node_modules/lodash/_getValue.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_getValue.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Gets the value at `key` of `object`.
 *
 * @private
 * @param {Object} [object] The object to query.
 * @param {string} key The key of the property to get.
 * @returns {*} Returns the property value.
 */
function getValue(object, key) {
  return object == null ? undefined : object[key];
}

module.exports = getValue;


/***/ }),

/***/ "./node_modules/lodash/_getWrapDetails.js":
/*!************************************************!*\
  !*** ./node_modules/lodash/_getWrapDetails.js ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/** Used to match wrap detail comments. */
var reWrapDetails = /\{\n\/\* \[wrapped with (.+)\] \*/,
    reSplitDetails = /,? & /;

/**
 * Extracts wrapper details from the `source` body comment.
 *
 * @private
 * @param {string} source The source to inspect.
 * @returns {Array} Returns the wrapper details.
 */
function getWrapDetails(source) {
  var match = source.match(reWrapDetails);
  return match ? match[1].split(reSplitDetails) : [];
}

module.exports = getWrapDetails;


/***/ }),

/***/ "./node_modules/lodash/_hasPath.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_hasPath.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var castPath = __webpack_require__(/*! ./_castPath */ "./node_modules/lodash/_castPath.js"),
    isArguments = __webpack_require__(/*! ./isArguments */ "./node_modules/lodash/isArguments.js"),
    isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js"),
    isIndex = __webpack_require__(/*! ./_isIndex */ "./node_modules/lodash/_isIndex.js"),
    isLength = __webpack_require__(/*! ./isLength */ "./node_modules/lodash/isLength.js"),
    toKey = __webpack_require__(/*! ./_toKey */ "./node_modules/lodash/_toKey.js");

/**
 * Checks if `path` exists on `object`.
 *
 * @private
 * @param {Object} object The object to query.
 * @param {Array|string} path The path to check.
 * @param {Function} hasFunc The function to check properties.
 * @returns {boolean} Returns `true` if `path` exists, else `false`.
 */
function hasPath(object, path, hasFunc) {
  path = castPath(path, object);

  var index = -1,
      length = path.length,
      result = false;

  while (++index < length) {
    var key = toKey(path[index]);
    if (!(result = object != null && hasFunc(object, key))) {
      break;
    }
    object = object[key];
  }
  if (result || ++index != length) {
    return result;
  }
  length = object == null ? 0 : object.length;
  return !!length && isLength(length) && isIndex(key, length) &&
    (isArray(object) || isArguments(object));
}

module.exports = hasPath;


/***/ }),

/***/ "./node_modules/lodash/_hashClear.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_hashClear.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var nativeCreate = __webpack_require__(/*! ./_nativeCreate */ "./node_modules/lodash/_nativeCreate.js");

/**
 * Removes all key-value entries from the hash.
 *
 * @private
 * @name clear
 * @memberOf Hash
 */
function hashClear() {
  this.__data__ = nativeCreate ? nativeCreate(null) : {};
  this.size = 0;
}

module.exports = hashClear;


/***/ }),

/***/ "./node_modules/lodash/_hashDelete.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_hashDelete.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Removes `key` and its value from the hash.
 *
 * @private
 * @name delete
 * @memberOf Hash
 * @param {Object} hash The hash to modify.
 * @param {string} key The key of the value to remove.
 * @returns {boolean} Returns `true` if the entry was removed, else `false`.
 */
function hashDelete(key) {
  var result = this.has(key) && delete this.__data__[key];
  this.size -= result ? 1 : 0;
  return result;
}

module.exports = hashDelete;


/***/ }),

/***/ "./node_modules/lodash/_hashGet.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_hashGet.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var nativeCreate = __webpack_require__(/*! ./_nativeCreate */ "./node_modules/lodash/_nativeCreate.js");

/** Used to stand-in for `undefined` hash values. */
var HASH_UNDEFINED = '__lodash_hash_undefined__';

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/**
 * Gets the hash value for `key`.
 *
 * @private
 * @name get
 * @memberOf Hash
 * @param {string} key The key of the value to get.
 * @returns {*} Returns the entry value.
 */
function hashGet(key) {
  var data = this.__data__;
  if (nativeCreate) {
    var result = data[key];
    return result === HASH_UNDEFINED ? undefined : result;
  }
  return hasOwnProperty.call(data, key) ? data[key] : undefined;
}

module.exports = hashGet;


/***/ }),

/***/ "./node_modules/lodash/_hashHas.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_hashHas.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var nativeCreate = __webpack_require__(/*! ./_nativeCreate */ "./node_modules/lodash/_nativeCreate.js");

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/**
 * Checks if a hash value for `key` exists.
 *
 * @private
 * @name has
 * @memberOf Hash
 * @param {string} key The key of the entry to check.
 * @returns {boolean} Returns `true` if an entry for `key` exists, else `false`.
 */
function hashHas(key) {
  var data = this.__data__;
  return nativeCreate ? (data[key] !== undefined) : hasOwnProperty.call(data, key);
}

module.exports = hashHas;


/***/ }),

/***/ "./node_modules/lodash/_hashSet.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_hashSet.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var nativeCreate = __webpack_require__(/*! ./_nativeCreate */ "./node_modules/lodash/_nativeCreate.js");

/** Used to stand-in for `undefined` hash values. */
var HASH_UNDEFINED = '__lodash_hash_undefined__';

/**
 * Sets the hash `key` to `value`.
 *
 * @private
 * @name set
 * @memberOf Hash
 * @param {string} key The key of the value to set.
 * @param {*} value The value to set.
 * @returns {Object} Returns the hash instance.
 */
function hashSet(key, value) {
  var data = this.__data__;
  this.size += this.has(key) ? 0 : 1;
  data[key] = (nativeCreate && value === undefined) ? HASH_UNDEFINED : value;
  return this;
}

module.exports = hashSet;


/***/ }),

/***/ "./node_modules/lodash/_insertWrapDetails.js":
/*!***************************************************!*\
  !*** ./node_modules/lodash/_insertWrapDetails.js ***!
  \***************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/** Used to match wrap detail comments. */
var reWrapComment = /\{(?:\n\/\* \[wrapped with .+\] \*\/)?\n?/;

/**
 * Inserts wrapper `details` in a comment at the top of the `source` body.
 *
 * @private
 * @param {string} source The source to modify.
 * @returns {Array} details The details to insert.
 * @returns {string} Returns the modified source.
 */
function insertWrapDetails(source, details) {
  var length = details.length;
  if (!length) {
    return source;
  }
  var lastIndex = length - 1;
  details[lastIndex] = (length > 1 ? '& ' : '') + details[lastIndex];
  details = details.join(length > 2 ? ', ' : ' ');
  return source.replace(reWrapComment, '{\n/* [wrapped with ' + details + '] */\n');
}

module.exports = insertWrapDetails;


/***/ }),

/***/ "./node_modules/lodash/_isFlattenable.js":
/*!***********************************************!*\
  !*** ./node_modules/lodash/_isFlattenable.js ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var Symbol = __webpack_require__(/*! ./_Symbol */ "./node_modules/lodash/_Symbol.js"),
    isArguments = __webpack_require__(/*! ./isArguments */ "./node_modules/lodash/isArguments.js"),
    isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js");

/** Built-in value references. */
var spreadableSymbol = Symbol ? Symbol.isConcatSpreadable : undefined;

/**
 * Checks if `value` is a flattenable `arguments` object or array.
 *
 * @private
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is flattenable, else `false`.
 */
function isFlattenable(value) {
  return isArray(value) || isArguments(value) ||
    !!(spreadableSymbol && value && value[spreadableSymbol]);
}

module.exports = isFlattenable;


/***/ }),

/***/ "./node_modules/lodash/_isIndex.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_isIndex.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/** Used as references for various `Number` constants. */
var MAX_SAFE_INTEGER = 9007199254740991;

/** Used to detect unsigned integer values. */
var reIsUint = /^(?:0|[1-9]\d*)$/;

/**
 * Checks if `value` is a valid array-like index.
 *
 * @private
 * @param {*} value The value to check.
 * @param {number} [length=MAX_SAFE_INTEGER] The upper bounds of a valid index.
 * @returns {boolean} Returns `true` if `value` is a valid index, else `false`.
 */
function isIndex(value, length) {
  var type = typeof value;
  length = length == null ? MAX_SAFE_INTEGER : length;

  return !!length &&
    (type == 'number' ||
      (type != 'symbol' && reIsUint.test(value))) &&
        (value > -1 && value % 1 == 0 && value < length);
}

module.exports = isIndex;


/***/ }),

/***/ "./node_modules/lodash/_isIterateeCall.js":
/*!************************************************!*\
  !*** ./node_modules/lodash/_isIterateeCall.js ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var eq = __webpack_require__(/*! ./eq */ "./node_modules/lodash/eq.js"),
    isArrayLike = __webpack_require__(/*! ./isArrayLike */ "./node_modules/lodash/isArrayLike.js"),
    isIndex = __webpack_require__(/*! ./_isIndex */ "./node_modules/lodash/_isIndex.js"),
    isObject = __webpack_require__(/*! ./isObject */ "./node_modules/lodash/isObject.js");

/**
 * Checks if the given arguments are from an iteratee call.
 *
 * @private
 * @param {*} value The potential iteratee value argument.
 * @param {*} index The potential iteratee index or key argument.
 * @param {*} object The potential iteratee object argument.
 * @returns {boolean} Returns `true` if the arguments are from an iteratee call,
 *  else `false`.
 */
function isIterateeCall(value, index, object) {
  if (!isObject(object)) {
    return false;
  }
  var type = typeof index;
  if (type == 'number'
        ? (isArrayLike(object) && isIndex(index, object.length))
        : (type == 'string' && index in object)
      ) {
    return eq(object[index], value);
  }
  return false;
}

module.exports = isIterateeCall;


/***/ }),

/***/ "./node_modules/lodash/_isKey.js":
/*!***************************************!*\
  !*** ./node_modules/lodash/_isKey.js ***!
  \***************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js"),
    isSymbol = __webpack_require__(/*! ./isSymbol */ "./node_modules/lodash/isSymbol.js");

/** Used to match property names within property paths. */
var reIsDeepProp = /\.|\[(?:[^[\]]*|(["'])(?:(?!\1)[^\\]|\\.)*?\1)\]/,
    reIsPlainProp = /^\w*$/;

/**
 * Checks if `value` is a property name and not a property path.
 *
 * @private
 * @param {*} value The value to check.
 * @param {Object} [object] The object to query keys on.
 * @returns {boolean} Returns `true` if `value` is a property name, else `false`.
 */
function isKey(value, object) {
  if (isArray(value)) {
    return false;
  }
  var type = typeof value;
  if (type == 'number' || type == 'symbol' || type == 'boolean' ||
      value == null || isSymbol(value)) {
    return true;
  }
  return reIsPlainProp.test(value) || !reIsDeepProp.test(value) ||
    (object != null && value in Object(object));
}

module.exports = isKey;


/***/ }),

/***/ "./node_modules/lodash/_isKeyable.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_isKeyable.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Checks if `value` is suitable for use as unique object key.
 *
 * @private
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is suitable, else `false`.
 */
function isKeyable(value) {
  var type = typeof value;
  return (type == 'string' || type == 'number' || type == 'symbol' || type == 'boolean')
    ? (value !== '__proto__')
    : (value === null);
}

module.exports = isKeyable;


/***/ }),

/***/ "./node_modules/lodash/_isLaziable.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_isLaziable.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var LazyWrapper = __webpack_require__(/*! ./_LazyWrapper */ "./node_modules/lodash/_LazyWrapper.js"),
    getData = __webpack_require__(/*! ./_getData */ "./node_modules/lodash/_getData.js"),
    getFuncName = __webpack_require__(/*! ./_getFuncName */ "./node_modules/lodash/_getFuncName.js"),
    lodash = __webpack_require__(/*! ./wrapperLodash */ "./node_modules/lodash/wrapperLodash.js");

/**
 * Checks if `func` has a lazy counterpart.
 *
 * @private
 * @param {Function} func The function to check.
 * @returns {boolean} Returns `true` if `func` has a lazy counterpart,
 *  else `false`.
 */
function isLaziable(func) {
  var funcName = getFuncName(func),
      other = lodash[funcName];

  if (typeof other != 'function' || !(funcName in LazyWrapper.prototype)) {
    return false;
  }
  if (func === other) {
    return true;
  }
  var data = getData(other);
  return !!data && func === data[0];
}

module.exports = isLaziable;


/***/ }),

/***/ "./node_modules/lodash/_isMasked.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_isMasked.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var coreJsData = __webpack_require__(/*! ./_coreJsData */ "./node_modules/lodash/_coreJsData.js");

/** Used to detect methods masquerading as native. */
var maskSrcKey = (function() {
  var uid = /[^.]+$/.exec(coreJsData && coreJsData.keys && coreJsData.keys.IE_PROTO || '');
  return uid ? ('Symbol(src)_1.' + uid) : '';
}());

/**
 * Checks if `func` has its source masked.
 *
 * @private
 * @param {Function} func The function to check.
 * @returns {boolean} Returns `true` if `func` is masked, else `false`.
 */
function isMasked(func) {
  return !!maskSrcKey && (maskSrcKey in func);
}

module.exports = isMasked;


/***/ }),

/***/ "./node_modules/lodash/_isPrototype.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_isPrototype.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/** Used for built-in method references. */
var objectProto = Object.prototype;

/**
 * Checks if `value` is likely a prototype object.
 *
 * @private
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is a prototype, else `false`.
 */
function isPrototype(value) {
  var Ctor = value && value.constructor,
      proto = (typeof Ctor == 'function' && Ctor.prototype) || objectProto;

  return value === proto;
}

module.exports = isPrototype;


/***/ }),

/***/ "./node_modules/lodash/_isStrictComparable.js":
/*!****************************************************!*\
  !*** ./node_modules/lodash/_isStrictComparable.js ***!
  \****************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var isObject = __webpack_require__(/*! ./isObject */ "./node_modules/lodash/isObject.js");

/**
 * Checks if `value` is suitable for strict equality comparisons, i.e. `===`.
 *
 * @private
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` if suitable for strict
 *  equality comparisons, else `false`.
 */
function isStrictComparable(value) {
  return value === value && !isObject(value);
}

module.exports = isStrictComparable;


/***/ }),

/***/ "./node_modules/lodash/_listCacheClear.js":
/*!************************************************!*\
  !*** ./node_modules/lodash/_listCacheClear.js ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Removes all key-value entries from the list cache.
 *
 * @private
 * @name clear
 * @memberOf ListCache
 */
function listCacheClear() {
  this.__data__ = [];
  this.size = 0;
}

module.exports = listCacheClear;


/***/ }),

/***/ "./node_modules/lodash/_listCacheDelete.js":
/*!*************************************************!*\
  !*** ./node_modules/lodash/_listCacheDelete.js ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var assocIndexOf = __webpack_require__(/*! ./_assocIndexOf */ "./node_modules/lodash/_assocIndexOf.js");

/** Used for built-in method references. */
var arrayProto = Array.prototype;

/** Built-in value references. */
var splice = arrayProto.splice;

/**
 * Removes `key` and its value from the list cache.
 *
 * @private
 * @name delete
 * @memberOf ListCache
 * @param {string} key The key of the value to remove.
 * @returns {boolean} Returns `true` if the entry was removed, else `false`.
 */
function listCacheDelete(key) {
  var data = this.__data__,
      index = assocIndexOf(data, key);

  if (index < 0) {
    return false;
  }
  var lastIndex = data.length - 1;
  if (index == lastIndex) {
    data.pop();
  } else {
    splice.call(data, index, 1);
  }
  --this.size;
  return true;
}

module.exports = listCacheDelete;


/***/ }),

/***/ "./node_modules/lodash/_listCacheGet.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_listCacheGet.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var assocIndexOf = __webpack_require__(/*! ./_assocIndexOf */ "./node_modules/lodash/_assocIndexOf.js");

/**
 * Gets the list cache value for `key`.
 *
 * @private
 * @name get
 * @memberOf ListCache
 * @param {string} key The key of the value to get.
 * @returns {*} Returns the entry value.
 */
function listCacheGet(key) {
  var data = this.__data__,
      index = assocIndexOf(data, key);

  return index < 0 ? undefined : data[index][1];
}

module.exports = listCacheGet;


/***/ }),

/***/ "./node_modules/lodash/_listCacheHas.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_listCacheHas.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var assocIndexOf = __webpack_require__(/*! ./_assocIndexOf */ "./node_modules/lodash/_assocIndexOf.js");

/**
 * Checks if a list cache value for `key` exists.
 *
 * @private
 * @name has
 * @memberOf ListCache
 * @param {string} key The key of the entry to check.
 * @returns {boolean} Returns `true` if an entry for `key` exists, else `false`.
 */
function listCacheHas(key) {
  return assocIndexOf(this.__data__, key) > -1;
}

module.exports = listCacheHas;


/***/ }),

/***/ "./node_modules/lodash/_listCacheSet.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_listCacheSet.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var assocIndexOf = __webpack_require__(/*! ./_assocIndexOf */ "./node_modules/lodash/_assocIndexOf.js");

/**
 * Sets the list cache `key` to `value`.
 *
 * @private
 * @name set
 * @memberOf ListCache
 * @param {string} key The key of the value to set.
 * @param {*} value The value to set.
 * @returns {Object} Returns the list cache instance.
 */
function listCacheSet(key, value) {
  var data = this.__data__,
      index = assocIndexOf(data, key);

  if (index < 0) {
    ++this.size;
    data.push([key, value]);
  } else {
    data[index][1] = value;
  }
  return this;
}

module.exports = listCacheSet;


/***/ }),

/***/ "./node_modules/lodash/_mapCacheClear.js":
/*!***********************************************!*\
  !*** ./node_modules/lodash/_mapCacheClear.js ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var Hash = __webpack_require__(/*! ./_Hash */ "./node_modules/lodash/_Hash.js"),
    ListCache = __webpack_require__(/*! ./_ListCache */ "./node_modules/lodash/_ListCache.js"),
    Map = __webpack_require__(/*! ./_Map */ "./node_modules/lodash/_Map.js");

/**
 * Removes all key-value entries from the map.
 *
 * @private
 * @name clear
 * @memberOf MapCache
 */
function mapCacheClear() {
  this.size = 0;
  this.__data__ = {
    'hash': new Hash,
    'map': new (Map || ListCache),
    'string': new Hash
  };
}

module.exports = mapCacheClear;


/***/ }),

/***/ "./node_modules/lodash/_mapCacheDelete.js":
/*!************************************************!*\
  !*** ./node_modules/lodash/_mapCacheDelete.js ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getMapData = __webpack_require__(/*! ./_getMapData */ "./node_modules/lodash/_getMapData.js");

/**
 * Removes `key` and its value from the map.
 *
 * @private
 * @name delete
 * @memberOf MapCache
 * @param {string} key The key of the value to remove.
 * @returns {boolean} Returns `true` if the entry was removed, else `false`.
 */
function mapCacheDelete(key) {
  var result = getMapData(this, key)['delete'](key);
  this.size -= result ? 1 : 0;
  return result;
}

module.exports = mapCacheDelete;


/***/ }),

/***/ "./node_modules/lodash/_mapCacheGet.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_mapCacheGet.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getMapData = __webpack_require__(/*! ./_getMapData */ "./node_modules/lodash/_getMapData.js");

/**
 * Gets the map value for `key`.
 *
 * @private
 * @name get
 * @memberOf MapCache
 * @param {string} key The key of the value to get.
 * @returns {*} Returns the entry value.
 */
function mapCacheGet(key) {
  return getMapData(this, key).get(key);
}

module.exports = mapCacheGet;


/***/ }),

/***/ "./node_modules/lodash/_mapCacheHas.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_mapCacheHas.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getMapData = __webpack_require__(/*! ./_getMapData */ "./node_modules/lodash/_getMapData.js");

/**
 * Checks if a map value for `key` exists.
 *
 * @private
 * @name has
 * @memberOf MapCache
 * @param {string} key The key of the entry to check.
 * @returns {boolean} Returns `true` if an entry for `key` exists, else `false`.
 */
function mapCacheHas(key) {
  return getMapData(this, key).has(key);
}

module.exports = mapCacheHas;


/***/ }),

/***/ "./node_modules/lodash/_mapCacheSet.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_mapCacheSet.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getMapData = __webpack_require__(/*! ./_getMapData */ "./node_modules/lodash/_getMapData.js");

/**
 * Sets the map `key` to `value`.
 *
 * @private
 * @name set
 * @memberOf MapCache
 * @param {string} key The key of the value to set.
 * @param {*} value The value to set.
 * @returns {Object} Returns the map cache instance.
 */
function mapCacheSet(key, value) {
  var data = getMapData(this, key),
      size = data.size;

  data.set(key, value);
  this.size += data.size == size ? 0 : 1;
  return this;
}

module.exports = mapCacheSet;


/***/ }),

/***/ "./node_modules/lodash/_mapToArray.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_mapToArray.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Converts `map` to its key-value pairs.
 *
 * @private
 * @param {Object} map The map to convert.
 * @returns {Array} Returns the key-value pairs.
 */
function mapToArray(map) {
  var index = -1,
      result = Array(map.size);

  map.forEach(function(value, key) {
    result[++index] = [key, value];
  });
  return result;
}

module.exports = mapToArray;


/***/ }),

/***/ "./node_modules/lodash/_matchesStrictComparable.js":
/*!*********************************************************!*\
  !*** ./node_modules/lodash/_matchesStrictComparable.js ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * A specialized version of `matchesProperty` for source values suitable
 * for strict equality comparisons, i.e. `===`.
 *
 * @private
 * @param {string} key The key of the property to get.
 * @param {*} srcValue The value to match.
 * @returns {Function} Returns the new spec function.
 */
function matchesStrictComparable(key, srcValue) {
  return function(object) {
    if (object == null) {
      return false;
    }
    return object[key] === srcValue &&
      (srcValue !== undefined || (key in Object(object)));
  };
}

module.exports = matchesStrictComparable;


/***/ }),

/***/ "./node_modules/lodash/_memoizeCapped.js":
/*!***********************************************!*\
  !*** ./node_modules/lodash/_memoizeCapped.js ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var memoize = __webpack_require__(/*! ./memoize */ "./node_modules/lodash/memoize.js");

/** Used as the maximum memoize cache size. */
var MAX_MEMOIZE_SIZE = 500;

/**
 * A specialized version of `_.memoize` which clears the memoized function's
 * cache when it exceeds `MAX_MEMOIZE_SIZE`.
 *
 * @private
 * @param {Function} func The function to have its output memoized.
 * @returns {Function} Returns the new memoized function.
 */
function memoizeCapped(func) {
  var result = memoize(func, function(key) {
    if (cache.size === MAX_MEMOIZE_SIZE) {
      cache.clear();
    }
    return key;
  });

  var cache = result.cache;
  return result;
}

module.exports = memoizeCapped;


/***/ }),

/***/ "./node_modules/lodash/_mergeData.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_mergeData.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var composeArgs = __webpack_require__(/*! ./_composeArgs */ "./node_modules/lodash/_composeArgs.js"),
    composeArgsRight = __webpack_require__(/*! ./_composeArgsRight */ "./node_modules/lodash/_composeArgsRight.js"),
    replaceHolders = __webpack_require__(/*! ./_replaceHolders */ "./node_modules/lodash/_replaceHolders.js");

/** Used as the internal argument placeholder. */
var PLACEHOLDER = '__lodash_placeholder__';

/** Used to compose bitmasks for function metadata. */
var WRAP_BIND_FLAG = 1,
    WRAP_BIND_KEY_FLAG = 2,
    WRAP_CURRY_BOUND_FLAG = 4,
    WRAP_CURRY_FLAG = 8,
    WRAP_ARY_FLAG = 128,
    WRAP_REARG_FLAG = 256;

/* Built-in method references for those with the same name as other `lodash` methods. */
var nativeMin = Math.min;

/**
 * Merges the function metadata of `source` into `data`.
 *
 * Merging metadata reduces the number of wrappers used to invoke a function.
 * This is possible because methods like `_.bind`, `_.curry`, and `_.partial`
 * may be applied regardless of execution order. Methods like `_.ary` and
 * `_.rearg` modify function arguments, making the order in which they are
 * executed important, preventing the merging of metadata. However, we make
 * an exception for a safe combined case where curried functions have `_.ary`
 * and or `_.rearg` applied.
 *
 * @private
 * @param {Array} data The destination metadata.
 * @param {Array} source The source metadata.
 * @returns {Array} Returns `data`.
 */
function mergeData(data, source) {
  var bitmask = data[1],
      srcBitmask = source[1],
      newBitmask = bitmask | srcBitmask,
      isCommon = newBitmask < (WRAP_BIND_FLAG | WRAP_BIND_KEY_FLAG | WRAP_ARY_FLAG);

  var isCombo =
    ((srcBitmask == WRAP_ARY_FLAG) && (bitmask == WRAP_CURRY_FLAG)) ||
    ((srcBitmask == WRAP_ARY_FLAG) && (bitmask == WRAP_REARG_FLAG) && (data[7].length <= source[8])) ||
    ((srcBitmask == (WRAP_ARY_FLAG | WRAP_REARG_FLAG)) && (source[7].length <= source[8]) && (bitmask == WRAP_CURRY_FLAG));

  // Exit early if metadata can't be merged.
  if (!(isCommon || isCombo)) {
    return data;
  }
  // Use source `thisArg` if available.
  if (srcBitmask & WRAP_BIND_FLAG) {
    data[2] = source[2];
    // Set when currying a bound function.
    newBitmask |= bitmask & WRAP_BIND_FLAG ? 0 : WRAP_CURRY_BOUND_FLAG;
  }
  // Compose partial arguments.
  var value = source[3];
  if (value) {
    var partials = data[3];
    data[3] = partials ? composeArgs(partials, value, source[4]) : value;
    data[4] = partials ? replaceHolders(data[3], PLACEHOLDER) : source[4];
  }
  // Compose partial right arguments.
  value = source[5];
  if (value) {
    partials = data[5];
    data[5] = partials ? composeArgsRight(partials, value, source[6]) : value;
    data[6] = partials ? replaceHolders(data[5], PLACEHOLDER) : source[6];
  }
  // Use source `argPos` if available.
  value = source[7];
  if (value) {
    data[7] = value;
  }
  // Use source `ary` if it's smaller.
  if (srcBitmask & WRAP_ARY_FLAG) {
    data[8] = data[8] == null ? source[8] : nativeMin(data[8], source[8]);
  }
  // Use source `arity` if one is not provided.
  if (data[9] == null) {
    data[9] = source[9];
  }
  // Use source `func` and merge bitmasks.
  data[0] = source[0];
  data[1] = newBitmask;

  return data;
}

module.exports = mergeData;


/***/ }),

/***/ "./node_modules/lodash/_metaMap.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_metaMap.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var WeakMap = __webpack_require__(/*! ./_WeakMap */ "./node_modules/lodash/_WeakMap.js");

/** Used to store function metadata. */
var metaMap = WeakMap && new WeakMap;

module.exports = metaMap;


/***/ }),

/***/ "./node_modules/lodash/_nativeCreate.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_nativeCreate.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getNative = __webpack_require__(/*! ./_getNative */ "./node_modules/lodash/_getNative.js");

/* Built-in method references that are verified to be native. */
var nativeCreate = getNative(Object, 'create');

module.exports = nativeCreate;


/***/ }),

/***/ "./node_modules/lodash/_nativeKeys.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_nativeKeys.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var overArg = __webpack_require__(/*! ./_overArg */ "./node_modules/lodash/_overArg.js");

/* Built-in method references for those with the same name as other `lodash` methods. */
var nativeKeys = overArg(Object.keys, Object);

module.exports = nativeKeys;


/***/ }),

/***/ "./node_modules/lodash/_nodeUtil.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_nodeUtil.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

/* WEBPACK VAR INJECTION */(function(module) {var freeGlobal = __webpack_require__(/*! ./_freeGlobal */ "./node_modules/lodash/_freeGlobal.js");

/** Detect free variable `exports`. */
var freeExports =  true && exports && !exports.nodeType && exports;

/** Detect free variable `module`. */
var freeModule = freeExports && typeof module == 'object' && module && !module.nodeType && module;

/** Detect the popular CommonJS extension `module.exports`. */
var moduleExports = freeModule && freeModule.exports === freeExports;

/** Detect free variable `process` from Node.js. */
var freeProcess = moduleExports && freeGlobal.process;

/** Used to access faster Node.js helpers. */
var nodeUtil = (function() {
  try {
    // Use `util.types` for Node.js 10+.
    var types = freeModule && freeModule.require && freeModule.require('util').types;

    if (types) {
      return types;
    }

    // Legacy `process.binding('util')` for Node.js < 10.
    return freeProcess && freeProcess.binding && freeProcess.binding('util');
  } catch (e) {}
}());

module.exports = nodeUtil;

/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./../webpack/buildin/module.js */ "./node_modules/webpack/buildin/module.js")(module)))

/***/ }),

/***/ "./node_modules/lodash/_objectToString.js":
/*!************************************************!*\
  !*** ./node_modules/lodash/_objectToString.js ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/** Used for built-in method references. */
var objectProto = Object.prototype;

/**
 * Used to resolve the
 * [`toStringTag`](http://ecma-international.org/ecma-262/7.0/#sec-object.prototype.tostring)
 * of values.
 */
var nativeObjectToString = objectProto.toString;

/**
 * Converts `value` to a string using `Object.prototype.toString`.
 *
 * @private
 * @param {*} value The value to convert.
 * @returns {string} Returns the converted string.
 */
function objectToString(value) {
  return nativeObjectToString.call(value);
}

module.exports = objectToString;


/***/ }),

/***/ "./node_modules/lodash/_overArg.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_overArg.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Creates a unary function that invokes `func` with its argument transformed.
 *
 * @private
 * @param {Function} func The function to wrap.
 * @param {Function} transform The argument transform.
 * @returns {Function} Returns the new function.
 */
function overArg(func, transform) {
  return function(arg) {
    return func(transform(arg));
  };
}

module.exports = overArg;


/***/ }),

/***/ "./node_modules/lodash/_overRest.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_overRest.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var apply = __webpack_require__(/*! ./_apply */ "./node_modules/lodash/_apply.js");

/* Built-in method references for those with the same name as other `lodash` methods. */
var nativeMax = Math.max;

/**
 * A specialized version of `baseRest` which transforms the rest array.
 *
 * @private
 * @param {Function} func The function to apply a rest parameter to.
 * @param {number} [start=func.length-1] The start position of the rest parameter.
 * @param {Function} transform The rest array transform.
 * @returns {Function} Returns the new function.
 */
function overRest(func, start, transform) {
  start = nativeMax(start === undefined ? (func.length - 1) : start, 0);
  return function() {
    var args = arguments,
        index = -1,
        length = nativeMax(args.length - start, 0),
        array = Array(length);

    while (++index < length) {
      array[index] = args[start + index];
    }
    index = -1;
    var otherArgs = Array(start + 1);
    while (++index < start) {
      otherArgs[index] = args[index];
    }
    otherArgs[start] = transform(array);
    return apply(func, this, otherArgs);
  };
}

module.exports = overRest;


/***/ }),

/***/ "./node_modules/lodash/_realNames.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/_realNames.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/** Used to lookup unminified function names. */
var realNames = {};

module.exports = realNames;


/***/ }),

/***/ "./node_modules/lodash/_reorder.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_reorder.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var copyArray = __webpack_require__(/*! ./_copyArray */ "./node_modules/lodash/_copyArray.js"),
    isIndex = __webpack_require__(/*! ./_isIndex */ "./node_modules/lodash/_isIndex.js");

/* Built-in method references for those with the same name as other `lodash` methods. */
var nativeMin = Math.min;

/**
 * Reorder `array` according to the specified indexes where the element at
 * the first index is assigned as the first element, the element at
 * the second index is assigned as the second element, and so on.
 *
 * @private
 * @param {Array} array The array to reorder.
 * @param {Array} indexes The arranged array indexes.
 * @returns {Array} Returns `array`.
 */
function reorder(array, indexes) {
  var arrLength = array.length,
      length = nativeMin(indexes.length, arrLength),
      oldArray = copyArray(array);

  while (length--) {
    var index = indexes[length];
    array[length] = isIndex(index, arrLength) ? oldArray[index] : undefined;
  }
  return array;
}

module.exports = reorder;


/***/ }),

/***/ "./node_modules/lodash/_replaceHolders.js":
/*!************************************************!*\
  !*** ./node_modules/lodash/_replaceHolders.js ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/** Used as the internal argument placeholder. */
var PLACEHOLDER = '__lodash_placeholder__';

/**
 * Replaces all `placeholder` elements in `array` with an internal placeholder
 * and returns an array of their indexes.
 *
 * @private
 * @param {Array} array The array to modify.
 * @param {*} placeholder The placeholder to replace.
 * @returns {Array} Returns the new array of placeholder indexes.
 */
function replaceHolders(array, placeholder) {
  var index = -1,
      length = array.length,
      resIndex = 0,
      result = [];

  while (++index < length) {
    var value = array[index];
    if (value === placeholder || value === PLACEHOLDER) {
      array[index] = PLACEHOLDER;
      result[resIndex++] = index;
    }
  }
  return result;
}

module.exports = replaceHolders;


/***/ }),

/***/ "./node_modules/lodash/_root.js":
/*!**************************************!*\
  !*** ./node_modules/lodash/_root.js ***!
  \**************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var freeGlobal = __webpack_require__(/*! ./_freeGlobal */ "./node_modules/lodash/_freeGlobal.js");

/** Detect free variable `self`. */
var freeSelf = typeof self == 'object' && self && self.Object === Object && self;

/** Used as a reference to the global object. */
var root = freeGlobal || freeSelf || Function('return this')();

module.exports = root;


/***/ }),

/***/ "./node_modules/lodash/_setCacheAdd.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_setCacheAdd.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/** Used to stand-in for `undefined` hash values. */
var HASH_UNDEFINED = '__lodash_hash_undefined__';

/**
 * Adds `value` to the array cache.
 *
 * @private
 * @name add
 * @memberOf SetCache
 * @alias push
 * @param {*} value The value to cache.
 * @returns {Object} Returns the cache instance.
 */
function setCacheAdd(value) {
  this.__data__.set(value, HASH_UNDEFINED);
  return this;
}

module.exports = setCacheAdd;


/***/ }),

/***/ "./node_modules/lodash/_setCacheHas.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_setCacheHas.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Checks if `value` is in the array cache.
 *
 * @private
 * @name has
 * @memberOf SetCache
 * @param {*} value The value to search for.
 * @returns {number} Returns `true` if `value` is found, else `false`.
 */
function setCacheHas(value) {
  return this.__data__.has(value);
}

module.exports = setCacheHas;


/***/ }),

/***/ "./node_modules/lodash/_setData.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/_setData.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseSetData = __webpack_require__(/*! ./_baseSetData */ "./node_modules/lodash/_baseSetData.js"),
    shortOut = __webpack_require__(/*! ./_shortOut */ "./node_modules/lodash/_shortOut.js");

/**
 * Sets metadata for `func`.
 *
 * **Note:** If this function becomes hot, i.e. is invoked a lot in a short
 * period of time, it will trip its breaker and transition to an identity
 * function to avoid garbage collection pauses in V8. See
 * [V8 issue 2070](https://bugs.chromium.org/p/v8/issues/detail?id=2070)
 * for more details.
 *
 * @private
 * @param {Function} func The function to associate metadata with.
 * @param {*} data The metadata.
 * @returns {Function} Returns `func`.
 */
var setData = shortOut(baseSetData);

module.exports = setData;


/***/ }),

/***/ "./node_modules/lodash/_setToArray.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_setToArray.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Converts `set` to an array of its values.
 *
 * @private
 * @param {Object} set The set to convert.
 * @returns {Array} Returns the values.
 */
function setToArray(set) {
  var index = -1,
      result = Array(set.size);

  set.forEach(function(value) {
    result[++index] = value;
  });
  return result;
}

module.exports = setToArray;


/***/ }),

/***/ "./node_modules/lodash/_setToString.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_setToString.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseSetToString = __webpack_require__(/*! ./_baseSetToString */ "./node_modules/lodash/_baseSetToString.js"),
    shortOut = __webpack_require__(/*! ./_shortOut */ "./node_modules/lodash/_shortOut.js");

/**
 * Sets the `toString` method of `func` to return `string`.
 *
 * @private
 * @param {Function} func The function to modify.
 * @param {Function} string The `toString` result.
 * @returns {Function} Returns `func`.
 */
var setToString = shortOut(baseSetToString);

module.exports = setToString;


/***/ }),

/***/ "./node_modules/lodash/_setWrapToString.js":
/*!*************************************************!*\
  !*** ./node_modules/lodash/_setWrapToString.js ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var getWrapDetails = __webpack_require__(/*! ./_getWrapDetails */ "./node_modules/lodash/_getWrapDetails.js"),
    insertWrapDetails = __webpack_require__(/*! ./_insertWrapDetails */ "./node_modules/lodash/_insertWrapDetails.js"),
    setToString = __webpack_require__(/*! ./_setToString */ "./node_modules/lodash/_setToString.js"),
    updateWrapDetails = __webpack_require__(/*! ./_updateWrapDetails */ "./node_modules/lodash/_updateWrapDetails.js");

/**
 * Sets the `toString` method of `wrapper` to mimic the source of `reference`
 * with wrapper details in a comment at the top of the source body.
 *
 * @private
 * @param {Function} wrapper The function to modify.
 * @param {Function} reference The reference function.
 * @param {number} bitmask The bitmask flags. See `createWrap` for more details.
 * @returns {Function} Returns `wrapper`.
 */
function setWrapToString(wrapper, reference, bitmask) {
  var source = (reference + '');
  return setToString(wrapper, insertWrapDetails(source, updateWrapDetails(getWrapDetails(source), bitmask)));
}

module.exports = setWrapToString;


/***/ }),

/***/ "./node_modules/lodash/_shortOut.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_shortOut.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/** Used to detect hot functions by number of calls within a span of milliseconds. */
var HOT_COUNT = 800,
    HOT_SPAN = 16;

/* Built-in method references for those with the same name as other `lodash` methods. */
var nativeNow = Date.now;

/**
 * Creates a function that'll short out and invoke `identity` instead
 * of `func` when it's called `HOT_COUNT` or more times in `HOT_SPAN`
 * milliseconds.
 *
 * @private
 * @param {Function} func The function to restrict.
 * @returns {Function} Returns the new shortable function.
 */
function shortOut(func) {
  var count = 0,
      lastCalled = 0;

  return function() {
    var stamp = nativeNow(),
        remaining = HOT_SPAN - (stamp - lastCalled);

    lastCalled = stamp;
    if (remaining > 0) {
      if (++count >= HOT_COUNT) {
        return arguments[0];
      }
    } else {
      count = 0;
    }
    return func.apply(undefined, arguments);
  };
}

module.exports = shortOut;


/***/ }),

/***/ "./node_modules/lodash/_stackClear.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/_stackClear.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var ListCache = __webpack_require__(/*! ./_ListCache */ "./node_modules/lodash/_ListCache.js");

/**
 * Removes all key-value entries from the stack.
 *
 * @private
 * @name clear
 * @memberOf Stack
 */
function stackClear() {
  this.__data__ = new ListCache;
  this.size = 0;
}

module.exports = stackClear;


/***/ }),

/***/ "./node_modules/lodash/_stackDelete.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/_stackDelete.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Removes `key` and its value from the stack.
 *
 * @private
 * @name delete
 * @memberOf Stack
 * @param {string} key The key of the value to remove.
 * @returns {boolean} Returns `true` if the entry was removed, else `false`.
 */
function stackDelete(key) {
  var data = this.__data__,
      result = data['delete'](key);

  this.size = data.size;
  return result;
}

module.exports = stackDelete;


/***/ }),

/***/ "./node_modules/lodash/_stackGet.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_stackGet.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Gets the stack value for `key`.
 *
 * @private
 * @name get
 * @memberOf Stack
 * @param {string} key The key of the value to get.
 * @returns {*} Returns the entry value.
 */
function stackGet(key) {
  return this.__data__.get(key);
}

module.exports = stackGet;


/***/ }),

/***/ "./node_modules/lodash/_stackHas.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_stackHas.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Checks if a stack value for `key` exists.
 *
 * @private
 * @name has
 * @memberOf Stack
 * @param {string} key The key of the entry to check.
 * @returns {boolean} Returns `true` if an entry for `key` exists, else `false`.
 */
function stackHas(key) {
  return this.__data__.has(key);
}

module.exports = stackHas;


/***/ }),

/***/ "./node_modules/lodash/_stackSet.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_stackSet.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var ListCache = __webpack_require__(/*! ./_ListCache */ "./node_modules/lodash/_ListCache.js"),
    Map = __webpack_require__(/*! ./_Map */ "./node_modules/lodash/_Map.js"),
    MapCache = __webpack_require__(/*! ./_MapCache */ "./node_modules/lodash/_MapCache.js");

/** Used as the size to enable large array optimizations. */
var LARGE_ARRAY_SIZE = 200;

/**
 * Sets the stack `key` to `value`.
 *
 * @private
 * @name set
 * @memberOf Stack
 * @param {string} key The key of the value to set.
 * @param {*} value The value to set.
 * @returns {Object} Returns the stack cache instance.
 */
function stackSet(key, value) {
  var data = this.__data__;
  if (data instanceof ListCache) {
    var pairs = data.__data__;
    if (!Map || (pairs.length < LARGE_ARRAY_SIZE - 1)) {
      pairs.push([key, value]);
      this.size = ++data.size;
      return this;
    }
    data = this.__data__ = new MapCache(pairs);
  }
  data.set(key, value);
  this.size = data.size;
  return this;
}

module.exports = stackSet;


/***/ }),

/***/ "./node_modules/lodash/_strictIndexOf.js":
/*!***********************************************!*\
  !*** ./node_modules/lodash/_strictIndexOf.js ***!
  \***********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * A specialized version of `_.indexOf` which performs strict equality
 * comparisons of values, i.e. `===`.
 *
 * @private
 * @param {Array} array The array to inspect.
 * @param {*} value The value to search for.
 * @param {number} fromIndex The index to search from.
 * @returns {number} Returns the index of the matched value, else `-1`.
 */
function strictIndexOf(array, value, fromIndex) {
  var index = fromIndex - 1,
      length = array.length;

  while (++index < length) {
    if (array[index] === value) {
      return index;
    }
  }
  return -1;
}

module.exports = strictIndexOf;


/***/ }),

/***/ "./node_modules/lodash/_stringToPath.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_stringToPath.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var memoizeCapped = __webpack_require__(/*! ./_memoizeCapped */ "./node_modules/lodash/_memoizeCapped.js");

/** Used to match property names within property paths. */
var rePropName = /[^.[\]]+|\[(?:(-?\d+(?:\.\d+)?)|(["'])((?:(?!\2)[^\\]|\\.)*?)\2)\]|(?=(?:\.|\[\])(?:\.|\[\]|$))/g;

/** Used to match backslashes in property paths. */
var reEscapeChar = /\\(\\)?/g;

/**
 * Converts `string` to a property path array.
 *
 * @private
 * @param {string} string The string to convert.
 * @returns {Array} Returns the property path array.
 */
var stringToPath = memoizeCapped(function(string) {
  var result = [];
  if (string.charCodeAt(0) === 46 /* . */) {
    result.push('');
  }
  string.replace(rePropName, function(match, number, quote, subString) {
    result.push(quote ? subString.replace(reEscapeChar, '$1') : (number || match));
  });
  return result;
});

module.exports = stringToPath;


/***/ }),

/***/ "./node_modules/lodash/_toKey.js":
/*!***************************************!*\
  !*** ./node_modules/lodash/_toKey.js ***!
  \***************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var isSymbol = __webpack_require__(/*! ./isSymbol */ "./node_modules/lodash/isSymbol.js");

/** Used as references for various `Number` constants. */
var INFINITY = 1 / 0;

/**
 * Converts `value` to a string key if it's not a string or symbol.
 *
 * @private
 * @param {*} value The value to inspect.
 * @returns {string|symbol} Returns the key.
 */
function toKey(value) {
  if (typeof value == 'string' || isSymbol(value)) {
    return value;
  }
  var result = (value + '');
  return (result == '0' && (1 / value) == -INFINITY) ? '-0' : result;
}

module.exports = toKey;


/***/ }),

/***/ "./node_modules/lodash/_toSource.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/_toSource.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/** Used for built-in method references. */
var funcProto = Function.prototype;

/** Used to resolve the decompiled source of functions. */
var funcToString = funcProto.toString;

/**
 * Converts `func` to its source code.
 *
 * @private
 * @param {Function} func The function to convert.
 * @returns {string} Returns the source code.
 */
function toSource(func) {
  if (func != null) {
    try {
      return funcToString.call(func);
    } catch (e) {}
    try {
      return (func + '');
    } catch (e) {}
  }
  return '';
}

module.exports = toSource;


/***/ }),

/***/ "./node_modules/lodash/_trimmedEndIndex.js":
/*!*************************************************!*\
  !*** ./node_modules/lodash/_trimmedEndIndex.js ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/** Used to match a single whitespace character. */
var reWhitespace = /\s/;

/**
 * Used by `_.trim` and `_.trimEnd` to get the index of the last non-whitespace
 * character of `string`.
 *
 * @private
 * @param {string} string The string to inspect.
 * @returns {number} Returns the index of the last non-whitespace character.
 */
function trimmedEndIndex(string) {
  var index = string.length;

  while (index-- && reWhitespace.test(string.charAt(index))) {}
  return index;
}

module.exports = trimmedEndIndex;


/***/ }),

/***/ "./node_modules/lodash/_updateWrapDetails.js":
/*!***************************************************!*\
  !*** ./node_modules/lodash/_updateWrapDetails.js ***!
  \***************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var arrayEach = __webpack_require__(/*! ./_arrayEach */ "./node_modules/lodash/_arrayEach.js"),
    arrayIncludes = __webpack_require__(/*! ./_arrayIncludes */ "./node_modules/lodash/_arrayIncludes.js");

/** Used to compose bitmasks for function metadata. */
var WRAP_BIND_FLAG = 1,
    WRAP_BIND_KEY_FLAG = 2,
    WRAP_CURRY_FLAG = 8,
    WRAP_CURRY_RIGHT_FLAG = 16,
    WRAP_PARTIAL_FLAG = 32,
    WRAP_PARTIAL_RIGHT_FLAG = 64,
    WRAP_ARY_FLAG = 128,
    WRAP_REARG_FLAG = 256,
    WRAP_FLIP_FLAG = 512;

/** Used to associate wrap methods with their bit flags. */
var wrapFlags = [
  ['ary', WRAP_ARY_FLAG],
  ['bind', WRAP_BIND_FLAG],
  ['bindKey', WRAP_BIND_KEY_FLAG],
  ['curry', WRAP_CURRY_FLAG],
  ['curryRight', WRAP_CURRY_RIGHT_FLAG],
  ['flip', WRAP_FLIP_FLAG],
  ['partial', WRAP_PARTIAL_FLAG],
  ['partialRight', WRAP_PARTIAL_RIGHT_FLAG],
  ['rearg', WRAP_REARG_FLAG]
];

/**
 * Updates wrapper `details` based on `bitmask` flags.
 *
 * @private
 * @returns {Array} details The details to modify.
 * @param {number} bitmask The bitmask flags. See `createWrap` for more details.
 * @returns {Array} Returns `details`.
 */
function updateWrapDetails(details, bitmask) {
  arrayEach(wrapFlags, function(pair) {
    var value = '_.' + pair[0];
    if ((bitmask & pair[1]) && !arrayIncludes(details, value)) {
      details.push(value);
    }
  });
  return details.sort();
}

module.exports = updateWrapDetails;


/***/ }),

/***/ "./node_modules/lodash/_wrapperClone.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/_wrapperClone.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var LazyWrapper = __webpack_require__(/*! ./_LazyWrapper */ "./node_modules/lodash/_LazyWrapper.js"),
    LodashWrapper = __webpack_require__(/*! ./_LodashWrapper */ "./node_modules/lodash/_LodashWrapper.js"),
    copyArray = __webpack_require__(/*! ./_copyArray */ "./node_modules/lodash/_copyArray.js");

/**
 * Creates a clone of `wrapper`.
 *
 * @private
 * @param {Object} wrapper The wrapper to clone.
 * @returns {Object} Returns the cloned wrapper.
 */
function wrapperClone(wrapper) {
  if (wrapper instanceof LazyWrapper) {
    return wrapper.clone();
  }
  var result = new LodashWrapper(wrapper.__wrapped__, wrapper.__chain__);
  result.__actions__ = copyArray(wrapper.__actions__);
  result.__index__  = wrapper.__index__;
  result.__values__ = wrapper.__values__;
  return result;
}

module.exports = wrapperClone;


/***/ }),

/***/ "./node_modules/lodash/assign.js":
/*!***************************************!*\
  !*** ./node_modules/lodash/assign.js ***!
  \***************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var assignValue = __webpack_require__(/*! ./_assignValue */ "./node_modules/lodash/_assignValue.js"),
    copyObject = __webpack_require__(/*! ./_copyObject */ "./node_modules/lodash/_copyObject.js"),
    createAssigner = __webpack_require__(/*! ./_createAssigner */ "./node_modules/lodash/_createAssigner.js"),
    isArrayLike = __webpack_require__(/*! ./isArrayLike */ "./node_modules/lodash/isArrayLike.js"),
    isPrototype = __webpack_require__(/*! ./_isPrototype */ "./node_modules/lodash/_isPrototype.js"),
    keys = __webpack_require__(/*! ./keys */ "./node_modules/lodash/keys.js");

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/**
 * Assigns own enumerable string keyed properties of source objects to the
 * destination object. Source objects are applied from left to right.
 * Subsequent sources overwrite property assignments of previous sources.
 *
 * **Note:** This method mutates `object` and is loosely based on
 * [`Object.assign`](https://mdn.io/Object/assign).
 *
 * @static
 * @memberOf _
 * @since 0.10.0
 * @category Object
 * @param {Object} object The destination object.
 * @param {...Object} [sources] The source objects.
 * @returns {Object} Returns `object`.
 * @see _.assignIn
 * @example
 *
 * function Foo() {
 *   this.a = 1;
 * }
 *
 * function Bar() {
 *   this.c = 3;
 * }
 *
 * Foo.prototype.b = 2;
 * Bar.prototype.d = 4;
 *
 * _.assign({ 'a': 0 }, new Foo, new Bar);
 * // => { 'a': 1, 'c': 3 }
 */
var assign = createAssigner(function(object, source) {
  if (isPrototype(source) || isArrayLike(source)) {
    copyObject(source, keys(source), object);
    return;
  }
  for (var key in source) {
    if (hasOwnProperty.call(source, key)) {
      assignValue(object, key, source[key]);
    }
  }
});

module.exports = assign;


/***/ }),

/***/ "./node_modules/lodash/bind.js":
/*!*************************************!*\
  !*** ./node_modules/lodash/bind.js ***!
  \*************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseRest = __webpack_require__(/*! ./_baseRest */ "./node_modules/lodash/_baseRest.js"),
    createWrap = __webpack_require__(/*! ./_createWrap */ "./node_modules/lodash/_createWrap.js"),
    getHolder = __webpack_require__(/*! ./_getHolder */ "./node_modules/lodash/_getHolder.js"),
    replaceHolders = __webpack_require__(/*! ./_replaceHolders */ "./node_modules/lodash/_replaceHolders.js");

/** Used to compose bitmasks for function metadata. */
var WRAP_BIND_FLAG = 1,
    WRAP_PARTIAL_FLAG = 32;

/**
 * Creates a function that invokes `func` with the `this` binding of `thisArg`
 * and `partials` prepended to the arguments it receives.
 *
 * The `_.bind.placeholder` value, which defaults to `_` in monolithic builds,
 * may be used as a placeholder for partially applied arguments.
 *
 * **Note:** Unlike native `Function#bind`, this method doesn't set the "length"
 * property of bound functions.
 *
 * @static
 * @memberOf _
 * @since 0.1.0
 * @category Function
 * @param {Function} func The function to bind.
 * @param {*} thisArg The `this` binding of `func`.
 * @param {...*} [partials] The arguments to be partially applied.
 * @returns {Function} Returns the new bound function.
 * @example
 *
 * function greet(greeting, punctuation) {
 *   return greeting + ' ' + this.user + punctuation;
 * }
 *
 * var object = { 'user': 'fred' };
 *
 * var bound = _.bind(greet, object, 'hi');
 * bound('!');
 * // => 'hi fred!'
 *
 * // Bound with placeholders.
 * var bound = _.bind(greet, object, _, '!');
 * bound('hi');
 * // => 'hi fred!'
 */
var bind = baseRest(function(func, thisArg, partials) {
  var bitmask = WRAP_BIND_FLAG;
  if (partials.length) {
    var holders = replaceHolders(partials, getHolder(bind));
    bitmask |= WRAP_PARTIAL_FLAG;
  }
  return createWrap(func, bitmask, thisArg, partials, holders);
});

// Assign default placeholders.
bind.placeholder = {};

module.exports = bind;


/***/ }),

/***/ "./node_modules/lodash/constant.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/constant.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Creates a function that returns `value`.
 *
 * @static
 * @memberOf _
 * @since 2.4.0
 * @category Util
 * @param {*} value The value to return from the new function.
 * @returns {Function} Returns the new constant function.
 * @example
 *
 * var objects = _.times(2, _.constant({ 'a': 1 }));
 *
 * console.log(objects);
 * // => [{ 'a': 1 }, { 'a': 1 }]
 *
 * console.log(objects[0] === objects[1]);
 * // => true
 */
function constant(value) {
  return function() {
    return value;
  };
}

module.exports = constant;


/***/ }),

/***/ "./node_modules/lodash/eq.js":
/*!***********************************!*\
  !*** ./node_modules/lodash/eq.js ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Performs a
 * [`SameValueZero`](http://ecma-international.org/ecma-262/7.0/#sec-samevaluezero)
 * comparison between two values to determine if they are equivalent.
 *
 * @static
 * @memberOf _
 * @since 4.0.0
 * @category Lang
 * @param {*} value The value to compare.
 * @param {*} other The other value to compare.
 * @returns {boolean} Returns `true` if the values are equivalent, else `false`.
 * @example
 *
 * var object = { 'a': 1 };
 * var other = { 'a': 1 };
 *
 * _.eq(object, object);
 * // => true
 *
 * _.eq(object, other);
 * // => false
 *
 * _.eq('a', 'a');
 * // => true
 *
 * _.eq('a', Object('a'));
 * // => false
 *
 * _.eq(NaN, NaN);
 * // => true
 */
function eq(value, other) {
  return value === other || (value !== value && other !== other);
}

module.exports = eq;


/***/ }),

/***/ "./node_modules/lodash/filter.js":
/*!***************************************!*\
  !*** ./node_modules/lodash/filter.js ***!
  \***************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var arrayFilter = __webpack_require__(/*! ./_arrayFilter */ "./node_modules/lodash/_arrayFilter.js"),
    baseFilter = __webpack_require__(/*! ./_baseFilter */ "./node_modules/lodash/_baseFilter.js"),
    baseIteratee = __webpack_require__(/*! ./_baseIteratee */ "./node_modules/lodash/_baseIteratee.js"),
    isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js");

/**
 * Iterates over elements of `collection`, returning an array of all elements
 * `predicate` returns truthy for. The predicate is invoked with three
 * arguments: (value, index|key, collection).
 *
 * **Note:** Unlike `_.remove`, this method returns a new array.
 *
 * @static
 * @memberOf _
 * @since 0.1.0
 * @category Collection
 * @param {Array|Object} collection The collection to iterate over.
 * @param {Function} [predicate=_.identity] The function invoked per iteration.
 * @returns {Array} Returns the new filtered array.
 * @see _.reject
 * @example
 *
 * var users = [
 *   { 'user': 'barney', 'age': 36, 'active': true },
 *   { 'user': 'fred',   'age': 40, 'active': false }
 * ];
 *
 * _.filter(users, function(o) { return !o.active; });
 * // => objects for ['fred']
 *
 * // The `_.matches` iteratee shorthand.
 * _.filter(users, { 'age': 36, 'active': true });
 * // => objects for ['barney']
 *
 * // The `_.matchesProperty` iteratee shorthand.
 * _.filter(users, ['active', false]);
 * // => objects for ['fred']
 *
 * // The `_.property` iteratee shorthand.
 * _.filter(users, 'active');
 * // => objects for ['barney']
 *
 * // Combining several predicates using `_.overEvery` or `_.overSome`.
 * _.filter(users, _.overSome([{ 'age': 36 }, ['age', 40]]));
 * // => objects for ['fred', 'barney']
 */
function filter(collection, predicate) {
  var func = isArray(collection) ? arrayFilter : baseFilter;
  return func(collection, baseIteratee(predicate, 3));
}

module.exports = filter;


/***/ }),

/***/ "./node_modules/lodash/find.js":
/*!*************************************!*\
  !*** ./node_modules/lodash/find.js ***!
  \*************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var createFind = __webpack_require__(/*! ./_createFind */ "./node_modules/lodash/_createFind.js"),
    findIndex = __webpack_require__(/*! ./findIndex */ "./node_modules/lodash/findIndex.js");

/**
 * Iterates over elements of `collection`, returning the first element
 * `predicate` returns truthy for. The predicate is invoked with three
 * arguments: (value, index|key, collection).
 *
 * @static
 * @memberOf _
 * @since 0.1.0
 * @category Collection
 * @param {Array|Object} collection The collection to inspect.
 * @param {Function} [predicate=_.identity] The function invoked per iteration.
 * @param {number} [fromIndex=0] The index to search from.
 * @returns {*} Returns the matched element, else `undefined`.
 * @example
 *
 * var users = [
 *   { 'user': 'barney',  'age': 36, 'active': true },
 *   { 'user': 'fred',    'age': 40, 'active': false },
 *   { 'user': 'pebbles', 'age': 1,  'active': true }
 * ];
 *
 * _.find(users, function(o) { return o.age < 40; });
 * // => object for 'barney'
 *
 * // The `_.matches` iteratee shorthand.
 * _.find(users, { 'age': 1, 'active': true });
 * // => object for 'pebbles'
 *
 * // The `_.matchesProperty` iteratee shorthand.
 * _.find(users, ['active', false]);
 * // => object for 'fred'
 *
 * // The `_.property` iteratee shorthand.
 * _.find(users, 'active');
 * // => object for 'barney'
 */
var find = createFind(findIndex);

module.exports = find;


/***/ }),

/***/ "./node_modules/lodash/findIndex.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/findIndex.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseFindIndex = __webpack_require__(/*! ./_baseFindIndex */ "./node_modules/lodash/_baseFindIndex.js"),
    baseIteratee = __webpack_require__(/*! ./_baseIteratee */ "./node_modules/lodash/_baseIteratee.js"),
    toInteger = __webpack_require__(/*! ./toInteger */ "./node_modules/lodash/toInteger.js");

/* Built-in method references for those with the same name as other `lodash` methods. */
var nativeMax = Math.max;

/**
 * This method is like `_.find` except that it returns the index of the first
 * element `predicate` returns truthy for instead of the element itself.
 *
 * @static
 * @memberOf _
 * @since 1.1.0
 * @category Array
 * @param {Array} array The array to inspect.
 * @param {Function} [predicate=_.identity] The function invoked per iteration.
 * @param {number} [fromIndex=0] The index to search from.
 * @returns {number} Returns the index of the found element, else `-1`.
 * @example
 *
 * var users = [
 *   { 'user': 'barney',  'active': false },
 *   { 'user': 'fred',    'active': false },
 *   { 'user': 'pebbles', 'active': true }
 * ];
 *
 * _.findIndex(users, function(o) { return o.user == 'barney'; });
 * // => 0
 *
 * // The `_.matches` iteratee shorthand.
 * _.findIndex(users, { 'user': 'fred', 'active': false });
 * // => 1
 *
 * // The `_.matchesProperty` iteratee shorthand.
 * _.findIndex(users, ['active', false]);
 * // => 0
 *
 * // The `_.property` iteratee shorthand.
 * _.findIndex(users, 'active');
 * // => 2
 */
function findIndex(array, predicate, fromIndex) {
  var length = array == null ? 0 : array.length;
  if (!length) {
    return -1;
  }
  var index = fromIndex == null ? 0 : toInteger(fromIndex);
  if (index < 0) {
    index = nativeMax(length + index, 0);
  }
  return baseFindIndex(array, baseIteratee(predicate, 3), index);
}

module.exports = findIndex;


/***/ }),

/***/ "./node_modules/lodash/flatten.js":
/*!****************************************!*\
  !*** ./node_modules/lodash/flatten.js ***!
  \****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseFlatten = __webpack_require__(/*! ./_baseFlatten */ "./node_modules/lodash/_baseFlatten.js");

/**
 * Flattens `array` a single level deep.
 *
 * @static
 * @memberOf _
 * @since 0.1.0
 * @category Array
 * @param {Array} array The array to flatten.
 * @returns {Array} Returns the new flattened array.
 * @example
 *
 * _.flatten([1, [2, [3, [4]], 5]]);
 * // => [1, 2, [3, [4]], 5]
 */
function flatten(array) {
  var length = array == null ? 0 : array.length;
  return length ? baseFlatten(array, 1) : [];
}

module.exports = flatten;


/***/ }),

/***/ "./node_modules/lodash/flattenDeep.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/flattenDeep.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseFlatten = __webpack_require__(/*! ./_baseFlatten */ "./node_modules/lodash/_baseFlatten.js");

/** Used as references for various `Number` constants. */
var INFINITY = 1 / 0;

/**
 * Recursively flattens `array`.
 *
 * @static
 * @memberOf _
 * @since 3.0.0
 * @category Array
 * @param {Array} array The array to flatten.
 * @returns {Array} Returns the new flattened array.
 * @example
 *
 * _.flattenDeep([1, [2, [3, [4]], 5]]);
 * // => [1, 2, 3, 4, 5]
 */
function flattenDeep(array) {
  var length = array == null ? 0 : array.length;
  return length ? baseFlatten(array, INFINITY) : [];
}

module.exports = flattenDeep;


/***/ }),

/***/ "./node_modules/lodash/forEach.js":
/*!****************************************!*\
  !*** ./node_modules/lodash/forEach.js ***!
  \****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var arrayEach = __webpack_require__(/*! ./_arrayEach */ "./node_modules/lodash/_arrayEach.js"),
    baseEach = __webpack_require__(/*! ./_baseEach */ "./node_modules/lodash/_baseEach.js"),
    castFunction = __webpack_require__(/*! ./_castFunction */ "./node_modules/lodash/_castFunction.js"),
    isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js");

/**
 * Iterates over elements of `collection` and invokes `iteratee` for each element.
 * The iteratee is invoked with three arguments: (value, index|key, collection).
 * Iteratee functions may exit iteration early by explicitly returning `false`.
 *
 * **Note:** As with other "Collections" methods, objects with a "length"
 * property are iterated like arrays. To avoid this behavior use `_.forIn`
 * or `_.forOwn` for object iteration.
 *
 * @static
 * @memberOf _
 * @since 0.1.0
 * @alias each
 * @category Collection
 * @param {Array|Object} collection The collection to iterate over.
 * @param {Function} [iteratee=_.identity] The function invoked per iteration.
 * @returns {Array|Object} Returns `collection`.
 * @see _.forEachRight
 * @example
 *
 * _.forEach([1, 2], function(value) {
 *   console.log(value);
 * });
 * // => Logs `1` then `2`.
 *
 * _.forEach({ 'a': 1, 'b': 2 }, function(value, key) {
 *   console.log(key);
 * });
 * // => Logs 'a' then 'b' (iteration order is not guaranteed).
 */
function forEach(collection, iteratee) {
  var func = isArray(collection) ? arrayEach : baseEach;
  return func(collection, castFunction(iteratee));
}

module.exports = forEach;


/***/ }),

/***/ "./node_modules/lodash/get.js":
/*!************************************!*\
  !*** ./node_modules/lodash/get.js ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseGet = __webpack_require__(/*! ./_baseGet */ "./node_modules/lodash/_baseGet.js");

/**
 * Gets the value at `path` of `object`. If the resolved value is
 * `undefined`, the `defaultValue` is returned in its place.
 *
 * @static
 * @memberOf _
 * @since 3.7.0
 * @category Object
 * @param {Object} object The object to query.
 * @param {Array|string} path The path of the property to get.
 * @param {*} [defaultValue] The value returned for `undefined` resolved values.
 * @returns {*} Returns the resolved value.
 * @example
 *
 * var object = { 'a': [{ 'b': { 'c': 3 } }] };
 *
 * _.get(object, 'a[0].b.c');
 * // => 3
 *
 * _.get(object, ['a', '0', 'b', 'c']);
 * // => 3
 *
 * _.get(object, 'a.b.c', 'default');
 * // => 'default'
 */
function get(object, path, defaultValue) {
  var result = object == null ? undefined : baseGet(object, path);
  return result === undefined ? defaultValue : result;
}

module.exports = get;


/***/ }),

/***/ "./node_modules/lodash/hasIn.js":
/*!**************************************!*\
  !*** ./node_modules/lodash/hasIn.js ***!
  \**************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseHasIn = __webpack_require__(/*! ./_baseHasIn */ "./node_modules/lodash/_baseHasIn.js"),
    hasPath = __webpack_require__(/*! ./_hasPath */ "./node_modules/lodash/_hasPath.js");

/**
 * Checks if `path` is a direct or inherited property of `object`.
 *
 * @static
 * @memberOf _
 * @since 4.0.0
 * @category Object
 * @param {Object} object The object to query.
 * @param {Array|string} path The path to check.
 * @returns {boolean} Returns `true` if `path` exists, else `false`.
 * @example
 *
 * var object = _.create({ 'a': _.create({ 'b': 2 }) });
 *
 * _.hasIn(object, 'a');
 * // => true
 *
 * _.hasIn(object, 'a.b');
 * // => true
 *
 * _.hasIn(object, ['a', 'b']);
 * // => true
 *
 * _.hasIn(object, 'b');
 * // => false
 */
function hasIn(object, path) {
  return object != null && hasPath(object, path, baseHasIn);
}

module.exports = hasIn;


/***/ }),

/***/ "./node_modules/lodash/identity.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/identity.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * This method returns the first argument it receives.
 *
 * @static
 * @since 0.1.0
 * @memberOf _
 * @category Util
 * @param {*} value Any value.
 * @returns {*} Returns `value`.
 * @example
 *
 * var object = { 'a': 1 };
 *
 * console.log(_.identity(object) === object);
 * // => true
 */
function identity(value) {
  return value;
}

module.exports = identity;


/***/ }),

/***/ "./node_modules/lodash/isArguments.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/isArguments.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseIsArguments = __webpack_require__(/*! ./_baseIsArguments */ "./node_modules/lodash/_baseIsArguments.js"),
    isObjectLike = __webpack_require__(/*! ./isObjectLike */ "./node_modules/lodash/isObjectLike.js");

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/** Built-in value references. */
var propertyIsEnumerable = objectProto.propertyIsEnumerable;

/**
 * Checks if `value` is likely an `arguments` object.
 *
 * @static
 * @memberOf _
 * @since 0.1.0
 * @category Lang
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is an `arguments` object,
 *  else `false`.
 * @example
 *
 * _.isArguments(function() { return arguments; }());
 * // => true
 *
 * _.isArguments([1, 2, 3]);
 * // => false
 */
var isArguments = baseIsArguments(function() { return arguments; }()) ? baseIsArguments : function(value) {
  return isObjectLike(value) && hasOwnProperty.call(value, 'callee') &&
    !propertyIsEnumerable.call(value, 'callee');
};

module.exports = isArguments;


/***/ }),

/***/ "./node_modules/lodash/isArray.js":
/*!****************************************!*\
  !*** ./node_modules/lodash/isArray.js ***!
  \****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Checks if `value` is classified as an `Array` object.
 *
 * @static
 * @memberOf _
 * @since 0.1.0
 * @category Lang
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is an array, else `false`.
 * @example
 *
 * _.isArray([1, 2, 3]);
 * // => true
 *
 * _.isArray(document.body.children);
 * // => false
 *
 * _.isArray('abc');
 * // => false
 *
 * _.isArray(_.noop);
 * // => false
 */
var isArray = Array.isArray;

module.exports = isArray;


/***/ }),

/***/ "./node_modules/lodash/isArrayLike.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/isArrayLike.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var isFunction = __webpack_require__(/*! ./isFunction */ "./node_modules/lodash/isFunction.js"),
    isLength = __webpack_require__(/*! ./isLength */ "./node_modules/lodash/isLength.js");

/**
 * Checks if `value` is array-like. A value is considered array-like if it's
 * not a function and has a `value.length` that's an integer greater than or
 * equal to `0` and less than or equal to `Number.MAX_SAFE_INTEGER`.
 *
 * @static
 * @memberOf _
 * @since 4.0.0
 * @category Lang
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is array-like, else `false`.
 * @example
 *
 * _.isArrayLike([1, 2, 3]);
 * // => true
 *
 * _.isArrayLike(document.body.children);
 * // => true
 *
 * _.isArrayLike('abc');
 * // => true
 *
 * _.isArrayLike(_.noop);
 * // => false
 */
function isArrayLike(value) {
  return value != null && isLength(value.length) && !isFunction(value);
}

module.exports = isArrayLike;


/***/ }),

/***/ "./node_modules/lodash/isBuffer.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/isBuffer.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

/* WEBPACK VAR INJECTION */(function(module) {var root = __webpack_require__(/*! ./_root */ "./node_modules/lodash/_root.js"),
    stubFalse = __webpack_require__(/*! ./stubFalse */ "./node_modules/lodash/stubFalse.js");

/** Detect free variable `exports`. */
var freeExports =  true && exports && !exports.nodeType && exports;

/** Detect free variable `module`. */
var freeModule = freeExports && typeof module == 'object' && module && !module.nodeType && module;

/** Detect the popular CommonJS extension `module.exports`. */
var moduleExports = freeModule && freeModule.exports === freeExports;

/** Built-in value references. */
var Buffer = moduleExports ? root.Buffer : undefined;

/* Built-in method references for those with the same name as other `lodash` methods. */
var nativeIsBuffer = Buffer ? Buffer.isBuffer : undefined;

/**
 * Checks if `value` is a buffer.
 *
 * @static
 * @memberOf _
 * @since 4.3.0
 * @category Lang
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is a buffer, else `false`.
 * @example
 *
 * _.isBuffer(new Buffer(2));
 * // => true
 *
 * _.isBuffer(new Uint8Array(2));
 * // => false
 */
var isBuffer = nativeIsBuffer || stubFalse;

module.exports = isBuffer;

/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./../webpack/buildin/module.js */ "./node_modules/webpack/buildin/module.js")(module)))

/***/ }),

/***/ "./node_modules/lodash/isFunction.js":
/*!*******************************************!*\
  !*** ./node_modules/lodash/isFunction.js ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseGetTag = __webpack_require__(/*! ./_baseGetTag */ "./node_modules/lodash/_baseGetTag.js"),
    isObject = __webpack_require__(/*! ./isObject */ "./node_modules/lodash/isObject.js");

/** `Object#toString` result references. */
var asyncTag = '[object AsyncFunction]',
    funcTag = '[object Function]',
    genTag = '[object GeneratorFunction]',
    proxyTag = '[object Proxy]';

/**
 * Checks if `value` is classified as a `Function` object.
 *
 * @static
 * @memberOf _
 * @since 0.1.0
 * @category Lang
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is a function, else `false`.
 * @example
 *
 * _.isFunction(_);
 * // => true
 *
 * _.isFunction(/abc/);
 * // => false
 */
function isFunction(value) {
  if (!isObject(value)) {
    return false;
  }
  // The use of `Object#toString` avoids issues with the `typeof` operator
  // in Safari 9 which returns 'object' for typed arrays and other constructors.
  var tag = baseGetTag(value);
  return tag == funcTag || tag == genTag || tag == asyncTag || tag == proxyTag;
}

module.exports = isFunction;


/***/ }),

/***/ "./node_modules/lodash/isLength.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/isLength.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/** Used as references for various `Number` constants. */
var MAX_SAFE_INTEGER = 9007199254740991;

/**
 * Checks if `value` is a valid array-like length.
 *
 * **Note:** This method is loosely based on
 * [`ToLength`](http://ecma-international.org/ecma-262/7.0/#sec-tolength).
 *
 * @static
 * @memberOf _
 * @since 4.0.0
 * @category Lang
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is a valid length, else `false`.
 * @example
 *
 * _.isLength(3);
 * // => true
 *
 * _.isLength(Number.MIN_VALUE);
 * // => false
 *
 * _.isLength(Infinity);
 * // => false
 *
 * _.isLength('3');
 * // => false
 */
function isLength(value) {
  return typeof value == 'number' &&
    value > -1 && value % 1 == 0 && value <= MAX_SAFE_INTEGER;
}

module.exports = isLength;


/***/ }),

/***/ "./node_modules/lodash/isNull.js":
/*!***************************************!*\
  !*** ./node_modules/lodash/isNull.js ***!
  \***************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Checks if `value` is `null`.
 *
 * @static
 * @memberOf _
 * @since 0.1.0
 * @category Lang
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is `null`, else `false`.
 * @example
 *
 * _.isNull(null);
 * // => true
 *
 * _.isNull(void 0);
 * // => false
 */
function isNull(value) {
  return value === null;
}

module.exports = isNull;


/***/ }),

/***/ "./node_modules/lodash/isObject.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/isObject.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Checks if `value` is the
 * [language type](http://www.ecma-international.org/ecma-262/7.0/#sec-ecmascript-language-types)
 * of `Object`. (e.g. arrays, functions, objects, regexes, `new Number(0)`, and `new String('')`)
 *
 * @static
 * @memberOf _
 * @since 0.1.0
 * @category Lang
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is an object, else `false`.
 * @example
 *
 * _.isObject({});
 * // => true
 *
 * _.isObject([1, 2, 3]);
 * // => true
 *
 * _.isObject(_.noop);
 * // => true
 *
 * _.isObject(null);
 * // => false
 */
function isObject(value) {
  var type = typeof value;
  return value != null && (type == 'object' || type == 'function');
}

module.exports = isObject;


/***/ }),

/***/ "./node_modules/lodash/isObjectLike.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/isObjectLike.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Checks if `value` is object-like. A value is object-like if it's not `null`
 * and has a `typeof` result of "object".
 *
 * @static
 * @memberOf _
 * @since 4.0.0
 * @category Lang
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is object-like, else `false`.
 * @example
 *
 * _.isObjectLike({});
 * // => true
 *
 * _.isObjectLike([1, 2, 3]);
 * // => true
 *
 * _.isObjectLike(_.noop);
 * // => false
 *
 * _.isObjectLike(null);
 * // => false
 */
function isObjectLike(value) {
  return value != null && typeof value == 'object';
}

module.exports = isObjectLike;


/***/ }),

/***/ "./node_modules/lodash/isSymbol.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/isSymbol.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseGetTag = __webpack_require__(/*! ./_baseGetTag */ "./node_modules/lodash/_baseGetTag.js"),
    isObjectLike = __webpack_require__(/*! ./isObjectLike */ "./node_modules/lodash/isObjectLike.js");

/** `Object#toString` result references. */
var symbolTag = '[object Symbol]';

/**
 * Checks if `value` is classified as a `Symbol` primitive or object.
 *
 * @static
 * @memberOf _
 * @since 4.0.0
 * @category Lang
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is a symbol, else `false`.
 * @example
 *
 * _.isSymbol(Symbol.iterator);
 * // => true
 *
 * _.isSymbol('abc');
 * // => false
 */
function isSymbol(value) {
  return typeof value == 'symbol' ||
    (isObjectLike(value) && baseGetTag(value) == symbolTag);
}

module.exports = isSymbol;


/***/ }),

/***/ "./node_modules/lodash/isTypedArray.js":
/*!*********************************************!*\
  !*** ./node_modules/lodash/isTypedArray.js ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseIsTypedArray = __webpack_require__(/*! ./_baseIsTypedArray */ "./node_modules/lodash/_baseIsTypedArray.js"),
    baseUnary = __webpack_require__(/*! ./_baseUnary */ "./node_modules/lodash/_baseUnary.js"),
    nodeUtil = __webpack_require__(/*! ./_nodeUtil */ "./node_modules/lodash/_nodeUtil.js");

/* Node.js helper references. */
var nodeIsTypedArray = nodeUtil && nodeUtil.isTypedArray;

/**
 * Checks if `value` is classified as a typed array.
 *
 * @static
 * @memberOf _
 * @since 3.0.0
 * @category Lang
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is a typed array, else `false`.
 * @example
 *
 * _.isTypedArray(new Uint8Array);
 * // => true
 *
 * _.isTypedArray([]);
 * // => false
 */
var isTypedArray = nodeIsTypedArray ? baseUnary(nodeIsTypedArray) : baseIsTypedArray;

module.exports = isTypedArray;


/***/ }),

/***/ "./node_modules/lodash/isUndefined.js":
/*!********************************************!*\
  !*** ./node_modules/lodash/isUndefined.js ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * Checks if `value` is `undefined`.
 *
 * @static
 * @since 0.1.0
 * @memberOf _
 * @category Lang
 * @param {*} value The value to check.
 * @returns {boolean} Returns `true` if `value` is `undefined`, else `false`.
 * @example
 *
 * _.isUndefined(void 0);
 * // => true
 *
 * _.isUndefined(null);
 * // => false
 */
function isUndefined(value) {
  return value === undefined;
}

module.exports = isUndefined;


/***/ }),

/***/ "./node_modules/lodash/keys.js":
/*!*************************************!*\
  !*** ./node_modules/lodash/keys.js ***!
  \*************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var arrayLikeKeys = __webpack_require__(/*! ./_arrayLikeKeys */ "./node_modules/lodash/_arrayLikeKeys.js"),
    baseKeys = __webpack_require__(/*! ./_baseKeys */ "./node_modules/lodash/_baseKeys.js"),
    isArrayLike = __webpack_require__(/*! ./isArrayLike */ "./node_modules/lodash/isArrayLike.js");

/**
 * Creates an array of the own enumerable property names of `object`.
 *
 * **Note:** Non-object values are coerced to objects. See the
 * [ES spec](http://ecma-international.org/ecma-262/7.0/#sec-object.keys)
 * for more details.
 *
 * @static
 * @since 0.1.0
 * @memberOf _
 * @category Object
 * @param {Object} object The object to query.
 * @returns {Array} Returns the array of property names.
 * @example
 *
 * function Foo() {
 *   this.a = 1;
 *   this.b = 2;
 * }
 *
 * Foo.prototype.c = 3;
 *
 * _.keys(new Foo);
 * // => ['a', 'b'] (iteration order is not guaranteed)
 *
 * _.keys('hi');
 * // => ['0', '1']
 */
function keys(object) {
  return isArrayLike(object) ? arrayLikeKeys(object) : baseKeys(object);
}

module.exports = keys;


/***/ }),

/***/ "./node_modules/lodash/map.js":
/*!************************************!*\
  !*** ./node_modules/lodash/map.js ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var arrayMap = __webpack_require__(/*! ./_arrayMap */ "./node_modules/lodash/_arrayMap.js"),
    baseIteratee = __webpack_require__(/*! ./_baseIteratee */ "./node_modules/lodash/_baseIteratee.js"),
    baseMap = __webpack_require__(/*! ./_baseMap */ "./node_modules/lodash/_baseMap.js"),
    isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js");

/**
 * Creates an array of values by running each element in `collection` thru
 * `iteratee`. The iteratee is invoked with three arguments:
 * (value, index|key, collection).
 *
 * Many lodash methods are guarded to work as iteratees for methods like
 * `_.every`, `_.filter`, `_.map`, `_.mapValues`, `_.reject`, and `_.some`.
 *
 * The guarded methods are:
 * `ary`, `chunk`, `curry`, `curryRight`, `drop`, `dropRight`, `every`,
 * `fill`, `invert`, `parseInt`, `random`, `range`, `rangeRight`, `repeat`,
 * `sampleSize`, `slice`, `some`, `sortBy`, `split`, `take`, `takeRight`,
 * `template`, `trim`, `trimEnd`, `trimStart`, and `words`
 *
 * @static
 * @memberOf _
 * @since 0.1.0
 * @category Collection
 * @param {Array|Object} collection The collection to iterate over.
 * @param {Function} [iteratee=_.identity] The function invoked per iteration.
 * @returns {Array} Returns the new mapped array.
 * @example
 *
 * function square(n) {
 *   return n * n;
 * }
 *
 * _.map([4, 8], square);
 * // => [16, 64]
 *
 * _.map({ 'a': 4, 'b': 8 }, square);
 * // => [16, 64] (iteration order is not guaranteed)
 *
 * var users = [
 *   { 'user': 'barney' },
 *   { 'user': 'fred' }
 * ];
 *
 * // The `_.property` iteratee shorthand.
 * _.map(users, 'user');
 * // => ['barney', 'fred']
 */
function map(collection, iteratee) {
  var func = isArray(collection) ? arrayMap : baseMap;
  return func(collection, baseIteratee(iteratee, 3));
}

module.exports = map;


/***/ }),

/***/ "./node_modules/lodash/memoize.js":
/*!****************************************!*\
  !*** ./node_modules/lodash/memoize.js ***!
  \****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var MapCache = __webpack_require__(/*! ./_MapCache */ "./node_modules/lodash/_MapCache.js");

/** Error message constants. */
var FUNC_ERROR_TEXT = 'Expected a function';

/**
 * Creates a function that memoizes the result of `func`. If `resolver` is
 * provided, it determines the cache key for storing the result based on the
 * arguments provided to the memoized function. By default, the first argument
 * provided to the memoized function is used as the map cache key. The `func`
 * is invoked with the `this` binding of the memoized function.
 *
 * **Note:** The cache is exposed as the `cache` property on the memoized
 * function. Its creation may be customized by replacing the `_.memoize.Cache`
 * constructor with one whose instances implement the
 * [`Map`](http://ecma-international.org/ecma-262/7.0/#sec-properties-of-the-map-prototype-object)
 * method interface of `clear`, `delete`, `get`, `has`, and `set`.
 *
 * @static
 * @memberOf _
 * @since 0.1.0
 * @category Function
 * @param {Function} func The function to have its output memoized.
 * @param {Function} [resolver] The function to resolve the cache key.
 * @returns {Function} Returns the new memoized function.
 * @example
 *
 * var object = { 'a': 1, 'b': 2 };
 * var other = { 'c': 3, 'd': 4 };
 *
 * var values = _.memoize(_.values);
 * values(object);
 * // => [1, 2]
 *
 * values(other);
 * // => [3, 4]
 *
 * object.a = 2;
 * values(object);
 * // => [1, 2]
 *
 * // Modify the result cache.
 * values.cache.set(object, ['a', 'b']);
 * values(object);
 * // => ['a', 'b']
 *
 * // Replace `_.memoize.Cache`.
 * _.memoize.Cache = WeakMap;
 */
function memoize(func, resolver) {
  if (typeof func != 'function' || (resolver != null && typeof resolver != 'function')) {
    throw new TypeError(FUNC_ERROR_TEXT);
  }
  var memoized = function() {
    var args = arguments,
        key = resolver ? resolver.apply(this, args) : args[0],
        cache = memoized.cache;

    if (cache.has(key)) {
      return cache.get(key);
    }
    var result = func.apply(this, args);
    memoized.cache = cache.set(key, result) || cache;
    return result;
  };
  memoized.cache = new (memoize.Cache || MapCache);
  return memoized;
}

// Expose `MapCache`.
memoize.Cache = MapCache;

module.exports = memoize;


/***/ }),

/***/ "./node_modules/lodash/noop.js":
/*!*************************************!*\
  !*** ./node_modules/lodash/noop.js ***!
  \*************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * This method returns `undefined`.
 *
 * @static
 * @memberOf _
 * @since 2.3.0
 * @category Util
 * @example
 *
 * _.times(2, _.noop);
 * // => [undefined, undefined]
 */
function noop() {
  // No operation performed.
}

module.exports = noop;


/***/ }),

/***/ "./node_modules/lodash/pick.js":
/*!*************************************!*\
  !*** ./node_modules/lodash/pick.js ***!
  \*************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var basePick = __webpack_require__(/*! ./_basePick */ "./node_modules/lodash/_basePick.js"),
    flatRest = __webpack_require__(/*! ./_flatRest */ "./node_modules/lodash/_flatRest.js");

/**
 * Creates an object composed of the picked `object` properties.
 *
 * @static
 * @since 0.1.0
 * @memberOf _
 * @category Object
 * @param {Object} object The source object.
 * @param {...(string|string[])} [paths] The property paths to pick.
 * @returns {Object} Returns the new object.
 * @example
 *
 * var object = { 'a': 1, 'b': '2', 'c': 3 };
 *
 * _.pick(object, ['a', 'c']);
 * // => { 'a': 1, 'c': 3 }
 */
var pick = flatRest(function(object, paths) {
  return object == null ? {} : basePick(object, paths);
});

module.exports = pick;


/***/ }),

/***/ "./node_modules/lodash/property.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/property.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseProperty = __webpack_require__(/*! ./_baseProperty */ "./node_modules/lodash/_baseProperty.js"),
    basePropertyDeep = __webpack_require__(/*! ./_basePropertyDeep */ "./node_modules/lodash/_basePropertyDeep.js"),
    isKey = __webpack_require__(/*! ./_isKey */ "./node_modules/lodash/_isKey.js"),
    toKey = __webpack_require__(/*! ./_toKey */ "./node_modules/lodash/_toKey.js");

/**
 * Creates a function that returns the value at `path` of a given object.
 *
 * @static
 * @memberOf _
 * @since 2.4.0
 * @category Util
 * @param {Array|string} path The path of the property to get.
 * @returns {Function} Returns the new accessor function.
 * @example
 *
 * var objects = [
 *   { 'a': { 'b': 2 } },
 *   { 'a': { 'b': 1 } }
 * ];
 *
 * _.map(objects, _.property('a.b'));
 * // => [2, 1]
 *
 * _.map(_.sortBy(objects, _.property(['a', 'b'])), 'a.b');
 * // => [1, 2]
 */
function property(path) {
  return isKey(path) ? baseProperty(toKey(path)) : basePropertyDeep(path);
}

module.exports = property;


/***/ }),

/***/ "./node_modules/lodash/stubArray.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/stubArray.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * This method returns a new empty array.
 *
 * @static
 * @memberOf _
 * @since 4.13.0
 * @category Util
 * @returns {Array} Returns the new empty array.
 * @example
 *
 * var arrays = _.times(2, _.stubArray);
 *
 * console.log(arrays);
 * // => [[], []]
 *
 * console.log(arrays[0] === arrays[1]);
 * // => false
 */
function stubArray() {
  return [];
}

module.exports = stubArray;


/***/ }),

/***/ "./node_modules/lodash/stubFalse.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/stubFalse.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

/**
 * This method returns `false`.
 *
 * @static
 * @memberOf _
 * @since 4.13.0
 * @category Util
 * @returns {boolean} Returns `false`.
 * @example
 *
 * _.times(2, _.stubFalse);
 * // => [false, false]
 */
function stubFalse() {
  return false;
}

module.exports = stubFalse;


/***/ }),

/***/ "./node_modules/lodash/toFinite.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/toFinite.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var toNumber = __webpack_require__(/*! ./toNumber */ "./node_modules/lodash/toNumber.js");

/** Used as references for various `Number` constants. */
var INFINITY = 1 / 0,
    MAX_INTEGER = 1.7976931348623157e+308;

/**
 * Converts `value` to a finite number.
 *
 * @static
 * @memberOf _
 * @since 4.12.0
 * @category Lang
 * @param {*} value The value to convert.
 * @returns {number} Returns the converted number.
 * @example
 *
 * _.toFinite(3.2);
 * // => 3.2
 *
 * _.toFinite(Number.MIN_VALUE);
 * // => 5e-324
 *
 * _.toFinite(Infinity);
 * // => 1.7976931348623157e+308
 *
 * _.toFinite('3.2');
 * // => 3.2
 */
function toFinite(value) {
  if (!value) {
    return value === 0 ? value : 0;
  }
  value = toNumber(value);
  if (value === INFINITY || value === -INFINITY) {
    var sign = (value < 0 ? -1 : 1);
    return sign * MAX_INTEGER;
  }
  return value === value ? value : 0;
}

module.exports = toFinite;


/***/ }),

/***/ "./node_modules/lodash/toInteger.js":
/*!******************************************!*\
  !*** ./node_modules/lodash/toInteger.js ***!
  \******************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var toFinite = __webpack_require__(/*! ./toFinite */ "./node_modules/lodash/toFinite.js");

/**
 * Converts `value` to an integer.
 *
 * **Note:** This method is loosely based on
 * [`ToInteger`](http://www.ecma-international.org/ecma-262/7.0/#sec-tointeger).
 *
 * @static
 * @memberOf _
 * @since 4.0.0
 * @category Lang
 * @param {*} value The value to convert.
 * @returns {number} Returns the converted integer.
 * @example
 *
 * _.toInteger(3.2);
 * // => 3
 *
 * _.toInteger(Number.MIN_VALUE);
 * // => 0
 *
 * _.toInteger(Infinity);
 * // => 1.7976931348623157e+308
 *
 * _.toInteger('3.2');
 * // => 3
 */
function toInteger(value) {
  var result = toFinite(value),
      remainder = result % 1;

  return result === result ? (remainder ? result - remainder : result) : 0;
}

module.exports = toInteger;


/***/ }),

/***/ "./node_modules/lodash/toNumber.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/toNumber.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseTrim = __webpack_require__(/*! ./_baseTrim */ "./node_modules/lodash/_baseTrim.js"),
    isObject = __webpack_require__(/*! ./isObject */ "./node_modules/lodash/isObject.js"),
    isSymbol = __webpack_require__(/*! ./isSymbol */ "./node_modules/lodash/isSymbol.js");

/** Used as references for various `Number` constants. */
var NAN = 0 / 0;

/** Used to detect bad signed hexadecimal string values. */
var reIsBadHex = /^[-+]0x[0-9a-f]+$/i;

/** Used to detect binary string values. */
var reIsBinary = /^0b[01]+$/i;

/** Used to detect octal string values. */
var reIsOctal = /^0o[0-7]+$/i;

/** Built-in method references without a dependency on `root`. */
var freeParseInt = parseInt;

/**
 * Converts `value` to a number.
 *
 * @static
 * @memberOf _
 * @since 4.0.0
 * @category Lang
 * @param {*} value The value to process.
 * @returns {number} Returns the number.
 * @example
 *
 * _.toNumber(3.2);
 * // => 3.2
 *
 * _.toNumber(Number.MIN_VALUE);
 * // => 5e-324
 *
 * _.toNumber(Infinity);
 * // => Infinity
 *
 * _.toNumber('3.2');
 * // => 3.2
 */
function toNumber(value) {
  if (typeof value == 'number') {
    return value;
  }
  if (isSymbol(value)) {
    return NAN;
  }
  if (isObject(value)) {
    var other = typeof value.valueOf == 'function' ? value.valueOf() : value;
    value = isObject(other) ? (other + '') : other;
  }
  if (typeof value != 'string') {
    return value === 0 ? value : +value;
  }
  value = baseTrim(value);
  var isBinary = reIsBinary.test(value);
  return (isBinary || reIsOctal.test(value))
    ? freeParseInt(value.slice(2), isBinary ? 2 : 8)
    : (reIsBadHex.test(value) ? NAN : +value);
}

module.exports = toNumber;


/***/ }),

/***/ "./node_modules/lodash/toString.js":
/*!*****************************************!*\
  !*** ./node_modules/lodash/toString.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var baseToString = __webpack_require__(/*! ./_baseToString */ "./node_modules/lodash/_baseToString.js");

/**
 * Converts `value` to a string. An empty string is returned for `null`
 * and `undefined` values. The sign of `-0` is preserved.
 *
 * @static
 * @memberOf _
 * @since 4.0.0
 * @category Lang
 * @param {*} value The value to convert.
 * @returns {string} Returns the converted string.
 * @example
 *
 * _.toString(null);
 * // => ''
 *
 * _.toString(-0);
 * // => '-0'
 *
 * _.toString([1, 2, 3]);
 * // => '1,2,3'
 */
function toString(value) {
  return value == null ? '' : baseToString(value);
}

module.exports = toString;


/***/ }),

/***/ "./node_modules/lodash/wrapperLodash.js":
/*!**********************************************!*\
  !*** ./node_modules/lodash/wrapperLodash.js ***!
  \**********************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

var LazyWrapper = __webpack_require__(/*! ./_LazyWrapper */ "./node_modules/lodash/_LazyWrapper.js"),
    LodashWrapper = __webpack_require__(/*! ./_LodashWrapper */ "./node_modules/lodash/_LodashWrapper.js"),
    baseLodash = __webpack_require__(/*! ./_baseLodash */ "./node_modules/lodash/_baseLodash.js"),
    isArray = __webpack_require__(/*! ./isArray */ "./node_modules/lodash/isArray.js"),
    isObjectLike = __webpack_require__(/*! ./isObjectLike */ "./node_modules/lodash/isObjectLike.js"),
    wrapperClone = __webpack_require__(/*! ./_wrapperClone */ "./node_modules/lodash/_wrapperClone.js");

/** Used for built-in method references. */
var objectProto = Object.prototype;

/** Used to check objects for own properties. */
var hasOwnProperty = objectProto.hasOwnProperty;

/**
 * Creates a `lodash` object which wraps `value` to enable implicit method
 * chain sequences. Methods that operate on and return arrays, collections,
 * and functions can be chained together. Methods that retrieve a single value
 * or may return a primitive value will automatically end the chain sequence
 * and return the unwrapped value. Otherwise, the value must be unwrapped
 * with `_#value`.
 *
 * Explicit chain sequences, which must be unwrapped with `_#value`, may be
 * enabled using `_.chain`.
 *
 * The execution of chained methods is lazy, that is, it's deferred until
 * `_#value` is implicitly or explicitly called.
 *
 * Lazy evaluation allows several methods to support shortcut fusion.
 * Shortcut fusion is an optimization to merge iteratee calls; this avoids
 * the creation of intermediate arrays and can greatly reduce the number of
 * iteratee executions. Sections of a chain sequence qualify for shortcut
 * fusion if the section is applied to an array and iteratees accept only
 * one argument. The heuristic for whether a section qualifies for shortcut
 * fusion is subject to change.
 *
 * Chaining is supported in custom builds as long as the `_#value` method is
 * directly or indirectly included in the build.
 *
 * In addition to lodash methods, wrappers have `Array` and `String` methods.
 *
 * The wrapper `Array` methods are:
 * `concat`, `join`, `pop`, `push`, `shift`, `sort`, `splice`, and `unshift`
 *
 * The wrapper `String` methods are:
 * `replace` and `split`
 *
 * The wrapper methods that support shortcut fusion are:
 * `at`, `compact`, `drop`, `dropRight`, `dropWhile`, `filter`, `find`,
 * `findLast`, `head`, `initial`, `last`, `map`, `reject`, `reverse`, `slice`,
 * `tail`, `take`, `takeRight`, `takeRightWhile`, `takeWhile`, and `toArray`
 *
 * The chainable wrapper methods are:
 * `after`, `ary`, `assign`, `assignIn`, `assignInWith`, `assignWith`, `at`,
 * `before`, `bind`, `bindAll`, `bindKey`, `castArray`, `chain`, `chunk`,
 * `commit`, `compact`, `concat`, `conforms`, `constant`, `countBy`, `create`,
 * `curry`, `debounce`, `defaults`, `defaultsDeep`, `defer`, `delay`,
 * `difference`, `differenceBy`, `differenceWith`, `drop`, `dropRight`,
 * `dropRightWhile`, `dropWhile`, `extend`, `extendWith`, `fill`, `filter`,
 * `flatMap`, `flatMapDeep`, `flatMapDepth`, `flatten`, `flattenDeep`,
 * `flattenDepth`, `flip`, `flow`, `flowRight`, `fromPairs`, `functions`,
 * `functionsIn`, `groupBy`, `initial`, `intersection`, `intersectionBy`,
 * `intersectionWith`, `invert`, `invertBy`, `invokeMap`, `iteratee`, `keyBy`,
 * `keys`, `keysIn`, `map`, `mapKeys`, `mapValues`, `matches`, `matchesProperty`,
 * `memoize`, `merge`, `mergeWith`, `method`, `methodOf`, `mixin`, `negate`,
 * `nthArg`, `omit`, `omitBy`, `once`, `orderBy`, `over`, `overArgs`,
 * `overEvery`, `overSome`, `partial`, `partialRight`, `partition`, `pick`,
 * `pickBy`, `plant`, `property`, `propertyOf`, `pull`, `pullAll`, `pullAllBy`,
 * `pullAllWith`, `pullAt`, `push`, `range`, `rangeRight`, `rearg`, `reject`,
 * `remove`, `rest`, `reverse`, `sampleSize`, `set`, `setWith`, `shuffle`,
 * `slice`, `sort`, `sortBy`, `splice`, `spread`, `tail`, `take`, `takeRight`,
 * `takeRightWhile`, `takeWhile`, `tap`, `throttle`, `thru`, `toArray`,
 * `toPairs`, `toPairsIn`, `toPath`, `toPlainObject`, `transform`, `unary`,
 * `union`, `unionBy`, `unionWith`, `uniq`, `uniqBy`, `uniqWith`, `unset`,
 * `unshift`, `unzip`, `unzipWith`, `update`, `updateWith`, `values`,
 * `valuesIn`, `without`, `wrap`, `xor`, `xorBy`, `xorWith`, `zip`,
 * `zipObject`, `zipObjectDeep`, and `zipWith`
 *
 * The wrapper methods that are **not** chainable by default are:
 * `add`, `attempt`, `camelCase`, `capitalize`, `ceil`, `clamp`, `clone`,
 * `cloneDeep`, `cloneDeepWith`, `cloneWith`, `conformsTo`, `deburr`,
 * `defaultTo`, `divide`, `each`, `eachRight`, `endsWith`, `eq`, `escape`,
 * `escapeRegExp`, `every`, `find`, `findIndex`, `findKey`, `findLast`,
 * `findLastIndex`, `findLastKey`, `first`, `floor`, `forEach`, `forEachRight`,
 * `forIn`, `forInRight`, `forOwn`, `forOwnRight`, `get`, `gt`, `gte`, `has`,
 * `hasIn`, `head`, `identity`, `includes`, `indexOf`, `inRange`, `invoke`,
 * `isArguments`, `isArray`, `isArrayBuffer`, `isArrayLike`, `isArrayLikeObject`,
 * `isBoolean`, `isBuffer`, `isDate`, `isElement`, `isEmpty`, `isEqual`,
 * `isEqualWith`, `isError`, `isFinite`, `isFunction`, `isInteger`, `isLength`,
 * `isMap`, `isMatch`, `isMatchWith`, `isNaN`, `isNative`, `isNil`, `isNull`,
 * `isNumber`, `isObject`, `isObjectLike`, `isPlainObject`, `isRegExp`,
 * `isSafeInteger`, `isSet`, `isString`, `isUndefined`, `isTypedArray`,
 * `isWeakMap`, `isWeakSet`, `join`, `kebabCase`, `last`, `lastIndexOf`,
 * `lowerCase`, `lowerFirst`, `lt`, `lte`, `max`, `maxBy`, `mean`, `meanBy`,
 * `min`, `minBy`, `multiply`, `noConflict`, `noop`, `now`, `nth`, `pad`,
 * `padEnd`, `padStart`, `parseInt`, `pop`, `random`, `reduce`, `reduceRight`,
 * `repeat`, `result`, `round`, `runInContext`, `sample`, `shift`, `size`,
 * `snakeCase`, `some`, `sortedIndex`, `sortedIndexBy`, `sortedLastIndex`,
 * `sortedLastIndexBy`, `startCase`, `startsWith`, `stubArray`, `stubFalse`,
 * `stubObject`, `stubString`, `stubTrue`, `subtract`, `sum`, `sumBy`,
 * `template`, `times`, `toFinite`, `toInteger`, `toJSON`, `toLength`,
 * `toLower`, `toNumber`, `toSafeInteger`, `toString`, `toUpper`, `trim`,
 * `trimEnd`, `trimStart`, `truncate`, `unescape`, `uniqueId`, `upperCase`,
 * `upperFirst`, `value`, and `words`
 *
 * @name _
 * @constructor
 * @category Seq
 * @param {*} value The value to wrap in a `lodash` instance.
 * @returns {Object} Returns the new `lodash` wrapper instance.
 * @example
 *
 * function square(n) {
 *   return n * n;
 * }
 *
 * var wrapped = _([1, 2, 3]);
 *
 * // Returns an unwrapped value.
 * wrapped.reduce(_.add);
 * // => 6
 *
 * // Returns a wrapped value.
 * var squares = wrapped.map(square);
 *
 * _.isArray(squares);
 * // => false
 *
 * _.isArray(squares.value());
 * // => true
 */
function lodash(value) {
  if (isObjectLike(value) && !isArray(value) && !(value instanceof LazyWrapper)) {
    if (value instanceof LodashWrapper) {
      return value;
    }
    if (hasOwnProperty.call(value, '__wrapped__')) {
      return wrapperClone(value);
    }
  }
  return new LodashWrapper(value);
}

// Ensure wrappers are instances of `baseLodash`.
lodash.prototype = baseLodash.prototype;
lodash.prototype.constructor = lodash;

module.exports = lodash;


/***/ }),

/***/ "./node_modules/min-dash/dist/index.esm.js":
/*!*************************************************!*\
  !*** ./node_modules/min-dash/dist/index.esm.js ***!
  \*************************************************/
/*! exports provided: assign, bind, debounce, ensureArray, every, filter, find, findIndex, flatten, forEach, get, groupBy, has, isArray, isDefined, isFunction, isNil, isNumber, isObject, isString, isUndefined, keys, map, matchPattern, merge, omit, pick, reduce, set, size, some, sortBy, throttle, unionBy, uniqueBy, values, without */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "assign", function() { return assign; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "bind", function() { return bind; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "debounce", function() { return debounce; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ensureArray", function() { return ensureArray; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "every", function() { return every; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "filter", function() { return filter; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "find", function() { return find; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "findIndex", function() { return findIndex; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "flatten", function() { return flatten; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "forEach", function() { return forEach; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "get", function() { return get; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "groupBy", function() { return groupBy; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "has", function() { return has; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "isArray", function() { return isArray; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "isDefined", function() { return isDefined; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "isFunction", function() { return isFunction; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "isNil", function() { return isNil; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "isNumber", function() { return isNumber; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "isObject", function() { return isObject; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "isString", function() { return isString; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "isUndefined", function() { return isUndefined; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "keys", function() { return keys; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "map", function() { return map; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "matchPattern", function() { return matchPattern; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "merge", function() { return merge; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "omit", function() { return omit; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "pick", function() { return pick; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "reduce", function() { return reduce; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "set", function() { return set; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "size", function() { return size; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "some", function() { return some; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "sortBy", function() { return sortBy; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "throttle", function() { return throttle; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "unionBy", function() { return unionBy; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "uniqueBy", function() { return uniqueBy; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "values", function() { return values; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "without", function() { return without; });
/**
 * Flatten array, one level deep.
 *
 * @param {Array<?>} arr
 *
 * @return {Array<?>}
 */
function flatten(arr) {
  return Array.prototype.concat.apply([], arr);
}

var nativeToString = Object.prototype.toString;
var nativeHasOwnProperty = Object.prototype.hasOwnProperty;
function isUndefined(obj) {
  return obj === undefined;
}
function isDefined(obj) {
  return obj !== undefined;
}
function isNil(obj) {
  return obj == null;
}
function isArray(obj) {
  return nativeToString.call(obj) === '[object Array]';
}
function isObject(obj) {
  return nativeToString.call(obj) === '[object Object]';
}
function isNumber(obj) {
  return nativeToString.call(obj) === '[object Number]';
}
function isFunction(obj) {
  var tag = nativeToString.call(obj);
  return tag === '[object Function]' || tag === '[object AsyncFunction]' || tag === '[object GeneratorFunction]' || tag === '[object AsyncGeneratorFunction]' || tag === '[object Proxy]';
}
function isString(obj) {
  return nativeToString.call(obj) === '[object String]';
}
/**
 * Ensure collection is an array.
 *
 * @param {Object} obj
 */

function ensureArray(obj) {
  if (isArray(obj)) {
    return;
  }

  throw new Error('must supply array');
}
/**
 * Return true, if target owns a property with the given key.
 *
 * @param {Object} target
 * @param {String} key
 *
 * @return {Boolean}
 */

function has(target, key) {
  return nativeHasOwnProperty.call(target, key);
}

/**
 * Find element in collection.
 *
 * @param  {Array|Object} collection
 * @param  {Function|Object} matcher
 *
 * @return {Object}
 */

function find(collection, matcher) {
  matcher = toMatcher(matcher);
  var match;
  forEach(collection, function (val, key) {
    if (matcher(val, key)) {
      match = val;
      return false;
    }
  });
  return match;
}
/**
 * Find element index in collection.
 *
 * @param  {Array|Object} collection
 * @param  {Function} matcher
 *
 * @return {Object}
 */

function findIndex(collection, matcher) {
  matcher = toMatcher(matcher);
  var idx = isArray(collection) ? -1 : undefined;
  forEach(collection, function (val, key) {
    if (matcher(val, key)) {
      idx = key;
      return false;
    }
  });
  return idx;
}
/**
 * Find element in collection.
 *
 * @param  {Array|Object} collection
 * @param  {Function} matcher
 *
 * @return {Array} result
 */

function filter(collection, matcher) {
  var result = [];
  forEach(collection, function (val, key) {
    if (matcher(val, key)) {
      result.push(val);
    }
  });
  return result;
}
/**
 * Iterate over collection; returning something
 * (non-undefined) will stop iteration.
 *
 * @param  {Array|Object} collection
 * @param  {Function} iterator
 *
 * @return {Object} return result that stopped the iteration
 */

function forEach(collection, iterator) {
  var val, result;

  if (isUndefined(collection)) {
    return;
  }

  var convertKey = isArray(collection) ? toNum : identity;

  for (var key in collection) {
    if (has(collection, key)) {
      val = collection[key];
      result = iterator(val, convertKey(key));

      if (result === false) {
        return val;
      }
    }
  }
}
/**
 * Return collection without element.
 *
 * @param  {Array} arr
 * @param  {Function} matcher
 *
 * @return {Array}
 */

function without(arr, matcher) {
  if (isUndefined(arr)) {
    return [];
  }

  ensureArray(arr);
  matcher = toMatcher(matcher);
  return arr.filter(function (el, idx) {
    return !matcher(el, idx);
  });
}
/**
 * Reduce collection, returning a single result.
 *
 * @param  {Object|Array} collection
 * @param  {Function} iterator
 * @param  {Any} result
 *
 * @return {Any} result returned from last iterator
 */

function reduce(collection, iterator, result) {
  forEach(collection, function (value, idx) {
    result = iterator(result, value, idx);
  });
  return result;
}
/**
 * Return true if every element in the collection
 * matches the criteria.
 *
 * @param  {Object|Array} collection
 * @param  {Function} matcher
 *
 * @return {Boolean}
 */

function every(collection, matcher) {
  return !!reduce(collection, function (matches, val, key) {
    return matches && matcher(val, key);
  }, true);
}
/**
 * Return true if some elements in the collection
 * match the criteria.
 *
 * @param  {Object|Array} collection
 * @param  {Function} matcher
 *
 * @return {Boolean}
 */

function some(collection, matcher) {
  return !!find(collection, matcher);
}
/**
 * Transform a collection into another collection
 * by piping each member through the given fn.
 *
 * @param  {Object|Array}   collection
 * @param  {Function} fn
 *
 * @return {Array} transformed collection
 */

function map(collection, fn) {
  var result = [];
  forEach(collection, function (val, key) {
    result.push(fn(val, key));
  });
  return result;
}
/**
 * Get the collections keys.
 *
 * @param  {Object|Array} collection
 *
 * @return {Array}
 */

function keys(collection) {
  return collection && Object.keys(collection) || [];
}
/**
 * Shorthand for `keys(o).length`.
 *
 * @param  {Object|Array} collection
 *
 * @return {Number}
 */

function size(collection) {
  return keys(collection).length;
}
/**
 * Get the values in the collection.
 *
 * @param  {Object|Array} collection
 *
 * @return {Array}
 */

function values(collection) {
  return map(collection, function (val) {
    return val;
  });
}
/**
 * Group collection members by attribute.
 *
 * @param  {Object|Array} collection
 * @param  {Function} extractor
 *
 * @return {Object} map with { attrValue => [ a, b, c ] }
 */

function groupBy(collection, extractor) {
  var grouped = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : {};
  extractor = toExtractor(extractor);
  forEach(collection, function (val) {
    var discriminator = extractor(val) || '_';
    var group = grouped[discriminator];

    if (!group) {
      group = grouped[discriminator] = [];
    }

    group.push(val);
  });
  return grouped;
}
function uniqueBy(extractor) {
  extractor = toExtractor(extractor);
  var grouped = {};

  for (var _len = arguments.length, collections = new Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
    collections[_key - 1] = arguments[_key];
  }

  forEach(collections, function (c) {
    return groupBy(c, extractor, grouped);
  });
  var result = map(grouped, function (val, key) {
    return val[0];
  });
  return result;
}
var unionBy = uniqueBy;
/**
 * Sort collection by criteria.
 *
 * @param  {Object|Array} collection
 * @param  {String|Function} extractor
 *
 * @return {Array}
 */

function sortBy(collection, extractor) {
  extractor = toExtractor(extractor);
  var sorted = [];
  forEach(collection, function (value, key) {
    var disc = extractor(value, key);
    var entry = {
      d: disc,
      v: value
    };

    for (var idx = 0; idx < sorted.length; idx++) {
      var d = sorted[idx].d;

      if (disc < d) {
        sorted.splice(idx, 0, entry);
        return;
      }
    } // not inserted, append (!)


    sorted.push(entry);
  });
  return map(sorted, function (e) {
    return e.v;
  });
}
/**
 * Create an object pattern matcher.
 *
 * @example
 *
 * const matcher = matchPattern({ id: 1 });
 *
 * var element = find(elements, matcher);
 *
 * @param  {Object} pattern
 *
 * @return {Function} matcherFn
 */

function matchPattern(pattern) {
  return function (el) {
    return every(pattern, function (val, key) {
      return el[key] === val;
    });
  };
}

function toExtractor(extractor) {
  return isFunction(extractor) ? extractor : function (e) {
    return e[extractor];
  };
}

function toMatcher(matcher) {
  return isFunction(matcher) ? matcher : function (e) {
    return e === matcher;
  };
}

function identity(arg) {
  return arg;
}

function toNum(arg) {
  return Number(arg);
}

/**
 * Debounce fn, calling it only once if
 * the given time elapsed between calls.
 *
 * @param  {Function} fn
 * @param  {Number} timeout
 *
 * @return {Function} debounced function
 */
function debounce(fn, timeout) {
  var timer;
  var lastArgs;
  var lastThis;
  var lastNow;

  function fire() {
    var now = Date.now();
    var scheduledDiff = lastNow + timeout - now;

    if (scheduledDiff > 0) {
      return schedule(scheduledDiff);
    }

    fn.apply(lastThis, lastArgs);
    timer = lastNow = lastArgs = lastThis = undefined;
  }

  function schedule(timeout) {
    timer = setTimeout(fire, timeout);
  }

  return function () {
    lastNow = Date.now();

    for (var _len = arguments.length, args = new Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    lastArgs = args;
    lastThis = this; // ensure an execution is scheduled

    if (!timer) {
      schedule(timeout);
    }
  };
}
/**
 * Throttle fn, calling at most once
 * in the given interval.
 *
 * @param  {Function} fn
 * @param  {Number} interval
 *
 * @return {Function} throttled function
 */

function throttle(fn, interval) {
  var throttling = false;
  return function () {
    if (throttling) {
      return;
    }

    fn.apply(void 0, arguments);
    throttling = true;
    setTimeout(function () {
      throttling = false;
    }, interval);
  };
}
/**
 * Bind function against target <this>.
 *
 * @param  {Function} fn
 * @param  {Object}   target
 *
 * @return {Function} bound function
 */

function bind(fn, target) {
  return fn.bind(target);
}

function _extends() {
  _extends = Object.assign || function (target) {
    for (var i = 1; i < arguments.length; i++) {
      var source = arguments[i];

      for (var key in source) {
        if (Object.prototype.hasOwnProperty.call(source, key)) {
          target[key] = source[key];
        }
      }
    }

    return target;
  };

  return _extends.apply(this, arguments);
}

/**
 * Convenience wrapper for `Object.assign`.
 *
 * @param {Object} target
 * @param {...Object} others
 *
 * @return {Object} the target
 */

function assign(target) {
  for (var _len = arguments.length, others = new Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
    others[_key - 1] = arguments[_key];
  }

  return _extends.apply(void 0, [target].concat(others));
}
/**
 * Sets a nested property of a given object to the specified value.
 *
 * This mutates the object and returns it.
 *
 * @param {Object} target The target of the set operation.
 * @param {(string|number)[]} path The path to the nested value.
 * @param {any} value The value to set.
 */

function set(target, path, value) {
  var currentTarget = target;
  forEach(path, function (key, idx) {
    if (key === '__proto__') {
      throw new Error('illegal key: __proto__');
    }

    var nextKey = path[idx + 1];
    var nextTarget = currentTarget[key];

    if (isDefined(nextKey) && isNil(nextTarget)) {
      nextTarget = currentTarget[key] = isNaN(+nextKey) ? {} : [];
    }

    if (isUndefined(nextKey)) {
      if (isUndefined(value)) {
        delete currentTarget[key];
      } else {
        currentTarget[key] = value;
      }
    } else {
      currentTarget = nextTarget;
    }
  });
  return target;
}
/**
 * Gets a nested property of a given object.
 *
 * @param {Object} target The target of the get operation.
 * @param {(string|number)[]} path The path to the nested value.
 * @param {any} [defaultValue] The value to return if no value exists.
 */

function get(target, path, defaultValue) {
  var currentTarget = target;
  forEach(path, function (key) {
    // accessing nil property yields <undefined>
    if (isNil(currentTarget)) {
      currentTarget = undefined;
      return false;
    }

    currentTarget = currentTarget[key];
  });
  return isUndefined(currentTarget) ? defaultValue : currentTarget;
}
/**
 * Pick given properties from the target object.
 *
 * @param {Object} target
 * @param {Array} properties
 *
 * @return {Object} target
 */

function pick(target, properties) {
  var result = {};
  var obj = Object(target);
  forEach(properties, function (prop) {
    if (prop in obj) {
      result[prop] = target[prop];
    }
  });
  return result;
}
/**
 * Pick all target properties, excluding the given ones.
 *
 * @param {Object} target
 * @param {Array} properties
 *
 * @return {Object} target
 */

function omit(target, properties) {
  var result = {};
  var obj = Object(target);
  forEach(obj, function (prop, key) {
    if (properties.indexOf(key) === -1) {
      result[key] = prop;
    }
  });
  return result;
}
/**
 * Recursively merge `...sources` into given target.
 *
 * Does support merging objects; does not support merging arrays.
 *
 * @param {Object} target
 * @param {...Object} sources
 *
 * @return {Object} the target
 */

function merge(target) {
  for (var _len2 = arguments.length, sources = new Array(_len2 > 1 ? _len2 - 1 : 0), _key2 = 1; _key2 < _len2; _key2++) {
    sources[_key2 - 1] = arguments[_key2];
  }

  if (!sources.length) {
    return target;
  }

  forEach(sources, function (source) {
    // skip non-obj sources, i.e. null
    if (!source || !isObject(source)) {
      return;
    }

    forEach(source, function (sourceVal, key) {
      if (key === '__proto__') {
        return;
      }

      var targetVal = target[key];

      if (isObject(sourceVal)) {
        if (!isObject(targetVal)) {
          // override target[key] with object
          targetVal = {};
        }

        target[key] = merge(targetVal, sourceVal);
      } else {
        target[key] = sourceVal;
      }
    });
  });
  return target;
}




/***/ }),

/***/ "./node_modules/min-dom/dist/index.esm.js":
/*!************************************************!*\
  !*** ./node_modules/min-dom/dist/index.esm.js ***!
  \************************************************/
/*! exports provided: attr, classes, clear, closest, delegate, domify, event, matches, query, queryAll, remove */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "attr", function() { return attr; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "classes", function() { return classes; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "clear", function() { return clear; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "closest", function() { return closest; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "delegate", function() { return delegate; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "domify", function() { return domify; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "event", function() { return componentEvent; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "matches", function() { return matchesSelector; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "query", function() { return query; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "queryAll", function() { return all; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "remove", function() { return remove; });
/**
 * Set attribute `name` to `val`, or get attr `name`.
 *
 * @param {Element} el
 * @param {String} name
 * @param {String} [val]
 * @api public
 */
function attr(el, name, val) {
  // get
  if (arguments.length == 2) {
    return el.getAttribute(name);
  }

  // remove
  if (val === null) {
    return el.removeAttribute(name);
  }

  // set
  el.setAttribute(name, val);

  return el;
}

var indexOf = [].indexOf;

var indexof = function(arr, obj){
  if (indexOf) return arr.indexOf(obj);
  for (var i = 0; i < arr.length; ++i) {
    if (arr[i] === obj) return i;
  }
  return -1;
};

/**
 * Taken from https://github.com/component/classes
 *
 * Without the component bits.
 */

/**
 * Whitespace regexp.
 */

var re = /\s+/;

/**
 * toString reference.
 */

var toString = Object.prototype.toString;

/**
 * Wrap `el` in a `ClassList`.
 *
 * @param {Element} el
 * @return {ClassList}
 * @api public
 */

function classes(el) {
  return new ClassList(el);
}

/**
 * Initialize a new ClassList for `el`.
 *
 * @param {Element} el
 * @api private
 */

function ClassList(el) {
  if (!el || !el.nodeType) {
    throw new Error('A DOM element reference is required');
  }
  this.el = el;
  this.list = el.classList;
}

/**
 * Add class `name` if not already present.
 *
 * @param {String} name
 * @return {ClassList}
 * @api public
 */

ClassList.prototype.add = function (name) {
  // classList
  if (this.list) {
    this.list.add(name);
    return this;
  }

  // fallback
  var arr = this.array();
  var i = indexof(arr, name);
  if (!~i) arr.push(name);
  this.el.className = arr.join(' ');
  return this;
};

/**
 * Remove class `name` when present, or
 * pass a regular expression to remove
 * any which match.
 *
 * @param {String|RegExp} name
 * @return {ClassList}
 * @api public
 */

ClassList.prototype.remove = function (name) {
  if ('[object RegExp]' == toString.call(name)) {
    return this.removeMatching(name);
  }

  // classList
  if (this.list) {
    this.list.remove(name);
    return this;
  }

  // fallback
  var arr = this.array();
  var i = indexof(arr, name);
  if (~i) arr.splice(i, 1);
  this.el.className = arr.join(' ');
  return this;
};

/**
 * Remove all classes matching `re`.
 *
 * @param {RegExp} re
 * @return {ClassList}
 * @api private
 */

ClassList.prototype.removeMatching = function (re) {
  var arr = this.array();
  for (var i = 0; i < arr.length; i++) {
    if (re.test(arr[i])) {
      this.remove(arr[i]);
    }
  }
  return this;
};

/**
 * Toggle class `name`, can force state via `force`.
 *
 * For browsers that support classList, but do not support `force` yet,
 * the mistake will be detected and corrected.
 *
 * @param {String} name
 * @param {Boolean} force
 * @return {ClassList}
 * @api public
 */

ClassList.prototype.toggle = function (name, force) {
  // classList
  if (this.list) {
    if ('undefined' !== typeof force) {
      if (force !== this.list.toggle(name, force)) {
        this.list.toggle(name); // toggle again to correct
      }
    } else {
      this.list.toggle(name);
    }
    return this;
  }

  // fallback
  if ('undefined' !== typeof force) {
    if (!force) {
      this.remove(name);
    } else {
      this.add(name);
    }
  } else {
    if (this.has(name)) {
      this.remove(name);
    } else {
      this.add(name);
    }
  }

  return this;
};

/**
 * Return an array of classes.
 *
 * @return {Array}
 * @api public
 */

ClassList.prototype.array = function () {
  var className = this.el.getAttribute('class') || '';
  var str = className.replace(/^\s+|\s+$/g, '');
  var arr = str.split(re);
  if ('' === arr[0]) arr.shift();
  return arr;
};

/**
 * Check if class `name` is present.
 *
 * @param {String} name
 * @return {ClassList}
 * @api public
 */

ClassList.prototype.has = ClassList.prototype.contains = function (name) {
  return this.list ? this.list.contains(name) : !!~indexof(this.array(), name);
};

/**
 * Remove all children from the given element.
 */
function clear(el) {

  var c;

  while (el.childNodes.length) {
    c = el.childNodes[0];
    el.removeChild(c);
  }

  return el;
}

var proto = typeof Element !== 'undefined' ? Element.prototype : {};
var vendor = proto.matches
  || proto.matchesSelector
  || proto.webkitMatchesSelector
  || proto.mozMatchesSelector
  || proto.msMatchesSelector
  || proto.oMatchesSelector;

var matchesSelector = match;

/**
 * Match `el` to `selector`.
 *
 * @param {Element} el
 * @param {String} selector
 * @return {Boolean}
 * @api public
 */

function match(el, selector) {
  if (!el || el.nodeType !== 1) return false;
  if (vendor) return vendor.call(el, selector);
  var nodes = el.parentNode.querySelectorAll(selector);
  for (var i = 0; i < nodes.length; i++) {
    if (nodes[i] == el) return true;
  }
  return false;
}

/**
 * Closest
 *
 * @param {Element} el
 * @param {String} selector
 * @param {Boolean} checkYourSelf (optional)
 */
function closest (element, selector, checkYourSelf) {
  var currentElem = checkYourSelf ? element : element.parentNode;

  while (currentElem && currentElem.nodeType !== document.DOCUMENT_NODE && currentElem.nodeType !== document.DOCUMENT_FRAGMENT_NODE) {

    if (matchesSelector(currentElem, selector)) {
      return currentElem;
    }

    currentElem = currentElem.parentNode;
  }

  return matchesSelector(currentElem, selector) ? currentElem : null;
}

var bind = window.addEventListener ? 'addEventListener' : 'attachEvent',
    unbind = window.removeEventListener ? 'removeEventListener' : 'detachEvent',
    prefix = bind !== 'addEventListener' ? 'on' : '';

/**
 * Bind `el` event `type` to `fn`.
 *
 * @param {Element} el
 * @param {String} type
 * @param {Function} fn
 * @param {Boolean} capture
 * @return {Function}
 * @api public
 */

var bind_1 = function(el, type, fn, capture){
  el[bind](prefix + type, fn, capture || false);
  return fn;
};

/**
 * Unbind `el` event `type`'s callback `fn`.
 *
 * @param {Element} el
 * @param {String} type
 * @param {Function} fn
 * @param {Boolean} capture
 * @return {Function}
 * @api public
 */

var unbind_1 = function(el, type, fn, capture){
  el[unbind](prefix + type, fn, capture || false);
  return fn;
};

var componentEvent = {
	bind: bind_1,
	unbind: unbind_1
};

/**
 * Module dependencies.
 */

/**
 * Delegate event `type` to `selector`
 * and invoke `fn(e)`. A callback function
 * is returned which may be passed to `.unbind()`.
 *
 * @param {Element} el
 * @param {String} selector
 * @param {String} type
 * @param {Function} fn
 * @param {Boolean} capture
 * @return {Function}
 * @api public
 */

// Some events don't bubble, so we want to bind to the capture phase instead
// when delegating.
var forceCaptureEvents = ['focus', 'blur'];

function bind$1(el, selector, type, fn, capture) {
  if (forceCaptureEvents.indexOf(type) !== -1) {
    capture = true;
  }

  return componentEvent.bind(el, type, function (e) {
    var target = e.target || e.srcElement;
    e.delegateTarget = closest(target, selector, true, el);
    if (e.delegateTarget) {
      fn.call(el, e);
    }
  }, capture);
}

/**
 * Unbind event `type`'s callback `fn`.
 *
 * @param {Element} el
 * @param {String} type
 * @param {Function} fn
 * @param {Boolean} capture
 * @api public
 */
function unbind$1(el, type, fn, capture) {
  if (forceCaptureEvents.indexOf(type) !== -1) {
    capture = true;
  }

  return componentEvent.unbind(el, type, fn, capture);
}

var delegate = {
  bind: bind$1,
  unbind: unbind$1
};

/**
 * Expose `parse`.
 */

var domify = parse;

/**
 * Tests for browser support.
 */

var innerHTMLBug = false;
var bugTestDiv;
if (typeof document !== 'undefined') {
  bugTestDiv = document.createElement('div');
  // Setup
  bugTestDiv.innerHTML = '  <link/><table></table><a href="/a">a</a><input type="checkbox"/>';
  // Make sure that link elements get serialized correctly by innerHTML
  // This requires a wrapper element in IE
  innerHTMLBug = !bugTestDiv.getElementsByTagName('link').length;
  bugTestDiv = undefined;
}

/**
 * Wrap map from jquery.
 */

var map = {
  legend: [1, '<fieldset>', '</fieldset>'],
  tr: [2, '<table><tbody>', '</tbody></table>'],
  col: [2, '<table><tbody></tbody><colgroup>', '</colgroup></table>'],
  // for script/link/style tags to work in IE6-8, you have to wrap
  // in a div with a non-whitespace character in front, ha!
  _default: innerHTMLBug ? [1, 'X<div>', '</div>'] : [0, '', '']
};

map.td =
map.th = [3, '<table><tbody><tr>', '</tr></tbody></table>'];

map.option =
map.optgroup = [1, '<select multiple="multiple">', '</select>'];

map.thead =
map.tbody =
map.colgroup =
map.caption =
map.tfoot = [1, '<table>', '</table>'];

map.polyline =
map.ellipse =
map.polygon =
map.circle =
map.text =
map.line =
map.path =
map.rect =
map.g = [1, '<svg xmlns="http://www.w3.org/2000/svg" version="1.1">','</svg>'];

/**
 * Parse `html` and return a DOM Node instance, which could be a TextNode,
 * HTML DOM Node of some kind (<div> for example), or a DocumentFragment
 * instance, depending on the contents of the `html` string.
 *
 * @param {String} html - HTML string to "domify"
 * @param {Document} doc - The `document` instance to create the Node for
 * @return {DOMNode} the TextNode, DOM Node, or DocumentFragment instance
 * @api private
 */

function parse(html, doc) {
  if ('string' != typeof html) throw new TypeError('String expected');

  // default to the global `document` object
  if (!doc) doc = document;

  // tag name
  var m = /<([\w:]+)/.exec(html);
  if (!m) return doc.createTextNode(html);

  html = html.replace(/^\s+|\s+$/g, ''); // Remove leading/trailing whitespace

  var tag = m[1];

  // body support
  if (tag == 'body') {
    var el = doc.createElement('html');
    el.innerHTML = html;
    return el.removeChild(el.lastChild);
  }

  // wrap map
  var wrap = map[tag] || map._default;
  var depth = wrap[0];
  var prefix = wrap[1];
  var suffix = wrap[2];
  var el = doc.createElement('div');
  el.innerHTML = prefix + html + suffix;
  while (depth--) el = el.lastChild;

  // one element
  if (el.firstChild == el.lastChild) {
    return el.removeChild(el.firstChild);
  }

  // several elements
  var fragment = doc.createDocumentFragment();
  while (el.firstChild) {
    fragment.appendChild(el.removeChild(el.firstChild));
  }

  return fragment;
}

function query(selector, el) {
  el = el || document;

  return el.querySelector(selector);
}

function all(selector, el) {
  el = el || document;

  return el.querySelectorAll(selector);
}

function remove(el) {
  el.parentNode && el.parentNode.removeChild(el);
}




/***/ }),

/***/ "./node_modules/node-libs-browser/node_modules/timers-browserify/main.js":
/*!*******************************************************************************!*\
  !*** ./node_modules/node-libs-browser/node_modules/timers-browserify/main.js ***!
  \*******************************************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

/* WEBPACK VAR INJECTION */(function(global) {var scope = (typeof global !== "undefined" && global) ||
            (typeof self !== "undefined" && self) ||
            window;
var apply = Function.prototype.apply;

// DOM APIs, for completeness

exports.setTimeout = function() {
  return new Timeout(apply.call(setTimeout, scope, arguments), clearTimeout);
};
exports.setInterval = function() {
  return new Timeout(apply.call(setInterval, scope, arguments), clearInterval);
};
exports.clearTimeout =
exports.clearInterval = function(timeout) {
  if (timeout) {
    timeout.close();
  }
};

function Timeout(id, clearFn) {
  this._id = id;
  this._clearFn = clearFn;
}
Timeout.prototype.unref = Timeout.prototype.ref = function() {};
Timeout.prototype.close = function() {
  this._clearFn.call(scope, this._id);
};

// Does not start the time, just sets up the members needed.
exports.enroll = function(item, msecs) {
  clearTimeout(item._idleTimeoutId);
  item._idleTimeout = msecs;
};

exports.unenroll = function(item) {
  clearTimeout(item._idleTimeoutId);
  item._idleTimeout = -1;
};

exports._unrefActive = exports.active = function(item) {
  clearTimeout(item._idleTimeoutId);

  var msecs = item._idleTimeout;
  if (msecs >= 0) {
    item._idleTimeoutId = setTimeout(function onTimeout() {
      if (item._onTimeout)
        item._onTimeout();
    }, msecs);
  }
};

// setimmediate attaches itself to the global object
__webpack_require__(/*! setimmediate */ "./node_modules/setimmediate/setImmediate.js");
// On some exotic environments, it's not clear which object `setimmediate` was
// able to install onto.  Search each possibility in the same order as the
// `setimmediate` library.
exports.setImmediate = (typeof self !== "undefined" && self.setImmediate) ||
                       (typeof global !== "undefined" && global.setImmediate) ||
                       (this && this.setImmediate);
exports.clearImmediate = (typeof self !== "undefined" && self.clearImmediate) ||
                         (typeof global !== "undefined" && global.clearImmediate) ||
                         (this && this.clearImmediate);

/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./../../../webpack/buildin/global.js */ "./node_modules/webpack/buildin/global.js")))

/***/ }),

/***/ "./node_modules/process/browser.js":
/*!*****************************************!*\
  !*** ./node_modules/process/browser.js ***!
  \*****************************************/
/*! no static exports found */
/***/ (function(module, exports) {

// shim for using process in browser
var process = module.exports = {};

// cached from whatever global is present so that test runners that stub it
// don't break things.  But we need to wrap it in a try catch in case it is
// wrapped in strict mode code which doesn't define any globals.  It's inside a
// function because try/catches deoptimize in certain engines.

var cachedSetTimeout;
var cachedClearTimeout;

function defaultSetTimout() {
    throw new Error('setTimeout has not been defined');
}
function defaultClearTimeout () {
    throw new Error('clearTimeout has not been defined');
}
(function () {
    try {
        if (typeof setTimeout === 'function') {
            cachedSetTimeout = setTimeout;
        } else {
            cachedSetTimeout = defaultSetTimout;
        }
    } catch (e) {
        cachedSetTimeout = defaultSetTimout;
    }
    try {
        if (typeof clearTimeout === 'function') {
            cachedClearTimeout = clearTimeout;
        } else {
            cachedClearTimeout = defaultClearTimeout;
        }
    } catch (e) {
        cachedClearTimeout = defaultClearTimeout;
    }
} ())
function runTimeout(fun) {
    if (cachedSetTimeout === setTimeout) {
        //normal enviroments in sane situations
        return setTimeout(fun, 0);
    }
    // if setTimeout wasn't available but was latter defined
    if ((cachedSetTimeout === defaultSetTimout || !cachedSetTimeout) && setTimeout) {
        cachedSetTimeout = setTimeout;
        return setTimeout(fun, 0);
    }
    try {
        // when when somebody has screwed with setTimeout but no I.E. maddness
        return cachedSetTimeout(fun, 0);
    } catch(e){
        try {
            // When we are in I.E. but the script has been evaled so I.E. doesn't trust the global object when called normally
            return cachedSetTimeout.call(null, fun, 0);
        } catch(e){
            // same as above but when it's a version of I.E. that must have the global object for 'this', hopfully our context correct otherwise it will throw a global error
            return cachedSetTimeout.call(this, fun, 0);
        }
    }


}
function runClearTimeout(marker) {
    if (cachedClearTimeout === clearTimeout) {
        //normal enviroments in sane situations
        return clearTimeout(marker);
    }
    // if clearTimeout wasn't available but was latter defined
    if ((cachedClearTimeout === defaultClearTimeout || !cachedClearTimeout) && clearTimeout) {
        cachedClearTimeout = clearTimeout;
        return clearTimeout(marker);
    }
    try {
        // when when somebody has screwed with setTimeout but no I.E. maddness
        return cachedClearTimeout(marker);
    } catch (e){
        try {
            // When we are in I.E. but the script has been evaled so I.E. doesn't  trust the global object when called normally
            return cachedClearTimeout.call(null, marker);
        } catch (e){
            // same as above but when it's a version of I.E. that must have the global object for 'this', hopfully our context correct otherwise it will throw a global error.
            // Some versions of I.E. have different rules for clearTimeout vs setTimeout
            return cachedClearTimeout.call(this, marker);
        }
    }



}
var queue = [];
var draining = false;
var currentQueue;
var queueIndex = -1;

function cleanUpNextTick() {
    if (!draining || !currentQueue) {
        return;
    }
    draining = false;
    if (currentQueue.length) {
        queue = currentQueue.concat(queue);
    } else {
        queueIndex = -1;
    }
    if (queue.length) {
        drainQueue();
    }
}

function drainQueue() {
    if (draining) {
        return;
    }
    var timeout = runTimeout(cleanUpNextTick);
    draining = true;

    var len = queue.length;
    while(len) {
        currentQueue = queue;
        queue = [];
        while (++queueIndex < len) {
            if (currentQueue) {
                currentQueue[queueIndex].run();
            }
        }
        queueIndex = -1;
        len = queue.length;
    }
    currentQueue = null;
    draining = false;
    runClearTimeout(timeout);
}

process.nextTick = function (fun) {
    var args = new Array(arguments.length - 1);
    if (arguments.length > 1) {
        for (var i = 1; i < arguments.length; i++) {
            args[i - 1] = arguments[i];
        }
    }
    queue.push(new Item(fun, args));
    if (queue.length === 1 && !draining) {
        runTimeout(drainQueue);
    }
};

// v8 likes predictible objects
function Item(fun, array) {
    this.fun = fun;
    this.array = array;
}
Item.prototype.run = function () {
    this.fun.apply(null, this.array);
};
process.title = 'browser';
process.browser = true;
process.env = {};
process.argv = [];
process.version = ''; // empty string to avoid regexp issues
process.versions = {};

function noop() {}

process.on = noop;
process.addListener = noop;
process.once = noop;
process.off = noop;
process.removeListener = noop;
process.removeAllListeners = noop;
process.emit = noop;
process.prependListener = noop;
process.prependOnceListener = noop;

process.listeners = function (name) { return [] }

process.binding = function (name) {
    throw new Error('process.binding is not supported');
};

process.cwd = function () { return '/' };
process.chdir = function (dir) {
    throw new Error('process.chdir is not supported');
};
process.umask = function() { return 0; };


/***/ }),

/***/ "./node_modules/selection-update/index.js":
/*!************************************************!*\
  !*** ./node_modules/selection-update/index.js ***!
  \************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


/**
 * Calculate the selection update for the given
 * current and new input values.
 *
 * @param {Object} currentSelection as {start, end}
 * @param {String} currentValue
 * @param {String} newValue
 *
 * @return {Object} newSelection as {start, end}
 */
function calculateUpdate(currentSelection, currentValue, newValue) {

  var currentCursor = currentSelection.start,
      newCursor = currentCursor,
      diff = newValue.length - currentValue.length,
      idx;

  var lengthDelta = newValue.length - currentValue.length;

  var currentTail = currentValue.substring(currentCursor);

  // check if we can remove common ending from the equation
  // to be able to properly detect a selection change for
  // the following scenarios:
  //
  //  * (AAATTT|TF) => (AAAT|TF)
  //  * (AAAT|TF) =>  (AAATTT|TF)
  //
  if (newValue.lastIndexOf(currentTail) === newValue.length - currentTail.length) {
    currentValue = currentValue.substring(0, currentValue.length - currentTail.length);
    newValue = newValue.substring(0, newValue.length - currentTail.length);
  }

  // diff
  var diff = createDiff(currentValue, newValue);

  if (diff) {
    if (diff.type === 'remove') {
      newCursor = diff.newStart;
    } else {
      newCursor = diff.newEnd;
    }
  }

  return range(newCursor);
}

module.exports = calculateUpdate;


function createDiff(currentValue, newValue) {

  var insert;

  var l_str, l_char, l_idx = 0,
      s_str, s_char, s_idx = 0;

  if (newValue.length > currentValue.length) {
    l_str = newValue;
    s_str = currentValue;
  } else {
    l_str = currentValue;
    s_str = newValue;
  }

  // assume there will be only one insert / remove and
  // detect that _first_ edit operation only
  while (l_idx < l_str.length) {

    l_char = l_str.charAt(l_idx);
    s_char = s_str.charAt(s_idx);

    // chars no not equal
    if (l_char !== s_char) {

      if (!insert) {
        insert = {
          l_start: l_idx,
          s_start: s_idx
        };
      }

      l_idx++;
    }

    // chars equal (again?)
    else {

      if (insert && !insert.complete) {
        insert.l_end = l_idx;
        insert.s_end = s_idx;
        insert.complete = true;
      }

      s_idx++;
      l_idx++;
    }
  }

  if (insert && !insert.complete) {
    insert.complete = true;
    insert.s_end = s_str.length;
    insert.l_end = l_str.length;
  }

  // no diff
  if (!insert) {
    return;
  }

  if (newValue.length > currentValue.length) {
    return {
      newStart: insert.l_start,
      newEnd: insert.l_end,
      type: 'add'
    };
  } else {
    return {
      newStart: insert.s_start,
      newEnd: insert.s_end,
      type: newValue.length < currentValue.length ? 'remove' : 'replace'
    };
  }
}

/**
 * Utility method for creating a new selection range {start, end} object.
 *
 * @param {Number} start
 * @param {Number} [end]
 *
 * @return {Object} selection range as {start, end}
 */
function range(start, end) {
  return {
    start: start,
    end: end === undefined ? start : end
  };
}

module.exports.range = range;


function splitStr(str, position) {
  return {
    before: str.substring(0, position),
    after: str.substring(position)
  };
}

/***/ }),

/***/ "./node_modules/setimmediate/setImmediate.js":
/*!***************************************************!*\
  !*** ./node_modules/setimmediate/setImmediate.js ***!
  \***************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

/* WEBPACK VAR INJECTION */(function(global, process) {(function (global, undefined) {
    "use strict";

    if (global.setImmediate) {
        return;
    }

    var nextHandle = 1; // Spec says greater than zero
    var tasksByHandle = {};
    var currentlyRunningATask = false;
    var doc = global.document;
    var registerImmediate;

    function setImmediate(callback) {
      // Callback can either be a function or a string
      if (typeof callback !== "function") {
        callback = new Function("" + callback);
      }
      // Copy function arguments
      var args = new Array(arguments.length - 1);
      for (var i = 0; i < args.length; i++) {
          args[i] = arguments[i + 1];
      }
      // Store and register the task
      var task = { callback: callback, args: args };
      tasksByHandle[nextHandle] = task;
      registerImmediate(nextHandle);
      return nextHandle++;
    }

    function clearImmediate(handle) {
        delete tasksByHandle[handle];
    }

    function run(task) {
        var callback = task.callback;
        var args = task.args;
        switch (args.length) {
        case 0:
            callback();
            break;
        case 1:
            callback(args[0]);
            break;
        case 2:
            callback(args[0], args[1]);
            break;
        case 3:
            callback(args[0], args[1], args[2]);
            break;
        default:
            callback.apply(undefined, args);
            break;
        }
    }

    function runIfPresent(handle) {
        // From the spec: "Wait until any invocations of this algorithm started before this one have completed."
        // So if we're currently running a task, we'll need to delay this invocation.
        if (currentlyRunningATask) {
            // Delay by doing a setTimeout. setImmediate was tried instead, but in Firefox 7 it generated a
            // "too much recursion" error.
            setTimeout(runIfPresent, 0, handle);
        } else {
            var task = tasksByHandle[handle];
            if (task) {
                currentlyRunningATask = true;
                try {
                    run(task);
                } finally {
                    clearImmediate(handle);
                    currentlyRunningATask = false;
                }
            }
        }
    }

    function installNextTickImplementation() {
        registerImmediate = function(handle) {
            process.nextTick(function () { runIfPresent(handle); });
        };
    }

    function canUsePostMessage() {
        // The test against `importScripts` prevents this implementation from being installed inside a web worker,
        // where `global.postMessage` means something completely different and can't be used for this purpose.
        if (global.postMessage && !global.importScripts) {
            var postMessageIsAsynchronous = true;
            var oldOnMessage = global.onmessage;
            global.onmessage = function() {
                postMessageIsAsynchronous = false;
            };
            global.postMessage("", "*");
            global.onmessage = oldOnMessage;
            return postMessageIsAsynchronous;
        }
    }

    function installPostMessageImplementation() {
        // Installs an event handler on `global` for the `message` event: see
        // * https://developer.mozilla.org/en/DOM/window.postMessage
        // * http://www.whatwg.org/specs/web-apps/current-work/multipage/comms.html#crossDocumentMessages

        var messagePrefix = "setImmediate$" + Math.random() + "$";
        var onGlobalMessage = function(event) {
            if (event.source === global &&
                typeof event.data === "string" &&
                event.data.indexOf(messagePrefix) === 0) {
                runIfPresent(+event.data.slice(messagePrefix.length));
            }
        };

        if (global.addEventListener) {
            global.addEventListener("message", onGlobalMessage, false);
        } else {
            global.attachEvent("onmessage", onGlobalMessage);
        }

        registerImmediate = function(handle) {
            global.postMessage(messagePrefix + handle, "*");
        };
    }

    function installMessageChannelImplementation() {
        var channel = new MessageChannel();
        channel.port1.onmessage = function(event) {
            var handle = event.data;
            runIfPresent(handle);
        };

        registerImmediate = function(handle) {
            channel.port2.postMessage(handle);
        };
    }

    function installReadyStateChangeImplementation() {
        var html = doc.documentElement;
        registerImmediate = function(handle) {
            // Create a <script> element; its readystatechange event will be fired asynchronously once it is inserted
            // into the document. Do so, thus queuing up the task. Remember to clean up once it's been called.
            var script = doc.createElement("script");
            script.onreadystatechange = function () {
                runIfPresent(handle);
                script.onreadystatechange = null;
                html.removeChild(script);
                script = null;
            };
            html.appendChild(script);
        };
    }

    function installSetTimeoutImplementation() {
        registerImmediate = function(handle) {
            setTimeout(runIfPresent, 0, handle);
        };
    }

    // If supported, we should attach to the prototype of global, since that is where setTimeout et al. live.
    var attachTo = Object.getPrototypeOf && Object.getPrototypeOf(global);
    attachTo = attachTo && attachTo.setTimeout ? attachTo : global;

    // Don't get fooled by e.g. browserify environments.
    if ({}.toString.call(global.process) === "[object process]") {
        // For Node.js before 0.9
        installNextTickImplementation();

    } else if (canUsePostMessage()) {
        // For non-IE10 modern browsers
        installPostMessageImplementation();

    } else if (global.MessageChannel) {
        // For web workers, where supported
        installMessageChannelImplementation();

    } else if (doc && "onreadystatechange" in doc.createElement("script")) {
        // For IE 6–8
        installReadyStateChangeImplementation();

    } else {
        // For older browsers
        installSetTimeoutImplementation();
    }

    attachTo.setImmediate = setImmediate;
    attachTo.clearImmediate = clearImmediate;
}(typeof self === "undefined" ? typeof global === "undefined" ? this : global : self));

/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./../webpack/buildin/global.js */ "./node_modules/webpack/buildin/global.js"), __webpack_require__(/*! ./../process/browser.js */ "./node_modules/process/browser.js")))

/***/ }),

/***/ "./node_modules/sweetalert/dist/sweetalert.min.js":
/*!********************************************************!*\
  !*** ./node_modules/sweetalert/dist/sweetalert.min.js ***!
  \********************************************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

/* WEBPACK VAR INJECTION */(function(setImmediate, clearImmediate) {!function(t,e){ true?module.exports=e():undefined}(this,function(){return function(t){function e(o){if(n[o])return n[o].exports;var r=n[o]={i:o,l:!1,exports:{}};return t[o].call(r.exports,r,r.exports,e),r.l=!0,r.exports}var n={};return e.m=t,e.c=n,e.d=function(t,n,o){e.o(t,n)||Object.defineProperty(t,n,{configurable:!1,enumerable:!0,get:o})},e.n=function(t){var n=t&&t.__esModule?function(){return t.default}:function(){return t};return e.d(n,"a",n),n},e.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},e.p="",e(e.s=8)}([function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o="swal-button";e.CLASS_NAMES={MODAL:"swal-modal",OVERLAY:"swal-overlay",SHOW_MODAL:"swal-overlay--show-modal",MODAL_TITLE:"swal-title",MODAL_TEXT:"swal-text",ICON:"swal-icon",ICON_CUSTOM:"swal-icon--custom",CONTENT:"swal-content",FOOTER:"swal-footer",BUTTON_CONTAINER:"swal-button-container",BUTTON:o,CONFIRM_BUTTON:o+"--confirm",CANCEL_BUTTON:o+"--cancel",DANGER_BUTTON:o+"--danger",BUTTON_LOADING:o+"--loading",BUTTON_LOADER:o+"__loader"},e.default=e.CLASS_NAMES},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.getNode=function(t){var e="."+t;return document.querySelector(e)},e.stringToNode=function(t){var e=document.createElement("div");return e.innerHTML=t.trim(),e.firstChild},e.insertAfter=function(t,e){var n=e.nextSibling;e.parentNode.insertBefore(t,n)},e.removeNode=function(t){t.parentElement.removeChild(t)},e.throwErr=function(t){throw t=t.replace(/ +(?= )/g,""),"SweetAlert: "+(t=t.trim())},e.isPlainObject=function(t){if("[object Object]"!==Object.prototype.toString.call(t))return!1;var e=Object.getPrototypeOf(t);return null===e||e===Object.prototype},e.ordinalSuffixOf=function(t){var e=t%10,n=t%100;return 1===e&&11!==n?t+"st":2===e&&12!==n?t+"nd":3===e&&13!==n?t+"rd":t+"th"}},function(t,e,n){"use strict";function o(t){for(var n in t)e.hasOwnProperty(n)||(e[n]=t[n])}Object.defineProperty(e,"__esModule",{value:!0}),o(n(25));var r=n(26);e.overlayMarkup=r.default,o(n(27)),o(n(28)),o(n(29));var i=n(0),a=i.default.MODAL_TITLE,s=i.default.MODAL_TEXT,c=i.default.ICON,l=i.default.FOOTER;e.iconMarkup='\n  <div class="'+c+'"></div>',e.titleMarkup='\n  <div class="'+a+'"></div>\n',e.textMarkup='\n  <div class="'+s+'"></div>',e.footerMarkup='\n  <div class="'+l+'"></div>\n'},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(1);e.CONFIRM_KEY="confirm",e.CANCEL_KEY="cancel";var r={visible:!0,text:null,value:null,className:"",closeModal:!0},i=Object.assign({},r,{visible:!1,text:"Cancel",value:null}),a=Object.assign({},r,{text:"OK",value:!0});e.defaultButtonList={cancel:i,confirm:a};var s=function(t){switch(t){case e.CONFIRM_KEY:return a;case e.CANCEL_KEY:return i;default:var n=t.charAt(0).toUpperCase()+t.slice(1);return Object.assign({},r,{text:n,value:t})}},c=function(t,e){var n=s(t);return!0===e?Object.assign({},n,{visible:!0}):"string"==typeof e?Object.assign({},n,{visible:!0,text:e}):o.isPlainObject(e)?Object.assign({visible:!0},n,e):Object.assign({},n,{visible:!1})},l=function(t){for(var e={},n=0,o=Object.keys(t);n<o.length;n++){var r=o[n],a=t[r],s=c(r,a);e[r]=s}return e.cancel||(e.cancel=i),e},u=function(t){var n={};switch(t.length){case 1:n[e.CANCEL_KEY]=Object.assign({},i,{visible:!1});break;case 2:n[e.CANCEL_KEY]=c(e.CANCEL_KEY,t[0]),n[e.CONFIRM_KEY]=c(e.CONFIRM_KEY,t[1]);break;default:o.throwErr("Invalid number of 'buttons' in array ("+t.length+").\n      If you want more than 2 buttons, you need to use an object!")}return n};e.getButtonListOpts=function(t){var n=e.defaultButtonList;return"string"==typeof t?n[e.CONFIRM_KEY]=c(e.CONFIRM_KEY,t):Array.isArray(t)?n=u(t):o.isPlainObject(t)?n=l(t):!0===t?n=u([!0,!0]):!1===t?n=u([!1,!1]):void 0===t&&(n=e.defaultButtonList),n}},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(1),r=n(2),i=n(0),a=i.default.MODAL,s=i.default.OVERLAY,c=n(30),l=n(31),u=n(32),f=n(33);e.injectElIntoModal=function(t){var e=o.getNode(a),n=o.stringToNode(t);return e.appendChild(n),n};var d=function(t){t.className=a,t.textContent=""},p=function(t,e){d(t);var n=e.className;n&&t.classList.add(n)};e.initModalContent=function(t){var e=o.getNode(a);p(e,t),c.default(t.icon),l.initTitle(t.title),l.initText(t.text),f.default(t.content),u.default(t.buttons,t.dangerMode)};var m=function(){var t=o.getNode(s),e=o.stringToNode(r.modalMarkup);t.appendChild(e)};e.default=m},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(3),r={isOpen:!1,promise:null,actions:{},timer:null},i=Object.assign({},r);e.resetState=function(){i=Object.assign({},r)},e.setActionValue=function(t){if("string"==typeof t)return a(o.CONFIRM_KEY,t);for(var e in t)a(e,t[e])};var a=function(t,e){i.actions[t]||(i.actions[t]={}),Object.assign(i.actions[t],{value:e})};e.setActionOptionsFor=function(t,e){var n=(void 0===e?{}:e).closeModal,o=void 0===n||n;Object.assign(i.actions[t],{closeModal:o})},e.default=i},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(1),r=n(3),i=n(0),a=i.default.OVERLAY,s=i.default.SHOW_MODAL,c=i.default.BUTTON,l=i.default.BUTTON_LOADING,u=n(5);e.openModal=function(){o.getNode(a).classList.add(s),u.default.isOpen=!0};var f=function(){o.getNode(a).classList.remove(s),u.default.isOpen=!1};e.onAction=function(t){void 0===t&&(t=r.CANCEL_KEY);var e=u.default.actions[t],n=e.value;if(!1===e.closeModal){var i=c+"--"+t;o.getNode(i).classList.add(l)}else f();u.default.promise.resolve(n)},e.getState=function(){var t=Object.assign({},u.default);return delete t.promise,delete t.timer,t},e.stopLoading=function(){for(var t=document.querySelectorAll("."+c),e=0;e<t.length;e++){t[e].classList.remove(l)}}},function(t,e){var n;n=function(){return this}();try{n=n||Function("return this")()||(0,eval)("this")}catch(t){"object"==typeof window&&(n=window)}t.exports=n},function(t,e,n){(function(e){t.exports=e.sweetAlert=n(9)}).call(e,n(7))},function(t,e,n){(function(e){t.exports=e.swal=n(10)}).call(e,n(7))},function(t,e,n){"undefined"!=typeof window&&n(11),n(16);var o=n(23).default;t.exports=o},function(t,e,n){var o=n(12);"string"==typeof o&&(o=[[t.i,o,""]]);var r={insertAt:"top"};r.transform=void 0;n(14)(o,r);o.locals&&(t.exports=o.locals)},function(t,e,n){e=t.exports=n(13)(void 0),e.push([t.i,'.swal-icon--error{border-color:#f27474;-webkit-animation:animateErrorIcon .5s;animation:animateErrorIcon .5s}.swal-icon--error__x-mark{position:relative;display:block;-webkit-animation:animateXMark .5s;animation:animateXMark .5s}.swal-icon--error__line{position:absolute;height:5px;width:47px;background-color:#f27474;display:block;top:37px;border-radius:2px}.swal-icon--error__line--left{-webkit-transform:rotate(45deg);transform:rotate(45deg);left:17px}.swal-icon--error__line--right{-webkit-transform:rotate(-45deg);transform:rotate(-45deg);right:16px}@-webkit-keyframes animateErrorIcon{0%{-webkit-transform:rotateX(100deg);transform:rotateX(100deg);opacity:0}to{-webkit-transform:rotateX(0deg);transform:rotateX(0deg);opacity:1}}@keyframes animateErrorIcon{0%{-webkit-transform:rotateX(100deg);transform:rotateX(100deg);opacity:0}to{-webkit-transform:rotateX(0deg);transform:rotateX(0deg);opacity:1}}@-webkit-keyframes animateXMark{0%{-webkit-transform:scale(.4);transform:scale(.4);margin-top:26px;opacity:0}50%{-webkit-transform:scale(.4);transform:scale(.4);margin-top:26px;opacity:0}80%{-webkit-transform:scale(1.15);transform:scale(1.15);margin-top:-6px}to{-webkit-transform:scale(1);transform:scale(1);margin-top:0;opacity:1}}@keyframes animateXMark{0%{-webkit-transform:scale(.4);transform:scale(.4);margin-top:26px;opacity:0}50%{-webkit-transform:scale(.4);transform:scale(.4);margin-top:26px;opacity:0}80%{-webkit-transform:scale(1.15);transform:scale(1.15);margin-top:-6px}to{-webkit-transform:scale(1);transform:scale(1);margin-top:0;opacity:1}}.swal-icon--warning{border-color:#f8bb86;-webkit-animation:pulseWarning .75s infinite alternate;animation:pulseWarning .75s infinite alternate}.swal-icon--warning__body{width:5px;height:47px;top:10px;border-radius:2px;margin-left:-2px}.swal-icon--warning__body,.swal-icon--warning__dot{position:absolute;left:50%;background-color:#f8bb86}.swal-icon--warning__dot{width:7px;height:7px;border-radius:50%;margin-left:-4px;bottom:-11px}@-webkit-keyframes pulseWarning{0%{border-color:#f8d486}to{border-color:#f8bb86}}@keyframes pulseWarning{0%{border-color:#f8d486}to{border-color:#f8bb86}}.swal-icon--success{border-color:#a5dc86}.swal-icon--success:after,.swal-icon--success:before{content:"";border-radius:50%;position:absolute;width:60px;height:120px;background:#fff;-webkit-transform:rotate(45deg);transform:rotate(45deg)}.swal-icon--success:before{border-radius:120px 0 0 120px;top:-7px;left:-33px;-webkit-transform:rotate(-45deg);transform:rotate(-45deg);-webkit-transform-origin:60px 60px;transform-origin:60px 60px}.swal-icon--success:after{border-radius:0 120px 120px 0;top:-11px;left:30px;-webkit-transform:rotate(-45deg);transform:rotate(-45deg);-webkit-transform-origin:0 60px;transform-origin:0 60px;-webkit-animation:rotatePlaceholder 4.25s ease-in;animation:rotatePlaceholder 4.25s ease-in}.swal-icon--success__ring{width:80px;height:80px;border:4px solid hsla(98,55%,69%,.2);border-radius:50%;box-sizing:content-box;position:absolute;left:-4px;top:-4px;z-index:2}.swal-icon--success__hide-corners{width:5px;height:90px;background-color:#fff;padding:1px;position:absolute;left:28px;top:8px;z-index:1;-webkit-transform:rotate(-45deg);transform:rotate(-45deg)}.swal-icon--success__line{height:5px;background-color:#a5dc86;display:block;border-radius:2px;position:absolute;z-index:2}.swal-icon--success__line--tip{width:25px;left:14px;top:46px;-webkit-transform:rotate(45deg);transform:rotate(45deg);-webkit-animation:animateSuccessTip .75s;animation:animateSuccessTip .75s}.swal-icon--success__line--long{width:47px;right:8px;top:38px;-webkit-transform:rotate(-45deg);transform:rotate(-45deg);-webkit-animation:animateSuccessLong .75s;animation:animateSuccessLong .75s}@-webkit-keyframes rotatePlaceholder{0%{-webkit-transform:rotate(-45deg);transform:rotate(-45deg)}5%{-webkit-transform:rotate(-45deg);transform:rotate(-45deg)}12%{-webkit-transform:rotate(-405deg);transform:rotate(-405deg)}to{-webkit-transform:rotate(-405deg);transform:rotate(-405deg)}}@keyframes rotatePlaceholder{0%{-webkit-transform:rotate(-45deg);transform:rotate(-45deg)}5%{-webkit-transform:rotate(-45deg);transform:rotate(-45deg)}12%{-webkit-transform:rotate(-405deg);transform:rotate(-405deg)}to{-webkit-transform:rotate(-405deg);transform:rotate(-405deg)}}@-webkit-keyframes animateSuccessTip{0%{width:0;left:1px;top:19px}54%{width:0;left:1px;top:19px}70%{width:50px;left:-8px;top:37px}84%{width:17px;left:21px;top:48px}to{width:25px;left:14px;top:45px}}@keyframes animateSuccessTip{0%{width:0;left:1px;top:19px}54%{width:0;left:1px;top:19px}70%{width:50px;left:-8px;top:37px}84%{width:17px;left:21px;top:48px}to{width:25px;left:14px;top:45px}}@-webkit-keyframes animateSuccessLong{0%{width:0;right:46px;top:54px}65%{width:0;right:46px;top:54px}84%{width:55px;right:0;top:35px}to{width:47px;right:8px;top:38px}}@keyframes animateSuccessLong{0%{width:0;right:46px;top:54px}65%{width:0;right:46px;top:54px}84%{width:55px;right:0;top:35px}to{width:47px;right:8px;top:38px}}.swal-icon--info{border-color:#c9dae1}.swal-icon--info:before{width:5px;height:29px;bottom:17px;border-radius:2px;margin-left:-2px}.swal-icon--info:after,.swal-icon--info:before{content:"";position:absolute;left:50%;background-color:#c9dae1}.swal-icon--info:after{width:7px;height:7px;border-radius:50%;margin-left:-3px;top:19px}.swal-icon{width:80px;height:80px;border-width:4px;border-style:solid;border-radius:50%;padding:0;position:relative;box-sizing:content-box;margin:20px auto}.swal-icon:first-child{margin-top:32px}.swal-icon--custom{width:auto;height:auto;max-width:100%;border:none;border-radius:0}.swal-icon img{max-width:100%;max-height:100%}.swal-title{color:rgba(0,0,0,.65);font-weight:600;text-transform:none;position:relative;display:block;padding:13px 16px;font-size:27px;line-height:normal;text-align:center;margin-bottom:0}.swal-title:first-child{margin-top:26px}.swal-title:not(:first-child){padding-bottom:0}.swal-title:not(:last-child){margin-bottom:13px}.swal-text{font-size:16px;position:relative;float:none;line-height:normal;vertical-align:top;text-align:left;display:inline-block;margin:0;padding:0 10px;font-weight:400;color:rgba(0,0,0,.64);max-width:calc(100% - 20px);overflow-wrap:break-word;box-sizing:border-box}.swal-text:first-child{margin-top:45px}.swal-text:last-child{margin-bottom:45px}.swal-footer{text-align:right;padding-top:13px;margin-top:13px;padding:13px 16px;border-radius:inherit;border-top-left-radius:0;border-top-right-radius:0}.swal-button-container{margin:5px;display:inline-block;position:relative}.swal-button{background-color:#7cd1f9;color:#fff;border:none;box-shadow:none;border-radius:5px;font-weight:600;font-size:14px;padding:10px 24px;margin:0;cursor:pointer}.swal-button:not([disabled]):hover{background-color:#78cbf2}.swal-button:active{background-color:#70bce0}.swal-button:focus{outline:none;box-shadow:0 0 0 1px #fff,0 0 0 3px rgba(43,114,165,.29)}.swal-button[disabled]{opacity:.5;cursor:default}.swal-button::-moz-focus-inner{border:0}.swal-button--cancel{color:#555;background-color:#efefef}.swal-button--cancel:not([disabled]):hover{background-color:#e8e8e8}.swal-button--cancel:active{background-color:#d7d7d7}.swal-button--cancel:focus{box-shadow:0 0 0 1px #fff,0 0 0 3px rgba(116,136,150,.29)}.swal-button--danger{background-color:#e64942}.swal-button--danger:not([disabled]):hover{background-color:#df4740}.swal-button--danger:active{background-color:#cf423b}.swal-button--danger:focus{box-shadow:0 0 0 1px #fff,0 0 0 3px rgba(165,43,43,.29)}.swal-content{padding:0 20px;margin-top:20px;font-size:medium}.swal-content:last-child{margin-bottom:20px}.swal-content__input,.swal-content__textarea{-webkit-appearance:none;background-color:#fff;border:none;font-size:14px;display:block;box-sizing:border-box;width:100%;border:1px solid rgba(0,0,0,.14);padding:10px 13px;border-radius:2px;transition:border-color .2s}.swal-content__input:focus,.swal-content__textarea:focus{outline:none;border-color:#6db8ff}.swal-content__textarea{resize:vertical}.swal-button--loading{color:transparent}.swal-button--loading~.swal-button__loader{opacity:1}.swal-button__loader{position:absolute;height:auto;width:43px;z-index:2;left:50%;top:50%;-webkit-transform:translateX(-50%) translateY(-50%);transform:translateX(-50%) translateY(-50%);text-align:center;pointer-events:none;opacity:0}.swal-button__loader div{display:inline-block;float:none;vertical-align:baseline;width:9px;height:9px;padding:0;border:none;margin:2px;opacity:.4;border-radius:7px;background-color:hsla(0,0%,100%,.9);transition:background .2s;-webkit-animation:swal-loading-anim 1s infinite;animation:swal-loading-anim 1s infinite}.swal-button__loader div:nth-child(3n+2){-webkit-animation-delay:.15s;animation-delay:.15s}.swal-button__loader div:nth-child(3n+3){-webkit-animation-delay:.3s;animation-delay:.3s}@-webkit-keyframes swal-loading-anim{0%{opacity:.4}20%{opacity:.4}50%{opacity:1}to{opacity:.4}}@keyframes swal-loading-anim{0%{opacity:.4}20%{opacity:.4}50%{opacity:1}to{opacity:.4}}.swal-overlay{position:fixed;top:0;bottom:0;left:0;right:0;text-align:center;font-size:0;overflow-y:auto;background-color:rgba(0,0,0,.4);z-index:10000;pointer-events:none;opacity:0;transition:opacity .3s}.swal-overlay:before{content:" ";display:inline-block;vertical-align:middle;height:100%}.swal-overlay--show-modal{opacity:1;pointer-events:auto}.swal-overlay--show-modal .swal-modal{opacity:1;pointer-events:auto;box-sizing:border-box;-webkit-animation:showSweetAlert .3s;animation:showSweetAlert .3s;will-change:transform}.swal-modal{width:478px;opacity:0;pointer-events:none;background-color:#fff;text-align:center;border-radius:5px;position:static;margin:20px auto;display:inline-block;vertical-align:middle;-webkit-transform:scale(1);transform:scale(1);-webkit-transform-origin:50% 50%;transform-origin:50% 50%;z-index:10001;transition:opacity .2s,-webkit-transform .3s;transition:transform .3s,opacity .2s;transition:transform .3s,opacity .2s,-webkit-transform .3s}@media (max-width:500px){.swal-modal{width:calc(100% - 20px)}}@-webkit-keyframes showSweetAlert{0%{-webkit-transform:scale(1);transform:scale(1)}1%{-webkit-transform:scale(.5);transform:scale(.5)}45%{-webkit-transform:scale(1.05);transform:scale(1.05)}80%{-webkit-transform:scale(.95);transform:scale(.95)}to{-webkit-transform:scale(1);transform:scale(1)}}@keyframes showSweetAlert{0%{-webkit-transform:scale(1);transform:scale(1)}1%{-webkit-transform:scale(.5);transform:scale(.5)}45%{-webkit-transform:scale(1.05);transform:scale(1.05)}80%{-webkit-transform:scale(.95);transform:scale(.95)}to{-webkit-transform:scale(1);transform:scale(1)}}',""])},function(t,e){function n(t,e){var n=t[1]||"",r=t[3];if(!r)return n;if(e&&"function"==typeof btoa){var i=o(r);return[n].concat(r.sources.map(function(t){return"/*# sourceURL="+r.sourceRoot+t+" */"})).concat([i]).join("\n")}return[n].join("\n")}function o(t){return"/*# sourceMappingURL=data:application/json;charset=utf-8;base64,"+btoa(unescape(encodeURIComponent(JSON.stringify(t))))+" */"}t.exports=function(t){var e=[];return e.toString=function(){return this.map(function(e){var o=n(e,t);return e[2]?"@media "+e[2]+"{"+o+"}":o}).join("")},e.i=function(t,n){"string"==typeof t&&(t=[[null,t,""]]);for(var o={},r=0;r<this.length;r++){var i=this[r][0];"number"==typeof i&&(o[i]=!0)}for(r=0;r<t.length;r++){var a=t[r];"number"==typeof a[0]&&o[a[0]]||(n&&!a[2]?a[2]=n:n&&(a[2]="("+a[2]+") and ("+n+")"),e.push(a))}},e}},function(t,e,n){function o(t,e){for(var n=0;n<t.length;n++){var o=t[n],r=m[o.id];if(r){r.refs++;for(var i=0;i<r.parts.length;i++)r.parts[i](o.parts[i]);for(;i<o.parts.length;i++)r.parts.push(u(o.parts[i],e))}else{for(var a=[],i=0;i<o.parts.length;i++)a.push(u(o.parts[i],e));m[o.id]={id:o.id,refs:1,parts:a}}}}function r(t,e){for(var n=[],o={},r=0;r<t.length;r++){var i=t[r],a=e.base?i[0]+e.base:i[0],s=i[1],c=i[2],l=i[3],u={css:s,media:c,sourceMap:l};o[a]?o[a].parts.push(u):n.push(o[a]={id:a,parts:[u]})}return n}function i(t,e){var n=v(t.insertInto);if(!n)throw new Error("Couldn't find a style target. This probably means that the value for the 'insertInto' parameter is invalid.");var o=w[w.length-1];if("top"===t.insertAt)o?o.nextSibling?n.insertBefore(e,o.nextSibling):n.appendChild(e):n.insertBefore(e,n.firstChild),w.push(e);else{if("bottom"!==t.insertAt)throw new Error("Invalid value for parameter 'insertAt'. Must be 'top' or 'bottom'.");n.appendChild(e)}}function a(t){if(null===t.parentNode)return!1;t.parentNode.removeChild(t);var e=w.indexOf(t);e>=0&&w.splice(e,1)}function s(t){var e=document.createElement("style");return t.attrs.type="text/css",l(e,t.attrs),i(t,e),e}function c(t){var e=document.createElement("link");return t.attrs.type="text/css",t.attrs.rel="stylesheet",l(e,t.attrs),i(t,e),e}function l(t,e){Object.keys(e).forEach(function(n){t.setAttribute(n,e[n])})}function u(t,e){var n,o,r,i;if(e.transform&&t.css){if(!(i=e.transform(t.css)))return function(){};t.css=i}if(e.singleton){var l=h++;n=g||(g=s(e)),o=f.bind(null,n,l,!1),r=f.bind(null,n,l,!0)}else t.sourceMap&&"function"==typeof URL&&"function"==typeof URL.createObjectURL&&"function"==typeof URL.revokeObjectURL&&"function"==typeof Blob&&"function"==typeof btoa?(n=c(e),o=p.bind(null,n,e),r=function(){a(n),n.href&&URL.revokeObjectURL(n.href)}):(n=s(e),o=d.bind(null,n),r=function(){a(n)});return o(t),function(e){if(e){if(e.css===t.css&&e.media===t.media&&e.sourceMap===t.sourceMap)return;o(t=e)}else r()}}function f(t,e,n,o){var r=n?"":o.css;if(t.styleSheet)t.styleSheet.cssText=x(e,r);else{var i=document.createTextNode(r),a=t.childNodes;a[e]&&t.removeChild(a[e]),a.length?t.insertBefore(i,a[e]):t.appendChild(i)}}function d(t,e){var n=e.css,o=e.media;if(o&&t.setAttribute("media",o),t.styleSheet)t.styleSheet.cssText=n;else{for(;t.firstChild;)t.removeChild(t.firstChild);t.appendChild(document.createTextNode(n))}}function p(t,e,n){var o=n.css,r=n.sourceMap,i=void 0===e.convertToAbsoluteUrls&&r;(e.convertToAbsoluteUrls||i)&&(o=y(o)),r&&(o+="\n/*# sourceMappingURL=data:application/json;base64,"+btoa(unescape(encodeURIComponent(JSON.stringify(r))))+" */");var a=new Blob([o],{type:"text/css"}),s=t.href;t.href=URL.createObjectURL(a),s&&URL.revokeObjectURL(s)}var m={},b=function(t){var e;return function(){return void 0===e&&(e=t.apply(this,arguments)),e}}(function(){return window&&document&&document.all&&!window.atob}),v=function(t){var e={};return function(n){return void 0===e[n]&&(e[n]=t.call(this,n)),e[n]}}(function(t){return document.querySelector(t)}),g=null,h=0,w=[],y=n(15);t.exports=function(t,e){if("undefined"!=typeof DEBUG&&DEBUG&&"object"!=typeof document)throw new Error("The style-loader cannot be used in a non-browser environment");e=e||{},e.attrs="object"==typeof e.attrs?e.attrs:{},e.singleton||(e.singleton=b()),e.insertInto||(e.insertInto="head"),e.insertAt||(e.insertAt="bottom");var n=r(t,e);return o(n,e),function(t){for(var i=[],a=0;a<n.length;a++){var s=n[a],c=m[s.id];c.refs--,i.push(c)}if(t){o(r(t,e),e)}for(var a=0;a<i.length;a++){var c=i[a];if(0===c.refs){for(var l=0;l<c.parts.length;l++)c.parts[l]();delete m[c.id]}}}};var x=function(){var t=[];return function(e,n){return t[e]=n,t.filter(Boolean).join("\n")}}()},function(t,e){t.exports=function(t){var e="undefined"!=typeof window&&window.location;if(!e)throw new Error("fixUrls requires window.location");if(!t||"string"!=typeof t)return t;var n=e.protocol+"//"+e.host,o=n+e.pathname.replace(/\/[^\/]*$/,"/");return t.replace(/url\s*\(((?:[^)(]|\((?:[^)(]+|\([^)(]*\))*\))*)\)/gi,function(t,e){var r=e.trim().replace(/^"(.*)"$/,function(t,e){return e}).replace(/^'(.*)'$/,function(t,e){return e});if(/^(#|data:|http:\/\/|https:\/\/|file:\/\/\/)/i.test(r))return t;var i;return i=0===r.indexOf("//")?r:0===r.indexOf("/")?n+r:o+r.replace(/^\.\//,""),"url("+JSON.stringify(i)+")"})}},function(t,e,n){var o=n(17);"undefined"==typeof window||window.Promise||(window.Promise=o),n(21),String.prototype.includes||(String.prototype.includes=function(t,e){"use strict";return"number"!=typeof e&&(e=0),!(e+t.length>this.length)&&-1!==this.indexOf(t,e)}),Array.prototype.includes||Object.defineProperty(Array.prototype,"includes",{value:function(t,e){if(null==this)throw new TypeError('"this" is null or not defined');var n=Object(this),o=n.length>>>0;if(0===o)return!1;for(var r=0|e,i=Math.max(r>=0?r:o-Math.abs(r),0);i<o;){if(function(t,e){return t===e||"number"==typeof t&&"number"==typeof e&&isNaN(t)&&isNaN(e)}(n[i],t))return!0;i++}return!1}}),"undefined"!=typeof window&&function(t){t.forEach(function(t){t.hasOwnProperty("remove")||Object.defineProperty(t,"remove",{configurable:!0,enumerable:!0,writable:!0,value:function(){this.parentNode.removeChild(this)}})})}([Element.prototype,CharacterData.prototype,DocumentType.prototype])},function(t,e,n){(function(e){!function(n){function o(){}function r(t,e){return function(){t.apply(e,arguments)}}function i(t){if("object"!=typeof this)throw new TypeError("Promises must be constructed via new");if("function"!=typeof t)throw new TypeError("not a function");this._state=0,this._handled=!1,this._value=void 0,this._deferreds=[],f(t,this)}function a(t,e){for(;3===t._state;)t=t._value;if(0===t._state)return void t._deferreds.push(e);t._handled=!0,i._immediateFn(function(){var n=1===t._state?e.onFulfilled:e.onRejected;if(null===n)return void(1===t._state?s:c)(e.promise,t._value);var o;try{o=n(t._value)}catch(t){return void c(e.promise,t)}s(e.promise,o)})}function s(t,e){try{if(e===t)throw new TypeError("A promise cannot be resolved with itself.");if(e&&("object"==typeof e||"function"==typeof e)){var n=e.then;if(e instanceof i)return t._state=3,t._value=e,void l(t);if("function"==typeof n)return void f(r(n,e),t)}t._state=1,t._value=e,l(t)}catch(e){c(t,e)}}function c(t,e){t._state=2,t._value=e,l(t)}function l(t){2===t._state&&0===t._deferreds.length&&i._immediateFn(function(){t._handled||i._unhandledRejectionFn(t._value)});for(var e=0,n=t._deferreds.length;e<n;e++)a(t,t._deferreds[e]);t._deferreds=null}function u(t,e,n){this.onFulfilled="function"==typeof t?t:null,this.onRejected="function"==typeof e?e:null,this.promise=n}function f(t,e){var n=!1;try{t(function(t){n||(n=!0,s(e,t))},function(t){n||(n=!0,c(e,t))})}catch(t){if(n)return;n=!0,c(e,t)}}var d=setTimeout;i.prototype.catch=function(t){return this.then(null,t)},i.prototype.then=function(t,e){var n=new this.constructor(o);return a(this,new u(t,e,n)),n},i.all=function(t){var e=Array.prototype.slice.call(t);return new i(function(t,n){function o(i,a){try{if(a&&("object"==typeof a||"function"==typeof a)){var s=a.then;if("function"==typeof s)return void s.call(a,function(t){o(i,t)},n)}e[i]=a,0==--r&&t(e)}catch(t){n(t)}}if(0===e.length)return t([]);for(var r=e.length,i=0;i<e.length;i++)o(i,e[i])})},i.resolve=function(t){return t&&"object"==typeof t&&t.constructor===i?t:new i(function(e){e(t)})},i.reject=function(t){return new i(function(e,n){n(t)})},i.race=function(t){return new i(function(e,n){for(var o=0,r=t.length;o<r;o++)t[o].then(e,n)})},i._immediateFn="function"==typeof e&&function(t){e(t)}||function(t){d(t,0)},i._unhandledRejectionFn=function(t){"undefined"!=typeof console&&console&&console.warn("Possible Unhandled Promise Rejection:",t)},i._setImmediateFn=function(t){i._immediateFn=t},i._setUnhandledRejectionFn=function(t){i._unhandledRejectionFn=t},void 0!==t&&t.exports?t.exports=i:n.Promise||(n.Promise=i)}(this)}).call(e,n(18).setImmediate)},function(t,e,n){function o(t,e){this._id=t,this._clearFn=e}var r=Function.prototype.apply;e.setTimeout=function(){return new o(r.call(setTimeout,window,arguments),clearTimeout)},e.setInterval=function(){return new o(r.call(setInterval,window,arguments),clearInterval)},e.clearTimeout=e.clearInterval=function(t){t&&t.close()},o.prototype.unref=o.prototype.ref=function(){},o.prototype.close=function(){this._clearFn.call(window,this._id)},e.enroll=function(t,e){clearTimeout(t._idleTimeoutId),t._idleTimeout=e},e.unenroll=function(t){clearTimeout(t._idleTimeoutId),t._idleTimeout=-1},e._unrefActive=e.active=function(t){clearTimeout(t._idleTimeoutId);var e=t._idleTimeout;e>=0&&(t._idleTimeoutId=setTimeout(function(){t._onTimeout&&t._onTimeout()},e))},n(19),e.setImmediate=setImmediate,e.clearImmediate=clearImmediate},function(t,e,n){(function(t,e){!function(t,n){"use strict";function o(t){"function"!=typeof t&&(t=new Function(""+t));for(var e=new Array(arguments.length-1),n=0;n<e.length;n++)e[n]=arguments[n+1];var o={callback:t,args:e};return l[c]=o,s(c),c++}function r(t){delete l[t]}function i(t){var e=t.callback,o=t.args;switch(o.length){case 0:e();break;case 1:e(o[0]);break;case 2:e(o[0],o[1]);break;case 3:e(o[0],o[1],o[2]);break;default:e.apply(n,o)}}function a(t){if(u)setTimeout(a,0,t);else{var e=l[t];if(e){u=!0;try{i(e)}finally{r(t),u=!1}}}}if(!t.setImmediate){var s,c=1,l={},u=!1,f=t.document,d=Object.getPrototypeOf&&Object.getPrototypeOf(t);d=d&&d.setTimeout?d:t,"[object process]"==={}.toString.call(t.process)?function(){s=function(t){e.nextTick(function(){a(t)})}}():function(){if(t.postMessage&&!t.importScripts){var e=!0,n=t.onmessage;return t.onmessage=function(){e=!1},t.postMessage("","*"),t.onmessage=n,e}}()?function(){var e="setImmediate$"+Math.random()+"$",n=function(n){n.source===t&&"string"==typeof n.data&&0===n.data.indexOf(e)&&a(+n.data.slice(e.length))};t.addEventListener?t.addEventListener("message",n,!1):t.attachEvent("onmessage",n),s=function(n){t.postMessage(e+n,"*")}}():t.MessageChannel?function(){var t=new MessageChannel;t.port1.onmessage=function(t){a(t.data)},s=function(e){t.port2.postMessage(e)}}():f&&"onreadystatechange"in f.createElement("script")?function(){var t=f.documentElement;s=function(e){var n=f.createElement("script");n.onreadystatechange=function(){a(e),n.onreadystatechange=null,t.removeChild(n),n=null},t.appendChild(n)}}():function(){s=function(t){setTimeout(a,0,t)}}(),d.setImmediate=o,d.clearImmediate=r}}("undefined"==typeof self?void 0===t?this:t:self)}).call(e,n(7),n(20))},function(t,e){function n(){throw new Error("setTimeout has not been defined")}function o(){throw new Error("clearTimeout has not been defined")}function r(t){if(u===setTimeout)return setTimeout(t,0);if((u===n||!u)&&setTimeout)return u=setTimeout,setTimeout(t,0);try{return u(t,0)}catch(e){try{return u.call(null,t,0)}catch(e){return u.call(this,t,0)}}}function i(t){if(f===clearTimeout)return clearTimeout(t);if((f===o||!f)&&clearTimeout)return f=clearTimeout,clearTimeout(t);try{return f(t)}catch(e){try{return f.call(null,t)}catch(e){return f.call(this,t)}}}function a(){b&&p&&(b=!1,p.length?m=p.concat(m):v=-1,m.length&&s())}function s(){if(!b){var t=r(a);b=!0;for(var e=m.length;e;){for(p=m,m=[];++v<e;)p&&p[v].run();v=-1,e=m.length}p=null,b=!1,i(t)}}function c(t,e){this.fun=t,this.array=e}function l(){}var u,f,d=t.exports={};!function(){try{u="function"==typeof setTimeout?setTimeout:n}catch(t){u=n}try{f="function"==typeof clearTimeout?clearTimeout:o}catch(t){f=o}}();var p,m=[],b=!1,v=-1;d.nextTick=function(t){var e=new Array(arguments.length-1);if(arguments.length>1)for(var n=1;n<arguments.length;n++)e[n-1]=arguments[n];m.push(new c(t,e)),1!==m.length||b||r(s)},c.prototype.run=function(){this.fun.apply(null,this.array)},d.title="browser",d.browser=!0,d.env={},d.argv=[],d.version="",d.versions={},d.on=l,d.addListener=l,d.once=l,d.off=l,d.removeListener=l,d.removeAllListeners=l,d.emit=l,d.prependListener=l,d.prependOnceListener=l,d.listeners=function(t){return[]},d.binding=function(t){throw new Error("process.binding is not supported")},d.cwd=function(){return"/"},d.chdir=function(t){throw new Error("process.chdir is not supported")},d.umask=function(){return 0}},function(t,e,n){"use strict";n(22).polyfill()},function(t,e,n){"use strict";function o(t,e){if(void 0===t||null===t)throw new TypeError("Cannot convert first argument to object");for(var n=Object(t),o=1;o<arguments.length;o++){var r=arguments[o];if(void 0!==r&&null!==r)for(var i=Object.keys(Object(r)),a=0,s=i.length;a<s;a++){var c=i[a],l=Object.getOwnPropertyDescriptor(r,c);void 0!==l&&l.enumerable&&(n[c]=r[c])}}return n}function r(){Object.assign||Object.defineProperty(Object,"assign",{enumerable:!1,configurable:!0,writable:!0,value:o})}t.exports={assign:o,polyfill:r}},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(24),r=n(6),i=n(5),a=n(36),s=function(){for(var t=[],e=0;e<arguments.length;e++)t[e]=arguments[e];if("undefined"!=typeof window){var n=a.getOpts.apply(void 0,t);return new Promise(function(t,e){i.default.promise={resolve:t,reject:e},o.default(n),setTimeout(function(){r.openModal()})})}};s.close=r.onAction,s.getState=r.getState,s.setActionValue=i.setActionValue,s.stopLoading=r.stopLoading,s.setDefaults=a.setDefaults,e.default=s},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(1),r=n(0),i=r.default.MODAL,a=n(4),s=n(34),c=n(35),l=n(1);e.init=function(t){o.getNode(i)||(document.body||l.throwErr("You can only use SweetAlert AFTER the DOM has loaded!"),s.default(),a.default()),a.initModalContent(t),c.default(t)},e.default=e.init},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(0),r=o.default.MODAL;e.modalMarkup='\n  <div class="'+r+'" role="dialog" aria-modal="true"></div>',e.default=e.modalMarkup},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(0),r=o.default.OVERLAY,i='<div \n    class="'+r+'"\n    tabIndex="-1">\n  </div>';e.default=i},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(0),r=o.default.ICON;e.errorIconMarkup=function(){var t=r+"--error",e=t+"__line";return'\n    <div class="'+t+'__x-mark">\n      <span class="'+e+" "+e+'--left"></span>\n      <span class="'+e+" "+e+'--right"></span>\n    </div>\n  '},e.warningIconMarkup=function(){var t=r+"--warning";return'\n    <span class="'+t+'__body">\n      <span class="'+t+'__dot"></span>\n    </span>\n  '},e.successIconMarkup=function(){var t=r+"--success";return'\n    <span class="'+t+"__line "+t+'__line--long"></span>\n    <span class="'+t+"__line "+t+'__line--tip"></span>\n\n    <div class="'+t+'__ring"></div>\n    <div class="'+t+'__hide-corners"></div>\n  '}},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(0),r=o.default.CONTENT;e.contentMarkup='\n  <div class="'+r+'">\n\n  </div>\n'},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(0),r=o.default.BUTTON_CONTAINER,i=o.default.BUTTON,a=o.default.BUTTON_LOADER;e.buttonMarkup='\n  <div class="'+r+'">\n\n    <button\n      class="'+i+'"\n    ></button>\n\n    <div class="'+a+'">\n      <div></div>\n      <div></div>\n      <div></div>\n    </div>\n\n  </div>\n'},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(4),r=n(2),i=n(0),a=i.default.ICON,s=i.default.ICON_CUSTOM,c=["error","warning","success","info"],l={error:r.errorIconMarkup(),warning:r.warningIconMarkup(),success:r.successIconMarkup()},u=function(t,e){var n=a+"--"+t;e.classList.add(n);var o=l[t];o&&(e.innerHTML=o)},f=function(t,e){e.classList.add(s);var n=document.createElement("img");n.src=t,e.appendChild(n)},d=function(t){if(t){var e=o.injectElIntoModal(r.iconMarkup);c.includes(t)?u(t,e):f(t,e)}};e.default=d},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(2),r=n(4),i=function(t){navigator.userAgent.includes("AppleWebKit")&&(t.style.display="none",t.offsetHeight,t.style.display="")};e.initTitle=function(t){if(t){var e=r.injectElIntoModal(o.titleMarkup);e.textContent=t,i(e)}},e.initText=function(t){if(t){var e=document.createDocumentFragment();t.split("\n").forEach(function(t,n,o){e.appendChild(document.createTextNode(t)),n<o.length-1&&e.appendChild(document.createElement("br"))});var n=r.injectElIntoModal(o.textMarkup);n.appendChild(e),i(n)}}},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(1),r=n(4),i=n(0),a=i.default.BUTTON,s=i.default.DANGER_BUTTON,c=n(3),l=n(2),u=n(6),f=n(5),d=function(t,e,n){var r=e.text,i=e.value,d=e.className,p=e.closeModal,m=o.stringToNode(l.buttonMarkup),b=m.querySelector("."+a),v=a+"--"+t;if(b.classList.add(v),d){(Array.isArray(d)?d:d.split(" ")).filter(function(t){return t.length>0}).forEach(function(t){b.classList.add(t)})}n&&t===c.CONFIRM_KEY&&b.classList.add(s),b.textContent=r;var g={};return g[t]=i,f.setActionValue(g),f.setActionOptionsFor(t,{closeModal:p}),b.addEventListener("click",function(){return u.onAction(t)}),m},p=function(t,e){var n=r.injectElIntoModal(l.footerMarkup);for(var o in t){var i=t[o],a=d(o,i,e);i.visible&&n.appendChild(a)}0===n.children.length&&n.remove()};e.default=p},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(3),r=n(4),i=n(2),a=n(5),s=n(6),c=n(0),l=c.default.CONTENT,u=function(t){t.addEventListener("input",function(t){var e=t.target,n=e.value;a.setActionValue(n)}),t.addEventListener("keyup",function(t){if("Enter"===t.key)return s.onAction(o.CONFIRM_KEY)}),setTimeout(function(){t.focus(),a.setActionValue("")},0)},f=function(t,e,n){var o=document.createElement(e),r=l+"__"+e;o.classList.add(r);for(var i in n){var a=n[i];o[i]=a}"input"===e&&u(o),t.appendChild(o)},d=function(t){if(t){var e=r.injectElIntoModal(i.contentMarkup),n=t.element,o=t.attributes;"string"==typeof n?f(e,n,o):e.appendChild(n)}};e.default=d},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(1),r=n(2),i=function(){var t=o.stringToNode(r.overlayMarkup);document.body.appendChild(t)};e.default=i},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(5),r=n(6),i=n(1),a=n(3),s=n(0),c=s.default.MODAL,l=s.default.BUTTON,u=s.default.OVERLAY,f=function(t){t.preventDefault(),v()},d=function(t){t.preventDefault(),g()},p=function(t){if(o.default.isOpen)switch(t.key){case"Escape":return r.onAction(a.CANCEL_KEY)}},m=function(t){if(o.default.isOpen)switch(t.key){case"Tab":return f(t)}},b=function(t){if(o.default.isOpen)return"Tab"===t.key&&t.shiftKey?d(t):void 0},v=function(){var t=i.getNode(l);t&&(t.tabIndex=0,t.focus())},g=function(){var t=i.getNode(c),e=t.querySelectorAll("."+l),n=e.length-1,o=e[n];o&&o.focus()},h=function(t){t[t.length-1].addEventListener("keydown",m)},w=function(t){t[0].addEventListener("keydown",b)},y=function(){var t=i.getNode(c),e=t.querySelectorAll("."+l);e.length&&(h(e),w(e))},x=function(t){if(i.getNode(u)===t.target)return r.onAction(a.CANCEL_KEY)},_=function(t){var e=i.getNode(u);e.removeEventListener("click",x),t&&e.addEventListener("click",x)},k=function(t){o.default.timer&&clearTimeout(o.default.timer),t&&(o.default.timer=window.setTimeout(function(){return r.onAction(a.CANCEL_KEY)},t))},O=function(t){t.closeOnEsc?document.addEventListener("keyup",p):document.removeEventListener("keyup",p),t.dangerMode?v():g(),y(),_(t.closeOnClickOutside),k(t.timer)};e.default=O},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(1),r=n(3),i=n(37),a=n(38),s={title:null,text:null,icon:null,buttons:r.defaultButtonList,content:null,className:null,closeOnClickOutside:!0,closeOnEsc:!0,dangerMode:!1,timer:null},c=Object.assign({},s);e.setDefaults=function(t){c=Object.assign({},s,t)};var l=function(t){var e=t&&t.button,n=t&&t.buttons;return void 0!==e&&void 0!==n&&o.throwErr("Cannot set both 'button' and 'buttons' options!"),void 0!==e?{confirm:e}:n},u=function(t){return o.ordinalSuffixOf(t+1)},f=function(t,e){o.throwErr(u(e)+" argument ('"+t+"') is invalid")},d=function(t,e){var n=t+1,r=e[n];o.isPlainObject(r)||void 0===r||o.throwErr("Expected "+u(n)+" argument ('"+r+"') to be a plain object")},p=function(t,e){var n=t+1,r=e[n];void 0!==r&&o.throwErr("Unexpected "+u(n)+" argument ("+r+")")},m=function(t,e,n,r){var i=typeof e,a="string"===i,s=e instanceof Element;if(a){if(0===n)return{text:e};if(1===n)return{text:e,title:r[0]};if(2===n)return d(n,r),{icon:e};f(e,n)}else{if(s&&0===n)return d(n,r),{content:e};if(o.isPlainObject(e))return p(n,r),e;f(e,n)}};e.getOpts=function(){for(var t=[],e=0;e<arguments.length;e++)t[e]=arguments[e];var n={};t.forEach(function(e,o){var r=m(0,e,o,t);Object.assign(n,r)});var o=l(n);n.buttons=r.getButtonListOpts(o),delete n.button,n.content=i.getContentOpts(n.content);var u=Object.assign({},s,c,n);return Object.keys(u).forEach(function(t){a.DEPRECATED_OPTS[t]&&a.logDeprecation(t)}),u}},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=n(1),r={element:"input",attributes:{placeholder:""}};e.getContentOpts=function(t){var e={};return o.isPlainObject(t)?Object.assign(e,t):t instanceof Element?{element:t}:"input"===t?r:null}},function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.logDeprecation=function(t){var n=e.DEPRECATED_OPTS[t],o=n.onlyRename,r=n.replacement,i=n.subOption,a=n.link,s=o?"renamed":"deprecated",c='SweetAlert warning: "'+t+'" option has been '+s+".";if(r){c+=" Please use"+(i?' "'+i+'" in ':" ")+'"'+r+'" instead.'}var l="https://sweetalert.js.org";c+=a?" More details: "+l+a:" More details: "+l+"/guides/#upgrading-from-1x",console.warn(c)},e.DEPRECATED_OPTS={type:{replacement:"icon",link:"/docs/#icon"},imageUrl:{replacement:"icon",link:"/docs/#icon"},customClass:{replacement:"className",onlyRename:!0,link:"/docs/#classname"},imageSize:{},showCancelButton:{replacement:"buttons",link:"/docs/#buttons"},showConfirmButton:{replacement:"button",link:"/docs/#button"},confirmButtonText:{replacement:"button",link:"/docs/#button"},confirmButtonColor:{},cancelButtonText:{replacement:"buttons",link:"/docs/#buttons"},closeOnConfirm:{replacement:"button",subOption:"closeModal",link:"/docs/#button"},closeOnCancel:{replacement:"buttons",subOption:"closeModal",link:"/docs/#buttons"},showLoaderOnConfirm:{replacement:"buttons"},animation:{},inputType:{replacement:"content",link:"/docs/#content"},inputValue:{replacement:"content",link:"/docs/#content"},inputPlaceholder:{replacement:"content",link:"/docs/#content"},html:{replacement:"content",link:"/docs/#content"},allowEscapeKey:{replacement:"closeOnEsc",onlyRename:!0,link:"/docs/#closeonesc"},allowClickOutside:{replacement:"closeOnClickOutside",onlyRename:!0,link:"/docs/#closeonclickoutside"}}}])});
/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! ./../../node-libs-browser/node_modules/timers-browserify/main.js */ "./node_modules/node-libs-browser/node_modules/timers-browserify/main.js").setImmediate, __webpack_require__(/*! ./../../node-libs-browser/node_modules/timers-browserify/main.js */ "./node_modules/node-libs-browser/node_modules/timers-browserify/main.js").clearImmediate))

/***/ }),

/***/ "./node_modules/webpack/buildin/global.js":
/*!***********************************!*\
  !*** (webpack)/buildin/global.js ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

var g;

// This works in non-strict mode
g = (function() {
	return this;
})();

try {
	// This works if eval is allowed (see CSP)
	g = g || new Function("return this")();
} catch (e) {
	// This works if the window reference is available
	if (typeof window === "object") g = window;
}

// g can still be undefined, but nothing to do about it...
// We return undefined, instead of nothing here, so it's
// easier to handle this case. if(!global) { ...}

module.exports = g;


/***/ }),

/***/ "./node_modules/webpack/buildin/module.js":
/*!***********************************!*\
  !*** (webpack)/buildin/module.js ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = function(module) {
	if (!module.webpackPolyfill) {
		module.deprecate = function() {};
		module.paths = [];
		// module.parent = undefined by default
		if (!module.children) module.children = [];
		Object.defineProperty(module, "loaded", {
			enumerable: true,
			get: function() {
				return module.l;
			}
		});
		Object.defineProperty(module, "id", {
			enumerable: true,
			get: function() {
				return module.i;
			}
		});
		module.webpackPolyfill = 1;
	}
	return module;
};


/***/ })

/******/ });
//# sourceMappingURL=client-bundle.js.map