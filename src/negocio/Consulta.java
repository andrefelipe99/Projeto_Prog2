package negocio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Consulta implements Serializable{

    private int id;
    private Medico medico;
    private Paciente paciente;
    private String descricao;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private Diagnostico diagnostico;

    public Consulta(int id, Medico medico, Paciente paciente, String descricao, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.descricao = descricao;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.diagnostico = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", medico=" + medico + ", paciente=" + paciente + ", descricao=" + descricao + ", dataHoraInicio=" + dataHoraInicio + ", dataHoraFim=" + dataHoraFim + '}';
    }

    public boolean equals(Consulta c) {
    	if(this.id == c.getId()) {
        	if(this.paciente.equals(c.getPaciente())) {
        		if((this.medico.equals(c.getMedico()))){
        			if(this.dataHoraInicio.isEqual(c.dataHoraInicio)) {
        				return true;
        			}
        		}
        	}
    	}
    	
    	return false;
    }
}
