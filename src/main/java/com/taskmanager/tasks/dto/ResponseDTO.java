package com.taskmanager.tasks.dto;

public class ResponseDTO {
    private String message;

    public ResponseDTO(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
