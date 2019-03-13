package com.onurersen.javadesignpatterns.template;

public class RoastedChicken extends DinnerProcedure{

    @Override
    public void prepareIngredients() {
        System.out.println("Preparing ingredients for chicken...");
    }

    @Override
    public void bakeInOven() {
        System.out.println("Bake the chicken in oven @ 200°C ...");
    }
}