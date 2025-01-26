package fr.treemanager.views.association;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.models.member.Member;
import fr.treemanager.models.member.Role;
import fr.treemanager.views.AbstractAssociationView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AssociationManageMemberView extends AbstractAssociationView implements Initializable {
    private List<Member> members;

    @FXML
    private ScrollPane manageMemberScrollPane;

    public AssociationManageMemberView(AssociationController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.members = controller.getMembers();
        VBox vBox = new VBox();
        for (Member member : members) {
            HBox memberHbox = createMemberHbox(member);
            vBox.getChildren().add(memberHbox);
        }
        manageMemberScrollPane.setContent(vBox);
    }

    private HBox createMemberHbox(Member member){
        HBox hBox = new HBox(10);

        String firstName = member.getFirstName();
        String lastName = member.getLastName();
        boolean isPresident = member.getRole() == Role.PRESIDENT;

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
