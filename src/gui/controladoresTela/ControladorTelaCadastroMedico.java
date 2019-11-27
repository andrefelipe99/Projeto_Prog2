package gui.controladoresTela;

import controladores.Fachada;
import exceptions.DadosInvalidosException;
import exceptions.JaCadastradoException;
import exceptions.MedicoExistenteException;
import gui.tela.GerenciadorHospitalAPP;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.Especialidades;
import negocio.Medico;

public class ControladorTelaCadastroMedico implements Initializable {

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
    @FXML
    private ComboBox<String> selecionarEspecialidade;
    private Fachada fachada = Fachada.getInstance();

    ObservableList<String> especialidades;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carregarComboBox();

    }

    private void carregarComboBox() {
        especialidades = FXCollections.observableArrayList();
        especialidades.addAll(Especialidades.listarEspecialidades());

        selecionarEspecialidade.setItems(especialidades);
    }

    @FXML
    void cadastrar() {
        int idade = -1;
        String cpf = "";
        String crm = "";
        if (!txtIdade.getText().isEmpty() && txtIdade.getText().matches("^[0-9]*$")) {
            idade = Integer.parseInt(txtIdade.getText());
        }
        if (txtCpf.getText().matches("^[0-9]*$") && txtCpf.getText().length() == 11) {
            cpf = this.txtCpf.getText();
        }
        if (txtCrm.getText().matches("^[0-9]*$")) {
            crm = this.txtCrm.getText();
        }
        String nome = this.txtNome.getText();
        String senha = this.txtSenha.getText();
        String area = selecionarEspecialidade.getSelectionModel().getSelectedItem();

        try {
            Medico medico = new Medico(crm, area, senha, nome, idade, cpf);
            fachada.cadastrarMedico(medico);
            alertaConfirmacaoOK();
            voltar();
            fachada.salvarMedicos();
        } catch (DadosInvalidosException e) {
            e.erro();
        } catch (MedicoExistenteException e) {
            e.erro();
        } catch (JaCadastradoException e) {
			e.erro();
		} catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    void voltar() throws IOException {
        GerenciadorHospitalAPP.getStage().close();
        GerenciadorHospitalAPP adm = new GerenciadorHospitalAPP();
        adm.start(new Stage(), "/gui/fxmlAdmin/TelaAdmin.fxml", "Administrador");
    }

    public void alertaConfirmacaoOK() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Informacao");
        alerta.setHeaderText("Salvo com sucesso!");
        alerta.setContentText("Pressione 'OK' para retornar!");
        alerta.showAndWait();
    }

}
