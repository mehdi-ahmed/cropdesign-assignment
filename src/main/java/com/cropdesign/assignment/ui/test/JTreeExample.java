import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class JTreeExample {

    public static void main (String args []) {
        JTreeExample j = new JTreeExample ();
    } // end main

    public JTreeExample () {
        Node top = createDataTree ();
        DefaultMutableTreeNode jtTop = createNodes(top);
        JTree tree = new JTree(jtTop);
        JScrollPane treeView = new JScrollPane(tree);
        JFrame jf = new JFrame ("JTree example - ND");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize (230, 400);
        jf.setLocationRelativeTo (null);
        jf.setVisible (true);
        jf.add (treeView, BorderLayout.CENTER);
    } // end constructor

    DefaultMutableTreeNode createNodes (NodeIF t) {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode (t);
        for (NodeIF n: t.getChildren()) top.add (createNodes (n));
        return top;
    } // end createNodes

    Node createDataTree () {
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
    } // end createDataTree
} // end class JTreeExample

interface NodeIF {
    public ArrayList <NodeIF> getChildren ();
} // end NodeIF

class Node implements NodeIF {
    String data;
    ArrayList <NodeIF> children = new ArrayList <NodeIF> ();

    public Node (String s) {data = s;}

    public ArrayList <NodeIF> getChildren () {return children;}

    public String toString () {return data;}
} // end Node

