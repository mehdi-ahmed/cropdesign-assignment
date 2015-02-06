package com.cropdesign.assignment.util;


import com.cropdesign.assignment.model.Obo;
import com.cropdesign.assignment.model.Term;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XmlReader {

    private final static String XML_FILE_PATH = "go_daily-termdb.obo-xml";

    public List<Term> getTerms() {
        return terms;
    }

    private List<Term> terms;
    private Obo obo;


    public XmlReader() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Obo.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        ClassLoader classLoader = XmlReader.class.getClassLoader();
        File xmlFile = new File(classLoader.getResource(XML_FILE_PATH).getFile());

        obo = (Obo) jaxbUnmarshaller.unmarshal(xmlFile);
        terms = obo.getTerms();
    }
}
