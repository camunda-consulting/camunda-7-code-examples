
$( document ).ready(function() {

  var priceindicationInCent = 0;

	var pathArray = window.location.pathname.split( '/' );
	var baseUrl = window.location.protocol + "//" + window.location.host + "/" + pathArray[1] + "/api";

  // Start single Process Instance
  $('#triggerStartApplication').click(function() {
        var application = {
          "applicant" : {
               "name":         $('#applicantName').val(),
               "gender":   $('#selectSex').val(),
               "dateOfBirth": $('#birthdate').val() + "T00:00:00",
               "email":        $('#email').val()
              },
          "car" : {
	          "manufacturer": $('#carManufacturer').val(),
  	        "type":       $('#carType').val(),
          },
          "insuranceProduct": "Camundanzia Super Safe Plus",
          "priceIndicationInCents": getPrice() * 100
          };

        var data = JSON.stringify(application);
        var url = baseUrl + "/insuranceApplication";

        console.log( data );

        $.ajax({
              type: 'POST',
              url: url,
              data: data,
              contentType: 'application/json; charset=utf-8',
              dataType: 'json',
              success: function (info) {
                  $('#applicationReceived').toggle();
                  $('#fieldsetForm').toggle();
              },
              crossDomain: true,
          });

  });

  // correlate message for Antrag
  $('#triggerUploadDocuments').click(function() {
  	
	      var fileUpload = $('#documentToUpload').get(0);

     	  var fileVar = {};
	      if(typeof FileReader === 'function' && fileUpload.files.length > 0) {		        
	        var reader = new FileReader();
	        reader.onloadend = (function(fileUpload) {
	          return function(e) {
	            var binary = '';
	            var bytes = new Uint8Array( e.target.result );
	            var len = bytes.byteLength;
	            for (var j = 0; j < len; j++) {
	                binary += String.fromCharCode( bytes[ j ] );
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
		             type : 'POST',
		             url: url,
		             data: data,
		             contentType: 'application/json; charset=utf-8',
		             dataType: 'json',
		             success: function (result) {
		                $('#documentsReceived').toggle();
		                $('#fieldsetForm').toggle();
		             },
		             crossDomain: true,
		    });
		}; 
    	
      
    
  });


  // Dynamic stuff to fill data into car selection
  $('#carManufacturer').on('change', function() {
      if ($('#carManufacturer').val() == "VW") {
        $('#carType').children()[0].value = 'Beatle';  $('#carType').children()[0].text = 'Beatle';
        $('#carType').children()[1].value = 'Golf IV'; $('#carType').children()[1].text = 'Golf IV';
        $('#carType').children()[2].value = 'Golf V';  $('#carType').children()[2].text = 'Golf V';
        $('#carType').children()[3].value = 'Passat';  $('#carType').children()[3].text = 'Passat';
      }
      if ($('#carManufacturer').val() == "BMW") {
        $('#carType').children()[0].value = '318i';  $('#carType').children()[0].text = '318i';
        $('#carType').children()[1].value = '525i';  $('#carType').children()[1].text = '525i';
        $('#carType').children()[2].value = '735i';  $('#carType').children()[2].text = '735i';
        $('#carType').children()[3].value = 'X3';    $('#carType').children()[3].text = 'X3';
      }
      if ($('#carManufacturer').val() == "Porsche") {
        $('#carType').children()[0].value = '911';       $('#carType').children()[0].text = '911';
        $('#carType').children()[1].value = '925';       $('#carType').children()[1].text = '925';
        $('#carType').children()[2].value = 'Boxster';   $('#carType').children()[2].text = 'Boxster';
        $('#carType').children()[3].value = 'Cayenne';  $('#carType').children()[3].text = 'Cayenne';
      }
      if ($('#carManufacturer').val() == "Audi") {
        $('#carType').children()[0].value = 'A3';  $('#carType').children()[0].text = 'A3';
        $('#carType').children()[1].value = 'A4';  $('#carType').children()[1].text = 'A4';
        $('#carType').children()[2].value = 'A6';  $('#carType').children()[2].text = 'A6';
        $('#carType').children()[3].value = 'A8';  $('#carType').children()[3].text = 'A8';
      }
      calculatePrice(priceindicationInCent);
  });

  $('#carType').on('change', function() {
      calculatePrice(priceindicationInCent);
  });

function calculatePrice(priceindicationInCent) {
   if ($('#carManufacturer').val() == "VW" && $('#carType').val()=="Beatle") { priceindicationInCent = 120}
   if ($('#carManufacturer').val() == "VW" && $('#carType').val()=="Golf IV") {priceindicationInCent = 160}
   if ($('#carManufacturer').val() == "VW" && $('#carType').val()=="Golf V") {priceindicationInCent = 150}
   if ($('#carManufacturer').val() == "VW" && $('#carType').val()=="Passat") {priceindicationInCent = 150}
   if ($('#carManufacturer').val() == "BMW" && $('#carType').val()=="318i") {priceindicationInCent = 190}
   if ($('#carManufacturer').val() == "BMW" && $('#carType').val()=="525i") {priceindicationInCent = 210}
   if ($('#carManufacturer').val() == "BMW" && $('#carType').val()=="735i") {priceindicationInCent = 240}
   if ($('#carManufacturer').val() == "BMW" && $('#carType').val()=="X3") {priceindicationInCent = 280}
   if ($('#carManufacturer').val() == "Porsche" && $('#carType').val()=="911") {priceindicationInCent = 310}
   if ($('#carManufacturer').val() == "Porsche" && $('#carType').val()=="925") {priceindicationInCent = 300}
   if ($('#carManufacturer').val() == "Porsche" && $('#carType').val()=="Boxster") {priceindicationInCent = 290}
   if ($('#carManufacturer').val() == "Porsche" && $('#carType').val()=="Cayenne") {priceindicationInCent = 300}
   if ($('#carManufacturer').val() == "Audi" && $('#carType').val()=="A3") {priceindicationInCent = 180}
   if ($('#carManufacturer').val() == "Audi" && $('#carType').val()=="A4") {priceindicationInCent = 180}
   if ($('#carManufacturer').val() == "Audi" && $('#carType').val()=="A6") {priceindicationInCent = 200}
   if ($('#carManufacturer').val() == "Audi" && $('#carType').val()=="A8") {priceindicationInCent = 280}

   $('#priceindicationInCent').val(priceindicationInCent + ",00 EUR");
}

function getPrice() {
  var priceindicationInCent;
   if ($('#carManufacturer').val() == "VW" && $('#carType').val()=="Beatle") { priceindicationInCent = 120}
   if ($('#carManufacturer').val() == "VW" && $('#carType').val()=="Golf IV") {priceindicationInCent = 160}
   if ($('#carManufacturer').val() == "VW" && $('#carType').val()=="Golf V") {priceindicationInCent = 150}
   if ($('#carManufacturer').val() == "VW" && $('#carType').val()=="Passat") {priceindicationInCent = 150}
   if ($('#carManufacturer').val() == "BMW" && $('#carType').val()=="318i") {priceindicationInCent = 190}
   if ($('#carManufacturer').val() == "BMW" && $('#carType').val()=="525i") {priceindicationInCent = 210}
   if ($('#carManufacturer').val() == "BMW" && $('#carType').val()=="735i") {priceindicationInCent = 240}
   if ($('#carManufacturer').val() == "BMW" && $('#carType').val()=="X3") {priceindicationInCent = 280}
   if ($('#carManufacturer').val() == "Porsche" && $('#carType').val()=="911") {priceindicationInCent = 310}
   if ($('#carManufacturer').val() == "Porsche" && $('#carType').val()=="925") {priceindicationInCent = 300}
   if ($('#carManufacturer').val() == "Porsche" && $('#carType').val()=="Boxster") {priceindicationInCent = 290}
   if ($('#carManufacturer').val() == "Porsche" && $('#carType').val()=="Cayenne") {priceindicationInCent = 300}
   if ($('#carManufacturer').val() == "Audi" && $('#carType').val()=="A3") {priceindicationInCent = 180}
   if ($('#carManufacturer').val() == "Audi" && $('#carType').val()=="A4") {priceindicationInCent = 180}
   if ($('#carManufacturer').val() == "Audi" && $('#carType').val()=="A6") {priceindicationInCent = 200}
   if ($('#carManufacturer').val() == "Audi" && $('#carType').val()=="A8") {priceindicationInCent = 280}

   return priceindicationInCent;
}

});
