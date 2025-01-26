package fr.treemanager.views.Member;

import fr.treemanager.controllers.member.MemberController;
import fr.treemanager.models.tree.Tree;
import fr.treemanager.views.AbstractMemberView;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MemberVoteView extends AbstractMemberView implements Initializable{

    @FXML
    private TableView<Tree> treeTable;

    @FXML
    private TableColumn<Tree, String> nameColumn;

    @FXML
    private TableColumn<Tree, String> speciesColumn;

    @FXML
    private TableColumn<Tree, String> genusColumn;

    @FXML
    private TableColumn<Tree, String> addressColumn;

    @FXML
    private TableColumn<Tree, Void> actionColumn;

    @FXML
    private Text infoLabel;

    private final List<Tree> votedTrees = new ArrayList<>();

    public MemberVoteView(MemberController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Configurer les colonnes avec des méthodes qui renvoient des chaînes de caractères
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCommonFrenchName()));
        speciesColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getSpecies()));
        genusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGender()));
        addressColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));

        // Ajouter un bouton dans la colonne Action
        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button voteButton = new Button("Vote");

            {
                voteButton.setOnAction(event -> {
                    Tree tree = getTableView().getItems().get(getIndex());
                    handleVote(tree);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || votedTrees.contains(getTableView().getItems().get(getIndex()))) {
                    setGraphic(null); // Masquer le bouton si déjà voté
                } else {
                    setGraphic(voteButton);
                }
            }
        });

        // Charger la liste des arbres dans le tableau
        treeTable.getItems().setAll(controller.getArbres());
    }

    private void handleVote(Tree tree) {
        if (votedTrees.size() >= 5) {
            infoLabel.setText("Vous avez atteint la limite de 5 votes.");
            return;
        }

        // Ajouter l'arbre voté à la liste
        votedTrees.add(tree);

        // Appeler la méthode de vote
        controller.vote(tree);

        // Mettre à jour l'affichage
        treeTable.refresh();
        infoLabel.setText("Vous avez voté pour " + votedTrees.size() + "/5 arbres.");
    }

}
