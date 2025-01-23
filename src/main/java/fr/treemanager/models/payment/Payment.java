package fr.treemanager.models.payment;

import fr.treemanager.models.association.Association;

import java.util.Date;
import java.util.UUID;

public abstract class Payment {
    private final UUID id;
    private final String description;
    private final double amount;
    protected Date paymentDate;
    protected PaymentState state;

    public Payment(String description, double amount) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.amount = amount;
        this.state = PaymentState.PENDING;
    }

    public UUID getId() {
        return id;
    }

    public double getAmount() {
        return amount;
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

    protected abstract void process(Association association);
}
