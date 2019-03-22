package com.onurersen.javadesignpatterns.bridge;

// Refined abstraction
public abstract class Guitar {

    protected Wood wood;

    protected Guitar(Wood wood){
        this.wood = wood;
    }

    public abstract void chopWoodForGuitar();

    public abstract void paintWoodOfGuitar(String color);

    public abstract void leaveGuitarToDry();

}
