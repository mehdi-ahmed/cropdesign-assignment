package com.cropdesign.assignment.util;


import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class JAXBHandlerUtilTest {

    public static final String XML_FILE_TEST = "terms-test.xml";
    public static final String XML_NOT_EXIST = "non-existing.xml";


    @Before
    public void initialize() {
    }

    @Test
    public void readXmlFileReturnNullWhenFileDoesNotExist() throws Exception {
        assertNull(JAXBHandlerUtil.readXmlFile(XML_NOT_EXIST));
    }

    @Test
    public void readXmlFile() throws Exception {
        assertNotNull(JAXBHandlerUtil.readXmlFile(XML_FILE_TEST));
    }
}