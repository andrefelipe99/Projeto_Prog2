
package gui.controladoresTela;

import controladores.Fachada;
import exceptions.DadosInvalidosException;
import exceptions.MedicoExistenteException;
import gui.tela.GerenciadorHospitalAPP;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.Medico;

public class ControladorTelaCadastroMedico {
    @FXML private TextField txtNome;
    @FXML private TextField txtCpf;
    @FXML private TextField txtIdade;
    @FXML private TextField txtArea;
    @FXML private TextField txtCrm;
    @FXML private PasswordField txtSenha;
    private Fachada fachada = Fachada.getInstance();

    @FXML
    void cadastrar(){
    	int idade = -1;
    	if(!txtIdade.getText().isEmpty()) {
    		idade = Integer.parseInt(txtIdade.getText());
    	}
        String nome = this.txtNome.getText();
        String cpf = this.txtCpf.getText();
        String senha = this.txtSenha.getText();
        String area = this.txtArea.getText();
        String crm = this.txtCrm.getText();


        try {
        	if(!nome.isEmpty() && !cpf.isEmpty() && !senha.isEmpty() && !area.isEmpty() && !crm.isEmpty()) {
        			Medico medico = new Medico(crm,area,senha,nome,idade,cpf);
                	fachada.cadastrarMedico(medico);
                	alertaConfirmacaoOK();
                    voltar();
        		}
        }catch(IOException ioe){
        	ioe.printStackTrace();
        } catch (DadosInvalidosException e) {
        	Alert alerta = new Alert(Alert.AlertType.ERROR);
        	alerta.setTitle("Erro");
        	alerta.setHeaderText("Dados Inseridos Ivalidos!");
        	alerta.setContentText("Revise as Informacoes!");
        	alerta.showAndWait();
		} catch (MedicoExistenteException e) {
			Alert alerta = new Alert(Alert.AlertType.ERROR);
	    	alerta.setTitle("Erro");
	    	alerta.setHeaderText("Medico Já Cadastrado!");
	    	alerta.setContentText("Este Médico já foi cadastrado no hospital!");
	    	alerta.showAndWait();
		}
        		

        
        
    }

    @FXML
    void voltar() throws IOException{
            GerenciadorHospitalAPP.getStage().close();
            GerenciadorHospitalAPP adm = new GerenciadorHospitalAPP();
            adm.start(new Stage(), "/gui/fxmlAdmin/TelaAdmin.fxml","Administrador");
    }
    
        public void alertaConfirmacaoOK() {
    	Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    	alerta.setTitle("Informação");
    	alerta.setHeaderText("Salvo com Sucesso!");
    	alerta.setContentText("Pressione 'OK' para retornar!");
    	alerta.showAndWait();
    }
    
}
