package supermercado;

import java.io.Serializable;

public abstract class Promocao implements Serializable {

    private String nomeProduto;
    private Data dataInicio;
    private Data dataFim;
    private int ativa;

    /**
     * constructor of a promotion that receives as parameters the name of the
     * product, the date of beginning of the promotion, the date of end and the
     * current date. The promotion is activated or not using the method
     * ativaPromoção that receives as parameter the current date
     *
     * @param nomeProduto name
     * @param dataInicio initial date
     * @param dataFim final date
     * @param dataHoje current date
     */
    public Promocao(String nomeProduto, Data dataInicio, Data dataFim, Data dataHoje) {
        this.nomeProduto = nomeProduto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativa = ativaPromocao(dataHoje);
    }

    public Data getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Data getDataFim() {
        return dataFim;
    }

    public void setDataFim(Data dataFim) {
        this.dataFim = dataFim;
    }

    public void setAtiva(int ativa) {
        this.ativa = ativa;
    }

    public int getAtiva() {
        return ativa;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * abstract method
     *
     * @param quantidade amount
     * @param preçoUnitario price
     * @return discount
     */
    public abstract Double promocao(int quantidade, Double preçoUnitario);

    /**
     * method that uses the comparaDatas method which takes as parameters the
     * promotion start date, promotion end date and current date and returns 0
     * or 1 if the current date is between the two previously mentioned. If it
     * returns 1 the promotion is active, if it returns 0 it's inactive
     *
     * @param dataHoje current date
     * @return 1 or 0
     */
    public int ativaPromocao(Data dataHoje) {
        int ativador;
        Data data = new Data();
        ativador = data.comparaDatas(dataInicio, dataFim, dataHoje);
        return ativador;
    }

    @Override
    public String toString() {
        return " ";
    }

}
