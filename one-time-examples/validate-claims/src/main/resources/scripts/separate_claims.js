// get all the errors
print("all collected errors");

print(resultCheckUniqueRspID);
print(resultCheckRspIDAgainstSelected);
print(resultCheckMandatoryFields);

var mandatoryFieldErrors = JSON.parse(resultCheckMandatoryFields);

for (i = 0; i < mandatoryFieldErrors.length; i++) {
	print(JSON.stringify(mandatoryFieldErrors[i]));
}