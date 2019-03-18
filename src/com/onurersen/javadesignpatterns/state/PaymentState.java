package com.onurersen.javadesignpatterns.state;

public interface PaymentState {

    int handlePayment(int currentAmount, int payment);

    void sendReceipt();

}
