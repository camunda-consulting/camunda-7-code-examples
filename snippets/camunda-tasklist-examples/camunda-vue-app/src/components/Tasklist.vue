<template>
  <div>
    <sui-grid>
      <sui-grid-row :columns="2">
        <sui-grid-column :width="3">
          <sui-segment raised>
            <sui-list divided relaxed v-if="tasks && tasks.length">
              <sui-list-item v-for="task of tasks">
                <sui-list-icon name="browser" size="large" vertical-align="middle" />
                <sui-list-content>
                  <p><strong>
                    <router-link :to="`/tasklist/${task.id}`">{{task.name}}</router-link><br>
                    {{task.created}}
                  </strong></p>
                </sui-list-content>
              </sui-list-item>
            </sui-list>
          </sui-segment>
        </sui-grid-column>
        <sui-grid-column :width="13">
          <sui-segment raised>
            <generic-form v-if="this.$route.params.taskId" :taskId="this.$route.params.taskId" :formKey="taskFormKey"></generic-form>
            <div v-if="!this.$route.params.taskId">
              <p>Please choose task.</p>
            </div>
          </sui-segment>
        </sui-grid-column>
      </sui-grid-row>
    </sui-grid>
  </div>
</template>

<script>
  import CamundaRest from '../services/camunda-rest';
  import GenericForm from './GenericForm';

  export default {
    data() {
      return {
        tasks: [],
        taskFormKey: ''
      };
    },
    components: {
      'generic-form': GenericForm
    },
    watch: {
      '$route': 'fetchData'
    },
    methods: {
      fetchData() {
        CamundaRest.getTasks().then((result) => {
          this.tasks = result.data;
        });
        if (this.$route.params.taskId) {
          CamundaRest.getTaskFormKey(this.$route.params.taskId).then((result) => {
            this.taskFormKey = result.data.key;
          });
        }
      }
    },
    mounted() {
      CamundaRest.getTasks().then((result) => {
        this.tasks = result.data;
      });
      this.fetchData();
    }
  };

</script>
