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

public class MemberManageCotisationsView extends AbstractMemberView implements Initializable{
    @FXML
    private Text statusText;

    @FXML
    private Button subscribeButton;

    public MemberManageCotisationsView(MemberController controller) {
        super(controller);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Vérifie si le membre est déjà abonné
        if (controller.isSubscribed()) {
            statusText.setText("Vous avez déjà payé votre cotisation pour l'année en cours.");
            subscribeButton.setVisible(false); // Cache le bouton
        } else {
            statusText.setText("Vous n'avez pas encore payé votre cotisation.");
            subscribeButton.setVisible(true); // Affiche le bouton
        }
    }

    @FXML
    private void handleSubscribe(ActionEvent event) {
        try {
            // Appelle la méthode du contrôleur pour payer la cotisation
            controller.subscribe();
            statusText.setText("Merci, votre cotisation a bien été enregistrée !");
            subscribeButton.setVisible(false); // Cache le bouton après paiement
        } catch (IllegalArgumentException e) {
            // Gestion des erreurs éventuelles
            statusText.setText("Erreur : " + e.getMessage());
        }
    }


}
