package fr.treemanager;

import fr.treemanager.controllers.association.AssociationController;
import fr.treemanager.controllers.municipality.MunicipalityController;
import fr.treemanager.models.Municipality;
import fr.treemanager.views.association.AssociationNavigationView;
import fr.treemanager.views.municipality.MunicipalityNavigationView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MunicipalityApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MunicipalityApp.class.getResource("municipality/layout.fxml"));
        MunicipalityController controller = new MunicipalityController();
        fxmlLoader.setController(new MunicipalityNavigationView(controller));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Tree Manager - Municipality");
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
