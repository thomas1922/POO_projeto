
package supermercado;

public  abstract class Promocao {
    private String produto;
    private int dataInicio;
    private int dataFim;

    public Promocao(String produto, int dataInicio, int dataFim) {
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

 
    public abstract int promocao(int quantidade,int precoUnitario);
    
    @Override
    public String toString(){
        return " ";}
     
}
