<template>
  <sui-grid centered>
    <sui-grid-row :columns="2">
      <sui-grid-column>
        <sui-segment raised>
          <h1>Start Process</h1>
          <sui-list v-if="processDefinitions && processDefinitions.length">
            <sui-list-item :key="processDefinition.id" v-for="processDefinition of processDefinitions">
              <p><strong>
                <router-link :to="`/startprocess/${processDefinition.key}`">{{processDefinition.name}} - {{processDefinition.version}}</router-link>
              </strong></p>
            </sui-list-item>
          </sui-list>
        </sui-segment>
      </sui-grid-column>
    </sui-grid-row>
  </sui-grid>
</template>

<script>
import CamundaRest from '../services/camunda-rest';

export default {
  data() {
    return {
      processDefinitions: []
    };
  },

  created() {
    CamundaRest.getProcessDefinitions().then((response) => {
      this.processDefinitions = response.data;
    }).catch(() => {
      //console.error(e);
    });
  }
};
</script>
