import React, { Component} from "react";
import "../styles/Cawemo.scss";

export default class Cawemo extends Component{
  render(){
    return(
      <div className="cawemo">
        <div className="header">
          <div className="breadcrumbs">
            <img src="/static/images/cawemo-header.png"/>
          </div>
          <div className="recruitingHook">
            {this.props.config.recruitingIsActive
              ? <a href="#" onClick={this.props.clickHandler}>{this.props.config.linkLabel}</a>
              : ""
            }
          </div>
          <div className="userMenu">
            <img src="/static/images/cawemo-user-menu.png"/>
          </div>
        </div>
        <div className="welcome">
          <div className="welcomeMessage">
            <img src="/static/images/cawemo-welcome.png"/>
          </div>
          <div className="recruitingHook">
          </div>
        </div>
        <div className="content">
          <img src="/static/images/cawemo-content.png"/>
        </div>
      </div>
    );
  }
}
