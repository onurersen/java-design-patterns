package com.onurersen.javadesignpatterns.template;

public abstract class DinnerProcedure {

    /** superclass defines order of execution**/
    public void roastMeal(){
        prepareIngredients();
        bakeInOven();
        washTheDishes();
    }

    /** abstract methods left for subclasses to implement **/
    public abstract void prepareIngredients();
    public abstract void bakeInOven();

    /** common method for all subclasses, superclass handles it for all **/
    public void washTheDishes(){
        System.out.println("Washing the dishes...");
    }

}
