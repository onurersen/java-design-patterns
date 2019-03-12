package com.onurersen.javadesignpatterns.factory;

public class WashFactory {

    private static final int BASE_PRICE = 1000;

    public void washVehicle(Vehicle.VehicleType type) {
        Vehicle vehicle = null;
        switch (type) {
            case CAR: {
                vehicle = new Car();
                break;
            }
            case VAN: {
                vehicle = new Van();
                break;
            }
            case TRUCK: {
                vehicle = new Truck();
                break;
            }
        }
        System.out.println("Total washing cost is : $" + vehicle.getPriceMultiplier() * BASE_PRICE);
    }

}
