
package supermercado;

import java.io.Serializable;


public class Pague3Leve4 extends Promocao implements Serializable{

    public Pague3Leve4(String produto, Data dataInicio, Data dataFim) {
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
        return "Pague3Leve4" + "Produto: "+getProduto()+"Data de inicio: "+getDataInicio()+"Data Fim: "+getDataFim();
    }
        
    }
    
    
 
