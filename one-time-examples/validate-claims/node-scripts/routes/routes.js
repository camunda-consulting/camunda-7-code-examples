var appRouter = function(app) {
	app.get("/", function(req, res) {
		console.log("root request with GET");
		res.send("Hello World");
	});
	
	app.post("/check-mandatory-fields", function(req, res) {
		console.log("check-mandatory-fields request with POST");
		console.log(req.body);
		
		var errorList = [];
		console.log(errorList);

		// You will get these from the master data base
		var mandatoryNumberFields = ["serialNumberIn", "customerComplaintCodePrimary"];

		var claimsParsed = req.body;
		console.log('after parse check mandatory field format');

		for (j = 0; j < mandatoryNumberFields.length; j++) {
			console.log(mandatoryNumberFields[j]);
			for (i = 0; i < claimsParsed.data[0].rows.length; i++) {
				console.log (JSON.stringify(claimsParsed.data[0].rows[i]));
				if (claimsParsed.data[0].rows[i][mandatoryNumberFields[j]] &&
						claimsParsed.data[0].rows[i][mandatoryNumberFields[j]].search(/[a-z]/i) > -1) {
					errorList.push({fieldName: mandatoryNumberFields[j], 
									rowNumber: i});
				}
			}
		}
		console.log(JSON.stringify(errorList));
		return res.send(errorList);		
	});	
	
	app.post("/check-field-length", function(req, res) {
		console.log("request with POST");
		console.log(req.body);

		var errorList = [];
		
		var fieldLengths = {"materialNumber": {maxlength: 10}, "courierIn": {maxlength: 3}}; 

		var claimsParsed = req.body;
		console.log('after parse check field length');
		var maxLength;

		for (var fieldName in fieldLengths) {
			console.log(fieldName);
			for (i = 0; i < claimsParsed.data[0].rows.length; i++) {
//				console.log (JSON.stringify(claimsParsed.data[0].rows[i][fieldName].length));
				maxLength = fieldLengths[fieldName].maxlength;
				if (claimsParsed.data[0].rows[i][fieldName] &&
						claimsParsed.data[0].rows[i][fieldName].length > maxLength) {
					errorList.push({fieldName: fieldName, 
									rowNumber: i});
				}
			}
		}
		console.log(JSON.stringify(errorList));
		if (errorList.length > 4) {
			return res.status(403).send({tooManyErrors: errorList});
		} else {			
			return res.send(errorList);		
		}
	});		
	
}
 
module.exports = appRouter;