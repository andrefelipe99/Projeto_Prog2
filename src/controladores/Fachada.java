package controladores;

import java.time.LocalDateTime;
import java.util.List;
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

    public void cadastrarPaciente(Paciente p) {
        controladorPaciente.cadastrarPaciente(p);
    }

    public void removerPaciente(String cpf) {
        controladorPaciente.removerPaciente(cpf);
    }

    public List<Paciente> listarPacientes() {
        return controladorPaciente.listarPacientes();
    }

    // MEDICO

    public void cadastrarMedico(Medico m) {
        controladorMedico.cadastrarMedico(m);
    }

    public void removerMedico(String crm) {
        controladorMedico.removerMedico(crm);
    }

    public List<Medico> listarMedicos() {
        return controladorMedico.listarMedicos();
    }

    public boolean login(String crm, String senha) {
        return controladorMedico.login(crm, senha);
    }

    public boolean MedicoExiste(Medico m) {
        return controladorMedico.MedicoExiste(m);
    }
    
    public Medico buscarMedico(String crm) {
    	return controladorMedico.buscarMedico(crm);
    }

    // CONSULTA

    public void cadastrarConsulta(Consulta c) {
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
