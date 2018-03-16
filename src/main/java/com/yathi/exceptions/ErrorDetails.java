package com.yathi.exceptions;
import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    private int status;

    public ErrorDetails(){

    }

    public ErrorDetails(Date timestamp, String message, String details, int status) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
