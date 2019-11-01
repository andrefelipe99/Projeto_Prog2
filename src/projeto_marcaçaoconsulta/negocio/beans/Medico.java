package projeto_marcaçaoconsulta.negocio.beans;

import java.util.Objects;

public class Medico extends Pessoa{
    
    private String crm;
    private String area;
    private String senha;

    public Medico(String crm, String area, String senha, String nome, int idade, String cfp) {
        super(nome, idade, cfp);
        this.crm = crm;
        this.area = area;
        this.senha = senha;
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
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medico other = (Medico) obj;
        if (!Objects.equals(this.crm, other.crm)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Medico{" + "crm=" + crm + ", area=" + area + ", senha=" + senha + '}';
    }
    
    
}
