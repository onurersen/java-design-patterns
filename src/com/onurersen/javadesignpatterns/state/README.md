---
title: "A Pattern A Day #7 : State"
author: "Berat Onur Ersen"
date: 2019-03-19
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The seventh pattern is **State**.

The basic idea of the State pattern is defining state specific behaviors in separate classes. Thus, new states/new behaviors can be added without changing the main class.  
Once there happens a change in values of class attributes, we assume class state changes.
Another principle;    
Context object is first created with an initial-state object and it is set as the current state of the object for the context.

Let me show this pattern via a typical example :)

We will implement a credit card payment process.
Let's say there are 2 functionalities to be executed for every payment state.  

```java
public interface PaymentState {

    int handlePayment(int currentAmount, int payment);

    void sendReceipt();

}
```

We have `handlePayment(int currentAmount,int payment)` and `sendReceipt()` methods as contracts in `PaymentState` interface.

Here `handlePayment(int currentAmount,int payment)` method takes current amount in account and payment amount as input parameters.  
`sendReceipt()` is basically sending payment receipt.

Next, we have 3 payment types implementing `PaymentState` interface:

- `NoPayment`
- `PartialPayment`
- `FullPayment`

Content of `FullPayment` looks like this :

```java
public class PartialPayment implements PaymentState {

    @Override
    public int handlePayment(int currentAmount, int payment) {
        System.out.println("Partial $" + payment + " payment was done. $" + (currentAmount - payment) + " left.");
        return (currentAmount - payment);
    }

    @Override
    public void sendReceipt() {
        System.out.println("Receipt for partial payment sent...");
    }

}
```

So we have an implementation in our payment classes separately ;)

Now, we need a context class which will handle state transitions and executes related methods in each state change.  
Let's call this class `CreditCardService`. 

```java
public class CreditCardService {

    private PaymentState currentPaymentState;

    int amount = 0;

    // constructing CreditCardService with initial amount
    public CreditCardService(int amount){
        currentPaymentState = new NoPayment();
        this.amount = amount;
    }

    public void requestReceipt(){
        currentPaymentState.sendReceipt();
    }

    public void partialPayment(int partialAmount){
        currentPaymentState = new PartialPayment();
        amount = currentPaymentState.handlePayment(amount,partialAmount);
        System.out.println("Partial amount paid. Remaining amount is $" + amount + ".");
    }

    public void fullPayment(){
        currentPaymentState = new FullPayment();
        currentPaymentState.handlePayment(amount,amount);
        System.out.println("Full amount paid. No debt remains.");
    }

    public void noPayment(){
        currentPaymentState = new NoPayment();
        currentPaymentState.handlePayment(amount,amount);
    }

}
```

`CreditCardService` will have the current/initial state as `NoPayment` and it will have a constructor taking `int amount` as initial debt for credit card.  
This amount will change when a state change happens - when payment is made.  
`CreditCardService` will also have `requestReceipt()` method to send payment receipt.  

To test, we create `CreditCardPayment` class:

```java
public class CreditCardPayment {

    public static void main(String[] args){

        CreditCardService service = new CreditCardService(1000);
        // will request receipt but no payment done current status is no payment
        service.requestReceipt();

        System.out.println("\n");

        service.partialPayment(65);
        service.requestReceipt();

        System.out.println("\n");

        // again will send receipt for partial payment
        // because current state is partial payment
        service.requestReceipt();

        service.fullPayment();
        service.requestReceipt();

        // again will send receipt for full payment
        // because current state is full payment
        service.requestReceipt();

        System.out.println("\n");
    }
}
```

Execution output will be as below:

```
No payment done.No receipt to be sent...

Partial $65 payment was done. $935 left.
Partial amount paid. Remaining amount is $935.
Receipt for partial payment sent...

Receipt for partial payment sent...

Full $935 payment was done. No more amount left.
Full amount paid. No debt remains.
```

**First,** we tried to send a receipt without making any payment and it returned **No payment done.No receipt to be sent...**

**Second,** we tried to make a partial payment of $65 and it deducted and returned $935.  
We requested receipt. Receipt sent. We requested receipt again and it sent the same receipt with $65 payment because our state is still partial payment. 
 
**Third,** we tried full payment and it returned **Full $935 payment was done. No more amount left.**. We requested receipt and it sent full payment receipt.

---

We demonstrated State pattern with a quite simple example.  

In this example, the State pattern helped us avoid many if-else/switch statements. You can add as many states as you can.

Whenever you need a state-change kind of implementation you can choose this pattern to separate each state functionality, making them having 
independent execution behaviors.

---

Next design pattern is [Proxy Pattern.](/post/2019-03-20-a-pattern-a-day-proxy/) 

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 