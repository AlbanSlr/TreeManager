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
    private Button toMemberHome, toManageCotisations, toMemberVote, toMemberVisit, toConnexion,receiveNotification,updateDataButton, reloadAppButton;

    @FXML
    private ActionEvent switchToMemberHome, switchToManageCotisations, switchToMemberVote, switchToMemberVisit, switchToConnexion,updateData, reloadApp;

    private final MemberController memberController;


    public MemberNavigationView(MemberController controller) {
        this.memberController = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.switchToMemberHome(null);
    }

    public void switchToMemberHome(ActionEvent event){
        this.switchContent("member/home.fxml", new MemberHomeView(this.memberController));
    }

    public void switchToManageCotisations(ActionEvent event){
        this.switchContent("member/manageCotisations.fxml", new MemberManageCotisationsView(this.memberController));
    }

    public void switchToMemberVote(ActionEvent event){
        this.switchContent("member/memberVote.fxml", new MemberVoteView(this.memberController));
    }

    public void switchToMemberVisit(ActionEvent event){
        this.switchContent("member/memberVisit.fxml", new MemberVisitView(this.memberController));
    }

    public void switchToMemberOwnVisit(ActionEvent event){
        this.switchContent("member/memberOwnVisit.fxml", new MemberOwnVisitView(this.memberController));
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

    public void updateData(ActionEvent event){
        memberController.save();
    }

    public void reloadApp(ActionEvent event){
        memberController.reload();
        this.initialize(null, null);
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

