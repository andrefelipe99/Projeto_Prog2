package dados;

import java.util.List;
import negocio.Medico;

public interface IRepositorioMedico {
	public void cadastrarMedico(Medico m);
	public void removerMedico (String crm);
	public boolean MedicoExiste(Medico m);
	public Medico buscarMedico (String crm);
	public List<Medico> listarMedicos();
}
