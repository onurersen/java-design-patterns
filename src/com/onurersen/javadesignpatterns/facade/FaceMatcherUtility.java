package com.onurersen.javadesignpatterns.facade;

public class FaceMatcherUtility implements IFaceDataAccess,IFaceClassifier{

    @Override
    public void cacheFaceData() {
        System.out.println("Caching facedata from database");
    }

    @Override
    public void setFaceDimensions(int x, int y) {
        System.out.println("Setting Face Dimensions - X : " + x + " Y : " + y);
    }

    @Override
    public void classifyInputFace() {
        System.out.println("Input face classified...");
    }

    @Override
    public int decideLockUnlock() {
        System.out.println("Deciding lock or unlock using facial data results...");
        return 1;
    }
}
