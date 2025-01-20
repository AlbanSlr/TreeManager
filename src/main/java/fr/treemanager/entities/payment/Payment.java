package fr.treemanager.entities.payment;

import java.util.Date;

public abstract class Payment {
    private final String description;
    private final Issuer issuer;
    private final Receiver receiver;
    private final double amount;
    private Date paymentDate;
    protected PaymentState state;

    public Payment(Issuer issuer, Receiver receiver, String description, double amount) {
        this.issuer = issuer;
        this.receiver = receiver;
        this.description = description;
        this.amount = amount;
        this.state = PaymentState.PENDING;
    }

    public double getAmount() {
        return amount;
    }

    public Issuer getIssuer() {
        return issuer;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getDescription() {
        return description;
    }

    public PaymentState getState() {
        return state;
    }

    protected void setPaid() {
        this.state = PaymentState.PAID;
        paymentDate = new Date();
    }
}
