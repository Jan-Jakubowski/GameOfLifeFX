module com.example.gameoflife {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.jjakubowski.gameOfLife to javafx.fxml;
    exports com.jjakubowski.gameOfLife;
}