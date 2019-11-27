package dados;

import java.util.List;
import negocio.Consulta;
import negocio.Diagnostico;

public interface IRepositorioDiagnostico {

    public void cadastrarDiagnostico(Diagnostico dg, Consulta c);

    public Diagnostico buscarDiagnosticoPorConsulta(Consulta c);

    public List<Diagnostico> listarDiagnosticoPorConsulta(Consulta c);
}
