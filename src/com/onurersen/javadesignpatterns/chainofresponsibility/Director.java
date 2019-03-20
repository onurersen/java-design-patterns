package com.onurersen.javadesignpatterns.chainofresponsibility;

public class Director implements IApproverChain {

    private IApproverChain nextInChain;
    private static int APPROVER_UPPER_LIMIT = 1000;
    private static int APPROVER_LOWER_LIMIT = 500;


    @Override
    public int getApproverUpperLimit() {
        return APPROVER_UPPER_LIMIT;
    }

    @Override
    public int getApproverLowerLimit() {
        return APPROVER_LOWER_LIMIT;
    }

    @Override
    public Approval processExpense(Expense submittedExpense) {
        if (submittedExpense.getExpenseAmount() < getApproverUpperLimit()
                && submittedExpense.getExpenseAmount() > getApproverLowerLimit()) {
            Approval approval = new Approval();
            approval.setApprovalNote("### Approved by Director ###");
            return approval;
        } else {
            System.out.println("degil");
            return nextInChain.processExpense(submittedExpense);
        }
    }

    @Override
    public void nextApprover(IApproverChain nextInChain) {
        this.nextInChain = nextInChain;
    }

}
