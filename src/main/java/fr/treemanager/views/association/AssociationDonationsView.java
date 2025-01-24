package fr.treemanager.views.association;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.models.member.Member;
import fr.treemanager.models.member.Role;
import fr.treemanager.models.payment.Donation;
import fr.treemanager.models.payment.PaymentState;
import fr.treemanager.views.AbstractAssociationView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class AssociationDonationsView extends AbstractAssociationView implements Initializable {
    List<Donation> donations;

    @FXML
    private TextField amountText;

    @FXML
    private ComboBox entityComboBox;

    @FXML
    private ScrollPane donationsScrollPane;

    @FXML
    private Button deleteButton, askButton, emulatorButton;

    @FXML
    private ActionEvent onDelete, onAsked, toEmulator;

    public AssociationDonationsView (AssociationController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.donations = controller.getDonations();
        List<String> donatorNames = new ArrayList<>(controller.getDonations().stream()
                .map(donator -> donator.getName())
                .toList());
        donatorNames.add("Nouveau Donateur");
        entityComboBox.getItems().addAll(donatorNames);

        VBox donationsVBox = new VBox(10);
        for (Donation donation : donations) {
            HBox hBox = new HBox(10);
            Text donationText = new Text("Donation: " + donation.getDescription() + ", Amount: " + donation.getAmount() + ", State: " + donation.getState());

            if (donation.getState() == PaymentState.PENDING) {
                Button deleteButton = new Button("Supprimer");
                deleteButton.setOnAction(e -> {
                    controller.getDonations().remove(donation);
                    donationsVBox.getChildren().remove(hBox);
                });
                hBox.getChildren().add(deleteButton);
            }

            hBox.getChildren().add(donationText);
            donationsVBox.getChildren().add(hBox);
        }

        donationsScrollPane.setContent(donationsVBox);
    }

    public void onDelete(ActionEvent event) {
        // TODO
    }

    public void onAsked(ActionEvent event) {
        String selectedDonator = (String) entityComboBox.getSelectionModel().getSelectedItem();
        String amountTextValue = amountText.getText();
        double amount;

        try {
            amount = Double.parseDouble(amountTextValue);
            if (amount <= 0) {
                throw new NumberFormatException("Amount must be greater than 0");
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Amount");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid amount greater than 0.");
            alert.showAndWait();
            return;
        }
        if ("Nouveau Donateur".equals(selectedDonator)) {
            showNewDonatorPopup(amount);
        } else {
            UUID donatorId = controller.getDonations().stream()
                    .filter(donator -> donator.getName().equals(selectedDonator))
                    .map(donator -> donator.getId())
                    .findFirst()
                    .get();
            Donation donation = new Donation("Donation " , amount, selectedDonator, donatorId);
            controller.getDonations().add(donation);
        }
    }

    private void showNewDonatorPopup(double amount) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nouveau Donateur");
        dialog.setHeaderText("Créer un nouveau donateur");
        dialog.setContentText("Nom du donateur:");

        dialog.showAndWait().ifPresent(name -> {
            // Handle the creation of the new donator
            UUID newDonatorId = UUID.randomUUID();
            Donation newDonator = new Donation("Description", amount, name, newDonatorId);
            controller.getDonations().add(newDonator);
            entityComboBox.getItems().add(name);
        });
    }

    public void toEmulator(ActionEvent event) {
        List<String> donatorNames = controller.getDonations().stream()
                .map(Donation::getName)
                .distinct()
                .toList();

        if (donatorNames.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pas de donateur");
            alert.setHeaderText(null);
            alert.setContentText("Il n'y a pas de donateur disponible.");
            alert.showAndWait();
            return;
        }

        Stage popupStage = new Stage();
        popupStage.setTitle("Selectionner donateur");

        VBox vBox = new VBox(10);
        ComboBox<String> donatorComboBox = new ComboBox<>();
        donatorComboBox.getItems().addAll(donatorNames);
        donatorComboBox.setPromptText("Selectionner un donateur");

        Button showDonationsButton = new Button("Montrer les dons");
        showDonationsButton.setOnAction(ev -> {
            String selectedDonator = donatorComboBox.getSelectionModel().getSelectedItem();
            if (selectedDonator == null || selectedDonator.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Pas de donateur selectionné");
                alert.setHeaderText(null);
                alert.setContentText("Veuiillez sélectionner un donateur.");
                alert.showAndWait();
                return;
            }

            List<Donation> donatorDonations = controller.getDonations().stream()
                    .filter(donation -> donation.getName().equals(selectedDonator))
                    .toList();

            if (donatorDonations.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pas de dons");
                alert.setHeaderText(null);
                alert.setContentText("Ce donateur n'a pas de dons.");
                alert.showAndWait();
                return;
            }

            VBox donationsVBox = new VBox(10);
            for (Donation donation : donatorDonations) {
                if (donation.getState() == PaymentState.PAID || donation.getState() == PaymentState.CANCELED) {
                    continue;
                }
                HBox hBox = new HBox(10);
                Text donationText = new Text("Donation: " + donation.getDescription() + ", Amount: " + donation.getAmount());

                Button acceptButton = new Button("Accept");
                acceptButton.setOnAction(e -> {
                    controller.processDonation(donation);
                    donationsVBox.getChildren().remove(hBox);
                });

                Button denyButton = new Button("Deny");
                denyButton.setOnAction(e -> {
                    donation.deny();
                    donationsVBox.getChildren().remove(hBox);
                });

                hBox.getChildren().addAll(donationText, acceptButton, denyButton);
                donationsVBox.getChildren().add(hBox);
            }

            ScrollPane scrollPane = new ScrollPane(donationsVBox);
            Scene scene = new Scene(scrollPane, 400, 300);
            popupStage.setScene(scene);
            popupStage.show();
        });

        vBox.getChildren().addAll(donatorComboBox, showDonationsButton);
        Scene scene = new Scene(vBox, 300, 200);
        popupStage.setScene(scene);
        popupStage.show();
    }

    private HBox createDonationHbox(Donation donation){
        HBox hBox = new HBox();

        String name = donation.getName();
        String amount = donation.getAmount() + " €";
        String state = donation.getState().toString();

        Text firstNameText = new Text(firstName);
        firstNameText.setWrappingWidth(100);
        Text lastNameText = new Text(lastName);
        lastNameText.setWrappingWidth(100);
        Text roleText = new Text(isPresident ? "Président" : "Membre");
        roleText.setWrappingWidth(100);

        Button supprimerButton = new Button("Supprimer");
        supprimerButton.setOnAction(event -> {
            controller.removeMember(member);
            initialize(null, null);
        });

        supprimerButton.setPrefWidth(100);

        hBox.getChildren().addAll(firstNameText, lastNameText, roleText, supprimerButton);

        return hBox;
    }
}
