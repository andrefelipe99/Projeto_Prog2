package dados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exceptions.DadosInvalidosException;
import exceptions.MedicoExistenteException;
import negocio.Consulta;
import negocio.Medico;

public class RepositorioMedico implements IRepositorioMedico {

    private List<Medico> medicos;

    public RepositorioMedico() {
        this.medicos = new ArrayList<>();
    }

    public void cadastrarMedico(Medico m) throws MedicoExistenteException, DadosInvalidosException, IOException {
        if (!medicoExiste(m)) {
            if (m != null && m.getArea() != null && m.getArea().isEmpty() == false
                    && m.getCpf() != null && m.getCpf().isEmpty() == false
                    && m.getNome() != null && m.getNome().isEmpty() == false
                    && m.getCrm() != null && m.getCrm().isEmpty() == false
                    && m.getIdade() >= 22 && m.getIdade() < 100) {

                medicos.add(m);

                salvarLoginSenha(m);
            } else {
                throw new DadosInvalidosException();
            }
        } else {
            throw new MedicoExistenteException();

        }

    }

    private void salvarLoginSenha(Medico m) throws IOException {
        File logins = new File("src/dados/arquivos/logins.txt");

        if (!logins.exists()) {
            logins.createNewFile();
        }
        FileWriter salvarLoginSenha = new FileWriter(logins, true);

        salvarLoginSenha.write(m.getCrm() + "," + m.getSenha() + "\n");

        salvarLoginSenha.close();
    }

    public void removerMedico(Medico m, List<Consulta> consultas) throws IOException {
        if (consultas.isEmpty() || consultas == null) {
            removerLogin(m);
            medicos.remove(m);
        } else {
            removerLogin(m);
            m.setAtivo(false);
        }

    }

    private void removerLogin(Medico m) throws IOException {
        File removendo = new File("src/dados/arquivos/loginsAux.txt");
        File lerLogins = new File("src/dados/arquivos/logins.txt");

        if (removendo.exists()) {
            removendo.delete();
            removendo.createNewFile();
        }

        BufferedReader le = new BufferedReader(new FileReader(lerLogins));

        while (le.ready()) {
            String[] lida = le.readLine().split(",");

            if (lida != null) {
                if (!(lida[0].contentEquals(m.getCrm()) && lida[1].contentEquals(m.getSenha()))) {
                    atualizarLoginAux(lida[0], lida[1]);
                }
            }

        }

        le.close();

        System.gc();

        lerLogins.delete();
        removendo.renameTo(lerLogins);

    }

    private void atualizarLoginAux(String crm, String senha) throws IOException {
        File removendo = new File("src/dados/arquivos/loginsAux.txt");
        FileWriter escreve = new FileWriter(removendo, true);

        escreve.write(crm + "," + senha + "\n");
        escreve.close();

        System.gc();
    }

    public boolean medicoExiste(Medico m) {
        for (int i = 0; i < this.medicos.size(); i++) {
            if (medicos.get(i).getCrm().equalsIgnoreCase(m.getCrm())
                    || medicos.get(i).getCpf().equalsIgnoreCase(m.getCpf())) {
                return true;
            }
        }
        return false;
    }

    public Medico buscarMedico(String crm) {
        Medico m = null;
        for (int i = 0; i < this.medicos.size(); i++) {
            if (medicos.get(i).getCrm().equals(crm) == true) {
                m = medicos.get(i);
            }
        }
        return m;
    }

    public List<Medico> listarMedicos() {
        List<Medico> lista = new ArrayList<>();
        for (int i = 0; i < this.medicos.size(); i++) {
            if (medicos.get(i) != null && medicos.get(i).getAtivo()) {
                lista.add(medicos.get(i));
            }
        }
        return lista;
    }

    public List<Medico> listarTodosMedicos() {
        List<Medico> lista = new ArrayList<>();
        for (int i = 0; i < this.medicos.size(); i++) {
            if (medicos.get(i) != null) {
                lista.add(medicos.get(i));
            }
        }
        return lista;
    }

    public void setarMedicosRecuperados(List<Medico> recuperados) {
        medicos.addAll(recuperados);
    }

    public boolean medicoVazio() {
        return medicos.isEmpty();
    }

}
