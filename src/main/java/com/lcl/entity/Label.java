package com.lcl.entity;

import lombok.Data;

import java.util.List;

@Data
public class Label {
    private String name;
    private boolean radio;
    private List<String> childLabel;


    public Label(String name) {
        this.name = name;
    }


}
