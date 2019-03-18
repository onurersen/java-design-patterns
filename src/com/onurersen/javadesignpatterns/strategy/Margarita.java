package com.onurersen.javadesignpatterns.strategy;

// A variation class implementing PizzaStrategy
public class Margarita implements PizzaStrategy{

    @Override
    public void prepareIngredients(int ovenHeatDegree) {
        System.out.println("---MARGARITA PREPARATION START---");
        System.out.println("Oven is hot. It is " + ovenHeatDegree + " °C");
        System.out.println("1/2 recipe homemade pizza dough.");
        System.out.println("1 Tablespoon olive oil.");
        System.out.println("2 cloves roasted garlic, finely chopped.");
        System.out.println("1/4 cup your favorite pizza or tomato sauce.");
        System.out.println("8 ounces mozzarella cheese, sliced into 1/2 inch thick pieces.");
        System.out.println("2 plum tomatoes, sliced (or any tomato you like)");
        System.out.println("handful of fresh basil.");
        System.out.println("fresh ground pepper, to taste.");
        System.out.println("---MARGARITA PREPARATION END---");
    }

}
