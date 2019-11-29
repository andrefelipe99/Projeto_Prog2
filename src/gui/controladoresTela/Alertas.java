package gui.controladoresTela;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Alertas {
   
	public static void avisoSelecao() {
   	 Alert alerta = new Alert(AlertType.WARNING);
        alerta.setTitle("AVISO");
        alerta.setHeaderText("Nada selecionado!");
        alerta.setContentText("Selecione algo na tabela!");
        alerta.showAndWait();
   }
	
	public static boolean alertaConfirmacaoProsseguir() {
	        Alert alerta = new Alert(AlertType.CONFIRMATION);
	        alerta.setTitle("Confirme");
	        alerta.setHeaderText("Deseja Prosseguir?");
	        alerta.setContentText("Pressione 'OK' para continuar ou 'Cancelar' para retornar!");
	        alerta.showAndWait();
	        ButtonType apertado = alerta.getResult();

	        if (apertado.getText().contentEquals("OK")) {
	            return true;
	        } else {
	            return false;
	        }
	 }
	 
	 public static void alertaRemocaoOK() {
	     Alert alerta = new Alert(AlertType.INFORMATION);
	     alerta.setTitle("Informacao");
	     alerta.setHeaderText("Remocao feita com sucesso!");
	     alerta.setContentText("Pressione 'OK' para retornar!");
	     alerta.showAndWait();
	 }

	 public static void avisoForaDeHorarioConsulta() {
	        Alert alerta = new Alert(AlertType.WARNING);
	        alerta.setTitle("Aviso");
	        alerta.setHeaderText("Fora do horario da consulta!");
	        alerta.setContentText("So e possivel salvar um diagnostico no horario da consulta. Tente novamente mais tarde!");
	        alerta.showAndWait();
	 }
	 
	 public static void erroPacienteExiste() {
	        Alert alerta = new Alert(Alert.AlertType.ERROR);
	        alerta.setTitle("Erro");
	        alerta.setHeaderText("Paciente ja cadastrado!");
	        alerta.setContentText("Este CPF ja foi cadastrado por outro paciente!");
	        alerta.showAndWait();
	 }
	 
	 public static void erroMedicoExiste() {
	        Alert alerta = new Alert(Alert.AlertType.ERROR);
	        alerta.setTitle("Erro");
	        alerta.setHeaderText("Medico ja cadastrado!");
	        alerta.setContentText("Este medico ja foi cadastrado no hospital!");
	        alerta.showAndWait();
	 }
	 
	 public static void erroJaCadastrado() {
	        Alert alerta = new Alert(Alert.AlertType.ERROR);
	        alerta.setTitle("Erro");
	        alerta.setHeaderText("CPF ja cadastrado!");
	        alerta.setContentText("Este CPF ja foi cadastrado no sistema!");
	        alerta.showAndWait();
	}
	 
	 public static void erroConsultaNaoAtendida() {
	        Alert alerta = new Alert(AlertType.WARNING);
	        alerta.setTitle("Aviso");
	        alerta.setHeaderText("Fora do Horario da consulta!");
	        alerta.setContentText("Paciente ainda nao foi atendido. A consulta esta marcada para mais tarde!");
	        alerta.showAndWait();
	 }
	 
	 public static void erroJaDiagnosticado() {
	        Alert alerta = new Alert(Alert.AlertType.ERROR);
	        alerta.setTitle("Erro");
	        alerta.setHeaderText("Diagnostico ja existe!");
	        alerta.setContentText("Esta consulta ja possui um diagnostico existente!");
	        alerta.showAndWait();
	 }
	 
	 public static void erroDadosInvalidos() {
	        Alert alerta = new Alert(Alert.AlertType.ERROR);
	        alerta.setTitle("Erro");
	        alerta.setHeaderText("Dados Inseridos Invalidos!");
	        alerta.setContentText("Revise as Informacoes!");
	        alerta.showAndWait();
	 }
	 
	 public static void erroConsultaExiste() {
	        Alert alerta = new Alert(Alert.AlertType.ERROR);
	        alerta.setTitle("Erro");
	        alerta.setHeaderText("Consulta ja existente!");
	        alerta.setContentText("Ja existe uma consulta marcada para este horario ou o ID ja foi cadastro em outra consulta!");
	        alerta.showAndWait();
	 }
	 
	 public static void alertaConfirmacaoOK() {
	     Alert alerta = new Alert(Alert.AlertType.INFORMATION);
	     alerta.setTitle("Informacao");
	     alerta.setHeaderText("Marcado com sucesso!");
	     alerta.setContentText("Pressione 'OK' para retornar!");
	     alerta.showAndWait();
	 }
	 
}
