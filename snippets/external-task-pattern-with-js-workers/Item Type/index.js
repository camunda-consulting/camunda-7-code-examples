var Workers = require('camunda-worker-node');

var Backoff = require('camunda-worker-node/lib/backoff');

var engineEndpoint = process.env.ENGINE_URL || 'http://localhost:8080/engine-rest';

var uuid = require('uuid');


var workers = new Workers(engineEndpoint);

Backoff(workers);

workers.registerWorker('DetermineStatus', ['productName'], function(context, callback) {

  var productName = context.variables.productName;

   
  if (Math.random() > 0.5) {
   console.log('[Item Worker] Checking on details for product #%s  - Its hardware that requires payment', productName);
    return callback(null, {
    variables: {
    	 hardware : "Yes",
       paymentRequired: "Yes"
    } 
  });

  }

  console.log('[Item Worker] Checking on details for product #%s  - Its software that requires payment', productName);
 

  // notify we are done with an updated loop variable
  callback(null, {
    variables: {
       hardware : "No",
       paymentRequired: "Yes"
    } 
  });

});



workers.on('start', function() {
  console.log('[Item Worker] Is starting up');
});

workers.on('poll', function() {
  console.log('[Item Worker] polling...');
});

// handle worker errors
workers.on('error', function(err) {
  console.error('[Item Worker] error: %s', err);
});