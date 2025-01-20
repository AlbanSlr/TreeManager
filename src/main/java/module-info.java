module fr.treemanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens fr.treemanager to javafx.fxml;
    opens fr.treemanager.views to javafx.fxml;
    opens fr.treemanager.views.association to javafx.fxml;

    exports fr.treemanager;
    exports fr.treemanager.views to javafx.fxml;
    exports fr.treemanager.views.association to javafx.fxml;
}