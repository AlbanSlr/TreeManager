package fr.treemanager.entities.payment;

import java.util.Date;

public abstract class Payment {
    private final String description;
    private final Issuer issuer;
    private final Receiver receiver;
    private final double amount;
    private final Date paymentDate;

    public Payment(Issuer issuer, Receiver receiver, String description, double amount, Date paymentDate) {
        this.issuer = issuer;
        this.receiver = receiver;
        this.description = description;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
