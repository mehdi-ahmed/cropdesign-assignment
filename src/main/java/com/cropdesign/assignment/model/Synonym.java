package com.cropdesign.assignment.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "term")
public class Synonym {

    private String synonym_text;

    public String getSynonym_text() {
        return synonym_text;
    }

    public void setSynonym_text(String synonym_text) {
        this.synonym_text = synonym_text;
    }
}
