package fr.treemanager.models.tree;

public record GPSCoordinatesPair(
    double latitude,
    double longitude
) {
    @Override
    public String toString() {
        return latitude + ", " + longitude;
    }
}