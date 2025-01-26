package fr.treemanager.views;


import fr.treemanager.controllers.municipality.MunicipalityController;

public abstract class AbstractMunicipalityView {
    protected MunicipalityController controller;

    public AbstractMunicipalityView(MunicipalityController controller) {
        this.controller = controller;
    }
}