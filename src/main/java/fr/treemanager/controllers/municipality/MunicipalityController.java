package fr.treemanager.controllers.municipality;

import fr.treemanager.models.Municipality;
import fr.treemanager.models.association.Association;
import fr.treemanager.models.payment.Bill;
import fr.treemanager.models.tree.Tree;

public class MunicipalityController {
    private final Municipality municipality;
    private final Association association;

    public MunicipalityController(Municipality municipality, Association association) {
        this.municipality = municipality;
        this.association = association;
    }

    public void createBill(String description, double amount) {
        Bill bill = new Bill(description,amount);
        this.association.addBill(bill);
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
