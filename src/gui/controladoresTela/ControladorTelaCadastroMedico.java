
package gui.controladoresTela;

import controladores.Fachada;
import gui.tela.GerenciadorHospitalAPP;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.Medico;

public class ControladorTelaCadastroMedico {
     @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtArea;

    @FXML
    private TextField txtCrm;

    @FXML
    private PasswordField txtSenha;

        
    private Fachada fachada = Fachada.getInstance();
    
    @FXML
    void Cadastrar(){
        int idade = Integer.parseInt(txtIdade.getText());
        String nome = this.txtNome.getText();
        String cpf = this.txtCpf.getText();
        String senha = this.txtSenha.getText();
        String area = this.txtArea.getText();
        String crm = this.txtCrm.getText();
        
        
        try{            
                        Medico medico = new Medico(crm,area,senha,nome,idade,cpf);
                            if(!fachada.MedicoExiste(medico)){
                                    fachada.cadastrarMedico(medico);
                            } 
                            voltar();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    void voltar() throws IOException{
            GerenciadorHospitalAPP.getStage().close();
            GerenciadorHospitalAPP adm = new GerenciadorHospitalAPP();
            adm.start(new Stage(), "/gui/fxmlAdmin/TelaAdmin.fxml","Administrador");
    }
}