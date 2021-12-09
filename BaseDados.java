package supermercado;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseDados implements Serializable {

    private Cliente cliente;
    private ArrayList<Compra> compras;

    /**
     * constructor that takes a customer as parameter and initializes the
     * arraylist of objects of type purchase
     *
     * @param cliente Client
     */
    public BaseDados(Cliente cliente) {
        this.cliente = cliente;
        this.compras = new ArrayList<>();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCompras(ArrayList<Compra> compras) {
        this.compras = compras;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public void addCompras(Compra compra) {
        compras.add(compra);
    }

    @Override
    public String toString() {
        return "Dias" + getCompras();
    }
}
