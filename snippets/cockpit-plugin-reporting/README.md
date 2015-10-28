KPI Plugin for Camunda Cockpit
=================================

This Plugin shows KPI's for processes which are configured for it (see below):

![Screenshot][1]

It also shows the number of started process instances for the last 14 days on the start page:

![Screenshot][2]


Configure KPI's in your process model
----------------------
In order to see KPI's for your process you have to set extension attributes in your process:

# Cycle Time

To show a cycle time you have to define the beginning and end of the cycle by providing:

* KPI-Cycle-Start (cycle time starts when entering the element with this extension)
* KPI-Cycle-End (cycle time ends when leaving the element with this extension)

Give it a nice name as this is shown in the resulting chart.

![Screenshot][3]



# Ratio (e.g. end event distribution)

To show a ration mark the points you want to relate to each other (e.g. end events or different paths) - you can use as much points as you like:

* KPI-Ratio

Give every ratio point a proper name - as this option is named accordingly.

![Screenshot][4]


Generate Data to test / persent your KPI's
----------------------

To create demo data you might want to use our [Demo Data Generator](https://github.com/camunda/camunda-consulting/tree/master/snippets/camunda-demo-data-generator).

Hint: Highcharts is not free!
----------------------

Please note that we used [Highcharts](http://www.highcharts.com/) for that example which is NOT free.
You have to buy a license in order to use the plugin shown here, see [Highcharts FAQ](http://shop.highsoft.com/faq). 
Or you have to replace it with some other JavaScript libraries drawing charts.


How to use
----------------------

Build and tested on Camunda BPM 7.3.0.

1. Build the plugin using maven with mvn clean install

2. Integrate the JAR in your camunda-*.war file and there under WEB-INF/lib

Now it will be shown on the start screen.



[1]: https://raw.github.com/camunda/camunda-consulting/master/snippets/cockpit-plugin-reporting/screenshot.png
[2]: https://raw.github.com/camunda/camunda-consulting/master/snippets/cockpit-plugin-reporting/screenshot2.png

[3]: https://raw.github.com/camunda/camunda-consulting/master/snippets/cockpit-plugin-reporting/KpiCycleTimeConfig.png
[4]: https://raw.github.com/camunda/camunda-consulting/master/snippets/cockpit-plugin-reporting/KpiRatioConfig.png

