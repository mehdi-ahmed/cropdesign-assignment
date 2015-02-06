package com.cropdesign.assignment.ui;

import com.cropdesign.assignment.model.Term;
import com.cropdesign.assignment.util.XmlReader;

import java.awt.*;
import java.net.URL;
import java.util.List;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.bind.JAXBException;

public class TermsTree extends JPanel {

    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;

    // Optionally play with line styles. Possible values are
    // "Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";

    // Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    public TermsTree() throws JAXBException {
        super(new GridLayout(1, 0));

        // Create the nodes.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Obo");
        createNodes(top);

        // Create a tree that allows one selection at a time.
        tree = new JTree(top);
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
        //splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(900, 900);
        //htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(800);
        splitPane.setPreferredSize(new Dimension(900, 900));

        // Add the split pane to this panel.
        add(splitPane);
    }

    private void createNodes(DefaultMutableTreeNode top) throws JAXBException {
        DefaultMutableTreeNode term = null;
        XmlReader xmlReader = new XmlReader();
        List<Term> terms = xmlReader.getTerms();

        for (Term t : terms) {
            term = new DefaultMutableTreeNode(t.getId());
            top.add(term);
        }
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
        JFrame frame = new JFrame("TreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add content to the window.
        frame.add(new TermsTree());

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
