package com.onurersen.javadesignpatterns.proxy;

public class Client {

    public static void main(String[] args){

        ConstructionMarket constructionMarket = new ConstructionMarket();

        Brick brickRequest = new Brick(110);
        System.out.println("Client wants to buy " + brickRequest.getAmount() + " bricks.");
        constructionMarket.requestMaterial(brickRequest);

        brickRequest = new Brick(120);
        System.out.println("Client wants to buy " + brickRequest.getAmount() + " bricks.");
        constructionMarket.requestMaterial(brickRequest);

        brickRequest = new Brick(70);
        System.out.println("Client wants to buy " + brickRequest.getAmount() + " bricks.");
        constructionMarket.requestMaterial(brickRequest);

    }
}
