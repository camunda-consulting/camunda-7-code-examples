const camunda = require('../managers/camunda');

async function evaluateDecision (ctx, next) {

  await camunda.evaluateDecision(ctx.params.decision_key).then((response_data) => {
    ctx.body = {
      'status': "success",
      'data': response_data
    }
  }).catch( () => {
    ctx.body = {
      'status': "failure"
    }
  });

  await next();
}

async function startProcess (ctx, next) {

  await camunda.startProcess(ctx.params.process_definition_key).then((response_data) => {
    ctx.body = {
      'status': "success",
      'processIsRunning': !response_data.ended,
      'processInstanceId': response_data.id,
      'config': JSON.parse(response_data.variables.general_config_json.value)
    }
  }).catch( () => {
    ctx.body = {
      'status': "failure"
    }
  });

  await next();
}


async function processStartForm (ctx, next) {

  await camunda.processStartForm(ctx.params.process_definition_key).then((response_data) => {
    ctx.body = {
      'status': "success",
      'data': response_data
    }
  }).catch( () => {
    ctx.body = {
      'status': "failure"
    }
  });

  await next();
}

async function nextForm (ctx, next) {
  await camunda.nextForm(ctx.params.process_instance_id).then((response_data) => {
    ctx.body = {
      'status': "success",
      'data': response_data
    }
  }).catch( () => {
    ctx.body = {
      'status': "failure"
    }
  });

  await next();
}

async function submitForm (ctx, next) {
  await camunda.submitForm(ctx.params.task_id, ctx.request.body).then((response_data) => {
    ctx.body = {
      'status': "success",
      'data': response_data
    }
  }).catch( () => {
    ctx.body = {
      'status': "failure"
    }
  });

  await next();
}

module.exports = {evaluateDecision, startProcess, processStartForm, nextForm, submitForm};
