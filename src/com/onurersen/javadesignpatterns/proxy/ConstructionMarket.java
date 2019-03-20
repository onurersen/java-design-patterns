package com.onurersen.javadesignpatterns.proxy;

// Proxy class
public class ConstructionMarket implements IBrickStock {

    // initial stock in construction market is 200
    private int currentAmount = 200;
    private MainWareHouse mainWareHouse = new MainWareHouse();

    @Override
    public Brick requestMaterial(Brick requestedBrick) {
        if (currentAmount > requestedBrick.getAmount()) {
            System.out.println("Current amount is " + currentAmount + " and it's enough , sell it to customer...");
            currentAmount = currentAmount - requestedBrick.getAmount();
        } else {
            System.out.println("Current amount is " + currentAmount + " and below requested amount, going to MainWareHouse...");
            currentAmount = currentAmount + mainWareHouse.requestMaterial(requestedBrick).getAmount();
            System.out.println("Current amount now in market is " + currentAmount +". Selling " + requestedBrick.getAmount() + " to customer.");
            currentAmount = currentAmount - requestedBrick.getAmount();
        }
        System.out.println(currentAmount + " bricks left in market.\n");
        return requestedBrick;
    }
}
