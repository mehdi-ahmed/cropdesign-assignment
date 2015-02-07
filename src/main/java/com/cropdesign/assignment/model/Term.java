package com.cropdesign.assignment.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "term")
public class Term {

    private String id;
    private String name;
    private String namespace;
    private Def def;
    private List<String> subset;
    private List<Synonym> synonym;
    private List<String> is_a;
    private List<Relationship> relationship;
    private String comment;
    private String is_obsolete;
    private List<String> consider;
    private List<String> alt_id;
    private List<XrefAnalog> xref_analogs;


    @XmlElement(name = "relationship")
    public List<Relationship> getRelationship() {
        return relationship;
    }

    public void setRelationship(List<Relationship> relationship) {
        this.relationship = relationship;
    }

    public String getIs_obsolete() {
        return is_obsolete;
    }

    public void setIs_obsolete(String is_obsolete) {
        this.is_obsolete = is_obsolete;
    }

    public List<String> getConsider() {
        return consider;
    }

    public void setConsider(List<String> consider) {
        this.consider = consider;
    }

    @XmlElement(name = "xref_analog")
    public List<XrefAnalog> getXref_analogs() {
        return xref_analogs;
    }

    public void setXref_analogs(List<XrefAnalog> xref_analogs) {
        this.xref_analogs = xref_analogs;
    }

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

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Def getDef() {
        return def;
    }

    public void setDef(Def def) {
        this.def = def;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    @XmlElement(name = "synonym")
    public List<Synonym> getSynonym() {
        return synonym;
    }

    public void setSynonym(List<Synonym> synonym) {
        this.synonym = synonym;
    }


}
