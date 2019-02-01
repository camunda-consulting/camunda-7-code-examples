import requests
import json
import time

#Define API Endpoint
url = 'http://localhost:8080/engine-rest/external-task/fetchAndLock'

# Define Json
task= {"workerId":"Nele",
  "maxTasks":2,
  "usePriority":"true",
  "topics":
      [{"topicName": "work",
      "lockDuration": 10000
      }]
}


# API call (Fetch and Lock)
try:
    res = requests.post(url, json=task)
    print(res.status_code)

#Get Body Text
    
    body = res.text    
    print(body)    
            
except: 
    print('Engine is down')

while body == '[]':
    print (body)
    res = requests.post(url, json=task)
    time.sleep(5)
    
    if body !='[]':
        break

# Put you Business Logic here

#Prepare url with task id and the response body
response = json.loads(body)
x = response[0]['id']
x = str(x)

complete_url = 'http://localhost:8080/engine-rest/external-task/'+x+'/complete'

response= {
  "workerId": "Nele",   
 }

# API call (Complete)
try:
    complete = requests.post(complete_url, json=response)
    print(complete.status_code)

except: 
    print('fail')

