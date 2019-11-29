package gui.controladoresTela;

import java.io.IOException;

import controladores.Fachada;
import exceptions.DadosInvalidosException;
import exceptions.JaCadastradoException;
import exceptions.PacienteExistenteException;
import gui.tela.GerenciadorHospitalAPP;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.Paciente;

public class ControladorTelaCadastroPaciente {

    @FXML
    private TextField textoNome;
    @FXML
    private TextField textoIdade;
    @FXML
    private TextField textoCPF;
    @FXML
    private TextField textoEndereco;
    @FXML
    private TextField textoTelefone;

    private Fachada fachada = Fachada.getInstance();

    @FXML
    void cadastrar() throws PacienteExistenteException, IOException {
        String nome = this.textoNome.getText();
        int idade = -1;
        String cpf = "";
        String telefone = "";
        String endereco = "";
        if (!textoIdade.getText().isEmpty() && textoIdade.getText().matches("^[0-9]*$")) {
            idade = Integer.parseInt(this.textoIdade.getText());
        }
        if (textoCPF.getText().matches("^[0-9]*$") && textoCPF.getText().length() == 11) {
            cpf = this.textoCPF.getText();
        }
        if (textoTelefone.getText().matches("^[0-9]*$") && textoTelefone.getText().length() == 9) {
            telefone = this.textoTelefone.getText();
        }
        if (!textoEndereco.getText().isEmpty()) {
            endereco = this.textoEndereco.getText();
        }

        try {
            Paciente paciente = new Paciente(endereco, telefone, nome, idade, cpf);
            fachada.cadastrarPaciente(paciente);
            Alertas.alertaConfirmacaoOK();
            voltar();
            fachada.salvarPacientes();
        } catch (DadosInvalidosException e) {
            Alertas.erroDadosInvalidos();
        } catch (PacienteExistenteException e) {
            Alertas.erroPacienteExiste();
        } catch (JaCadastradoException e) {
            Alertas.erroJaCadastrado();
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    void voltar() throws IOException {
        GerenciadorHospitalAPP.getStage().close();
        GerenciadorHospitalAPP atd = new GerenciadorHospitalAPP();
        atd.start(new Stage(), "/gui/fxmlAtendente/TelaAtendente.fxml", "Atendente");
    }

}
