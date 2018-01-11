# Camunda - React Redux Example Tasklist

This project shows how one can use React Redux with the standard Camunda REST API. It is an example implementation of how one could build a Tasklist for Camunda using Front End technology only.

The Task Forms are defined within this application and not on the Server-Side to make it very easy to use.

This project was bootstrapped with [Create React App](https://github.com/facebookincubator/create-react-app).
Please see the [React Scripts Guide](https://github.com/facebookincubator/create-react-app/blob/master/packages/react-scripts/template/README.md) to see what you can do with this project from the React perspective.

## Example
Within this project you can find some example task forms that were defined for an example process.
The example process can be found in the folder `examples/myprocess`.
The forms can be found in `src/components/forms/myprocess`.

## How to use
This project can be seen as an example implementation or as a starting point for your custom Tasklist.
As it uses standard react mechanisms it should be very easy for a React developer to adapt and change or reuse the code.

### How to run the example process

1. Prerequisite: Install [NPM](https://docs.npmjs.com/getting-started/installing-node) & [Yarn](https://yarnpkg.com/lang/en/docs/install/)
1. run `npm install`
1. run `yarn start`
1. Start the Camunda Engine (standard distribution with REST-API at `localhost:8080/engine-rest`)
1. Open Browser (e.g. Chrome) on `locahost:3000`
1. Upload the example BPMN Process given in `examples/myprocess`
1. Start Instance & fill in forms
1. Click on Tasklist and complete tasks

### How to customize this example

This application can start all BPMN process that are deployed on the process engine via the rest API.
You have to make sure that your UserTasks within the BPMN files are configured correctly, so that the forms are displayed in the Tasklist.
Within the `src/components/forms` folder you have to create a new folder for every new BPMN Process and name it with the PROCESS_DEFINITION_KEY of your process.
Then you need to place js forms (similar to the once in the myprocess folder) in this new folder. The name of each file has to be used as the FORM_KEY for your UserTask.

---
### Limitations
1. So far this application only supports very basic variables like String & Integer & Boolean.
The application will check automatically if variables for defined fields already exist within the process instance in camunda.
1. So far now Tests were written...
