if(window.location.pathname.match(/\/\/+/g)){
  console.log(window);
  var toReplace = "/"
  var toBeReplaced = window.location.pathname.match(/\/\/+/g)
  var correctPathname = window.location.pathname.replace(toBeReplaced, toReplace);
  console.log(window.href);
  window.location.href = window.location.origin + correctPathname;
}

$(document).ready(function() {
  autofillWorkaround();

  var lang = "en";

  // fill fields from URL and remember language
  (new URL(window.location.href)).searchParams.forEach((x, y) => {
    if (y == "lang") {
      lang = x;
    }
    var field = document.getElementById(y);
    if (field) {
      document.getElementById(y).value = x;
    }
  });

  // // set URL to reach UI
  // if ($('#uiUrl')) {
  // var url = window.location;
  // var paths = url.pathname.split('/');
  // var guiUrl = url .protocol + "//" + url.host + "/";
  // paths.slice(0,paths.length -1).forEach((x) => guiUrl += x + "/");
  // $('#uiUrl').val(guiUrl);
  // }

  var preisindikationInCent = 0;

  // $('#birthdate').on('change', function() {
  // console.log($('#birthdate').val());
  // });

  // Removed Auth
  // beforeSend = function(xhr) {
  // xhr.setRequestHeader('Authorization', 'Basic ' +
  // window.btoa(unescape(encodeURIComponent('${camunda.rest.username}' + ':'
  // + '${camunda.rest.password}'))))
  // };


  var pathArray = window.location.pathname.split('/');
  var baseUrl = window.location.protocol + "//" + window.location.host + "/" + pathArray[1] + "/API";

  $.i18n().load({
    'en': window.langEn['en'],
    'de': window.langDe['de']
  })

  $.i18n({
    locale: lang
  });

  $('body').i18n();

  $('#search').attr("placeholder", lang == 'de' ? 'Suchen...' : 'Search...');

  $('.langlink').each(function(i, obj) {
    $(this).click(function(event) {
      event.preventDefault();
      window.location.href = $(this).context.href + '?lang=' + lang;
    });
  });

  // Start single Process Instance
  $('#triggerStartApplication').click(function() {
    var neuantrag = {
    	"traceid": uuidv4(),
      "applicant": {
        "name": $('#applicant').val(),
        "gender": $('#selectSex').val(),
        "birthday": $('#birthdate').val() + "T00:00:00",
        "email": $('#email').val()
      },
      "employment": $('#employment').val(),
      "category": $('#category').val(),
      "priceIndicationInCent": getPrice() * 100,
      "product": $("#category option:selected").text(),
      "corporation": "Camundanzia"
      // ,
      // "uiUrl": $('#uiUrl').val()
    };

    var data = JSON.stringify(neuantrag);

    console.log(data);

    $.ajax({
      type: 'POST',
      url: "/api/input/request/digital",
      data: data,
      contentType: 'application/json; charset=utf-8',
      success: function(result) {
        $('#applicationId').text(result);
        $('#applicationReceived').toggle();
        $('#fieldsetForm').toggle();
      },
      crossDomain: true,
    });

  });
  
//Start single Process Instance
  $('#triggerStartManualApplication').click(function() {
    var neuantrag = {
    	"traceid": uuidv4(),
      "applicant": {
        "name": $('#applicant').val(),
        "gender": $('#selectSex').val(),
        "birthday": $('#birthdate').val() + "T00:00:00",
        "email": $('#email').val()
      },
      "employment": $('#employment').val(),
      "category": $('#category').val(),
      "priceIndicationInCent": getPrice() * 100,
      "product": $("#category option:selected").text(),
      "corporation": "Camundanzia"
      // ,
      // "uiUrl": $('#uiUrl').val()
    };

    var data = JSON.stringify(neuantrag);

    console.log(data);

    $.ajax({
      type: 'POST',
      url: "/api/input/request/analog",
      data: data,
      contentType: 'application/json; charset=utf-8',
      success: function(result) {
        $('#applicationId').text(result);
        $('#applicationReceived').toggle();
        $('#fieldsetForm').toggle();
      },
      crossDomain: true,
    });

  });

  // correlate message for Antrag
  $('#triggerUploadDocuments').click(function() {
    debugger;
    var fileUpload = $('#documentToUpload').get(0);

    var fileVar = {};
    if (typeof FileReader === 'function' && fileUpload.files.length > 0) {
      var reader = new FileReader();
      reader.onloadend = (function(fileUpload) {
        return function(e) {
          var binary = '';
          var bytes = new Uint8Array(e.target.result);
          var len = bytes.byteLength;
          for (var j = 0; j < len; j++) {
            binary += String.fromCharCode(bytes[j]);
          }
          fileVar.value = btoa(binary);

          // set file metadata as value info
          fileVar.type = 'File';
          fileVar.valueInfo = {
            filename: fileUpload.files[0].name,
            mimeType: fileUpload.files[0].type
          };


          callCallback();
        };
      })(fileUpload);
      reader.readAsArrayBuffer(fileUpload.files[0]);
    };

    var callCallback = function() {
      var data = JSON.stringify(fileVar);
      var url = baseUrl + "/document/" + $('#referenceId').val();
      $.ajax({
        type: 'POST',
        url: url,
        data: data,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(result) {
          $('#documentsReceived').toggle();
          $('#fieldsetForm').toggle();
        },
        crossDomain: true,
      });
    };



  });

  $('#category').on('change, keyup, mouseup', function() {
    calculatePrice(preisindikationInCent);
  });


  $('#employment').on('change, keyup, mouseup', function() {
    calculatePrice(preisindikationInCent);
  });


  function isEqualToOneOf(objectToBeChecked) {
    for (var i = 1; i < arguments.length; i++) {
      if (arguments[i] == objectToBeChecked) {
        return true;
      }
    }
    return false;
  }

  function uuidv4() {
	  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
	    var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
	    return v.toString(16);
	  });
	}
  
  function calculatePrice(preisindikationInCent) {

    var category = $('#category').val();
    var employment = $('#employment').val();

    if (isEqualToOneOf(category, 'Basispaket', 'Basic Package')) {
      preisindikationInCent = 120;
    } else if (isEqualToOneOf(category, 'Standard Paket', 'Standard Package')) {
      preisindikationInCent = 200;
    } else {
      preisindikationInCent = 340;
    }

    preisindikationInCent = Math.round(preisindikationInCent);
    $('#priceIndicationInCent').val(preisindikationInCent + ",00 EUR");
  }

  function getPrice() {
    var preisindikationInCent;

    var category = $('#category').val();
    var employment = $('#employment').val();

    if (isEqualToOneOf(category, 'Basispaket', 'Basic Package')) {
      preisindikationInCent = 120;
    } else if (isEqualToOneOf(category, 'Standard Paket', 'Standard Package')) {
      preisindikationInCent = 200;
    } else {
      preisindikationInCent = 340;
    }

    return preisindikationInCent;
  }
});

function autofillWorkaround() {
  /**
   * Autofill event polyfill ##version:1.0.0##
   * (c) 2014 Google, Inc.
   * License: MIT
   */
  (function(window) {
    var $ = window.jQuery || window.angular.element;
    var rootElement = window.document.documentElement,
      $rootElement = $(rootElement);

    addGlobalEventListener('change', markValue);
    addValueChangeByJsListener(markValue);

    $.prototype.checkAndTriggerAutoFillEvent = jqCheckAndTriggerAutoFillEvent;

    // Need to use blur and not change event
    // as Chrome does not fire change events in all cases an input is changed
    // (e.g. when starting to type and then finish the input by auto filling a username)
    addGlobalEventListener('blur', function(target) {
      // setTimeout needed for Chrome as it fills other
      // form fields a little later...
      window.setTimeout(function() {
        findParentForm(target).find('input').checkAndTriggerAutoFillEvent();
      }, 20);
    });

    window.document.addEventListener('DOMContentLoaded', function() {
      // The timeout is needed for Chrome as it auto fills
      // login forms some time after DOMContentLoaded!
      window.setTimeout(function() {
        $rootElement.find('input').checkAndTriggerAutoFillEvent();
      }, 200);
    }, false);

    return;

    // ----------

    function jqCheckAndTriggerAutoFillEvent() {
      var i, el;
      for (i = 0; i < this.length; i++) {
        el = this[i];
        if (!valueMarked(el)) {
          markValue(el);
          triggerChangeEvent(el);
        }
      }
    }

    function valueMarked(el) {
      var val = el.value,
        $$currentValue = el.$$currentValue;
      if (!val && !$$currentValue) {
        return true;
      }
      return val === $$currentValue;
    }

    function markValue(el) {
      el.$$currentValue = el.value;
    }

    function addValueChangeByJsListener(listener) {
      var jq = window.jQuery || window.angular.element,
        jqProto = jq.prototype;
      var _val = jqProto.val;
      jqProto.val = function(newValue) {
        var res = _val.apply(this, arguments);
        if (arguments.length > 0) {
          forEach(this, function(el) {
            listener(el, newValue);
          });
        }
        return res;
      }
    }

    function addGlobalEventListener(eventName, listener) {
      // Use a capturing event listener so that
      // we also get the event when it's stopped!
      // Also, the blur event does not bubble.
      rootElement.addEventListener(eventName, onEvent, true);

      function onEvent(event) {
        var target = event.target;
        listener(target);
      }
    }

    function findParentForm(el) {
      while (el) {
        if (el.nodeName === 'FORM') {
          return $(el);
        }
        el = el.parentNode;
      }
      return $();
    }

    function forEach(arr, listener) {
      if (arr.forEach) {
        return arr.forEach(listener);
      }
      var i;
      for (i = 0; i < arr.length; i++) {
        listener(arr[i]);
      }
    }

    function triggerChangeEvent(element) {
      var doc = window.document;
      var event = doc.createEvent("HTMLEvents");
      event.initEvent("change", true, true);
      element.dispatchEvent(event);
    }

  })(window);
}
