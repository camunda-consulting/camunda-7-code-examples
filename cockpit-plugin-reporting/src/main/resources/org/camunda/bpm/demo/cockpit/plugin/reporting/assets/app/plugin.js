ngDefine('cockpit.plugin.reporting-process-count', 
		 ['http://code.highcharts.com/highcharts.js'], // refer to version in web to avoid thinking about licensing issues with Highcharts
		 function(module) {

  var DashboardController = function($scope, $http, Uri) {

    $http.get(Uri.appUri("plugin://reporting-process-count/default/process-instance-count"))
      .success(function(data) {
        printChart(data)
      });
  };

  DashboardController.$inject = ["$scope", "$http", "Uri"];
  
  var Configuration = function Configuration(ViewsProvider) {

	    ViewsProvider.registerDefaultView('cockpit.dashboard', {
	      id: 'process-definitions',
	      label: 'Process Instance Count',
	      url: 'plugin://reporting-process-count/static/app/dashboard.html',
	      controller: DashboardController,

	      // make sure we have a higher priority than the default plugin
	      priority: 12
	    });
	  };

	  Configuration.$inject = ['ViewsProvider'];

	  module.config(Configuration);

	  return module;  

//
//  var PluginConfiguration = function PluginConfiguration(PluginsProvider) {
//
//    PluginsProvider.registerDefaultPlugin('cockpit.dashboard', {
//      id: 'process-definitions',
//      label: 'Process Instance Count',
//      url: 'plugin://reporting-process-count/static/app/dashboard.html',
//      controller: DashboardController
//    });
//  };
//
//  PluginConfiguration.$inject = ['PluginsProvider'];
//
//  module.config(PluginConfiguration);
//
//  return module;
});


function printChart(data) {
	// create categories array
	processDefinitionKey = {};

	// and data
	seriesData = [{
        name: 'Failed',
        data: []
    }, {
        name: 'Running',
        data: []
    }, {
        name: 'Ended',
        data: []
    }];
	
	pieData = [];	
	overallSum = 0;
	for( var i=0; i<data.length; i++ ) {
         processDefinitionKey[i] = data[i].processDefinitionKey;
         seriesData[1].data[i] = parseInt(data[i].failedInstanceCount);
         seriesData[1].data[i] = parseInt(data[i].runningInstanceCount - data[i].failedInstanceCount); // failed are running in the query, but should not in the graph
         seriesData[2].data[i] = parseInt(data[i].endedInstanceCount);
         
         sum = seriesData[2].data[i] + seriesData[1].data[i] + seriesData[1].data[i];
         overallSum = overallSum + sum;
         
         pieData[i] = [data[i].processDefinitionKey, sum];
	}	
	
	$('#instance-count-bar').highcharts({
        chart: {
            type: 'bar'
        },
        colors: ['#910000', '#8bbc21', '#0d233a'],
        title: {
            text: 'instance count per state'
        },
        xAxis: {
            categories: processDefinitionKey,
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Number of instances',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' instances'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -100,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: '#FFFFFF',
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: seriesData
    });
	
	// -------------------
	$('#definition-pie').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'instance sum per process definition'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
        	percentageDecimals: 1
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
                        return '<b>'+ this.point.name +'</b>: '+ Math.round(this.percentage) +' %';
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'instances per process definition',
            data: pieData
        }]
    });

}

