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

public class MemberVisitView extends AbstractMemberView implements Initializable{
    @FXML
    private Text identite;

    @FXML
    private Button quitterAsso;

    @FXML
    private ActionEvent toNextExercize;

    public MemberVisitView(MemberController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        identite.setText("Member.getMember()");
    }

}
