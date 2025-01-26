package fr.treemanager.views.municipality;

import fr.treemanager.controllers.municipality.MunicipalityController;
import fr.treemanager.models.tree.DevelopmentState;
import fr.treemanager.models.tree.GPSCoordinatesPair;
import fr.treemanager.models.tree.Tree;
import fr.treemanager.views.AbstractMunicipalityView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MunicipalityAddTreeView extends AbstractMunicipalityView implements Initializable {
    @FXML
    private TextField treeName, treeGender, treeSpecies, treeAdress, treeHeight, treeCircunference, treeLat, treeLong;

    @FXML
    private CheckBox treeRemarquable;

    @FXML
    private ComboBox treeDevState;

    @FXML
    private Button addTreeButton;

    @FXML
    private ActionEvent addTree;


    public MunicipalityAddTreeView(MunicipalityController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.treeDevState.getItems().addAll("Jeune", "Jeune adulte", "Adulte", "Mature");
    }

    public void addTree(ActionEvent event) {
        String name = this.treeName.getText();
        String gender = this.treeGender.getText();
        String species = this.treeSpecies.getText();
        String adress = this.treeAdress.getText();
        String height = this.treeHeight.getText();
        String circunference = this.treeCircunference.getText();
        String lat = this.treeLat.getText();
        String lon = this.treeLong.getText();

        boolean remarquable = this.treeRemarquable.isSelected();
        if (treeDevState.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'ajout de l'arbre");
            alert.setContentText("Veuillez choisir un état de développement");
            alert.showAndWait();
            return;
        }
        String devState = this.treeDevState.getValue().toString();

        if (name.isEmpty() || gender.isEmpty() || species.isEmpty() || adress.isEmpty() || height.isEmpty() || circunference.isEmpty() || lat.isEmpty() || lon.isEmpty() || devState.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'ajout de l'arbre");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            return;
        }
        if (!isNumeric(height) || !isNumeric(circunference) || !isNumeric(lat) || !isNumeric(lon)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'ajout de l'arbre");
            alert.setContentText("Les champs hauteur, circonférence, latitude et longitude doivent être des nombres");
            alert.showAndWait();
            return;
        }

        GPSCoordinatesPair coordinates = new GPSCoordinatesPair(Double.parseDouble(lat), Double.parseDouble(lon));
        DevelopmentState state = DevelopmentState.parse(devState);
        Tree tree = new Tree(gender, species, name, adress, Double.parseDouble(height), Double.parseDouble(circunference), coordinates, state, remarquable);
        controller.addTree(tree);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Arbre ajouté");
        alert.setHeaderText("Arbre ajouté avec succès");
        alert.setContentText("L'arbre a été ajouté avec succès");
        alert.showAndWait();

        this.treeName.clear();
        this.treeGender.clear();
        this.treeSpecies.clear();
        this.treeAdress.clear();
        this.treeHeight.clear();
        this.treeCircunference.clear();
        this.treeLat.clear();
        this.treeLong.clear();
        this.treeRemarquable.setSelected(false);
        this.treeDevState.setValue(null);

    }
    private boolean isNumeric (String str){
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
