Sample plugin for camunda Cockpit showing xml of process definitions
=================================

This is a simple plugin that shows you how to add a tab with the xml of the process definitions 
on its process definition page.

Built and tested against camunda BPM version `7.1.0-Final`.

![Screenshot][1]


How to use
----------------------

1. Build the plugin using maven with mvn clean install

2. Integrate the JAR in your camunda-*.war file and there under WEB-INF/lib

3. After starting the cockpit application it will be shown on the process definition screen.


More information
-----

Read the [plugin development how to](http://docs.camunda.org/latest/real-life/how-to/#cockpit-how-to-develop-a-cockpit-plugin).


[1]: https://raw.github.com/camunda/camunda-consulting/master/snippets/cockpit-plugin-process-xml/screenshot.png 