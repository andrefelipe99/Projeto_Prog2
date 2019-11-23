package dados;

import java.util.List;

import exceptions.DadosInvalidosException;
import exceptions.PacienteExistenteException;
import negocio.Paciente;

public interface IRepositorioPaciente {
	public void cadastrarPaciente(Paciente p) throws DadosInvalidosException, PacienteExistenteException;
	public void removerPaciente (Paciente p);
	public boolean pacienteExiste(Paciente p);
	public Paciente buscarPaciente (String cpf);
	public List<Paciente> listarPacientes();
}
