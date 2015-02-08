package com.cropdesign.assignment.util;


import org.junit.Before;

public class JAXBHandlerUtilTest {

    public static final String XML_FILE_TEST = "terms-test.xml";
    public static final String XML_NOT_EXIST = "non-existing/xml";


    @Before
    public void initialize() {

    }

    public void readXmlFileWhenFileDoesNotExist() throws Exception {
        JAXBHandlerUtil.readXmlFile(XML_NOT_EXIST);
    }
}