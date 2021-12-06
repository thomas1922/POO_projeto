package supermercado;

public abstract class Promoçao {
    private String nomeProduto;
    private Data dataInicio;
    private Data dataFim;
    private int ativa;

    public Promoçao(String nomeProduto,Data dataInicio, Data dataFim, Data dataHoje) {
        this.nomeProduto=nomeProduto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativa=ativaPromoçao(dataHoje);
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
    
    public int ativaPromoçao(Data dataHoje){
        int ativador=0;
        Data data = new Data();
        ativador=data.comparaDatas(dataInicio, dataFim, dataHoje);
        return ativador;
    }
    
    @Override
    public String toString(){
        return " ";}
     
}