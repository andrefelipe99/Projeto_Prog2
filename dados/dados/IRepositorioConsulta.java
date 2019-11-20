package dados;

import java.util.List;

import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public interface IRepositorioConsulta {
	public void cadastrarConsulta(Consulta c);
	public void removerConsulta(Consulta c); 
	public List<Consulta> listarConsultasPaciente(Paciente p);
	public List<Consulta> listarConsultasMedico(Medico m);
	public List<Consulta> listarConsultas();
}


