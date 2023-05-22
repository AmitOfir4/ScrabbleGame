module com.example.scrabblegamegit {
    requires javafx.controls;
    requires javafx.fxml;

    exports View;
    opens View to javafx.fxml;
}