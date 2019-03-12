package com.onurersen.javadesignpatterns.factory;

import java.util.Scanner;

public class WashTestNonFactory {

    private static final int BASE_PRICE = 1000;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter vehicle type (Car : 0 , Van : 1 , Truck : 2)");
        int type = input.nextInt();
        switch (type) {
            case 0:
                Car car = new Car();
                System.out.println("Total washing cost is : $" + car.getPriceMultiplier() * BASE_PRICE);
            case 1:
                Van van = new Van();
                System.out.println("Total washing cost is : $" + van.getPriceMultiplier() * BASE_PRICE);
            case 2:
                Truck truck = new Truck();
                System.out.println("Total washing cost is : $" + truck.getPriceMultiplier() * BASE_PRICE);
        }
    }

}
