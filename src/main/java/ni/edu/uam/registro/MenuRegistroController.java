package ni.edu.uam.registro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuRegistroController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private void abrirRegistroCliente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registro-cliente.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Registro del Cliente");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void abrirSolicitudRegistro(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("solicitud-servicio.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Solicitud de Servicio");
        stage.setScene(new Scene(root));
        stage.show();
    }
}