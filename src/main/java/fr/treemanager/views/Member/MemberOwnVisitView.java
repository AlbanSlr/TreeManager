package fr.treemanager.views.Member;

import fr.treemanager.controllers.member.MemberController;
import fr.treemanager.models.visit.Visit;
import fr.treemanager.models.visit.VisitState;
import fr.treemanager.models.tree.Tree;
import fr.treemanager.views.AbstractMemberView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.UUID;

public class MemberOwnVisitView extends AbstractMemberView implements Initializable{

    public MemberOwnVisitView(MemberController controller) {
        super(controller);
    }


    @FXML
    private TableView<Visit> visitTable;

    @FXML
    private TableColumn<Visit, String> treeNameColumn;

    @FXML
    private TableColumn<Visit, String> visitDateColumn;

    @FXML
    private TableColumn<Visit, String> visitStateColumn;

    @FXML
    private TableColumn<Visit, Void> actionColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Configurer les colonnes
        treeNameColumn.setCellValueFactory(cellData -> {
            Visit visit = cellData.getValue(); // La visite pour la ligne actuelle
            UUID treeId = visit.getTreeId(); // Récupère l'ID de l'arbre
            Tree tree = controller.getTreeById(treeId); // Récupère l'arbre associé
            return new SimpleStringProperty(tree != null ? tree.getCommonFrenchName() : "Arbre inconnu");
        });
        visitDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        visitStateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));


        // Ajouter une colonne pour les actions (réserver)
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button completeButton = new Button("Faire la visite");

            {
                completeButton.setOnAction(event -> {
                    Visit visit = getTableView().getItems().get(getIndex());
                    completeVisit(visit);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(completeButton);
                }
            }
        });

        // Charger les données
        List<Visit> memberVisits = controller.getAssociation().getVisits().stream()
                .filter(visit -> visit.getMemberId() != null && visit.getMemberId().equals(controller.getMember().getId()))
                .toList();

        visitTable.getItems().addAll(memberVisits);
    }

        private void loadVisits() {
            // Obtenir les visites disponibles depuis le contrôleur
        ObservableList<Visit> visits = FXCollections.observableArrayList(controller.getAvailableVisits());
        visitTable.setItems(visits);
    }
    private void completeVisit(Visit visit) {
        try {
            // Vérifier si la visite est bien en état SCHEDULED avant de la passer en DONE
            if (visit.getState() == VisitState.SCHEDULED) {
                visit.setState(VisitState.DONE);
                visitTable.refresh(); // Rafraîchir la table pour afficher la mise à jour
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }



}
