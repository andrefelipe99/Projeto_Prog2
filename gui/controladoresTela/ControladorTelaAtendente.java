package controladoresTela;

import java.net.URL;
import java.util.ResourceBundle;

import tela.Tela;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import negocio.Paciente;

public class ControladorTelaAtendente implements Initializable{

	@FXML private TableColumn<Paciente, String> colunaNome;
	@FXML private TableColumn<Paciente, String> colunaCPF;
    @FXML private TextField barraPesquisa;
    @FXML private Label textoCPF;
	@FXML private Label textoNome;
    @FXML private Label textoIdade;
    @FXML private Label textoEndereco;
    @FXML private Label textoTelefone;
    @FXML private Button botaoHistorico;
    @FXML private Button botaoCadastrar;
    @FXML private Button botaoRemover;
    @FXML private Button botaoSair;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	botaoHistorico.setOnMouseClicked((MouseEvent e)->{
    		abrirTelaConsulta();
    	});
    	botaoHistorico.setOnKeyTyped((KeyEvent e)->{
    		if(e.getCode() == KeyCode.ENTER) {
    			abrirTelaConsulta();
    		}
    	});
    	
    	botaoSair.setOnMouseClicked((MouseEvent e)->{
			Tela.fechar();
		});
		botaoSair.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				Tela.fechar();
			}
		});
		
	}
    
    public void abrirTelaConsulta() {
		Tela.getStage().close();
    	Tela tela = new Tela();
		try {
			tela.start(new Stage(), "/fxmlAtendente/TelaConsulta.fxml", "Histórico do Paciente");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }
    



}
