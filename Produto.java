package supermercado;

import java.io.Serializable;

public class Produto implements Serializable {

    private int identificador;
    private String nome;
    private Double precoUnitario;
    private int stock;

    /**
     * constructor which takes as parameters an identifier, a name, the price
     * and the existing stock
     *
     * @param identificador Identifier
     * @param nome Name
     * @param precoUnitario price
     * @param stock stock
     */
    public Produto(int identificador, String nome, Double precoUnitario, int stock) {
        this.identificador = identificador;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.stock = stock;
    }

    /**
     * Empty constructor
     */
    public Produto() {

    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getStock() {
        return stock;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return getNome() + " ";
    }

}
