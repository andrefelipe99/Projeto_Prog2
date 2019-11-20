package controladores;

import java.util.List;

import dados.RepositorioConsulta;
import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public class ControladorConsulta implements IControladorConsulta {
	private RepositorioConsulta consultasSalvas;

	ControladorConsulta(){
		consultasSalvas = new RepositorioConsulta();
	}

	@Override
	public void cadastrarConsulta(Consulta c) {
		if(c != null && c.getMedico() != null && c.getPaciente() != null
				&& c.getDataHoraFim() != null && c.getDataHoraInicio() != null
				&& c.getDescricao() != null && c.getDescricao().isEmpty() == false
				&& c.getId()> 0) {
			consultasSalvas.cadastrarConsulta(c);
		}
		else { //exception dados invalidos
			System.out.println("Consulta dados invalidos");
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
}
