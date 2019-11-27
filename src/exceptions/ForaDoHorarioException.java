package exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ForaDoHorarioException extends Exception {
	
	public void erro() {
		Alert alerta = new Alert(AlertType.WARNING);
		alerta.setTitle("Aviso");
		alerta.setHeaderText("Fora do Hor�rio da Consulta!");
		alerta.setContentText("Paciente ainda n�o foi atendido. A consulta est� marcada para mais tarde!");
		alerta.showAndWait();
	}
}	
