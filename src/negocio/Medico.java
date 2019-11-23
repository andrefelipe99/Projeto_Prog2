package negocio;

import java.util.List;

public class Medico extends Pessoa{

    private String crm;
    private String area;
    private String senha;
    private List<Consulta> consultas;

    public Medico(String crm, String area, String senha, String nome, int idade, String cpf) {
        super(nome, idade, cpf);
        this.crm = crm;
        this.area = area;
        this.senha = senha;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((crm == null) ? 0 : crm.hashCode());
		return result;
	}

   
	public boolean equals(Medico m) {
		return this.crm.equals(m.crm);
	}

    @Override
    public String toString() {
        return "Medico{" + "crm=" + crm + ", area=" + area + ", senha=" + senha + ", consultas=" + consultas + super.toString() + '}';
    }
}
