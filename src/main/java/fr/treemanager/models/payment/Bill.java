package fr.treemanager.models.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.treemanager.models.association.Association;

import java.util.Date;

public class Bill extends Payment{
        public Bill(@JsonProperty("description") String description,@JsonProperty("amount") double amount) {
            super(description, amount);
        }

        protected void process(Association association) {
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