module com.donner.mazerunner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.donner.mazerunner to javafx.fxml;
    exports com.donner.mazerunner;
}