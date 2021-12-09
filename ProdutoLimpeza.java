
public class ProdutoLimpeza extends Produto implements Serializable {

    private int grauToxicidade;

    /**
     * method that calls the constructor of the super class, receiving its
     * arguments, plus the degree of toxicity
     *
     * @param identificador identifier
     * @param nome name
     * @param precoUnitario price
     * @param stock stock
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
