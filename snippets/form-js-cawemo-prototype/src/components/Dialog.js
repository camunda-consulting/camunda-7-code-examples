import React, { Component} from "react";
import "../styles/Dialog.scss";

import CamundaForm from "./CamundaForm.js";

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

              : ""
            }
          </div>
        </div>
      );
    } else {
      return (<div></div>);
    }
  }
}
