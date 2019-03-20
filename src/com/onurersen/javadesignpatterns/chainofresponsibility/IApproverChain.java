package com.onurersen.javadesignpatterns.chainofresponsibility;

public interface IApproverChain {

    int getApproverUpperLimit();

    int getApproverLowerLimit();

    Approval processExpense(Expense submittedExpense);

    void nextApprover(IApproverChain nextInChain);

}
