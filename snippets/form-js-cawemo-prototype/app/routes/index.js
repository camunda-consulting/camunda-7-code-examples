const Router = require('koa2-router'),
      KoaBody = require('koa-body')
      send = require('koa-send'),
     {evaluateDecision, startProcess, processStartForm, nextForm, submitForm} = require('../controllers/indexController');

const router = new Router();

router
  .get('/api/decision/:decision_key/evaluate', evaluateDecision)
  .get('/api/process/:process_definition_key/start', startProcess)
  .get('/api/process/:process_definition_key/form', processStartForm)
  .get('/api/nextForm/:process_instance_id', nextForm)
  .post('/api/submitForm/:task_id', KoaBody(), submitForm)
  .use('/static', async (ctx) => {
    await send(ctx, ctx.path, { root: __dirname + '/../../dist/' });
  });

module.exports = {
   routes () { return router; }
};
