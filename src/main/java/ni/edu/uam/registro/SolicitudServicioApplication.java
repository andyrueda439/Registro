package ni.edu.uam.registro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SolicitudServicioApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SolicitudServicioApplication.class.getResource("solicitud-servicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Solicitud de Servicio");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}