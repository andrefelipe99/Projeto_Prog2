package controladores;

import java.util.List;

import exceptions.DadosInvalidosException;
import exceptions.MedicoExistenteException;
import negocio.Medico;

public interface IControladorMedico {
	public void cadastrarMedico(Medico m) throws DadosInvalidosException, MedicoExistenteException;
	public void removerMedico(Medico m);
	public List<Medico> listarMedicos();
    public boolean login(String crm, String senha);
}
