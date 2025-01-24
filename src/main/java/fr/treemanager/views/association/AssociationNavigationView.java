package fr.treemanager.views.association;

import fr.treemanager.AssociationApp;
import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.views.AbstractAssociationView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssociationNavigationView implements Initializable {

    @FXML
    protected StackPane contentPane;

    @FXML
    private Button toHome, toManageMembers, toAddMember, toCreateTreeVisit, toDefrayals, toDonations, toBills, toVisitHistory, toTreeRanking, receiveNotification;

    @FXML
    private ActionEvent switchToHome, switchToManageMembers, switchToAddMember, switchToCreateTreeVisit, switchToDefrayals, switchToDonations, switchToBills, switchToVisitHistory, switchToTreeRanking;

    private final AssociationController associationController;

    public AssociationNavigationView() {
        this.associationController = new AssociationController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.switchToHome(null);
    }

    public void switchToHome(ActionEvent event){
        this.switchContent("association/home.fxml", new AssociationHomeView(this.associationController));
    }

    public void switchToManageMembers(ActionEvent event){
        this.switchContent("association/manageMember.fxml", new AssociationManageMemberView(this.associationController));
    }

    public void switchToAddMember(ActionEvent event){
        this.switchContent("association/addMember.fxml", new AssociationAddMemberView(this.associationController));
    }

    public void switchToCreateTreeVisit(ActionEvent event){
        this.switchContent("association/createTreeVisit.fxml", new AssociationCreateVisitView(this.associationController));
    }

    public void switchToDefrayals(ActionEvent event){

    }

    public void switchToDonations(ActionEvent event){
        this.switchContent("association/donations.fxml", new AssociationDonationsView(this.associationController));
    }

    public void switchToBills(ActionEvent event){

    }

    public void switchToVisitHistory(ActionEvent event){

    }

    public void switchToTreeRanking(ActionEvent event){

    }

    public void receiveNotification(ActionEvent event){

    }


    protected void switchContent(String filename, AbstractAssociationView viewController) {
        try {
            // load fxml file
            FXMLLoader fxml = new FXMLLoader(AssociationApp.class.getResource(filename));
            fxml.setController(viewController);

            contentPane.getChildren().clear();
            contentPane.getChildren().add(fxml.load());
        } catch (IOException exception) {
            Logger.getLogger(AssociationNavigationView.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

}
