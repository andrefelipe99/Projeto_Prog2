package controladores;

import java.util.List;

import dados.RepositorioPaciente;
import exceptions.DadosInvalidosException;
import exceptions.PacienteExistenteException;
import negocio.Paciente;

public class ControladorPaciente {
	private RepositorioPaciente paciente;

	public ControladorPaciente() {
		this.paciente = new RepositorioPaciente();
	}

	public void cadastrarPaciente(Paciente p) throws DadosInvalidosException, PacienteExistenteException {
		paciente.cadastrarPaciente(p);

	}

	public void removerPaciente(Paciente p) {
		paciente.removerPaciente(p);
	}

	public Paciente buscarPaciente(String cpf) {
		return paciente.buscarPaciente(cpf);
	}

	public List<Paciente> listarPacientes() {
		return paciente.listarPacientes();
	}
}
