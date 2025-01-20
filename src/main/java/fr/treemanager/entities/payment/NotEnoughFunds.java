package fr.treemanager.entities.payment;

public class NotEnoughFunds extends RuntimeException {
    public NotEnoughFunds() {
        super("Not enough funds");
    }
}
