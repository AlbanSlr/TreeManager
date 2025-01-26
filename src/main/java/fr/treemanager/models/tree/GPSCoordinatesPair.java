package fr.treemanager.models.tree;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GPSCoordinatesPair(
    double latitude,
    double longitude
) {
    public GPSCoordinatesPair(@JsonProperty("latitude") double latitude,@JsonProperty("longitude") double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return latitude + ", " + longitude;
    }
}