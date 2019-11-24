package controladores;

import java.util.List;
import negocio.Paciente;

public interface IControladorPaciente {
	public void cadastrarPaciente(Paciente p);
	public void removerPaciente(String cpf);
	public Paciente buscarPaciente(String cpf);
	public List<Paciente> listarPacientes();
}
