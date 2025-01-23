package fr.treemanager.views;

import fr.treemanager.controllers.association.AssociationController;

public abstract class AbstractAssociationView {
    protected AssociationController controller;

    public AbstractAssociationView(AssociationController controller) {
        this.controller = controller;
    }
}
