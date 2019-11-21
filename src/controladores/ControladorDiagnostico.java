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
    	if(dg != null && c.getDiagnostico() == null) {
    		diagnostico.cadastrarDiagnostico(dg, c);
    	}
    	else { //exception dados invalidos
    		System.out.println("Dados invalidos diagnostico");
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
