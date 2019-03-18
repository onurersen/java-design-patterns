package com.onurersen.javadesignpatterns.state;

public class CreditCardService {

    private PaymentState currentPaymentState;

    int amount = 0;

    // constructing CreditCardService with initial amount
    public CreditCardService(int amount){
        currentPaymentState = new NoPayment();
        this.amount = amount;
    }

    public void requestReceipt(){
        currentPaymentState.sendReceipt();
    }

    public void partialPayment(int partialAmount){
        currentPaymentState = new PartialPayment();
        amount = currentPaymentState.handlePayment(amount,partialAmount);
        System.out.println("Partial amount paid. Remaining amount is $" + amount + ".");
    }

    public void fullPayment(){
        currentPaymentState = new FullPayment();
        currentPaymentState.handlePayment(amount,amount);
        System.out.println("Full amount paid. No debt remains.");
    }

    public void noPayment(){
        currentPaymentState = new NoPayment();
        currentPaymentState.handlePayment(amount,amount);
    }

}
