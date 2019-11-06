package negocio;

public class Diagnostico {
    
    private int id;
    private Consulta consulta;
    private String descricao;
    private String medicamentos;

    public Diagnostico(int id, Consulta consulta, String descricao, String medicamentos) {
        this.id = id;
        this.consulta = consulta;
        this.descricao = descricao;
        this.medicamentos = medicamentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Diagnostico{" + "id=" + id + ", consulta=" + consulta + ", descricao=" + descricao + ", medicamentos=" + medicamentos + '}';
    }
}
