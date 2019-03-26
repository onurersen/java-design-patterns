package com.onurersen.javadesignpatterns.decorator;

public class Tripod extends CameraDecorator {

    public Tripod(Camera inputCamera) {
        super(inputCamera);
    }

    public String prepareCamera() {
        return assembleTripod(camera.prepareCamera());
    }

    public String assembleTripod(String inputCamera) {
        return inputCamera + " and Manfrotto tripod attached";
    }

}
