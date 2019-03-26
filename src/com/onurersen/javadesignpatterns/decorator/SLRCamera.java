package com.onurersen.javadesignpatterns.decorator;

public class SLRCamera implements Camera {

    @Override
    public String prepareCamera() {
        return "preparing Canon EOS 600D";
    }
}
