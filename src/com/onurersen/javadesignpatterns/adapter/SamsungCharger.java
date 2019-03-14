package com.onurersen.javadesignpatterns.adapter;

import java.util.Random;

public class SamsungCharger {

    Random random = new Random();

    // Samsung starts charging between 0 and 4 seconds...
    public void testElectricity(){
        int low = 0;
        int high = 4;
        int duration = random.nextInt(high-low) + low;
        System.out.println("Plugged in Samsung Device, Checking device voltage in " + duration + " seconds");
    }

    public void terminateBatteryPower(){
        System.out.println("Terminating battery power...");
    }

    public void initiateCharge(){
        System.out.println("Initiating charge...");

    }

}
