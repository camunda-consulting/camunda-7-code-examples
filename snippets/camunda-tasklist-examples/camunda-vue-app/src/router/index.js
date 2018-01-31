import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/components/Home';
import TaskList from '@/components/Tasklist';
import ProcessList from '@/components/Processlist';
import StartProcess from '@/components/StartProcess';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
    },
    {
      path: '/tasklist',
      name: 'Tasklist',
      component: TaskList,
    },
    {
      path: '/startprocess',
      name: 'Process List',
      component: ProcessList,
    },
    {
      path: '/startprocess/:processDefinitionKey',
      name: 'Process List',
      component: StartProcess,
    },
  ],
});
