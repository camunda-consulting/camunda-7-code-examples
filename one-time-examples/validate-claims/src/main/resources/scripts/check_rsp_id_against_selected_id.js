var errorList = [];

var claimsParsed = JSON.parse(claims);
print('after parse check rsp if against selected id');
print(selectedRepairServicePartnerId);

for (i = 0; i < claimsParsed.data[0].rows.length; i++) {
	print (JSON.stringify(claimsParsed.data[0].rows[i]));
	if (claimsParsed.data[0].rows[i].repairServicePartnerId != selectedRepairServicePartnerId) {
		errorList.push("errorSelectedRepairServicePartner");
	}
}
print(S(JSON.stringify(errorList), "application/json"));
S(JSON.stringify(errorList), "application/json");
