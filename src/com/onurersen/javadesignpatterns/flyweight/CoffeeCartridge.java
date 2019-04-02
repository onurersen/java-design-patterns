package com.onurersen.javadesignpatterns.flyweight;

public class CoffeCartridge implements Cartridge {

    private int cartridgeCount;
    private CARTRIDGE_TYPE type;

    public CoffeCartridge(CARTRIDGE_TYPE type, int cartridgeCount) {
        this.type = type;
        this.cartridgeCount = cartridgeCount;
    }

    @Override
    public int getCartridgeCount() {
        return cartridgeCount;
    }

    @Override
    public void setCartridgeCount(int cartridgeCount) {
        this.cartridgeCount = cartridgeCount;
    }

    @Override
    public CARTRIDGE_TYPE getType() {
        return type;
    }

    @Override
    public void setType(CARTRIDGE_TYPE type) {
        this.type = type;
    }
}
