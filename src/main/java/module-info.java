module fr.treemanager.treemanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.treemanager to javafx.fxml;
    exports fr.treemanager;
}