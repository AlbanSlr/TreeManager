package fr.treemanager.views.association;

import fr.treemanager.AssociationApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class NavigationController {

    @FXML
    Pane contentPane;

    public NavigationController() {
    }

    protected void switchContent(String filename) {
        try {
            // load fxml file
            Parent fxml = FXMLLoader.load(Objects.requireNonNull(AssociationApp.class.getResource(filename)));

            contentPane.getChildren().removeAll();
            contentPane.getChildren().add(fxml);
        } catch (IOException exception) {
            Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, exception);
        }

    }
}
