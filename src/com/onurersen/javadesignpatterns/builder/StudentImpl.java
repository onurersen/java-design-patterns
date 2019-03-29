package com.onurersen.javadesignpatterns.builder;

// Final output of builder pattern
public class StudentImpl implements Student {

    private String name;

    private String parentName;

    private int number;

    public StudentImpl(String name, String parentName, int number) {
        this.name = name;
        this.parentName = parentName;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getParentName() {
        return parentName;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Applying student name is " + name +
                        "\nApplying student number is "
                            + number + "\nApplying student\'s parent name is "
                                + parentName;
    }

}
