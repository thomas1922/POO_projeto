
package supermercado;

public class PagueMenos extends Promocao{
    
    public PagueMenos(String produto, int dataInicio, int dataFim) {
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
        return " ";
    }
    
}
