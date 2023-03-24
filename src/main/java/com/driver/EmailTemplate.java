package com.driver;

import java.util.Date;

public class EmailTemplate {
    Date date;
    String sender;
    String message;

    //    public EmailTemplate(){
//
//    }
    public EmailTemplate(Date date, String sender, String message) {
        this.date = date;
        this.sender = sender;
        this.message = message;
    }
}
