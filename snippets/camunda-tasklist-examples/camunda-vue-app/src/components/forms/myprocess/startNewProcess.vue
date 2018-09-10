<template>
  <div>
    <form class="ui form" v-on:submit.prevent="submit" v-if="!STARTED">
      <div class="field">
        <label for="formdata.email">E-Mail</label>
        <input v-model="formdata.email" v-validate="{ required: true, email: true, regex: !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i }" type="text" name="email">
        <span v-show="errors.has('email')">{{ errors.first('email') }}</span>
      </div>
      <input class="ui button primary" type="submit" value="Start">
    </form>
    <div v-if="STARTED">
      <p>A new process instance was created.</p>
    </div>
  </div>
</template>

<script>
import DataTransformation from '../../../utils/data-transformation';
import CamundaRest from '../../../services/camunda-rest';

export default {
  data() {
    return {
      formdata: {},
      STARTED: false
    };
  },
  methods: {
    submit() {
      const variables = DataTransformation.generateVariablesFromFormFields(this.formdata);
      CamundaRest.postProcessInstance('myprocess', variables).then((result) => {
        if (result.status === 200) {
          this.STARTED = true;
        }
      });
    }
  }
};
</script>
