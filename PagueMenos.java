
package supermercado;

public class PagueMenos extends Promoçao {
    public PagueMenos(String nomeProduto,Data dataInicio, Data dataFim, Data dataHoje){
        super(nomeProduto,dataInicio,dataFim,dataHoje);
    }

    @Override
    public Double promoçao(int quantidade, Double preçoUnitario) {
        Double descontoPorUni=(preçoUnitario*5)/100, preçoSemDesconto=quantidade*preçoUnitario,preçoComDesconto=preçoUnitario,desconto;
        int i=0;
        if(quantidade==0)preçoComDesconto=0.0;
        while(i<10||quantidade>0){
            preçoComDesconto+=preçoComDesconto-descontoPorUni;
            quantidade--;
        }
        desconto=preçoSemDesconto-preçoComDesconto;
        return desconto;
    }
}
