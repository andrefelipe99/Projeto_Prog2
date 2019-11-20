package dados;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import negocio.Medico;

public class RepositorioMedico implements IRepositorioMedico{
	 private List<Medico> medicos;

	    public RepositorioMedico() {
			this.medicos = new ArrayList<>();
		}

	    public void cadastrarMedico(Medico m) {
	    	if(this.MedicoExiste(m) == false) {
	    		medicos.add(m);
	    		System.out.println("Medico cadastrado");
	    	}
	    	else {
	    		System.out.println("Medico ja existente");
	    		//exception
	    	}

	    }

	    public void removerMedico (String crm) {
	    	Medico m = this.buscarMedico(crm);
	    	if(m != null) {
	    		medicos.remove(m);
	    		System.out.println("Medico removido");
	    	}
	    	else {
	    		System.out.println("Medico não encontrado");
	    		//exception
	    	}
	    }

	    public boolean MedicoExiste(Medico m) {
	    	for(int i = 0; i < this.medicos.size(); i++) {
	    		if(medicos.get(i).equals(m) == true) {
	    			return true;
	    		}
	    	}
	    	return false;
	    }

	    public Medico buscarMedico (String crm) {
	    	Medico m = null;
	    	for(int i = 0; i < this.medicos.size(); i++) {
	    		if(medicos.get(i).getCrm().equals(crm)) {
	    			m = medicos.get(i);
	    		}
	    	}
	    	return m;
	    }

	    public ObservableList<Medico> listarMedicos() {
	    	ObservableList<Medico> lista = FXCollections.observableArrayList();
	    	for(int i = 0; i < this.medicos.size(); i++) {
	    		if(medicos.get(i) != null) {
	    			lista.add(medicos.get(i));
	    		}
	    	}
	    	return lista;
	    }
}
