var errorList = errorList || [];
print(errorList);

var mandatoryNumberFields = ["serialNumberIn", "customerComplaintCodePrimary"];

var claimsParsed = JSON.parse(claims);
print('after parse check mandatory field format');

for (j = 0; j < mandatoryNumberFields.length; j++) {
	print(mandatoryNumberFields[j]);
	for (i = 0; i < claimsParsed.data[0].rows.length; i++) {
		print (JSON.stringify(claimsParsed.data[0].rows[i]));
//		print(claimsParsed.data[0].rows[i][mandatoryNumberFields[j]].search(/[a-z]/i));
		if (claimsParsed.data[0].rows[i][mandatoryNumberFields[j]] &&
				claimsParsed.data[0].rows[i][mandatoryNumberFields[j]].search(/[a-z]/i) > -1) {
			errorList.push("errorMandatoryFieldFormat");
		}
	}
	
}
print(JSON.stringify(errorList));
JSON.stringify(errorList);