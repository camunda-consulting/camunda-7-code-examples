# Camunda - React Redux Example Tasklist

This project shows how one can use React Redux with the standard Camunda REST API. It is an example implementation of how one could build a Tasklist for Camunda using Front End technology only.

The Task Forms are defined within this application and not on the Server-Side to make it very easy to use.

This project was bootstrapped with [Create React App](https://github.com/facebookincubator/create-react-app).
Please see the [React Scripts Guide](https://github.com/facebookincubator/create-react-app/blob/master/packages/react-scripts/template/README.md) to see what you can do with this project from the React perspective.

## Example
Within this project you can find some example task forms that were defined for an example process.
The example including can be found `examples/myprocess`.
The forms can be found in `src/components/forms/myprocess`.

## How to use
This project can be seen as an example implementation or as a starting point for your custom Tasklist.
As it uses standard react mechanisms it should be very easy for a React developer to adapt and change or reuse the code.

### Some background
Within the folder `src/components/forms/` you can place task forms that will be used for your Tasklist and for Starting a Process Instance.
The folder name within `src/components/forms/` should match the Process Definition Key of your BPMN Process.
The file names within this folder should match the FORM KEYs that you defined within the BPMN Process for your User Tasks.
The application will check automatically if variables for defined fields already exist within the process instance in camunda.
