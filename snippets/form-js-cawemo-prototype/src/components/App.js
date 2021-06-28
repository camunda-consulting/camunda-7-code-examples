import React, { Component} from "react";
import "../styles/App.scss";

import Cawemo from "./Cawemo.js";
import Dialog from "./Dialog.js";
import Communication from "../helper/Communication.js";
import { InlineWidget } from "react-calendly";

class App extends Component{

  componentDidMount() {
    this.comm.evaluateDecision(this.state.params.configurationDecisionKey).then(this.handleLoadConfig.bind(this));
  }

  constructor(props) {
    super(props);

    this.state = {
      dialogIsOpen: false,
      config: {
        recruitingIsActive: false
      },
      params: {
        configurationDecisionKey: "general_config_decision",
        processDefinitionKey: "screening_process"
      }
    };

    this.comm = new Communication();
  }

  handleLoadConfig(result) {
    if(!result.status == "success")
      return;

    if(result.data.general_switch) {
      this.setState({
        config: {
          recruitingIsActive: true,
          linkLabel: result.data.linkLabel
        }
      });
    } else {
      this.setState({
        config: {
          recruitingIsActive: false
        }
      });
    }
  }

  showStartForm() {
    this.comm.processStartForm(this.state.params.processDefinitionKey).then((startForm) => {

      if(startForm.status == "success") {
        this.setState({
          dialogIsOpen: true,
          resourceId: this.state.params.processDefinitionKey,
          type: 'process',
          form: {
            type: startForm.data.formType,
            data: startForm.data.formVariables,
            schema: startForm.data.formSchema
          }
        });
      } else {
        this.setState({
          dialogIsOpen: false,
          config: {
            recruitingIsActive: false
          }
        });
      }
    });
  }

  handleProcessStart(data) {
    if(!data.status == "success")
      return;

    if(data.processIsRunning) {
      this.setState({
        processInstanceId: data.processInstanceId
      });
    } else {
      this.setState({
        config: {
          recruitingIsActive: false
        }
      });
    }
  }

  showNextStep() {
    this.comm.getNextForm(this.state.processInstanceId).then((nextForm) => {

      if(nextForm.data.processIsRunning) {
        this.setState({
          dialogIsOpen: true,
          resourceId: nextForm.data.taskId,
          type: 'task',
          form: {
            type: nextForm.data.formType,
            data: nextForm.data.formVariables,
            schema: nextForm.data.formSchema
          }
        });
      } else {
        this.setState({
          dialogIsOpen: false,
          config: {
            recruitingIsActive: false
          }
        });
      }
    });
  }

  handleFormSubmission(data) {
    if(this.state.type == 'task') {
      this.comm.submitForm(this.state.resourceId, data).then(this.showNextStep.bind(this));
    } else {
      this.comm.startProcess(this.state.resourceId, data).then((response) => {
        this.handleProcessStart(response);
        this.showNextStep();
      });
    }
  }

  render(){
    return(
      <div className="App">
        <Cawemo config={this.state.config} clickHandler={this.showStartForm.bind(this)}/>
        <Dialog open={this.state.dialogIsOpen}
                content={this.state.form}
                submitCallback={this.handleFormSubmission.bind(this)}/>
        {false
          ? <InlineWidget url="https://calendly.com/camunda-user-research/interview?hide_event_type_details=1" />
          : ""
        }
      </div>
    );
  }
}

export default App;
