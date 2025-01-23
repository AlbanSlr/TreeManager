package fr.treemanager.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.treemanager.models.association.Association;

import java.util.Date;
import java.util.UUID;

public class Donation extends Payment {
    private final UUID donatorId;

    public Donation(@JsonProperty("description") String description, @JsonProperty("amount") double amount, @JsonProperty("donatorId") UUID donatorId) {
        super(description, amount);
        this.donatorId = donatorId;
    }

    public void process(Association association) {
        try {
            association.processPayment(this);
        } catch (NotEnoughFunds e) {
            System.err.println("Association " + association.getName() + " has not enough money");
            return;
        }
        this.state = PaymentState.PAID;
        this.paymentDate = new Date();
    }
}