package com.onurersen.javadesignpatterns.facade;

public class FaceDetectFacade {

    private static FaceDetectFacade singletonFacade;

    private FaceDetectFacade(){}

    public void faceDetect(){

        LaserUtility laser = new LaserUtility();
        FaceMatcherUtility faceMatcher = new FaceMatcherUtility();

        /** initialize laser and data processor **/
        laser.chargeLaser();
        faceMatcher.cacheFaceData();

        /** start face recognition process **/
        laser.beamLaser(10);
        faceMatcher.setFaceDimensions(15,21);
        faceMatcher.classifyInputFace();
        faceMatcher.decideLockUnlock();

    }

    public static FaceDetectFacade getInstance(){
        if(singletonFacade == null)
            singletonFacade = new FaceDetectFacade();
        return singletonFacade;
    }

}
