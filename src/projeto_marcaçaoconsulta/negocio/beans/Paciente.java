package projeto_marcaçaoconsulta.negocio.beans;

import java.time.LocalDateTime;

public class Paciente extends Pessoa{
    
    private String endereco;
    private String telefone;
    
    
    public Paciente(String endereco, String telefone, String nome, int idade, String cfp) {
        super(nome, idade, cfp);
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Paciente{" + "endereco=" + endereco + ", telefone=" + telefone + '}';
    }    
    
}
