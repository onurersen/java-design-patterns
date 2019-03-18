package com.onurersen.javadesignpatterns.strategy;

public class Sicilian implements PizzaStrategy{
    @Override
    public void prepareIngredients(int ovenHeatDegree) {
        System.out.println("---SICILIAN PREPARATION START---");
        System.out.println("Oven is hot. It is " + ovenHeatDegree + " °C");
        System.out.println("1 1/2 cups water (warm)");
        System.out.println("1 1/2 tablespoons yeast (dry)");
        System.out.println("4 cups bread flour (or all-purpose flour)");
        System.out.println("1 1/2 teaspoons salt.");
        System.out.println("3 tablespoons olive oil (divided)");
        System.out.println("3-4 tablespoons tomato sauce.");
        System.out.println("1 cup shredded mozzarella cheese.");
        System.out.println("4 to 6 fresh basil leaves.");
        System.out.println("---SICILIAN PREPARATION END---");
    }

}
