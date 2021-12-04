package supermercado;

import java.io.Serializable;
import java.util.ArrayList;


public class Compra implements Serializable {
    private int preco;
    private ArrayList<Produto> produtos;

    public Compra(int quantidade, ArrayList<Produto> produtos) {
        this.preco = quantidade;
        this.produtos = produtos;
    }

    public int getPreco() {
        return preco;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setPreco(int preço) {
        this.preco = preço;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    @Override
    public String toString(){
        return "Preço: "+getPreco()+"\t"+getProdutos();
    }
}