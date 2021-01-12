package com.springdemo.springdemo.exception;

import java.util.Date;

public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String errorDetail;

    public ErrorDetails(Date timeStamp, String message, String errorDetail) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.errorDetail = errorDetail;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
