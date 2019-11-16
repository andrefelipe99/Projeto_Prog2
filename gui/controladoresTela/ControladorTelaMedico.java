package controladoresTela;

import java.net.URL;
import java.util.ResourceBundle;

import tela.Tela;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ControladorTelaMedico implements Initializable{
	
	@FXML private TableView<?> tabelaConsultaMed;
	@FXML private TableColumn<?, ?> colunaPaciente;
    @FXML private TableColumn<?, ?> colunaHorario;
    @FXML private Label campoMedIdade;
    @FXML private Label campoMedAvisos;
    @FXML private Button botaoDiagnostico;
    @FXML private Label campoMedHora;
    @FXML private Button botaoSair;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		botaoSair.setOnMouseClicked((MouseEvent e)->{
			Tela.fechar();
		});
		botaoSair.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				Tela.fechar();
			}
		});
		
	}
    
    public void atualizarTabela() {
    	
    }
    
}
