package com.example.bootstrap.util.util;

public class PersonErrorResponse {
    private String message;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PersonErrorResponse(String message, long timestamp) {
        this.timestamp = timestamp;
        this.message = message;
    }

    private long timestamp;
}
