package fr.treemanager.models.tree;

public enum DevelopmentState {
    NULL,
    YOUNG,
    YOUNG_ADULT,
    ADULT,
    MATURE;

    public static DevelopmentState parse(String state) {
        return switch (state) {
            case "Jeune" -> YOUNG;
            case "Jeune adulte" -> YOUNG_ADULT;
            case "Adulte" -> ADULT;
            case "Mature" -> MATURE;
            default -> NULL;
        };
    }
}
