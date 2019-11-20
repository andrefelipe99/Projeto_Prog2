package controladores;

import dados.RepositorioMedico;
import javafx.collections.ObservableList;
import negocio.Medico;

public class ControladorMedico implements IControladorMedico{
	private RepositorioMedico medicos;

	private static ControladorMedico instance;
	
	private ControladorMedico(){
		medicos = new RepositorioMedico();
	}
	
	public ControladorMedico getInstance() {
		if(instance == null) {
			instance = new ControladorMedico();
		}
		
		return instance;
	}

	public void cadastrarMedico(Medico m) {
		if(m != null && m.getArea()!= null && m.getArea().isEmpty() == false
				&& m.getCpf()!= null && m.getCpf().isEmpty() == false
				&& m.getNome()!= null && m.getNome().isEmpty() == false
				&& m.getCrm()!= null && m.getCrm().isEmpty() == false
				&& m.getIdade() >= 22) {
			medicos.cadastrarMedico(m);
		}
		else {
			System.out.println("Medico dados invalidos");
			//exception
		}
	}

	public void removerMedico(String crm) {
		if(crm!= null && crm.isEmpty() == false) {
			medicos.removerMedico(crm);
		}
		else {
			System.out.println("crm invalido");
			//exception
		}
	}

	public ObservableList<Medico> listarMedicos() {
		return medicos.listarMedicos();
	}
	
	public Medico buscarMedico(String crm) {
		return medicos.buscarMedico(crm);
	}
}
