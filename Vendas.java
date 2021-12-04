
package supermercado;

public class Vendas {
    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    
    public Vendas(Cliente cliente, Produto produto, int quantidade) {
       this.cliente = cliente;
       this.produto = produto;
       this.quantidade = quantidade;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    @Override
    public String toString(){
        return "Produto: "+ getProduto()+"quantidade: "+ getQuantidade();
    }
}
