package com.onurersen.javadesignpatterns.flyweight;

public class CoffeeShop {

    public static void main(String[] args){

        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.BLACK_COFFEE);

        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.BLACK_COFFEE);

        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.BLACK_COFFEE);

        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.CAFE_CREME);
        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.CAFE_CREME);


        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.ESPRESSO);
        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.ESPRESSO);

        CoffeeMachine.listStock();
    }
}
