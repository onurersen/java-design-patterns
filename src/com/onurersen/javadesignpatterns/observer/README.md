---
title: "A Pattern A Day #13 : Observer"
author: "Berat Onur Ersen"
date: 2019-03-27
draft: false
---

**"Once Is Chance, Twice is Coincidence, Third Time Is A Pattern."**

In everything that occurs in a repeated manner, there is a pattern. 
In software design, there also exist certain patterns. To be good at what we are doing as software engineers, it is better to 
 understand which design patterns go best in which circumstances, and recognize when we see it.

With that in mind, I started to write down each and every pattern I know, learn and experiment. There will *16 patterns* in total.

---

The thirteenth pattern is **Observer**.

Observer pattern is generally used for **publisher-subscriber** kind of interaction between structures. There is a **subject** class to send notification about a state change and an **observer** class to register for **notifications** from that subject class.  

Objects use a particular `Subject` interface to **register as observers** and **Subjects* have an **update interface** to notify their observers when a change happens.

This time our scenario is around a `YogaCenter` which has existing members and potential members. This `YogaCenter` regularly creates Yoga sessions for its members. These sessions are sometimes for existing members and sometimes for potential members.
All members subscribe to announcements by `YogaCenter`.

We will build a member-yoga center interaction using the Observer pattern.

Let's create a `Subject` interface to be implemented by `Yoga` : 

```java
public interface Subject {

    enum MEMBER_TYPE {
        EXISTING,
        POTENTIAL
    }

    void attachObserver(Observer observer);
    void detachObserver(Observer observer);
    void sendNotificationToObservers(MEMBER_TYPE type);
    Object getUpdate(Subject.MEMBER_TYPE type);

}
```

This interface pushes `Yoga` to have `void attachObserver(Observer observer)` to attach observers and `detachObserver(Observer observer)` to remove observers.  
For informing observers and for observers to check new updates, it has `sendNotificationToObservers(MEMBER_TYPE type)` and for observers to retrieve updates `Object getUpdate(Subject.MEMBER_TYPE type)`.

```java
import java.util.ArrayList;
import java.util.List;

//Concrete Subject
public class Yoga implements Subject {

    List<Observer> observers;
    private boolean isNewSessionAvailable;

    public Yoga() {
        isNewSessionAvailable = false;
        observers = new ArrayList<>();
    }

    @Override
    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void sendNotificationToObservers(MEMBER_TYPE type) {
        if (isNewSessionAvailable) {
            for (Observer o: observers) {
                if (o.getMemberType() == type) {
                    o.update(type);
                    if (type == MEMBER_TYPE.POTENTIAL)
                        System.out.println("A notification was sent to Potential members for an upcoming session.");
                    else if (type == MEMBER_TYPE.EXISTING)
                        System.out.println("A notification was sent to Existing members for an upcoming session.");
                }
            }
        }
    }

    @Override
    public Object getUpdate(Subject.MEMBER_TYPE type) {
        Object changedState = null;
        if (isNewSessionAvailable) {
            if (type == MEMBER_TYPE.POTENTIAL)
                changedState = "A notification arrived that a New Yoga Session was created for Potential Members.";
            else if (type == MEMBER_TYPE.EXISTING)
                changedState = "A notification arrived that a New Yoga Session was created for Existing Members.";
        }
        return changedState;
    }

    public void createNewYogaSessionForExistingMembers() {
        isNewSessionAvailable = true;
        sendNotificationToObservers(MEMBER_TYPE.EXISTING);
    }

    public void createNewYogaSessionForPotentialMembers() {
        isNewSessionAvailable = true;
        sendNotificationToObservers(MEMBER_TYPE.POTENTIAL);
    }
}
```

Next, we create a `Observer` interface which will be implemented by `Member` concrete implementation.

```java
public interface Observer {

    void assignSubject(Subject yoga);
    void update(Subject.MEMBER_TYPE type);
    void setMemberType(Subject.MEMBER_TYPE type);
    Subject.MEMBER_TYPE getMemberType();
}
```

Observer interface will have `void assignSubject(Subject yoga)` to build a connection between `Subject` (which is `Yoga`) and itself.  
It also has `update(Subject.MEMBER_TYPE type)` for `Subject` to update it. Plus, a setter and a getter for member type.

```java
// Concrete Observer
public class Member implements Observer {

    private String session;
    private Subject yoga;
    private Subject.MEMBER_TYPE type;

    @Override
    public void assignSubject(Subject yoga) {
        this.yoga = yoga;
        if (getMemberType() == Subject.MEMBER_TYPE.POTENTIAL)
            session = "No new sessions for Potential Members right now.";
        else if (getMemberType() == Subject.MEMBER_TYPE.EXISTING)
            session = "No new sessions for Existing Members right now.";
    }

    @Override
    public void update(Subject.MEMBER_TYPE type) {
        session = (String) yoga.getUpdate(type);
    }

    @Override
    public void setMemberType(Subject.MEMBER_TYPE memberType) {
        type = memberType;
    }

    @Override
    public Subject.MEMBER_TYPE getMemberType() {
        return type;
    }

    public String getSession() {
        return session;
    }
}
```

Finally, we will create a `YogaCenter` class to push notification about yoga sessions to yoga center members.

```java
public class YogaCenter {

    public static void main(String[] args){

        Yoga yoga = new Yoga();

        // create an existing member
        Member existingMember = new Member();
        existingMember.setMemberType(Subject.MEMBER_TYPE.EXISTING);

        // create a potential member
        Member potentialMember = new Member();
        potentialMember.setMemberType(Subject.MEMBER_TYPE.POTENTIAL);

        // subscribe existing member to yoga
        yoga.attachObserver(existingMember);
        existingMember.assignSubject(yoga);

        // subscribe potential member to yoga
        yoga.attachObserver(potentialMember);
        potentialMember.assignSubject(yoga);

        System.out.println(existingMember.getSession());
        System.out.println(potentialMember.getSession());

        System.out.println("----");

        yoga.createNewYogaSessionForPotentialMembers();
        //yoga.createNewYogaSessionForExistingMembers();

        System.out.println("----");

        System.out.println(existingMember.getSession());
        System.out.println(potentialMember.getSession());
    }

}
```

Output when we execute `YogaCenter`, the output will be:

```
No new sessions for Existing Members right now.
No new sessions for Potential Members right now.
----
A notification was sent to Potential members for an upcoming session.
----
No new sessions for Existing Members right now.
A notification arrived that a New Yoga Session was created for Potential Members.
```

We created existing and potential members and set these members as observers of our `Yoga` class. Then we ran `yoga.createNewYogaSessionForPotentialMembers()` 
to create a session for potential members. As it's shown in execution output, a potential member got notified about session but existing member was not.

In this example, Subject created something without knowing who was subscribed to its changes. Once a change happened and it sent the update to its subscribers (in this example we segmented those subscribers as potential and existing). When we need to create such loosely coupled interactions, we can prefer the Observer pattern.

---

Next design pattern is Builder Pattern.

Source code used in this post can be found [here.](https://gitlab.com/onurersen/java-design-patterns) 