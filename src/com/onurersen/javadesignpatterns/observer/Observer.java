package com.onurersen.javadesignpatterns.observer;

public interface Observer {

    void assignSubject(Subject yoga);
    void update(Subject.MEMBER_TYPE type);
    void setMemberType(Subject.MEMBER_TYPE type);
    Subject.MEMBER_TYPE getMemberType();
}
