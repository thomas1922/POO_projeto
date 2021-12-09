package supermercado;

import java.io.Serializable;

public class ProdutoAlimentar extends Produto implements Serializable {

    private int numCalorias;
    private int percentGordura;

    /**
     * method that calls the constructor of the super class, receiving its
     * arguments, plus the number of calories and fat percentage
     *
     * @param identificador identifier
     * @param nome name
     * @param precoUnitario price
     * @param stock stock
     * @param numCalorias number of calories
     * @param percentGordura fat percentage
     */
    public ProdutoAlimentar(int identificador, String nome, Double precoUnitario, int stock, int numCalorias, int percentGordura) {
        super(identificador, nome, precoUnitario, stock);
        this.numCalorias = numCalorias;
        this.percentGordura = percentGordura;
    }

    public int getNumCalorias() {
        return numCalorias;
    }

    public int getPercentGordura() {
        return percentGordura;
    }

    public void setNumCalorias(int numCalorias) {
        this.numCalorias = numCalorias;
    }

    public void setPercentGordura(int percentGordura) {
        this.percentGordura = percentGordura;
    }

    @Override
    public String toString() {
        return getNome();
    }

}
