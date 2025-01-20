package fr.treemanager.entities.payment;

public interface Issuer {

    void tryToPay(Payment payment) throws NotEnoughFunds;

}
