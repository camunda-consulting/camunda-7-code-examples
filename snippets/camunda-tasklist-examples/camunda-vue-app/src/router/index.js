import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/components/Home';
import TaskList from '@/components/Tasklist';
import ProcessList from '@/components/Processlist';

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
  ],
});
