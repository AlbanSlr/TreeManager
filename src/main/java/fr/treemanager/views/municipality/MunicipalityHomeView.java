package fr.treemanager.views.municipality;

import fr.treemanager.controllers.municipality.MunicipalityController;
import fr.treemanager.views.AbstractMunicipalityView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.views.AbstractView;

import java.net.URL;
import java.util.ResourceBundle;

public class MunicipalityHomeView extends AbstractMunicipalityView implements Initializable {

    @FXML
    Button addMunicipalityButton;

    @FXML
    TextField nameTF, addressTF, postalCodeTF, cityTF;

    @FXML
    ActionEvent addMunicipality;

    public MunicipalityHomeView(MunicipalityController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addMunicipality(ActionEvent event) {

    }
}
