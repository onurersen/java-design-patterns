package com.onurersen.javadesignpatterns.state;

public class FullPayment implements PaymentState {

    @Override
    public int handlePayment(int currentAmount, int payment) {
        System.out.println("Full $" + payment + " payment was done. No more amount left.");
        return 0;
    }

    @Override
    public void sendReceipt() {
    }


}
