package supermercado;

public class Pague3Leve4 extends Promocao {

    public Pague3Leve4(String nomeProduto, Data dataInicio, Data dataFim, Data dataHoje) {
        super(nomeProduto, dataInicio, dataFim, dataHoje);
    }

    /**
     * method that returns the discount value of the price of a product
     *
     * @param quantidade amount
     * @param preçoUnitario price
     * @return discount
     */
    @Override
    public Double promocao(int quantidade, Double preçoUnitario) {
        /*Double preçoTotal = quantidade * preçoUnitario,*/
        Double desconto;
        int produtosGratuitos = (int) (quantidade / 4);
        desconto = produtosGratuitos * preçoUnitario;
        return desconto;
    }

    @Override
    public String toString() {
        return "Tipo:Pague3Leve4 Nome: " + getNomeProduto();
    }
}
