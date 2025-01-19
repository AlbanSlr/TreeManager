package fr.treemanager.entities.payment;

import java.util.Date;

public class Bill extends Payment {
        private BillState state;

        public Bill(Issuer issuer, Receiver receiver, String description, double amount, Date paymentDate) {
                super(issuer, receiver, description, amount, paymentDate);
                state = BillState.PENDING;
        }

        public boolean isPaid() {
                return state == BillState.PAID;
        }

        public void setPaid() {
                state = BillState.PAID;
        }
}