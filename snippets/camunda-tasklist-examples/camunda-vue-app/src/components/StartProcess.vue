<template>
  <sui-grid centered>
    <sui-grid-row :columns="2">
      <sui-grid-column>
        <sui-segment raised>
          <h2>Start Process</h2>
          <generic-form :formKey="formKey"></generic-form>
        </sui-segment>
      </sui-grid-column>
    </sui-grid-row>
  </sui-grid>

</template>

<script>
import GenericForm from './GenericForm';
import CamundaRest from '../services/camunda-rest';

export default {
  data() {
    return {
      formKey: ''
    };
  },
  components: {
    'generic-form': GenericForm
  },
  mounted() {
    CamundaRest.getFormKey(this.$route.params.processDefinitionKey).then((result) => {
      this.formKey = result.data.key;
    });
  }
};
</script>
