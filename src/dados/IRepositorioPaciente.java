package dados;

import java.util.List;
import negocio.Paciente;

public interface IRepositorioPaciente {
	public void cadastrarPaciente(Paciente p);
	public void removerPaciente (String cpf);
	public boolean PacienteExiste(Paciente p);
	public Paciente buscarPaciente (String cpf);
	public List<Paciente> listarPacientes();
}
