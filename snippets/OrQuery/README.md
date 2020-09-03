# Springboot - Camunda And/Or Query example 

**The purpose of this example is the following :**

 A demonstration on how to query User Tasks given multiple **and** and **or** requirements based on process variables or candidate/assignment data. 
 

## How to setup the example


To use this example locally, please follow these steps:

1. Clone the repository locally.
directory. 
2. Run `mvn clean package -DskipTests` in /OrQuery directory
3. Run command `mvn spring-boot:run` in /OrQuery directory
4. Login to Camunda BPM at [http://localhost:8080/camunda/app/cockpit/default/#/login](http://localhost:8080/camunda/app/cockpit/default/#/login).
5. Login using "demo"/"demo".
6. Input license if wanting to use Enterprise Edition
7. Follow the steps on the next section to start a process



##How does the query work

1. The query can be found under /OrQuery/src/main/java/com/camunda/com/query/QueryController.java
2. It does the following: 

First it gets all tasks where Candidate Group = "camunda-admin" **and** Business Unit is "HR123". 

Then gets all tasks that have Candidate User = "demo1" **or** Age  ="20"

Later gets all tasks where Country ="MEX" **and** Currency = "EUR"

Finally it removes all duplicate tasks from the list and returns the list size



##How to test query

1.Send  a POST request to ```http://localhost:8080/engine-rest/message```   using  ```https://docs.camunda.org/manual/7.13/reference/rest/message/post-message/``` Rest API and  include the following variables in the request Body 

```
Business Unit "HR1234"
Age "21"
Country "MU"
Currency "EUR"
```
2.Do a GET request to ```http://localhost:8080/taskQuery``` and it should return 0 tasks

3.Change POST request variables or QueryController queries and repeat step 1 and 2 as needed. Number of tasks returned should change if queries from QueryController match. 
  







