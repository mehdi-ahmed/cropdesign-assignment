package com.cropdesign.assignment.ui.test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class JTreeExample3 extends JFrame{

    Container contentPane;
    JTree jt;
    DefaultTreeModel treeModel;
    DefaultMutableTreeNode rootNode;
    JScrollPane jsp;

    public JTreeExample3(String args[]){
        super("JTreeExample");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        rootNode = new DefaultMutableTreeNode("Root Node");
        treeModel = new DefaultTreeModel(rootNode);
        jt = new JTree(treeModel);
        jsp = new JScrollPane(jt);
        populateTree();
        contentPane.add(jsp,BorderLayout.CENTER);

        //this code sets the root to visible and then selects it
       /* jt.setRootVisible(true);
        jt.setSelectionPath(new TreePath(rootNode));*/

    }

    public void populateTree(){
        DefaultMutableTreeNode dmtn;
        for (int i = 1; i < 10; i++){
            dmtn = new DefaultMutableTreeNode("Node " + i);
            for (int j = 1; j < 5; j++){
                dmtn.add(new DefaultMutableTreeNode("Leaf Node " + i + "." + j));
                if(i == 5) {
                    jt.setRootVisible(true);
                    jt.setSelectionPath(new TreePath(rootNode));
                }


            }
            rootNode.add(dmtn);
        }
        treeModel.reload();
    }

    public static void main(String args[]){
        JTreeExample3 jte = new JTreeExample3(args);
        jte.setSize(400,400);
        jte.setVisible(true);
    }
}