package com.onurersen.javadesignpatterns.factory;

public interface Vehicle {

    enum VehicleType {
        CAR,
        VAN,
        TRUCK
    }

    int getPriceMultiplier();

}
