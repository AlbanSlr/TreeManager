package fr.treemanager.views.association;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.models.tree.Tree;
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

public class AssociationTreeRankingView extends AbstractAssociationView implements Initializable {
    List<Tree> nonRemarkableTreesRanking;
    @FXML
    private ScrollPane treeRankingScrollPane;

    public AssociationTreeRankingView(AssociationController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nonRemarkableTreesRanking = controller.getNonRemarkableTreesRanking();

        VBox vBox = new VBox();
        for (int i = 0; i < nonRemarkableTreesRanking.size(); i++) {
            Tree tree = nonRemarkableTreesRanking.get(i);
            HBox treeRankingItem = createTreeRankingItem(tree, i + 1);
            vBox.getChildren().add(treeRankingItem);
        }
        treeRankingScrollPane.setContent(vBox);
        treeRankingScrollPane.setPickOnBounds(false);
    }

    public HBox createTreeRankingItem(Tree tree, int rank) {
        HBox treeRankingItem = new HBox(8);

        Text rankText = new Text('[' + String.valueOf(rank) + ']');
        String treeName = truncateString(tree.getCommonFrenchName(), 12);
        String treeSpecies = truncateString(tree.getSpecies(), 12);
        String treeGender = truncateString(tree.getGender(), 12);

        Text treeNameText = new Text(treeName);
        treeNameText.setWrappingWidth(200);
        Text treeSpeciesText = new Text(treeSpecies);
        treeSpeciesText.setWrappingWidth(130);
        Text treeGenderText = new Text(treeGender);
        treeGenderText.setWrappingWidth(130);

        treeRankingItem.getChildren().addAll(rankText, treeNameText, treeSpeciesText, treeGenderText);

        treeRankingItem.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                displayTreeInfo(tree);
            }
        });
        return treeRankingItem;
    }

    private void displayTreeInfo(Tree tree) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tree Information");
        alert.setHeaderText(null);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Text("Nom:"), 0, 0);
        gridPane.add(new Text(tree.getCommonFrenchName()), 1, 0);
        gridPane.add(new Text("Espèce:"), 0, 1);
        gridPane.add(new Text(tree.getSpecies()), 1, 1);
        gridPane.add(new Text("Genre:"), 0, 2);
        gridPane.add(new Text(tree.getGender()), 1, 2);
        gridPane.add(new Text("Adresse:"), 0, 3);
        gridPane.add(new Text(tree.getAddress()), 1, 3);
        gridPane.add(new Text("Hauteur:"), 0, 4);
        gridPane.add(new Text(String.valueOf(tree.getHeight()) + " m"), 1, 4);
        gridPane.add(new Text("Circonférence:"), 0, 5);
        gridPane.add(new Text(String.valueOf(tree.getCircumference()) + " cm"), 1, 5);
        gridPane.add(new Text("Coordonnées:"), 0, 6);
        gridPane.add(new Text(tree.getCoordinates().toString()), 1, 6);

        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();
    }

    private String truncateString(String str, int maxLength) {
        if (str.length() > maxLength) {
            return str.substring(0, maxLength) + "...";
        } else {
            return str;
        }
    }
}
