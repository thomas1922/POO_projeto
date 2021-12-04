
package supermercado;


public class Alimentar extends Produto {
    private int numCalorias;
    private int percentGordura;

    public Alimentar(int identificador, String nome, double precoUnitario, int stock,int numCalorias, int percentGordura) {
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
         return "Nome: "+getNome()+" Preco por Unidade: "+getPrecoUnitario()+" Stock"+getStock()+" ";
    }
    
}
