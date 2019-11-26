package dados;
import java.util.*;

import exceptions.DadosInvalidosException;
import exceptions.PacienteExistenteException;
import negocio.Paciente;

	public class RepositorioPaciente implements IRepositorioPaciente{

	    private List<Paciente> pacientes;

	    public RepositorioPaciente() {
			this.pacientes = new ArrayList<>();
		}

	    public void cadastrarPaciente(Paciente p) throws DadosInvalidosException, PacienteExistenteException {
	    	if(!pacienteExiste(p)) {
	    		if(p != null && p.getEndereco()!= null && p.getEndereco().isEmpty() == false
	    				&& p.getCpf()!= null && p.getCpf().isEmpty() == false
	    				&& p.getNome()!= null && p.getNome().isEmpty() == false
	    				&& p.getTelefone()!= null && p.getTelefone().isEmpty() == false
	    				&& p.getIdade() > 0 && p.getIdade() < 120) {

	    			pacientes.add(p);
	    		}
	    		else {
	    			throw new DadosInvalidosException();
	    		}
	    	}
	    	else {
	    		throw new PacienteExistenteException();
	    	}

	    }

	    public void removerPaciente (Paciente p) {
	    	pacientes.remove(p);
	    }

	    public boolean pacienteExiste(Paciente p) {
	    	for(int i = 0; i < this.pacientes.size(); i++) {
	    		if(pacientes.get(i).getCpf().equals(p.getCpf()) == true) {
	    			return true;
	    		}
	    	}
	    	return false;
	    }

	    public Paciente buscarPaciente (String cpf) {
	    	Paciente p = null;
	    	for(int i = 0; i < this.pacientes.size(); i++) {
	    		if(pacientes.get(i).getCpf().equals(cpf) == true) {
	    			p = pacientes.get(i);
	    		}
	    	}
	    	return p;
	    }

	    public List<Paciente> listarPacientes() {
	    	List<Paciente> lista = new ArrayList<> ();
	    	for(int i = 0; i < this.pacientes.size(); i++) {
	    		if(pacientes.get(i) != null) {
	    			lista.add(pacientes.get(i));
	    		}
	    	}
	    	return lista;
	    }

		public void setarPacientesRecuperados(List<Paciente> recuperados) {
			pacientes.addAll(recuperados);
			
		}
		
		public boolean pacienteVazio() {
			return pacientes.isEmpty();
		}
	}

