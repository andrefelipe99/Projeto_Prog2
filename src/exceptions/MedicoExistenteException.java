package exceptions;

import javafx.scene.control.Alert;

public class MedicoExistenteException extends Exception{
    public void erro() {
    	Alert alerta = new Alert(Alert.AlertType.ERROR);
    	alerta.setTitle("Erro");
    	alerta.setHeaderText("Medico ja cadastrado!");
    	alerta.setContentText("Este medico ja foi cadastrado no hospital!");
    	alerta.showAndWait();
    }
}
