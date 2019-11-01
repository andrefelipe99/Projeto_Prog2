package projeto_marcaçaoconsulta.negocio.beans;

public class Diagnostico {
    
    private Medico medico;
    private Paciente paciente;
    private String descricao;
    private String medicamentos;

    public Diagnostico(Medico medico, Paciente paciente, String descricao, String medicamentos) {
        this.medico = medico;
        this.paciente = paciente;
        this.descricao = descricao;
        this.medicamentos = medicamentos;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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
        return "Diagnostico{" + "medico=" + medico + ", paciente=" + paciente + ", descricao=" + descricao + ", medicamentos=" + medicamentos + '}';
    }

}
