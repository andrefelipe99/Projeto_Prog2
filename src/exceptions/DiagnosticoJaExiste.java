package exceptions;

import javafx.scene.control.Alert;

public class DiagnosticoJaExiste extends Exception{
	public void erro() {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Diagnostico ja existe!");
        alerta.setContentText("Esta consulta ja possui um diagnostico existente!");
        alerta.showAndWait();
    }
}
