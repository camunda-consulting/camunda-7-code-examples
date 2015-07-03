ngDefine('cockpit.plugin.rechnungseingang', 
		 // ['http://code.highcharts.com/highcharts.js'], / refer to version in web to avoid thinking about licensing issues with Highcharts
		  ['./highcharts'], // you can use this as well if it is included in the plugin		 
		 function(module) {

  var DashboardController = function($scope, $http, Uri) {
    $http.get(Uri.appUri("plugin://rechnungseingang/:engine/statistics"))
      .success(function(data) {
        printData(data)
      });
  };

  DashboardController.$inject = ["$scope", "$http", "Uri"];
  
  var Configuration = function Configuration(ViewsProvider) {

    ViewsProvider.registerDefaultView('cockpit.processDefinition.runtime.tab', {
	      id: 'rechnungseingang',
	      label: 'Statistics',
	      url: 'plugin://rechnungseingang/static/app/dashboard.html',
	      controller: DashboardController,

	      priority: 2
	    });
  	};

	  Configuration.$inject = ['ViewsProvider'];

	  module.config(Configuration);

	  return module;  
});


function printData(data) {
	
	// Kuchen End States
	////////////////////////////////////////////	

	$('#pieChartRE').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'Ended Processes'
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
	$('#lineChartRE').highcharts({
		title : {
			text : 'Started Process Instances',
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

