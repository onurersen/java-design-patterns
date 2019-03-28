package com.onurersen.javadesignpatterns.observer;

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
            for (Observer o : observers) {
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
