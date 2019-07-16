---
title: "A Pattern A Day #11 : Composite"
author: "Berat Onur Ersen"
date: 2019-03-25
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The eleventh pattern is **Composite**.

Composite pattern is applicable when the core model can be structured as a **tree**. In other words, this pattern is generally used to craft a hierarchical code structure.

All elements of tree structure will share a **common component interface**. An item that has children items will be a node and an item without children will be a leaf. Nodes will be deemed as 
composites because they will have common implementations with their children.

Imagine we are looking for a particular item. If we start to search for it among different implementations in nodes and leaves of the tree, we would be grappling while traversing many details on the way. 
At this point, the composite pattern help us to create a common structure through inheritance - letting a client deal with only a certain type of composite structure.

Our analogy will be creating a hierarchical structure with military rankings.
All military rankings in this analogy will implement `Soldier` interface which has some definitive methods.

```java
// component interface
public interface Soldier {

    void add(Soldier soldier);
    void remove(Soldier soldier);
    Soldier getChild(int i);
    void setName(String name);
    String getName();
    void setExperienceYears(int years);
    int getExperienceYears();
    void report();

}
```

There will be three rankings from lower to higher as :

- `Corporal`
- `Sarge`
- `Captain`

`Soldier` interface will have 
`void add(Soldier soldier)` and `void remove(Soldier soldier)` methods to manage hierarchy when the implementation is a node.

`Corporal`, as lowest ranking officer, will have `report()` method to print the name of the soldier and its years of experience.

```java
public class Corporal implements Soldier {

    private String name;
    private int experienceYears;

    @Override
    public void add(Soldier soldier) {
        throw new UnsupportedOperationException("Soldier does not support operation.");
    }

    @Override
    public void remove(Soldier soldier) {
        throw new UnsupportedOperationException("Soldier does not support operation.");
    }

    @Override
    public Soldier getChild(int i) {
        throw new UnsupportedOperationException("Soldier does not support operation.");
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setExperienceYears(int years) {
        this.experienceYears = years;
    }

    @Override
    public int getExperienceYears() {
        return experienceYears;
    }

    @Override
    public void report() {
        System.out.println("---Corporal Reporting---");
        System.out.println("Name : " + getName());
        System.out.println("Experience(Years) : " + getExperienceYears());
        System.out.println("---");
    }
}
```

On the other side, `Sarge` and `Captain` will have the same implementation from `Soldier` but 
`Sarge` will have a team of `Corporal` and `Captain` will have a team of `Sarge`. That will make their report method change a little bit to enable reporting hierarchy.

```java
import java.util.ArrayList;
import java.util.List;

public class Sarge implements Soldier {

    private List<Soldier> team = new ArrayList<>();
    private String name;
    private int experienceYears;

    @Override
    public void add(Soldier soldier) {

        team.add(soldier);
    }

    @Override
    public void remove(Soldier soldier) {

        team.remove(soldier);
    }

    @Override
    public Soldier getChild(int i) {
        return null;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setExperienceYears(int years) {
        this.experienceYears = years;
    }

    @Override
    public int getExperienceYears() {
        return experienceYears;
    }

    @Override
    public void report() {
        System.out.println("---Sarge Reporting---");
        System.out.println("Name : " + getName());
        System.out.println("Experience(Years) : " + getExperienceYears());
        System.out.println("---");
        for (Soldier soldier : team) {
            soldier.report();
        }
    }
}
```

Now, let's create a tree structure using soldiers those we specify as `Corporal`,`Sarge` and `Captain`.
Our testing class name will be `Military`.
In `Military` class we create a couple of `Corporal` and add to a `Sarge`.  
Next, we create a `Captain` and add `Sarge` to add that `Captain`.

```java
public class Military {

        public static void main(String[] args){

            Soldier corporal1 = new Corporal();
            corporal1.setExperienceYears(3);
            corporal1.setName("Arnold Warner");

            Soldier corporal2 = new Corporal();
            corporal2.setExperienceYears(2);
            corporal2.setName("Henry Jordan");

            Soldier corporal3 = new Corporal();
            corporal3.setExperienceYears(4);
            corporal3.setName("Steven Curry");

            Soldier sarge1 = new Sarge();
            sarge1.setExperienceYears(5);
            sarge1.setName("Randall Kennedy");

            Soldier sarge2 = new Sarge();
            sarge2.setExperienceYears(8);
            sarge2.setName("Tony McAlpine");

            Soldier captain = new Captain();
            captain.setExperienceYears(22);
            captain.setName("John Mayfield");

            // add corporal1 and corporal2 as leaves to sarge1 node
            sarge1.add(corporal1);
            sarge1.add(corporal2);

            // add corporal3 as leaf to sarge2
            sarge2.add(corporal3);

            // add sarge1 and sarge2 as leaves to captain
            captain.add(sarge1);
            captain.add(sarge2);

            // print captain report
            captain.report();

        }

}
```

When we execute this hierarchy (tree) and print `Captain` report, it will print traversing all elements starting from the `Captain`. It won't distinguish between a military ranking.

```
---Captain Reporting---
Name : John Mayfield
Experience(Years) : 22
---
---Sarge Reporting---
Name : Randall Kennedy
Experience(Years) : 5
---
---Corporal Reporting---
Name : Arnold Warner
Experience(Years) : 3
---
---Corporal Reporting---
Name : Henry Jordan
Experience(Years) : 2
---
---Sarge Reporting---
Name : Tony McAlpine
Experience(Years) : 8
---
---Corporal Reporting---
Name : Steven Curry
Experience(Years) : 4
---
```

In this example, using the Composite pattern, we let clients treat individual objects and compositions of those objects (as `Corporal`,`Sarge`,`Captain`) uniformly.  
Our client did not need to think about leaf and composite differentiation.

---

Next design pattern is Decorator Pattern.

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 