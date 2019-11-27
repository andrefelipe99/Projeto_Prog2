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
		Medico m = new Medico("12341", "coracao", "1234", "Claudio", 24, "12345678901");
		Medico m1 = new Medico("12342", "urologista", "1234", "Vanderlei", 34, "12345678902");
		Medico m2 = new Medico("12343", "urologista", "1234", "Silvio", 46, "12345678903");
		Medico m3 = new Medico("12345", "ginecologista", "1234", "Marina", 35, "12345678904");

		RepositorioMedicoFile medicos = new RepositorioMedicoFile();
		RepositorioMedico medico = new RepositorioMedico();

		try {
			medico.cadastrarMedico(m);
			medico.cadastrarMedico(m1);
			medico.cadastrarMedico(m2);
			medico.cadastrarMedico(m3);
			//medico.removerMedico(m3);
		} catch (MedicoExistenteException | DadosInvalidosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Paciente p = new Paciente("rua 1", "998877665", "Joao", 15, "12345678905");
		Paciente p1 = new Paciente("rua 2", "998877666", "Maria", 20,"12345678906");

		RepositorioPacienteFile pacientes = new RepositorioPacienteFile();
		RepositorioPaciente paciente = new RepositorioPaciente();

		try {
			paciente.cadastrarPaciente(p);
			paciente.cadastrarPaciente(p1);
			paciente.removerPaciente(p1);
		} catch (PacienteExistenteException | DadosInvalidosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Consulta c = new Consulta(0, m2, p, "", LocalDateTime.now().plusMinutes(30), LocalDateTime.now().plusMinutes(40));
		Consulta c1 = new Consulta(1, m2, p, "", LocalDateTime.now().plusMinutes(10), LocalDateTime.now().plusMinutes(20));

		RepositorioConsultaFile consultas = new RepositorioConsultaFile();
		RepositorioConsulta consulta = new RepositorioConsulta();

		try {
			consulta.cadastrarConsulta(c);
			consulta.cadastrarConsulta(c1);
			consulta.removerConsulta(c1);
		} catch (ConsultaJaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		medicos.salvarMedicos(medico.listarMedicos());
		pacientes.salvarPacientes(paciente.listarPacientes());
		consultas.salvarConsultas(consulta.listarConsultas());

		List<Medico> medicosRecovery = medicos.recuperarMedicos();
		List<Paciente> pacientesRecovery = pacientes.recuperarPacientes();
		List<Consulta> consultasRecovery = consultas.recuperarConsultas();

		medicosRecovery.stream().forEach(m5->System.out.println(m5));
		pacientesRecovery.stream().forEach(p5->System.out.println(p5));
		consultasRecovery.stream().forEach(c5->System.out.println(c5));


	}

}
