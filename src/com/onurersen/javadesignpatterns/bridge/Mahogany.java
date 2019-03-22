package com.onurersen.javadesignpatterns.bridge;

// Concrete implementation
public class Mahogany implements  Wood{
    @Override
    public void chopWood(int x, int y) {
        System.out.println("Mahogany chopped with dimensions X : " + x + " and Y : " + y);
    }

    @Override
    public void paintWood(String color) {
        System.out.println("Mahogany guitar being painted in " + color);
    }

}
