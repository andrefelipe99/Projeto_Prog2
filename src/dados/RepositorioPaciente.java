package dados;
import java.util.*;

import negocio.Paciente;

	public class RepositorioPaciente implements IRepositorioPaciente{

	    private List<Paciente> pacientes;

	    public RepositorioPaciente() {
			this.pacientes = new ArrayList<>();
		}

	    public void cadastrarPaciente(Paciente p) {
	    	if(this.PacienteExiste(p) == false) {
	    		pacientes.add(p);
	    		System.out.println("Paciente cadastrado");
	    	}
	    	else {
	    		System.out.println("cpf ja existente");
	    		//exception
	    	}

	    }

	    public void removerPaciente (String cpf) {
	    	Paciente p = this.buscarPaciente(cpf);
	    	if(p != null) {
	    		pacientes.remove(p);
	    		System.out.println("Paciente removido");
	    	}
	    	else {
	    		System.out.println("Paciente não encontrado");
	    		//exception
	    	}
	    }

	    public boolean PacienteExiste(Paciente p) {
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
	}

