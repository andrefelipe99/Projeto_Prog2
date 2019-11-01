package projeto_marcaçaoconsulta.negocio.beans;

public class Diagnostico {
    
    private Consulta consulta;
    private String descricao;
    private String medicamentos;

    public Diagnostico(Consulta consulta, String descricao, String medicamentos) {
        this.consulta = consulta;
        this.descricao = descricao;
        this.medicamentos = medicamentos;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    @Override
    public String toString() {
        return "Diagnostico{" + "consulta=" + consulta + ", descricao=" + descricao + ", medicamentos=" + medicamentos + '}';
    }
}
