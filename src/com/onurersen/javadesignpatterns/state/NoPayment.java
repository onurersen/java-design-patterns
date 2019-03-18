package com.onurersen.javadesignpatterns.state;

public class NoPayment implements PaymentState {

    @Override
    public int handlePayment(int currentAmount, int payment) {
        System.out.println("No payment was done.");
        return currentAmount;
    }

    @Override
    public void sendReceipt() {
        System.out.println("No payment done.No receipt to be sent...");
    }


}
