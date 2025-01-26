package fr.treemanager.views.Member;

import fr.treemanager.controllers.member.MemberController;
import fr.treemanager.models.visit.Visit;
import fr.treemanager.views.AbstractMemberView;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class MemberVisitView extends AbstractMemberView implements Initializable{

    @FXML
    private TableView<Visit> visitTable;

    @FXML
    private TableColumn<Visit, Integer> idColumn;

    @FXML
    private TableColumn<Visit, String> dateColumn;

    @FXML
    private TableColumn<Visit, String> stateColumn;

    @FXML
    private TableColumn<Visit, Void> actionColumn;

    public MemberVisitView(MemberController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurer les colonnes
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

        // Ajouter une colonne pour les actions (réserver)
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button reserveButton = new Button("Réserver");

            {
                reserveButton.setOnAction(event -> {
                    Visit visit = getTableView().getItems().get(getIndex());
                    reserveVisit(visit);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(reserveButton);
                }
            }
        });

        // Charger les visites disponibles
        loadVisits();
    }

    private void loadVisits() {
        // Obtenir les visites disponibles depuis le contrôleur
        ObservableList<Visit> visits = FXCollections.observableArrayList(controller.getAvailableVisits());
        visitTable.setItems(visits);
    }

    private void reserveVisit(Visit visit) {
        try {
            // Réserver la visite pour le membre connecté
            controller.selectScheduledVisit(visit);
            visitTable.getItems().remove(visit); // Retirer la visite de la liste affichée
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

}
