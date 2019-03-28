package com.onurersen.javadesignpatterns.observer;

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
