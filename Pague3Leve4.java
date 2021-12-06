
package supermercado;

public class Pague3Leve4 extends Promoçao {
    public Pague3Leve4(String nomeProduto,Data dataInicio, Data dataFim, Data dataHoje){
        super(nomeProduto,dataInicio,dataFim,dataHoje);
    }

    @Override
    public Double promoçao(int quantidade, Double preçoUnitario) {
        Double preçoTotal=quantidade*preçoUnitario,desconto;
        int produtosGratuitos=(int)(quantidade/4);
        desconto=produtosGratuitos*preçoUnitario;
        return desconto;
    }
    
}
