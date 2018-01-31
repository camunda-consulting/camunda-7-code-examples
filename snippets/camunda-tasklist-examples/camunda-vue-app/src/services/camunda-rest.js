import axios from 'axios';

class CamundaRest {
  static ENGINE_REST_ENDPOINT = '/engine-rest/';

  static getProcessDefinitions() {
    return axios.get(`${CamundaRest.ENGINE_REST_ENDPOINT}process-definition`);
  }

  static deployProcessDefinition(file) {
    return axios.post(`${CamundaRest.ENGINE_REST_ENDPOINT}deployment/create`, file);
  }

  static getFormKey(processDefinitionKey) {
    return axios.get(`${CamundaRest.ENGINE_REST_ENDPOINT}process-definition/key/${processDefinitionKey}/startForm`);
  }

  static postProcessInstance(processDefinitionKey, values) {
    return axios.post(`${CamundaRest.ENGINE_REST_ENDPOINT}process-definition/key/${processDefinitionKey}/start`, values);
  }

  static getTasks() {
    return axios.get(`${CamundaRest.ENGINE_REST_ENDPOINT}task?sortBy=created&sortOrder=desc`);
  }

  static getTaskFormKey(taskId) {
    return axios.get(`${CamundaRest.ENGINE_REST_ENDPOINT}task/${taskId}/form`);
  }

  static postCompleteTask(taskId, values) {
    return axios.post(`${CamundaRest.ENGINE_REST_ENDPOINT}task/${taskId}/complete`, values);
  }

  static getTaskVariables(taskId, variableNames) {
    const variableName = Object.keys(variableNames).join(',');
    return axios.get(`${CamundaRest.ENGINE_REST_ENDPOINT}task/${taskId}/form-variables?variableNames=${variableName}`);
  }
}

export default CamundaRest;
