package com.onurersen.javadesignpatterns.strategy;

// Our context/service class
public class PizzaService {

    private PizzaStrategy pizzaStrategy;

    // construct service with getting strategy implementer as input
    public PizzaService(PizzaStrategy pizzaStrategy) {
        this.pizzaStrategy = pizzaStrategy;
    }

    // let that implementer execute process
    public void executePreparation(int heat){
        pizzaStrategy.prepareIngredients(heat);
    }

}