package com.onurersen.javadesignpatterns.flyweight;

public interface Cartridge {

    enum CARTRIDGE_TYPE {
        ESPRESSO,
        BLACK_COFFEE,
        CAFE_CREME
    }

    CARTRIDGE_TYPE getType();

    void setType(CARTRIDGE_TYPE type);

}
