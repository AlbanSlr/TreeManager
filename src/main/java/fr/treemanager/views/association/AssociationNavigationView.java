package fr.treemanager.views.association;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.views.AbstractNavigationView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AssociationNavigationView extends AbstractNavigationView implements Initializable {

    @FXML
    private Button toHome, toManageMembers, toAddMember, toCreateTreeVisit, toDefrayals, toDonations, toBills, toVisitHistory, toTreeRanking, receiveNotification;

    @FXML
    ActionEvent switchToHome, switchToManageMembers, switchToAddMember, switchToCreateTreeVisit, switchToDefrayals, switchToDonations, switchToBills, switchToVisitHistory, switchToTreeRanking;

    private final AssociationController associationController;

    public AssociationNavigationView() {
        this.associationController = new AssociationController();
    }

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

    public void receiveNotification(ActionEvent event){

    }
}
