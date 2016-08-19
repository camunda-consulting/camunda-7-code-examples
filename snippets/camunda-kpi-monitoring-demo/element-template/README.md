# Camunda KPI Element Template

## Overview
Within this project you find the element template (kpi.json) that can be used to add KPI information to your BPMN model within Camunda Modeler.
This element template is an example for adding KPI information as extension elements to tasks, events and processes. This template can be customized for your needs.

## Element templates
You haven't heard about element templates? 
Checkout [Camunda Docs](https://docs.camunda.org/manual/7.5/modeler/camunda-modeler/element-templates/#overview)

## How to use?
To use this element template put the JSON file 'kpi.json' into the `resources/element-templates` directory relative to the modeler executable or the modelers data directory. Restart the modeler to let it recognize the templates.
If you would like you can easily add more properties to the JSON in order to configure more KPIs.

> The location of the modelers data directory differs across operating systems. On Windows it is situated under `%APPDATA%/camunda-modeler`. Mac OSX users find it under `~/Library/Application Support/...`.

> Mac OSX users will have a path like: `~/Library/Application Support/camunda-modeler/resources/element-templates/kpi.json`

## How will it look like inside the Camunda Modeler?
![Screenshot](screenshot.png)

## License
Use under terms of the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)