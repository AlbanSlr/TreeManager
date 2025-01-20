package fr.treemanager.entities.payment;

import fr.treemanager.entities.association.Association;

public class Bill extends Payment {
        public Bill(Association association, Receiver receiver, String description, double amount) {
                super(association, receiver, description, amount);
        }

        public boolean isPaid() {
                return this.state == PaymentState.PAID;
        }

        public void setPaid() {
                super.setPaid();
        }
}