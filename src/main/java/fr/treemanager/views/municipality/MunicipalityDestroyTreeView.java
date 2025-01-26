package fr.treemanager.views.municipality;

import fr.treemanager.controllers.municipality.MunicipalityController;
import fr.treemanager.models.tree.Tree;
import fr.treemanager.views.AbstractMunicipalityView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MunicipalityDestroyTreeView extends AbstractMunicipalityView implements Initializable {
    private List<Tree> trees;
    private List<Tree> filteredTrees;

    private Tree selectedTree = null;

    @FXML
    private ScrollPane treeScrollPane;

    @FXML
    private Button deleteTreeButton;

    @FXML
    private ActionEvent deleteTree;

    @FXML
    private TextField searchBar;

    public MunicipalityDestroyTreeView(MunicipalityController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.trees = controller.getTrees();
        this.filteredTrees = new ArrayList<>(trees);
        if (this.trees == null) {
            return;
        }

        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filterTreeList(newValue);
            }
        });
        updateTreeList();
    }

    private void filterTreeList(String query) {
        filteredTrees.clear();
        for (Tree tree : trees) {
            if (tree.getCommonFrenchName().toLowerCase().contains(query.toLowerCase()) ||
                    tree.getSpecies().toLowerCase().contains(query.toLowerCase()) ||
                    tree.getGender().toLowerCase().contains(query.toLowerCase()) ||
                    tree.getAddress().toLowerCase().contains(query.toLowerCase())) {
                filteredTrees.add(tree);
            }
        }
        updateTreeList();
    }

    private void updateTreeList() {
        VBox vBox = new VBox();
        if (filteredTrees.size() > 99){
            filteredTrees = filteredTrees.subList(0, 99);
        }
        for (Tree tree : filteredTrees) {
            HBox treeHBox = createTreeHBox(tree);
            vBox.getChildren().add(treeHBox);
        }
        treeScrollPane.setContent(vBox);
        treeScrollPane.setPickOnBounds(false);
    }

    private HBox createTreeHBox(Tree tree) {
        HBox hBox = new HBox(8);
        String treeName = tree.getCommonFrenchName();
        String treeSpecies = tree.getSpecies();
        String treeGender = tree.getGender();
        String treeAddress = tree.getAddress();

        Text treeNameText = new Text(truncateString(treeName, 12));
        treeNameText.setWrappingWidth(200);
        Text treeSpeciesText = new Text(truncateString(treeSpecies, 12));
        treeSpeciesText.setWrappingWidth(130);
        Text treeGenderText = new Text(truncateString(treeGender, 12));
        treeGenderText.setWrappingWidth(130);
        Text treeAddressText = new Text(truncateString(treeAddress, 12));
        treeAddressText.setWrappingWidth(150);

        hBox.getChildren().addAll(treeNameText, treeSpeciesText, treeGenderText, treeAddressText);

        hBox.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (selectedTree == tree) {
                    hBox.setStyle("");
                    selectedTree = null;
                } else {
                    hBox.setStyle("-fx-background-color: #0000ff;");
                    selectedTree = tree;
                }
            }
        });

        return hBox;
    }

    public void deleteTree(ActionEvent event) {
        if (selectedTree == null) {
            return;
        }
        controller.cutDownTree(this.selectedTree);
        initialize(null, null);
    }

    private String truncateString(String str, int maxLength) {
        if (str.length() > maxLength) {
            return str.substring(0, maxLength) + "...";
        } else {
            return str;
        }
    }
}
