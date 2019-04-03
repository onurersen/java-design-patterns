package com.onurersen.javadesignpatterns.flyweight;

import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine {

    private static int DEFAULT_STOCK = 3;

    private static Map<Cartridge.CARTRIDGE_TYPE, CoffeeCartridge> cartridgeCache = new HashMap<>();

    private static void checkCartridge(Cartridge.CARTRIDGE_TYPE type, CartridgeStock stock) {
        cartridgeCache.computeIfAbsent(type, newType -> {
            System.out.println(type + " Cartridge is absent, adding " + DEFAULT_STOCK + " new cartridges for " + type);
            return new CoffeeCartridge(type, stock);
        });
    }

    public static void makeCoffee(Cartridge.CARTRIDGE_TYPE type) {

        CartridgeStock defaultStock = new CartridgeStock();
        defaultStock.setCount(DEFAULT_STOCK);
        checkCartridge(type, defaultStock);

        useCartridge(type);

    }

    private static void useCartridge(Cartridge.CARTRIDGE_TYPE type) {
        CoffeeCartridge coffeeCartridge = cartridgeCache.get(type);
        int cartridge = coffeeCartridge.getStock().getCount();
        coffeeCartridge.getStock().setCount(cartridge - 1);
        cartridgeCache.replace(type, coffeeCartridge);
        System.out.println(type + " cartridge was used. " + coffeeCartridge.getStock().getCount() + " " + type + " cartridge(s) left.");
        if (coffeeCartridge.getStock().getCount() == 0) {
            System.out.println(type + " cartridges are finished.");
            cartridgeCache.remove(type);
        }
    }

    public static void listStock() {
        System.out.println("\n");
        cartridgeCache.forEach((k, v) -> {
            System.out.println("Stock Status : Cartridge Type : " + k + " Count : " + v.getStock().getCount());
        });
        System.out.println("\n");
    }

}
