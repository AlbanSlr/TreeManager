package fr.treemanager.views.association;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.models.tree.Tree;
import fr.treemanager.models.visit.Visit;
import fr.treemanager.models.visit.VisitState;
import fr.treemanager.views.AbstractAssociationView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AssociationVisitHistoryView extends AbstractAssociationView implements Initializable {
    List<Visit> visits;

    @FXML
    private ScrollPane visitHistoryScrollPane;

    public AssociationVisitHistoryView(AssociationController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        visits = controller.getVisits();

        VBox visitHistoryVBox = new VBox();
        for (Visit visit : visits) {
            HBox visitHbox = createVisitHistoryHBox(visit);
            visitHistoryVBox.getChildren().add(visitHbox);
        }
        visitHistoryScrollPane.setContent(visitHistoryVBox);
        visitHistoryScrollPane.setPickOnBounds(false);
    }

    public HBox createVisitHistoryHBox(Visit visit) {
        HBox visitHbox = new HBox();
        if (visit == null) {
            return visitHbox;
        }
        List<Tree> trees = controller.getRemarkableTrees();
        Tree tree = trees.stream().filter(t -> t.getId().equals(visit.getTreeId())).findFirst().orElse(null);
        if (tree == null) {
            return visitHbox;
        }
        Text visitDate = new Text(visit.getDateString());
        visitDate.setWrappingWidth(200);
        String treeCommonName = tree.getCommonFrenchName();
        String treeSpecies = tree.getSpecies();
        String visitState = switch (visit.getState()) {
            case SCHEDULED -> "Prévue";
            case DONE -> "Réalisée";
            case CANCELED -> "Annulée";
        };
        Text treeCommonNameText = new Text(treeCommonName);
        treeCommonNameText.setWrappingWidth(200);
        Text treeSpeciesText = new Text(treeSpecies);
        treeSpeciesText.setWrappingWidth(200);
        Text visitStateText = new Text(visitState);
        visitHbox.getChildren().addAll(visitDate, treeCommonNameText, treeSpeciesText, visitStateText);

        visitHbox.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                displayVisitInfo(visit, tree);
            }
        });

        return visitHbox;
    }

    private void displayVisitInfo(Visit visit, Tree tree) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information de la visite");
        alert.setHeaderText(null);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Text("Date :"), 0, 0);
        gridPane.add(new Text(visit.getDateString()), 1, 0);
        gridPane.add(new Text("Nom de l'abre :"), 0, 1);
        gridPane.add(new Text(tree.getCommonFrenchName()), 1, 1);
        gridPane.add(new Text("Espece :"), 0, 2);
        gridPane.add(new Text(tree.getSpecies()), 1, 2);
        gridPane.add(new Text("Etat de la visite :"), 0, 3);
        gridPane.add(new Text(visit.getState().toString()), 1, 3);
        if (visit.getState() == VisitState.DONE) {
            gridPane.add(new Text("Visité par :"), 0, 4);
            gridPane.add(new Text(controller.getMember(visit.getMemberId()).getFirstName()), 1, 4);
            gridPane.add(new Text("CR :"), 0, 5);
            gridPane.add(new Text(visit.getReport().getContent()), 1, 5);
        } else if (visit.getState() == VisitState.SCHEDULED) {
            if (visit.isBooked()) {
                gridPane.add(new Text("Réservée par :"), 0, 4);
                gridPane.add(new Text(controller.getMember(visit.getMemberId()).getFirstName()), 1, 4);
            }
        }

        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();
    }
}
