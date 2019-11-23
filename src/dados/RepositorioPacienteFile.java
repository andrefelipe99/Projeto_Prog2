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
import exceptions.PacienteExistenteException;
import negocio.Paciente;

public class RepositorioPacienteFile {
	File pacientes;
	FileOutputStream infoEscrever;
	ObjectOutputStream escreverNoArquivo;
	
	private int numeroPacientes;
	
	public RepositorioPacienteFile() throws IOException {
		pacientes = new File("src/dados/arquivos/repositorios/repositorioPacientes.txt");
		
		if(!pacientes.exists()) {
			pacientes.createNewFile();
		}
		
		infoEscrever = new FileOutputStream(pacientes);
		escreverNoArquivo = new ObjectOutputStream(infoEscrever);
		
		numeroPacientes = 0;		
		
	}
	
	public void cadastrarPaciente(Paciente p) throws ClassNotFoundException, IOException, PacienteExistenteException, DadosInvalidosException {
    	if(!pacienteExiste(p)) {
    		if(p != null && p.getEndereco()!= null && p.getEndereco().isEmpty() == false
    				&& p.getCpf()!= null && p.getCpf().isEmpty() == false
    				&& p.getNome()!= null && p.getNome().isEmpty() == false
    				&& p.getTelefone()!= null && p.getTelefone().isEmpty() == false
    				&& p.getIdade() > 0) {
    			
    			escreverNoArquivo.writeObject(p);
    			numeroPacientes++;
    		}
    		else {
    			throw new DadosInvalidosException();
    		}
    		
    	}
    	else {
    		throw new PacienteExistenteException();
    	}

    }

	public void removerPaciente(Paciente p) throws ClassNotFoundException, IOException {
		Paciente auxiliar;
		File repositorioAuxiliar = new File("src/dados/arquivos/repositorios/repositorioPacienteAux");
		
		FileInputStream infoLer = new FileInputStream(pacientes);
		ObjectInputStream leitorDeArquivo = new ObjectInputStream(infoLer);
		
		for(int i = 1; i < numeroPacientes; i++) {
			auxiliar = (Paciente)leitorDeArquivo.readObject();
			if(!auxiliar.equals(p)) {
				arquivoPacienteAuxiliar(repositorioAuxiliar, auxiliar);
			}
		}
		
		leitorDeArquivo.close();
		repositorioAuxiliar.renameTo(pacientes);
		numeroPacientes--;
	}

	public void arquivoPacienteAuxiliar(File auxiliar, Paciente p) throws IOException {
		
		if(!auxiliar.exists()) {
			auxiliar.createNewFile();
		}
		
		FileOutputStream infoEscreverAuxiliar = new FileOutputStream(auxiliar);
		ObjectOutputStream escreverNoArquivoAuxiliar = new ObjectOutputStream(infoEscreverAuxiliar);
		
		escreverNoArquivoAuxiliar.writeObject(p);
		escreverNoArquivoAuxiliar.close();	
		
	}

    public boolean pacienteExiste(Paciente p) throws ClassNotFoundException, IOException {
    	Paciente auxiliar;
    	boolean existe = false;
    	
    	FileInputStream infoLer = new FileInputStream(pacientes);
		ObjectInputStream leitorDeArquivo = new ObjectInputStream(infoLer);
    	
    	if(numeroPacientes !=0) {
			for(int i = 1; i != numeroPacientes; i++) {
				auxiliar = (Paciente) leitorDeArquivo.readObject();
				if(auxiliar.equals(p)) {
					existe = true; 
				}
			}
		}
    	
    	leitorDeArquivo.close();
    	return existe;
    }

    public Paciente buscarPaciente (String cpf) throws ClassNotFoundException, IOException {
    	Paciente p = null;
    	Paciente auxiliar;
    	
    	FileInputStream infoLer = new FileInputStream(pacientes);
		ObjectInputStream leitorDeArquivo = new ObjectInputStream(infoLer);
    	
    	for(int i = 1; i < numeroPacientes; i++) {
    		auxiliar = (Paciente)leitorDeArquivo.readObject();
    		if(auxiliar.getCpf().contentEquals(cpf)) {
    			p = auxiliar;
    		}
    	}
    	
    	leitorDeArquivo.close();
    	return p;
    }

    public List<Paciente> listarPacientes() throws ClassNotFoundException, IOException {
    	List<Paciente> lista = new ArrayList<Paciente>();
    	Paciente auxiliar;
    	
    	FileInputStream infoLer = new FileInputStream(pacientes);
		ObjectInputStream leitorDeArquivo = new ObjectInputStream(infoLer);
		
    	for(int i = 1; i < numeroPacientes; i++) {
    		auxiliar = (Paciente)leitorDeArquivo.readObject();
    		lista.add(auxiliar);
    	}
    	
    	leitorDeArquivo.close();
    	return lista;
    }
	
	
}
