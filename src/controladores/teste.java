package controladores;

import java.time.LocalDateTime;
import negocio.Consulta;
import negocio.Diagnostico;
import negocio.Medico;
import negocio.Paciente;

public class teste {
	public static void main(String[] args) {

		Fachada f = Fachada.getInstance();

		Paciente p1 = new Paciente("endereco", "telefone", "nome", 15, "123");
		Paciente p2 = new Paciente("endereco", "telefone", "nome", 15, "1234");

		Medico m1 = new Medico("12345", "area", "senha", "nome", 23, "123");
		Medico m2 = new Medico("123456", "area", "senha", "nome", 23, "123");

		f.cadastrarPaciente(p1);
		f.cadastrarPaciente(p2);
		//f.removerPaciente("1234");
		//System.out.println(f.listarPacientes().toString());

		f.cadastrarMedico(m1);
		f.cadastrarMedico(m2);
		//f.removerMedico("12345");
		//f.login("12345", "senha");
		//System.out.println(f.listarMedicos().toString());


        Consulta c1 = new Consulta(01, m1, p1, "Dor no cunhao", LocalDateTime.of(2019, 2, 12, 10, 0), LocalDateTime.of(2019, 2, 12, 11, 0));
        Consulta c2 = new Consulta(02, m1, p2, "Dor no cunhao", LocalDateTime.of(2019, 2, 12, 11, 0), LocalDateTime.of(2019, 2, 12, 12, 0));
        f.cadastrarConsulta(c1);
        f.cadastrarConsulta(c2);
        //System.out.println(f.listarConsultasPaciente(p1));

        Diagnostico dg1 = new Diagnostico("Falta de Sexo", "Puta");
        Diagnostico dg2 = new Diagnostico( "Falta de Sexo", "Puta");

        f.cadastrarDiagnostico(dg1, c1);
        f.cadastrarDiagnostico(dg2, c2);
        //f.atualizarDiagnostico(dg1);
        //System.out.println(f.buscarDiagnosticoPorConsulta(c1).toString());
	}
}
