package gui.controladoresTela;

import java.io.IOException;

import controladores.Fachada;
import gui.tela.GerenciadorHospitalAPP;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.Paciente;

public class ControladorTelaCadastroPaciente {
	@FXML private TextField textoNome;
	@FXML private TextField textoIdade;
	@FXML private TextField textoCPF;
	@FXML private TextField textoEndereco;
	@FXML private TextField textoTelefone;

	private Fachada fachada = Fachada.getInstance();

	@FXML
	void cadastrar() {
		String nome = this.textoNome.getText();
		int idade = -1;
		if(!textoIdade.getText().isEmpty()) {
			idade = Integer.parseInt(this.textoIdade.getText());
		}
		String cpf = this.textoCPF.getText();
		String endereco = this.textoEndereco.getText();
		String telefone = this.textoTelefone.getText();

		try{

            if(!nome.isEmpty() && !cpf.isEmpty() && !endereco.isEmpty() && !telefone.isEmpty()) {
            	Paciente paciente = new Paciente(endereco, telefone, nome, idade, cpf);
            	fachada.cadastrarPaciente(paciente);
            	voltar();
            }

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@FXML
    void voltar() throws IOException{
            GerenciadorHospitalAPP.getStage().close();
            GerenciadorHospitalAPP atd = new GerenciadorHospitalAPP();
            atd.start(new Stage(), "/gui/fxmlAtendente/TelaAtendente.fxml","Atendente");
    }




}
