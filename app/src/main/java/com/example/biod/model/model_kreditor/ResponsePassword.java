package com.example.biod.model.model_kreditor;


public class ResponsePassword {

    private Boolean status;
    private String message;

    public ResponsePassword(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
