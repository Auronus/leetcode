package com.company.reflection;

public class SoEasyClass {
    public SoEasySubClass subClass;
    public double someDoubleValueField;
    private String someStringValueField;

    public SoEasyClass(SoEasySubClass subClass, double someDoubleValueField, String someStringValue) {
        this.subClass = subClass;
        this.someDoubleValueField = someDoubleValueField;
        this.someStringValueField = someStringValue;
    }

    public SoEasySubClass getSubClass() {
        return subClass;
    }

    public double getSomeDoubleValueField() {
        return someDoubleValueField;
    }

    public String getSomeStringValueField() {
        return someStringValueField;
    }
}
