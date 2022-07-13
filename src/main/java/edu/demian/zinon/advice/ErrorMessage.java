package edu.demian.zinon.advice;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {

    private int status;
    private Date date;
    private String message;
    private String description;

    public ErrorMessage(int status, Date date, String message, String description) {
        this.status = status;
        this.date = date;
        this.message = message;
        this.description = description;
    }
}
