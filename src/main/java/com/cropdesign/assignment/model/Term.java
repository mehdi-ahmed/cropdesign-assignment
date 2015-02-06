package com.cropdesign.assignment.model;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "term")
public class Term {

    private String id;
    private String name;
    private String nameSpace;
    private String def;
    private List<String> is_a;
    private List<String> alt_id;
    private List<String> subset;
    private List<String> synonym;
    private String xref_analog;
    private String relationship;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public List<String> getIs_a() {
        return is_a;
    }

    public void setIs_a(List<String> is_a) {
        this.is_a = is_a;
    }

    public List<String> getAlt_id() {
        return alt_id;
    }

    public void setAlt_id(List<String> alt_id) {
        this.alt_id = alt_id;
    }

    public List<String> getSubset() {
        return subset;
    }

    public void setSubset(List<String> subset) {
        this.subset = subset;
    }

    public List<String> getSynonym() {
        return synonym;
    }

    public void setSynonym(List<String> synonym) {
        this.synonym = synonym;
    }

    public String getXref_analog() {
        return xref_analog;
    }

    public void setXref_analog(String xref_analog) {
        this.xref_analog = xref_analog;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
