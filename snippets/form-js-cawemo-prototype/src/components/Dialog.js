import React, { Component} from "react";
import "../styles/Dialog.scss";

import CamundaForm from "./CamundaForm.js";
import CustomForm from "./CustomForm.js";

export default class Dialog extends Component{

  constructor(props) {
    super(props);
  }

  render(){
    if (this.props.open) {
      return(
        <div className="dialog">
          <div className="content">
            {this.props.content.type == 'camundaForm'
              ? <CamundaForm data={this.props.content.data}
                            schema={this.props.content.schema}
                            submitCallback={this.props.submitCallback}/>

              : <CustomForm schema={this.props.content.schema}/>
            }
          </div>
        </div>
      );
    } else {
      return (<div></div>);
    }
  }
}
