package ni.edu.uam.registro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuRegistroApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuRegistroApplication.class.getResource("menu-principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menú Principal - Registro");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}