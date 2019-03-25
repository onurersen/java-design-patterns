package com.onurersen.javadesignpatterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Sarge implements Soldier {

    private List<Soldier> team = new ArrayList<>();
    private String name;
    private int experienceYears;

    @Override
    public void add(Soldier soldier) {

        team.add(soldier);
    }

    @Override
    public void remove(Soldier soldier) {

        team.remove(soldier);
    }

    @Override
    public Soldier getChild(int i) {
        return null;
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
        System.out.println("---Sarge Reporting---");
        System.out.println("Name : " + getName());
        System.out.println("Experience(Years) : " + getExperienceYears());
        System.out.println("---");
        for (Soldier soldier : team) {
            soldier.report();
        }
    }
}
