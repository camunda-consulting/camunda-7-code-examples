<template>
  <div>
    <form enctype="multipart/form-data" novalidate v-if="isInitial || isSaving">
        <h1>Deploy a process</h1>
        <p>
          If this is the first time you are running this application, you might want to upload an example BPMN process. All you need to do is to upload the example BPMN file (e.g. choose the BPMN file in /examples/myprocess/myprocess.bpmn).
          After uploading the process you should probably go to "Start Process" and choose e.g. the "My Process" Process.
        </p>
        <div class="dropbox">
          <input type="file" :name="uploadFieldName" :disabled="isSaving" @change="filesChange($event.target.name, $event.target.files); fileCount = 1" class="input-file">
            <p v-if="isInitial">
              Drag your bpmn file(s) here to begin<br> or click to browse
            </p>
            <p v-if="isSaving">
              Deploying {{ fileCount }} process(es)...
            </p>
        </div>
    </form>
    <!--SUCCESS-->
     <div v-if="isSuccess">
       <h2>Uploaded {{ uploadedFiles.length }} file(s) successfully.</h2>
     </div>
     <!--FAILED-->
     <div v-if="isFailed">
       <h2>Uploaded failed.</h2>
       <p>
         <a href="javascript:void(0)" @click="reset()">Try again</a>
       </p>
       <pre>{{ uploadError }}</pre>
     </div>
   </div>
</template>

<script>
  import CamundaRest from '../services/camunda-rest';

  const STATUS_INITIAL = 0;
  const STATUS_SAVING = 1;
  const STATUS_SUCCESS = 2;
  const STATUS_FAILED = 3;

  export default {
    name: 'app',
    data() {
      return {
        uploadedFiles: [],
        uploadError: null,
        currentStatus: null,
        uploadFieldName: 'data'
      };
    },
    computed: {
      isInitial() {
        return this.currentStatus === STATUS_INITIAL;
      },
      isSaving() {
        return this.currentStatus === STATUS_SAVING;
      },
      isSuccess() {
        return this.currentStatus === STATUS_SUCCESS;
      },
      isFailed() {
        return this.currentStatus === STATUS_FAILED;
      }
    },
    methods: {
      reset() {
        // reset form to initial state
        this.currentStatus = STATUS_INITIAL;
        this.uploadedFiles = [];
        this.uploadError = null;
      },
      save(formData) {
        // upload data to the server
        this.currentStatus = STATUS_SAVING;
        CamundaRest.deployProcessDefinition(formData)
          .then((x) => {
            this.uploadedFiles = [].concat(x);
            this.currentStatus = STATUS_SUCCESS;
          })
          .catch((err) => {
            this.uploadError = err.response;
            this.currentStatus = STATUS_FAILED;
          });
      },
      filesChange(fieldName, fileList) {
        // handle file changes
        const formData = new FormData();
        if (!fileList.length) return;
        // append the files to FormData
        Array
          .from(Array(fileList.length).keys())
          .map((x) => {
            formData.append(fieldName, fileList[x], fileList[x].name);
          });
        // save it
        this.save(formData);
      }
    },
    mounted() {
      this.reset();
    }
  };
</script>


<!-- SASS styling -->
<style lang="scss">
  .input-file {
    opacity: 0; /* invisible but it's there! */
    width: 100%;
    height: 200px;
    position: absolute;
    cursor: pointer;
  }
</style>
