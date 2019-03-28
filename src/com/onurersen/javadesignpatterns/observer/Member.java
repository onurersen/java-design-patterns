package com.onurersen.javadesignpatterns.observer;

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
