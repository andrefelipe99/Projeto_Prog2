package controladores;

import java.util.List;

import exceptions.DadosInvalidosException;
import exceptions.DiagnosticoJaExiste;
import negocio.Consulta;
import negocio.Diagnostico;

public interface IControladorDiagnostico {

    public void cadastrarDiagnostico(Diagnostico dg, Consulta c) throws DiagnosticoJaExiste, DadosInvalidosException;

    public Diagnostico buscarDiagnosticoPorConsulta(Consulta c);

    public List<Diagnostico> listarDiagnosticoPorConsulta(Consulta c);
}
