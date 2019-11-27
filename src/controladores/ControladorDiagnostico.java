package controladores;

import dados.RepositorioDiagnostico;
import exceptions.DadosInvalidosException;
import exceptions.DiagnosticoJaExiste;

import java.util.List;
import negocio.Consulta;
import negocio.Diagnostico;

public class ControladorDiagnostico implements IControladorDiagnostico {

    private RepositorioDiagnostico diagnostico;

    public ControladorDiagnostico() {
        this.diagnostico = new RepositorioDiagnostico();
    }

    @Override
    public void cadastrarDiagnostico(Diagnostico dg, Consulta c) throws DiagnosticoJaExiste, DadosInvalidosException {
        if (dg != null && !dg.getDescricao().isEmpty() && !dg.getMedicamentos().isEmpty()) {
            diagnostico.cadastrarDiagnostico(dg, c);
        } else {
            throw new DadosInvalidosException();
        }
    }

    @Override
    public Diagnostico buscarDiagnosticoPorConsulta(Consulta c) {
        return diagnostico.buscarDiagnosticoPorConsulta(c);
    }

    @Override
    public List<Diagnostico> listarDiagnosticoPorConsulta(Consulta c) {
        return diagnostico.listarDiagnosticoPorConsulta(c);
    }

}
