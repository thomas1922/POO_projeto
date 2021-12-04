
package supermercado;

import java.io.Serializable;

public  abstract class Promocao implements Serializable {
    private String produto;
    private Data dataInicio;
    private Data dataFim;

    public Promocao(String produto, Data dataInicio, Data dataFim) {
        this.produto = produto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
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

 
    public abstract int promocao(int quantidade,int precoUnitario);
    
    @Override
    public String toString(){
        return "Produto: "+getProduto()+"Data de inicio: "+getDataInicio()+"Data Fim: "+getDataFim();}
     
}
