
function loadStaff () {

	$("#staffTableContainer").empty();

	var staffTable = "<table class='table'><th>Mitarbeiter/in</th><th>Benutzername</th><th colspan='2'>Derzeit anwesend</th><th colspan='2'>Vertreter</th></tr>";

	$.getJSON('http://localhost:8080/absence/rest/staff', function(data) {
		$(data).each (function () {
			staffTable += "<tr id='entry_" + this.username + "'><td>" + this.name + "</td><td>" + this.username + "</td><td class='absence " + this.absent +"'></td><td><button username='" + this.username + "' toggleValue='" + this.absent + "' href='' class='toggleAbsence btn btn-default btn-xs'>Switch</button></td><td><span class='replacement'>" + getUser(this.vertreter, data).name + "</span></td><td><button username='" + this.username + "' class='switchReplacement btn btn-default btn-xs'>Switch</button></td></tr>";
		});

		staffTable += "</table>";

		$("#staffTableContainer").append (staffTable);

		$(".toggleAbsence").click (function() {
			toggleAbsence ($(this).attr("username"), data);
		});

		$(".switchReplacement").click (function() {
			user = getUser($(this).attr("username"), data);

			$('#myModal #myModalLabel').text ('Vertreter für ' + user.name);
			
			var myModalBody = "";

			var selectReplacement = "";
			$(data).each (function () {
				if (this.username != user.username) {
					selectReplacement += "<option value='" + this.username + "'>" + this.name + "</option>";
				}
			});

			console.log (selectReplacement);

			myModalBody += '<div class="row"><form class="form-horizontal col-md-8" role="form">' + 
								'<div class="form-group">'  + 
									'<label class="col-sm-2 control-label">Aktuell</label>' +
									'<div class="col-sm-10">' +
								      '<input class="form-control" type="text" readonly value="' + getUser(user.vertreter, data).name  + '" />' + 
								    '</div>' + 									
								'</div>' + 
								'<div class="form-group">'  + 
									'<label class="col-sm-2 control-label">Neu</label>' +
									'<div class="col-sm-10">' +
								      '<select id="selectReplacement" class="form-control" size="1">' + selectReplacement + ' </select>' +
								    '</div>' + 									
								'</div>' + 
							'</form></div>';

			$('#myModal #chooseReplacement').empty();
			$('#myModal #chooseReplacement').append (myModalBody);

			$('#myModal #saveReplacement').click (function () {
				var newReplacement = $('#myModal #selectReplacement').val();
				getUser(user.username, data).vertreter = newReplacement;
				saveJson (data);
				$('#myModal').modal('hide');
				$('#entry_' + user.username + ' .replacement').text(getUser(newReplacement, data).name);
			});


			$('#myModal').modal();
		});

	});

}

function getUser (username, users) {
	var result;
	$(users).each (function () {
		if (this.username == username) {
			result = this
		}
	});
	return result;
}

function toggleAbsence (username, users, callback) {
	
	if (getUser(username, users).absent == "true") {

		getUser(username, users).absent = "false";
		$('#entry_' + username + ' .absence').removeClass('true');
		$('#entry_' + username + ' .absence').addClass('false');

	} else if (getUser(username, users).absent == "false") {

		getUser(username, users).absent = "true";
		$('#entry_' + username + ' .absence').removeClass('false');
		$('#entry_' + username + ' .absence').addClass('true');

	}

	saveJson(users);
}



function saveJson (users) {


	$.ajax({
	    type : "POST",
	    url : "http://localhost:8080/absence/rest/staff",
	    contentType: 'application/json; charset=utf-8',
	    dataType : 'json',
        async: true,
	    data : JSON.stringify(users) /* convert here only */
	    ,
	    success: function () {
	    	console.log ("Json saved");
	    },
        failure: function() {
        	console.log ("Error!");
        }
	});

}



loadStaff();