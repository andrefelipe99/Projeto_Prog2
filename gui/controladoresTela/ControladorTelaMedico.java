package controladoresTela;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import controladores.ControladorHospital;
import tela.GerenciadorHospitalAPP;

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
import negocio.Consulta;

public class ControladorTelaMedico implements Initializable{
	
	ControladorHospital hospital;
	@FXML private TableView<Consulta> tabelaConsultasMed;
	@FXML private TableColumn<Consulta, String> colunaPaciente;
    @FXML private TableColumn<Consulta, LocalDateTime> colunaHorario;
    @FXML private Label campoMedIdade;
    @FXML private Label campoMedAvisos;
    @FXML private Button botaoDiagnostico;
    @FXML private Label campoMedHora;
    @FXML private Button botaoSair;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    		colunaPaciente.setCellValueFactory(new PropertyValueFactory<>("Paciente"));
    		colunaHorario.setCellValueFactory(new PropertyValueFactory<>("Hora"));
    		
    	
    		botaoSair.setOnMouseClicked((MouseEvent e)->{
    			GerenciadorHospitalAPP.fechar();
    		});
    		botaoSair.setOnKeyPressed((KeyEvent e)->{
    			if(e.getCode() == KeyCode.ENTER) {
    				GerenciadorHospitalAPP.fechar();
    			}
    		});
    	
	}
    
    public String atualizarHora() {
    	LocalDateTime horaDoSistema = LocalDateTime.now();
    	DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    	return horaDoSistema.format(formatador);
    }
    
    
    public String medicoLogado() {
    	String medicoLogado = new String();
    	
    	try {
			BufferedReader leitor = new BufferedReader(new FileReader("dados/arquivos/medicoLogado.txt"));
			
			if(leitor.ready()) {
				medicoLogado = leitor.readLine();
			}
			
			
			leitor.close();
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		return medicoLogado;
    }
    
    
    
}
