---
title: "A Pattern A Day #2 : Factory"
author: "Berat Onur Ersen"
date: 2019-03-13
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will be *16 patterns* in total.

---

The second pattern is **Factory**.

Factory pattern is the most commonly used pattern. Even in JVM, there are many places the factory pattern is widely used.

In the factory pattern, the type of objects that will be created in runtime is determined by the **factory class**.
In other words, we need a factory class which acts like a **factory to instantiate subclasses** during runtime. 
Implementing factory pattern depends on **using interfaces intensively**. 

Let's assume we are running a Car Wash. We can wash 3 types of vehicles as Car, Van, and Truck. 
We report the type of vehicle being washed and then determine the price regarding that type.

Without applying a factory pattern we can use below class to have the desired outcome:

```java
import java.util.Scanner;

public class WashTestNonFactory {

    private static final int BASE_PRICE = 1000;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter vehicle type (Car : 0 , Van : 1 , Truck : 2)");
        int type = input.nextInt();
        switch (type) {
            case 0:
                Car car = new Car();
                System.out.println("Total washing cost is : $" + car.getPriceMultiplier() * BASE_PRICE);
            case 1:
                Van van = new Van();
                System.out.println("Total washing cost is : $" + van.getPriceMultiplier() * BASE_PRICE);
            case 2:
                Truck truck = new Truck();
                System.out.println("Total washing cost is : $" + truck.getPriceMultiplier() * BASE_PRICE);
        }
    }

}
```

Here everything is managed within `WashTestNonFactory.java` class. 

If we need to do a similar thing in another class, we have to implement the same flow in that class, too.

The factory pattern move that will help us lose the connection between the washing process and vehicle classes will be creating a `WashFactory.java` class and delegate managing 
creation of vehicles in the washing process and determining washing cost via that factory class.

First, we create a `Vehicle` interface.

```java
public interface Vehicle {

    enum VehicleType {
        CAR,
        VAN,
        TRUCK
    }

    int getPriceMultiplier();

}
```

Then we implement this interface in vehicle classes; `Car.java`, `Van.java`, `Truck.java`.

```java
public class Car implements Vehicle{

    private static final int PRICE_MULTIPLIER = 1;

    public Car(){
        System.out.println("A car is being washed...");
    }

    public int getPriceMultiplier() {
        return PRICE_MULTIPLIER;
    }
}
```

Every vehicle class has its own `PRICE_MULTIPLIER` value. `WashFactory` uses this value to calculate the washing cost.

```java
public class WashFactory {

    private static final int BASE_PRICE = 1000;

    public void washVehicle(Vehicle.VehicleType type) {
        Vehicle vehicle = null;
        switch (type) {
            case CAR: {
                vehicle = new Car();
                break;
            }
            case VAN: {
                vehicle = new Van();
                break;
            }
            case TRUCK: {
                vehicle = new Truck();
                break;
            }
        }
        System.out.println("Total washing cost is : $" + vehicle.getPriceMultiplier() * BASE_PRICE);
    }
}
```

Whenever we need to implement a washing flow anywhere in the code, we can consult WashingFactory class to help us. 

Let's test our new `WashFactory` system via running below class :

```java
public class WashTest {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter vehicle type (Car : 0 , Van : 1 , Truck : 2)");
        int type = input.nextInt();
        WashFactory factory = new WashFactory();
        Vehicle.VehicleType vehicleType = Vehicle.VehicleType.values()[type];
        factory.washVehicle(vehicleType);

    }
    
}
```
The output will be :

```
Enter vehicle type (Car : 0 , Van : 1 , Truck : 2)
2
A truck is being washed...
Total washing cost is: $3000
```

If we ever need to add a new type of vehicle like `Bus`, `Motorcycle` etc. again we implement it in `WashFactory.java` class.

---

Next design pattern is Template Pattern.

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 