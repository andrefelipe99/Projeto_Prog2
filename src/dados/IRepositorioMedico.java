package dados;

import java.io.IOException;
import java.util.List;

import exceptions.DadosInvalidosException;
import exceptions.MedicoExistenteException;
import negocio.Consulta;
import negocio.Medico;

public interface IRepositorioMedico {

    public void cadastrarMedico(Medico m) throws MedicoExistenteException, DadosInvalidosException, IOException;

    public void removerMedico(Medico m, List<Consulta> consultas) throws IOException;

    public boolean medicoExiste(Medico m);

    public Medico buscarMedico(String crm);

    public List<Medico> listarMedicos();
}
