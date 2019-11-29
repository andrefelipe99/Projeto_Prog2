package gui.controladoresTela;

import controladores.Fachada;
import exceptions.SemSelecaoException;
import gui.tela.GerenciadorHospitalAPP;
import negocio.Medico;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class ControladorTelaAdmin implements Initializable {

    @FXML
    private Button botaoCadastrar;
    @FXML
    private Button botaoRemover;
    @FXML
    private Button botaoSair;
    @FXML
    private TableView<Medico> tblMedico;
    @FXML
    private TableColumn<Medico, String> colunaCRM;
    @FXML
    private TableColumn<Medico, String> colunaNome;
    @FXML
    private TableColumn<Medico, String> colunaEspecialidade;

    private ObservableList<Medico> listaMedicos;
    private Fachada fachada = Fachada.getInstance();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        botaoSair.setOnMouseClicked((MouseEvent e) -> {
            try {
                fachada.salvarMedicos();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            GerenciadorHospitalAPP.fechar();
        });
        botaoSair.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    fachada.salvarMedicos();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                GerenciadorHospitalAPP.fechar();
            }
        });

        botaoCadastrar.setOnMouseClicked((MouseEvent e) -> {
            try {
                cadastro();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        botaoCadastrar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    cadastro();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        botaoRemover.setOnMouseClicked((MouseEvent e) -> {
            try {
                remover();
                fachada.salvarMedicos();

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (SemSelecaoException e1) {
            	Alertas.avisoSelecao();
            }
        });

        botaoRemover.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                   remover();
                   fachada.salvarMedicos();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (SemSelecaoException e1) {
                	Alertas.avisoSelecao();
                }
            }
        });

        carregarTableMedico();

    }

    private void cadastro() throws IOException {
        GerenciadorHospitalAPP.getStage().close();
        GerenciadorHospitalAPP adm = new GerenciadorHospitalAPP();
        adm.start(new Stage(), "/gui/fxmlAdmin/TelaCadastroMedico.fxml", "Cadastro");

    }

    private void carregarTableMedico() {
        colunaCRM.setCellValueFactory(new PropertyValueFactory<Medico, String>("crm"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<Medico, String>("nome"));
        colunaEspecialidade.setCellValueFactory(new PropertyValueFactory<Medico, String>("area"));

        listaMedicos = FXCollections.observableArrayList();
        listaMedicos.addAll(fachada.listarMedicos());
        tblMedico.setItems(listaMedicos);
    }

    private void remover() throws SemSelecaoException, IOException {
        Medico medicoSelecionado = tblMedico.getSelectionModel().getSelectedItem();

        if (medicoSelecionado != null) {
        	
        	if(Alertas.alertaConfirmacaoProsseguir()) {
        		fachada.removerMedico(medicoSelecionado);
        		Alertas.alertaRemocaoOK();
        		atualizarTabela();
        	}
            
        } else {
            throw new SemSelecaoException();
        }

    }

    private void atualizarTabela() {
        tblMedico.getItems().setAll(fachada.listarMedicos());
    }

}
