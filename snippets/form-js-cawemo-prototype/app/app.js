const Koa = require('koa'),
      {routes} = require('./routes');

const app = new Koa();

app.use(routes());

app.listen(1234, function () {
    console.log('listening');
});
