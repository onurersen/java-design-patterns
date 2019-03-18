package com.onurersen.javadesignpatterns.facade;

public class LaserUtility implements ILaserBeam{

    @Override
    public void chargeLaser() {
        System.out.println("Charging laser, getting device ready...");
    }

    @Override
    public void beamLaser(int level) {
        System.out.println("Laser beam executed at level : " + level);
    }

}
