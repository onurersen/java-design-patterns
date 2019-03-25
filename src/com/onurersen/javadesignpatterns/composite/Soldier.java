package com.onurersen.javadesignpatterns.composite;

// component interface
public interface Soldier {

    void add(Soldier soldier);
    void remove(Soldier soldier);
    Soldier getChild(int i);
    void setName(String name);
    String getName();
    void setExperienceYears(int years);
    int getExperienceYears();
    void report();

}
