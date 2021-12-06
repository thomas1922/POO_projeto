package supermercado;

import java.io.Serializable;
import java.util.ArrayList;

public class Compra implements Serializable {

    private double preço;
    private ArrayList<Produto> produtos;

    public Compra(double preço, ArrayList<Produto> produtos) {
        this.preço = preço;
        this.produtos = produtos;
    }

    public double getPreço() {
        return preço;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setPreço(int preço) {
        this.preço = preço;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    @Override
    public String toString(){
        return " ";
    }
}
