package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ForaDoHorarioException extends Exception {
	
	public void erro() {
		Alert alerta = new Alert(AlertType.WARNING);
		alerta.setTitle("Aviso");
		alerta.setHeaderText("Fora do Horário da Consulta!");
		alerta.setContentText("Paciente ainda não foi atendido. A consulta está marcada para mais tarde!");
		alerta.showAndWait();
	}
}	
