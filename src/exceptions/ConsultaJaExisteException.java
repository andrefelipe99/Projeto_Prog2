package exceptions;

import javafx.scene.control.Alert;

public class ConsultaJaExisteException extends Exception {

    public void erro() {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Erro");
        alerta.setHeaderText("Consulta ja existente!");
        alerta.setContentText("Ja existe uma consulta marcada para este horario ou o ID ja foi cadastro em outra consulta!");
        alerta.showAndWait();
    }
}
