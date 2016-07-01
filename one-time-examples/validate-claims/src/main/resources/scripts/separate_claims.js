// get all the errors
print("all collected errors");

print(resultCheckMandatoryFields);
//print(JSON.parse(resultCheckUniqueRspID));

var mandatoryFieldErrors = JSON.parse(resultCheckMandatoryFields);
//var mandatoryFieldErrors = resultCheckMandatoryFields;
print(mandatoryFieldErrors)

for (i = 0; i < mandatoryFieldErrors.length; i++) {
	print(JSON.stringify(mandatoryFieldErrors[i]));
}