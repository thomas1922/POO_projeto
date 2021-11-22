
package supermercado;

public abstract class Promocao {
    int produto;
    int dataInicio;
    int dataFim;

    public Promocao(int produto, int dataInicio, int dataFim) {
        this.produto = produto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getProduto() {
        return produto;
    }

    public void setProduto(int produto) {
        this.produto = produto;
    }

    public int getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(int dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getDataFim() {
        return dataFim;
    }

    public void setDataFim(int dataFim) {
        this.dataFim = dataFim;
    }
    
    @Override
    public String toString(){
        return " ";}
     
}
