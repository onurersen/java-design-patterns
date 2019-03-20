package com.onurersen.javadesignpatterns.proxy;

import java.util.Random;

public class MainWareHouse implements IBrickStock {

    @Override
    public Brick requestMaterial(Brick brick) {
        Random random = new Random();
        int returnAmount = random.nextInt(100) + brick.getAmount();
        System.out.println("Mainwarehouse transferring " + returnAmount + " bricks.");
        return new Brick(returnAmount);
    }

}
