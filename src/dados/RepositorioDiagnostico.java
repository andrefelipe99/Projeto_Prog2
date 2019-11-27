package dados;

import java.util.ArrayList;
import java.util.List;
import negocio.Consulta;
import negocio.Diagnostico;

public class RepositorioDiagnostico implements IRepositorioDiagnostico {

    private List<Diagnostico> diagnosticos;

    public RepositorioDiagnostico() {
        this.diagnosticos = new ArrayList<>();
    }

    @Override
    public void cadastrarDiagnostico(Diagnostico dg, Consulta c) {
        if (c.getDiagnostico() == null) {
            c.setDiagnostico(dg);
            diagnosticos.add(dg);
            System.out.println("Diagnostico criado");
        } else { //exception consulta ja tem um diagnostico cadastrado
            System.out.println("Diagnostico ja cadastrado");
        }
    }

    @Override
    public Diagnostico buscarDiagnosticoPorConsulta(Consulta c) {

        for (Diagnostico dg : this.diagnosticos) {
            if (c.getDiagnostico().equals(dg)) {
                return dg;
            }
        }
        return null;
    }

    @Override
    public List<Diagnostico> listarDiagnosticoPorConsulta(Consulta c) {
        List<Diagnostico> diagnostico = new ArrayList<>();

        for (Diagnostico dg : this.diagnosticos) {
            if (c.getDiagnostico().equals(dg)) {
                diagnostico.add(dg);
            }
        }
        return diagnostico;
    }

    @Override
    public String toString() {
        return "RepositorioDiagnostico{" + "diagnosticos=" + diagnosticos + '}';
    }

}
