package fr.treemanager.views.municipality;

import fr.treemanager.AssociationApp;
import fr.treemanager.controllers.municipality.MunicipalityController;
import fr.treemanager.views.AbstractAssociationView;
import fr.treemanager.views.AbstractMunicipalityView;
import fr.treemanager.views.association.AssociationNavigationView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MunicipalityNavigationView implements Initializable {
    @FXML
    protected StackPane contentPane;

    private final MunicipalityController municipalityController;

    @FXML
    private Button toHome;

    @FXML
    private ActionEvent switchToHome;

    public MunicipalityNavigationView(MunicipalityController controller) {
        this.municipalityController = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.switchToHome(null);
    }

    public void switchToHome(ActionEvent event){
        this.switchContent("municipality/home.fxml", new MunicipalityHomeView(this.municipalityController));
    }


    protected void switchContent(String filename, AbstractMunicipalityView viewController) {
        try {
            // load fxml file
            FXMLLoader fxml = new FXMLLoader(AssociationApp.class.getResource(filename));
            fxml.setController(viewController);

            contentPane.getChildren().clear();
            contentPane.getChildren().add(fxml.load());
        } catch (IOException exception) {
            Logger.getLogger(AssociationNavigationView.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
}
