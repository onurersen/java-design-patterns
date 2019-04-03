package com.onurersen.javadesignpatterns.visitor;

public interface MuseumVisitor {

    int visit(EgyptianAntiquities egyptianAntiquities);
    int visit(GreekAntiquities greekAntiquities);
    int visit(RomanAntiquities romanAntiquities);

}
