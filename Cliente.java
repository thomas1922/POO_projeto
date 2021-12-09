package supermercado;

import java.io.Serializable;

public class Cliente implements Serializable {

    private String nome;
    private String morada;
    private String email;
    private int telefone;
    private String dataNascimento;
    private int regularidade;

    /**
     * Empty constructor
     */
    public Cliente() {

    }

    /**
     * constructor of the client object that receives as parameters the
     * name,adress ,email,phone number ,birth date and the type of client
     *
     * @param nome Name
     * @param morada Adress
     * @param email Email
     * @param telefone phone number
     * @param dataNascimento birth date
     * @param regularidade type of client
     */
    public Cliente(String nome, String morada, String email, int telefone, String dataNascimento, int regularidade) {
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.regularidade = regularidade;
    }

    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefone() {
        return telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public int getRegularidade() {
        return regularidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setRegularidade(int regularidade) {
        this.regularidade = regularidade;
    }

    @Override
    public String toString() {
        return "Nome: " + getNome();
    }

}

