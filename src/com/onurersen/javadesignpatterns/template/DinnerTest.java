package com.onurersen.javadesignpatterns.template;

public class DinnerTest {

    public static void main(String[] args){

        /** Cooking RoastedBeef **/
        RoastedBeef roastedBeef = new RoastedBeef();
        roastedBeef.prepareIngredients();
        roastedBeef.bakeInOven();
        roastedBeef.washTheDishes();

        /** Cooking RoastedBeef **/
        RoastedChicken roastedChicken = new RoastedChicken();
        roastedChicken.prepareIngredients();
        roastedChicken.bakeInOven();
        roastedChicken.washTheDishes();

    }

}
