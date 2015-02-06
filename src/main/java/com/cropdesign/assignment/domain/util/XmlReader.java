package com.cropdesign.assignment.domain.util;


import com.cropdesign.assignment.domain.Obo;
import com.cropdesign.assignment.domain.Term;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class XmlReader {

    private final static String XML_FILE_PATH = "go_daily-termdb.obo-xml";

    private List<Term> terms;
    private Obo obo;


    public XmlReader() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Obo.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        ClassLoader classLoader = XmlReader.class.getClassLoader();
        File xmlFile = new File(classLoader.getResource(XML_FILE_PATH).getFile());

        obo = (Obo) jaxbUnmarshaller.unmarshal(xmlFile);
        terms = obo.getTerms();

        for(Term term : terms) {
            System.out.println(term.getId());
        }

    }
}
