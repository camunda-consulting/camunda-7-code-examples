import React from 'react';

import Viewer from 'bpmn-js/lib/NavigatedViewer';

export default class BPMNDiagram extends React.Component {
  constructor(props) {
    super(props);

    this.viewer = new Viewer({
      canvas: {
        deferUpdate: false
      }
    });

    this.state = {
      loaded: false
    };
  }

  storeContainer = container => {
    this.container = container;
  }

  render() {
    return <div className='BPMNDiagram' style={this.props.style} ref={this.storeContainer}>
      {this.state.loaded && this.props.children && React.cloneElement(this.props.children, { viewer: this.viewer })}
    </div>;
  }

  componentDidUpdate(prevProps) {
    if(prevProps.xml !== this.props.xml) {
      this.setState({loaded: false});
      this.importXML(this.props.xml);
    }
  }

  importXML(xml) {
    this.viewer.importXML(xml, (err) => {
      const canvas = this.viewer.get('canvas');

      canvas.resized();
      canvas.zoom('fit-viewport', 'auto');

      this.setState({loaded: true});
    });
  }

  componentDidMount() {
    this.viewer.attachTo(this.container);

    this.importXML(this.props.xml);
  }
}
