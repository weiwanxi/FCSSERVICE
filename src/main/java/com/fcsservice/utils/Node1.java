package com.fcsservice.utils;

import java.util.List;

/**
 * Created by YE on 2017/11/26 20:57.
 */
public class Node1 {
    private List<Node2> children;
    private String value;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Node2> getChildren() {
        return children;
    }

    public void setChildren(List<Node2> children) {
        this.children = children;
    }
}
