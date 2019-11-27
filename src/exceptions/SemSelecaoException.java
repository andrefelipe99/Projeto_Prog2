package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SemSelecaoException extends Exception {

    public void erro() {
        Alert alerta = new Alert(AlertType.WARNING);
        alerta.setTitle("AVISO");
        alerta.setHeaderText("Nada selecionado!");
        alerta.setContentText("Selecione algo na tabela!");
        alerta.show();
    }
}
