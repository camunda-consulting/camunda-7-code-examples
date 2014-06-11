Sample plugin for camunda Cockpit showing delinquent process instances
=================================

This is a simple plugin that showcases how you can add a list of delinquent process instances to the 
cockpit homepage - as this is pretty handy for any kind of demo situation.

Built and tested against camunda BPM version `7.1.0-Final`.

![Screenshot][1]


How to use
----------------------

1. Build the plugin using maven with mvn clean install

2. Integrate the JAR in your camunda-*.war file and there under WEB-INF/lib

Now it will be shown on the start screen.


More information
-----

Read the [plugin development how to](http://docs.camunda.org/latest/real-life/how-to/#cockpit-how-to-develop-a-cockpit-plugin).


[1]: https://raw.github.com/camunda/camunda-consulting/master/snippets/cockpit-plugin-delinquent-instances/screenshot.png
