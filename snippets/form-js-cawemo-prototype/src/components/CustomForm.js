import React, { Component} from "react";

import "../styles/CustomForm.scss";

export default class CustomForm extends Component {

  constructor(props) {
    super(props);
  }

  render() {
    return(
      <div className="customForm">
        This is a custom form component for the formKey<br/>
        {this.props.schema.key}
      </div>
    )
  }
}
