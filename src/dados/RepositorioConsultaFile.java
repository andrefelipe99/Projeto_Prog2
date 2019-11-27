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

import negocio.Consulta;

public class RepositorioConsultaFile{
	private int numeroConsultas;
	
	private FileOutputStream infoEscrever;
	private ObjectOutputStream escreverNoArquivo;
	
	private FileInputStream infoLer;
	private ObjectInputStream lerNoArquivo;
	
	private BufferedReader le;
	private BufferedWriter escreve;
	
	public RepositorioConsultaFile(){
		numeroConsultas = 0;
	}

	public void salvarConsultas(List<Consulta> consulta) throws IOException {
		File consultas = new File("src/dados/arquivos/repositorios/repositorioDeConsultas.txt");
		
		if(consultas.exists()) {
			consultas.delete();
			consultas.createNewFile();
		}
		else {
			consultas.createNewFile();
		}
		
		infoEscrever = new FileOutputStream(consultas);
		escreverNoArquivo = new ObjectOutputStream(infoEscrever);
		
		consulta.stream().forEach((Consulta c)->{
			try {
				escreverNoArquivo.writeObject(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		salvarNumeroConsultas(consulta.size());
	}
	
	private void salvarNumeroConsultas(int i) throws IOException {
		File arquivoNumeroConsultas = new File("src/dados/arquivos/numeroConsultas.txt");
		escreve = new BufferedWriter(new FileWriter(arquivoNumeroConsultas));
		
		escreve.write(Integer.toString(i));
		
		escreve.flush();

	}
	
	public List<Consulta> recuperarConsultas() throws IOException, ClassNotFoundException{
		List<Consulta> consultasRecuperadas = new ArrayList<Consulta>();
		
		File consultas = new File("src/dados/arquivos/repositorios/repositorioDeConsultas.txt");
		
		if(!consultas.exists()){
			consultas.createNewFile();
		}
		
		infoLer = new FileInputStream(consultas);

		recuperarNumeroConsultas();
		
		Consulta consulta;
		
		if(numeroConsultas != 0) {
			lerNoArquivo = new ObjectInputStream(infoLer);
			
			for(int i = 0; i < numeroConsultas; i++){
				consulta = (Consulta) lerNoArquivo.readObject();
				consultasRecuperadas.add(consulta);
			}
			
			
		}
		
		return consultasRecuperadas;
	}
	
	private void recuperarNumeroConsultas() throws NumberFormatException, IOException {
		File arquivoNumeroConsultas = new File("src/dados/arquivos/numeroConsultas.txt");
		le = new BufferedReader(new FileReader(arquivoNumeroConsultas));
		
		setNumeroConsultas(Integer.parseInt(le.readLine()));
	}
	
	private void setNumeroConsultas(int i) {
		this.numeroConsultas = i;
	}
}
