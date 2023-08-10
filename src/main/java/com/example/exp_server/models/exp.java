package com.example.exp_server.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class exp {
    private String expId;

    private String expTitle;

    private String expValue;

    private boolean paid = false;

    public exp(String expId, String expTitle, String expValue, boolean paid) {
        this.expId = expId;
        this.expTitle = expTitle;
        this.expValue = expValue;
        this.paid = paid;
    }

    public String getExpId() {
        return expId;
    }

    public void setExpId(String expId) {
        this.expId = expId;
    }

    public String getExpTitle() {
        return expTitle;
    }

    public void setExpTitle(String expTitle) {
        this.expTitle = expTitle;
    }

    public String getExpValue() {
        return expValue;
    }

    public void setExpValue(String expValue) {
        this.expValue = expValue;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "exp{" +
                "expId='" + expId + '\'' +
                ", expTitle='" + expTitle + '\'' +
                ", expValue='" + expValue + '\'' +
                ", paid=" + paid +
                '}';
    }
}
