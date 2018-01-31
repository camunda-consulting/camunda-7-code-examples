<template>
  <div>
    <h1>Start Process</h1>
    <ul v-if="processDefinitions && processDefinitions.length">
      <li v-for="processDefinition of processDefinitions">
        <p><strong>
          <router-link :to="`/startprocess/${processDefinition.key}`">{{processDefinition.name}} - {{processDefinition.version}}</router-link>
        </strong></p>
      </li>
    </ul>
  </div>
</template>

<script>
import CamundaRest from '../services/camunda-rest';

export default {
  data() {
    return {
      processDefinitions: [],
      errors: [],
    };
  },

  created() {
    CamundaRest.getProcessDefinitions().then((response) => {
      this.processDefinitions = response.data;
    })
    .catch((e) => {
      this.errors.push(e);
    });
  },
};
</script>
