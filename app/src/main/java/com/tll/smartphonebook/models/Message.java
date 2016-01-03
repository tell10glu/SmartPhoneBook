package com.tll.smartphonebook.models;

/**
 * Created by abdullahtellioglu on 30/12/15.
 */
public class Message {
    private String from,to,body,date;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Message(String from, String to, String body, String date) {
        this.from = from;
        this.to = to;
        this.body = body;
        this.date = date;
    }

}
