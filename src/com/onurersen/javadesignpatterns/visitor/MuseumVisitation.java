package com.onurersen.javadesignpatterns.visitor;

public class MuseumVisitation {

    public static void main(String[] args) {

        EgyptianAntiquities egyptianAntiquities = new EgyptianAntiquities(50, "09:00 - 18:00");
        RomanAntiquities romanAntiquities = new RomanAntiquities(75, "08:30 - 17:00");
        GreekAntiquities greekAntiquities = new GreekAntiquities(100, "08:00 - 19:00");

        Archeologist archeologist = new Archeologist(400);

        egyptianAntiquities.accept(archeologist);
        romanAntiquities.accept(archeologist);
        greekAntiquities.accept(archeologist);

        System.out.println("\n");
        System.out.println("Remaining budget is : $" + archeologist.getBudget());

    }
}
