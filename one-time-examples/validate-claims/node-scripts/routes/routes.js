var appRouter = function(app) {
	app.get("/", function(req, res) {
		console.log("root request with GET");
		res.send("Hello World");
	});
	
	app.post("/check-mandatory-fields", function(req, res) {
		console.log("account request with POST");
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
//					errorList.push("{errorMandatoryFieldFormat: {\"" + mandatoryNumberFields[j] + "\", \"" 
//							+ i + "\"}}");
					errorList.push({fieldName: mandatoryNumberFields[j], 
									rowNumber: i}
							);
				}
			}
			
		}
		console.log(JSON.stringify(errorList));
		return res.send(JSON.stringify(errorList));		
//		if(!req.body.username || !req.body.password || !req.body.twitter) {
//			return res.send({"status": "error", "message": "missing a parameter"});
//		} else {
//			return res.send(req.body);
//		}
	});	
	
	app.get("/account", function(req, res) {
		var accountMock = {
			"username": "nraboy",
			"password": "1234",
			"twitter": "@nraboy"
		}
		console.log("account request with GET");
		if(!req.query.username) {
			return res.send({"status": "error", "message": "missing username"});
		} else if(req.query.username != accountMock.username) {
			return res.send({"status": "error", "message": "wrong username"});
		} else {
			return res.send(accountMock);
		}
	});
	
}
 
module.exports = appRouter;