package com.company.reflection;

public class SoEasySubClass {
    public int counterField;
    private String descriptionField;

    public SoEasySubClass(int counter, String description) {
        this.counterField = counter;
        this.descriptionField = description;
    }

    public int getCounter() {
        return counterField;
    }

    public String getDescription() {
        return descriptionField;
    }
}
