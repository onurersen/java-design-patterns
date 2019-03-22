package com.onurersen.javadesignpatterns.bridge;

// Concrete implementation
public class Rosewood implements Wood {
    @Override
    public void chopWood(int x, int y) {
        System.out.println("Rosewood chopped with dimensions X : " + x + " and Y : " + y);

    }

    @Override
    public void paintWood(String color) {
        System.out.println("Rosewood guitar being painted in " + color);
    }
}
