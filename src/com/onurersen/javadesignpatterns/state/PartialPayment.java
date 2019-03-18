package com.onurersen.javadesignpatterns.state;

public class PartialPayment implements PaymentState {

    @Override
    public int handlePayment(int currentAmount, int payment) {
        System.out.println("Partial $" + payment + " payment was done. $" + (currentAmount - payment) + " left.");
        return (currentAmount - payment);
    }

    @Override
    public void sendReceipt() {
        System.out.println("Receipt for partial payment sent...");
    }

}
