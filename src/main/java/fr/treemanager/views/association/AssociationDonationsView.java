package fr.treemanager.views.association;

import fr.treemanager.controllers.association.AssociationController;
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
    private HBox selectedHBox = null;
    private int selectedIndex = -1;

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
                .distinct()
                .toList());
        donatorNames.add("Nouveau donateur");
        entityComboBox.getItems().clear();
        entityComboBox.getItems().addAll(donatorNames);

        VBox donationsVBox = new VBox();
        for (Donation donation : donations) {
            HBox hBox = createDonationHbox(donation);
            donationsVBox.getChildren().add(hBox);
        }

        donationsScrollPane.setContent(donationsVBox);
        donationsScrollPane.setPickOnBounds(false);
    }

    public void onDelete(ActionEvent event) {
        if (selectedIndex != -1) {
            Donation selectedDonation = donations.get(selectedIndex);
            if (selectedDonation.getState() == PaymentState.PENDING) {
                controller.getDonations().remove(selectedDonation);
                initialize(null, null);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Seuls les dons en attente peuvent être supprimés.");
                alert.showAndWait();
            }
        }
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
        if ("Nouveau donateur".equals(selectedDonator)) {
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
        initialize(null, null);
    }

    private void showNewDonatorPopup(double amount) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nouveau Donateur");
        dialog.setHeaderText("Créer un nouveau donateur");
        dialog.setContentText("Nom du donateur:");

        dialog.showAndWait().ifPresent(name -> {
            // Handle the creation of the new donator
            boolean nameExists = controller.getDonations().stream()
                    .anyMatch(donation -> donation.getName().equalsIgnoreCase(name));

            if (nameExists) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Nom du donateur existant");
                alert.setHeaderText(null);
                alert.setContentText("Un donateur avec ce nom existe déjà. Veuillez choisir un autre nom.");
                alert.showAndWait();
            } else {
                // Handle the creation of the new donator
                UUID newDonatorId = UUID.randomUUID();
                Donation newDonation = new Donation("Description", amount, name, newDonatorId);
                controller.getDonations().add(newDonation);
                entityComboBox.getItems().add(name);
            }
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
                Text donationText = new Text("Donner : " + donation.getAmount() + " € à l'association ");

                Button acceptButton = new Button("OUI");
                acceptButton.setOnAction(e -> {
                    controller.processDonation(donation);
                    donationsVBox.getChildren().remove(hBox);
                    initialize(null, null);
                    popupStage.close();
                });

                Button denyButton = new Button("NON");
                denyButton.setOnAction(e -> {
                    donation.deny();
                    donationsVBox.getChildren().remove(hBox);
                    initialize(null, null);
                    popupStage.close();
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
        initialize(null, null);
    }

    private HBox createDonationHbox(Donation donation){
        HBox hBox = new HBox(8);

        String name = donation.getName();
        String amount = donation.getAmount() + " €";
        String state = donation.getState().toString();

        Text donationText = new Text("Donation demandée à " + name + " de " + amount + " | " + state);

        hBox.getChildren().addAll(donationText);
        hBox.setOnMouseClicked(event -> toggleSelection(hBox));
        return hBox;
    }

    private void toggleSelection(HBox hBox) {
        if (selectedHBox != null) {
            selectedHBox.getStyleClass().remove("selected");
            selectedHBox.setStyle("");
        }
        if (selectedHBox == hBox) {
            selectedHBox = null;
            selectedIndex = -1;
        } else {
            hBox.getStyleClass().add("selected");
            hBox.setStyle("-fx-background-color: lightblue;");
            selectedHBox = hBox;
            selectedIndex = ((VBox) donationsScrollPane.getContent()).getChildren().indexOf(hBox);
        }
    }
}
