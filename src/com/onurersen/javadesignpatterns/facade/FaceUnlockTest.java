package com.onurersen.javadesignpatterns.facade;

public class FaceUnlockTest {

    public static void main(String[] args){

        FaceDetectFacade facade = FaceDetectFacade.getInstance();
        facade.faceDetect();

    }

}
