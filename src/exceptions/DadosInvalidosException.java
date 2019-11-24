package exceptions;

import javafx.scene.control.Alert;

public class DadosInvalidosException extends Exception{
	public void erro() {
		Alert alerta = new Alert(Alert.AlertType.ERROR);
		alerta.setTitle("Erro");
		alerta.setHeaderText("Dados Inseridos Invalidos!");
		alerta.setContentText("Revise as Informacoes!");
		alerta.showAndWait();
	}
}
