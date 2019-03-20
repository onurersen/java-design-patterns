package com.onurersen.javadesignpatterns.chainofresponsibility;

public class Approval implements IApproval {

    String approvalNote;

    @Override
    public void setApprovalNote(String note) {
        this.approvalNote = note;
    }

    @Override
    public String getApprovalNote() {
        return approvalNote;
    }
}
