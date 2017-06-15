# Camunda BPM Process Application
A Process Application for [Camunda BPM](http://docs.camunda.org).

Spring Boot version of the tenant-specific-call-activity example. 

## How does it work?

Just run the Spring Boot application. This project requires to access the Camunda Enterprise Nexus repository in order to include the EE Webapps. If you don't have access, just strip out the ee-parts in the pom.xml.

Once you run the application you can start processes using
[Camunda Tasklist](http://docs.camunda.org/latest/guides/user-guide/#tasklist)
and inspect them using
[Camunda Cockpit](http://docs.camunda.org/latest/guides/user-guide/#cockpit).

## Environment Restrictions
Built and tested against Camunda BPM version 7.5.0.

## Known Limitations

## Improvements Backlog

## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).

<!-- HTML snippet for index page
  <tr>
    <td><img src="snippets/tenant-specific-call-activity/src/main/resources/process.png" width="100"></td>
    <td><a href="snippets/tenant-specific-call-activity">Camunda BPM Process Application</a></td>
    <td>A Process Application for [Camunda BPM](http://docs.camunda.org).</td>
  </tr>
-->
<!-- Tweet
New @CamundaBPM example: Camunda BPM Process Application - A Process Application for [Camunda BPM](http://docs.camunda.org). https://github.com/camunda/camunda-consulting/tree/master/snippets/tenant-specific-call-activity
-->
