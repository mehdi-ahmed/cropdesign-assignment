package com.cropdesign.assignment.ui;

import com.cropdesign.assignment.model.*;
import com.cropdesign.assignment.ui.event.IS_ASelectionListener;
import com.cropdesign.assignment.ui.util.CreateDataTree;
import com.cropdesign.assignment.ui.util.Node;
import com.cropdesign.assignment.util.JAXBHandlerUtil;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.bind.JAXBException;

/**
 * JTree element inside a JPanel container.
 * ref @http://docs.oracle.com/javase/tutorial/uiswing/components/tree.html
 */

public class GoTermsTree extends JPanel {

    private List<Term> terms;
    private CreateDataTree createDataTree;
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";

    // Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    public GoTermsTree() throws JAXBException {
        super(new GridLayout(1, 0));

        //Read and Parse XML file, then retrieve list of terms.
        JAXBHandlerUtil.createUnMarshaller();
        JAXBHandlerUtil.createStAXReader(JAXBHandlerUtil.XML_FILE_NAME);
        terms = JAXBHandlerUtil.getTerms();


        // Creating the nodes.
        createDataTree = new CreateDataTree();
        Node top = createDataTree.createDataTree(terms);

        // Create a tree that allows one selection at a time.
        //tree = new JTree(top);
        DefaultMutableTreeNode jtTop = createDataTree.createNodes(top);
        final JTree tree = new JTree(jtTop);
        tree.addTreeSelectionListener(new IS_ASelectionListener(tree));
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        // Create the scroll pane and add the tree to it.
        JScrollPane treeView = new JScrollPane(tree);

        // Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);

        Dimension minimumSize = new Dimension(600, 600);
        //htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(600);
        splitPane.setPreferredSize(new Dimension(600, 600));

        // Add the split pane to this panel.
        add(splitPane);
    }


    /**
     * Create the GUI and show it. For thread safety, this method should be invoked from the event dispatch thread.
     */
    private static void createAndShowGUI() throws JAXBException {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        // Create and set up the window.
        JFrame frame = new JFrame("CropDesign assignment - GO terms parsing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add content to the window.
        frame.add(new GoTermsTree());

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    createAndShowGUI();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
