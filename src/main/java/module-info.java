module ni.edu.uam.registro {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.registro to javafx.fxml;
    exports ni.edu.uam.registro;
}