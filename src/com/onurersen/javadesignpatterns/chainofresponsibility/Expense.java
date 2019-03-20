package com.onurersen.javadesignpatterns.chainofresponsibility;

public class Expense implements IExpense {

    private int expenseAmount;

    @Override
    public int getExpenseAmount() {
        return expenseAmount;
    }

    @Override
    public void setExpenseAmount(int expenseAmount) {
        this.expenseAmount = expenseAmount;
    }
}
