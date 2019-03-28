package com.example.demo.entity.enumeration;

public enum TextColor {
    BLACK("black"), RED("red"), BLUE("blue"), GREEN("green");

    private String val;

    TextColor(String val){
        this.val = val;
    }

    public String val() {
        return val;
    }

}
