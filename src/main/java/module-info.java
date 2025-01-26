module fr.treemanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires java.logging;
    requires java.desktop;

    opens fr.treemanager to javafx.fxml;
    opens fr.treemanager.views to javafx.fxml;
    opens fr.treemanager.views.association to javafx.fxml;
    opens fr.treemanager.views.Member to javafx.fxml;
    opens fr.treemanager.models.visit to javafx.base, com.fasterxml.jackson.databind;
    opens fr.treemanager.views.municipality to javafx.fxml;

    opens fr.treemanager.models to com.fasterxml.jackson.databind;
    opens fr.treemanager.models.association to com.fasterxml.jackson.databind;
    opens fr.treemanager.models.member to com.fasterxml.jackson.databind;
    opens fr.treemanager.models.tree to com.fasterxml.jackson.databind;
    opens fr.treemanager.models.payment to com.fasterxml.jackson.databind;
    exports fr.treemanager;
    exports  fr.treemanager.models to com.fasterxml.jackson.databind;
    exports fr.treemanager.models.payment to com.fasterxml.jackson.databind;
    exports fr.treemanager.models.association to com.fasterxml.jackson.databind;
    exports fr.treemanager.models.member to com.fasterxml.jackson.databind;
    exports fr.treemanager.models.tree to com.fasterxml.jackson.databind;
    exports fr.treemanager.models.visit to com.fasterxml.jackson.databind;
    exports fr.treemanager.views to javafx.fxml;
    exports fr.treemanager.views.association to javafx.fxml;
    exports fr.treemanager.views.Member to javafx.fxml;
}