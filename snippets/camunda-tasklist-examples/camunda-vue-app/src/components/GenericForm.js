import Vue from 'vue';

import CamundaRest from '../services/camunda-rest';
import * as FormTypes from './forms';

export default Vue.component('GenericForm', {
  props: ['formKey', 'taskId'],
  data() {
    return {
      template: null
    };
  },
  render(createElement) {
    if (!this.template) {
      return createElement('div', 'Loading...');
    }
    return this.template();
  },
  mounted() {
    if (this.formKey !== '') {
      this.changeTemplate();
    }
  },
  methods: {
    changeTemplate: function() {
      const formdata = FormTypes.myprocess[this.formKey].data().formdata;
      if (formdata && this.taskId) {
        const variables = Object.keys(formdata);
        this.loadVariables(variables);
      }
      this.mycomponent = Vue.compile(`<div>
        <component ref="formsChild" :taskId="taskId" v-bind:is="formKey"></component>
      </div>`);
      this.template = this.mycomponent.render;
    },
    loadVariables: function(variables) {
      CamundaRest.getTaskVariables(this.taskId, variables.join(',')).then((result) => {
        const variableResult = result.data;
        variables.forEach((item) => {
          const itemData = this.$refs.formsChild.formdata[item];
          if (typeof itemData === 'boolean') {
            this.$refs.formsChild.formdata[item] = false;
          } else {
            this.$refs.formsChild.formdata[item] = '';
          }
        });
        Object.keys(variableResult).forEach((item) => {
          this.$refs.formsChild.formdata[item] = variableResult[item].value;
        });
      });
    }
  },
  watch: {
    formKey: 'changeTemplate',
    taskId: 'changeTemplate'
  },
  components: {
    // adding all forms for myprocess to components
    ...FormTypes.myprocess
  }
});
