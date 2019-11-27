package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ForaDoHorarioException extends Exception {

    public void erro() {
        Alert alerta = new Alert(AlertType.WARNING);
        alerta.setTitle("Aviso");
        alerta.setHeaderText("Fora do Horario da consulta!");
        alerta.setContentText("Paciente ainda nao foi atendido. A consulta esta marcada para mais tarde!");
        alerta.showAndWait();
    }
}
