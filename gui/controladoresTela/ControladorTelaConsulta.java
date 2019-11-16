package controladoresTela;

import java.net.URL;
import java.util.ResourceBundle;

import tela.Tela;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControladorTelaConsulta implements Initializable{
	 
	@FXML private TableColumn<?, ?> colunaMedPast;
    @FXML private TableColumn<?, ?> colunaDataPast;
    @FXML private Button botaoDiagnostico;
    @FXML private Button botaoVoltar;
    @FXML private TableColumn<?, ?> colunaMedicoPres;
    @FXML private TableColumn<?, ?> colunaDataPres;
    @FXML private Button botaoMarcaConsulta;
    @FXML private Button botaoCancelaConsulta;
    @FXML private Label textoAvisoConsulta;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	botaoVoltar.setOnMouseClicked((MouseEvent e)->{
			Tela.getStage().close();
	    	Tela tela = new Tela();
			try {
				tela.start(new Stage(), "/fxmlAtendente/TelaAtendente.fxml", "Atendente");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    
		});
		botaoVoltar.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				Tela.getStage().close();
				Tela tela = new Tela();
				try {
					tela.start(new Stage(), "/fxmlAtendente/TelaAtendente.fxml", "Atendente");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
	}
    
    
}
