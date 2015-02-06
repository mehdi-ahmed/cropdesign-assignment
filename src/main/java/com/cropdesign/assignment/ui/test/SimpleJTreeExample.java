package com.cropdesign.assignment.ui.test;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;

public class SimpleJTreeExample extends JFrame {
    public static void main( String[] argv ) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SimpleJTreeExample( "Simple JTree Example" );
            }
        });
    }
    public SimpleJTreeExample( String title ) {
        super( title );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setSize( 320, 240 );
        setLocationRelativeTo(null);
        setVisible( true );
    }
    private void init() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode( "Calendar" );
        DefaultMutableTreeNode months = new DefaultMutableTreeNode( "Months" );
        root.add( months );

        String[] monthLabels = {
                "January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December"
        };
        for (String ml:monthLabels) months.add( new DefaultMutableTreeNode( ml ) );
        DefaultMutableTreeNode weeks = new DefaultMutableTreeNode( "Weeks" );
        root.add( weeks );

        String[] weekLabels = {
                "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday"
        };
        for (String wl:weekLabels ) weeks.add( new DefaultMutableTreeNode( wl ) );

        JTree tree = new JTree( root );
        getContentPane().add(new JScrollPane(tree));
        searchTree(tree, tree.getPathForRow(0), "July");
        searchTree(tree, tree.getPathForRow(0), "Monday");
    }
    private static void searchTree(JTree tree, TreePath path, String q) {
        TreeNode node = (TreeNode)path.getLastPathComponent();
        if (node==null) return;
        if (node.toString().equals(q)) {
            tree.addSelectionPath(path);
        }
        if (!node.isLeaf() && node.getChildCount()>=0) {
            java.util.Enumeration e = node.children();
            while (e.hasMoreElements()) {
                searchTree(tree, path.pathByAddingChild(e.nextElement()), q);
            }
        }
    }
}