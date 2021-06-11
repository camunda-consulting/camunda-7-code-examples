export default class Communication {
  async evaluateDecision(decisionKey) {
    return new Promise((resolve, reject) => {
      fetch('/api/decision/' + decisionKey + '/evaluate').
      then( async (response) => {
        if(response.ok) {
          const data = await response.json();
          resolve(data);
        }
      });
    });
  }

  async startProcess(processDefinitionKey) {
    return new Promise((resolve, reject) => {
      fetch('/api/process/' + processDefinitionKey + '/start').
      then( async (response) => {
        if(response.ok) {
          const data = await response.json();
          resolve(data);
        }
      });
    });
  }

  async processStartForm(processDefinitionKey) {
    return new Promise((resolve, reject) => {
      fetch('/api/process/' + processDefinitionKey + '/form').
      then( async (response) => {
        if(response.ok) {
          const data = await response.json();
          resolve(data);
        }
      });
    });
  }

  async getNextForm(processInstanceId) {
    return new Promise((resolve, reject) => {
      fetch('/api/nextForm/' + processInstanceId).
      then( async (response) => {
        if(response.ok) {
          const data = await response.json();
          resolve(data);
        }
      });
    });
  }

  async submitForm(taskId, data) {
    return new Promise((resolve, reject) => {
      fetch('/api/submitForm/' + taskId, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      }).then( async (response) => {
        if(response.ok) {
          const data = await response.json();
          resolve(data);
        }
      });
    });
  }
}
