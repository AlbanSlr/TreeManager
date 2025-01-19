package fr.treemanager.entities.tree;

public final class Tree {
    private final String gender;
    private final String species;
    private final String commonFrenchName;
    private final String adress;
    private double height;
    private double circumference;

    private GPSCoordinatesPair coordinates;
    private DevelopmentState developmentState;

    public Tree(String gender, String species, String commonFrenchName, String adress, double height, double circumference, GPSCoordinatesPair coordinates) {
        this.gender = gender;
        this.species = species;
        this.commonFrenchName = commonFrenchName;
        this.adress = adress;
        this.height = height;
        this.circumference = circumference;
        this.coordinates = coordinates;
    }
}
