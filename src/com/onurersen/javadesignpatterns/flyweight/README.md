---
title: "A Pattern A Day #15 : Flyweight"
author: "Berat Onur Ersen"
date: 2019-03-29
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The fifteenth pattern is **Flyweight**.

One and only purpose of the Flyweight pattern is minimizing memory usage through re-use. This pattern can also be used to reduce the load on memory by sharing objects. It can be preferred in circumstances where data compression or data caching is required.

Let's analyze this pattern via a coffee shop analogy.
Assume that we are running a coffee shop and we have 3 different kinds of coffee cartridges.

- `BLACK_COFFEE`
- `ESPRESSO`
- `CAFE_CREME`

We will create a `Cartridge` interface to be implemented by `CoffeeCartridge` class. This `Cartridge` interface will have 
enum values for those three cartridge types.

```java
public interface Cartridge {

    enum CARTRIDGE_TYPE {
        ESPRESSO,
        BLACK_COFFEE,
        CAFE_CREME
    }

    CARTRIDGE_TYPE getType();

    void setType(CARTRIDGE_TYPE type);

}
```

`CoffeeCartridge` class implementing `Cartridge` interface will have `CARTRIDGE_TYPE type` and `CartridgeStock stock` as instance variables.

```java
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
```

`CartridgeStock`class will be used to keep cartridge count. Its implementation will be as below.

```java
public class CartridgeStock {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
```

Although we are running a very qualified coffee shop, we will need a `CoffeeMachine` to insert `CoffeeCartridge` in it and brew coffee.  
`CoffeeMachine` will have a `cartridgeCache` and whenever we run out of `CoffeeCartridge`, `checkCartridge(Cartridge.CARTRIDGE_TYPE type, CartridgeStock stock)` method 
will insert three new cartridges. Client classes will execute `makeCoffee(Cartridge.CARTRIDGE_TYPE type)` with specifying `Cartridge.CARTRIDGE_TYPE`.

```java
public class CoffeeMachine {

    private static int DEFAULT_STOCK = 3;

    private static Map<Cartridge.CARTRIDGE_TYPE, CoffeeCartridge> cartridgeCache = new HashMap<>();

    private static void checkCartridge(Cartridge.CARTRIDGE_TYPE type, CartridgeStock stock) {
        cartridgeCache.computeIfAbsent(type, newType -> {
            System.out.println(type + " Cartridge is absent, adding " + DEFAULT_STOCK + " new cartridges for " + type);
            return new CoffeeCartridge(type, stock);
        });
    }

    public static void makeCoffee(Cartridge.CARTRIDGE_TYPE type) {

        CartridgeStock defaultStock = new CartridgeStock();
        defaultStock.setCount(DEFAULT_STOCK);
        checkCartridge(type, defaultStock);

        useCartridge(type);

    }

    private static void useCartridge(Cartridge.CARTRIDGE_TYPE type) {
        CoffeeCartridge coffeeCartridge = cartridgeCache.get(type);
        int cartridge = coffeeCartridge.getStock().getCount();
        coffeeCartridge.getStock().setCount(cartridge - 1);
        cartridgeCache.replace(type, coffeeCartridge);
        System.out.println(type + " cartridge was used. " + coffeeCartridge.getStock().getCount() + " " + type + " cartridge(s) left.");
        if (coffeeCartridge.getStock().getCount() == 0) {
            System.out.println(type + " cartridges are finished.");
            cartridgeCache.remove(type);
        }
    }

    public static void listStock() {
        System.out.println("\n");
        cartridgeCache.forEach((k, v) -> {
            System.out.println("Stock Status : Cartridge Type : " + k + " Count : " + v.getStock().getCount());
        });
        System.out.println("\n");
    }

}
```

Now, let's create a client class as `CoffeeShop`.

```java
public class CoffeeShop {

    public static void main(String[] args){

        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.BLACK_COFFEE);

        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.BLACK_COFFEE);

        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.BLACK_COFFEE);

        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.CAFE_CREME);
        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.CAFE_CREME);


        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.ESPRESSO);
        CoffeeMachine.makeCoffee(Cartridge.CARTRIDGE_TYPE.ESPRESSO);

        CoffeeMachine.listStock();
    }
}
```

Execution of this class will result as below.

```
BLACK_COFFEE Cartridge is absent, adding 3 new cartridges for BLACK_COFFEE
BLACK_COFFEE cartridge was used. 2 BLACK_COFFEE cartridge(s) left.
BLACK_COFFEE cartridge was used. 1 BLACK_COFFEE cartridge(s) left.
BLACK_COFFEE cartridge was used. 0 BLACK_COFFEE cartridge(s) left.
BLACK_COFFEE cartridges are finished.
CAFE_CREME Cartridge is absent, adding 3 new cartridges for CAFE_CREME
CAFE_CREME cartridge was used. 2 CAFE_CREME cartridge(s) left.
CAFE_CREME cartridge was used. 1 CAFE_CREME cartridge(s) left.
ESPRESSO Cartridge is absent, adding 3 new cartridges for ESPRESSO
ESPRESSO cartridge was used. 2 ESPRESSO cartridge(s) left.
ESPRESSO cartridge was used. 1 ESPRESSO cartridge(s) left.


Stock Status : Cartridge Type : ESPRESSO Count : 1
Stock Status : Cartridge Type : CAFE_CREME Count : 1
```

Every time a customer orders coffee, we check the `cartridgeCache` and if not exists insert three more cartridges of that type. If exists, we use the same cartridge batch and decrement it in every use.  
In this example, we assume coffee cartridge insertion is an expensive action, 
therefore it should be executed when there is no specified cartridge left. So, we put a caching mechanism. 

This was a simple demonstration of the Flyweight pattern.

---

Next design pattern is Visitor Pattern.

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 