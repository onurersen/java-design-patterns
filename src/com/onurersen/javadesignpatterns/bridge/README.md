---
title: "A Pattern A Day #10 : Bridge"
author: "Berat Onur Ersen"
date: 2019-03-22
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The tenth pattern is **Bridge**.

In bridge pattern, basically, we aim to separate an abstraction and its implementation. Then we develop **separate inheritance structures** for both the abstraction and the implementer. Decoupling abstraction and implementation helps us to change them without affecting each other.

For example;  
we can have both an old car and a new car; a car with a white color and a car with black color. How can we design a good structure of **decoupling** old/new car structure and black/white colored car structure? 

What if there comes a used car and we have 3 cars as **old/new/used**  ? Or what if we have **an additional color** for a car? We need to design such a system that adding new feature or structure won't be affecting everything top down. 

At this point, we use the Bridge pattern to create separate inheritance hierarchies.

Children of the abstraction are referred to as **refined abstractions**, and children of the implementer are **concrete implementers**.

This time our analogy will be created using a musical instrument: Guitar.
As you know the majority of guitars are produced using wood. There are many types of wood.  

`Wood` will be our **implementer for bridge pattern**. In other words, our base interface.
To craft a guitar, we first need to `chopWood(int x, int y)` with certain dimensions and then `paintWood(String color)` with a particular color:

```java
// Implementer for pattern
public interface Wood {

    void chopWood(int x, int y);

    void paintWood(String color);
}
```

Next, we will use two types of wood to craft a guitar:

- `Mahogany`
- `Rosewood`

These two `Wood` implementers will have **concrete implementation** by implementing `chopWood(int x, int y)` and `paintWood(String color)` methods from `Wood`:

```java
// Concrete implementation
public class Rosewood implements Wood {
    @Override
    public void chopWood(int x, int y) {
        System.out.println("Rosewood chopped with dimensions X : " + x + " and Y : " + y);

    }

    @Override
    public void paintWood(String color) {
        System.out.println("Rosewood guitar being painted in " + color);
    }
}
```

Then we need to have a `Guitar` abstract class **- a refined abstraction -** which holds a reference to implementer - `Wood`.
It will have desired methods from `Wood` implementation and also additional methods to specify a `Guitar`.

```java
// Refined abstraction
public abstract class Guitar {

    protected Wood wood;

    protected Guitar(Wood wood){
        this.wood = wood;
    }

    public abstract void chopWoodForGuitar();

    public abstract void paintWoodOfGuitar(String color);

    public abstract void leaveGuitarToDry();

}
```

Let's apply this `Guitar` abstraction which is holding a reference to `Wood` implementation to an `AcousticGuitar` class.

```java
public class AcousticGuitar extends Guitar {
    private int x, y;

    public AcousticGuitar(int x, int y, Wood wood) {
        super(wood);
        this.x = x;
        this.y = y;
    }

    @Override
    public void chopWoodForGuitar() {
        wood.chopWood(x, y);
    }

    @Override
    public void paintWoodOfGuitar(String color) {
        wood.paintWood(color);
    }

    @Override
    public void leaveGuitarToDry() {
        System.out.println("Leaving wood to dry...");
    }
}
```

Our `AcousticGuitar` is implementing `chopWoodForGuitar() -> wood.chopWood(x, y)` and  `paintWoodOfGuitar(String color) -> wood.paintWood(color)` to `Wood` implementation.  
And also it has `leaveGuitarToDry()` which is inherited from `Guitar` abstraction.

Now, we separated `Wood` implementation and `Guitar` abstraction by bridging them. 

- A new `Wood` type can be created?  
**It won't affect** `Guitar` **abstraction.**

- A new method inside `Guitar` abstraction can be created?  
**It won't affect** `Wood` **implementation.**

Let's create a class to test what we've done and name it `AcousticGuitarProduction`. This class will create **a Brown Mahogany Guitar** and **a Black Rosewood Guitar**.

```java
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
```

The output of the execution will be:

```
Mahogany chopped with dimensions X : 100 and Y : 140
Mahogany guitar being painted in brown
Leaving wood to dry...


Rosewood chopped with dimensions X : 200 and Y : 120
Rosewood guitar being painted in black
Leaving wood to dry...
```

This was a simple demonstration of the Bridge pattern.  
Inheritance, abstraction, and abstraction are very important concepts for Object-Oriented Programming.  

I hope this post was understandable to get an idea of the main characteristics of the pattern.

---

Next design pattern is [Composite Pattern.](/post/2019-03-25-a-pattern-a-day-composite/)

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 