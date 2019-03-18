package com.onurersen.javadesignpatterns.strategy;

import java.util.Random;

public class PizzaOrder {

    public static void main(String[] args){

        // create random heat value and pizza type to provide as input
        // for context/service class
        Random random = new Random();
        int heat = random.nextInt(50) + 200;
        int pizzaType = random.nextInt(2);

        PizzaStrategy strategy = null;

        switch (pizzaType){
            case 0:
                strategy = new Sicilian();
                break;
            case 1:
                strategy = new Margarita();
                break;
            case 2:
                strategy = new Calzone();
                break;
        }

        PizzaService pizzaService = new PizzaService(strategy);
        pizzaService.executePreparation(heat);

    }

}
