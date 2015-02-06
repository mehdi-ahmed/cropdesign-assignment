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

public class GoTermsTree extends JPanel {

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

    public GoTermsTree() throws JAXBException {
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

        Dimension minimumSize = new Dimension(600, 600);
        //htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(600);
        splitPane.setPreferredSize(new Dimension(600, 600));

        // Add the split pane to this panel.
        add(splitPane);
    }

    private void createNodes(DefaultMutableTreeNode top) throws JAXBException {
        DefaultMutableTreeNode term;
        DefaultMutableTreeNode name;
        DefaultMutableTreeNode namespace;
        DefaultMutableTreeNode top_synonym = null;

        //call constructor and fill the list after reading and parsing
        XmlReader xmlReader = new XmlReader();
        List<Term> terms = xmlReader.getTerms();
        List<Synonym> synonyms;
        DefaultMutableTreeNode term = null;
        DefaultMutableTreeNode name = null;
        DefaultMutableTreeNode namespace = null;


        List<Term> terms = JAXBHandlerUtil.unMarshal();

        for (Term t : terms) {
            term = new DefaultMutableTreeNode(t.getId());
            name = new DefaultMutableTreeNode("name="+t.getName());
            namespace = new DefaultMutableTreeNode("namespace="+t.getNamespace());
            top_synonym = new DefaultMutableTreeNode("synonym");
            //synonyms = t.getSynonym();
             /*if(synonyms != null) {
                for(Synonym s : synonyms) {
                   // top_synonym = new DefaultMutableTreeNode("synonym_text="+s.getSynonym_text());

                }
            }*/
            term.add(name);
            term.add(namespace);
            term.add(top_synonym);
            top.add(term);
        }
    }

   /* Node createDataTree () {
        Node t, u, v, w, x;
        Node z = new Node ("zero");

        z.children.add (t = new Node ("one A"));
        t.children.add (u = new Node ("twoA A"));
        t.children.add (u = new Node ("twoA B"));
        t.children.add (u = new Node ("twoA C"));
        u.children.add (w = new Node ("threeAC A"));
        u.children.add (w = new Node ("threeAC B"));
        t.children.add (u = new Node ("twoA D"));
        z.children.add (t = new Node ("one B"));
        t.children.add (u = new Node ("twoB A"));
        t.children.add (u = new Node ("twoB B"));
        u.children.add (w = new Node ("threeBB A"));
        u.children.add (w = new Node ("threeBB B"));
        w.children.add (x = new Node ("threeBBB A"));
        w.children.add (x = new Node ("threeBBB B"));
        t.children.add (u = new Node ("twoB C"));
        t.children.add (u = new Node ("twoB D"));
        z.children.add (t = new Node ("one C"));
        t.children.add (u = new Node ("twoC A"));
        t.children.add (u = new Node ("twoC B"));
        t.children.add (u = new Node ("twoC C"));
        t.children.add (u = new Node ("twoC D"));
        z.children.add (t = new Node ("one D"));
        t.children.add (u = new Node ("twoD A"));
        t.children.add (u = new Node ("twoD B"));
        t.children.add (u = new Node ("twoD C"));
        t.children.add (u = new Node ("twoD D"));
        return z;
    } // end createDataTree*/
//} // end class JTreeExample
































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
