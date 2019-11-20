package controladores;

public class ControladorHospital {
	private ControladorMedico medicos;
	private ControladorConsulta consultas;
	private ControladorPaciente pacientes;
	
	private static ControladorHospital instance;
	
	private ControladorHospital() {
		medicos = medicos.getInstance();
		consultas = consultas.getInstance();
		pacientes = pacientes.getInstance();
		
	}
	
	public ControladorHospital getInstance() {
		if(instance == null) {
			instance = new ControladorHospital();
		}
		
		return instance;
	}

	public ControladorMedico getMedicos() {
		return medicos;
	}

	public ControladorConsulta getConsultas() {
		return consultas;
	}

	public ControladorPaciente getPacientes() {
		return pacientes;
	}
	
}
