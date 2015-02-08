package com.cropdesign.assignment.util;


import com.cropdesign.assignment.model.Term;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.Reader;
import java.util.List;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class JAXBHandlerUtilTest {

    public static final String XML_FILE_TEST_OK = "terms-test.xml";
    public static final String XML_NOT_EXIST = "non-existing.xml";
    public static final String XML_NOT_PARSABLE = "unparsable-file.xml";


    @Mock
    private File mockInputFile;
    private Reader reader;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(mockInputFile.getName()).thenReturn("someFileName");
    }

    @Test
    public void readXmlFileReturnNullWhenFileDoesNotExist() throws Exception {
        assertNull(JAXBHandlerUtil.getReader(XML_NOT_EXIST));
    }

    @Test
    public void readXmlFile() throws Exception {
        assertNotNull(JAXBHandlerUtil.getReader(XML_FILE_TEST_OK));
    }

    @Test
    public void testCreateJAXBContext() throws JAXBException {
        assertNotNull(JAXBHandlerUtil.createJAXBContext());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createStAXReaderThrowIllegalExceptionWhenFileReaderIsNull() throws JAXBException {
        JAXBHandlerUtil.createStAXReader(mockInputFile.getName());
    }


    @Test
    public void unmarshalUnParseableXMLShouldThrowAnException() throws JAXBException {
        try {
            JAXBHandlerUtil.createUnMarshaller();
            JAXBHandlerUtil.createStAXReader(XML_NOT_PARSABLE);
            JAXBHandlerUtil.getTerms();
        } catch (final RuntimeException e) {
            assertEquals("XML input file parsing error: unable to setup parser context.", e.getMessage());
        }

    }

    @Test
    public void unmarshalXML() throws JAXBException {
        JAXBHandlerUtil.createUnMarshaller();
        JAXBHandlerUtil.createStAXReader(XML_FILE_TEST_OK);
        List<Term> terms = JAXBHandlerUtil.getTerms();
        assertEquals(terms.size(), 2);

        Term term1 = terms.get(0);
        assertEquals(term1.getIs_a().size(), 4);
        assertEquals(term1.getIs_a().get(0), "GO:0034308");

        Term term2 = terms.get(1);
        assertEquals(term2.getIs_a().get(0), "GO:2001316");
    }
}