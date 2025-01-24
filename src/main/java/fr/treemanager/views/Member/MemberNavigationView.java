package fr.treemanager.views.Member;

import fr.treemanager.MemberApp;
import fr.treemanager.controllers.member.MemberController;
import fr.treemanager.models.association.Association;
import fr.treemanager.models.member.Member;
import fr.treemanager.models.member.Role;
import fr.treemanager.views.AbstractMemberView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberNavigationView implements Initializable {

    @FXML
    protected StackPane contentPane;

    @FXML
    private Button toMemberHome, toManageCotisations, toMemberVote, toMemberVisit, toConnexion,receiveNotification;

    @FXML
    private ActionEvent switchToMemberHome, switchToManageCotisations, switchToMemberVote, switchToMemberVisit, switchToConnexion;

    private final MemberController memberController;



    public MemberNavigationView(Member connectedMember) {
        this.memberController = new MemberController(connectedMember, new Association("ASSOTEST"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.switchToMemberHome(null);
    }

    public void switchToMemberHome(ActionEvent event){
        this.switchContent("member/memberVisit.fxml", new MemberHomeView(this.memberController));
    }

    public void switchToManageCotisations(ActionEvent event){
        this.switchContent("member/manageMember.fxml", new MemberManageCotisationsView(this.memberController));
    }

    public void switchToMemberVote(ActionEvent event){
        this.switchContent("member/memberVote.fxml", new MemberVoteView(this.memberController));
    }

    public void switchToMemberVisit(ActionEvent event){
        this.switchContent("member/memberVisit.fxml", new MemberVisitView(this.memberController));
    }

    public void switchToConnexion(ActionEvent event) {
        try {
            // Charger la page de connexion
            FXMLLoader connexionLoader = new FXMLLoader(MemberApp.class.getResource("member/connexion.fxml"));
            MemberConnexionView connexionView = new MemberConnexionView();
            connexionLoader.setController(connexionView);
            Scene connexionScene = new Scene(connexionLoader.load(), 300, 200);

            // Réinitialiser la fenêtre principale avec la scène de connexion
            Stage primaryStage = (Stage) contentPane.getScene().getWindow(); // Récupère le primaryStage
            primaryStage.setScene(connexionScene);

            // Optionnel : Mettre à jour le titre
            primaryStage.setTitle("Tree Manager - Connexion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveNotification(ActionEvent event){

    }


    protected void switchContent(String filename, AbstractMemberView viewController) {
        try {
            // load fxml file
            FXMLLoader fxml = new FXMLLoader(MemberApp.class.getResource(filename));
            fxml.setController(viewController);

            System.out.println("Switching to " + filename);

            contentPane.getChildren().clear();
            contentPane.getChildren().add(fxml.load());
        } catch (IOException exception) {
            Logger.getLogger(MemberNavigationView.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

}

