import React, { Component} from "react";
//import { createForm } from '@bpmn-io/form-js-viewer';
const { createForm } = window.FormViewer;
import "../styles/CamundaForm.scss";

// see https://github.com/bpmn-io/react-bpmn

export default class CamundaForm extends Component{

  constructor(props) {
    super(props);

    this.state = { };

    this.containerRef = React.createRef();
  }

  componentDidMount() {

    const {
      data,
      schema
    } = this.props;

    const container = this.containerRef.current;

    this.formViewer = createForm({
      container: container,
      schema,
      data
    });

    this.formViewer.on('submit', event => {
      if(Object.keys(event.errors).length == 0) {
        this.props.submitCallback(event.data);
      }
    });

  }

  componentWillUnmount() {
  }

  componentDidUpdate(prevProps, prevState) {
    this.componentDidMount();
  }

  render() {
    return(
      <div ref={ this.containerRef }></div>
    )
  }
}
