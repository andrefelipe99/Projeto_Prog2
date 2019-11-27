package dados;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import exceptions.ConsultaJaExisteException;
import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public class RepositorioConsulta implements IRepositorioConsulta {
	private List<Consulta> consultas;

        public RepositorioConsulta(){
            this.consultas = new ArrayList<>();
        }
	@Override
	public void cadastrarConsulta(Consulta c) throws ConsultaJaExisteException {
		if(validarConsulta(c) && this.buscarConsultaPorId(c.getId()) == null) {
                    consultas.add(c);
		}
        else {
             	throw new ConsultaJaExisteException();
        }
	}

	@Override
	public void removerConsulta(Consulta c) {
		if(consultas.contains(c)) {
			consultas.remove(c);
		}
		else {

		}
	}

	public boolean validarConsulta(Consulta c) {
		for(int i = 0; i < consultas.size(); i++) {
			if(c.getMedico().getCrm() == consultas.get(i).getMedico().getCrm()){
				if((c.getDataHoraInicio().isBefore(consultas.get(i).getDataHoraInicio())
						&& c.getDataHoraFim().isAfter(consultas.get(i).getDataHoraInicio()))
						|| (c.getDataHoraInicio().isBefore(consultas.get(i).getDataHoraFim())
						&& c.getDataHoraFim().isAfter(consultas.get(i).getDataHoraFim()))
						|| c.getDataHoraInicio().equals(consultas.get(i).getDataHoraInicio())) {
					return false;
				}
			}


		}
		return true;
	}

	@Override
	public List<Consulta> listarConsultasPaciente(Paciente p) {
		List<Consulta> consultasPaciente = new ArrayList<Consulta>();

		for(Consulta c: consultas) {
			if(c.getPaciente().equals(p)) {
				consultasPaciente.add(c);
			}
		}

		return consultasPaciente;
	}

	@Override
	public List<Consulta> listarConsultasMedico(Medico m) {
		List<Consulta> consultasMedico = new ArrayList<Consulta>();

		for(Consulta c: consultas) {
			if(c.getMedico().equals(m)) {
				consultasMedico.add(c);
			}
		}

		return consultasMedico;
	}

	@Override
	public List<Consulta> listarConsultas() {
		return consultas;
	}

    @Override
    public Consulta buscarConsultaPorId(int id) {
       for(Consulta c: this.consultas){
           if(c.getId() == id){
               return c;
           }
       }
       return null;
    }

    public Consulta consultaDoMomento(LocalDateTime horaDoSistema, Medico m) {
    	List<Consulta> acharConsultaDoMomento = listarConsultasMedico(m);
    	Consulta localizada = null;
    	for (Consulta consulta : acharConsultaDoMomento) {
			if(consulta.getDataHoraInicio().isAfter(horaDoSistema) && horaDoSistema.isBefore(consulta.getDataHoraFim())){
				localizada = consulta;
			}
		}

    	return localizada;
    }

	public void recuperarConsultas(List<Consulta> consultasRecuperadas) {
		consultas.addAll(consultasRecuperadas);
		
	}
	
	public boolean consultaVazia() {
		return consultas.isEmpty();
	}

}
