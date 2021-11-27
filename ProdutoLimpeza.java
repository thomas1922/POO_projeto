
package supermercado;

import java.util.ArrayList;

public class ProdutoLimpeza extends Produto {
    private int grauToxicidade;

    public ProdutoLimpeza(int grauToxicidade, int identificador, String nome, int precoUnitario, int stock, ArrayList<Promocao> promocoes) {
        super(identificador, nome, precoUnitario, stock, promocoes);
        this.grauToxicidade = grauToxicidade;
    }

    public int getGrauToxicidade() {
        return grauToxicidade;
    }

    public void setGrauToxicidade(int grauToxicidade) {
        this.grauToxicidade = grauToxicidade;
    }

    
    @Override
    public String toString(){
        return " ";
    }
    
    
}
