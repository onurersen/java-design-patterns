package com.onurersen.javadesignpatterns.visitor;

public class RomanAntiquities implements AntiquitiesMuseum {

    private int entranceFee;
    private String visitHours;

    public RomanAntiquities(int entranceFee, String visitHours) {
        this.entranceFee = entranceFee;
        this.visitHours = visitHours;
    }

    public int getEntranceFee() {
        return entranceFee;
    }

    public void setEntranceFee(int entranceFee) {
        this.entranceFee = entranceFee;
    }

    public String getVisitHours() {
        return visitHours;
    }

    public void setVisitHours(String visitHours) {
        this.visitHours = visitHours;
    }

    @Override
    public int accept(MuseumVisitor visitor) {
        return visitor.visit(this);
    }
}
