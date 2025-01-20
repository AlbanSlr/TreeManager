package fr.treemanager.entities.member;

import fr.treemanager.entities.payment.Issuer;
import fr.treemanager.entities.payment.Payment;

public class Donator implements Issuer {
    public final String name;

    public Donator(String name) {
        this.name = name;
    }

    public void tryToPay(Payment payment) {}
}
