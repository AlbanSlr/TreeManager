package fr.treemanager.views.Member;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.treemanager.controllers.member.MemberController;
import fr.treemanager.models.Municipality;
import fr.treemanager.models.association.Association;
import fr.treemanager.models.member.Member;
import fr.treemanager.models.tree.Tree;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MemberConnexionView implements Initializable {
    private Association association;
    private List<Member> members;

    @FXML
    private ComboBox<Member> memberDropdown;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private Member selectedMember;

    public MemberConnexionView() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.association = mapper.readValue(new File("./association.json"), Association.class);
        } catch (Exception e) {

            System.out.println("Error while reading association from file, creating a new one : " + e);

            this.association = new Association("lez arbres", new Municipality(Tree.loadTreesFromCSV()));
        }
        this.members = this.association.getMembers();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
