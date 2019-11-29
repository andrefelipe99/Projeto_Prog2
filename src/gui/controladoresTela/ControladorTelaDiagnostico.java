package gui.controladoresTela;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controladores.Fachada;
import exceptions.DadosInvalidosException;
import exceptions.DiagnosticoJaExiste;
import exceptions.SemSelecaoException;
import gui.tela.GerenciadorHospitalAPP;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import negocio.Consulta;
import negocio.Diagnostico;

public class ControladorTelaDiagnostico implements Initializable {

    @FXML
    private Button botaoVoltar;
    @FXML
    private Button botaoSalvar;
    @FXML
    private TextArea areaTextoDiag;
    @FXML
    private TextArea areaTextoMedicamentos;

    Fachada fachada = Fachada.getInstance();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        botaoVoltar.setOnMouseClicked((MouseEvent e) -> {
            retornarTelaMedico();
        });
        botaoVoltar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                retornarTelaMedico();
            }
        });

        botaoSalvar.setOnMouseClicked((MouseEvent e) -> {
            try {
                cadastrarDiagnostico();
            } catch (SemSelecaoException e1) {
                Alertas.avisoSelecao();
            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        botaoSalvar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    cadastrarDiagnostico();
                } catch (SemSelecaoException e1) {
                    Alertas.avisoSelecao();
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void cadastrarDiagnostico() throws NumberFormatException, IOException, SemSelecaoException {
        File consultaSelecionada = new File("src/dados/arquivos/consultaSelecionada.txt");
        BufferedReader leitor = new BufferedReader(new FileReader(consultaSelecionada));

        Consulta c = fachada.buscarConsultaPorId(Integer.parseInt(leitor.readLine()));

        leitor.close();

        try {
        	Diagnostico d = new Diagnostico(areaTextoDiag.getText(), areaTextoMedicamentos.getText());
			fachada.cadastrarDiagnostico(d, c);
			Alertas.alertaConfirmacaoOK();
			retornarTelaMedico();
			fachada.salvarConsultas();
		} catch (DiagnosticoJaExiste e) {
			Alertas.erroJaDiagnosticado();
		} catch (DadosInvalidosException e) {
			Alertas.erroDadosInvalidos();
		}
    }

    public String medicoLogado() {
        String medicoLogado = new String();

        try {
            BufferedReader leitor = new BufferedReader(new FileReader("dados/arquivos/medicoLogado.txt"));

            if (leitor.ready()) {
                medicoLogado = leitor.readLine();
            }

            leitor.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return medicoLogado;
    }

    public void retornarTelaMedico() {
        GerenciadorHospitalAPP.getStage().close();
        GerenciadorHospitalAPP novaTela = new GerenciadorHospitalAPP();

        try {
            novaTela.start(new Stage(), "/gui/fxmlMedico/TelaMedico.fxml", "Medico");
        } catch (IOException e1) {

        }
    }
}
