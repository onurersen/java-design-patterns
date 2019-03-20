package com.onurersen.javadesignpatterns.chainofresponsibility;

import java.util.Random;

public class ExpenseSubmission {

    public static void main(String[] args){

        IApproverChain lineManager = new LineManager();
        IApproverChain director = new Director();
        IApproverChain regionHead = new RegionHead();

        // chaining approvers one to another regarding approve level
        lineManager.nextApprover(director);
        director.nextApprover(regionHead);

        Random random = new Random();

        Expense expense = new Expense();
        expense.setExpenseAmount(random.nextInt(2000));
        System.out.println("Submitting $" + expense.getExpenseAmount() + " expense to Line Manager");
        System.out.println(lineManager.processExpense(expense).getApprovalNote());

        System.out.println("\n");

        expense.setExpenseAmount(random.nextInt(2000));
        System.out.println("Submitting $" + expense.getExpenseAmount() + " expense to Line Manager");
        System.out.println(lineManager.processExpense(expense).getApprovalNote());

        System.out.println("\n");

        expense.setExpenseAmount(random.nextInt(2000));
        System.out.println("Submitting $" + expense.getExpenseAmount() + " expense to Line Manager");
        System.out.println(lineManager.processExpense(expense).getApprovalNote());

    }

}
