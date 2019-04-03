package com.onurersen.javadesignpatterns.visitor;

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
