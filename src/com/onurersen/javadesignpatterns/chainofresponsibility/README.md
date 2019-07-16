---
title: "A Pattern A Day #9 : Chain of Responsibility"
author: "Berat Onur Ersen"
date: 2019-03-21
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The ninth pattern is **Chain of Responsibility**.

Chain of Responsibility sometimes known as "COR" pattern, is all about chaining execution between client and handler. Request condition(s) or parameter(s) are 
effective in this chaining process. 

Chain of Responsibility pattern provides **loosely-coupling** between client and executor. Each handler in chain decides whether it will be handling the request or pass it to the next handler in the chain. 

**try-catch blocks** are an example of COR pattern. Each exception type tries to catch, if cannot it goes to next exception type, in the end general "Exception" is caught - default handling is done.

```java
try {
    ...     
} catch (IllegalArgumentException e) {
    handle();
} catch (SecurityException e) {
    handle();
} catch (IllegalAccessException e) {
    handle();
} catch (NoSuchFieldException e) {
    handle();
} catch (Exception e) {
    defaultHandle();
}
```

Now, let's create an expense approval system using the Chain of Responsibility pattern.
In our system, there will be 3 types of approvers for a particular expense.

- `LineManager`
- `Director`
- `RegionHead`

First, we need to have an `Expense` class to submit. This class will hold `expenseAmount` which 
plays a critical role in the expense approval flow.

```java
public class Expense implements IExpense {

    private int expenseAmount;

    @Override
    public int getExpenseAmount() {
        return expenseAmount;
    }

    @Override
    public void setExpenseAmount(int expenseAmount) {
        this.expenseAmount = expenseAmount;
    }
}
```

Then, we need to have an `Approval` class. This class will keep a note about expense approval.

```java
public class Approval implements IApproval {

    String approvalNote;

    @Override
    public void setApprovalNote(String note) {
        this.approvalNote = note;
    }

    @Override
    public String getApprovalNote() {
        return approvalNote;
    }
}
```

Next, we create an `IApproverChain` interface which will be implemented by approvers.  
This interface will keep upper approval limit and lower approval limit.  
Each expense going through the approval chain will be evaluated using these values.  
If expense is well between any approval limit by current approval it will be approved.  
Otherwise, it will be passed to the next approver.

```java
public interface IApproverChain {

    int getApproverUpperLimit();

    int getApproverLowerLimit();

    Approval processExpense(Expense submittedExpense);

    void nextApprover(IApproverChain nextInChain);

}
```

Let `LineManager`, `Director`, `RegionHead` to implement this interface.

```java
public class LineManager implements IApproverChain {

    private IApproverChain nextInChain;
    private static int APPROVER_UPPER_LIMIT = 500;
    private static int APPROVER_LOWER_LIMIT = 0;


    @Override
    public int getApproverUpperLimit() {
        return APPROVER_UPPER_LIMIT;
    }

    @Override
    public int getApproverLowerLimit() {
        return APPROVER_LOWER_LIMIT;
    }

    @Override
    public Approval processExpense(Expense submittedExpense) {

        if (submittedExpense.getExpenseAmount() < getApproverUpperLimit()
                && submittedExpense.getExpenseAmount() > getApproverLowerLimit()) {
            Approval approval = new Approval();
            approval.setApprovalNote("### Approved by LineManager ###");
            return approval;
        } else {
            return nextInChain.processExpense(submittedExpense);
        }
    }

    @Override
    public void nextApprover(IApproverChain nextInChain) {
        this.nextInChain = nextInChain;
    }
}
```

Last, we create an `ExpenseSubmission` class to test. 

This class will create a chain by constructing each approver class in runtime.  
Then it will generate 3 `Expense` classes having 3 random expense amounts. These 3 classes will be sent to approval one by one. `ExpenseSubmission` class will also print out who handled our expense in the chain.

```java
import java.util.Random;

public class ExpenseSubmission {

    public static void main(String[] args){

        IApproverChain lineManager = new LineManager();
        IApproverChain director = new Director();
        IApproverChain regionHead = new RegionHead();

        // chaining approvers one to another regarding approve level
        lineManager.nextApprover(director);
        director.nextApprover(regionHead);

        Random random = new Random();

        Expense expense = new Expense();
        expense.setExpenseAmount(random.nextInt(2000));
        System.out.println("Submitting $" + expense.getExpenseAmount() + " expense to Line Manager");
        System.out.println(lineManager.processExpense(expense).getApprovalNote());

        System.out.println("\n");

        expense.setExpenseAmount(random.nextInt(2000));
        System.out.println("Submitting $" + expense.getExpenseAmount() + " expense to Line Manager");
        System.out.println(lineManager.processExpense(expense).getApprovalNote());

        System.out.println("\n");

        expense.setExpenseAmount(random.nextInt(2000));
        System.out.println("Submitting $" + expense.getExpenseAmount() + " expense to Line Manager");
        System.out.println(lineManager.processExpense(expense).getApprovalNote());

    }

}
```

The output of the execution will be:

```
Submitting $851 expense to Line Manager
### Approved by Director ###


Submitting $1808 expense to Line Manager
degil
### Approved by RegionHead ###


Submitting $790 expense to Line Manager
### Approved by Director ###
```

Each expense went through the chain to be approved by the appropriate approver.

This was a simple demonstration of the Chain of Responsibility pattern. Whenever the handler of an object is required to be assigned dynamically like in this example,  Chain of Responsibility pattern can be a good alternative.

---

Next design pattern is Bridge Pattern.

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 