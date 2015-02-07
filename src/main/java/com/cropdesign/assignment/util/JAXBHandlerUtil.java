package com.cropdesign.assignment.util;


import com.cropdesign.assignment.model.Obo;
import com.cropdesign.assignment.model.Term;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * Utility Class that reads an XML File, and then Jaxb is used to unmarshal the XML document into a Java content tree
 * To be used by the UI(e.g JTree..)
 */

public class JAXBHandlerUtil {

    private final static String XML_FILE_NAME = "go_daily-termdb.obo-xml";


    public static List<Term> unMarshal() throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Obo.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        File xmlFile = readXmlFile(XML_FILE_NAME);
        Obo obo = (Obo) jaxbUnmarshaller.unmarshal(xmlFile);
        return obo.getTerms();

    }

    public static File readXmlFile(String fileName) {
        ClassLoader classLoader = JAXBHandlerUtil.class.getClassLoader();
        if (classLoader.getResource(fileName) == null) {
            return null;
        }
        return new File(classLoader.getResource(fileName).getFile());
    }

}

