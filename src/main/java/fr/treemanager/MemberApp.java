package fr.treemanager;

import fr.treemanager.controllers.member.MemberController;
import fr.treemanager.views.Member.MemberConnexionView;
import fr.treemanager.views.Member.MemberNavigationView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
// Charger la page de connexion
        FXMLLoader connexionLoader = new FXMLLoader(MemberApp.class.getResource("member/connexion.fxml"));
        MemberConnexionView connexionView = new MemberConnexionView(); // Temporaire, sans contrôleur pour l'instant
        connexionLoader.setController(connexionView);
        Scene connexionScene = new Scene(connexionLoader.load(), 300, 200);

        // Créer une fenêtre modale pour la connexion
        Stage connexionStage = new Stage();
        connexionStage.setTitle("Connexion");
        connexionStage.setScene(connexionScene);
        connexionStage.initModality(Modality.APPLICATION_MODAL); // Modale
        connexionStage.setResizable(false);

        // Afficher le pop-up de connexion
        connexionStage.showAndWait();

        // Vérifier si un utilisateur est connecté
        if (connexionView.getConnectedMember() == null) {
            System.exit(0); // Fermer l'application si aucun utilisateur
        }

        // Charger la fenêtre principale avec le membre connecté
        FXMLLoader mainLoader = new FXMLLoader(MemberApp.class.getResource("member/layout.fxml"));
        MemberController controller = new MemberController(connexionView.getConnectedMember().getId());
        MemberNavigationView navigationView = new MemberNavigationView(controller);
        mainLoader.setController(navigationView);
        Scene scene = new Scene(mainLoader.load(), 900, 600);
        primaryStage.setTitle("Tree Manager - Member");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}