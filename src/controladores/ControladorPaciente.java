package controladores;

import java.util.List;

import dados.RepositorioPaciente;
import negocio.Paciente;

public class ControladorPaciente {
	private RepositorioPaciente paciente;

	public ControladorPaciente() {
		this.paciente = new RepositorioPaciente();
	}

	public void cadastrarPaciente(Paciente p) {
		if(p != null && p.getEndereco()!= null && p.getEndereco().isEmpty() == false
				&& p.getCpf()!= null && p.getCpf().isEmpty() == false
				&& p.getNome()!= null && p.getNome().isEmpty() == false
				&& p.getTelefone()!= null && p.getTelefone().isEmpty() == false
				&& p.getIdade() > 0) {
			paciente.cadastrarPaciente(p);
		}
		else {
			System.out.println("paciente nao cadastrado");
			//exception
		}
	}

	public void removerPaciente(String cpf) {
		if(cpf!= null && cpf.isEmpty() == false) {
			paciente.removerPaciente(cpf);
		}
		else {
			System.out.println("cpf invalido");
			//exception
		}
	}

	public List<Paciente> listarPacientes() {
		return paciente.listarPacientes();
	}
}
