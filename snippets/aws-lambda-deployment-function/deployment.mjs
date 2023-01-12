import fs from "fs";
import request from "request";

export default new Promise((resolve, reject) => {
  request.post(
    {
      url: "https://training.consulting-sandbox.camunda.cloud/engine-rest/deployment/create",
      formData: {
        "process-model": fs.createReadStream("diagram_1.bpmn"),
        "deployment-name": "test",
        "deployment-source": "js-client",
      },
    },
    function (error, response, body) {
      console.log(body);
      resolve(body);
    }
  );
});
