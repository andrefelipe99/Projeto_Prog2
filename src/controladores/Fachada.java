package controladores;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import exceptions.ConsultaJaExisteException;
import exceptions.DadosInvalidosException;
import exceptions.MedicoExistenteException;
import exceptions.PacienteExistenteException;
import negocio.Consulta;
import negocio.Diagnostico;
import negocio.Medico;
import negocio.Paciente;

public class Fachada {

    private static Fachada instance;

    private ControladorPaciente controladorPaciente;
    private ControladorMedico controladorMedico;
    private ControladorConsulta controladorConsulta;
    private ControladorDiagnostico controladorDiagnostico;

    private Fachada(){
        controladorPaciente = new ControladorPaciente();
        controladorMedico = new ControladorMedico();
        controladorConsulta = new ControladorConsulta();
        controladorDiagnostico = new ControladorDiagnostico();
    }

    public static Fachada getInstance(){
        if(instance == null){
            instance = new Fachada();
        }
        return instance;
    }

    // PACIENTE

    public void cadastrarPaciente(Paciente p) throws DadosInvalidosException, PacienteExistenteException {
        controladorPaciente.cadastrarPaciente(p);
    }

    public void removerPaciente(Paciente p) {
        controladorPaciente.removerPaciente(p);
    }

    public Paciente buscarPaciente(String cpf) {
		return controladorPaciente.buscarPaciente(cpf);
	}

	public List<Paciente> listarPacientes() {
        return controladorPaciente.listarPacientes();
    }
	
	public void recuperarPacientes() throws ClassNotFoundException, IOException {
		controladorPaciente.recuperarPacientes();
	}
	
	public void salvarPacientes() throws IOException {
		controladorPaciente.salvarPacientes();
	}
	
	public boolean pacienteVazio() {
		return controladorPaciente.pacienteVazio();
	}

    // MEDICO

    public void cadastrarMedico(Medico m) throws DadosInvalidosException, MedicoExistenteException, IOException {
        controladorMedico.cadastrarMedico(m);
    }

    public void removerMedico(Medico m) throws IOException {
        controladorMedico.removerMedico(m);
    }

    public List<Medico> listarMedicos() {
        return controladorMedico.listarMedicos();
    }

    public boolean login(String crm, String senha) {
        return controladorMedico.login(crm, senha);
    }

    public boolean medicoExiste(Medico m) {
        return controladorMedico.medicoExiste(m);
    }

    public Medico buscarMedico(String crm) {
    	return controladorMedico.buscarMedico(crm);
    }
    
    public void recuperarMedicos() throws ClassNotFoundException, IOException {
    	controladorMedico.recuperarMedicos();
    }
    
    public void salvarMedicos() throws IOException {
    	controladorMedico.salvarMedicos();
    }
    
    public boolean medicoVazio() {
		return controladorMedico.medicoVazio();
	}

    // CONSULTA

    public void cadastrarConsulta(Consulta c) throws DadosInvalidosException, ConsultaJaExisteException {
        controladorConsulta.cadastrarConsulta(c);
    }

    public void removerConsulta(Consulta c) {
        controladorConsulta.removerConsulta(c);
    }

    public List<Consulta> listarConsultas() {
        return controladorConsulta.listarConsultas();
    }

    public List<Consulta> listarConsultasPaciente(Paciente p) {
        return controladorConsulta.listarConsultasPaciente(p);
    }

    public List<Consulta> listarConsultasMedico(Medico m) {
        return controladorConsulta.listarConsultasMedico(m);
    }

    public Consulta buscarConsultaPorId(int id) {
        return controladorConsulta.buscarConsultaPorId(id);
    }

    public Consulta consultaDoMomento(LocalDateTime horaDoSistema, Medico m) {
    	return controladorConsulta.consultaDoMomento(horaDoSistema, m);
    }
    
    public void recuperarConsultas() throws ClassNotFoundException, IOException {
    	controladorConsulta.recuperarConsultas();
    }
    
    public void salvarConsultas() throws IOException {
    	controladorConsulta.salvarConsultas();
    }
    
    public boolean consultaVazia() {
		return controladorConsulta.consultaVazia();
	}


    // DIAGNOSTICO

    public void cadastrarDiagnostico(Diagnostico dg, Consulta c) {
    	controladorDiagnostico.cadastrarDiagnostico(dg, c);
    }

    public Diagnostico buscarDiagnosticoPorConsulta(Consulta c) {
        return controladorDiagnostico.buscarDiagnosticoPorConsulta(c);
    }

    public List<Diagnostico> listarDiagnosticoPorConsulta(Consulta c) {
        return controladorDiagnostico.listarDiagnosticoPorConsulta(c);
    }


}
