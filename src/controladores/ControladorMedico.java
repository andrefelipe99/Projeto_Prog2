package controladores;

import java.io.IOException;
import java.util.List;

import dados.RepositorioMedico;
import dados.RepositorioMedicoFile;
import exceptions.DadosInvalidosException;
import exceptions.MedicoExistenteException;
import negocio.Consulta;
import negocio.Medico;

public class ControladorMedico implements IControladorMedico {

    private RepositorioMedico medico;
    private RepositorioMedicoFile medicoFile;

    public ControladorMedico() {
        this.medico = new RepositorioMedico();
        this.medicoFile = new RepositorioMedicoFile();
    }

    public void cadastrarMedico(Medico m) throws DadosInvalidosException, MedicoExistenteException, IOException {
        medico.cadastrarMedico(m);
    }

    public void removerMedico(Medico m, List<Consulta> consultas) throws IOException {
        medico.removerMedico(m, consultas);
    }

    public boolean login(String crm, String senha) {
        for (Medico m : medico.listarMedicos()) {
            if (crm.equalsIgnoreCase(m.getCrm())) {
                if (senha.equals(m.getSenha())) {
                    System.out.println("Medico logado");
                    return true;
                } else { //exception senha errada
                    System.out.println("Senha errada");
                }
            }
        }
        return false;
    }

    public List<Medico> listarMedicos() {
        return medico.listarMedicos();
    }

    public boolean medicoExiste(Medico m) {
        return medico.medicoExiste(m);
    }

    public Medico buscarMedico(String crm) {
        return medico.buscarMedico(crm);
    }

    public void recuperarMedicos() throws ClassNotFoundException, IOException {
        medico.setarMedicosRecuperados(medicoFile.recuperarMedicos());
    }

    public void salvarMedicos() throws IOException {
        medicoFile.salvarMedicos(medico.listarTodosMedicos());
    }

    public boolean medicoVazio() {
        return medico.medicoVazio();
    }

}
