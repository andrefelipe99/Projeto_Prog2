package gui.controladoresTela;

import controladores.Fachada;
import exceptions.SemSelecaoException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.tela.GerenciadorHospitalAPP;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import negocio.Medico;

public class ControladorTelaAdmin implements Initializable{

    @FXML private Button botaoCadastrar;
    @FXML private Button botaoRemover;
    @FXML private Button botaoSair;
    @FXML private TableView<Medico> tblMedico;
    @FXML private TableColumn<Medico, String> colunaCRM;
    @FXML private TableColumn<Medico, String> colunaNome;
    @FXML private TableColumn<Medico, String> colunaEspecialidade;

    private ObservableList<Medico> listaMedicos;
    private Fachada fachada = Fachada.getInstance();

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		botaoSair.setOnMouseClicked((MouseEvent e)->{
			try {
				fachada.salvarMedicos();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			GerenciadorHospitalAPP.fechar();
		});
		botaoSair.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				try {
					fachada.salvarMedicos();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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

                botaoCadastrar.setOnKeyPressed((KeyEvent e) ->{
                    if(e.getCode() == KeyCode.ENTER){
                        try {
                            cadastro();
                        } catch (Exception ex) {
                            Logger.getLogger(ControladorTelaAdmin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                botaoRemover.setOnMouseClicked((MouseEvent e) -> {
                    try {
						remover();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (SemSelecaoException e1) {
						e1.erro();
					}
                });

                botaoRemover.setOnKeyPressed((KeyEvent e) -> {
                    if(e.getCode() == KeyCode.ENTER){
                        try {
							remover();
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (SemSelecaoException e1) {
							e1.erro();
						}
                    }
                });

                carregarTableMedico();

	}

        public void cadastro() throws Exception{
            GerenciadorHospitalAPP.getStage().close();
            GerenciadorHospitalAPP adm = new GerenciadorHospitalAPP();
            adm.start(new Stage(), "/gui/fxmlAdmin/TelaCadastroMedico.fxml","Cadastro");


        }

        public void carregarTableMedico(){
            colunaCRM.setCellValueFactory(new PropertyValueFactory<Medico, String>("crm"));
            colunaNome.setCellValueFactory(new PropertyValueFactory<Medico, String>("nome"));
            colunaEspecialidade.setCellValueFactory(new PropertyValueFactory<Medico, String>("area"));

            listaMedicos = FXCollections.observableArrayList();
            listaMedicos.addAll(fachada.listarMedicos());
            tblMedico.setItems(listaMedicos);
        }

        public void remover() throws IOException, SemSelecaoException{
            Medico medicoSelecionado = tblMedico.getSelectionModel().getSelectedItem();

            if(medicoSelecionado != null){

                fachada.removerMedico(medicoSelecionado);
                alertaConfirmacaoOK();
                atualizarTabela();
            } else{
                throw new SemSelecaoException();
            }

        }

        public void atualizarTabela(){
            tblMedico.getItems().setAll(fachada.listarMedicos());
        }

        public void alertaConfirmacaoOK() {
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Informacao");
            alerta.setHeaderText("Remocao feita com sucesso!");
            alerta.setContentText("Pressione 'OK' para retornar!");
            alerta.showAndWait();
    }

}
