package fr.treemanager.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.treemanager.models.association.Association;

import java.util.Date;
import java.util.UUID;

public class Subscription extends Payment {
    private final static double SUBSCRIPTION_AMOUNT = 42;

    private final UUID memberId;


    public Subscription(@JsonProperty("description") String description, @JsonProperty("memberId") UUID memberId) {
        super(description, SUBSCRIPTION_AMOUNT);
        this.memberId = memberId;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public void process(Association association) {
        try {
            association.processPayment(this);
        } catch (NotEnoughFunds e) {
            System.err.println("Association " + association.getName() + " has not enough money");
            return;
        }
        this.state = PaymentState.PAID;
        this.paymentDate =  new Date();

    }


}