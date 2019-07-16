---
title: "A Pattern A Day #4 : Adapter"
author: "Berat Onur Ersen"
date: 2019-03-15
draft: no
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The fourth pattern is **Adapter**.

In adapter pattern, as its name refers, we adapt certain functionalities those are not directly implementable in our current situation. 
That can be some sort of legacy code to be integrated with new application, or adaptation of a 3rd party library to be used in our project etc.

Again we'll use an analogy to explain this pattern...

Let's assume we are on vacation with a group of friends all using Samsung mobile phones. We have an iPhone and forgot our charger at home. (Such a stupid situation :))  
It will be a long trip and all along the way, we will need to charge our iPhone using a Samsung charger.

Charger specification changes regarding device type but generally let's say a charger follows this execution order:

1. Check electricity
1. Stop battery consumption
1. Start to charge

Now, let's go through how Apple and Samsung devices implement these.

### Apple Device

All Apple devices implement `AppleChargerInterface` which defines charging functionalities. (Later we will thank God that there is such an interface.)

Those functionalities are:

- `checkVoltage()`
- `bypassBatteryUsage()`
- `startCharging()`

That interface will look like this:

```java
public interface AppleChargerInterface {

    void checkVoltage();

    void bypassBatteryUsage();

    void startCharging();
}
```

Let `iPhoneCharger` implement them all:


```java
import java.util.Random;

public class iPhoneCharger implements AppleChargerInterface{

    Random random = new Random();

    // an iPhone starts charging between 5 and 10 seconds...
    @Override
    public void checkVoltage() {
        int low = 5;
        int high = 10;
        int duration = random.nextInt(high-low) + low;
        System.out.println("Plugged in Apple Device, Checking device voltage in " + duration + " seconds");
    }

    @Override
    public void bypassBatteryUsage() {
        System.out.println("Bypassing battery usage...");
    }

    @Override
    public void startCharging() {
        System.out.println("Starting charge process...");
    }
}
```

### Samsung Device

Samsung charger has following ones:

- `testElectricity()`
- `terminateBatteryPower()`
- `initiateCharge()`

```java
import java.util.Random;

public class Samsung charger {

    Random random = new Random();

    // Samsung starts charging between 0 and 4 seconds...
    public void testElectricity(){
        int low = 0;
        int high = 4;
        int duration = random.nextInt(high-low) + low;
        System.out.println("Plugged in Samsung Device, Checking device voltage in " + duration + " seconds");
    }

    public void terminateBatteryPower(){
        System.out.println("Terminating battery power...");
    }

    public void initiateCharge(){
        System.out.println("Initiating charge...");

    }

}
```

Now the only thing we need is to adapt Samsung charger to be used with our iPhone. 

Fortunately, `AppleChargerInterface` will help us to do that.

Here once again we come up with **the importance of using interfaces during implementation.**

### Adapter Implementation

We create a `Sasmsung2AppleChargingAdapter` which implements `AppleChargerInterface`. This class will enable our Apple device to be charged via a Samsung charger.

```java
public class Samsung2AppleChargingAdapter implements AppleChargerInterface{

    /** in adapter class we define an instance of class adapting**/
    SamsungCharger samsungCharger;

    public Samsung2AppleChargingAdapter(SamsungCharger newSamsungCharger){

        this.samsungCharger = newSamsungCharger;

    }

    @Override
    public void checkVoltage() {

        samsungCharger.testElectricity();

    }

    @Override
    public void bypassBatteryUsage() {

        samsungCharger.terminateBatteryPower();

    }

    @Override
    public void startCharging() {

        samsungCharger.initiateCharge();

    }
}

```

Let's create a `ChargingTester` class to check if when we plug in a Samsung charger on our iPhone if it will do nothing or it will 
think it is a Samsung device and start to charge...

```java
public class ChargingTester {

    public static void main(String[] args){

        /** getting a samsung charger... **/
        SamsungCharger samsungCharger = new SamsungCharger();

        /** putting a charging adapter on it and connecting our iPhone **/
        Samsung2AppleChargingAdapter chargingAdapter = new Samsung2AppleChargingAdapter(samsungCharger);

        /** Expecting it to think a Samsung plugged in and start charging... **/
        chargingAdapter.bypassBatteryUsage();
        chargingAdapter.checkVoltage();
        chargingAdapter.startCharging();

    }

}

```

The output of this execution will be as below.

```
Terminating battery power...
Plugged in Samsung Device, Checking device voltage in 1 second
Initiating charge...
```

Now, we successfully adapted a class to another via an adapter class.  
... and of course with the help of abstraction through an interface.

---

Next design pattern is [Façade Pattern.](/post/2019-03-16-a-pattern-a-day-facade/) 

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 