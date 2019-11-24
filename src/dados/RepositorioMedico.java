package dados;

import java.util.ArrayList;
import java.util.List;

import exceptions.DadosInvalidosException;
import exceptions.MedicoExistenteException;
import negocio.Medico;

public class RepositorioMedico implements IRepositorioMedico{
	 private List<Medico> medicos;

	    public RepositorioMedico() {
			this.medicos = new ArrayList<>();
		}

	    public void cadastrarMedico(Medico m) throws MedicoExistenteException, DadosInvalidosException {
	    	if(!medicoExiste(m)) {
	    		if(m != null && m.getArea()!= null && m.getArea().isEmpty() == false
	    				&& m.getCpf()!= null && m.getCpf().isEmpty() == false
	    				&& m.getNome()!= null && m.getNome().isEmpty() == false
	    				&& m.getCrm()!= null && m.getCrm().isEmpty() == false
	    				&& m.getIdade() >= 22 && m.getIdade() < 100) {
	    			
	    			
	    			medicos.add(m);
	    		}
	    		else {
	    			throw new DadosInvalidosException();
	    		}
	    	}
	    	else {
	    		throw new MedicoExistenteException();

	    	}

	    }
	    
	    public void removerMedico (Medico m) {
	    	medicos.remove(m);
	    }

	    public boolean medicoExiste(Medico m) {
	    	for(int i = 0; i < this.medicos.size(); i++) {
	    		if(medicos.get(i).getCrm().equalsIgnoreCase(m.getCrm())
	    				|| medicos.get(i).getCpf().equalsIgnoreCase(m.getCpf())) {
	    			return true;
	    		}
	    	}
	    	return false;
	    }

	    public Medico buscarMedico (String crm) {
	    	Medico m = null;
	    	for(int i = 0; i < this.medicos.size(); i++) {
	    		if(medicos.get(i).getCrm().equals(crm) == true) {
	    			m = medicos.get(i);
	    		}
	    	}
	    	return m;
	    }

	    public List<Medico> listarMedicos() {
	    	List<Medico> lista = new ArrayList<> ();
	    	for(int i = 0; i < this.medicos.size(); i++) {
	    		if(medicos.get(i) != null) {
	    			lista.add(medicos.get(i));
	    		}
	    	}
	    	return lista;
	    }

}
