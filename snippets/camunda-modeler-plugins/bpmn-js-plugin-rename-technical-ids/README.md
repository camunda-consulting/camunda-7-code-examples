# Camunda Modeler - Rename Technical IDs

## This plugin has moved to a new home in the [Camunda Community Hub](https://github.com/camunda-community-hub/camunda-modeler-plugin-rename-technical-ids) and its name has been updated to reflect our [journey from Camunda BPM to Camunda Platform](https://camunda.com/blog/2021/04/the-journey-from-camunda-bpm-to-camunda-platform).

*Have a large BPMN process and want to automate it? Are you annoyed about editing all technical IDs manually?*

Then this is the right plugin for you. This plugin helps you to generate and rename technical IDs for all BPMN symbols:

e.g.:
- StartEvent_050orni with name Payload received becomes `PayloadReceivedStartEvent`
- UserTask_0eyj290 with name Process data becomes `ProcessDataTask`
- ...

The generated IDs suite to our best practice.

Usage:
1. generate the technical IDs
2. rename the IDs by clicking the button.
3. IDs that will replace the exisiting ones are highlighted with a yellow background.
4. If the name starts with a number (it is an invalid QName), the ID will start with a `N`: Time event with 10 Days will become `N10DaysEvent`

## Example

![Screenshot 1](screenshot1.png)

Put this directory into the `plugins` directory of the Camunda Modeler and you're ready to go.
First click on the "Generate IDs" button and check if all IDs are fine. Then click on Rename IDs and save your BPMN file.

If you're interested in how to create your own plugins see the [documentation](https://github.com/camunda/camunda-modeler/tree/547-plugins/docs/plugins) and this [example](https://github.com/camunda/camunda-modeler-plugin-example).
