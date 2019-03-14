package com.onurersen.javadesignpatterns.adapter;

import java.util.Random;

public class iPhoneCharger implements AppleChargerInterface{

    Random random = new Random();

    // an iPhone starts charging between 5 and 10 seconds...
    @Override
    public void checkVoltage() {
        int low = 5;
        int high = 10;
        int duration = random.nextInt(high-low) + low;
        System.out.println("Plugged in Apple Device, Checking device voltage in " + duration + " seconds");
    }

    @Override
    public void bypassBatteryUsage() {
        System.out.println("Bypassing battery usage...");
    }

    @Override
    public void startCharging() {
        System.out.println("Starting charge process...");
    }
}