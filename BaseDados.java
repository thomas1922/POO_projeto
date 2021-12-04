package supermercado;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseDados implements Serializable {
    private Cliente cliente;
    private ArrayList<Compra>compras;
    public BaseDados(Cliente cliente){
        this.cliente=cliente;
         compras = new ArrayList<>();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Compra> compras) {
        this.compras = compras;
    }
    
    public void addCompras(Compra compra){
        compras.add(compra);
    }
    
    @Override
    public String toString(){
        return "Cliente: "+getCliente()+getCompras();
    }
}
