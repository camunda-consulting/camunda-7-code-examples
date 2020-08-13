# Staymunda - Hotel Check In -Springboot Application - Automating Business Rules with DMN 

**This is a demonstration showcasing the following:**

1. Use of DMN to implement businesss rules
2. Use of DRD to design complex decisions
3. Use of Cockpit to demonstrate transparency and agility on business rules

**The story behind this demo is the following.**

Hotel Staymunda has been operating since 2020. Since it's inception the hotel tried different ways to implement business rules. However, it always had problems finding a right software solution that provided both Business and IT alignment. Or in other words a solution that provided transparency and agility with business rules. Until... it found Camunda.  

In this demo a BPMN and DMN process is used to help determine the VIP benefits of the guests that are checking in. See BPMN file under resources folder to better understand. 

Presentation of this demo can be found here https://docs.google.com/presentation/d/15ZdSm2wDi9-RyVMQ1Qjmj_dEjwhWtHRJxs6byigjJiA/edit?usp=sharing

## How to run the demo


To use this demonstration locally, please follow these steps:

1. Clone the repository locally.
2. Copy the 'settings.xml' file (from the root of the local repository you just created) into your local home .m2 directory.  For example, if your home directory is "C:\Users\Joe", then copy the 'settings.xml' file to 'C:\Users\Joe\.m2\settings.xml'.
3. Run `mvn clean package -DskipTests` in the child `meetup` directory.
6. Login to Camunda BPM at [http://localhost:8080/app/cockpit/](http://localhost:8080/app/cockpit/).
7. Login using "demo"/"demo".
8. Input license if wanting to use Enterprise Edition
8. Follow the steps on the next section.



## How to walk through the demo

1. Start off by explaining story behind demo written above
2. Explain the BPMN process
3. Show Cockpit and unstarted process
4. Check in guest at http://localhost:8080/checkin
4. Show Cockpit and analyze how BPMN traversed and how DMN got evaluated
3. Complete human task from Tasklist
4. Show history in Cockpit
5. Start multiple instances from http://localhost:8080/checkin and see different DMN outputs in Cockpit
6. Make changes to DMN with Live Editing due to no more room upgrades as per business requirement. This rule can be changed in the Determine Benefit decision table. 
7. Modify process instances (back track all recently started processes) to show how new edited rules can take effect
9. Make any change to BPMN, re-deploy from Modeler and use migration 



## DEV:  How to update the UI that starts the check in BPMN process
The start form is accessible from here:

http://localhost:8080/checkin

In the directory structure of the Camunda BPM project, it is located at src/main/resources/templates/index.html.  It uses both the Thymeleaf framework as well as the Bootstrap project for styling.  Supporting files are located directly under the templates directory.

More on working with Springboot and Thymeleaf can be found here https://spring.io/guides/gs/serving-web-content/

## DEV:  How to update variables that are passed in to Camunda

This Springboot app has a controller that is used to receive the check in request from the index.html website. After the request is received (by CheckInController.java). Some random variables are created and then passed on to Camunda Engine via Rest API call as a JSON object. A Rest API call and a JSON object was preferred  to show how easy it is to parse a JSON object using DMN-FEEL in the latest Camunda Engine version. 





