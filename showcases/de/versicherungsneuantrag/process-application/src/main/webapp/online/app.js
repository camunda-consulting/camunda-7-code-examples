
$( document ).ready(function() {
	
	var preisindikationInCent = 0;

	camClient = new CamSDK.Client({
		apiUri: 'http://localhost:8080/engine-rest/'
	});

  //$('#birthdate').on('change', function() {
  //	console.log($('#birthdate').val());
  //});

	// Start single Process Instance
	$('#triggerStartApplication').click(function() {

		var neuantrag = {
			"antragssteller" : {
				   "name":         $('#antragstellerName').val(),
				   "geschlecht":   $('#selectSex').val(),
				   "geburtsdatum": $('#birthdate').val() + "T00:00:00",
				   "email":        $('#email').val()
				  },
			"fahrzeugHersteller": $('#kfzHersteller').val(),
		  "fahrzeugTyp":       $('#kfzTyp').val(),
			"versicherungsprodukt": "Camundanzia Vollkasko Plus",
			"preisindikationInCent": getPrice() * 100
			};
			
	  console.log(neuantrag);
		var resource = camClient.resource("process-definition");
		
		// {"antragssteller":{"vorname":null,"nachname":null,"geburtsdatum":170926761061},"fahrzeug":{"hersteller":"VW","typ":"Golf IV"},"fahrerUeber25":false,"versicherungsprodukt":"Camundanzia Vollkasko Plus"}

		resource.submitForm ({
			key: "versicherungsneuantragMitDokumentenerstellung",
			variables: {
				neuantrag: {
					value: JSON.stringify(neuantrag),
					type: 'Object',
          valueInfo: {
             serializationDataFormat: 'application/json',
             objectTypeName: 'com.camunda.demo.versicherungsneuantrag.model.Neuantrag'
          }
				}
			}
		}, function (error, response) {
			$('#applicationReceived').toggle();
			$('#fieldsetForm').toggle();
		});

	});
	
	
	// correlate message for Antrag	
	$('#triggerUploadDocuments').click(function() {
		
		var data = {
			"messageName": "MSG_DOCUMENT_RECEIVED", 
			"processVariables" : {}
		};
    $.ajax('/camunda/api/engine/engine/default/message/', {
		         data: JSON.stringify(data),
		         contentType : 'application/json',
		         type : 'POST',
		         success: function (result) {
								$('#documentsReceived').toggle();
								$('#fieldsetForm').toggle();
		         }
	  });			
	});
	
	
	// Dynamic stuff to fill data into car selection
	$('#kfzHersteller').on('change', function() {
		  if ($('#kfzHersteller').val() == "VW") {
		  	$('#kfzTyp').children()[0].value = 'Beatle';  $('#kfzTyp').children()[0].text = 'Beatle';
		  	$('#kfzTyp').children()[1].value = 'Golf IV'; $('#kfzTyp').children()[1].text = 'Golf IV';
		  	$('#kfzTyp').children()[2].value = 'Golf V';  $('#kfzTyp').children()[2].text = 'Golf V';
		  	$('#kfzTyp').children()[3].value = 'Passat';  $('#kfzTyp').children()[3].text = 'Passat';
		  }
		  if ($('#kfzHersteller').val() == "BMW") {
		  	$('#kfzTyp').children()[0].value = '318i';  $('#kfzTyp').children()[0].text = '318i';
		  	$('#kfzTyp').children()[1].value = '525i';  $('#kfzTyp').children()[1].text = '525i';
		  	$('#kfzTyp').children()[2].value = '735i';  $('#kfzTyp').children()[2].text = '735i';
		  	$('#kfzTyp').children()[3].value = 'X3';    $('#kfzTyp').children()[3].text = 'X3';
		  }
		  if ($('#kfzHersteller').val() == "Porsche") {
		  	$('#kfzTyp').children()[0].value = '911';       $('#kfzTyp').children()[0].text = '911';
		  	$('#kfzTyp').children()[1].value = '925';       $('#kfzTyp').children()[1].text = '925';
		  	$('#kfzTyp').children()[2].value = 'Boxster';   $('#kfzTyp').children()[2].text = 'Boxster';
		  	$('#kfzTyp').children()[3].value = 'Cayenne';  $('#kfzTyp').children()[3].text = 'Cayenne';
		  }
		  if ($('#kfzHersteller').val() == "Audi") {
		  	$('#kfzTyp').children()[0].value = 'A3';  $('#kfzTyp').children()[0].text = 'A3';
		  	$('#kfzTyp').children()[1].value = 'A4';  $('#kfzTyp').children()[1].text = 'A4';
		  	$('#kfzTyp').children()[2].value = 'A6';  $('#kfzTyp').children()[2].text = 'A6';
		  	$('#kfzTyp').children()[3].value = 'A8';  $('#kfzTyp').children()[3].text = 'A8';
		  }
		  calculatePrice(preisindikationInCent);
  });	
   
  $('#kfzTyp').on('change', function() {  
		  calculatePrice(preisindikationInCent);
  });	

function calculatePrice(preisindikationInCent) {
	 if ($('#kfzHersteller').val() == "VW" && $('#kfzTyp').val()=="Beatle") { preisindikationInCent = 120}	
	 if ($('#kfzHersteller').val() == "VW" && $('#kfzTyp').val()=="Golf IV") {preisindikationInCent = 160}	
	 if ($('#kfzHersteller').val() == "VW" && $('#kfzTyp').val()=="Golf V") {preisindikationInCent = 150}	
	 if ($('#kfzHersteller').val() == "VW" && $('#kfzTyp').val()=="Passat") {preisindikationInCent = 150}	
	 if ($('#kfzHersteller').val() == "BMW" && $('#kfzTyp').val()=="318i") {preisindikationInCent = 190}	
	 if ($('#kfzHersteller').val() == "BMW" && $('#kfzTyp').val()=="525i") {preisindikationInCent = 210}	
	 if ($('#kfzHersteller').val() == "BMW" && $('#kfzTyp').val()=="735i") {preisindikationInCent = 240}	
	 if ($('#kfzHersteller').val() == "BMW" && $('#kfzTyp').val()=="X3") {preisindikationInCent = 280}	
	 if ($('#kfzHersteller').val() == "Porsche" && $('#kfzTyp').val()=="911") {preisindikationInCent = 310}	
	 if ($('#kfzHersteller').val() == "Porsche" && $('#kfzTyp').val()=="925") {preisindikationInCent = 300}	
	 if ($('#kfzHersteller').val() == "Porsche" && $('#kfzTyp').val()=="Boxster") {preisindikationInCent = 290}	
	 if ($('#kfzHersteller').val() == "Porsche" && $('#kfzTyp').val()=="Cayenne") {preisindikationInCent = 300}	
	 if ($('#kfzHersteller').val() == "Audi" && $('#kfzTyp').val()=="A3") {preisindikationInCent = 180}	
	 if ($('#kfzHersteller').val() == "Audi" && $('#kfzTyp').val()=="A4") {preisindikationInCent = 180}	
	 if ($('#kfzHersteller').val() == "Audi" && $('#kfzTyp').val()=="A6") {preisindikationInCent = 200}	
	 if ($('#kfzHersteller').val() == "Audi" && $('#kfzTyp').val()=="A8") {preisindikationInCent = 280}	
	 
	 $('#preisindikationInCent').val(preisindikationInCent + ",00 EUR");
}

function getPrice() {
	var preisindikationInCent;
	 if ($('#kfzHersteller').val() == "VW" && $('#kfzTyp').val()=="Beatle") { preisindikationInCent = 120}	
	 if ($('#kfzHersteller').val() == "VW" && $('#kfzTyp').val()=="Golf IV") {preisindikationInCent = 160}	
	 if ($('#kfzHersteller').val() == "VW" && $('#kfzTyp').val()=="Golf V") {preisindikationInCent = 150}	
	 if ($('#kfzHersteller').val() == "VW" && $('#kfzTyp').val()=="Passat") {preisindikationInCent = 150}	
	 if ($('#kfzHersteller').val() == "BMW" && $('#kfzTyp').val()=="318i") {preisindikationInCent = 190}	
	 if ($('#kfzHersteller').val() == "BMW" && $('#kfzTyp').val()=="525i") {preisindikationInCent = 210}	
	 if ($('#kfzHersteller').val() == "BMW" && $('#kfzTyp').val()=="735i") {preisindikationInCent = 240}	
	 if ($('#kfzHersteller').val() == "BMW" && $('#kfzTyp').val()=="X3") {preisindikationInCent = 280}	
	 if ($('#kfzHersteller').val() == "Porsche" && $('#kfzTyp').val()=="911") {preisindikationInCent = 310}	
	 if ($('#kfzHersteller').val() == "Porsche" && $('#kfzTyp').val()=="925") {preisindikationInCent = 300}	
	 if ($('#kfzHersteller').val() == "Porsche" && $('#kfzTyp').val()=="Boxster") {preisindikationInCent = 290}	
	 if ($('#kfzHersteller').val() == "Porsche" && $('#kfzTyp').val()=="Cayenne") {preisindikationInCent = 300}	
	 if ($('#kfzHersteller').val() == "Audi" && $('#kfzTyp').val()=="A3") {preisindikationInCent = 180}	
	 if ($('#kfzHersteller').val() == "Audi" && $('#kfzTyp').val()=="A4") {preisindikationInCent = 180}	
	 if ($('#kfzHersteller').val() == "Audi" && $('#kfzTyp').val()=="A6") {preisindikationInCent = 200}	
	 if ($('#kfzHersteller').val() == "Audi" && $('#kfzTyp').val()=="A8") {preisindikationInCent = 280}	
	 
	 return preisindikationInCent;
}

});
