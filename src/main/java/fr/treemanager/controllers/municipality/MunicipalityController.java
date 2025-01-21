package fr.treemanager.controllers.municipality;

import fr.treemanager.entities.Municipality;
import fr.treemanager.entities.association.Association;
import fr.treemanager.entities.payment.Bill;
import fr.treemanager.entities.payment.Payment;
import fr.treemanager.entities.tree.Tree;

public class MunicipalityController {
    private final Municipality municipality;
    private final Association association;

    public MunicipalityController(Municipality municipality, Association association) {
        this.municipality = municipality;
        this.association = association;
    }

    public void createBill(String billDescription, double amount) {
        Payment payment = new Bill(municipality, association, billDescription,amount);
        this.association.addPayment(payment);
    }


    public void addTree(Tree tree) {
        this.municipality.addTree(tree);
    }

    public void cutDownTree (Tree tree) {
        this.municipality.cutDownTree(tree);
    }

    public void acceptRemarkableClassificationTree (Tree tree) {
        tree.setRemarkable(true); // TODO choose wether or not to set the tree as remarkable
    }
}
