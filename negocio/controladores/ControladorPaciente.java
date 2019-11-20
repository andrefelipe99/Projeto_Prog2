package controladores;

import dados.RepositorioPaciente;
import javafx.collections.ObservableList;
import negocio.Paciente;

public class ControladorPaciente implements IControladorPaciente{
	private RepositorioPaciente pacientes;

	private static ControladorPaciente instance;
	
	private ControladorPaciente(){
		pacientes = new RepositorioPaciente();
	}
	
	public ControladorPaciente getInstance() {
		if(instance == null) {
			instance = new ControladorPaciente();
		}
		
		return instance;
	}

	public void cadastrarPaciente(Paciente p) {
		if(p != null && p.getEndereco()!= null && p.getEndereco().isEmpty() == false
				&& p.getCpf()!= null && p.getCpf().isEmpty() == false
				&& p.getNome()!= null && p.getNome().isEmpty() == false
				&& p.getTelefone()!= null && p.getTelefone().isEmpty() == false
				&& p.getIdade() > 0) {
			pacientes.cadastrarPaciente(p);
		}
		else {
			System.out.println("paciente nao cadastrado");
			//exception
		}
	}

	public void removerPaciente(String cpf) {
		if(cpf!= null && cpf.isEmpty() == false) {
			pacientes.removerPaciente(cpf);
		}
		else {
			System.out.println("cpf invalido");
			//exception
		}
	}

	public ObservableList<Paciente> listarPacientes() {
		return pacientes.listarPacientes();
	}
}
