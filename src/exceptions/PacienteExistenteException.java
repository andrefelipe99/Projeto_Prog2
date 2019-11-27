package exceptions;

import javafx.scene.control.Alert;

public class PacienteExistenteException extends Exception{
	
	public void erro() {
    	Alert alerta = new Alert(Alert.AlertType.ERROR);
    	alerta.setTitle("Erro");
    	alerta.setHeaderText("Paciente ja cadastrado!");
    	alerta.setContentText("Este CPF ja foi cadastrado por outro paciente!");
    	alerta.showAndWait();
    }
}
