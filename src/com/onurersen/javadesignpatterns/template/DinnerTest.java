package com.onurersen.javadesignpatterns.template;

public class DinnerTest {

    public static void main(String[] args){

        /** Cooking RoastedBeef **/
        RoastedBeef roastedBeef = new RoastedBeef();
        roastedBeef.roastMeal();

        /** Cooking RoastedBeef **/
        RoastedChicken roastedChicken = new RoastedChicken();
        roastedChicken.roastMeal();

    }

}
