package com.camunda.consulting.dto;

public class EmailDto {

    private String to;
    private String content;
    private String subject;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "EmailDto{" +
                "to='" + to + '\'' +
                ", content='" + content + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
