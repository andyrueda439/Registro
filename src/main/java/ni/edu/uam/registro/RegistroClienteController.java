package ni.edu.uam.registro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Cliente {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private ComboBox<String> cbTipoCliente;
    @FXML
    private TextField txtDocumento;
    @FXML
    private TextField txtDirectorio;
    @FXML
    private Button btnSeleccionarDocumento;
    @FXML

    private Button btnSeleccionarDirectorio;
    @FXML
    private Button btnCerrar;

    @FXML
    public void initialize() {
        cbTipoCliente.getItems().addAll("Particular", "Empresarial", "Gobierno");
    }

    @FXML
    private void seleccionarDocumento() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar Documento de Identificación");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivo PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Imagen PNG", "*.png"),
                new FileChooser.ExtensionFilter("Imagen JPG", "*.jpg")
        );
        File f = fc.showOpenDialog(btnSeleccionarDocumento.getScene().getWindow());
        if (f != null) {
            txtDocumento.setText(f.getAbsolutePath());
        }
    }

    @FXML
    private void seleccionarDirectorio() {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccionar Directorio del Cliente");
        dc.setInitialDirectory(new File("C:\\"));
        File f = dc.showDialog(btnSeleccionarDirectorio.getScene().getWindow());
        if (f != null) {
            txtDirectorio.setText(f.getAbsolutePath());

        }
    }

    @FXML
    private void guardarCliente() {
        if (!validarFormulario()) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cliente guardado");
        alert.setHeaderText("Guardado exitoso");
        alert.setContentText("El cliente " + txtNombre.getText() + " se ha guardado correctamente.");
        alert.showAndWait();
    }

    @FXML
    private void crearSolicitud(ActionEvent event) {
        if (!validarFormulario()) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("solicitud-servicio.fxml"));
            Parent root = loader.load();

            SolicitudServicioController controller = loader.getController();
            controller.precargarCliente(txtNombre.getText(), txtCorreo.getText(), cbTipoCliente.getValue());

            Stage stage = new Stage();
            stage.setTitle("Solicitud de Servicio");
            stage.setScene(new Scene(root));

            stage.show();
        } catch (IOException e) {
            mostrarError("No se pudo abrir la ventana de solicitud de servicio.");
        }
    }

    @FXML
    private void limpiarCliente() {
        txtNombre.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        cbTipoCliente.setValue(null);
        txtDocumento.clear();
        txtDirectorio.clear();
    }

    @FXML
    private void cerrarCliente() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    private boolean validarFormulario() {
        StringBuilder errores = new StringBuilder();

        if (txtNombre.getText().trim().isEmpty()) {
            errores.append("- El nombre es obligatorio.\n");
        }
        if (txtCorreo.getText().trim().isEmpty()) {
            errores.append("- El correo es obligatorio.\n");
        } else if (!txtCorreo.getText().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            errores.append("- El correo no tiene un formato válido.\n");

        }
        if (txtTelefono.getText().trim().isEmpty()) {
            errores.append("- El teléfono es obligatorio.\n");
        } else if (!txtTelefono.getText().matches("\\d{4,15}")) {
            errores.append("- El teléfono debe contener solo números.\n");
        }
        if (cbTipoCliente.getValue() == null) {
            errores.append("- Debe seleccionar un tipo de cliente.\n");
        }
        if (txtDocumento.getText().trim().isEmpty()) {
            errores.append("- Debe seleccionar el documento de identificación.\n");
        }
        if (txtDirectorio.getText().trim().isEmpty()) {
            errores.append("- Debe seleccionar el directorio del cliente.\n");
        }

        if (errores.length() > 0) {
            mostrarError(errores.toString());
            return false;
        }
        return true;
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de validación");
        alert.setHeaderText("Revise los siguientes campos:");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
