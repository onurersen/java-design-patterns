---
title: "A Pattern A Day #5 : Facade"
author: "Berat Onur Ersen"
date: 2019-03-16
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The fifth pattern is **Façade**.

In complex architectures, clients expect to interact with multiple subsystems. Access to those subsystems can be very complicated and tortuous. Façade pattern is used in such complex systems to create a simple and unified interface to access subsystems. 

Accessors, in other words, clients can interact just with our façade to use tricky functionalities. Façade pattern 
helps us to create tightly coupled connections between systems to minimize complexity through usability.

Let's assume that we are implementing a face-unlock feature for our new mobile phone.
Face-unlock feature requires the use of multiple classes. Moreover, some of those classes implementing multiple interfaces.

In our scenario, face-unlock uses two classes for unlocking, they are `LaserUtility` and `FaceMatcherUtility`.  

`LaserUtility` class implements methods from `ILaserBeam` interface. 

They are;

- `chargeLaser()` to charge laser before shooting 
- `beamLaser(int level)` to shoot laser on face that is to be recognized.

```java
public class LaserUtility implements ILaserBeam{

    @Override
    public void chargeLaser() {
        System.out.println("Charging laser, getting device ready...");
    }

    @Override
    public void beamLaser(int level) {
        System.out.println("Laser beam executed at level : " + level);
    }
}
```

and `FaceMatcherUtility` class implements following methods from `IFaceDataAccess` and `IFaceClassifier` interfaces.

- `cacheFaceData()` to initialize all face data stored before
- `setFaceDimensions(int x, int y)` to set face dimensions obtained by laser beaming
- `classifyInputFace()` to classify facial data 
- `decideLockUnlock()` to decide lock or unlock device

```java
public class FaceMatcherUtility implements IFaceDataAccess,IFaceClassifier{
    @Override
    public void setFaceDimensions(int x, int y) {
        System.out.println("Setting Face Dimensions - X : " + x + " Y : " + y);
    }

    @Override
    public void classifyInputFace() {
        System.out.println("Input face classified...");
    }

    @Override
    public void cacheFaceData() {
        System.out.println("Caching facedata from database");
    }

    @Override
    public int decideLockUnlock() {
        System.out.println("Deciding lock or unlock using facial data results...");
        return 1;
    }
}
```

We gather this complicated process in `FaceDetectFacade` class and implement this `FaceDetectFacade` class using **Singleton** pattern that we explained before. Let's call it **Pattern in a pattern** :)

```java
public class FaceDetectFacade {

    private static FaceDetectFacade singletonFacade;

    private FaceDetectFacade(){}

    public void faceDetect(){

        LaserUtility laser = new LaserUtility();
        FaceMatcherUtility faceMatcher = new FaceMatcherUtility();

        /** initialize laser and data processor **/
        laser.chargeLaser();
        faceMatcher.cacheFaceData();

        /** start face recognition process **/
        laser.beamLaser(10);
        faceMatcher.setFaceDimensions(15,21);
        faceMatcher.classifyInputFace();
        faceMatcher.decideLockUnlock();

    }

    public static FaceDetectFacade getInstance(){
        if(singletonFacade == null)
            singletonFacade = new FaceDetectFacade();
        return singletonFacade;
    }

}
```

To test our façade, we create a very simple `FaceUnlockTest` class.

```java
public class FaceUnlockTest {

    public static void main(String[] args){

        FaceDetectFacade facade = FaceDetectFacade.getInstance();
        facade.faceDetect();

    }

}
```

A class that requires to use face-unlock feature won't use any functionality than getting a `FaceDetectFacade` instance and executing `faceDetect()`.  
Output of execution will be:

```
Charging laser, getting device ready...
Laser beam executed at level : 10
Caching facedata from database
Selecting face data from face database and returning...
Setting Face Dimensions - X : 15 Y : 21
Input face classified...
```

This was the simplest implementation example of the Façade pattern.

One important point about this pattern is; 

developers should take really good care of complicated flow behind façade implementation. They have the advantage of single point entry to 
functionality but as long as **everything behind façade implementation is clean and working perfectly**.

---

Next design pattern is [Strategy Pattern.](/post/2019-03-18-a-pattern-a-day-strategy/) 

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 