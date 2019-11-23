package negocio;

import java.io.Serializable;

public class Pessoa implements Serializable{

    private String nome;
    private int idade;
    private String cpf;


    public Pessoa(String nome, int idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return ", nome=" + nome + ", idade=" + idade + ", cpf=" + cpf;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

 
	public boolean equals(Pessoa p) {
    	return this.cpf.equals(p.cpf);
	}

}
