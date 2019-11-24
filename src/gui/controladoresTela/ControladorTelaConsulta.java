package gui.controladoresTela;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import controladores.Fachada;
import gui.tela.GerenciadorHospitalAPP;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import negocio.Consulta;
import negocio.Paciente;

public class ControladorTelaConsulta implements Initializable{

	@FXML private TableView<Consulta> tabelaConsultas;
	@FXML private TableColumn<Consulta, String> colunaMedicoMarcadas;
    @FXML private TableColumn<Consulta, String> colunaDataMarcadas;
    @FXML private Button botaoMarcarConsulta;
    @FXML private Button botaoCancelaConsulta;
    @FXML private Button botaoVoltar;
    @FXML private Label txtID;
	@FXML private Label txtMedico;
    @FXML private Label txtPaciente;
    @FXML private Label txtDescricao;
    @FXML private Label txtHorario;
    @FXML private Label txtDiagDescricao;
	@FXML private Label txtMedicamentos;

    private ObservableList<Consulta> listaConsultas;
    private Fachada fachada = Fachada.getInstance();

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	botaoVoltar.setOnMouseClicked((MouseEvent e)->{
			GerenciadorHospitalAPP.getStage().close();
	    	GerenciadorHospitalAPP gerenciadorHospitalAPP = new GerenciadorHospitalAPP();
			try {
				gerenciadorHospitalAPP.start(new Stage(), "/gui/fxmlAtendente/TelaAtendente.fxml", "Atendente");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
		botaoVoltar.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				GerenciadorHospitalAPP.getStage().close();
				GerenciadorHospitalAPP gerenciadorHospitalAPP = new GerenciadorHospitalAPP();
				try {
					gerenciadorHospitalAPP.start(new Stage(), "/gui/fxmlAtendente/TelaAtendente.fxml", "Atendente");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		botaoMarcarConsulta.setOnMouseClicked((MouseEvent e)->{
			try {
	             	cadastroConsulta();
	        } catch (Exception ex) {
	            	Logger.getLogger(ControladorTelaAtendente.class.getName()).log(Level.SEVERE, null, ex);
	        }
		});
		botaoMarcarConsulta.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				try {
	             		cadastroConsulta();
				} catch (Exception ex) {
	            		Logger.getLogger(ControladorTelaAtendente.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		botaoCancelaConsulta.setOnMouseClicked((MouseEvent e)->{
			remover();
		});
		botaoCancelaConsulta.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				remover();
			}
		});

		carregarTableConsulta();
		mostrarDetalhesConsulta(null);
        tabelaConsultas.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> mostrarDetalhesConsulta(newValue));
	}

    public void cadastroConsulta() {
		GerenciadorHospitalAPP.getStage().close();
    	GerenciadorHospitalAPP gerenciadorHospitalAPP = new GerenciadorHospitalAPP();
		try {
			gerenciadorHospitalAPP.start(new Stage(), "/gui/fxmlAtendente/TelaCadastroConsulta.fxml", "Marcar consulta");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    }

    public void remover(){
        Consulta consultaSelecionada = tabelaConsultas.getSelectionModel().getSelectedItem();

        if(consultaSelecionada != null){

            fachada.removerConsulta(consultaSelecionada);
            alertaConfirmacaoOK();
            atualizarTabela();
        } else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Nenhuma consulta selecionada!");
            alert.setContentText("Por favor, selecione uma consulta na tabela!");
            alert.show();
        }

    }

    public Paciente pacienteSelecionado() {
    	Paciente pacienteSelecionado = null;

    	try {
			BufferedReader leitor = new BufferedReader(new FileReader("src/dados/arquivos/pacienteSelecionado.txt"));

			if(leitor.ready()) {
				pacienteSelecionado = fachada.buscarPaciente(leitor.readLine());
			}
			leitor.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		return pacienteSelecionado;
    }

    public void carregarTableConsulta(){
    	colunaMedicoMarcadas.setCellValueFactory((TableColumn.CellDataFeatures<Consulta, String> c)->
    	new SimpleStringProperty(c.getValue().getMedico().getNome()));
    	colunaDataMarcadas.setCellValueFactory((TableColumn.CellDataFeatures<Consulta, String> c)->
    	new SimpleStringProperty(c.getValue().horarioConsulta()));
        listaConsultas = FXCollections.observableArrayList();
        tabelaConsultas.setItems(listaConsultas);
        listaConsultas.addAll(fachada.listarConsultasPaciente(pacienteSelecionado()));

    }

    public void atualizarTabela(){
        tabelaConsultas.getItems().setAll(fachada.listarConsultasPaciente(pacienteSelecionado()));
    }

    public void alertaConfirmacaoOK() {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Informacao");
        alerta.setHeaderText("Remocao feita com sucesso!");
        alerta.setContentText("Pressione 'OK' para retornar!");
        alerta.showAndWait();
    }

    private void mostrarDetalhesConsulta(Consulta c) {
        if (c != null) {
        	txtID.setText(c.getId() + "");
        	txtMedico.setText(c.getMedico().getNome());
        	txtPaciente.setText(c.getPaciente().getNome());
        	txtDescricao.setText(c.getDescricao());
        	txtHorario.setText(c.horarioConsulta());
        	if(c.getDiagnostico() != null) {
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
