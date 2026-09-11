package ni.edu.uam.registro;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SolicitudServicioController {
    @FXML
    private TextField txtCliente;
    @FXML
    private TextField txtCorreoCliente;
    @FXML
    private TextField txtTipoCliente;
    @FXML
    private TextField txtAsunto;
    @FXML
    private ComboBox<String> cbTipoServicio;
    @FXML
    private RadioButton rbAlta;
    @FXML
    private RadioButton rbMedia;
    @FXML
    private RadioButton rbBaja;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtArchivoAdjunto;

    @FXML
    private TextField txtCarpetaEvidencias;
    @FXML
    private Button btnSeleccionarArchivo;
    @FXML
    private Button btnSeleccionarCarpeta;
    @FXML
    private Button btnCerrar;

    private ToggleGroup grupoPrioridad;

    @FXML
    public void initialize() {
        cbTipoServicio.getItems().addAll("Soporte técnico", "Mantenimiento", "Instalación", "Consultoría");

        grupoPrioridad = new ToggleGroup();
        rbAlta.setToggleGroup(grupoPrioridad);
        rbMedia.setToggleGroup(grupoPrioridad);
        rbBaja.setToggleGroup(grupoPrioridad);
    }

    public void precargarCliente(String nombre, String correo, String tipoCliente) {
        txtCliente.setText(nombre);
        txtCorreoCliente.setText(correo);
        txtTipoCliente.setText(tipoCliente);
    }

    @FXML
    private void seleccionarArchivoAdjunto() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar Archivo Adjunto");
        fc.getExtensionFilters().addAll(

                new FileChooser.ExtensionFilter("Archivo PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Documento Word", "*.docx"),
                new FileChooser.ExtensionFilter("Imagen", "*.png", "*.jpg")
        );
        File f = fc.showOpenDialog(btnSeleccionarArchivo.getScene().getWindow());
        if (f != null) {
            txtArchivoAdjunto.setText(f.getAbsolutePath());
        }
    }

    @FXML
    private void seleccionarCarpetaEvidencias() {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Seleccionar Carpeta de Evidencias");
        dc.setInitialDirectory(new File("C:\\"));
        File f = dc.showDialog(btnSeleccionarCarpeta.getScene().getWindow());
        if (f != null) {
            txtCarpetaEvidencias.setText(f.getAbsolutePath());
        }
    }

    @FXML
    private void guardarSolicitud() {
        if (!validarFormulario()) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Solicitud guardada");
        alert.setHeaderText("Guardado exitoso");
        alert.setContentText("La solicitud para " + txtCliente.getText() + " se ha guardado.");
        alert.showAndWait();

    }

    @FXML
    private void crearSolicitud() {
        if (!validarFormulario()) {
            return;
        }

        RadioButton seleccionado = (RadioButton) grupoPrioridad.getSelectedToggle();

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmación");
        confirmacion.setHeaderText("¿Confirmar creación de la solicitud?");
        confirmacion.setContentText("Cliente: " + txtCliente.getText() +
                "\nAsunto: " + txtAsunto.getText() +
                "\nPrioridad: " + seleccionado.getText());

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Solicitud creada");
                alert.setHeaderText("Éxito");
                alert.setContentText("La solicitud de servicio se ha creado correctamente.");
                alert.showAndWait();
            }
        });
    }
    @FXML
    private void limpiarSolicitud() {
        txtCliente.clear();
        txtCorreoCliente.clear();

        txtTipoCliente.clear();
        txtAsunto.clear();
        cbTipoServicio.setValue(null);
        grupoPrioridad.selectToggle(null);
        txtDescripcion.clear();
        txtArchivoAdjunto.clear();
        txtCarpetaEvidencias.clear();
    }

    @FXML
    private void cerrarSolicitud() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    private boolean validarFormulario() {
        StringBuilder errores = new StringBuilder();

        if (txtCliente.getText().trim().isEmpty()) {
            errores.append("- El nombre del cliente es obligatorio.\n");
        }
        if (txtCorreoCliente.getText().trim().isEmpty()) {
            errores.append("- El correo del cliente es obligatorio.\n");
        } else if (!txtCorreoCliente.getText().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            errores.append("- El correo no tiene un formato válido.\n");
        }
        if (txtAsunto.getText().trim().isEmpty()) {
            errores.append("- El asunto es obligatorio.\n");
        }
        if (cbTipoServicio.getValue() == null) {
            errores.append("- Debe seleccionar un tipo de servicio.\n");
        }

        if (grupoPrioridad.getSelectedToggle() == null) {
            errores.append("- Debe seleccionar una prioridad.\n");
        }
        if (txtDescripcion.getText().trim().isEmpty()) {
            errores.append("- La descripción del problema es obligatoria.\n");
        }
        if (txtArchivoAdjunto.getText().trim().isEmpty()) {
            errores.append("- Debe seleccionar un archivo adjunto.\n");
        }
        if (txtCarpetaEvidencias.getText().trim().isEmpty()) {
            errores.append("- Debe seleccionar la carpeta de evidencias.\n");
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