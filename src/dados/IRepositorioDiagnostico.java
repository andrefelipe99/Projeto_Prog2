package dados;

import java.util.List;
import negocio.Consulta;
import negocio.Diagnostico;

public interface IRepositorioDiagnostico {
    public void cadastrarDiagnostico(Diagnostico dg);
    public void atualizarDiagnostico(Diagnostico dg);
    public Diagnostico buscarDiagnosticoPorConsulta(Consulta c);
    public Diagnostico buscarDiagnosticoPorId(int id);
    public List<Diagnostico> listarDiagnosticoPorConsulta(Consulta c);
}
