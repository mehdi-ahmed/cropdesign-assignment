package com.cropdesign.assignment.util;


import com.cropdesign.assignment.model.Obo;
import com.cropdesign.assignment.model.Term;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

/**
 * Utility Class that reads an XML File, and then Jaxb is used to unmarshal the XML document into a Java content tree
 * To be used by the UI(e.g JTree..)
 */

public class JAXBHandlerUtil {

    public final static String XML_FILE_NAME = "go_daily-termdb.obo-xml";

    /**
     * the file reader
     */
    private static Reader reader;
    /**
     * StAX parser reader
     */
    private static XMLStreamReader xmlStreamReader;
    /**
     * xml unmarshaller with importer context
     */
    private static Unmarshaller unmarshaller;

    public static List<Term> getTerms() throws JAXBException {
        try {
            Obo obo = (Obo) getUnmarshaller().unmarshal(xmlStreamReader);
            return obo.getTerms();
        } catch (final JAXBException e) {
            throw new RuntimeException("XML input file parsing error: unable to setup parser context.", e);
        }
    }


    public static JAXBContext createJAXBContext() throws JAXBException {
        return JAXBContext.newInstance(Obo.class);
    }

    public static void createUnMarshaller() throws JAXBException {
        final JAXBContext jaxbContext = createJAXBContext();
        unmarshaller = jaxbContext.createUnmarshaller();
    }

    public static Reader getReader(String filename) throws FileNotFoundException {
        ClassLoader classLoader = JAXBHandlerUtil.class.getClassLoader();
        if (classLoader.getResource(filename) != null) {
            return new FileReader(classLoader.getResource(filename).getFile());
        }
        return null;
    }


    public static void createStAXReader(String filename) {
        try {
            final XMLInputFactory xmlif = XMLInputFactory.newInstance();
            xmlif.setProperty(XMLInputFactory.IS_COALESCING, true);
            reader = getReader(filename);
            xmlStreamReader = xmlif.createXMLStreamReader(reader);
        } catch (final Exception e) {
            throw new IllegalArgumentException("XML input read error: Unable to create reader.", e);
        }
    }

    public static Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }
}

