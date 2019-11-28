package dados;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import exceptions.ConsultaJaExisteException;
import exceptions.DadosInvalidosException;
import exceptions.MedicoExistenteException;
import exceptions.PacienteExistenteException;
import negocio.Consulta;
import negocio.Medico;
import negocio.Paciente;

public class testeFile {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Medico m1 = new Medico("1321", "Cardiologista", "1321", "Diogo", 24, "72245609878");
		Medico m2 = new Medico("2451", "Clinico_Geral", "2451", "Vanderlei", 34, "45689723367");
		Medico m3 = new Medico("3329", "Urologista", "3329", "Silvio", 46, "33364789140");
		Medico m4 = new Medico("1145", "Ginecologista", "1145", "Marina", 35, "12345678904");
                Medico m5 = new Medico("2744", "Cardiologista", "2744", "Genesio", 24, "56489712288");
		Medico m6 = new Medico("4321", "Clinico_Geral", "4321", "Evandro", 34, "12345678902");
		Medico m7 = new Medico("2544", "Fisioterapeuta", "2544", "Cleiton", 46, "12345678903");
		Medico m8 = new Medico("1212", "Pediatra", "1212", "Olivia", 35, "12345678984");
                
		RepositorioMedicoFile medicos = new RepositorioMedicoFile();
		RepositorioMedico medico = new RepositorioMedico();

		try {
			medico.cadastrarMedico(m1);
			medico.cadastrarMedico(m2);
			medico.cadastrarMedico(m3);
			medico.cadastrarMedico(m4);
                        medico.cadastrarMedico(m5);
			medico.cadastrarMedico(m6);
			medico.cadastrarMedico(m7);
			medico.cadastrarMedico(m8);
			
		} catch (MedicoExistenteException | DadosInvalidosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Paciente p = new Paciente("rua da Verdade", "999987456", "Joao", 15, "24067845622");

		RepositorioPacienteFile pacientes = new RepositorioPacienteFile();
		RepositorioPaciente paciente = new RepositorioPaciente();

		try {
			paciente.cadastrarPaciente(p);
		} catch (PacienteExistenteException | DadosInvalidosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		medicos.salvarMedicos(medico.listarMedicos());
		pacientes.salvarPacientes(paciente.listarPacientes());
		//consultas.salvarConsultas(consulta.listarConsultas());

		List<Medico> medicosRecovery = medicos.recuperarMedicos();
		List<Paciente> pacientesRecovery = pacientes.recuperarPacientes();
		//List<Consulta> consultasRecovery = consultas.recuperarConsultas();

		medicosRecovery.stream().forEach(m9->System.out.println(m9));
		pacientesRecovery.stream().forEach(p5->System.out.println(p5));
		//consultasRecovery.stream().forEach(c5->System.out.println(c5));


	}

}
