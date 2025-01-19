module fr.treemanager.treemanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.treemanager.treemanager to javafx.fxml;
    exports fr.treemanager.treemanager;
}