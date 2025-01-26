package fr.treemanager.views.municipality;

import fr.treemanager.controllers.municipality.MunicipalityController;
import fr.treemanager.views.AbstractMunicipalityView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class MunicipalityCreateBillView extends AbstractMunicipalityView implements Initializable {
    @FXML
    private TextField billAmount;

    @FXML
    private Button addBillButton;

    @FXML
    private ActionEvent addBill;

    public MunicipalityCreateBillView(MunicipalityController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        billAmount.setText("");
    }

    public void addBill(ActionEvent event) {
        String amount = billAmount.getText();

        if (amount.isEmpty()) {
            return;
        }

        try {
            BigDecimal amountDecimal = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
            controller.createBill("Facture des espaces verts de " + amountDecimal.doubleValue() + "€",amountDecimal.doubleValue());
            System.out.println("Bill created");
            initialize(null, null);
        } catch (NumberFormatException e) {
            // Handle invalid input
            System.out.println("Invalid amount entered");
        }
    }
}
