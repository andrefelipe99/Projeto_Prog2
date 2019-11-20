package gui.controladoresTela;

import java.net.URL;
import java.util.ResourceBundle;

import gui.tela.GerenciadorHospitalAPP;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControladorTelaAdmin implements Initializable{

    @FXML private Button botaoCadastrar;
    @FXML private Button botaoRemover;
    @FXML private Button botaoSair;
    @FXML private TableColumn<?, ?> colunaCRM;
    @FXML private TableColumn<?, ?> colunaNome;
    @FXML private TableColumn<?, ?> colunaEspecialidade;
    
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		botaoSair.setOnMouseClicked((MouseEvent e)->{
			GerenciadorHospitalAPP.fechar();
		});
		botaoSair.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				GerenciadorHospitalAPP.fechar();
			}
		});
                
                botaoCadastrar.setOnMouseClicked((MouseEvent e) ->{
                    try {
                        cadastro();
                    } catch (Exception ex) {
                        Logger.getLogger(ControladorTelaAdmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
		
	}
    
        public void cadastro() throws Exception{
            GerenciadorHospitalAPP.getStage().close();
            GerenciadorHospitalAPP adm = new GerenciadorHospitalAPP();
            adm.start(new Stage(), "/gui/fxmlAdmin/TelaCadastroMedico.fxml","Cadastro");
            
            
        }    
    

	
}
