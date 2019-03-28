package com.onurersen.javadesignpatterns.observer;

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
