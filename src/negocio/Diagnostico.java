package negocio;

import java.io.Serializable;

public class Diagnostico implements Serializable{
	
    private String descricao;
    private String medicamentos;

    public Diagnostico(String descricao, String medicamentos) {
    	this.descricao = descricao;
        this.medicamentos = medicamentos;
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
        return "Diagnostico{ descricao=" + descricao + ", medicamentos=" + medicamentos + '}';
    }

}
