package fr.treemanager.entities.payment;

import fr.treemanager.entities.association.Association;
import fr.treemanager.entities.member.Donator;

public class Donation extends Payment {
    public Donation(Donator donator, Association association, double amount) {
        super(donator, association, "Donation de " + donator, amount);
    }
}