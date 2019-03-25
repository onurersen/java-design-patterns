package com.onurersen.javadesignpatterns.composite;

public class Corporal implements Soldier {

    private String name;
    private int experienceYears;

    @Override
    public void add(Soldier soldier) {
        throw new UnsupportedOperationException("Soldier does not support operation.");
    }

    @Override
    public void remove(Soldier soldier) {
        throw new UnsupportedOperationException("Soldier does not support operation.");
    }

    @Override
    public Soldier getChild(int i) {
        throw new UnsupportedOperationException("Soldier does not support operation.");
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setExperienceYears(int years) {
        this.experienceYears = years;
    }

    @Override
    public int getExperienceYears() {
        return experienceYears;
    }

    @Override
    public void report() {
        System.out.println("---Corporal Reporting---");
        System.out.println("Name : " + getName());
        System.out.println("Experience(Years) : " + getExperienceYears());
        System.out.println("---");
    }
}
