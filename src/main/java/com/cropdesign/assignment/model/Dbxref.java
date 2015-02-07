package com.cropdesign.assignment.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "def")
public class Dbxref {

    private String acc;
    private String dbname;

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
}
