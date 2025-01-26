package fr.treemanager.views.association;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.models.tree.Tree;
import fr.treemanager.models.visit.Visit;
import fr.treemanager.models.visit.VisitState;
import fr.treemanager.views.AbstractAssociationView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AssociationVisitDefrayalView extends AbstractAssociationView implements Initializable {
    private List<Visit> visits;
    private HBox selectedHBox = null;
    private int selectedIndex = -1;

    @FXML
    private ScrollPane visitDefrayalScrollPane;

    @FXML
    private Button defrayButton;

    @FXML
    private ActionEvent defray;

    @FXML
    private Text infoText;

    public AssociationVisitDefrayalView (AssociationController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.visits =new ArrayList<>();
        List<Visit> allVisits = controller.getVisits();
        if (allVisits == null) {
            return;
        }
        for (Visit visit : allVisits) {
            if (visit.getState() == VisitState.DONE && !hasDefrayal(visit)) {

                this.visits.add(visit);
            }
        }
        VBox vBox = new VBox();
        for (Visit visit : visits) {
            HBox visitHBox = createVisitHBox(visit);
            vBox.getChildren().add(visitHBox);
        }
        visitDefrayalScrollPane.setContent(vBox);
        visitDefrayalScrollPane.setPickOnBounds(false);
    }



    public void defray(ActionEvent event) {
        if (selectedIndex == -1) {
            infoText.setText("Selectionnez une visite");
            return;
        }
        Visit visit = visits.get(selectedIndex);
        if (!controller.createVisitDefrayal(visit)){
            infoText.setText("Erreur lors du paiement de la visite : fonds insuffisants");
            return;
        }
        infoText.setText("Paiement effectué avec succès");
    }

    private HBox createVisitHBox(Visit visit){
        HBox hbox = new HBox(8);
        Text visitDate = new Text(visit.getDateString());
        visitDate.setWrappingWidth(200);
        List<Tree> trees = controller.getRemarkableTrees();
        Tree tree = trees.stream().filter(t -> t.getId().equals(visit.getTreeId())).findFirst().orElse(null);
        if (tree == null) {
            return hbox;
        }
        String treeCommonName = tree.getCommonFrenchName();
        String treeSpecies = tree.getSpecies();
        Text treeCommonNameText = new Text(treeCommonName);
        treeCommonNameText.setWrappingWidth(200);
        Text treeSpeciesText = new Text(treeSpecies);
        treeSpeciesText.setWrappingWidth(200);

        hbox.getChildren().addAll(visitDate, treeCommonNameText, treeSpeciesText);
        hbox.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (selectedHBox != null) {
                    selectedHBox.setStyle("");
                }
                selectedHBox = hbox;
                selectedIndex = visits.indexOf(visit);
                hbox.setStyle("-fx-background-color: #f0f0f0");
            }
        });
        return hbox;
    }

    private boolean hasDefrayal(Visit visit) {
        return controller.getVisitDefrayals().stream()
                .anyMatch(defrayal -> defrayal.getVisitId().equals(visit.getId()));
    }
}
