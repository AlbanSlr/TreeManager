package fr.treemanager.views.Member;

import fr.treemanager.controllers.member.MemberController;
import fr.treemanager.models.member.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MemberConnexionView implements Initializable {

    @FXML
    private ComboBox<Member> memberDropdown;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private Member selectedMember;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Simuler des membres d'association
        List<Member> members = List.of(
                new Member("Alice", "Smith", null),
                new Member("Bob", "Johnson", null),
                new Member("Charlie", "Brown", null)
        );

        // Ajouter les membres au menu déroulant
        memberDropdown.getItems().addAll(members);

        // Gérer le clic sur le bouton "Se connecter"
        loginButton.setOnAction(event -> {
            selectedMember = memberDropdown.getSelectionModel().getSelectedItem();

            if (selectedMember != null) {
                this.selectedMember = memberDropdown.getSelectionModel().getSelectedItem();
                ((Stage) loginButton.getScene().getWindow()).close(); // Fermer la fenêtre de connexion
            } else {
                System.out.println("Connexion échouée !");
            }
        });
    }

    public Member getConnectedMember() {
        return selectedMember;
    }

}
