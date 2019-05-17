import requests
import json
import time

url = 'http://localhost:8080/engine-rest/external-task/fetchAndLock'

fetchAndLockPayload = {"workerId":"myExampleWorker",
  "maxTasks":1,
  "usePriority":"true",
  "topics":
      [{"topicName": "credit-charge",
      "lockDuration": 30000
      }]
}

try:
    while True: 

        # Fetch and Lock
        try:
            res = requests.post(url, json=fetchAndLockPayload)
            print('Fetch and lock status code: ', res.status_code)

        #Get Body Text
            body = res.text    
            print('Fetch and lock response: ', body)    
                    
        except: 
            print('Engine is down')

        while body == '[]':
            time.sleep(5)
            res = requests.post(url, json=fetchAndLockPayload)
            body = res.text
            print ('Fetch and lock response: ', body)
            
            if body !='[]':
                break

        # Put you Business Logic here
        print('Deduct existing credit')

        #Prepare url with task id and the response body
        response = json.loads(body)
        taskId = response[0]['id']
        taskId = str(taskId)

        complete_url = ('http://localhost:8080/'
        'engine-rest/external-task/'
        + taskId + '/complete')

        response = {
          "workerId": "myExampleWorker",   
        }

        # API call (Complete)
        try:
            complete = requests.post(complete_url, json=response)
            print('complete status code: ', complete.status_code)

        except: 
            print('failed')

except KeyboardInterrupt:
    pass