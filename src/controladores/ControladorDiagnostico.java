package controladores;

import dados.RepositorioDiagnostico;
import java.util.List;
import negocio.Consulta;
import negocio.Diagnostico;

public class ControladorDiagnostico implements IControladorDiagnostico{

    private RepositorioDiagnostico diagnostico;
    
    public ControladorDiagnostico(){
        this.diagnostico = new RepositorioDiagnostico();
    }
    
    @Override
    public void cadastrarDiagnostico(Diagnostico dg, Consulta c) {
        diagnostico.cadastrarDiagnostico(dg, c);
    }

    @Override
    public Diagnostico buscarDiagnosticoPorConsulta(Consulta c) {
       return diagnostico.buscarDiagnosticoPorConsulta(c);
    }

    @Override
    public Diagnostico buscarDiagnosticoPorId(int id) {
        return diagnostico.buscarDiagnosticoPorId(id);
    }

    @Override
    public List<Diagnostico> listarDiagnosticoPorConsulta(Consulta c) {
        return diagnostico.listarDiagnosticoPorConsulta(c);
    }
    
}
