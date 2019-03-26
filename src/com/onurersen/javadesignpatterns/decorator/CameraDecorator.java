package com.onurersen.javadesignpatterns.decorator;

public class CameraDecorator implements Camera {

    protected Camera camera;

    public CameraDecorator(Camera inputCamera){
        this.camera = inputCamera;
    }

    @Override
    public String prepareCamera() {
        return camera.prepareCamera();
    }
}
