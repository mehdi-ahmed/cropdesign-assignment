package com.cropdesign.assignment.ui.util;


import com.cropdesign.assignment.model.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * This pseudo Builder class' purpose is to populate a Swing JTree.
 */

public class CreateDataTree {

    public Node createDataTree(List<Term> terms) throws JAXBException {

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
                    if (s.getDbxref() != null) {
                        synonym.children.add(dbxref = new Node("dbxref"));
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
     * Loop(only level 1) through the Tree and find the referenced (IS_A) node element.
     *
     * @param root
     * @param s
     * @return
     */

    public TreePath find(DefaultMutableTreeNode root, String s) {
        DefaultMutableTreeNode currentNode = root.getNextNode();
        do {
            if (currentNode.getLevel() == 1) {
                if (currentNode.toString().equalsIgnoreCase(s)) {
                    return new TreePath(currentNode.getPath());
                }
            }
            currentNode = currentNode.getNextNode();
            continue;
        }
        while (currentNode != null);
        return null;
    }

    /**
     * Recursive method maint to transfer the tree to the Swing JTree, using DefaultMutableTreeNode
     *
     * @param t
     * @return
     */

    public DefaultMutableTreeNode createNodes(NodeIF t) {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(t);
        for (NodeIF n : t.getChildren()) {
            top.add(createNodes(n));
        }
        return top;
    }

}
