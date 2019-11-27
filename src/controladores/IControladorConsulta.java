package controladores;

import java.util.List;

import exceptions.ConsultaJaExisteException;
import exceptions.DadosInvalidosException;
import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public interface IControladorConsulta {

    public void cadastrarConsulta(Consulta c) throws DadosInvalidosException, ConsultaJaExisteException;

    public void removerConsulta(Consulta c);

    public Consulta buscarConsultaPorId(int id);

    public List<Consulta> listarConsultas();

    public List<Consulta> listarConsultasPaciente(Paciente p);

    public List<Consulta> listarConsultasMedico(Medico m);
}
