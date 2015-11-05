ngDefine('cockpit.plugin.reporting-process-count', 
		 // ['http://code.highcharts.com/highcharts.js'], / refer to version in web to avoid thinking about licensing issues with Highcharts
		  ['./highcharts'], // you can use this as well if it is included in the plugin
		 //['http://localhost:8080/cockpit/api/cockpit/plugin/reporting-process-count/static/app/highcharts.js'],		 
		 function(module) {

  var DashboardController = function($scope, $http, Uri) {
    $http.get(Uri.appUri("plugin://reporting-process-count/:engine/process-instance-count"))
      .success(function(data) {
					printStartedInstances(data);
      });
  };


  var ProcessDefinitionKpiController = function($scope, $http, Uri) {
  	var baseUrl = "plugin://reporting-process-count/:engine/" + $scope.processDefinition.key;
    $http.get(Uri.appUri(baseUrl + "/process-instance-count"))
      .success(function(data) {
					printStartedInstances(data);
      });
    $http.get(Uri.appUri(baseUrl + "/cycle-time/hour"))
      .success(function(data) {
					printCycleTime(data);
      });
    $http.get(Uri.appUri(baseUrl + "/ratio"))
      .success(function(data) {
					printRatio(data);
      });
  };

  DashboardController.$inject = ["$scope", "$http", "Uri"];
  ProcessDefinitionKpiController.$inject = ["$scope", "$http", "Uri"];
  
  var Configuration = function Configuration(ViewsProvider) {

	    ViewsProvider.registerDefaultView('cockpit.dashboard', {
	      id: 'process-definitions',
	      label: 'Statistics',
	      url: 'plugin://reporting-process-count/static/app/dashboard.html',
	      controller: DashboardController,

	      // make sure we have a higher priority than the default plugin
	      priority: 12
	    });
	    
			ViewsProvider.registerDefaultView('cockpit.processDefinition.history.tab', {
				id: 'kpi',
				priority: -50,// put at the end
				label: 'KPI',
				url: 'plugin://reporting-process-count/static/app/processDefinition.html',
				controller: ProcessDefinitionKpiController
			});	    
	  };
	  

	  Configuration.$inject = ['ViewsProvider'];

	  module.config(Configuration);

	  return module;
});



function printCycleTime(data) {
	$('#cycleTimeInfo').html( data.name + "<br>(Calculated from <em>'" + data.startName + "'</em> to <em>'" + data.endName + "</em>')" );

	seriesData = [{
            name: 'Duration in Hours',
            data: []
          }];
  
  for( var i=0; i<data.timesPerDuration.length; i++ ) {
     seriesData[0].data.push([
     		data.timesPerDuration[i].duration + " h",
     		data.timesPerDuration[i].count 
     	]);
	}	
	
	$('#barChart').highcharts({
		 		title: {
		 			text: null
		 		},
		 		legend: {
		 			enabled: false
		 		},
        chart: {
            type: 'column'
        },
        xAxis: {
						type: 'category',    
            labels: {
                overflow: 'justify'
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: null,
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },       
        credits: {
            enabled: true
        },
        series: seriesData
    });	
}



function printRatio(data) {

	seriesData = [];
  for( var i=0; i<data.length; i++ ) {
         seriesData.push([data[i].optionName, data[i].count]);
	}	

	$('#pieChart').highcharts({
		 		title: {
		 			text: null
		 		},
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        tooltip: {
    	    pointFormat: '<b>{point.y}</b>'
        },
        plotOptions: {
            pie: {
                dataLabels: {
                    enabled: true,
                    formatter: function() {
                        return '<b>'+ this.point.name +'</b>: ' + Math.round(this.percentage*100)/100 + ' %';
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            data: seriesData
        }],
        credits: {
            enabled: true
        }
    });
}

function printStartedInstances(data) {
	
	var legend = {enabled: false};
	if (data.series.length > 1) {
		legend = {
			layout : 'vertical',
			align : 'right',
			verticalAlign : 'middle',
			borderWidth : 0,
			enabled: true
		}
	}
	
	$('#lineChart').highcharts({
 		title: {
 			text: null
 		},
		subtitle : {
			text : null,
			x : -20
		},
		xAxis : {
			categories : data.categories
		},
		yAxis : {
			title : {
				text : null
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
		legend : legend,
		series : data.series
	});
}


