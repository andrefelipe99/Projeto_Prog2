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
    public void cadastrarDiagnostico(Diagnostico dg) {
    	if(dg != null && dg.getConsulta() != null) {
    		diagnostico.cadastrarDiagnostico(dg);
    	}
    	else { //exception dados invalidos
    		System.out.println("Dados invalidos diagnostico");
    	}
    }


    public void atualizarDiagnostico(Diagnostico dg) {
    	if(dg != null && dg.getConsulta() != null) {
    		diagnostico.atualizarDiagnostico(dg);
    	}
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
