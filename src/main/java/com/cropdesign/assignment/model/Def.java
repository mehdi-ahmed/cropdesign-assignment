package com.cropdesign.assignment.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "term")
public class Def {

    private String defstr;
    private List<Dbxref> dbxrefs;

    public String getDefstr() {
        return defstr;
    }

    public void setDefstr(String defstr) {
        this.defstr = defstr;
    }

    @XmlElement(name = "dbxref", required = false)
    public List<Dbxref> getDbxrefs() {
        return dbxrefs;
    }

    public void setDbxrefs(List<Dbxref> dbxrefs) {
        this.dbxrefs = dbxrefs;
    }

    @Override
    public String toString() {
        return "Def{" +
                "defstr='" + defstr + '\'' +
                ", dbxrefs=" + dbxrefs +
                '}';
    }
}
