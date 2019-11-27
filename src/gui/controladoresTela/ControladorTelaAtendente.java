package gui.controladoresTela;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import controladores.Fachada;
import gui.tela.GerenciadorHospitalAPP;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import negocio.Paciente;

public class ControladorTelaAtendente implements Initializable{

	@FXML private TableView<Paciente> tblPaciente;
	@FXML private TableColumn<Paciente, String> colunaNome;
	@FXML private TableColumn<Paciente, String> colunaCPF;
    @FXML private TextField barraPesquisa;
    @FXML private Label textoCPF;
	@FXML private Label textoNome;
    @FXML private Label textoIdade;
    @FXML private Label textoEndereco;
    @FXML private Label textoTelefone;
    @FXML private Button botaoHistorico;
    @FXML private Button botaoCadastrar;
    @FXML private Button botaoRemover;
    @FXML private Button botaoSair;

    private ObservableList<Paciente> listaPacientes;
    private Fachada fachada = Fachada.getInstance();

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	botaoHistorico.setOnMouseClicked((MouseEvent e)->{
    		try {
    			
				abrirTelaConsulta();
			
    		} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	});
    	botaoHistorico.setOnKeyPressed((KeyEvent e)->{
    		if(e.getCode() == KeyCode.ENTER) {
    			try {
    				
    				abrirTelaConsulta();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}
    	});
    	botaoSair.setOnMouseClicked((MouseEvent e)->{
			try {
				fachada.salvarPacientes();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		GerenciadorHospitalAPP.fechar();
		});
		botaoSair.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				try {
					fachada.salvarPacientes();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				GerenciadorHospitalAPP.fechar();
			}
		});
		botaoCadastrar.setOnMouseClicked((MouseEvent e)->{
			try {
                cadastro();
            } catch (Exception ex) {
                Logger.getLogger(ControladorTelaAtendente.class.getName()).log(Level.SEVERE, null, ex);
            }
		});
		botaoCadastrar.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				try {
					cadastro();
				} catch (Exception ex) {
					Logger.getLogger(ControladorTelaAtendente.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		botaoRemover.setOnMouseClicked((MouseEvent e)->{
				remover();
		});
		botaoRemover.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
				remover();
			}
		});
		barraPesquisa.setOnKeyReleased((KeyEvent e)->{
			tblPaciente.getItems().setAll(busca());
		});

		carregarTablePaciente();

		mostrarDetalhesPaciente(null);
        tblPaciente.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> mostrarDetalhesPaciente(newValue));
	}

    public void cadastro() throws Exception {
    	GerenciadorHospitalAPP.getStage().close();
        GerenciadorHospitalAPP atd = new GerenciadorHospitalAPP();
        atd.start(new Stage(), "/gui/fxmlAtendente/TelaCadastroPaciente.fxml","Cadastro paciente");

    }

    public void abrirTelaConsulta() throws IOException, ClassNotFoundException {
    	Paciente pacienteSelecionado = tblPaciente.getSelectionModel().getSelectedItem();
    	
    	if(fachada.medicoVazio()) {
			fachada.recuperarMedicos();
		}
    	if(fachada.consultaVazia()) {
			fachada.recuperarConsultas();
		}

    	if(pacienteSelecionado != null) {
    		setarPacienteSelecionado(pacienteSelecionado.getCpf());
    		GerenciadorHospitalAPP.getStage().close();
        	GerenciadorHospitalAPP gerenciadorHospitalAPP = new GerenciadorHospitalAPP();
    		try {
    			gerenciadorHospitalAPP.start(new Stage(), "/gui/fxmlAtendente/TelaConsulta.fxml", "Historico do Paciente");
    		} catch (Exception e1) {
    			e1.printStackTrace();
    		}
    	} else {
    		Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Nenhum paciente selecionado!");
            alert.setContentText("Por favor, selecione um paciente na tabela!");
            alert.show();
    	}

    }

    public void carregarTablePaciente(){
    	colunaNome.setCellValueFactory(new PropertyValueFactory<Paciente, String>("Nome"));
    	colunaCPF.setCellValueFactory(new PropertyValueFactory<Paciente, String>("cpf"));
        listaPacientes = FXCollections.observableArrayList();
        tblPaciente.setItems(listaPacientes);
        listaPacientes.addAll(fachada.listarPacientes());

    }

    public void remover(){
        Paciente pacienteSelecionado = tblPaciente.getSelectionModel().getSelectedItem();

        if(pacienteSelecionado != null){

            fachada.removerPaciente(pacienteSelecionado);
            alertaConfirmacaoOK();
            atualizarTabela();
        } else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Nenhum paciente selecionado!");
            alert.setContentText("Por favor, selecione um paciente na tabela!");
            alert.show();
        }

    }

    public void atualizarTabela(){
        tblPaciente.getItems().setAll(fachada.listarPacientes());
    }

    public void alertaConfirmacaoOK() {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Informacao");
        alerta.setHeaderText("Remocao feita com sucesso!");
        alerta.setContentText("Pressione 'OK' para retornar!");
        alerta.showAndWait();
    }

    private void mostrarDetalhesPaciente(Paciente p) {
        if (p != null) {
            textoNome.setText(p.getNome());
            textoCPF.setText(p.getCpf());
            textoIdade.setText(p.getIdade() + "");
            textoEndereco.setText(p.getEndereco());
            textoTelefone.setText(p.getTelefone());

        } else {
        	textoNome.setText("");
        	textoCPF.setText("");
        	textoIdade.setText("");
        	textoEndereco.setText("");
        	textoTelefone.setText("");
        }
    }

    private ObservableList<Paciente> busca() {
    	atualizarTabela();
    	ObservableList<Paciente> pacientePesquisa = FXCollections.observableArrayList();
    	for(int i = 0; i < listaPacientes.size(); i++) {
    		if(listaPacientes.get(i).getNome().toLowerCase().contains(barraPesquisa.getText().toLowerCase())
    				|| listaPacientes.get(i).getCpf().contains(barraPesquisa.getText())) {
    			pacientePesquisa.add(listaPacientes.get(i));
    		}
    	}
    	return pacientePesquisa;
    }

    public void setarPacienteSelecionado(String cpf) throws IOException {
		File f = new File("src/dados/arquivos/pacienteSelecionado.txt");

		if(f.exists()) {
			f.delete();
		}

		f.createNewFile();
		FileWriter fw = new FileWriter(f);

		fw.write(cpf);

		fw.close();
	}

}
