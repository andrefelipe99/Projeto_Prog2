package dados;

import java.util.List;

import exceptions.DadosInvalidosException;
import exceptions.MedicoExistenteException;
import negocio.Medico;

public interface IRepositorioMedico {
	public void cadastrarMedico(Medico m) throws MedicoExistenteException, DadosInvalidosException;
	public void removerMedico (Medico m);
	public boolean medicoExiste(Medico m);
	public Medico buscarMedico (String crm);
	public List<Medico> listarMedicos();
}
