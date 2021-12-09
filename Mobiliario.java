package supermercado;

import java.io.Serializable;

public class ProdutoMobiliario extends Produto implements Serializable {

    private int peso;
    private int dimensao;

    /**
     * method that calls the constructor of the super class, receiving its
     * arguments, plus the weight and size
     *
     * @param identificador Identifier
     * @param nome Name
     * @param precoUnitario Price
     * @param stock Stock
     * @param peso weight
     * @param dimensao size
     */
    public ProdutoMobiliario(int identificador, String nome, Double precoUnitario, int stock, int peso, int dimensao) {
        super(identificador, nome, precoUnitario, stock);
        this.peso = peso;
        this.dimensao = dimensao;
    }

    public int getPeso() {
        return peso;
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
