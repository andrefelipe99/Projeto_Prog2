package controladores;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import dados.RepositorioConsulta;
import dados.RepositorioConsultaFile;
import exceptions.ConsultaJaExisteException;
import exceptions.DadosInvalidosException;
import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public class ControladorConsulta implements IControladorConsulta {

    private RepositorioConsulta consultas;
    private RepositorioConsultaFile consultasFile;

    ControladorConsulta() {
        consultas = new RepositorioConsulta();
        consultasFile = new RepositorioConsultaFile();
    }

    @Override
    public void cadastrarConsulta(Consulta c) throws DadosInvalidosException, ConsultaJaExisteException {
        if (c != null && c.getMedico() != null && c.getPaciente() != null
                && !c.getDataHoraInicio().equals(LocalDateTime.of(1900, 1, 1, 0, 0))
                && c.getDescricao().isEmpty() == false && c.getId() > 0) {
            consultas.cadastrarConsulta(c);
        } else {
            throw new DadosInvalidosException();
        }
    }

    @Override
    public void removerConsulta(Consulta c) {
        consultas.removerConsulta(c);
    }

    @Override
    public List<Consulta> listarConsultas() {
        return consultas.listarConsultas();
    }

    @Override
    public List<Consulta> listarConsultasPaciente(Paciente p) {
        return consultas.listarConsultasPaciente(p);
    }

    @Override
    public List<Consulta> listarConsultasMedico(Medico m) {
        return consultas.listarConsultasMedico(m);
    }

    @Override
    public Consulta buscarConsultaPorId(int id) {
        return consultas.buscarConsultaPorId(id);
    }

    public Consulta consultaDoMomento(LocalDateTime horaDoSistema, Medico m) {
        return consultas.consultaDoMomento(horaDoSistema, m);
    }

    public void recuperarConsultas() throws ClassNotFoundException, IOException {
        consultas.recuperarConsultas(consultasFile.recuperarConsultas());
    }

    public void salvarConsultas() throws IOException {
        consultasFile.salvarConsultas(consultas.listarConsultas());

    }

    public boolean consultaVazia() {
        return consultas.consultaVazia();
    }

}
