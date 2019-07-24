'use strict';

var domify = require('min-dom/lib/domify'),
  domEvent = require('min-dom/lib/event'),
  domClasses = require('min-dom/lib/classes'),
  domQuery = require('min-dom/lib/query'),
  clear = require('min-dom/lib/clear');
var PropertiesActivator = require('bpmn-js-properties-panel/lib/PropertiesActivator');
var CamundaPropertiesProvider = require('bpmn-js-properties-panel/lib/provider/camunda/CamundaPropertiesProvider');
var formHelper = require('bpmn-js-properties-panel/lib/helper/FormHelper');
var swal = require('sweetalert');
var copy = require('clipboard-copy');

function GeneratedFormPreviewPluginProvider(eventBus, elementRegistry, bpmnFactory, elementTemplates, translate) {
  var camunda = new CamundaPropertiesProvider(eventBus, bpmnFactory, elementRegistry, elementTemplates, translate);

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


GeneratedFormPreviewPlugin.$inject = ['eventBus', 'elementRegistry', 'bpmnFactory', 'elementTemplates', 'translate'];

function GeneratedFormPreviewPlugin() {

};

module.exports = {
  __init__: ['generatedFormPreviewPlugin'],
  propertiesProvider: ['type', GeneratedFormPreviewPluginProvider],
  generatedFormPreviewPlugin: ['type', GeneratedFormPreviewPlugin]
};
