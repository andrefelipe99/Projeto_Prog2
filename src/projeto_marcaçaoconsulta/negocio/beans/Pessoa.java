package projeto_marcaçaoconsulta.negocio.beans;

import java.util.Objects;

public class Pessoa {
    
    private String nome;
    private int idade;
    private String cfp;
    

    public Pessoa(String nome, int idade, String cfp) {
        this.nome = nome;
        this.idade = idade;
        this.cfp = cfp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCfp() {
        return cfp;
    }

    public void setCfp(String cfp) {
        this.cfp = cfp;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", idade=" + idade + ", cfp=" + cfp + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.cfp, other.cfp)) {
            return false;
        }
        return true;
    }
    
}
