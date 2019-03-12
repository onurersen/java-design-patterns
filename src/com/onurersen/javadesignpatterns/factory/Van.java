package com.onurersen.javadesignpatterns.factory;

public class Van implements Vehicle {

    private static final int PRICE_MULTIPLIER = 2;

    public Van(){
        System.out.println("A van is being washed...");
    }

    public int getPriceMultiplier() {
        return PRICE_MULTIPLIER;
    }
}
