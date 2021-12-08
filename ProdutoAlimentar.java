package supermercado;

import java.io.Serializable;

public class ProdutoAlimentar extends Produto implements Serializable{
    private int numCalorias;
    private int percentGordura;
    
    public ProdutoAlimentar(int identificador, String nome, Double precoUnitario, int stock,int numCalorias,int percentGordura) {
        super(identificador, nome, precoUnitario, stock);
        this.numCalorias = numCalorias;
        this.percentGordura = percentGordura;
    }

    public int getNumCalorias() {
        return numCalorias;
    }

    public void setNumCalorias(int numCalorias) {
        this.numCalorias = numCalorias;
    }

    public int getPercentGordura() {
        return percentGordura;
    }

    public void setPercentGordura(int percentGordura) {
        this.percentGordura = percentGordura;
    }

    @Override
    public String toString(){
        return getNome();
    }
    
}
