package com.example.test;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CamundaHelper {

    private final String baseUrl;
    private final OkHttpClient client = new OkHttpClient();
    
    private static final Logger LOGGER = Logger.getLogger("CamundaHelper");

    public CamundaHelper(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void deployBpmnDiagram(String bpmnPath) throws IOException {
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("deployment-name", "test-deployment")
                .addFormDataPart("data", "java-script-external.bpmn",
                        RequestBody.create(java.nio.file.Path.of(bpmnPath).toFile(), MediaType.parse("application/octet-stream")));

        Request request = new Request.Builder()
                .url(baseUrl + "/deployment/create")
                .post(bodyBuilder.build())
                .build();

        client.newCall(request).execute().close();
    }

    public ProcessInstance startProcessInstance(String processDefinitionKey, Map<String, Object> variables) throws IOException {
        String json = "{ \"variables\": " + buildVariableJson(variables) + " }";

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(baseUrl + "/process-definition/key/" + processDefinitionKey + "/start")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return ProcessInstance.fromJson(Objects.requireNonNull(response.body()).string());
        }
    }

    public boolean checkElement(String instanceId, String activityId, boolean passed) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            String history = get("/history/activity-instance?processInstanceId=" + instanceId + "&finished" + passed);
            LOGGER.info("history: " + history);
            boolean condition = history.contains("\"activityName\":\"" + activityId + "\"");

            if (condition) return true;
            TimeUnit.SECONDS.sleep(1);
        }
        return false;
    }
    

    public boolean isUserTaskActive(String instanceId, String taskName) throws IOException {
    	LOGGER.info("isUserTaskActive for process:" +  instanceId);
    	String response = get("/task?processInstanceId=" + instanceId);
        return response.contains("\"name\":\"" + taskName + "\"");
    }
    
    public boolean getTaskHistory(String instanceId) throws IOException {
    	LOGGER.info("external-task for process:" +  instanceId);
    	String response = get("/external-task??processInstanceId=" + instanceId);
        return response.contains("\"name\":\"" + "test" + "\"");
    }

    public String getUserTaskId(String instanceId, String taskName) throws IOException {
        String response = get("/task?processInstanceId=" + instanceId);
        int nameIndex = response.indexOf("\"name\":\"" + taskName + "\"");
        if (nameIndex == -1) throw new RuntimeException("Task not found");

        int idStart = response.lastIndexOf("\"id\":\"", nameIndex) + 6;
        int idEnd = response.indexOf("\"", idStart);
        return response.substring(idStart, idEnd);
    }

    public void completeUserTask(String taskId) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + "/task/" + taskId + "/complete")
                .post(RequestBody.create("{}", MediaType.parse("application/json")))
                .build();
        client.newCall(request).execute().close();
    }

    public boolean instanceIsCompleted(String instanceId) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            String response = get("/history/process-instance/" + instanceId);
            if (response.contains("\"endTime\"")) return true;
            TimeUnit.SECONDS.sleep(1);
        }
        return false;
    }

    private String get(String path) throws IOException {
        Request request = new Request.Builder()
        		.url(baseUrl + path)
//        		.url(path)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        }
    }

    private String buildVariableJson(Map<String, Object> vars) {
        StringBuilder json = new StringBuilder("{");
        for (var entry : vars.entrySet()) {
            Map<String, String> valueMap = (Map<String, String>) entry.getValue();
            json.append("\"").append(entry.getKey()).append("\":{")
                    .append("\"value\":\"").append(valueMap.get("value")).append("\",")
                    .append("\"type\":\"").append(valueMap.get("type")).append("\"},");
        }
        if (json.charAt(json.length() - 1) == ',') {
            json.setLength(json.length() - 1);
        }
        return json.append("}").toString();
    }

    public static class ProcessInstance {
        public String id;

        public static ProcessInstance fromJson(String json) {
            int idStart = json.indexOf("\"id\":\"") + 6;
            int idEnd = json.indexOf("\"", idStart);
            ProcessInstance pi = new ProcessInstance();
            pi.id = json.substring(idStart, idEnd);
            return pi;
        }
    }
}
