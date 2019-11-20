<<<<<<< HEAD
package controladoresTela;

import java.net.URL;
import java.util.ResourceBundle;

import tela.GerenciadorHospitalAPP;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
		
	}
    
  
    
    

	
}
=======
package controladoresTela;

import java.net.URL;
import java.util.ResourceBundle;

import tela.Tela;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
			Tela.fechar();
		});
		botaoSair.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				Tela.fechar();
			}
		});
		
	}
    
  
    
    

	
}
>>>>>>> e227eebdf700acaa08823ef7812489f34b0c2e7f
