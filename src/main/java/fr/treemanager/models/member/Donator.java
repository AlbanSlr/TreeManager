package fr.treemanager.models.member;

import fr.treemanager.models.payment.Payment;

public class Donator {
    public final String name;

    public Donator(String name) {
        this.name = name;
    }

    public void tryToPay(Payment payment) {}
}
