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

import negocio.Medico;

public class RepositorioMedicoFile {
	private int numeroMedicos;
	
	private FileOutputStream infoEscrever;
	private ObjectOutputStream escreverNoArquivo;
	
	private FileInputStream infoLer;
	private ObjectInputStream lerNoArquivo;
	
	private BufferedReader le;
	private BufferedWriter escreve;
	
	public RepositorioMedicoFile() {
		numeroMedicos = 0;
	}
	
	public void salvarMedicos(List<Medico> medico) throws IOException {
		File medicos = new File("src/dados/arquivos/repositorios/repositorioMedico.txt");
		
		if(medicos.exists()) {
			medicos.delete();
			medicos.createNewFile();
		}
		else {
			medicos.createNewFile();
		}
		
		infoEscrever = new FileOutputStream(medicos);
    	escreverNoArquivo = new ObjectOutputStream(infoEscrever);
		
		medico.stream().forEach(
				(Medico m)->{
					try {
						escreverNoArquivo.writeObject(m);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
		
		
			salvarNumeroMedicos(medico.size());
	}
	
	private void salvarNumeroMedicos(int i) throws IOException {
		File arquivoNumeroMedicos = new File("src/dados/arquivos/numeroMedicos.txt");
		escreve = new BufferedWriter(new FileWriter(arquivoNumeroMedicos));
		
		String salvar = Integer.toString(i);
		escreve.write(salvar);
		
		escreve.flush();
		
	}
	
	public List<Medico> recuperarMedicos() throws IOException, ClassNotFoundException {
		List<Medico> medicosRecuperados = new ArrayList<Medico>();
		
		File medicos = new File("src/dados/arquivos/repositorios/repositorioMedico.txt");
		
		infoLer = new FileInputStream(medicos);
		
		if(!medicos.exists()) {
			medicos.createNewFile();
		}
		
		Medico medico;
		
		recuperarNumeroMedicos();
		
		if(numeroMedicos != 0) {
			lerNoArquivo = new ObjectInputStream(infoLer);
			for(int i = 0; i < numeroMedicos; i++) {
				medico = (Medico) lerNoArquivo.readObject();
				medicosRecuperados.add(medico);
			}
			
		}
			
		return medicosRecuperados;
	}
	
	private void recuperarNumeroMedicos() throws IOException {
		File arquivoNumeroMedicos = new File("src/dados/arquivos/numeroMedicos.txt");
		
		le = new BufferedReader(new FileReader(arquivoNumeroMedicos));
		
		setNumeroMedicos(Integer.parseInt(le.readLine()));
		
	}
	
	private void setNumeroMedicos(int i) {
		this.numeroMedicos = i;
	}
}

