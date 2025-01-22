package fr.treemanager.views;

import fr.treemanager.views.association.NavigationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AbstractNavigationView extends NavigationController implements Initializable {

    @FXML
    private Button toHome, toManageMembers, toAddMember, toCreateTreeVisit, toDefrayals, toDonations, toBills, toVisitHistory, toTreeRanking;

    @FXML
    ActionEvent switchToHome, switchToManageMembers, switchToAddMember, switchToCreateTreeVisit, switchToDefrayals, switchToDonations, switchToBills, switchToVisitHistory, switchToTreeRanking;

    public AbstractNavigationView() {}

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.switchToHome(null);
    }

    public void switchToHome(ActionEvent event){
        this.switchContent("association/home.fxml");
    }

    public void switchToManageMembers(ActionEvent event){
        this.switchContent("association/manageMember.fxml");
    }

    public void switchToAddMember(ActionEvent event){
        this.switchContent("association/addMember.fxml");
    }

    public void switchToCreateTreeVisit(ActionEvent event){
        this.switchContent("association/createTreeVisit.fxml");
    }

    public void switchToDefrayals(ActionEvent event){
        this.switchContent("association/defrayals.fxml");
    }


    public void switchToDonations(ActionEvent event){
        this.switchContent("association/donations.fxml");
    }

    public void switchToBills(ActionEvent event){
        this.switchContent("association/bills.fxml");
    }

    public void switchToVisitHistory(ActionEvent event){
        this.switchContent("association/visitHistory.fxml");
    }

    public void switchToTreeRanking(ActionEvent event){
        this.switchContent("association/treeRanking.fxml");
    }
}
