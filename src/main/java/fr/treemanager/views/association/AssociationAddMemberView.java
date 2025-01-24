package fr.treemanager.views.association;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.models.member.Member;
import fr.treemanager.models.member.Role;
import fr.treemanager.views.AbstractAssociationView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AssociationAddMemberView extends AbstractAssociationView implements Initializable {

    @FXML
    Button addMemberButton;

    @FXML
    TextField firstNameTF, lastNameTF;

    @FXML
    ActionEvent addMember;

    public AssociationAddMemberView(AssociationController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addMember(ActionEvent event) {
        String firstName = firstNameTF.getText();
        String lastName = lastNameTF.getText();
        controller.addMember(new Member(lastName, firstName, Role.MEMBER));
        System.out.println("Member added");
    }


}
