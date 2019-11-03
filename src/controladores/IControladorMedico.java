package controladores;

import java.util.List;
import negocio.Medico;

public interface IControladorMedico {
	public void cadastrarMedico(Medico m);
	public void removerMedico(String crm);
	public List<Medico> listarMedicos();
}
