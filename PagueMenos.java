
package supermercado;

import java.io.Serializable;

public class PagueMenos extends Promocao implements Serializable{
    
    public PagueMenos(String produto, Data dataInicio, Data dataFim) {
        super(produto, dataInicio, dataFim);
    }
    @Override
    public int promocao(int quantidade, int precoUnitario){
        int descontoPorUni = (precoUnitario*5)/100;
        int precoTotal = quantidade*precoUnitario;
        int precoPosPromocao = 0;
        while(precoTotal>=precoUnitario*10){
            precoPosPromocao+= precoTotal;
            precoTotal-=descontoPorUni;
        }
        return precoPosPromocao;
    }
    @Override
    public String toString(){
        return "PagueMenos"+"Produto: "+getProduto()+"Data de inicio: "+getDataInicio()+"Data Fim: "+getDataFim();
    }
    
}
