package dados;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public class RepositorioConsulta implements IRepositorioConsulta {
	private ObservableList<Consulta> consultas;
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
	public ObservableList<Consulta> listarConsultasPaciente(Paciente p) {
		ObservableList<Consulta> consultasPaciente = FXCollections.observableArrayList();
		
		for(Consulta c: consultas) {
			if(c.getPaciente() == p) {
				consultasPaciente.add(c);
			}
		}
		
		return consultasPaciente;
	}

	@Override
	public ObservableList<Consulta> listarConsultasMedico(Medico m) {
		ObservableList<Consulta> consultasMedico = FXCollections.observableArrayList();
		
		for(Consulta c: consultas) {
			if(c.getMedico() == m) {
				consultasMedico.add(c);
			}
		}
		
		return consultasMedico;
	}

	@Override
	public ObservableList<Consulta> listarConsultas() {
		return consultas;
	}

}
