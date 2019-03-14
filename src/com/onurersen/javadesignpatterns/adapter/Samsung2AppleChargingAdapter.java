package com.onurersen.javadesignpatterns.adapter;

public class Samsung2AppleChargingAdapter implements AppleChargerInterface{

    /** in adapter class we define an instance of class adapting **/
    SamsungCharger samsungCharger;

    public Samsung2AppleChargingAdapter(SamsungCharger newSamsungCharger){

        this.samsungCharger = newSamsungCharger;

    }

    @Override
    public void checkVoltage() {

        samsungCharger.testElectricity();

    }

    @Override
    public void bypassBatteryUsage() {

        samsungCharger.terminateBatteryPower();

    }

    @Override
    public void startCharging() {

        samsungCharger.initiateCharge();

    }
}
