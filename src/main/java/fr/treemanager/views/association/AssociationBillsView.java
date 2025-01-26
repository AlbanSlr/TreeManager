package fr.treemanager.views.association;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.models.payment.Bill;
import fr.treemanager.models.payment.PaymentState;
import fr.treemanager.views.AbstractAssociationView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AssociationBillsView extends AbstractAssociationView implements Initializable {
    List<Bill> bills;

    @FXML
    private ScrollPane billsScrollPane;
    @FXML
    private Text errorMsgText;


    public AssociationBillsView(AssociationController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bills = controller.getBills();

        if (bills.isEmpty()) {
            errorMsgText.setText("Aucune facture n'a été trouvée.");
            return;
        }
        VBox billsVBox = new VBox();
        for (Bill bill : bills) {
            HBox billHbox = createBillHbox(bill);
            billsVBox.getChildren().add(billHbox);
        }
        errorMsgText.setText("Appuyez sur une facture pour la visualiser.");
        billsScrollPane.setContent(billsVBox);
        billsScrollPane.setPickOnBounds(false);
    }

    private HBox createBillHbox(Bill bill) {
        HBox billHbox = new HBox(10);
        String billString = "Facture n°" + bill.getId() + " - " + bill.getDateString() + " - " + bill.getAmount() + "€";
        Text billText = new Text();
        billText.setText(billString);

        billHbox.getChildren().add(billText);
        billHbox.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                displayBill(bill);
            }
        });
        return billHbox;
    }

    public void displayBill(Bill bill) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Facture n°" + bill.getId());
        alert.setHeaderText(null);
        alert.setContentText("Montant : " + bill.getAmount() + "€\n" +
                "Date : " + bill.getDateString() + "\n" +
                "Description : " + bill.getDescription());

        Button payButton = new Button("Payer");
        payButton.setOnAction(event -> {
            bill.process(controller.getAssociation());
            if (bill.getState() != PaymentState.PAID) {
                errorMsgText.setText("Erreur : La facture n'a pas pu être payée.");
            } else {
                initialize(null, null);
            }
        });

        VBox vbox = new VBox(alert.getDialogPane().getContent(), payButton);
        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();
    }
}
