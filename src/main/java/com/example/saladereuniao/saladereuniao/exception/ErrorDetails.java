package com.example.saladereuniao.saladereuniao.exception;

import java.util.Date;

public class ErrorDetails {

    private Date stamp;
    private String message;
    private String details;

    public Date getStamp() {
        return stamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public ErrorDetails(Date stamp, String message, String details) {
        super();
        this.stamp = stamp;
        this.message = message;
        this.details = details;
    }
}
