package com.onurersen.javadesignpatterns.adapter;

public class ChargingTester {

    public static void main(String[] args){

        /** getting a samsung charger... **/
        SamsungCharger samsungCharger = new SamsungCharger();

        /** putting a charging adapter on it and connecting our iPhone **/
        Samsung2AppleChargingAdapter chargingAdapter = new Samsung2AppleChargingAdapter(samsungCharger);

        /** Expecting it to think a Samsung plugged in and start charging... **/
        chargingAdapter.bypassBatteryUsage();
        chargingAdapter.checkVoltage();
        chargingAdapter.startCharging();

    }

}
