package fr.treemanager.views;

import fr.treemanager.AssociationApp;
import fr.treemanager.views.association.AssociationNavigationView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class  AbstractNavigationView  {

    @FXML
    StackPane contentPane;

    public AbstractNavigationView() {
    }

    protected void switchContent(String filename) {
        try {
            // load fxml file
            Parent fxml = FXMLLoader.load(Objects.requireNonNull(AssociationApp.class.getResource(filename)));
            System.out.println("Switching to " + filename);
            contentPane.getChildren().clear();
            contentPane.getChildren().add(fxml);
        } catch (IOException exception) {
            Logger.getLogger(AssociationNavigationView.class.getName()).log(Level.SEVERE, null, exception);
        }

    }


}
