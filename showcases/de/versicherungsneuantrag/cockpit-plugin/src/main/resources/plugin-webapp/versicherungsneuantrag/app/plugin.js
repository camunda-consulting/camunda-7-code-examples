ngDefine('cockpit.plugin.versicherungsneuantrag', 
		 // ['http://code.highcharts.com/highcharts.js'], / refer to version in web to avoid thinking about licensing issues with Highcharts
		  ['./highcharts'], // you can use this as well if it is included in the plugin		 
		 function(module) {

  var DashboardController = function($scope, $http, Uri) {
    $http.get(Uri.appUri("plugin://versicherungsneuantrag/:engine/statistics"))
      .success(function(data) {
        printData(data)
      });
  };

  DashboardController.$inject = ["$scope", "$http", "Uri"];
  
  var Configuration = function Configuration(ViewsProvider) {

    ViewsProvider.registerDefaultView('cockpit.processDefinition.runtime.tab', {
	      id: 'versicherungsneuantrag',
	      label: 'Statistik',
	      url: 'plugin://versicherungsneuantrag/static/app/dashboard.html',
	      controller: DashboardController,

	      priority: -50
	    });
  	};

	  Configuration.$inject = ['ViewsProvider'];

	  module.config(Configuration);

	  return module;  
});


function printData(data) {
	// AUTRAGSVOLUMEN INFO
	////////////////////////////////////////////	
	$('#openApplicationSum').html( data.openApplicationSum );
	
	// Kuchen End States
	////////////////////////////////////////////	

	$('#pieChart').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'Anzahl beendete Prozesse'
        },
        tooltip: {
    	    pointFormat: '<b>{point.y}</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    formatter: function() {
                        return '<b>'+ this.point.name +'</b>: '+ this.point.y +' ';
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Endzustaende',
            data: data.endEvents
        }],
        credits: {
            enabled: true
        }
    });


	// Linien Instanz-Count
	////////////////////////////////////////////	
	$('#lineChart').highcharts({
		title : {
			text : 'Anzahl gestarteter Prozessinstanzen',
			x : -20
		// center
		},
		subtitle : {
			text : '(letzte 14 Tage)',
			x : -20
		},
		xAxis : {
			categories : data.instanceCountChart.categories
		},
		yAxis : {
			title : {
				text : 'Anzahl'
			},
			plotLines : [ {
				value : 0,
				width : 1,
				color : '#808080'
			} ],
			min : 0
		},
		tooltip : {
			valueSuffix : ''
		},
		legend : {
			layout : 'vertical',
			align : 'right',
			verticalAlign : 'middle',
			borderWidth : 0
		},
		series : data.instanceCountChart.series
	});
}

