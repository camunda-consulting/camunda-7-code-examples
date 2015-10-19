package com.camunda.training.twitterQa;

public class Tweet {

  private String email;
  private String content;
  private boolean approved = true;
  private String rejectionComment;
  
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