package supermercado;

public class Mobiliario extends Produto {
    private int peso;
    private int dimensao;
    
    public Mobiliario(int identificador, String nome, Double precoUnitario, int stock,int peso,int dimensao) {
        super(identificador, nome, precoUnitario, stock);
        this.peso = peso;
        this.dimensao = dimensao;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getDimensao() {
        return dimensao;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }
    
    @Override
    public String toString(){
        return " ";
    }
}