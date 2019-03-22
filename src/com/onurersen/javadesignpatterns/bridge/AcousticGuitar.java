package com.onurersen.javadesignpatterns.bridge;

public class AcousticGuitar extends Guitar {
    private int x, y;

    public AcousticGuitar(int x, int y, Wood wood) {
        super(wood);
        this.x = x;
        this.y = y;
    }

    @Override
    public void chopWoodForGuitar() {
        wood.chopWood(x, y);
    }

    @Override
    public void paintWoodOfGuitar(String color) {
        wood.paintWood(color);
    }

    @Override
    public void leaveGuitarToDry() {
        System.out.println("Leaving wood to dry...");
    }
}
