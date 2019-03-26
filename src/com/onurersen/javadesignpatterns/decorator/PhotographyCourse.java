package com.onurersen.javadesignpatterns.decorator;

public class PhotographyCourse {

    public static void main(String[] args) {

        Camera camera = new Tripod(new WideAngleLens(new SLRCamera()));
        System.out.println(camera.prepareCamera());

    }

}
