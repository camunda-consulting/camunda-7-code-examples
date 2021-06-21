const http = require('http');

const base_config = {
  hostname: 'localhost',
  port: 8080
}

async function evaluateDecision(decision_key) {
  return new Promise((resolve, reject) => {
    const data = JSON.stringify({
      "variables": {}
    })

    const req = http.request({
      path: '/engine-rest/decision-definition/key/' + decision_key + '/evaluate',
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Content-Length': data.length
      },
      ...base_config
    }, res => {
      res.on('data', d => {
        const decision_result = JSON.parse(d);

        // multi-output-column single result
        if(Array.isArray(decision_result) && decision_result.length === 1) {
          resolve(Object.fromEntries( Object.keys(decision_result[0]).map( varName => [varName, decision_result[0][varName].value ] )));
        }

      });
    });

    req.on('error', error => {
      console.error(error);
      reject(error);
    })

    req.write(data)
    req.end()
  });
}

async function startProcess(process_definition_key) {
  return new Promise((resolve, reject) => {
    const data = JSON.stringify({
      "withVariablesInReturn": true
    })

    const req = http.request({
      path: '/engine-rest/process-definition/key/' + process_definition_key + '/start',
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Content-Length': data.length
      },
      ...base_config
    }, res => {
      res.on('data', d => {
        resolve(JSON.parse(d));
      });
    });

    req.on('error', error => {
      console.error(error);
      reject(error);
    })

    req.write(data)
    req.end()
  });
}

async function nextTask(process_instance_id) {
  return new Promise((resolve, reject) => {
    const req = http.request({
      path: '/engine-rest/task?maxResults=1&processInstanceId=' + process_instance_id,
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      },
      ...base_config
    }, res => {
      res.on('data', d => {
        const response_data = JSON.parse(d);
        if(response_data.length == 1) {
          resolve(response_data[0]);
        } else if(response_data.length == 0) {
          reject("no more tasks");
        } else {
          reject("error");
        }
      });
    });

    req.on('error', error => {
      console.error(error);
      reject(error);
    })

    req.end()
  });
}

async function claimTask(task_id) {
  return new Promise((resolve, reject) => {
    const data = JSON.stringify({
      "userId": "someUser"
    })

    const req = http.request({
      path: '/engine-rest/task/' + task_id + '/claim',
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Content-Length': data.length
      },
      ...base_config
    }, res => {
      if(res.statusCode == 204) {
        resolve();
      } else {
        reject();
      }
    });

    req.on('error', error => {
      console.error(error);
      reject(error);
    })

    req.write(data)
    req.end()
  });
}


async function deployedForm(type, resource_id) {
  const path = (type == 'task' ?
      '/engine-rest/task/' + resource_id + '/deployed-form' :
      '/engine-rest/process-definition/key/' + resource_id + '/deployed-start-form');

  return new Promise((resolve, reject) => {
    const req = http.request({
      path: path,
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      },
      ...base_config
    }, res => {
      res.on('data', d => {
        const response_data = JSON.parse(d);
        resolve(response_data);
      });
    });

    req.on('error', error => {
      console.error(error);
      reject(error);
    })

    req.end()
  });
}

async function formVariables(type, resource_id, variableNames) {
  const path = (type == 'task' ?
      '/engine-rest/task/' + resource_id + '/form-variables?variableNames=' + variableNames.join(',') :
      '/engine-rest/process-definition/' + resource_id + '/form-variables?variableNames=' + variableNames.join(','));

  return new Promise((resolve, reject) => {

    const req = http.request({
      path: path,
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      },
      ...base_config
    }, res => {
      res.on('data', d => {
        const response_data = JSON.parse(d);
        resolve(response_data);
      });
    });

    req.on('error', error => {
      console.error(error);
      reject(error);
    })

    req.end()
  });
}


async function processStartForm(process_definition_key) {
  return new Promise((resolve, reject) => {
    var data = {
      "processIsRunning": false
    };
    data.formType = "camundaForm";
    deployedForm("process", process_definition_key).then((form) => {
      data.formSchema = form;
      resolve(data);
    }).catch( (error) => {
      if(error === "no more tasks") {
        resolve({
          "processIsRunning": false
        });
      }
    });
  });
}


async function nextForm(process_instance_id) {
  return new Promise((resolve, reject) => {
    var data = {
      "processIsRunning": true
    };
    nextTask(process_instance_id).then((task) => {
      data.taskId = task.id;
      claimTask(task.id).then(() => {
        if(task.formKey && task.formKey.startsWith("camunda-forms:")) {
          data.formType = "camundaForm";
          deployedForm("task", task.id).then((form) => {
            data.formSchema = form;
            const variableNames = form.components.map(field => field.key);
            formVariables("task", task.id, variableNames).then((variables) => {
              // convert from process variables to formData
              const formVariables = Object.fromEntries( Object.keys(variables).map(
                varName => [varName, variables[varName].value ]
              ));

              data.formVariables = formVariables;
              resolve(data);
            });
          });
        } else {
          data.formType = "customForm";
          data.formSchema = {
            key: task.formKey
          };
          resolve(data);
        }
      });
    }).catch( (error) => {
      if(error === "no more tasks") {
        resolve({
          "processIsRunning": false
        });
      }
    });
  });
}


async function completeTask(task_id, variables) {
  return new Promise((resolve, reject) => {
    const data = JSON.stringify({
      variables: variables
    });

    const req = http.request({
      path: '/engine-rest/task/' + task_id + '/complete',
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Content-Length': data.length
      },
      ...base_config
    }, res => {
      if(res.statusCode == 204) {
        resolve();
      } else {
        reject();
      }
    });

    req.on('error', error => {
      console.error(error);
      reject(error);
    })

    req.write(data)
    req.end()
  });
}

async function submitForm(task_id, formData) {
  return new Promise((resolve, reject) => {
    // convert from formData to process variables
    const variables = Object.fromEntries( Object.keys(formData).map(
      varName => [varName, {value: formData[varName] } ]
    ));
    completeTask(task_id, variables).then(() => {
      resolve();
    }).catch(console.log);
  });
}

module.exports = { evaluateDecision, startProcess, processStartForm, nextForm, submitForm }
