package fr.treemanager.models.tree;

import fr.treemanager.utils.CSVReader;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Tree {
    private static final String CSV_FILE_PATH = "./resources/trees.csv";

    private final UUID id;
    private final String gender;
    private final String species;
    private final String commonFrenchName;
    private final String address;
    private double height;
    private double circumference;
    private boolean remarkable;

    private final GPSCoordinatesPair coordinates;
    private DevelopmentState developmentState;

    public Tree(){
        this.id = UUID.randomUUID();
        this.gender = "";
        this.species = "";
        this.commonFrenchName = "";
        this.address = "";
        this.height = 0;
        this.circumference = 0;
        this.coordinates = new GPSCoordinatesPair(0, 0);
        this.developmentState = DevelopmentState.NULL;
        this.remarkable = false;
    }


    public Tree(String gender, String species, String commonFrenchName, String address, double height, double circumference, GPSCoordinatesPair coordinates, DevelopmentState developmentState, boolean remarkable) {
        this.id = UUID.randomUUID();
        this.gender = gender;
        this.species = species;
        this.commonFrenchName = commonFrenchName;
        this.address = address;
        this.height = height;
        this.circumference = circumference;
        this.coordinates = coordinates;
        this.developmentState = developmentState;
        this.remarkable = remarkable;
    }

    public UUID getId() {
        return id;
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

    public static List<Tree> loadTreesFromCSV() {
        List<Tree> trees = new ArrayList<>();

        List<String[]> data = CSVReader.read(CSV_FILE_PATH);

        for (String[] row : data) {

            try {
                String gender = row[9];
                String species = row[10];
                String commonFrenchName = row[8];
                String address = row[6];
                double height = Double.parseDouble(row[13]);
                double circumference = Double.parseDouble(row[12]);
                boolean remarkable = row[15].equals("OUI");

                String[] coordParts = row[16].split(",");
                double latitude = Double.parseDouble(coordParts[0]);
                double longitude = Double.parseDouble(coordParts[1]);

                GPSCoordinatesPair coordinates = new GPSCoordinatesPair(latitude, longitude);
                DevelopmentState developmentState = DevelopmentState.parse(row[14]);

                Tree tree = new Tree(gender, species, commonFrenchName, address, height, circumference,
                        coordinates, developmentState, remarkable);

                trees.add(tree);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load trees from CSV file");
            }
        }

        return trees;
    }

}
