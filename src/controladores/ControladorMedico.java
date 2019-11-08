package controladores;

import java.util.List;

import dados.RepositorioMedico;
import negocio.Medico;

public class ControladorMedico implements IControladorMedico{
	private RepositorioMedico medico;

	public ControladorMedico() {
		this.medico = new RepositorioMedico();
	}

	public void cadastrarMedico(Medico m) {
		if(m != null && m.getArea()!= null && m.getArea().isEmpty() == false
				&& m.getCpf()!= null && m.getCpf().isEmpty() == false
				&& m.getNome()!= null && m.getNome().isEmpty() == false
				&& m.getCrm()!= null && m.getCrm().isEmpty() == false
				&& m.getIdade() >= 22) {
			medico.cadastrarMedico(m);
		}
		else {
			System.out.println("Medico dados invalidos");
			//exception
		}
	}

	public void removerMedico(String crm) {
		if(crm!= null && crm.isEmpty() == false) {
			medico.removerMedico(crm);
		}
		else {
			System.out.println("crm invalido");
			//exception
		}
	}

	public List<Medico> listarMedicos() {
		return medico.listarMedicos();
	}
        
        public boolean login(String crm, String senha){
            for(Medico m: medico.listarMedicos()){
                if(crm.equals(m.getCrm())){
                    if(senha.equals(m.getSenha())){
                        return true;
                    }
                }
            }
            return false;
        } 
}
