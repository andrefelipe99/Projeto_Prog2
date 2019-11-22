package gui.controladoresTela;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import controladores.Fachada;
import exceptions.SemConsultaNoMomentoException;
import gui.tela.GerenciadorHospitalAPP;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import negocio.Consulta;
import negocio.Diagnostico;

public class ControladorTelaDiagnostico implements Initializable{
    @FXML private Button botaoVoltar;
    @FXML private Button botaoSalvar;
    @FXML private TextArea areaTextoDiag;
    @FXML private TextArea areaTextoMedicamentos;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		botaoVoltar.setOnMouseClicked((MouseEvent e)->{
			retornarTelaMedico();
		});
		botaoVoltar.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				retornarTelaMedico();
			}
		});
		
		botaoSalvar.setOnMouseClicked((MouseEvent e)->{
			try {
				cadastrarDiagnostico();
			} catch (SemConsultaNoMomentoException e1) {
				Alert alerta = new Alert(AlertType.WARNING);
		    	alerta.setTitle("Aviso");
		    	alerta.setHeaderText("Fora do Horario da Consulta!");
		    	alerta.setContentText("Só é possivel salvar um diagnóstico no horário da consulta. Tente novamente mais tarde!");
		    	alerta.show();
			}

		});
		botaoSalvar.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				try {
					cadastrarDiagnostico();
				} catch (SemConsultaNoMomentoException e1) {
					Alert alerta = new Alert(AlertType.WARNING);
			    	alerta.setTitle("Aviso");
			    	alerta.setHeaderText("Fora do Horario da Consulta!");
			    	alerta.setContentText("Só é possivel salvar um diagnóstico no horário da consulta. Tente novamente mais tarde!");
			    	alerta.show();
				}
				
			}
		});
	}
    
    public void cadastrarDiagnostico() throws SemConsultaNoMomentoException {
    	Consulta c = Fachada.getInstance().consultaDoMomento(LocalDateTime.now(), Fachada.getInstance().buscarMedico(medicoLogado()));
    	
    	if(c == null) {
    		throw new SemConsultaNoMomentoException();
    	}
    	Diagnostico d = c.getDiagnostico();
    	
    	d.setDescricao(areaTextoDiag.getText());
    	d.setMedicamentos(areaTextoMedicamentos.getText());
    	
    	Fachada.getInstance().cadastrarDiagnostico(d, c);
    	
    	alertaConfirmacaoOK();
    }
    
    public void alertaConfirmacaoOK() {
    	Alert alerta = new Alert(AlertType.INFORMATION);
    	alerta.setTitle("Informação");
    	alerta.setHeaderText("Salvo com Sucesso!");
    	alerta.setContentText("Pressione 'OK' para retornar!");
    	alerta.show();
    }
    
    public String medicoLogado() {
    	String medicoLogado = new String();
    	
    	try {
			BufferedReader leitor = new BufferedReader(new FileReader("dados/arquivos/medicoLogado.txt"));
			
			if(leitor.ready()) {
				medicoLogado = leitor.readLine();
			}
			
			
			leitor.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		return medicoLogado;
    }
    
    public void retornarTelaMedico() {
    	GerenciadorHospitalAPP.getStage().close();
		GerenciadorHospitalAPP novaTela = new GerenciadorHospitalAPP();
		
		try {
			novaTela.start(new Stage(), "/gui/fxmlMedico/TelaMedico.fxml", "M�dico");
		} catch (IOException e1) {
			
		}
    }
}
