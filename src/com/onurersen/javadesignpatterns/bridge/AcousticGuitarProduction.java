package com.onurersen.javadesignpatterns.bridge;

public class AcousticGuitarProduction {

    public static void main(String[] args){

        Guitar mahoganyAcousticGuitar = new AcousticGuitar(100,140,new Mahogany());
        Guitar roseWoodAcousticGuitar = new AcousticGuitar(200,120,new Rosewood());

        mahoganyAcousticGuitar.chopWoodForGuitar();
        mahoganyAcousticGuitar.paintWoodOfGuitar("brown");
        mahoganyAcousticGuitar.leaveGuitarToDry();

        System.out.println("\n");

        roseWoodAcousticGuitar.chopWoodForGuitar();
        roseWoodAcousticGuitar.paintWoodOfGuitar("black");
        roseWoodAcousticGuitar.leaveGuitarToDry();

    }

}
