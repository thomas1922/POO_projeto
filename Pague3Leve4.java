
package supermercado;


public class Pague3Leve4 extends Promocao {

    public Pague3Leve4(String produto, int dataInicio, int dataFim) {
        super(produto, dataInicio, dataFim);
        
    }
    @Override
    public int promocao(int quantidade,int precoUnitario){
        int precoTotal = quantidade*precoUnitario;
        int produtosGratuitos = (int)(quantidade/4);
        int precoPosPromocao;
        precoPosPromocao = precoTotal -(produtosGratuitos*precoUnitario);
        return precoPosPromocao;
    }
    @Override
    public String toString(){
        return " ";
    }
        
    }
    
    
 
