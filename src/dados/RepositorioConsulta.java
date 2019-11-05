package dados;

import java.util.ArrayList;
import java.util.List;

import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public class RepositorioConsulta implements IRepositorioConsulta {
	private List<Consulta> consultas;
	@Override
	public void cadastrarConsulta(Consulta c) {
		if(!consultas.contains(c)) {
			consultas.add(c);
		}

	}

	@Override
	public void removerConsulta(Consulta c) {
		if(consultas.contains(c)) {
			consultas.remove(c);
		}

	}

	@Override
	public List<Consulta> listarConsultasPaciente(Paciente p) {
		List<Consulta> consultasPaciente = new ArrayList<Consulta>();
		
		for(Consulta c: consultas) {
			if(c.getPaciente() == p) {
				consultasPaciente.add(c);
			}
		}
		
		return consultasPaciente;
	}

	@Override
	public List<Consulta> listarConsultasMedico(Medico m) {
		List<Consulta> consultasMedico = new ArrayList<Consulta>();
		
		for(Consulta c: consultas) {
			if(c.getMedico() == m) {
				consultasMedico.add(c);
			}
		}
		
		return consultasMedico;
	}

	@Override
	public List<Consulta> listarConsultas() {
		
		return consultas;
	}

}
