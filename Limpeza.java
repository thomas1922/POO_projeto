
package supermercado;

public class Limpeza extends Produto{
    private int grauToxicidade;

    public Limpeza(int identificador, String nome, double precoUnitario, int stock,int grauToxicidade) {
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
         return "Nome: "+getNome()+" Preco por Unidade: "+getPrecoUnitario()+" Stock"+getStock()+" ";
    }
    
    
}
