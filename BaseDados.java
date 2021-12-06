
package supermercado;

import java.util.ArrayList;
import java.util.List;

public class BaseDados {
    private Cliente cliente;
    private List<Compra>compras;
    public BaseDados(Cliente cliente){
        this.cliente=cliente;
        this.compras=new ArrayList<>();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Compra> getCompras() {
        return compras;
    }
    public void addCompras(Compra compra){
        compras.add(compra);
    }
}
