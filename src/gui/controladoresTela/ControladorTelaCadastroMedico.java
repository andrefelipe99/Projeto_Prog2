
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
    	String cpf = "";
    	String crm = "";
    	if(!txtIdade.getText().isEmpty() && txtIdade.getText().matches("^[0-9]*$")) {
    		idade = Integer.parseInt(txtIdade.getText());
    	}
    	if(txtCpf.getText().matches("^[0-9]*$") && txtCpf.getText().length() == 11) {
			cpf = this.txtCpf.getText();
		}	
    	if(txtCrm.getText().matches("^[0-9]*$")) {
			crm = this.txtCrm.getText();
		}	
        String nome = this.txtNome.getText();
        String senha = this.txtSenha.getText();
        String area = this.txtArea.getText();

        try {
        		Medico medico = new Medico(crm,area,senha,nome,idade,cpf);
                fachada.cadastrarMedico(medico);
                alertaConfirmacaoOK();
                voltar();
        }catch (DadosInvalidosException e) {
        	e.erro();
		}catch (MedicoExistenteException e) {
			e.erro();
		}catch(IOException ioe){
        	ioe.printStackTrace();
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
    	alerta.setTitle("Informacao");
    	alerta.setHeaderText("Salvo com sucesso!");
    	alerta.setContentText("Pressione 'OK' para retornar!");
    	alerta.showAndWait();
    }

}
