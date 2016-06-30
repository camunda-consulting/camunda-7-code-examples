var errorList = [];
print(claims);
var claimsParsed = JSON.parse(claims);
print('after parse check unique rsp id');
print(JSON.stringify(claimsParsed.data[0].rows));

print (claimsParsed.data[0].rows[0].repairServicePartnerId);
var firstRepairServicePartnerId = claimsParsed.data[0].rows[0].repairServicePartnerId;
for (i = 0; i < claimsParsed.data[0].rows.length; i++) {
	print (JSON.stringify(claimsParsed.data[0].rows[i]));
	if (claimsParsed.data[0].rows[i].repairServicePartnerId != firstRepairServicePartnerId) {
		errorList.push("errorMultipleRepairServicePartners");
	}
}
print(JSON.stringify(errorList));
errorList;
