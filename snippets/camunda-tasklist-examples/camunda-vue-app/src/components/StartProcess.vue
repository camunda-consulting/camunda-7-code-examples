<template>
  <div>
    <h1>Start Process</h1>
    {{this.$route.params.processDefinitionKey}}
    <generic-form></generic-form>
  </div>

</template>

<script>
import GenericForm from './GenericForm';

export default {
  components: {
    'generic-form': GenericForm,
  },
};
</script>
