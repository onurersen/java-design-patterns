package com.onurersen.javadesignpatterns.factory;

public class Truck implements Vehicle {

    private static final int PRICE_MULTIPLIER = 3;

    public Truck(){
        System.out.println("A truck is being washed...");
    }

    public int getPriceMultiplier() {
        return PRICE_MULTIPLIER;
    }

}
