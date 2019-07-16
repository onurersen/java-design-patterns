---
title: "A Pattern A Day #3 : Template"
author: "Berat Onur Ersen"
date: 2019-03-14
draft: no
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The third pattern is **Template**.

In the template pattern, the basic idea is letting subclasses to implement particular functionality. What do we mean by this?

As you'll remember in "factory" pattern, the factory class was in charge of managing objects, doing hard work. 

In template pattern we tell subclasses:

-- "Hey buddy, this is the name of functionality, get it and you decide how to implement, OK?"

and subclass responds:

-- "OK boss, but wait... am I allowed to design execution order?"

and then template pattern says:

-- "Not always, I will also consider how you will be calling methods, in what particular order things will execute is on me."

---

Let's exemplify things within an analogy.  

Let's assume while one person is preparing "Roasted Beef" and the other will be preparing "Roasted Chicken" for the dinner.

For preparing something for dinner, regardless of the change in food type, there is the same procedure flow, always. That is: 

1. Prepare ingredients
1. Bake in the oven
1. Wash the dishes

We create both classes as `RoastedBeef.java` and `RoastedChicken.java`.

```java
public class RoastedBeef {

    public void prepareIngredients(){
        System.out.println("Preparing ingredients for beef...");
    }

    public void bakeInOven(){
        System.out.println("Bake the beef in oven @ 240°C ...");
    }

    public void washTheDishes(){
        System.out.println("Washing the dishes...");
    }
}
```

Then we create a `DinnerTest.java` class to test dinner execution.

```java
public class DinnerTest {

    public static void main(String[] args){

        /** Cooking RoastedBeef **/
        RoastedBeef roastedBeef = new RoastedBeef();
        roastedBeef.prepareIngredients();
        roastedBeef.bakeInOven();
        roastedBeef.washTheDishes();

        /** Cooking RoastedBeef **/
        RoastedChicken roastedChicken = new RoastedChicken();
        roastedChicken.prepareIngredients();
        roastedChicken.bakeInOven();
        roastedChicken.washTheDishes();

    }

}
```

Now, we have `prepareIngredients()`, `bakeInOven()`, `washTheDishes()` method but we cannot be sure if an implementer washes the dishes before baking the meal, or baking the meal before preparing ingredients... 
At this point, execution order goes out of control. We need to do something to make each dinner preparation follow the same procedure.

To solve this issue, we can gather separate `prepareIngredients()`, `bakeInOven()`, `washTheDishes()` methods and implement `roastMeal()` method.  

```java
public class RoastedBeef {

    public void roastMeal(){
        prepareIngredients();
        bakeInOven();
        washTheDishes();
    }

    public void prepareIngredients(){
        System.out.println("Preparing ingredients for beef...");
    }

    public void bakeInOven(){
        System.out.println("Bake the beef in oven...");
    }

    public void washTheDishes(){
        System.out.println("Washing the dishes...");
    }
}
```

Now implementers do not need to think about how to execute, they just use `roastMeal()` method.

```java
public class DinnerTest {

    public static void main(String[] args){

        /** Cooking RoastedBeef **/
        RoastedBeef roastedBeef = new RoastedBeef();
        roastedBeef.roastMeal();

        /** Cooking RoastedBeef **/
        RoastedChicken roastedChicken = new RoastedChicken();
        roastedChicken.roastMeal();

    }

}
```

But wait... There is a glitch here, everyone preparing dinner needs to copy and paste this `roastMeal()` method, then implement?  

**Of course, No.**

To overcome this issue, we use object-oriented programming basics. For efficiency, common methods can be defined in an abstract superclass, and those common methods will be shared by subclasses those extend our superclass.

We create our super class: `DinnerProcedure.java`. Because washing the dishes is the same functionality for both meals,
we implement `washTheDishes()` method inside this superclass. Subclasses can call it directly from superclass without implementing.
However, `prepareIngredients()` and `bakeInOven()` methods differ in each subclass. 

They need to implement it separately. The only thing needs to be done is defining them in `DinnerProcedure.java` superclass, so whenever a developer needs to create a new meal they will know 
that `prepareIngredients() and `bakeInOven()` methods are to be defined in their superclass and left to be implemented in their subclass.

```java
public abstract class DinnerProcedure {

    /** superclass defines order of execution**/
    public void roastMeal(){
        prepareIngredients();
        bakeInOven();
        washTheDishes();
    }

    /** abstract methods left for subclasses to implement **/
    public abstract void prepareIngredients();
    public abstract void bakeInOven();

    /** common method for all subclasses, superclass handles it for all **/
    public void washTheDishes(){
        System.out.println("Washing the dishes...");
    }

}
```

Our subclasses extend DinnerProcedure and implement `prepareIngredients()` and `bakeInOven()` methods by overriding.

```java
public class RoastedChicken extends DinnerProcedure{

    @Override
    public void prepareIngredients() {
        System.out.println("Preparing ingredients for chicken...");
    }

    @Override
    public void bakeInOven() {
        System.out.println("Bake the chicken in oven @ 200°C ...");
    }
}
```

In DinnerTest class, when ever a new meal is being prepared, only `roastMeal()` method needs to be called. Subfunctionalities of `prepareIngredients()` and `bakeInOven()` methods are implemented separately via each subclass. Last but not least; `roastMeal()` execution order is handled by our abstract superclass 
`DinnerProcedure`.

```java
RoastedBeef roastedBeef = new RoastedBeef();
roastedBeef.roastMeal();
```

The easiest way to use the Template pattern is :

-- We first identify the differences in the existing code and then separate the differences into new operations. 

-- Next, we replace the differing code with a template method that calls one of these new operations.

---

Next design pattern is [Adapter Pattern.](/post/2019-03-15-a-pattern-a-day-adapter/) 

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 