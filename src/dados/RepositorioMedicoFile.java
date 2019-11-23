package dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import exceptions.DadosInvalidosException;
import exceptions.MedicoExistenteException;
import negocio.Medico;

public class RepositorioMedicoFile {
	File medicos;
	FileOutputStream infoEscrever;
	ObjectOutputStream escreverNoArquivo;
	
	private int numeroMedicos;
	
	public RepositorioMedicoFile() throws IOException {
		medicos = new File("src/dados/arquivos/repositorios/repositorioMedicos.txt");
		
		if(!medicos.exists()) {
			medicos.createNewFile();
		}
		
		infoEscrever = new FileOutputStream(medicos);
		escreverNoArquivo = new ObjectOutputStream(infoEscrever);
		
		numeroMedicos = 0;		
		
	}
	
	public void cadastrarMedico(Medico m) throws MedicoExistenteException, IOException, ClassNotFoundException, DadosInvalidosException {
		if(!medicoExiste(m)) {
			
			if(m != null && m.getArea()!= null && m.getArea().isEmpty() == false
    			&& m.getCpf()!= null && m.getCpf().isEmpty() == false
    			&& m.getNome()!= null && m.getNome().isEmpty() == false
    			&& m.getCrm()!= null && m.getCrm().isEmpty() == false
    			&& m.getIdade() >= 22) {
    			
    			
    			escreverNoArquivo.writeObject(m);
    			numeroMedicos++;
    		}
    		else {
    			throw new DadosInvalidosException();
    		}
			
		}
		else {
			throw new MedicoExistenteException();
		}
	}
	
	public boolean medicoExiste(Medico m) throws ClassNotFoundException, IOException {
		Medico auxiliar;
		boolean existe = false;
		
		FileInputStream infoLer = new FileInputStream(medicos);
		ObjectInputStream leitorDeArquivo = new ObjectInputStream(infoLer);
		
		if(numeroMedicos !=0) {
			for(int i = 1; i != numeroMedicos; i++) {
				auxiliar = (Medico) leitorDeArquivo.readObject();
				if(auxiliar.equals(m)) {
					existe = true; 
				}
			}
		}
		
		leitorDeArquivo.close();
		
		return existe;
	}
	
	public void removerMedico(Medico m) throws ClassNotFoundException, IOException {
		Medico auxiliar;
		File repositorioAuxiliar = new File("src/dados/arquivos/repositorios/repositorioMedicoAux");
		
		FileInputStream infoLer = new FileInputStream(medicos);
		ObjectInputStream leitorDeArquivo = new ObjectInputStream(infoLer);
		
		for(int i = 1; i < numeroMedicos; i++) {
			auxiliar = (Medico)leitorDeArquivo.readObject();
			if(!auxiliar.equals(m)) {
				arquivoMedicoAuxiliar(repositorioAuxiliar, auxiliar);
			}
		}
		
		leitorDeArquivo.close();
		repositorioAuxiliar.renameTo(medicos);
		numeroMedicos--;
	}

	public void arquivoMedicoAuxiliar(File auxiliar, Medico m) throws IOException {
		
		if(!auxiliar.exists()) {
			auxiliar.createNewFile();
		}
		
		FileOutputStream infoEscreverAuxiliar = new FileOutputStream(auxiliar);
		ObjectOutputStream escreverNoArquivoAuxiliar = new ObjectOutputStream(infoEscreverAuxiliar);
		
		escreverNoArquivoAuxiliar.writeObject(m);
		escreverNoArquivoAuxiliar.close();	
		
	}
	
	public Medico buscarMedico (String crm) throws ClassNotFoundException, IOException {
    	Medico m = null;
    	Medico auxiliar;
    	
    	FileInputStream infoLer = new FileInputStream(medicos);
		ObjectInputStream leitorDeArquivo = new ObjectInputStream(infoLer);
		
    	for(int i = 1; i < numeroMedicos; i++) {
    		auxiliar = (Medico)leitorDeArquivo.readObject();
    		if(auxiliar.getCrm().contentEquals(crm)) {
    			m = auxiliar;
    		}
    	}
    	
    	leitorDeArquivo.close();
    	return m;
    }

    public List<Medico> listarMedicos() throws ClassNotFoundException, IOException {
    	List<Medico> lista = new ArrayList<> ();
    	Medico auxiliar;
    	FileInputStream infoLer = new FileInputStream(medicos);
		ObjectInputStream leitorDeArquivo = new ObjectInputStream(infoLer);
		
    	for(int i = 1; i < numeroMedicos; i++) {
    		auxiliar = (Medico)leitorDeArquivo.readObject();
    		lista.add(auxiliar);
    	}
    	
    	leitorDeArquivo.close();
    	return lista;
    }
}

