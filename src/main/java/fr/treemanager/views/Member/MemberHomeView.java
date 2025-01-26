package fr.treemanager.views.Member;

import fr.treemanager.controllers.member.MemberController;
import fr.treemanager.models.member.Member;
import fr.treemanager.views.AbstractMemberView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MemberHomeView extends AbstractMemberView implements Initializable{

    private Member member;
    @FXML
    private Text identite;

    @FXML
    private Button quitterAsso;

    public MemberHomeView(MemberController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.member = controller.getMember();
        identite.setText("Firstname : "+member.getFirstName()+"\nLastname :"+member.getLastName());
    }

    @FXML
    private void MemberLeaveAsso(ActionEvent event){
        controller.unsubscribe();
        System.out.println("Vous avez quitté l'association !");
    }


}
