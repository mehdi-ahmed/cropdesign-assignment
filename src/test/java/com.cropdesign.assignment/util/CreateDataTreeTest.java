package com.cropdesign.assignment.util;

import com.cropdesign.assignment.model.Term;
import com.cropdesign.assignment.ui.util.CreateDataTree;
import com.cropdesign.assignment.ui.util.Node;
import org.junit.Before;
import org.junit.Test;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class CreateDataTreeTest {

    private CreateDataTree createDataTree;
    private List<Term> terms;
    private Node rootObo;
    DefaultMutableTreeNode rootTop;

    public static String EXISTING_IS_A_NODE = "GO:2001316";

    @Before
    public void setUp() throws Exception {
        try {
            JAXBHandlerUtil.createUnMarshaller();
            JAXBHandlerUtil.createStAXReader(JAXBHandlerUtilTest.XML_FILE_TEST_OK);
            terms = JAXBHandlerUtil.getTerms();
        } catch (final RuntimeException e) {
            assertEquals("XML input file parsing error: unable to setup parser context.", e.getMessage());
        }

        createDataTree = new CreateDataTree();
        Node rootObo = createDataTree.createDataTree(terms);
        rootTop = createDataTree.createNodes(rootObo);
    }


    @Test
    public void returnNullWhenIS_ANodeDoesNotExistingNode() {
        TreePath treePath =  createDataTree.find(rootTop, "DUMMY_IS_A");
        assertNull(treePath);
    }

    @Test
    public void findExistingNode() {
        TreePath treePath =  createDataTree.find(rootTop, EXISTING_IS_A_NODE);
        assertNotNull(treePath);
    }
}
