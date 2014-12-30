'use strict';
/* jshint node:true */

var CamSDK = require('camunda-bpm-sdk-js');
var camClient = new CamSDK.Client({mock: false, apiUri: 'http://localhost:8080/engine-rest'});

var processDefinitionService  = new camClient.resource('process-definition');


// clear the terminal
console.log('\u001B[2J\u001B[0;0f');

// query process definitions
processDefinitionService.list({ latest: true, active: true}, function (err, results) {

definitions.forEach(definition) {definition.name || 
  console.log('start process instance ' + definition.name);
  processDefinitionService.submit({id: definition.id}, function (err) {
    console.log(err);
  });
};




function listProcessInstances() {
  // get the list of process instances
  processInstanceService.list({}, function (err, instances) {
    thr(err);

    // collect the relevant process definitions in a array (suitable for CamSDK.utils.series())
    var processDefinitionRequests = {};
    instances.items.forEach(function (instance) {
      if (!processDefinitionRequests[instance.definitionId]) {
        processDefinitionRequests[instance.definitionId] = function (cb) {
          processDefinitionService.get(instance.definitionId, cb);
        };
      }
    });

    // perform the requests for the process definitions
    CamSDK.utils.series(processDefinitionRequests, function (err, definitions) {
      thr(err);

      var table = new Table({
        head: [
          'Instance ID',
          'Process name',
          'Version',
          'Description'
        ],
        colWidths: [
          38,
          38,
          10,
          40
        ]
      });

      instances.items.forEach(function (instance) {
        var definition = definitions[instance.definitionId];
        table.push([
          instance.id,
          definition.name || '',
          definition.version || '',
          definition.description || ''
        ]);
      });

      home(table.toString());
    });
  });
}



function listTasksByFilter() {
  // list the tasks filters
  filterService.list({
    resourceType: 'Task'
  }, function (err, filterResults) {
    thr(err);

    // format the results to suite the inquirer choices
    var filters = filterResults.map(function (filter) {
      return {
        value: filter.id,
        name: filter.name
      };
    });

    // ask for which filter the tasks should be listed
    inquirer.prompt([
      {
        type: 'list',
        name: 'filterId',
        message: 'For which filter do you want the tasks?',
        choices: filters
      }
    ], function (answers) {
      var selectedFilter = byId(filterResults, answers.filterId);

      // get the filtered results
      filterService.getTasks(selectedFilter.id, function (err, taskResults) {
        thr(err);

        var count = (taskResults._embedded && taskResults._embedded.task) ? taskResults._embedded.task.length : 0;

        if (!count) {
          return home('No task for filter  "' + selectedFilter.name + '"');
        }

        var table = new Table({
          head: [
            'Task ID',
            'Process name',
            'Task name'
          ],
          colWidths: [
            38,
            38,
            38
          ]
        });

        taskResults._embedded.task.forEach(function (task) {
          table.push([
            task.id,
            task._embedded.processDefinition[0].name,
            task.name
          ]);
        });

        home(table.toString());
      });
    });
  });
}

