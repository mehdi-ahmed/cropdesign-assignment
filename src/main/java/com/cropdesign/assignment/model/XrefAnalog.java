package com.cropdesign.assignment.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "term")
public class XrefAnalog {

    private String acc;
    private String dbname;
    private String name;

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
