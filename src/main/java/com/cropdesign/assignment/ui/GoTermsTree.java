package com.cropdesign.assignment.ui;

import com.cropdesign.assignment.model.*;
import com.cropdesign.assignment.ui.test.Node;
import com.cropdesign.assignment.ui.test.NodeIF;
import com.cropdesign.assignment.util.JAXBHandlerUtil;

import java.awt.*;
import java.net.URL;
import java.util.List;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.bind.JAXBException;

public class GoTermsTree extends JPanel {

    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;
    private List<Term> terms;

    // Optionally play with line styles. Possible values are
    // "Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";

    // Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    public GoTermsTree() throws JAXBException {
        super(new GridLayout(1, 0));


        //read ans Parse XML file
        JAXBHandlerUtil.createUnMarshaller();
        JAXBHandlerUtil.createStAXReader(JAXBHandlerUtil.XML_FILE_NAME);
        terms = JAXBHandlerUtil.getTerms();


        // Create the nodes.
        Node top = createDataTree();

        // Create a tree that allows one selection at a time.
        //tree = new JTree(top);
        DefaultMutableTreeNode jtTop = createNodes(top);
        final JTree tree = new JTree(jtTop);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();

    /* if nothing is selected */
                if (node == null) {
                    return;
                }

    /* retrieve the node that was selected */
                Object nodeInfo = node.getUserObject();
    /* React to the node selection. */
                final JFrame parent = new JFrame();
                JButton button = new JButton();

                button.setText("Click me to show dialog!");
                parent.add(button);
                parent.pack();
                parent.setVisible(true);

                button.addActionListener(new java.awt.event.ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        String name = JOptionPane.showInputDialog(parent,
                                "What is your name?", null);
                    }
                });
            }

        });


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
    }


    DefaultMutableTreeNode createNodes(NodeIF t) {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(t);
        for (NodeIF n : t.getChildren()) top.add(createNodes(n));
        return top;
    }


    Node createDataTree() throws JAXBException {

        Node term, name, namespace, def, defstr, dbxref, acc, dbname, synonym, is_a, consider, relationship, xref_analog;

        Node obo = new Node("obo");

        for (Term t : terms) {
            obo.children.add(term = new Node(t.getId()));
            term.children.add(name = new Node("name = " + t.getName())); //
            term.children.add(namespace = new Node("namespace = " + t.getNamespace())); //
            term.children.add(def = new Node("defs")); //
            def.children.add(defstr = new Node("defstr = " + t.getDef().getDefstr())); //
            def.children.add(dbxref = new Node("dbxref")); //
            if (t.getDef().getDbxrefs() != null) {
                for (Dbxref dbx_ref : t.getDef().getDbxrefs()) { //
                    dbxref.children.add(acc = new Node("acc = " + dbx_ref.getAcc())); //
                    dbxref.children.add(dbname = new Node("dbname = " + dbx_ref.getDbname())); //
                }
            }

            term.children.add(xref_analog = new Node("xref_analog"));
            if (t.getXref_analogs() != null) {
                for (XrefAnalog x : t.getXref_analogs()) {
                    xref_analog.children.add(new Node("acc = " + x.getAcc()));
                    xref_analog.children.add(new Node("dbname = " + x.getDbname()));
                    xref_analog.children.add(new Node("name = " + x.getName()));
                }
            }

            term.children.add(is_a = new Node("is_a"));
            if (t.getIs_a() != null) {
                for (String isa : t.getIs_a()) {
                    is_a.children.add(new Node(isa));
                }
            }

            term.children.add((synonym = new Node("synonyms")));

            if (t.getSynonym() != null) {
                for (Synonym s : t.getSynonym()) {
                    synonym.children.add(new Node("synonym_text = " + s.getSynonym_text()));
                    synonym.children.add(new Node(s.getSynonym_text()));
                    if (s.getDbxref() != null) {
                        dbxref.children.add(new Node("acc = " + s.getDbxref().getAcc())); //
                        dbxref.children.add(new Node("dbname = " + s.getDbxref().getDbname())); //
                    }
                }
            }

            term.children.add(relationship = new Node("relationship"));
            if (t.getRelationship() != null) {
                for (Relationship r : t.getRelationship()) {
                    relationship.children.add(new Node("type = " + r.getType()));
                    relationship.children.add(new Node("to = " + r.getTo()));
                }
            }


            term.children.add(new Node("comment = " + t.getComment()));
            term.children.add(new Node("is_obsolete = " + t.getIs_obsolete()));

            term.children.add(consider = new Node("consider"));
            if (t.getConsider() != null) {
                for (String cns : t.getConsider()) {
                    consider.children.add(new Node("consider = " + cns));
                }
            }

        }

        return obo;
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
