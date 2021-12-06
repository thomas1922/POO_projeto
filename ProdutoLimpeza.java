package supermercado;

public class ProdutoLimpeza extends Produto {
    private int grauToxicidade;

    public ProdutoLimpeza(int identificador, String nome, Double precoUnitario, int stock,int grauToxicidade) {
        super(identificador, nome, precoUnitario, stock);
        this.grauToxicidade = grauToxicidade;
    }

    public int getGrauToxicidade() {
        return grauToxicidade;
    }

    public void setGrauToxicidade(int grauToxicidade) {
        this.grauToxicidade = grauToxicidade;
    }
    
    @Override
    public String toString(){
        return " ";
    }
    
    
}