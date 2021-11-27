
package supermercado;

import java.util.ArrayList;


public abstract class  Produto {
   private int identificador;
   private String nome;
   private int precoUnitario;
   private int stock;
   private ArrayList<Promocao> promocoes;

    public Produto(int identificador, String nome, int precoUnitario, int stock, ArrayList<Promocao> promocoes) {
        this.identificador = identificador;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.stock = stock;
        this.promocoes = promocoes;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(int precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ArrayList<Promocao> getPromocoes() {
        return promocoes;
    }

    public void setPromocoes(ArrayList<Promocao> promocoes) {
        this.promocoes = promocoes;
    }
   
   
    

    
    
   @Override
  public String toString(){
      return " ";
  }
   
   
}
