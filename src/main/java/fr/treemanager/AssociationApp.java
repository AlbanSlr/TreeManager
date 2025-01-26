package fr.treemanager;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.utils.FileWatcherService;
import fr.treemanager.views.association.AssociationNavigationView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class AssociationApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(AssociationApp.class.getResource("association/layout.fxml"));
        AssociationController controller = new AssociationController();
        fxmlLoader.setController(new AssociationNavigationView(controller));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Tree Manager - Association");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();

        stage.setOnCloseRequest(e ->
        {controller.save();});
    }

    public static void main(String[] args) {
        launch();
    }
}