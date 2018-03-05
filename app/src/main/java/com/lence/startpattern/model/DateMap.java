package com.lence.startpattern.model;


import java.util.ArrayList;

public class DateMap {
    String key;
    ArrayList<String> value;

    public DateMap(String key, ArrayList<String> value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<String> getValue() {
        return value;
    }

    public void setValue(ArrayList<String> value) {
        this.value = value;
    }
}
