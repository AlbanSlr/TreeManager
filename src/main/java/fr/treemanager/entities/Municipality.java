package fr.treemanager.entities;

import java.util.List;

import fr.treemanager.entities.payment.Issuer;
import fr.treemanager.entities.payment.NotEnoughFunds;
import fr.treemanager.entities.payment.Payment;
import fr.treemanager.entities.tree.Tree;

public class Municipality implements Issuer {
    private List<Tree> trees;

    public Municipality(List<Tree> trees) {
        this.trees = trees;
    }

    public void tryToPay(Payment payment) throws NotEnoughFunds {}

    public void addTree(Tree tree) {
        this.trees.add(tree); //
    }

    public void cutDownTree(Tree tree) {
        this.trees.remove(tree);
        // TODO delete or change state
    }
}
