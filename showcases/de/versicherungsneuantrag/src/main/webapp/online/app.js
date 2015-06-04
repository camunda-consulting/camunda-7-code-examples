
$( document ).ready(function() {

	camClient = new CamSDK.Client({
		apiUri: 'http://localhost:8080/engine-rest/'
	});


	// Start single Process Instance
	$('#triggerStartApplication').click(function() {

		var neuantrag = {
			"antragssteller" : {
				   "name":         $('#antragstellerName').val(),
				   "geschlecht":   $('#selectSex').val(),
				   "geburtsdatum": "1979-04-28T00:00:00",
				   "email":        $('#email').val()
				  },
			"fahrzeugHersteller": $('#kfzHersteller').val(),
		  "fahrzeugTyp":       $('#kfzTyp').val(),
			"versicherungsprodukt": "Camundanzia Vollkasko Plus"
			};
			
			console.log(neuantrag);

		var resource = camClient.resource("process-definition");
		
		// {"antragssteller":{"vorname":null,"nachname":null,"geburtsdatum":170926761061},"fahrzeug":{"hersteller":"VW","typ":"Golf IV"},"fahrerUeber25":false,"versicherungsprodukt":"Camundanzia Vollkasko Plus"}

		resource.submitForm ({
			key: "versicherungsneuantrag",
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

});