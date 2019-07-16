---
title: "A Pattern A Day #8 : Proxy"
author: "Berat Onur Ersen"
date: 2019-03-20
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The eight pattern is **Proxy**.

The proxy pattern can be in two forms;

- Static Proxy
- Dynamic Proxy

In this post, I will tell about Static Proxy, to help to understand the basic idea. 
The proxy pattern as its name refers is about creating a structure that buffers invocation of certain functionalities.
When a class tries to invoke a method, it first goes to Proxy class and then due to certain circumstances execution happens.

_In a further update to this post, I will be adding Dynamic Proxy.
For now, knowing that Dynamic Proxy uses Java Reflection API, and processing if a method is executed on runtime is enough._

Proxy pattern is generally used to create **representation of real object** to control execution **resource-consuming processes** or creation of ** expensive objects**.  
It can even delay the construction of original object until it is unavoidable. In an implementation, the client is unaware of the existence of proxy. 

Let's show an example of this pattern via an analogy.

Think that we are building a house using a special brick. In the beginning, we don't know how much brick we will use throughout construction.  

We go to the construction market in our town. That construction market is a branch of a huge global construction market chain. 

Construction market does keep a certain amount of bricks in their stock at a time because of inventory cost and brick transfer is also another cost. So, the construction market is behaving like a proxy between the client and main warehouse.
Whenever we go to buy more than the amount exists in their inventory, they create transfer request to the main warehouse.

First, we create an `IBrickStock` interface which will be implemented by both `ConstructionMarket` and `MainWareHouse`.

```java
public interface IBrickStock {

    Brick requestMaterial(Brick brick);

}
```

This interface will be used to `requestMaterial()` from `MainWareHouse`. At this point, `ConstructionMarket` will take the request and due to brick 
availability, it will go to `MainWareHouse`.

`ConstructionMarket` implementation will look like below:

```java
// Proxy class
public class ConstructionMarket implements IBrickStock {

    // initial stock in construction market is 200
    private int currentAmount = 200;
    private MainWareHouse mainWareHouse = new MainWareHouse();

    @Override
    public Brick requestMaterial(Brick requestedBrick) {
        if (currentAmount > requestedBrick.getAmount()) {
            System.out.println("Current amount is " + currentAmount + " and it's enough , sell it to customer...");
            currentAmount = currentAmount - requestedBrick.getAmount();
        } else {
            System.out.println("Current amount is " + currentAmount + " and below requested amount, going to MainWareHouse...");
            currentAmount = currentAmount + mainWareHouse.requestMaterial(requestedBrick).getAmount();
            System.out.println("Current amount now in market is " + currentAmount +". Selling " + requestedBrick.getAmount() + " to customer.");
            currentAmount = currentAmount - requestedBrick.getAmount();
        }
        System.out.println(currentAmount + " bricks left in market.\n");
        return requestedBrick;
    }
}
```

It implements `requestMaterial()` and in method checks brick availability.
Behind that proxy class, we have `MainWareHouse` class also implementing `IBrickStock` interface. Real material request happens here whenever this class is called. 

```java
import java.util.Random;

public class MainWareHouse implements IBrickStock {

    @Override
    public Brick requestMaterial(Brick brick) {
        Random random = new Random();
        int returnAmount = random.nextInt(100) + brick.getAmount();
        System.out.println("Mainwarehouse transferring " + returnAmount + " bricks.");
        return new Brick(returnAmount);
    }

}
```

If we simulate `Client` via below class and request bricks for 3 times with different amounts.

```java
public class Client {

    public static void main(String[] args){

        ConstructionMarket constructionMarket = new ConstructionMarket();

        Brick brickRequest = new Brick(110);
        System.out.println("Client wants to buy " + brickRequest.getAmount() + " bricks.");
        constructionMarket.requestMaterial(brickRequest);

        brickRequest = new Brick(120);
        System.out.println("Client wants to buy " + brickRequest.getAmount() + " bricks.");
        constructionMarket.requestMaterial(brickRequest);

        brickRequest = new Brick(70);
        System.out.println("Client wants to buy " + brickRequest.getAmount() + " bricks.");
        constructionMarket.requestMaterial(brickRequest);

    }
}
```

The output of this execution will be:

```
Client wants to buy 110 bricks.
Current amount is 200 and it's enough , sell it to customer...
90 bricks left in market.

Client wants to buy 120 bricks.
Current amount is 90 and below requested amount, going to MainWareHouse...
Mainwarehouse transferring 211 bricks.
Current amount now in market is 301. Selling 120 to customer.
181 bricks left in market.

Client wants to buy 70 bricks.
Current amount is 181 and it's enough , sell it to customer...
111 bricks left in market.
```

As you see in this example, we limited access to our original object which is `MainWareHouse` because supplying bricks is an expensive process.

This was a very simple example of Proxy pattern. 

---

Next design pattern is Chain of Responsibility Pattern.

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 