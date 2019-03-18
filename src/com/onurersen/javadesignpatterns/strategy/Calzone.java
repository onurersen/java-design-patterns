package com.onurersen.javadesignpatterns.strategy;

public class Calzone implements PizzaStrategy{

    @Override
    public void prepareIngredients(int ovenHeatDegree) {
        System.out.println("---CALZONE PREPARATION START---");
        System.out.println("Oven is hot. It is " + ovenHeatDegree + " °C");
        System.out.println("First, make your Pizza Dough. Preheat the oven to 450 to 500 degrees.");
        System.out.println("Pour a large glug of olive oil into a hot frying pan.");
        System.out.println("Add the tomato sauce to the pan and stir. Cook for a few minutes, then add the spinach (in batches if you need to) and stir again. ");
        System.out.println("Divide the mushroom and spinach mixture evenly between the 4 pizza bases and spread it out nicely.");
        System.out.println("Cook for 10 to 15 minutes on the bottom of the preheated oven until the dough is puffed up and golden on top and the filling is hot.");
        System.out.println("Sift the flours and salt onto a clean work surface and make a well in the middle.");
        System.out.println("Place the ball of dough in a large flour-dusted bowl and flour the top of it.");
        System.out.println("Now remove the dough to a flour-dusted surface and knead it around a bit to push the air out with your hands - this is called punching down the dough.");
        System.out.println("Timing-wise, it's a good idea to roll the pizzas out about 15 to 20 minutes before you want to cook them.");
        System.out.println("---CALZONE PREPARATION END---");
    }

}
