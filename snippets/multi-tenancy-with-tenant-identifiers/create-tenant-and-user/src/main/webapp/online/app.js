
$( document ).ready(function() {

  // Removed Auth
	//		        	beforeSend = function(xhr) {
	//		                 xhr.setRequestHeader('Authorization', 'Basic ' + window.btoa(unescape(encodeURIComponent('${camunda.rest.username}' + ':' + '${camunda.rest.password}'))))
	//		          };


	var pathArray = window.location.pathname.split( '/' );
	var baseUrl = window.location.protocol + "//" + window.location.host + "/" + pathArray[1] + "/api";

	$.ajax({
		url: "http://localhost:8080/engine-rest/tenant",
		crossDomain: true
	}).then(function(data){
		for (var i=0; i < data.length; i++){
			console.log(data[i].id + " : " + data[i].name);
			$('#tenantName').append($('<option>', {value: data[i].id, text: data[i].name}));
		}
	})

  // Start single Process Instance
  $('#triggerStartApplication').click(function() {


        var newtenant = {
          "tenantName"     : $('#tenantName').val(),
          "user" : {
               "firstName":  $('#firstName').val(),
               "lastName":   $('#lastName').val(),
               "email":      $('#email').val(),
               "userName":   $('#userName').val(),
               "password":   $('#password').val(),
               "userType":   "cockpit"
              },
          "allowedProcessDefinitions":  ["invoice"]
          };

          // enhancement for further versions: select process definitions in registration form

        var data = JSON.stringify(newtenant);
        var url = baseUrl + "/create-tenant-specific-user/newrequest";

        console.log( data );

        $.ajax({
              type: 'POST',
              url: url,
              data: data,
              contentType: 'application/json; charset=utf-8',
              dataType: 'json',
              success: function (info) {
                  $('#signUpReceived').toggle();
                  $('#fieldsetForm').toggle();
              },
              crossDomain: true,
          });

  });

	$('#triggerAddUser').click(function() {


				var newuser = {
					"tenantName"     : $('#tenantName').val(),
					"user" : {
							 "firstName":  $('#firstName').val(),
							 "lastName":   $('#lastName').val(),
							 "email":      $('#email').val(),
							 "userName":   $('#userName').val(),
							 "password":   $('#password').val(),
							 "userType":   $('#userType').val()
							},
					"allowedProcessDefinitions":  ["invoice"]
					};

					//TODO: process definition binding

				var data = JSON.stringify(newuser);
				var url = baseUrl + "/create-tenant-specific-user/newrequest";

				console.log( data );

				$.ajax({
							type: 'POST',
							url: url,
							data: data,
							contentType: 'application/json; charset=utf-8',
							dataType: 'json',
							success: function (info) {
									$('#addUserReceived').fadeToggle(600);
									$('#addUserReceived').fadeToggle(1000);
							},
							crossDomain: true,
					});

	});

});
