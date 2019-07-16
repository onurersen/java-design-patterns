---
title: "A Pattern A Day #6 : Strategy"
author: "Berat Onur Ersen"
date: 2019-03-18
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The sixth pattern is **Strategy**.

Strategy pattern is good when variations of an algorithm are considered to be used.  

Building blocks of the strategy pattern are:  

- Strategy interface
- Variations / Implementations of strategy interface - concrete classes implementing strategy
- Context class (which can be deemed as Service class also)

Let's assume we are running a Pizza Restaurant and we have 3 types of Pizza as:

- Margarita
- Sicilian
- Calzone

We want to implement the Strategy pattern for pizza preparation process.
 
**First, we create a Strategy interface for preparing pizza ingredients.**

That interface has `prepareIngredients()` method which takes the heat as a parameter for heat level of oven for particular pizza type.

```java
// Our strategy interface
public interface PizzaStrategy {

     void prepareIngredients(int ovenHeatDegree);

}
```

**Second, we create variation classes to implement strategy interface.**

Each of `Margarita`, `Sicilian` and `Calzone` will inherit this strategy and have their own `prepareIngredients()` method implemented.

```java
// A variation class implementing PizzaStrategy
public class Margarita implements PizzaStrategy{

    @Override
    public void prepareIngredients(int ovenHeatDegree) {
        System.out.println("---MARGARITA PREPARATION START---");
        System.out.println("Oven is hot. It is " + ovenHeatDegree + " °C");
        System.out.println("1/2 recipe homemade pizza dough.");
        System.out.println("1 Tablespoon olive oil.");
        System.out.println("2 cloves roasted garlic, finely chopped.");
        System.out.println("1/4 cup your favorite pizza or tomato sauce.");
        System.out.println("8 ounces mozzarella cheese, sliced into 1/2 inch thick pieces.");
        System.out.println("2 plum tomatoes, sliced (or any tomato you like)");
        System.out.println("handful of fresh basil.");
        System.out.println("fresh ground pepper, to taste.");
        System.out.println("---MARGARITA PREPARATION END---");
    }

} 
```

**Third, we create a context/service class which takes particular strategy implementer class as input and let that implementer execute the process.**

```java
// Our context/service class
public class PizzaService {

    private PizzaStrategy pizzaStrategy;

    // construct context/service class with getting strategy implementer as input
    public PizzaService(PizzaStrategy pizzaStrategy) {
        this.pizzaStrategy = pizzaStrategy;
    }

    // let that implementer execute process
    public void executePreparation(int heat){
        pizzaStrategy.prepareIngredients(heat);
    }

}
```

As a result, we delegated pizza preparation to different pizza types but abstracting pizza preparation via a strategy interface.  
In other words, our strategy pattern lets each pizza type execute own preparation process using a context/service class.

Finally, to test our code, let's create a `PizzaOrder` class generating random heat value and random pizza type. You see PizzaService class here being used to execute pizza preparation through `executePreparation(heat)` method.

```java
import java.util.Random;

public class PizzaOrder {

    public static void main(String[] args){

        // create random heat value and pizza type to provide as input
        // for context/service class
        Random random = new Random();
        int heat = random.nextInt(50) + 200;
        int pizzaType = random.nextInt(2);

        PizzaStrategy strategy = null;

        switch (pizzaType){
            case 0:
                strategy = new Scilian();
                break;
            case 1:
                strategy = new Margarita();
                break;
            case 2:
                strategy = new Calzone();
                break;
        }

        PizzaService pizzaService = new PizzaService(strategy);
        pizzaService.executePreparation(heat);

    }

}
```

One advantage of using strategy pattern here is; neither `PizzaService` nor `PizzaOrder` knows anything about pizza preparation and when we execute `PizzaOrder`, we have the following output:

```
---SICILIAN PREPARATION START---
The oven is hot. It is 247 °C
1 1/2 cups water (warm)
1 1/2 tablespoons yeast (dry)
4 cups bread flour (or all-purpose flour)
1 1/2 teaspoons salt.
3 tablespoons olive oil (divided)
3-4 tablespoons tomato sauce.
1 cup shredded mozzarella cheese.
4 to 6 fresh basil leaves.
---SICILIAN PREPARATION END---
```

---

After this example, to sum up the Strategy pattern;

During implementation in a project, a class can have **many behaviors**, and these many behaviors can appear as **multiple conditional statements**. Instead of implementing many conditions with if/else or switch, we **can move conditional executions into particular strategy classes**. There we have a good implementation of the Strategy pattern.

Another point;

Interface usage and implementation of the **Strategy pattern** have similarities with **Factory pattern**, but their purposes are different actually.

The main difference is;

while factory pattern is used for **creating specific objects for different purposes at runtime** like a factory, strategy pattern is used for **selecting particular algorithm to execute at runtime**.

---

Next design pattern is State Pattern.

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 