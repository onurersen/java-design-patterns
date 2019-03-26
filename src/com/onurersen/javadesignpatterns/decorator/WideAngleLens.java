package com.onurersen.javadesignpatterns.decorator;

public class WideAngleLens extends CameraDecorator {

    public WideAngleLens(Camera camera) {
        super(camera);
    }

    public String prepareCamera(){
        return assembleWideAngleLens(camera.prepareCamera());
    }

    public String assembleWideAngleLens(String inputCamera){
        return inputCamera + " with 18-200 wide angle lens";
    }
}
