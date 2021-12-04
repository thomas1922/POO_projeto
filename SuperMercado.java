
package supermercado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    public ArrayList<Cliente> readFichClients(File f){
     ArrayList<Cliente> clientes;
     clientes = new ArrayList<>();
             if(f.exists() && f.isFile() ) {
            try{
                
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                
                String line;
                String[] arr;
                
                while((line = br.readLine()) != null){
                    arr = line.split(",");
                    if(arr[0].equals("REG")&& arr.length == 6){
                        Cliente novo = new Cliente(arr[1],arr[2],arr[3],Integer.parseInt(arr[4]),arr[5],1);
                        clientes.add(novo);
                     }
                    else if(arr[0].equals("NORM")&& arr.length == 6){
                        Cliente novo = new Cliente(arr[1],arr[2],arr[3],Integer.parseInt(arr[4]),arr[5],0);
                        clientes.add(novo);
                    }
                    }
            }catch (NumberFormatException e){
                System.out.println("Erro ao criar objeto");
                
            }catch (FileNotFoundException ex) {
                System.out.println("Erro ao abrir ficheiro");
            }catch(IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
     }
    else{
            System.out.println("Ficheiro não existe");
        }
    return clientes;
}
    public ArrayList<Promocao> readFichProm(File f){
     ArrayList<Promocao> prom;
     prom = new ArrayList<>();
             if(f.exists() && f.isFile() ) {
            try{
               
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                
                String line;
                String[] arr;
                String[] aux;
                String[] aux2;
                
                while((line = br.readLine()) != null){
                    arr = line.split(",");
                    if(arr[0].equals("MENOS")){
                        aux = arr[2].split("-");
                        Data inicio = new Data(Integer.parseInt(aux[0]),Integer.parseInt(aux[1]),Integer.parseInt(aux[2]));
                        aux2 = arr[3].split("-");
                        Data fim = new Data(Integer.parseInt(aux2[0]),Integer.parseInt(aux2[1]),Integer.parseInt(aux2[2]));
                        PagueMenos prom1 = new PagueMenos(arr[1],inicio,fim);
                        prom.add(prom1);
                    }
                    else if(arr[0].equals("PAG3")){
                        aux = arr[2].split("-");
                        Data inicio = new Data(Integer.parseInt(aux[0]),Integer.parseInt(aux[1]),Integer.parseInt(aux[2]));
                        aux2 = arr[3].split("-");
                        Data fim = new Data(Integer.parseInt(aux2[0]),Integer.parseInt(aux2[1]),Integer.parseInt(aux2[2]));
                        Pague3Leve4 promx = new Pague3Leve4(arr[1],inicio,fim);
                        prom.add(promx);
                    }
                    }
            }catch (NumberFormatException e){
                System.out.println("Erro ao criar objeto");
            }catch (FileNotFoundException ex) {
                System.out.println("Erro ao abrir ficheiro");
            }catch(IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
     }
    else{
            System.out.println("Ficheiro não existe");
        }
    return prom;
}
    public ArrayList<Produto> readFichProducts(File f){
     ArrayList<Produto> product;
     product = new ArrayList<>();
             if(f.exists() && f.isFile() ) {
            try{
                
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                
                String line;
                String[] arr;
                
                while((line = br.readLine()) != null){
                    arr = line.split(",");
                    if(arr[0].equals("LIMP")&& arr.length == 6){
                        Limpeza limpeza = new Limpeza(Integer.parseInt(arr[1]),arr[2],Double.parseDouble(arr[3]),Integer.parseInt(arr[4]),Integer.parseInt(arr[5]));
                        product.add(limpeza);
                     }
                    else if(arr[0].equals("MOB")&& arr.length == 7){
                         Mobiliario mobiliario = new Mobiliario(Integer.parseInt(arr[1]),arr[2],Double.parseDouble(arr[3]),Integer.parseInt(arr[4]),Integer.parseInt(arr[5]),Integer.parseInt(arr[6]));
                         product.add(mobiliario);
                    }
                    else if(arr[0].equals("ALI")&& arr.length == 7){
                        Alimentar alimento = new Alimentar (Integer.parseInt(arr[1]),arr[2],Double.parseDouble(arr[3]),Integer.parseInt(arr[4]),Integer.parseInt(arr[5]),Integer.parseInt(arr[6]));
                        product.add(alimento);
                    }
                }
            }catch (NumberFormatException e){
                System.out.println("Erro ao criar objeto");  
            }catch (FileNotFoundException ex) {
                System.out.println("Erro ao abrir ficheiro************");
            }catch(IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
     }
    else{
            System.out.println("Ficheiro não existe");
        }
    return product;
}
      
    public void makeFichClients(File ftext,File fobj){
    ArrayList<Cliente> list = readFichClients(ftext);
            try{
        FileOutputStream fos= new FileOutputStream(fobj);
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();
    } catch(FileNotFoundException ex) {
        System.out.println("Erro a criar ficheiro.");
    }
    catch(IOException ex) {
     System.out.println("Erro a escrever para o ficheiro.");
 }
    }
    public void makeFichPromocao(File ftext,File fobj) {
    ArrayList<Promocao> list = readFichProm(ftext);
            try{
        FileOutputStream fos= new FileOutputStream(fobj);
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();
    } catch(FileNotFoundException ex) {
        System.out.println("Erro a criar ficheiro.");
    }
    catch(IOException ex) {
     System.out.println("Erro a escrever para o ficheiro.");
 }
    }
    public void makeFichProducts(File ftext,File fobj) {
    ArrayList<Produto> list = readFichProducts(ftext);
            try{
        FileOutputStream fos= new FileOutputStream(fobj);
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();
    } catch(FileNotFoundException ex) {
        System.out.println("Erro a criar ficheiro************.");
    }
    catch(IOException ex) {
     System.out.println("Erro a escrever para o ficheiro---------------------.");
 }
    }

    public void leFicheiroPromocao(ArrayList<Promocao>listaPromocao,File fobj){
    try{
FileInputStream fis = new FileInputStream(fobj);
ObjectInputStream ois= new ObjectInputStream(fis);
ArrayList<Promocao> x = (ArrayList<Promocao>)ois.readObject();
for(Promocao a:x){
    listaPromocao.add(a);
}
ois.close();
fis.close();
}
catch(FileNotFoundException ex) {
System.out.println("Erro a abrir ficheiro.");} 
catch(IOException ex) {
System.out.println("Erro a ler ficheiro.");} catch (ClassNotFoundException ex) { 
          System.out.println("Erro a converter objeto.");
       } 
}        
    public void leFicheiroClients(ArrayList<Cliente>listaClientes,File fobj){
    try{
FileInputStream fis = new FileInputStream(fobj);
ObjectInputStream ois= new ObjectInputStream(fis);
ArrayList<Cliente> x = (ArrayList<Cliente>)ois.readObject();
for(Cliente c:x){
    listaClientes.add(c);
}
ois.close();
fis.close();
}
catch(FileNotFoundException ex) {
System.out.println("Erro a abrir ficheiro.");} 
catch(IOException ex) {
System.out.println("Erro a ler ficheiro.");} catch (ClassNotFoundException ex) { 
          System.out.println("Erro a converter objeto.");
       } 
} 
    public void leFicheiroProducts(ArrayList<Produto>listaProdutos,File fobj){
    try{
FileInputStream fis = new FileInputStream(fobj);
ObjectInputStream ois= new ObjectInputStream(fis);
ArrayList<Produto> x = (ArrayList<Produto>)ois.readObject();
for(Produto c:x){
    listaProdutos.add(c);
}
ois.close();
fis.close();
}
catch(FileNotFoundException ex) {
System.out.println("Erro a abrir ficheiro.");} 
catch(IOException ex) {
System.out.println("Erro a ler ficheiro.");} catch (ClassNotFoundException ex) { 
          System.out.println("Erro a converter objeto.");
       } 
}    

    
    public static void main(String[] args) {
        SuperMercado bomDia = new SuperMercado();
        bomDia.ficheiros();
        /*bomDia.login();
        bomDia.menu();*/      
}
        

    public void ficheiros(){
        File fclientes = new File("ClientesText.txt");
        File fprom = new File("PromoText.txt");
        File fproducts = new File ("ProdutosText.txt");
        File fObjProm = new File("Promocoes.obj");
        File fObjClients = new File("Clientes.obj");
        File fObjProducts = new File("Produtos.obj");
        
        if(!fObjProm.exists() && !fObjClients.exists() && !fObjProducts.exists()){
            makeFichClients(fclientes,fObjClients);
            makeFichPromocao(fprom,fObjProm);
            makeFichProducts(fproducts,fObjProducts);
        }
        else{
            leFicheiroClients(clientes,fObjClients);
            leFicheiroPromocao(promocao,fObjProm);
            leFicheiroProducts(produtos,fObjProducts);
        }
        for(Cliente c: clientes){
            System.out.println(c+"\n");
           
        }
        System.out.println("\n\n");
        
        for(Promocao x: promocao){
            System.out.println(x+"\n");
 }
        System.out.println("\n\n");
         for(Produto y: produtos){
            System.out.println(y+"\n");
        
 }
}
}

    
