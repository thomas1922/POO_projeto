package supermercado;

import java.io.Serializable;
import java.util.ArrayList;

public class Compra implements Serializable {

    private double preco;
    private Data data;
    private ArrayList<Produto> produtos;

    /**
     * constructor de um compra que recebe como parametros o preço, a data da
     * compra e a arrayList de produtos com os produtos comprados
     *
     * @param preco price
     * @param produtos arrayList of objects with the type product
     * @param data date of purchase
     */
    public Compra(double preco, ArrayList<Produto> produtos, Data data) {
        this.preco = preco;
        this.produtos = produtos;
        this.data = data;
    }

    public double getPreco() {
        return preco;
    }

    public Data getData() {
        return data;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Data: " + getData() + "  Preço: " + getPreco() + " " + getProdutos();
    }
}
