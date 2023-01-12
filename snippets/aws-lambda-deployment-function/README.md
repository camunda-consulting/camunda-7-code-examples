# AWS Lambda Function for Deployment

This project is meant to be deployed to AWS Lambda. It will create a deployment for the bpmn file to our training instance.

# How to use it

1. Check which parts of the function should be parameterized (The bpmn file and the endpoint url for example).
2. Rewrite everything that should be replaced.
3. Run `npm run build`.
4. Copy the bpmn file to the `dist` folder.
5. Zip everything in there using `zip -r function.zip .` inside the `dist` folder.
6. Upload the zip file to your AWS Lambda function.
7. Set the function entry to `index.handler`, this should also be default.
8. Enjoy the function.

# Version compatibility

This project was created using node `v18.13.0`.