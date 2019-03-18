package com.onurersen.javadesignpatterns.state;

public class CreditCardPayment {

    public static void main(String[] args){

        CreditCardService service = new CreditCardService(1000);
        // will request receipt but no payment done current status is no payment
        service.requestReceipt();

        System.out.println("\n");

        service.partialPayment(65);
        service.requestReceipt();

        System.out.println("\n");

        // again will send receipt for partial payment
        // because current state is partial payment
        service.requestReceipt();

        service.fullPayment();
        service.requestReceipt();

        // again will send receipt for full payment
        // because current state is full payment
        service.requestReceipt();

        System.out.println("\n");
    }
}
