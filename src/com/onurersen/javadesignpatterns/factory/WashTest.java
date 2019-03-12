package com.onurersen.javadesignpatterns.factory;

import java.util.Scanner;

public class WashTest {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter vehicle type (Car : 0 , Van : 1 , Truck : 2)");
        int type = input.nextInt();
        WashFactory factory = new WashFactory();
        Vehicle.VehicleType vehicleType = Vehicle.VehicleType.values()[type];
        factory.washVehicle(vehicleType);

    }

}
