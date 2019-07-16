---
title: "A Pattern A Day #16 : Visitor"
author: "Berat Onur Ersen"
date: 2019-03-30
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The sixteenth and final pattern is **Visitor**.

Visitor pattern, as referred by its name is for performing visits to particular functionalities in classes. When we plan to perform an operation on similar kind of objects we can loosely-couple execution via this pattern.  
The basic thing done using the Visitor pattern is defining the operations in separate visitor classes, and every time we need a particular operation, we create a new visitor class.

Our example will be a museum analogy. Let's assume we are running a museum complex and expecting a `MuseumVisitor` to visit. 
In the museum, we have three different antiquities exhibition.

- `EgyptianAntiquities`
- `RomanAntiquities`
- `GreekAntiquities`

We need to have an `AntiquitiesMuseum` interface for each exhibition.

```java
public interface AntiquitiesMuseum {

    int accept(MuseumVisitor visitor);

}
```

Each exhibition will implement `AntiquitiesMuseum` to `accept(MuseumVisitor visitor)`. This accept() method will be taking `MuseumVisitor` as a parameter 
and calling it's `visit()` method.

```java
public class EgyptianAntiquities implements AntiquitiesMuseum {

    private int entranceFee;
    private String visitHours;

    public EgyptianAntiquities(int entranceFee, String visitHours) {
        this.entranceFee = entranceFee;
        this.visitHours = visitHours;
    }

    public int getEntranceFee() {
        return entranceFee;
    }

    public void setEntranceFee(int entranceFee) {
        this.entranceFee = entranceFee;
    }

    public String getVisitHours() {
        return visitHours;
    }

    public void setVisitHours(String visitHours) {
        this.visitHours = visitHours;
    }

    @Override
    public int accept(MuseumVisitor visitor) {
        return visitor.visit(this);
    }

}
```

For visitor classes, `MuseumVisitor` will be our interface. It will have different visiting methods for different antiquities in the museum.

```java
public interface MuseumVisitor {

    int visit(EgyptianAntiquities egyptianAntiquities);
    int visit(GreekAntiquities greekAntiquities);
    int visit(RomanAntiquities romanAntiquities);

}
```

An `Archeologist` class will implement `MuseumVisitor` and come to visit our complex.

```java
public class Archeologist implements MuseumVisitor {

    private int budget;

    public Archeologist(int budget) {
        System.out.println("An archeologist entered museum area with $" + budget + " budget...");
        this.budget = budget;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public int visit(EgyptianAntiquities egyptianAntiquities) {
        System.out.println(">> Visiting Egyptian Museum...");
        System.out.println("Entrance fee : $" + egyptianAntiquities.getEntranceFee());
        System.out.println("Visiting Hours : " + egyptianAntiquities.getVisitHours());
        budget = budget - egyptianAntiquities.getEntranceFee();
        System.out.println("$" + egyptianAntiquities.getEntranceFee() + " paid.");
        return budget;
    }

    @Override
    public int visit(GreekAntiquities greekAntiquities) {
        System.out.println(">> Visiting Greek Museum...");
        System.out.println("Entrance fee : $" + greekAntiquities.getEntranceFee());
        System.out.println("Visiting Hours : " + greekAntiquities.getVisitHours());
        budget = budget - greekAntiquities.getEntranceFee();
        System.out.println("$" + greekAntiquities.getEntranceFee() + " paid.");
        return budget;
    }

    @Override
    public int visit(RomanAntiquities romanAntiquities) {
        System.out.println(">> Visiting Roman Museum...");
        System.out.println("Entrance fee : $" + romanAntiquities.getEntranceFee());
        System.out.println("Visiting Hours : " + romanAntiquities.getVisitHours());
        budget = budget - romanAntiquities.getEntranceFee();
        System.out.println("$" + romanAntiquities.getEntranceFee() + " paid.");
        return budget;
    }
}
```

Now, we have an `Archeologist` as `MuseumVisitor` with `visit()` method to visit our museum complex, plus we have `accept()` method in each `AntiquitiesMuseum` to accept visitors and call their `visit()` method.  
Let's create a test class to send and `Archeologist` to these three exhibitions. 

```java
public class MuseumVisitation {

    public static void main(String[] args) {

        EgyptianAntiquities egyptianAntiquities = new EgyptianAntiquities(50, "09:00 - 18:00");
        RomanAntiquities romanAntiquities = new RomanAntiquities(75, "08:30 - 17:00");
        GreekAntiquities greekAntiquities = new GreekAntiquities(100, "08:00 - 19:00");

        Archeologist archeologist = new Archeologist(400);

        egyptianAntiquities.accept(archeologist);
        romanAntiquities.accept(archeologist);
        greekAntiquities.accept(archeologist);

        System.out.println("\n");
        System.out.println("Remaining budget is : $" + archeologist.getBudget());

    }
}
```

Executing this class will result in:

```
An archeologist entered museum area with $400 budget...
>> Visiting Egyptian Museum...
Entrance fee : $50
Visiting Hours : 09:00 - 18:00
$50 paid.
>> Visiting Roman Museum...
Entrance fee : $75
Visiting Hours : 08:30 - 17:00
$75 paid.
>> Visiting Greek Museum...
Entrance fee : $100
Visiting Hours : 08:00 - 19:00
$100 paid.


Remaining budget is: $175
```

When `Archeologist` entered our museum, she came with a $400 budget.  
Consecutively, she went to visit our three exhibitions.  
Each exhibition charged different `entranceFee`.  
At the end of the day, she was left with $175 in her pocket.

Further in this implementation, if we want to create a `Student` class to visit our museum;  
We will have a `Student` class implementing `MuseumVisitor` interface, having three `visit()` methods for our exhibitions. `Student` class will decide what it will do when it visits each exhibition. Our exhibitions implementing `AntiquitiesMuseum` will `accept()` and execute `visit()` methods of `Student` 
class.

This was a simple exemplification of the Visitor pattern. 
Hoping it was easy to understand and apply.

---

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 