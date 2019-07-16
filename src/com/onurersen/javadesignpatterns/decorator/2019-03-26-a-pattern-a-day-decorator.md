---
title: "A Pattern A Day #12 : Decorator"
author: "Berat Onur Ersen"
date: 2019-03-26
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The twelfth pattern is **Decorator**.

Decorator pattern, as its name refers, is used for decoration. We use this pattern for decorating our classes with additional features and functionalities.
In other words, we craft our code around the decorator pattern to attach additional responsibilities to them.

Let's exemplify this pattern via an analogy.

Assume that we are getting ready for a photography course. We'll certainly need a `SLRCamera`, a `WideAngleLens` and a `Tripod`.
`WideAngleLens` and `Tripod` are our accessories - our **decorations**.

First, we'll have an interface for our `Camera`. It will have just a `prepareCamera()` method to return camera specification.

```java
public interface Camera {

    String prepareCamera();

}
```

Its implementation will be a `SLRCamera`.

```java
public class SLRCamera implements Camera {

    @Override
    public String prepareCamera() {
        return "preparing Canon EOS 600D";
    }
}
```

Next, we want to enhance our camera with additional accessories - decorations. To achieve that, we need a `CameraDecorator` base class.  
This base class will implement `Camera` interface and also have a`Camera` as an instance variable. Implementing `Camera` will force this class to have 
`prepareCamera()` method. `CameraDecorator` class will use this method as a bridge to the class being decorated by returning as `return camera.prepareCamera()`.


```java
public class CameraDecorator implements Camera {

    protected Camera camera;

    public CameraDecorator(Camera inputCamera){
        this.camera = inputCamera;
    }

    @Override
    public String prepareCamera() {
        return camera.prepareCamera();
    }
}
```

Now, we want to put a `WideAngleLens` on our `SLRCamera`. Then we create a `WideAngleLens` extending `CameraDecorator`.
This decoration class will have a constructor to set an input camera as its superclass' camera and `assembleWideAngleLens(String inputCamera)` to assemble a lens on that camera.

```java
public class WideAngleLens extends CameraDecorator {

    public WideAngleLens(Camera camera) {
        super(camera);
    }

    public String prepareCamera(){
        return assembleWideAngleLens(camera.prepareCamera());
    }

    public String assembleWideAngleLens(String inputCamera){
        return inputCamera + " with 18-200 wide angle lens";
    }
}
```

We also need a `Tripod`. `Tripod` decorator will have the same structure with `WideAngleLens` and `assembleTripod(String inputCamera)` method.

```java
public class Tripod extends CameraDecorator {

    public Tripod(Camera inputCamera) {
        super(inputCamera);
    }

    public String prepareCamera() {
        return assembleTripod(camera.prepareCamera());
    }

    public String assembleTripod(String inputCamera) {
        return inputCamera + " and Manfrotto tripod attached";
    }

}
```

We are ready to go to `PhotographyCourse`. Let's create our test class and see what our camera will look like.

```java
public class PhotographyCourse {

    public static void main(String[] args) {

        Camera camera = new Tripod(new WideAngleLens(new SLRCamera()));
        System.out.println(camera.prepareCamera());

    }

}
```

The output of this execution will be:

```
preparing Canon EOS 600D with 18-200 wide angle lens and Manfrotto tripod attached
```

In this example, we used the Decorator pattern which is built mainly using inheritance feature of Object-Oriented Programming.

---

Next design pattern is [Observer Pattern.](/post/2019-03-27-a-pattern-a-day-observer/)

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 