
package supermercado;

import java.util.ArrayList;
import java.util.Scanner;


public class SuperMercado {

    private ArrayList<Cliente> clientes;
    private ArrayList<Produto> produtos;
    private ArrayList<Promocao> promocao;
    
    public SuperMercado(){
        clientes = new ArrayList<>();
        produtos = new ArrayList<>();
        promocao = new ArrayList<>();
    }
    
    public void addCliente(Cliente a){
        clientes.add(a);
    }
    public void addProduto(Produto b){
        produtos.add(b);
    }
    public void addPromocao(Promocao c){
        promocao.add(c);
    }
    
    public Boolean comparaEmail(String x){
        boolean y = false;
        for(Cliente c: clientes){
            if(c.getEmail()== x){
                y = true;
                break;
            }
            System.out.println();
            }
        return y;
    }
    
    public String login(){
        Boolean correct = false;
        String nova = "";
        do{
            Scanner scDados = new Scanner(System.in);
            System.out.println("Insira o seu email");
            if (scDados.hasNext()) {
                 if (scDados.hasNext()) {
                    nova = scDados.nextLine();}}
            for(Cliente c: clientes){
                if(nova.equals(c.getEmail())){
                correct = true;
            }
            }
            if(correct == false){
                System.out.println("O email não existe ou está errado");
                System.out.println("----------------------------------\n");
            }
           
        }while(!correct);  
        return nova;
    }
    
    public void menu(){
    int escolha = 0;
    String email = login();
    do{
        System.out.println("1-efetuar compra");
        System.out.println("2-Consultas compras efetuadas");
        System.out.println("3-Mudar dia");
        System.out.println("4-logout");
        Scanner scDados = new Scanner(System.in);
         if (scDados.hasNextInt()) {
            escolha = scDados.nextInt();}
        
        switch(escolha){
            case 1:;break;
            case 2:;break;
            case 3:;break;
            case 4:System.exit(0);break;
        }
        
    }while(escolha!=4);
}
    
    public static void main(String[] args) {
        SuperMercado bomDia = new SuperMercado();
        Cliente x = new Cliente("Antonio","Rua de Angola","a8",965345654,1242002,1);
        Cliente x1 = new Cliente("Antonio","Rua de Angola","b",965345654,1242002,1);
        Cliente x2 = new Cliente("Antonio","Rua de Angola","c",965345654,1242002,1);
        Cliente x3 = new Cliente("Antonio","Rua de Angola","d",965345654,1242002,1);
        Cliente x5 = new Cliente("Antonio","Rua de Angola","e",965345654,1242002,1);
        Cliente x6 = new Cliente("Antonio","Rua de Angola","f",965345654,1242002,1);
        Cliente x7 = new Cliente("Antonio","Rua de Angola","g",965345654,1242002,1);
        bomDia.addCliente(x1);
        bomDia.addCliente(x2);
        bomDia.addCliente(x3);
        bomDia.addCliente(x5);
        bomDia.addCliente(x6);
        bomDia.addCliente(x7);
        bomDia.addCliente(x);
        bomDia.menu();
        
        
        
        
    }
    
}
