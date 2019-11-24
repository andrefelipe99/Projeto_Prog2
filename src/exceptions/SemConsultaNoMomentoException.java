package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SemConsultaNoMomentoException extends Exception {
	public void erro() {
		Alert alerta = new Alert(AlertType.WARNING);
    	alerta.setTitle("Aviso");
    	alerta.setHeaderText("Fora do horario da consulta!");
    	alerta.setContentText("So e possivel salvar um diagnostico no horario da consulta. Tente novamente mais tarde!");
    	alerta.show();
	}
}
