package com.cropdesign.assignment.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "term")
public class Relationship {

    private String type;
    private String to;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
