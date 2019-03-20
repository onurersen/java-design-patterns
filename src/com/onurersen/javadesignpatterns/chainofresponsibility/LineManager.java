package com.onurersen.javadesignpatterns.chainofresponsibility;

public class LineManager implements IApproverChain {

    private IApproverChain nextInChain;
    private static int APPROVER_UPPER_LIMIT = 500;
    private static int APPROVER_LOWER_LIMIT = 0;


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
            approval.setApprovalNote("### Approved by LineManager ###");
            return approval;
        } else {
            return nextInChain.processExpense(submittedExpense);
        }
    }

    @Override
    public void nextApprover(IApproverChain nextInChain) {
        this.nextInChain = nextInChain;
    }
}
