package controladores;

import dados.RepositorioConsulta;
import javafx.collections.ObservableList;
import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public class ControladorConsulta implements IControladorConsulta {
	private RepositorioConsulta consultasSalvas;
	
	private static ControladorConsulta instance;
	
	private ControladorConsulta(){
		consultasSalvas = new RepositorioConsulta();
	}
	
	public ControladorConsulta getInstance() {
		if(instance == null) {
			instance = new ControladorConsulta();
		}
		
		return instance;
	}
	
	@Override
	public void cadastrarConsulta(Consulta c) {
		consultasSalvas.cadastrarConsulta(c);
	}
	
	@Override
	public void removerConsulta(Consulta c) {
		consultasSalvas.removerConsulta(c);
	}
	
	@Override
	public ObservableList<Consulta> listarConsultas(){
		return consultasSalvas.listarConsultas();
	}
	
	@Override
	public ObservableList<Consulta> listarConsultasPaciente(Paciente p){
		return consultasSalvas.listarConsultasPaciente(p);
	}
	
	@Override
	public ObservableList<Consulta> listarConsultasMedico(Medico m){
		return consultasSalvas.listarConsultasMedico(m);
	}
}
