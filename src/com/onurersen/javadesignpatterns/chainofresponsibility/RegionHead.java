package com.onurersen.javadesignpatterns.chainofresponsibility;

public class RegionHead implements IApproverChain {

    private IApproverChain nextInChain;
    private static int APPROVER_UPPER_LIMIT = 2000;
    private static int APPROVER_LOWER_LIMIT = 1000;


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
            approval.setApprovalNote("### Approved by RegionHead ###");
            return approval;
        } else {
            Approval approval = new Approval();
            approval.setApprovalNote("Expense exceeding limits. Please talk to your manager.");
            return approval;
        }
    }

    @Override
    public void nextApprover(IApproverChain nextInChain) {
        this.nextInChain = nextInChain;
    }
}
