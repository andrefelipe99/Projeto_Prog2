package controladores;

import java.util.List;

import dados.RepositorioMedico;
import exceptions.DadosInvalidosException;
import exceptions.MedicoExistenteException;
import negocio.Medico;

public class ControladorMedico implements IControladorMedico{
	private RepositorioMedico medico;

	public ControladorMedico() {
		this.medico = new RepositorioMedico();
	}

	public void cadastrarMedico( Medico m) throws DadosInvalidosException, MedicoExistenteException {
		medico.cadastrarMedico(m);
	}

	public void removerMedico(Medico m) {
		medico.removerMedico(m);
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

    public boolean medicoExiste(Medico m) {
        return medico.medicoExiste(m);
    }
    
    public Medico buscarMedico(String crm) {
    	return medico.buscarMedico(crm);
    }

        
}
