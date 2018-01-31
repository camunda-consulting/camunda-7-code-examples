import Vue from 'vue';

import {startNewProcess, empty} from './forms/myprocess';
import CamundaRest from '../services/camunda-rest';

export default Vue.component('GenericForm', {
  data() {
    return {
      form: 'empty',
      template: null,
    };
  },
  render(createElement) {
    if (!this.template) {
      return createElement('div', 'Loading...');
    }
    return this.template();
  },
  mounted() {
    CamundaRest.getFormKey('myprocess').then((result) => {
      this.form = result.data.key;
      this.template = Vue.compile('<div><component v-bind:is="form"></component></div>').render;
    });
  },
  components: {
    startNewProcess,empty
  },
});
