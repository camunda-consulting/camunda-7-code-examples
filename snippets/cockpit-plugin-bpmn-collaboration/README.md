Collaborating BPMN processes in cockpit
========================

This example shows how two executable BPMN processes can interact with each other via messages on the [camunda BPM platform](http://camunda.org/) and how a 
[cockpit plugin] (http://docs.camunda.org/latest/guides/user-guide/#cockpit-plugins) can be developed to see the "linked" process instance there as own tab (comparable to call activites).

The first screenshot shows the "perform" process (which starts the whole collaboration) with a link to the "change" process:
![cockpit screenshot perform process][1]

The second screenshot shows the "change" process which was initiated by the "peform" process. It has a link to the "perform" process:
![cockpit screenshot change process][2]

# What will you learn?

1. How to build a cockpit plugin
1. How to send messages between processes

# The Process

![Collaboration between Perform and Change process][3]

# Getting Started

**Important:** The Plug-In-API evolved in version 7.1 - so the current code only works from 7.1.0-alpha2 on.

1. Download the [camunda BPM platform](http://camunda.org/) **(tested on 7.1.0-alpha3 using JBoss AS 7)** from [here](http://camunda.org/download.html).
1. Install it, start it
1. Clone this repository
1. Build  and deploy process application
    1. Build the example process application in `cockpit-plugin-bpmn-collaboration-process-example` with `mvn package`
    1. Deploy the generated WAR artifact
    1. Point your browser to tasklist (`http://localhost:8080/camunda/app/tasklist/`) and start a process `perform`
1. Build and deploy cockpit plugin
    1. Build the plugin in `cockpit-plugin-bpmn-collaboration` with `mvn package`
    1. Package the resulting JAR artifact INTO you `camunda-webapp-....war` in the `WEB-INF/lib` directory
    1. Point your browser to cockpit (`http://localhost:8080/camunda/app/cockpit/`) and to a process instance of the `perform` process and you can see the additional tab

# Further Resources

For help please ask at the [camunda BPM User Forum](http://camunda.org/community/forum.html).

[1]: https://raw.github.com/camunda/camunda-consulting/master/snippets/cockpit-plugin-bpmn-collaboration/screenshot1.png
[2]: https://raw.github.com/camunda/camunda-consulting/master/snippets/cockpit-plugin-bpmn-collaboration/screenshot2.png
[3]: https://raw.github.com/camunda/camunda-consulting/master/snippets/cockpit-plugin-bpmn-collaboration/cockpit-plugin-bpmn-collaboration-process-example\src\main\resources\collaboration.png
