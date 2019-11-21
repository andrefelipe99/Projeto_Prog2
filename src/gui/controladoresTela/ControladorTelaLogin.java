package gui.controladoresTela;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.tela.GerenciadorHospitalAPP;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ControladorTelaLogin implements Initializable{

    @FXML private TextField textoUser;
    @FXML private PasswordField textoSenha;
    @FXML private Button botaoLogin;
    @FXML private Label textoAvisos;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		botaoLogin.setOnMouseClicked((MouseEvent me)->{
			logar();
		});

		botaoLogin.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
			logar();
			}
		});
		
		textoSenha.setOnKeyPressed((KeyEvent e)->{
			if(e.getCode() == KeyCode.ENTER) {
			logar();
			}
		});
	}

	public void logar(){

			try {
                                BufferedReader br = new BufferedReader(new FileReader("src/dados/arquivos/logins.txt"));
				boolean logado = false;
				String caminho = "";
				String titulo = new String();
				String crm = new String();

				do {
					String[] loginSenha = br.readLine().split(",");

					if(textoUser.getText().equals(loginSenha[0]) && textoSenha.getText().equals(loginSenha[1])) {

						if(textoUser.getText().equals("admin")) {
							titulo = "Administrador";
							caminho = "/gui/fxmlAdmin/TelaAdmin.fxml";
							logado = true;

						}
						else if(textoUser.getText().equals("atendente")) {
							titulo = "Atendente";
							caminho = "/gui/fxmlAtendente/TelaAtendente.fxml";
							logado = true;

						}
						else {
							titulo = "Medico";
							caminho = "/gui/fxmlMedico/TelaMedico.fxml";
							crm = textoUser.getText();
							setarMedicoLogado(crm);
							logado = true;


						}

						if(!br.ready()) {
							br.close();
							logado = true;

						}
					}


				}while(!logado);

				if(!caminho.equals("")) {
					GerenciadorHospitalAPP.getStage().close();
					GerenciadorHospitalAPP gerenciadorHospitalAPP = new GerenciadorHospitalAPP();
					gerenciadorHospitalAPP.start(new Stage(), caminho, titulo);

				}

			}
			catch(IOException ioe) {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("ERRO");
				alerta.setHeaderText("Erro nos Arquivos");
				alerta.setContentText("Falha ao ler o arquivo dos logins, contate a administracao!");
				alerta.show();
                                ioe.printStackTrace();
			}
			catch(Exception e) {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("ERRO");
				alerta.setHeaderText("Erro no Login");
				alerta.setContentText("Usuario ou senha invalidos!");
				alerta.show();
			}


		}

	public void setarMedicoLogado(String crm) throws IOException {
		File f = new File("src/dados/arquivos/medicoLogado.txt");

		if(f.exists()) {
			f.delete();
		}

		f.createNewFile();
		FileWriter fw = new FileWriter(f);

		fw.write(crm);

		fw.close();



	}



	public PasswordField getTextoSenha() {
		return textoSenha;
	}
	public void setTextoSenha(PasswordField textoSenha) {
		this.textoSenha = textoSenha;
	}


	public Button getBotaoLogin() {
		return botaoLogin;
	}


	public void setBotaoLogin(Button botaoLogin) {
		this.botaoLogin = botaoLogin;
	}

}
