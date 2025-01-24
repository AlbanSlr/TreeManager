package fr.treemanager.views.association;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.views.AbstractAssociationView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AssociationHomeView extends AbstractAssociationView implements Initializable{
    @FXML
    private Text balanceText, budgetYearText;

    @FXML
    private Button nextExercize;

    @FXML
    private ActionEvent toNextExercize;

    public AssociationHomeView(AssociationController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        balanceText.setText(controller.getBalance() + " €");
        budgetYearText.setText(controller.getBudgetYear());
    }

    public void toNextExercize(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir passer à l'année fiscale suivante ?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                controller.nextBudgetYear();
                budgetYearText.setText(controller.getBudgetYear());
            }
        });
    }


}
