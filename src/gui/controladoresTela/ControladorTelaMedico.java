package gui.controladoresTela;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import negocio.Consulta;

public class ControladorTelaMedico implements Initializable{

	private Fachada fachada = Fachada.getInstance();
	@FXML private TableView<Consulta> tabelaConsultasMed;
	@FXML private TableColumn<Consulta, String> colunaPaciente;
    @FXML private TableColumn<Consulta, String> colunaHorario;
    @FXML private Label campoMedIdade;
    @FXML private Label campoMedAvisos;
    @FXML private Button botaoDiagnostico;
    @FXML private Label campoMedHora;
    @FXML private Button botaoSair;
    
    private ObservableList<Consulta> listaConsultas;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    		botaoSair.setOnMouseClicked((MouseEvent e)->{
    			GerenciadorHospitalAPP.fechar();
    		});
    		botaoSair.setOnKeyPressed((KeyEvent e)->{
    			if(e.getCode() == KeyCode.ENTER) {
    				GerenciadorHospitalAPP.fechar();
    			}
    		});

    		botaoDiagnostico.setOnMouseClicked((MouseEvent e)->{
    			try {
					abrirTelaDiagnostico();
				} catch (SemSelecaoException e1) {
					e1.erro();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
    			
    		});
    		botaoDiagnostico.setOnKeyPressed((KeyEvent e)->{
    			if(e.getCode() == KeyCode.ENTER) {
    				try {
						abrirTelaDiagnostico();
					} catch (SemSelecaoException e1) {
						e1.erro();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
    			}
    		});

                carregarTableMedico();
	}

    public String atualizarHora() {
    	LocalDateTime horaDoSistema = LocalDateTime.now();
    	DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    	return horaDoSistema.format(formatador);
    }

    public void abrirTelaDiagnostico() throws SemSelecaoException, IOException {
    	Consulta c = tabelaConsultasMed.getSelectionModel().getSelectedItem();
    	
    	if(c == null) {
    		throw new SemSelecaoException();
    	}
    	
    	salvarConsultaSelecionada(c);
    	
    	GerenciadorHospitalAPP.getStage().close();
		GerenciadorHospitalAPP novaTela = new GerenciadorHospitalAPP();

		try {
			novaTela.start(new Stage(), "/gui/fxmlMedico/TelaDiagnostico.fxml", "Diagnostico");
		} catch (IOException e1) {

		}
    }
    
    private void salvarConsultaSelecionada(Consulta c) throws IOException {
    	File consultaSelecionada = new File("src/dados/arquivos/consultaSelecionada.txt");
    	
    	if(!consultaSelecionada.exists()) {
    		consultaSelecionada.createNewFile();
    	}
    	
    	BufferedWriter escritor = new BufferedWriter(new FileWriter(consultaSelecionada));
    	escritor.write(Integer.toString(c.getId()));
    	
    	escritor.flush();
    	
    	escritor.close();
    	
    }
    
    private String medicoLogado() {
    	String medicoLogado = new String();

    	try {
			BufferedReader leitor = new BufferedReader(new FileReader("src/dados/arquivos/medicoLogado.txt"));

			if(leitor.ready()) {
				medicoLogado = leitor.readLine();
			}


			leitor.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
		return medicoLogado;
    }

    public void carregarTableMedico(){
            colunaPaciente.setCellValueFactory(new PropertyValueFactory<Consulta, String>("nomePaciente"));
            colunaHorario.setCellValueFactory((TableColumn.CellDataFeatures<Consulta, String> c)->
        	new SimpleStringProperty(c.getValue().horarioConsulta()));
            
            listaConsultas = FXCollections.observableArrayList();
            listaConsultas.addAll(fachada.listarConsultasMedico(fachada.buscarMedico(medicoLogado())));
            tabelaConsultasMed.setItems(listaConsultas);
        }

}
