package controladores;

import java.time.LocalDateTime;
import java.util.List;

import dados.RepositorioConsulta;
import exceptions.ConsultaJaExisteException;
import exceptions.DadosInvalidosException;
import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public class ControladorConsulta implements IControladorConsulta {
	private RepositorioConsulta consultasSalvas;

	ControladorConsulta(){
		consultasSalvas = new RepositorioConsulta();
	}

	@Override
	public void cadastrarConsulta(Consulta c) throws DadosInvalidosException, ConsultaJaExisteException {
		if(c != null && c.getMedico() != null && c.getPaciente() != null
				&& !c.getDataHoraInicio().equals(LocalDateTime.of(1900, 1, 1, 0, 0))
				&& c.getDescricao().isEmpty() == false	&& c.getId()> 0) {
			consultasSalvas.cadastrarConsulta(c);
		}
		else {
			throw new DadosInvalidosException();
		}
	}

	@Override
	public void removerConsulta(Consulta c) {
		consultasSalvas.removerConsulta(c);
	}

	@Override
	public List<Consulta> listarConsultas(){
		return consultasSalvas.listarConsultas();
	}

	@Override
	public List<Consulta> listarConsultasPaciente(Paciente p){
		return consultasSalvas.listarConsultasPaciente(p);
	}

	@Override
	public List<Consulta> listarConsultasMedico(Medico m){
		return consultasSalvas.listarConsultasMedico(m);
	}

    @Override
    public Consulta buscarConsultaPorId(int id) {
    	return consultasSalvas.buscarConsultaPorId(id);
    }

    public Consulta consultaDoMomento(LocalDateTime horaDoSistema, Medico m) {
    	return consultasSalvas.consultaDoMomento(horaDoSistema, m);
    }
}
