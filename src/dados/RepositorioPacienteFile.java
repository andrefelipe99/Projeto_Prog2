package dados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import negocio.Paciente;

public class RepositorioPacienteFile {

    private int numeroPacientes;

    private FileOutputStream infoEscrever;
    private ObjectOutputStream escreverNoArquivo;

    private FileInputStream infoLer;
    private ObjectInputStream lerNoArquivo;

    private BufferedReader le;
    private BufferedWriter escreve;

    public RepositorioPacienteFile() {
        numeroPacientes = 0;
    }

    public void salvarPacientes(List<Paciente> paciente) throws IOException {
        File pacientes = new File("src/dados/arquivos/repositorios/repositorioPacientes.txt");

        if (pacientes.exists()) {
            pacientes.delete();
            pacientes.createNewFile();
        } else {
            pacientes.createNewFile();
        }

        infoEscrever = new FileOutputStream(pacientes);
        escreverNoArquivo = new ObjectOutputStream(infoEscrever);

        paciente.stream().forEach((Paciente p) -> {
            try {
                escreverNoArquivo.writeObject(p);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        salvarNumeroPacientes(paciente.size());
    }

    private void salvarNumeroPacientes(int i) throws IOException {
        File arquivoNumeroPacientes = new File("src/dados/arquivos/numeroPacientes.txt");
        escreve = new BufferedWriter(new FileWriter(arquivoNumeroPacientes));

        escreve.write(Integer.toString(i));

        escreve.flush();
    }

    public List<Paciente> recuperarPacientes() throws IOException, ClassNotFoundException {
        List<Paciente> pacientesRecuperados = new ArrayList<Paciente>();

        File pacientes = new File("src/dados/arquivos/repositorios/repositorioPacientes.txt");

        if (!pacientes.exists()) {
            pacientes.createNewFile();
        }

        infoLer = new FileInputStream(pacientes);

        recuperarNumeroPacientes();

        Paciente paciente;

        if (numeroPacientes != 0) {
            lerNoArquivo = new ObjectInputStream(infoLer);

            for (int i = 0; i < numeroPacientes; i++) {
                paciente = (Paciente) lerNoArquivo.readObject();
                pacientesRecuperados.add(paciente);
            }

        }

        return pacientesRecuperados;
    }

    private void recuperarNumeroPacientes() throws NumberFormatException, IOException {
        File arquivoNumeroPacientes = new File("src/dados/arquivos/numeroPacientes.txt");

        le = new BufferedReader(new FileReader(arquivoNumeroPacientes));

        setNumeroPacientes(Integer.parseInt(le.readLine()));
    }

    private void setNumeroPacientes(int i) {
        this.numeroPacientes = i;
    }
}
