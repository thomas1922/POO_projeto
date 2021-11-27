
package supermercado;

import java.util.ArrayList;

public class ProdutoAlimentar extends Produto{
    private int numCalorias;
    private int percentGordura;

    public ProdutoAlimentar(int numCalorias, int percentGordura, int identificador, String nome, int precoUnitario, int stock, ArrayList<Promocao> promocoes) {
        super(identificador, nome, precoUnitario, stock, promocoes);
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
        return " ";
    }
    
}
