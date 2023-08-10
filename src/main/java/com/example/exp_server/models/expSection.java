package com.example.exp_server.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class expSection {

    @Id
    private String id;
    private String secTitle;

    private List<exp> exp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecTitle() {
        return secTitle;
    }

    public void setSecTitle(String secTitle) {
        this.secTitle = secTitle;
    }

    public List<com.example.exp_server.models.exp> getExp() {
        return exp;
    }

    public void setExp(List<com.example.exp_server.models.exp> exp) {
        this.exp = exp;
    }

    // This method can be used to add a review to the list
    public void addExp(exp exp) {
        if (this.exp == null) {
            this.exp = new ArrayList<>();
        }
        this.exp.add(exp);
    }

    @Override
    public String toString() {
        return "expSection{" +
                "id='" + id + '\'' +
                ", secTitle='" + secTitle + '\'' +
                ", exp=" + exp +
                '}';
    }
}
