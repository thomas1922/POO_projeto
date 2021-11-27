
package supermercado;

import java.util.ArrayList;

public class ProdutoMobiliario extends Produto {
    private int peso;
    private int dimensao;

    public ProdutoMobiliario(int peso, int dimensao, int identificador, String nome, int precoUnitario, int stock, ArrayList<Promocao> promocoes) {
        super(identificador, nome, precoUnitario, stock, promocoes);
        this.peso = peso;
        this.dimensao = dimensao;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }
    
    @Override
    public String toString(){
        return " ";
    }
}
