import React, { Component} from "react";
import { Form } from '@bpmn-io/form-js-viewer';
import "../styles/CamundaForm.scss";

// see https://github.com/bpmn-io/react-bpmn

export default class CamundaForm extends Component {

  constructor(props) {
    super(props);
    this.containerRef = React.createRef();   // create a container reference
  }

  componentDidMount() {
    this.formViewer = new Form({
      container: this.containerRef.current  // render the form into the container
    });

    this.formViewer.on('submit', event => {
      if(Object.keys(event.errors).length == 0) {
        this.props.submitCallback(event.data);
      }
    });

    const {
      data,
      schema
    } = this.props;                          // fetch data and schema from component props

    this.updateForm(schema, data);
  }

  async updateForm(schema, data) {
    try {
      await this.formViewer.importSchema(schema, data);
    } catch(error) {
      console.error(error);
    }
  }

  componentDidUpdate(prevProps, prevState) {
    const {
      data,
      schema
    } = this.props;                          // fetch data and schema from component props

    this.updateForm(schema, data);
  }

  componentWillUnmount() {
    this.formViewer.destroy();
  }

  render() {
    return(                                  // create the container and use the reference
      <div ref={ this.containerRef }></div>
    )
  }
}
