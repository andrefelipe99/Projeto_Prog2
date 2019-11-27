package dados;

import java.util.List;

import exceptions.DiagnosticoJaExiste;
import negocio.Consulta;
import negocio.Diagnostico;

public interface IRepositorioDiagnostico {

    public void cadastrarDiagnostico(Diagnostico dg, Consulta c) throws DiagnosticoJaExiste;

    public Diagnostico buscarDiagnosticoPorConsulta(Consulta c);

    public List<Diagnostico> listarDiagnosticoPorConsulta(Consulta c);
}
