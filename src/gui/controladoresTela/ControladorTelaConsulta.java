package gui.controladoresTela;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controladores.Fachada;
import exceptions.SemSelecaoException;
import gui.tela.GerenciadorHospitalAPP;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import negocio.Consulta;
import negocio.Paciente;

public class ControladorTelaConsulta implements Initializable {

    @FXML
    private TableView<Consulta> tabelaConsultas;
    @FXML
    private TableColumn<Consulta, String> colunaMedicoMarcadas;
    @FXML
    private TableColumn<Consulta, String> colunaDataMarcadas;
    @FXML
    private Button botaoMarcarConsulta;
    @FXML
    private Button botaoCancelaConsulta;
    @FXML
    private Button botaoVoltar;
    @FXML
    private Label txtID;
    @FXML
    private Label txtMedico;
    @FXML
    private Label txtPaciente;
    @FXML
    private Label txtDescricao;
    @FXML
    private Label txtHorario;
    @FXML
    private Label txtDiagDescricao;
    @FXML
    private Label txtMedicamentos;

    private ObservableList<Consulta> listaConsultas;
    private Fachada fachada = Fachada.getInstance();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        botaoVoltar.setOnMouseClicked((MouseEvent e) -> {
            GerenciadorHospitalAPP.getStage().close();
            GerenciadorHospitalAPP gerenciadorHospitalAPP = new GerenciadorHospitalAPP();
            try {
                fachada.salvarConsultas();
                gerenciadorHospitalAPP.start(new Stage(), "/gui/fxmlAtendente/TelaAtendente.fxml", "Atendente");
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });
        botaoVoltar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                GerenciadorHospitalAPP.getStage().close();
                GerenciadorHospitalAPP gerenciadorHospitalAPP = new GerenciadorHospitalAPP();
                try {
                    fachada.salvarConsultas();
                    gerenciadorHospitalAPP.start(new Stage(), "/gui/fxmlAtendente/TelaAtendente.fxml", "Atendente");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        botaoMarcarConsulta.setOnMouseClicked((MouseEvent e) -> {
            try {

                if (fachada.medicoVazio()) {
                    fachada.recuperarMedicos();
                }

                cadastroConsulta();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        botaoMarcarConsulta.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {

                    if (fachada.medicoVazio()) {
                        fachada.recuperarMedicos();
                    }

                    cadastroConsulta();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        botaoCancelaConsulta.setOnMouseClicked((MouseEvent e) -> {
        	try {
                remover();
                fachada.salvarConsultas();
            } catch (SemSelecaoException e1) {
                Alertas.avisoSelecao();
            } catch (IOException e2) {
                
            }
        });
        botaoCancelaConsulta.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
            	try {
                    remover();
                    fachada.salvarConsultas();
                } catch (SemSelecaoException e1) {
                    Alertas.avisoSelecao();
                } catch (IOException e2) {
                    
                }
            }
        });

        carregarTableConsulta();
        mostrarDetalhesConsulta(null);
        tabelaConsultas.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> mostrarDetalhesConsulta(newValue));
    }

    public void cadastroConsulta() throws IOException {
        GerenciadorHospitalAPP.getStage().close();
        GerenciadorHospitalAPP gerenciadorHospitalAPP = new GerenciadorHospitalAPP();
        gerenciadorHospitalAPP.start(new Stage(), "/gui/fxmlAtendente/TelaCadastroConsulta.fxml", "Marcar consulta");
    }

    public void remover() throws SemSelecaoException {
        Consulta consultaSelecionada = tabelaConsultas.getSelectionModel().getSelectedItem();

        if (consultaSelecionada != null) {
        	if(Alertas.alertaConfirmacaoProsseguir()) {
        		fachada.removerConsulta(consultaSelecionada);
        		Alertas.alertaRemocaoOK();
        		atualizarTabela();
        	}
            
        } else {
            throw new SemSelecaoException();
        }

    }

    public Paciente pacienteSelecionado() {
        Paciente pacienteSelecionado = null;

        try {
            BufferedReader leitor = new BufferedReader(new FileReader("src/dados/arquivos/pacienteSelecionado.txt"));

            if (leitor.ready()) {
                pacienteSelecionado = fachada.buscarPaciente(leitor.readLine());
            }
            leitor.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return pacienteSelecionado;
    }

    public void carregarTableConsulta() {
        colunaMedicoMarcadas.setCellValueFactory((TableColumn.CellDataFeatures<Consulta, String> c)
                -> new SimpleStringProperty(c.getValue().getMedico().getNome()));
        colunaDataMarcadas.setCellValueFactory((TableColumn.CellDataFeatures<Consulta, String> c)
                -> new SimpleStringProperty(c.getValue().horarioConsulta()));
        listaConsultas = FXCollections.observableArrayList();
        tabelaConsultas.setItems(listaConsultas);
        listaConsultas.addAll(fachada.listarConsultasPaciente(pacienteSelecionado()));

    }

    public void atualizarTabela() {
        tabelaConsultas.getItems().setAll(fachada.listarConsultasPaciente(pacienteSelecionado()));
    }

    private void mostrarDetalhesConsulta(Consulta c) {
        if (c != null) {
            txtID.setText(c.getId() + "");
            txtMedico.setText(c.getMedico().getNome());
            txtPaciente.setText(c.getPaciente().getNome());
            txtDescricao.setText(c.getDescricao());
            txtHorario.setText(c.horarioConsulta());
            if (c.getDiagnostico() != null) {
                txtDiagDescricao.setText(c.getDiagnostico().getDescricao());
                txtMedicamentos.setText(c.getDiagnostico().getMedicamentos());
            } else {
                txtDiagDescricao.setText("");
                txtMedicamentos.setText("");
            }

        } else {
            txtID.setText("");
            txtMedico.setText("");
            txtPaciente.setText("");
            txtDescricao.setText("");
            txtHorario.setText("");
            txtDiagDescricao.setText("");
            txtMedicamentos.setText("");
        }
    }

}
