package fr.treemanager.entities.payment;

import fr.treemanager.entities.association.Association;

public class Bill extends Payment {
        public Bill(Issuer issuer, Association association, String description, double amount) {
                super(issuer, association, description, amount);
        }

        public boolean isPaid() {
                return this.state == PaymentState.PAID;
        }

        public void process() {
                super.process();
        }
}