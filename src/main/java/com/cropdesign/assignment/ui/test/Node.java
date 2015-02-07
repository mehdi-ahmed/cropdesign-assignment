package com.cropdesign.assignment.ui.test;

import java.util.ArrayList;

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
}