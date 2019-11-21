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
		else { //exception dados invalidos
			System.out.println("Medico dados invalidos");

		}
	}

	public void removerMedico(String crm) {
		if(crm!= null && crm.isEmpty() == false) {
			medico.removerMedico(crm);
		}
		else { //exception crm invalido
			System.out.println("crm invalido");

		}
	}

	public boolean login(String crm, String senha){
        for(Medico m: medico.listarMedicos()){
           if(crm.equalsIgnoreCase(m.getCrm())){
               if(senha.equals(m.getSenha())){
            	   System.out.println("Medico logado");
                    return true;
               }
               else { //exception senha errada
            	   System.out.println("Senha errada");
               }
           }
        }
        return false;
   }

	public List<Medico> listarMedicos() {
		return medico.listarMedicos();
	}

    public boolean MedicoExiste(Medico m) {
        return medico.MedicoExiste(m);
    }
    
    public Medico buscarMedico(String crm) {
    	return medico.buscarMedico(crm);
    }

        
}
