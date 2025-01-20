package fr.treemanager.entities.tree;

public final class Tree {
    private final String gender;
    private final String species;
    private final String commonFrenchName;
    private final String address;
    private double height;
    private double circumference;
    private boolean remarkable;

    private final GPSCoordinatesPair coordinates;
    private DevelopmentState developmentState;

    public Tree(String gender, String species, String commonFrenchName, String address, double height, double circumference, GPSCoordinatesPair coordinates) {
        this.gender = gender;
        this.species = species;
        this.commonFrenchName = commonFrenchName;
        this.address = address;
        this.height = height;
        this.circumference = circumference;
        this.coordinates = coordinates;
    }

    public String getGender() {
        return gender;
    }

    public String getSpecies() {
        return species;
    }

    public String getCommonFrenchName() {
        return commonFrenchName;
    }

    public String getAddress() {
        return address;
    }

    public double getHeight() {
        return height;
    }

    public double getCircumference() {
        return circumference;
    }

    public GPSCoordinatesPair getCoordinates() {
        return coordinates;
    }

    public DevelopmentState getDevelopmentState() {
        return developmentState;
    }

    public boolean getRemarkable() {
        return remarkable;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setCircumference(double circumference) {
        this.circumference = circumference;
    }

    public void setDevelopmentState(DevelopmentState developmentState) {
        this.developmentState = developmentState;
    }

    public void setRemarkable(boolean remarkable) {
        this.remarkable = remarkable;
    }
}
