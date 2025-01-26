package fr.treemanager.controllers.municipality;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.treemanager.models.Municipality;
import fr.treemanager.models.association.Association;
import fr.treemanager.models.payment.Bill;
import fr.treemanager.models.tree.Tree;

import java.io.File;

public class MunicipalityController {
    private final Municipality municipality;
    private Association association;

    public MunicipalityController() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.association = mapper.readValue(new File("./association.json"), Association.class);
        } catch (Exception e) {

            System.out.println("Error while reading association from file, creating a new one : " + e);
            this.association = new Association("lez arbres", new Municipality(Tree.loadTreesFromCSV()));
        }
        this.municipality = association.getMunicipality();
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

    public void save(){

    }
}
