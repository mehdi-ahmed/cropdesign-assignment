package com.cropdesign.assignment.ui.util;


import java.util.ArrayList;

/**
 * Node contains data a links to child Node's
 * ref @ http://sandsduchon.org/duchon/Musings/a/index.html
 */

public class Node implements NodeIF {

    public String data;
    public ArrayList<NodeIF> children = new ArrayList<NodeIF>();

    public Node(String s) {
        data = s;
    }

    public ArrayList<NodeIF> getChildren() {
        return children;
    }

    public String toString() {
        return data;
    }

    public String getData() {
        return data;
    }
}