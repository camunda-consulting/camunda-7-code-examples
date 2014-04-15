Sample Reporting plugin for camunda Cockpit
=================================

This is a simple plugin that showcases how easy you can build some dashboard as cockpit plugin for [camunda BPM](http://docs.camunda.org).

Built and tested against camunda BPM version `7.1.0-Final`.

![Screenshot][1]

Highcharts is not free or open source!
----------------------

Please note that I used [Highcharts](http://www.highcharts.com/) for that example which is NOT free.
You have to buy a license in order to use the plugin shown here, see [Highcharts FAQ](http://shop.highsoft.com/faq). 
Or you have to replace it with some other JavaScript libraries drawing charts.


How to use
----------------------

1. Build the plugin using maven with mvn clean install

2. Integrate the JAR in your camunda-*.war file and there under WEB-INF/lib

Now it will be shown on the start screen.


More information
-----

Read the [plugin development how to](http://docs.camunda.org/latest/real-life/how-to/#cockpit-how-to-develop-a-cockpit-plugin).


[1]: https://raw.github.com/camunda/camunda-consulting/master/snippets/cockpit-plugin-reporting/screenshot.png
