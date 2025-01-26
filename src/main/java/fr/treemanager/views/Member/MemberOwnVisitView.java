package fr.treemanager.views.Member;

import fr.treemanager.controllers.member.MemberController;
import fr.treemanager.models.visit.Visit;
import fr.treemanager.models.tree.Tree;
import fr.treemanager.views.AbstractMemberView;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class MemberOwnVisitView extends AbstractMemberView implements Initializable{

    public MemberOwnVisitView(MemberController controller) {
        super(controller);
    }


    @FXML
    private TableView<Visit> visitsTable;

    @FXML
    private TableColumn<Visit, String> treeNameColumn;

    @FXML
    private TableColumn<Visit, String> visitDateColumn;

    @FXML
    private TableColumn<Visit, String> visitStateColumn;

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

        // Charger les données
        List<Visit> memberVisits = controller.getAssociation().getVisits().stream()
                .filter(visit -> visit.getMemberId() != null && visit.getMemberId().equals(controller.getMember().getId()))
                .toList();

        visitsTable.getItems().addAll(memberVisits);
    }

}
