import requests
import time
import threading
import logging
from camunda.external_task.external_task import ExternalTask, TaskResult
from camunda.external_task.external_task_worker import ExternalTaskWorker

# try:
#     import http.client as http_client
# except ImportError:
#     # Python 2
#     import httplib as http_client
# http_client.HTTPConnection.debuglevel = 1
# configuration for the Client
# logging.basicConfig()
# logging.getLogger().setLevel(logging.DEBUG)
# requests_log = logging.getLogger("requests.packages.urllib3")
# requests_log.setLevel(logging.DEBUG)
# requests_log.propagate = True

default_config = {
    "maxTasks": 1,
    "lockDuration": 10000,
    "asyncResponseTimeout": 5000,
    "retries": 3,
    "retryTimeout": 5000,
    "sleepSeconds": 30
}

def handle_task(task: ExternalTask) -> TaskResult:
    key = task.get_business_key()

    print("business key from task event", key)
    # create a seperated thread for sendTaskResponse in
    e = threading.Event()
    t1 = threading.Thread(target=sendTaskResponse, args=[key])
    t1.start()
    print("create task event")

    return task.complete({"var1": 1, "var2": "value"})

def sendTaskResponse(businessKey):

    print("using businesskey", businessKey)
    time.sleep(2)
    url = "http://localhost:8080/engine-rest/message"
    data = {"businessKey": businessKey, "messageName": "sample_response"}
    print("used url is " ,url)
    print("used json data is ", data)
    response = requests.post(url, headers={'Content-Type': 'application/json'}, json=data)

    if response.status_code == 204:
        print("Request successful")
        print(response)
    else:
        print("Request failed")
        print(response)

def doExternalTaskWorker(): 
    print("Calling external taskworker")
    task = ExternalTaskWorker(worker_id="1", config=default_config).subscribe("messageSending", handle_task)
    print(task)
        
if __name__ == '__main__':
    doExternalTaskWorker()