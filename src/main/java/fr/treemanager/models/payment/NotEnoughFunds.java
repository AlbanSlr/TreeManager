package fr.treemanager.models.payment;

public class NotEnoughFunds extends RuntimeException {
    public NotEnoughFunds() {
        super("Not enough funds");
    }
}
