package supermercado;

import java.io.Serializable;

public abstract class Promocao implements Serializable {
    private String nomeProduto;
    private Data dataInicio;
    private Data dataFim;
    private int ativa;

    public Promocao(String nomeProduto,Data dataInicio, Data dataFim, Data dataHoje) {
        this.nomeProduto=nomeProduto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativa=ativaPromocao(dataHoje);
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
    
    
    public abstract Double promoçao(int quantidade, Double preçoUnitario);
    
    public int ativaPromocao(Data dataHoje){
        int ativador;
        Data data = new Data();
        ativador= data.comparaDatas(dataInicio, dataFim, dataHoje);
        return ativador;
    }
    
    @Override
    public String toString(){
        return " ";}
     
}
