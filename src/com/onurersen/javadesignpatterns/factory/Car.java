package com.onurersen.javadesignpatterns.factory;

public class Car implements Vehicle{

    private static final int PRICE_MULTIPLIER = 1;

    public Car(){
        System.out.println("A car is being washed...");
    }

    public int getPriceMultiplier() {
        return PRICE_MULTIPLIER;
    }

}
