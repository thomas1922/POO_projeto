
package supermercado;

import java.util.ArrayList;

public class Carrinho {
    private ArrayList<Vendas> carrinho;

    public Carrinho() {
        carrinho = new ArrayList<>();
    }
    
    public ArrayList<Vendas> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(ArrayList<Vendas> carrinho) {
        this.carrinho = carrinho;
    }
   
    @Override
    public String toString(){
        return "compra:"+getCarrinho();
    }
    
    
    
}
