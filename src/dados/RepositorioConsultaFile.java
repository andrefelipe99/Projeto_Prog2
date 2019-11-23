package dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import exceptions.ConsultaJaExisteException;
import exceptions.HorarioIndisponivelException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public class RepositorioConsultaFile{
	private File consultas;
	FileOutputStream infoEscrever;
	ObjectOutputStream salvarNoArquivo;
	private int numeroConsultas;
	
	public RepositorioConsultaFile() throws IOException{
		consultas = new File("src/dados/arquivos/repositorios/repositorioDeConsultas.txt");
		numeroConsultas = 0;
		
		if(!consultas.exists()) {
			consultas.createNewFile();	
		}

		infoEscrever = new FileOutputStream(consultas);
		salvarNoArquivo = new ObjectOutputStream(infoEscrever);
		
	}

	public void cadastrarConsulta(Consulta c) throws IOException, ConsultaJaExisteException, HorarioIndisponivelException, ClassNotFoundException{
		if(!consultaExiste(c)) {
			salvarNoArquivo.writeObject(c);
		}
		else {
			throw new ConsultaJaExisteException();
		}
		numeroConsultas++;
		
	}
	
	public boolean consultaExiste(Consulta c) throws ClassNotFoundException, IOException {
		boolean existe = false;
		
		List<Consulta> consultas = listarConsultas();
		
		for (Consulta consulta : consultas) {
			if(consulta.getMedico().equals(c.getMedico())) {
				if((c.getDataHoraInicio().isAfter(consulta.getDataHoraInicio()) || c.getDataHoraInicio().isEqual(consulta.getDataHoraInicio())) && (c.getDataHoraInicio().isBefore(consulta.getDataHoraFim()) || c.getDataHoraInicio().isEqual(consulta.getDataHoraFim()))) {
					existe = true;
				}
			}
			if(consulta.getPaciente().equals(c.getPaciente())){
				if((c.getDataHoraInicio().isAfter(consulta.getDataHoraInicio()) || c.getDataHoraInicio().isEqual(consulta.getDataHoraInicio())) && (c.getDataHoraInicio().isBefore(consulta.getDataHoraFim()) || c.getDataHoraInicio().isEqual(consulta.getDataHoraFim()))) {
					existe = true;
				}
			}
		}
		
		
		return existe;
	}

	public void removerConsulta(Consulta c) throws IOException, ClassNotFoundException{
		File auxFile = new File("src/dados/arquivos/repositorios/repositorioConsultaAux.txt");
		
		FileInputStream infoLer = new FileInputStream(consultas);
		ObjectInputStream lerArquivos = new ObjectInputStream(infoLer);
		
		for(int contador = 1; contador < numeroConsultas; contador++) {
			Consulta aux = (Consulta) lerArquivos.readObject();
		
			if(!aux.equals(c)) {
				cadastroRemover(auxFile, aux);
			}
		
		}
		
		lerArquivos.close();
		auxFile.renameTo(consultas);		
		numeroConsultas--;	
	}
	
	private void cadastroRemover(File aux, Consulta c) throws IOException {
		if(!aux.exists()) {
			aux.createNewFile();
		}
		
		FileOutputStream infoAux = new FileOutputStream(aux);
		ObjectOutputStream salvarNoArquivoAux = new ObjectOutputStream(infoAux);
		
		
		salvarNoArquivoAux.writeObject(c);
		
		salvarNoArquivoAux.close();
	}

	public ObservableList<Consulta> listarConsultasPaciente(Paciente p) throws IOException, ClassNotFoundException {
		ObservableList<Consulta> listarConsultasPorPaciente = FXCollections.observableArrayList();
		
		FileInputStream infoLer = new FileInputStream(consultas);
		ObjectInputStream lerArquivos = new ObjectInputStream(infoLer);
		
		for(int contador = 1; contador < numeroConsultas; contador++) {
			Consulta aux = (Consulta) lerArquivos.readObject();
			if(!aux.getPaciente().equals(p)) {
				listarConsultasPorPaciente.add(aux);
			}
			
		}
		
		lerArquivos.close();
		return listarConsultasPorPaciente;
		
	}

	public ObservableList<Consulta> listarConsultasMedico(Medico m) throws IOException, ClassNotFoundException {
		ObservableList<Consulta> listarConsultasPorMedico = FXCollections.observableArrayList();
		
		FileInputStream infoLer = new FileInputStream(consultas);
		ObjectInputStream lerArquivos = new ObjectInputStream(infoLer);
		
		for(int contador = 1; contador < numeroConsultas; contador++) {
			Consulta aux = (Consulta) lerArquivos.readObject();
			
			if(!aux.getMedico().equals(m)) {
				listarConsultasPorMedico.add(aux);
			}
		}
		
		lerArquivos.close();
		return listarConsultasPorMedico;
		
	}

	public ObservableList<Consulta> listarConsultas() throws IOException, ClassNotFoundException {
		ObservableList<Consulta> listarConsultas = FXCollections.observableArrayList();
		
		FileInputStream infoLer = new FileInputStream(consultas);
		ObjectInputStream lerArquivos = new ObjectInputStream(infoLer);
		
		for(int contador = 1; contador < numeroConsultas; contador++) {
			Consulta aux = (Consulta) lerArquivos.readObject();
			
			listarConsultas.add(aux);
		}
		
		lerArquivos.close();
		return listarConsultas;
	}
	
	
}
