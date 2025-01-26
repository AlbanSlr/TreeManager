package fr.treemanager.views.association;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.models.tree.Tree;
import fr.treemanager.models.visit.Visit;
import fr.treemanager.views.AbstractAssociationView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class AssociationCreateVisitView extends AbstractAssociationView implements Initializable {
    List<Tree> remarkableTrees;
    private HBox selectedHBox = null;
    private int selectedIndex = -1;

    @FXML
    private ScrollPane createVisitScrollPane;

    @FXML
    private Button createVisitButton;

    @FXML
    private ActionEvent createVisit;

    public AssociationCreateVisitView(AssociationController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.remarkableTrees = controller.getRemarkableTrees();

        VBox vBox = new VBox(8);

        for (Tree tree : remarkableTrees) {
            HBox treeHbox = createVisitHbox(tree);
            vBox.getChildren().add(treeHbox);
        }

        createVisitScrollPane.setContent(vBox);
        createVisitScrollPane.setPickOnBounds(false);
    }

    private HBox createVisitHbox(Tree tree){
        HBox hBox = new HBox();

        String treeName = truncateString(tree.getCommonFrenchName(), 12);
        String treeSpecies = truncateString(tree.getSpecies(), 12);
        String treeGender = truncateString(tree.getGender(), 12);
        String treeLocation = truncateString(tree.getAddress(), 15);

        Text treeNameText = new Text(treeName);
        treeNameText.setWrappingWidth(200);
        Text treeSpeciesText = new Text(treeSpecies);
        treeSpeciesText.setWrappingWidth(130);
        Text treeGenderLocation = new Text(treeGender);
        treeGenderLocation.setWrappingWidth(130);
        Text treeLocationText = new Text(treeLocation);
        treeLocationText.setWrappingWidth(150);


        hBox.getChildren().addAll(treeNameText, treeSpeciesText, treeGenderLocation, treeLocationText);

        hBox.setOnMouseClicked(event -> toggleSelection(hBox));

        return hBox;
    }

    private String truncateString(String str, int maxLength) {
        if (str.length() > maxLength) {
            return str.substring(0, maxLength) + "...";
        } else {
            return str;
        }
    }


    private void toggleSelection(HBox hBox) {
        if (selectedHBox != null) {
            selectedHBox.getStyleClass().remove("selected");
            selectedHBox.setStyle("");
        }
        if (selectedHBox == hBox) {
            selectedHBox = null;
            selectedIndex = -1;
        } else {
            hBox.getStyleClass().add("selected");
            hBox.setStyle("-fx-background-color: lightblue;");
            selectedHBox = hBox;
            selectedIndex = ((VBox) createVisitScrollPane.getContent()).getChildren().indexOf(hBox);
        }
    }

    public void createVisit(ActionEvent event) {
        if (selectedIndex != -1) {
            Tree selectedTree = remarkableTrees.get(selectedIndex);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informations sur l'arbre");
            alert.setHeaderText(null);

            DatePicker datePicker = new DatePicker();
            datePicker.setValue(LocalDate.now().plusDays(1)); // Default to tomorrow

            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            gridPane.add(new Text("Nom:"), 0, 0);
            gridPane.add(new Text(selectedTree.getCommonFrenchName()), 1, 0);
            gridPane.add(new Text("Espèce:"), 0, 1);
            gridPane.add(new Text(selectedTree.getSpecies()), 1, 1);
            gridPane.add(new Text("Genre:"), 0, 2);
            gridPane.add(new Text(selectedTree.getGender()), 1, 2);
            gridPane.add(new Text("Adresse:"), 0, 3);
            gridPane.add(new Text(selectedTree.getAddress()), 1, 3);
            gridPane.add(new Text("Hauteur:"), 0, 4);
            gridPane.add(new Text(String.valueOf(selectedTree.getHeight()) + " m"), 1, 4);
            gridPane.add(new Text("Circonférence:"), 0, 5);
            gridPane.add(new Text(String.valueOf(selectedTree.getCircumference()) + " cm"), 1, 5);
            gridPane.add(new Text("Coordonnées:"), 0, 6);
            gridPane.add(new Text(selectedTree.getCoordinates().toString()), 1, 6);

            VBox vBox = new VBox(5);
            vBox.getChildren().addAll(gridPane, datePicker);
            alert.getDialogPane().setContent(vBox);

            alert.showAndWait().ifPresent(response -> {
                LocalDate selectedDate = datePicker.getValue();
                if (selectedDate != null && selectedDate.isAfter(LocalDate.now())) {
                    controller.addScheduledVisit(new Visit(null, Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), selectedTree.getId()));
                    System.out.println("Visit created for date: " + selectedDate);
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Erreur");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Veuillez sélectionner une date dans le futur.");
                    errorAlert.showAndWait();
                }
            });
        }
    }
}
