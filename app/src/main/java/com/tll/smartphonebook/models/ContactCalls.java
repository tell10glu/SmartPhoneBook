package com.tll.smartphonebook.models;

import java.util.Date;

/**
 * Created by abdullahtellioglu on 12/12/15.
 */
public class ContactCalls {
    private String number;
    private long duration;
    private Date callingDate;
    private boolean missing;

    public ContactCalls(String number, long duration, Date callingDate, boolean missing) {
        this.number = number;
        this.duration = duration;
        this.callingDate = callingDate;
        this.missing = missing;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Date getCallingDate() {
        return callingDate;
    }

    public void setCallingDate(Date callingDate) {
        this.callingDate = callingDate;
    }

    public boolean isMissing() {
        return missing;
    }

    public void setMissing(boolean missing) {
        this.missing = missing;
    }
}
