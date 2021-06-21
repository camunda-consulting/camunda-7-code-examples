import React, { Component} from "react";
//import { createForm } from '@bpmn-io/form-js-viewer';
const { createForm } = window.FormViewer;
import "../styles/CamundaForm.scss";

// see https://github.com/bpmn-io/react-bpmn

export default class CamundaForm extends Component {

  constructor(props) {
    super(props);
    this.containerRef = React.createRef();   // create a container reference
  }

  componentDidMount() {
    const {
      data,
      schema
    } = this.props;                          // fetch data and schema from component props

    this.formViewer = createForm({
      container: this.containerRef.current,  // render the form into the container
      schema,
      data
    });

    this.formViewer.on('submit', event => {
      if(Object.keys(event.errors).length == 0) {
        this.props.submitCallback(event.data);
      }
    });
  }

  componentDidUpdate(prevProps, prevState) {
    this.componentDidMount();
  }

  render() {
    return(                                  // create the container and use the reference
      <div ref={ this.containerRef }></div>
    )
  }
}
