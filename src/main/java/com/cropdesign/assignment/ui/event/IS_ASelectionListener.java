package com.cropdesign.assignment.ui.event;

import com.cropdesign.assignment.ui.util.CreateDataTree;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class IS_ASelectionListener implements TreeSelectionListener {
    private final JTree tree;
    private CreateDataTree createDataTree = null;


    public IS_ASelectionListener(JTree tree) {
        this.tree = tree;
        createDataTree = new CreateDataTree();
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        DefaultMutableTreeNode root = null;
        DefaultMutableTreeNode swap;
        TreePath tp;
        swap = node;
        while (node.getParent() != null) {
            root = (DefaultMutableTreeNode) node.getParent();
            node = root;
        }
        node = swap;
                /* if nothing is selected */
        if (node == null) {
            return;
        }

        DefaultMutableTreeNode directParent = (DefaultMutableTreeNode) node.getParent();
        if (directParent.toString().equals("is_a")) {
            tp = createDataTree.find(root, node.toString());

            if (tp == null) {
                final JFrame parent = new JFrame();
                JOptionPane.showMessageDialog(parent, "IS_A reference not found!");
            } else {
                tree.setSelectionPath(tp);
                tree.scrollPathToVisible(tp);
            }
        }
    }
}
