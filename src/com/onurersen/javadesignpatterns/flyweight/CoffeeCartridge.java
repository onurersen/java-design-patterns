package com.onurersen.javadesignpatterns.flyweight;

public class CoffeeCartridge implements Cartridge {

    private CARTRIDGE_TYPE type;
    private CartridgeStock stock;

    public CoffeeCartridge(Cartridge.CARTRIDGE_TYPE type, CartridgeStock stock) {
        this.type = type;
        this.stock = stock;
    }

    public CARTRIDGE_TYPE getType() {
        return type;
    }

    public void setType(CARTRIDGE_TYPE type) {
        this.type = type;
    }

    public CartridgeStock getStock() {
        return stock;
    }

    public void setStock(CartridgeStock stock) {
        this.stock = stock;
    }
}
