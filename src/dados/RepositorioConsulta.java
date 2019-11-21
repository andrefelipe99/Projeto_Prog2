package dados;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public class RepositorioConsulta implements IRepositorioConsulta {
	private List<Consulta> consultas;

        public RepositorioConsulta(){
            this.consultas = new ArrayList<>();
        }
	@Override
	public void cadastrarConsulta(Consulta c) {
		if(!consultas.contains(c) && this.buscarConsultaPorId(c.getId()) == null) {
                    consultas.add(c);
                    System.out.println("criou consulta");
		}
        else { //exception consulta ja existe
                  	System.out.println("nao criou consulta");
        }
	}

	@Override
	public void removerConsulta(Consulta c) {
		if(consultas.contains(c)) {
			consultas.remove(c);
			System.out.println("Remover consulta");
		}
		else { //exception consulta nao encontrada
			System.out.println("Consulta nao encontrada");
		}

	}

	@Override
	public List<Consulta> listarConsultasPaciente(Paciente p) {
		List<Consulta> consultasPaciente = new ArrayList<Consulta>();

		for(Consulta c: consultas) {
			if(c.getPaciente() == p) {
				consultasPaciente.add(c);
			}
		}

		return consultasPaciente;
	}

	@Override
	public List<Consulta> listarConsultasMedico(Medico m) {
		List<Consulta> consultasMedico = new ArrayList<Consulta>();

		for(Consulta c: consultas) {
			if(c.getMedico() == m) {
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

}
