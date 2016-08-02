Authentication Filter for Camunda Webapp for SSO
=========================

This snippet demonstrates a custom [https://docs.camunda.org/javadoc/camunda-bpm-platform/7.5/?org/camunda/bpm/webapp/impl/security/auth/AuthenticationFilter.html](AuthenicationFilter). Such a filter can be the basis to hook in SSO into Camunda Webapp.

The provided [src/main/java/com/camunda/demo/sso/AutoLoginAuthenticationFilter.java](AutoLoginAuthenticationFilter) is a very simple version and logs on a user which is provided by an URL parameter.

You can simply login with the user "demo" by simply go to:

- Tasklist: [http://localhost:8080/camunda/app/tasklist/?auto-login-username=admin](http://localhost:8080/camunda/app/tasklist/?auto-login-username=admin)

- Cockpit: [http://localhost:8080/camunda/app/cockpit/?auto-login-username=admin](http://localhost:8080/camunda/app/cockpit/?auto-login-username=admin)

*WARNING:* The current limitation can only deal with one default process engine.

How to use it?
--------------

Run

 mvn install


Copy the resulting jar into your camunda webapp (WEB-INF/lib filder).

Edit your web.xml to point to this filter:

  <!-- Authentication filter -->
  <filter>
    <filter-name>Authentication Filter</filter-name>
    <filter-class>com.camunda.demo.sso.AutoLoginAuthenticationFilter</filter-class>
  </filter>
  ...

Environment Restrictions
------------------------

Built and tested against Camunda BPM version 7.5.0 using WildFly Application Server.


License
-------

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).
