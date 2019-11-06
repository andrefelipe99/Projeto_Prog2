package controladores;

import java.time.LocalDateTime;
import java.time.Month;
import negocio.Consulta;
import negocio.Diagnostico;
import negocio.Medico;
import negocio.Paciente;

public class teste {
	public static void main(String[] args) {
		ControladorPaciente p = new ControladorPaciente();
		ControladorMedico m = new ControladorMedico();
                ControladorConsulta c = new ControladorConsulta();
                ControladorDiagnostico dg = new ControladorDiagnostico();

		Paciente p1 = new Paciente("endereco", "telefone", "nome", 15, "123");
		Paciente p2 = new Paciente("endereco", "telefone", "nome", 15, "1234");

		Medico m1 = new Medico("12345", "area", "senha", "nome", 23, "123");
		Medico m2 = new Medico("123456", "area", "senha", "nome", 23, "123");
		
		p.cadastrarPaciente(p1);
		p.cadastrarPaciente(p2);
		//System.out.println(p.listarPacientes().toString());
		//p.removerPaciente("123");
		//System.out.println(p.listarPacientes().toString());
		
		m.cadastrarMedico(m1);
		m.cadastrarMedico(m2);
		//System.out.println(m.listarMedicos().toString());
		m.removerMedico("12345");
		//System.out.println(m.listarMedicos().toString());
                
                Consulta c1 = new Consulta(01, m1, p1, "Dor no cunhão", LocalDateTime.of(2019, 2, 12, 10, 0), LocalDateTime.of(2019, 2, 12, 11, 0));
                Consulta c2 = new Consulta(02, m1, p2, "Dor no cunhão", LocalDateTime.of(2019, 2, 12, 11, 0), LocalDateTime.of(2019, 2, 12, 12, 0));
                c.cadastrarConsulta(c1);
                c.cadastrarConsulta(c2);
                
                //System.out.println(c1.toString());
                
                Diagnostico dg1 = new Diagnostico(01, c1, "Falta de Sexo", "Puta");
                dg.cadastrarDiagnostico(dg1, c1);
                dg.cadastrarDiagnostico(dg1, c1);
                
                //System.out.println(dg.listarDiagnosticoPorConsulta(c1));
                System.out.println(m1.getConsultas().toString());
	}
}
