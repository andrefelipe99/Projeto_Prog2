package controladores;

import java.io.IOException;
import java.util.List;

import dados.RepositorioPaciente;
import dados.RepositorioPacienteFile;
import exceptions.DadosInvalidosException;
import exceptions.PacienteExistenteException;
import negocio.Paciente;

public class ControladorPaciente {

    private RepositorioPaciente paciente;
    private RepositorioPacienteFile pacienteFile;

    public ControladorPaciente() {
        this.paciente = new RepositorioPaciente();
        this.pacienteFile = new RepositorioPacienteFile();
    }

    public void cadastrarPaciente(Paciente p) throws DadosInvalidosException, PacienteExistenteException {
        paciente.cadastrarPaciente(p);

    }

    public void removerPaciente(Paciente p) {
        paciente.removerPaciente(p);
    }

    public Paciente buscarPaciente(String cpf) {
        return paciente.buscarPaciente(cpf);
    }

    public List<Paciente> listarPacientes() {
        return paciente.listarPacientes();
    }

    public void recuperarPacientes() throws ClassNotFoundException, IOException {
        paciente.setarPacientesRecuperados(pacienteFile.recuperarPacientes());
    }

    public void salvarPacientes() throws IOException {
        pacienteFile.salvarPacientes(paciente.listarPacientes());

    }

    public boolean pacienteVazio() {
        return paciente.pacienteVazio();
    }
}
