var Workers = require('camunda-worker-node');

var Backoff = require('camunda-worker-node/lib/backoff');

var engineEndpoint = process.env.ENGINE_URL || 'http://localhost:8080/engine-rest';

var uuid = require('uuid');


var workers = new Workers(engineEndpoint);

Backoff(workers);

workers.registerWorker('ReserveItem', [ 'productName'], function(context, callback) {

  var productName = context.variables.productName;

  // do actual work here, write database, provision goods
  //order.shipmentId = uuid.v4();
  //order.shipped = true;

  console.log('[Reservation Worker] The product #%s has been reserved', productName);

  // notify we are done with an updated loop variable
  callback(null, {
    variables: {

    }
  });

});

workers.registerWorker('ReleaseItem', [ 'productName'], function(context, callback) {

  var productName = context.variables.productName;

  // do actual work here, write database, provision goods
  //order.shipmentId = uuid.v4();
  //order.shipped = true;

  console.log('[Release Worker] The product #%s had its reservation released', productName);

  // notify we are done with an updated loop variable
  callback(null, {
    variables: {

    }
  });

});


workers.on('start', function() {
  console.log('[Warehouse Worker] starting');
});

workers.on('poll', function() {
  console.log('[Warehouse Worker] polling');
});

// handle worker errors
workers.on('error', function(err) {
  console.error('[Warehouse Worker] error: %s', err);
});