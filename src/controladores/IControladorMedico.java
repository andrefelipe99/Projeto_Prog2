package controladores;

import java.io.IOException;
import java.util.List;

import exceptions.DadosInvalidosException;
import exceptions.MedicoExistenteException;
import negocio.Consulta;
import negocio.Medico;

public interface IControladorMedico {

    public void cadastrarMedico(Medico m) throws DadosInvalidosException, MedicoExistenteException, IOException;

    public void removerMedico(Medico m, List<Consulta> consultas) throws IOException;

    public List<Medico> listarMedicos();

    public boolean login(String crm, String senha);
}
