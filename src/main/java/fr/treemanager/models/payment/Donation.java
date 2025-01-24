package fr.treemanager.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.treemanager.models.association.Association;

import java.util.Date;
import java.util.UUID;

public class Donation extends Payment {
    private final UUID donatorId;
    private final String name;

    public Donation(@JsonProperty("description") String description, @JsonProperty("amount") double amount,@JsonProperty("name") String name, @JsonProperty("donatorId") UUID donatorId) {
        super(description, amount);
        this.donatorId = donatorId;
        this.name = name;
    }

    public UUID getDonatorId() {
        return donatorId;
    }

    public String getName() {
        return name;
    }

    public void process(Association association) {
        association.processPayment(this);
        this.state = PaymentState.PAID;
        this.paymentDate = new Date();
    }

    public void deny(){
        this.state = PaymentState.CANCELED;
    }
}