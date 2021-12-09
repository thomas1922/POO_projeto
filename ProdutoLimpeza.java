package supermercado;

import java.io.Serializable;

public class ProdutoLimpeza extends Produto implements Serializable {

    private int grauToxicidade;

    /**
     * method that calls the constructor of the super class, receiving its
     * arguments, plus the degree of toxicity
     *
     * @param identificador Identifier
     * @param nome Name
     * @param precoUnitario Price
     * @param stock Stock
     * @param grauToxicidade degree of toxicity
     */
    public ProdutoLimpeza(int identificador, String nome, Double precoUnitario, int stock, int grauToxicidade) {
        super(identificador, nome, precoUnitario, stock);
        this.grauToxicidade = grauToxicidade;
    }

    public int getGrauToxicidade() {
        return grauToxicidade;
    }

    public void setGrauToxicidade(int grauToxicidade) {
        this.grauToxicidade = grauToxicidade;
    }

    @Override
    public String toString() {
        return getNome();
    }

}
