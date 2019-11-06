package dados;

import java.util.ArrayList;
import java.util.List;
import negocio.Consulta;
import negocio.Diagnostico;

public class RepositorioDiagnostico implements IRepositorioDiagnostico{

    private List<Diagnostico> diagnosticos;
    
    public RepositorioDiagnostico(){
        this.diagnosticos = new ArrayList<>();
    }
    
    @Override
    public void cadastrarDiagnostico(Diagnostico dg) {
        if(dg != null){
            this.diagnosticos.add(dg);
        }
    }

    @Override
    public Diagnostico buscarDiagnosticoPorConsulta(Consulta c) {
       
        for(Diagnostico dg: this.diagnosticos){
            if(dg.getConsulta().equals(c)){
                return dg;
            }
        }
        return null;
    }

    @Override
    public Diagnostico buscarDiagnosticoPorId(int id) {
       for(Diagnostico dg: this.diagnosticos){
           if(dg.getId() == id){
               return dg;
           }
       } 
       return null;
    }

    @Override
    public List<Diagnostico> listarDiagnosticoPorConsulta(Consulta c) {
        List<Diagnostico> diagnostico = new ArrayList<>();
        
        for(Diagnostico dg: this.diagnosticos){
            if(dg.getConsulta().equals(c)){
                diagnostico.add(dg);
            }
        }
        return diagnostico;
    }
    
}
