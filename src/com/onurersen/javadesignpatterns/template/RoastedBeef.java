package com.onurersen.javadesignpatterns.template;

public class RoastedBeef extends DinnerProcedure{


    @Override
    public void prepareIngredients() {
        System.out.println("Preparing ingredients for beef...");
    }

    @Override
    public void bakeInOven() {
        System.out.println("Bake the beef in oven @ 240°C ...");
    }

    public void washTheDishes(){
        System.out.println("Washing the dishes...");
    }
}
