package fr.treemanager.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.treemanager.models.association.Association;

import java.util.Date;
import java.util.UUID;

public class VisitDefrayal extends Payment {
    private final static double VISIT_DEFRAYAL_AMOUNT = 17;
    private final UUID visitID;

    public VisitDefrayal(@JsonProperty("description") String description, @JsonProperty("visitID") UUID visitID) {
        super(description, VISIT_DEFRAYAL_AMOUNT);
        this.visitID = visitID;
    }

    public UUID getVisitId() {
        return visitID;
    }

    public void process(Association association) {
        try {
            association.tryToPay(this);
        } catch (NotEnoughFunds e) {
            System.err.println("Association " + association.getName() + " has not enough money");
            return;
        }
        this.state = PaymentState.PAID;
        this.paymentDate = new Date();

    }
}
