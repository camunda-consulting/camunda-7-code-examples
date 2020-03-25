var hidden = true;
var stickyHelperContainerActive = false;
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

var isGerman = lang == 'de';


$(document).ready(function() {
  var addRootContainers = () => {
    var demoButtonContainer = `<div id="stickyHelperButtonContainer" class="stickyHelper text-center">
                                <div id="demoTextWrapper" class="h-100">
                                &nbsp;&nbsp;DEMO&nbsp;&nbsp;
                                </div>
                              </div>`;

    var helperButtonsContainer = `<div hidden id="stickyHelperContainer" class="stickyHelper text-center w-100"></div>`;

    $('body').append(demoButtonContainer);
    $('body').append(helperButtonsContainer);
  }

  addRootContainers();

  var hide = (id) => {
    $('#' + id).attr('hidden', true);
  }

  var show = (id) => {
    $('#' + id).removeAttr('hidden');
  }

  var showEnabledHelpers = () => {
    if(hidden){
      show('stickyHelperButtonContainer');
      if(stickyHelperContainerActive){
        show('stickyHelperContainer');
      }
      hidden = false;
    }
  }

  var hideDisabledHelpers = () => {
    hide('stickyHelperButtonContainer');
    if(stickyHelperContainerActive){
      hide('stickyHelperContainer');
    }
    hidden = true;
  }

  var getButtonDiv = (id, text, divClass) => {
    return `<div class="buttonContainer col-lg-1 col-md-1 col-sm-1 col-xs-12 h-100 ${divClass}" style="padding-top:1em;bottom: -100%;display: inline-block;background: black;">
              <button id="${id}" class="autofillButton"><span class="text-dark">${text}</span></button>
            </div>`;
  }

  var getStickyHelperContainerDiv = () => {
    return `<div id="stickyHelper" class="stickyHelper">
              <div id="stickyHelperRow" class="row h-100 container-fluid text-center justify-content-center">
              </div>
            </div>`;
  }

  var addStickyHelperContainer = () => {
      var stickyHelperContainer = getStickyHelperContainerDiv()
      $('#stickyHelperContainer').append(stickyHelperContainer);

      var row = $('#stickyHelperRow');
      row.append(getButtonDiv('greenButton', 'Green path', 'greenDiv'));
      row.append(getButtonDiv('yellowButton', 'Yellow path', 'yelDiv'));
      row.append(getButtonDiv('redButton', 'Red path', 'redDiv'));

      $('#greenButton').css('border', '2px solid #32CD32');
      $('#greenButton').click(greenButtonClick);
      $('#yellowButton').css('border', '2px solid #FFFF00');
      $('#yellowButton').click(yellowButtonClick);
      $('#redButton').css('border', '2px solid red');
      $('#redButton').click(redButtonClick);

      stickyHelperContainerActive = true;
  }

  $('#demoTextWrapper').click(() => {

    if(stickyHelperContainerActive){
      removeHelperButtons();
    }else {
      show('stickyHelperContainer');
      addStickyHelperContainer();

      $('#stickyHelperButtonContainer').animate({bottom: '+=7%'}, 'slow');
      $('.buttonContainer').animate({bottom: '+=100%'}, 'slow');
    }
  });



    var greenButtonClick = () => {
      $('#birthdate').val('1988-03-22');
      $('#employment').val('Freelancer').change();
      $('#premiumButton').click();
      removeHelperButtons();
    }

    var yellowButtonClick = () => {
      $('#birthdate').val('1993-05-03');
      $("#employment").val(isGerman ? 'Selbstständig' : "Self-employed").change();
      $('#premiumButton').click();
      removeHelperButtons();
    }

    var redButtonClick = () => {
      $('#birthdate').val('1995-02-28');
      $('#employment').val(isGerman ? 'Nicht erwerbstätig' : 'Unemployed').change();
      $('#standardButton').click();
      removeHelperButtons();
    }

  var removeHelperButtons = () => {
    var div = $('#stickyHelperButtonContainer');
    div.animate({bottom: '-=7%'}, 'fast');
    $('.buttonContainer').animate({bottom: '-=100%'}, 'fast', () => {
      $('#stickyHelper').remove();
      hide('stickyHelperContainer');
    });
    stickyHelperContainerActive = false;
  }
});
