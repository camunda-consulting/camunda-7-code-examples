package org.camunda.bpm.example.twitter;

import java.io.Serializable;

public class Tweet implements Serializable {

  private static final long serialVersionUID = 1L;

  private String email;
  private String content;
  private boolean approved = true;
  private String rejectionComment;
  
  public void reject(String comment) {
    this.rejectionComment = comment;
    this.approved = false;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isApproved() {
    return approved;
  }

  public void setApproved(boolean approved) {
    this.approved = approved;
  }

  public String getRejectionComment() {
    return rejectionComment;
  }

  public void setRejectionComment(String rejectionComment) {
    this.rejectionComment = rejectionComment;
  }

}
