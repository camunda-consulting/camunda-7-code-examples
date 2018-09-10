<template>
  <div>
    <form class="ui form" v-on:submit.prevent="submit" v-if="!COMPLETED">
      <div class="field">
        <label for="email">E-Mail</label>
        <sui-input placeholder="E-Mail" v-model="formdata.email" readonly type="text" name="email"/>
        <span v-show="errors.has('email')">{{ errors.first('email') }}</span>
      </div>
      <div class="field">
          <label for="approved">Approve?</label>
          <input v-model="formdata.approved" type="checkbox" name="approved" />
      </div>
      <input class="ui button primary" type="submit" value="Submit">
    </form>
    <div v-if="COMPLETED">
      <p>Task has been completed. Please choose next task.</p>
    </div>
  </div>
</template>

<script>
import DataTransformation from '../../../utils/data-transformation';
import CamundaRest from '../../../services/camunda-rest';

export default {
  props: ['taskId'],
  data() {
    return {
      formdata: {
        'email': null,
        'approved': true
      },
      COMPLETED: false
    };
  },
  methods: {
    submit() {
      const variables = DataTransformation.generateVariablesFromFormFields(this.formdata);
      CamundaRest.postCompleteTask(this.taskId, variables).then((result) => {
        if (result.status === 200 || result.status === 204) {
          this.COMPLETED = true;
          this.$router.push({ path: '/tasklist/' });
        }
      });
    }
  }
};
</script>
