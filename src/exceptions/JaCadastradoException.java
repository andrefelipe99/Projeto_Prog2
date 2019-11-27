package exceptions;

import javafx.scene.control.Alert;

public class JaCadastradoException extends Exception {
	  public void erro() {
	        Alert alerta = new Alert(Alert.AlertType.ERROR);
	        alerta.setTitle("Erro");
	        alerta.setHeaderText("CPF ja cadastrado!");
	        alerta.setContentText("Este CPF ja foi cadastrado no sistema!");
	        alerta.showAndWait();
	    }
}
