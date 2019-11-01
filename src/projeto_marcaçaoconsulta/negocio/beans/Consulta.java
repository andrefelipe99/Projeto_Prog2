package projeto_marcaçaoconsulta.negocio.beans;

import java.time.LocalDateTime;
import java.util.Objects;

public class Consulta {
    
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    public Consulta(Medico medico, Paciente paciente, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
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

    @Override
    public String toString() {
        return "Consulta{" + "medico=" + medico + ", paciente=" + paciente + ", dataHoraInicio=" + dataHoraInicio + ", dataHoraFim=" + dataHoraFim + '}';
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
        final Consulta other = (Consulta) obj;
        if (!Objects.equals(this.dataHoraInicio, other.dataHoraInicio)) {
            return false;
        }
        if (!Objects.equals(this.dataHoraFim, other.dataHoraFim)) {
            return false;
        }
        return true;
    }
}
