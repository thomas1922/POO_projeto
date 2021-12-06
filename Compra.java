package supermercado;

import java.util.List;

public class Compra {

    private double preço;
    private List<Produto> produtos;

    public Compra(double preço, List<Produto> produtos) {
        this.preço = preço;
        this.produtos = produtos;
    }

    public double getPreço() {
        return preço;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setPreço(int preço) {
        this.preço = preço;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
