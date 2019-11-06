package controladores;

import negocio.Medico;
import negocio.Paciente;

public class teste {
	public static void main(String[] args) {
		ControladorPaciente p = new ControladorPaciente();
		ControladorMedico m = new ControladorMedico();

		Paciente p1 = new Paciente("endereco", "telefone", "nome", 15, "123");
		Paciente p2 = new Paciente("endereco", "telefone", "nome", 15, "1234");

		Medico m1 = new Medico("12345", "area", "senha", "nome", 23, "123");
		Medico m2 = new Medico("123456", "area", "senha", "nome", 23, "123");
		
		p.cadastrarPaciente(p1);
		p.cadastrarPaciente(p2);
		System.out.println(p.listarPacientes().toString());
		p.removerPaciente("123");
		System.out.println(p.listarPacientes().toString());
		
		m.cadastrarMedico(m1);
		m.cadastrarMedico(m2);
		System.out.println(m.listarMedicos().toString());
		m.removerMedico("12345");
		System.out.println(m.listarMedicos().toString());
                
                
	}
}
