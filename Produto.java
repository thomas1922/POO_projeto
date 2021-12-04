
package supermercado;

import java.io.Serializable;

public abstract class  Produto implements Serializable {
   private int identificador;
   private String nome;
   private double precoUnitario;
   private int stock;

    public Produto(int identificador, String nome, double precoUnitario, int stock) {
        this.identificador = identificador;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.stock = stock;
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

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

   @Override
  public String toString(){
      return "Nome: "+getNome()+" Preco por Unidade: "+getPrecoUnitario()+" Stock"+getStock()+" ";
  }
   
   
}