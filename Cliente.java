package supermercado;

public class Cliente {
    private String nome;
    private String morada;
    private String email;
    private int telefone;
    String dataNascimento;
    private int regularidade;
    
    public Cliente(){
        
    }
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getRegularidade() {
        return regularidade;
    }

    public void setRegularidade(int regularidade) {
        this.regularidade = regularidade;
    }
    
    @Override
    public String toString(){
        return " ";
    }
    
}