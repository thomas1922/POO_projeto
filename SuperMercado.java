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

    private ArrayList<BaseDados> clientes;
    private ArrayList<Produto> produtos;
    private ArrayList<Promocao> promocao;

    public SuperMercado() {
        clientes = new ArrayList<>();
        produtos = new ArrayList<>();
        promocao = new ArrayList<>();
    }
    
    public static void main(String[] args) {
        SuperMercado bomDia = new SuperMercado();
        Data hoje=new Data(5,12,2021);
        bomDia.ficheiros(hoje);
        bomDia.menu(hoje);
        bomDia.login();

    }
   

    public String login(){
        Boolean correct = false;
        String nova = "",auxiliar[]={};
        int aux=0;
        do{
            Scanner scDados = new Scanner(System.in);
            System.out.println("Insira o seu email");
            if(scDados.hasNext()){
            auxiliar=scDados.nextLine().split("\s");
        }
        while(auxiliar[aux].isBlank()){
            aux++;
        }
        nova=auxiliar[aux];
            for(BaseDados c: clientes){
                if(nova.equals(c.getCliente().getEmail())){
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

    public void menu(Data hoje) {
        int escolha = 0;
        String email = login();
        Cliente cliente = cliente(email);
        
        do {
            System.out.println("1-efetuar compra");
            System.out.println("2-Consultas compras efetuadas");
            System.out.println("3-Mudar");
            System.out.println("4-logout");
            Scanner scDados = new Scanner(System.in);
            if (scDados.hasNextInt()) {
                escolha = scDados.nextInt();
            }
            else{
                System.out.println("erro");
                escolha = 0;
            }
            switch (escolha) {
                case 1 -> {
                    Compra compra = compra(hoje,cliente);
                    atualizaBaseDados(compra, email);
                }
                case 2 -> consultaCompra(email);
                case 3 -> {
                    
                }
                case 4 -> {
                    UpdateFich();
                    System.exit(0);
            }}

        } while (escolha != 4);
    }
    public Cliente cliente(String email){
        Cliente cliente=new Cliente();
        for(BaseDados bd: clientes){
            if(bd.getCliente().getEmail().equals(email)){
                cliente=bd.getCliente();
            }
        }
        return cliente;
    }

    public Compra compra( Data hoje, Cliente cliente) {
        ArrayList<Produto> produtosComprados = new ArrayList<>();
        for(Produto p:produtos){
            System.out.println(p.getNome()+ "  Preço: "+p.getPrecoUnitario()+ " Stock: "+p.getStock());
        }
        int quantidade = 0, i=0,aux=0,aux2=0;
        double custo=0.0;
        double custoTotal;
        double precoTransporte = 0.0;
        double custoExtra = 0.0;
        String simNao="",auxiliar[]={};
        while(aux==0){
            i=0;
            Produto novoProduto = pedeProduto(produtos);
            quantidade=pedeQuantidade(novoProduto);
            
            System.out.println(novoProduto);
            System.out.println(quantidade);
            
            produtosComprados.add(novoProduto);
            
            
            
        for(Promocao prom: promocao){
            if(prom.getNomeProduto().equals(novoProduto.getNome())){
                if(prom.getAtiva()==1){
                    custo+=quantidade*novoProduto.getPrecoUnitario();
                    custo-=prom.promoçao(quantidade, novoProduto.getPrecoUnitario());
                    i++;
                }
            }
        }
           
	if(i==0)custo+=quantidade*novoProduto.getPrecoUnitario();
        custoExtra+= custoExtraMobiliario(novoProduto, quantidade);
        custo+=custoExtra;
        Scanner scDados = new Scanner(System.in);
        System.out.println("Deseja continuar a adicionar produtos ao carrinho?");
        if(scDados.hasNext()){
            auxiliar=scDados.nextLine().split("\s");
        }
        while(auxiliar[aux2].isBlank()){
            aux2++;
        }
        simNao=auxiliar[aux2];
        if(simNao.equals("nao"))aux=1; 
        }
        
        precoTransporte += custoTransporte(cliente, custo);
        System.out.println("O preço é " + custo + " euros");
        System.out.println("O custo do transporte é " + precoTransporte+ " euros");
        custoTotal = custo + precoTransporte;
        Compra novaCompra = new Compra(custoTotal,produtosComprados,hoje);
        return novaCompra;
    }

    public Produto pedeProduto(ArrayList<Produto> produtosEmStock) {
        String nomeProduto=" ";

        String auxiliar[]={}; 
        
        int i=0,aux=0;

        Produto produto= new Produto();

        System.out.println("Que produto deseja comprar?");
        Scanner scDados = new Scanner(System.in);
        if(scDados.hasNext()){
            auxiliar=scDados.nextLine().split("\s");
        }
        while(auxiliar[aux].isBlank()){
            aux++;
        }
        nomeProduto=auxiliar[aux];
                

        for(Produto p: produtosEmStock){
            if(p.getNome().equals(nomeProduto)){
                produto=p;
            }
            else{
                i++;
            }
        }

        if(i==produtosEmStock.size()){
            System.out.println("O produto que deseja não se encontra disponível em stock");
            pedeProduto(produtosEmStock);
        }

        return produto;
    }
    public int pedeQuantidade(Produto produto) {
        int quantidade = 0, stock;
        Scanner scDados = new Scanner(System.in);
        System.out.println("Qual a quantidade que deseja?");
        if (scDados.hasNext()) {
            quantidade = scDados.nextInt();
        }
        if(produto.getStock()<quantidade){
            System.out.println("Não há a quantidade disponível em stock");
            pedeQuantidade(produto);
        }
        else{
            stock=produto.getStock()-quantidade;
            produto.setStock(stock);
            if(produto.getStock()==0){
                produtos.remove(produto);
            }
        }
        return quantidade;
    }


   
    public double custoExtraMobiliario(Produto produto, int quantidade){
        double custoExtra = 0;
        try{
        ProdutoMobiliario mob = (ProdutoMobiliario)produto;
        if(mob.getPeso()>15){
            custoExtra = 10.0*quantidade;
        }
        else{
            return 0;
        }
        }catch(ClassCastException x){
                return 0.0;
        }
            
       return custoExtra; 
    }
    public double custoTransporte(Cliente cliente,double preçoCompra){
        int regularidade=cliente.getRegularidade();
        double custoTransporte;
        if(regularidade==1){
            if(preçoCompra>40.0){
                custoTransporte=0.0;
            }
            else{custoTransporte=15.0;} 
        }
        else custoTransporte=20.0;
        
        return custoTransporte;
    }
    
    public boolean atualizaBaseDados(Compra compra, String email) {
        for (BaseDados c : clientes) {
            if (c.getCliente().getEmail().equals(email)) {
                c.addCompras(compra);
                return true;
            }
        }
        return false;
    }
    
    public void consultaCompra(String email){
        for (BaseDados c : clientes) {
            if (c.getCliente().getEmail().equals(email)) {
                System.out.println(c.getCompras()+"\n");
            }
    }
    }
    public void UpdateFichClients(ArrayList<BaseDados> clientes, File fobj) {
        try {
            FileOutputStream fos = new FileOutputStream(fobj);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(clientes);
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    public void UpdateFichProducts(ArrayList<Produto> produtos, File fobj) {
        try {
            FileOutputStream fos = new FileOutputStream(fobj);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(produtos);
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    public void UpdateFich(){
        File fObjClients = new File("Clientes.obj");
        File fObjProducts = new File("Produtos.obj");
        UpdateFichProducts(produtos,fObjProducts);
        UpdateFichClients(clientes,fObjClients);
        
    }
    
    public ArrayList<BaseDados> readFichClients(File f) {
        ArrayList<BaseDados> clientes;
        clientes = new ArrayList<>();
        if (f.exists() && f.isFile()) {
            try {

                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String line;
                String[] arr;

                while ((line = br.readLine()) != null) {
                    arr = line.split(",");
                    if (arr[0].equals("REG") && arr.length == 6) {
                        Cliente novo = new Cliente(arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), arr[5], 1);
                        BaseDados nova = new BaseDados(novo);
                        clientes.add(nova);
                    } else if (arr[0].equals("NORM") && arr.length == 6) {
                        Cliente novo = new Cliente(arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), arr[5], 0);
                        BaseDados nova = new BaseDados(novo);
                        clientes.add(nova);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro ao criar objeto");

            } catch (FileNotFoundException ex) {
                System.out.println("Erro ao abrir ficheiro");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro não existe");
        }
        return clientes;
    }

    public ArrayList<Promocao> readFichProm(File f, Data hoje) {
        ArrayList<Promocao> prom;
        prom = new ArrayList<>();
        if (f.exists() && f.isFile()) {
            try {

                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String line;
                String[] arr;
                String nomeProduto;
                String[] aux2;
                String[] aux3;

                while ((line = br.readLine()) != null) {
                    arr = line.split(",");
                    if (arr[0].equals("MENOS")) {
                        nomeProduto = arr[1];
                        aux2 = arr[2].split("-");
                        Data inicio = new Data(Integer.parseInt(aux2[0]), Integer.parseInt(aux2[1]), Integer.parseInt(aux2[2]));
                        aux3 = arr[3].split("-");
                        Data fim = new Data(Integer.parseInt(aux3[0]), Integer.parseInt(aux3[1]), Integer.parseInt(aux3[2]));
                        PagueMenos prom1 = new PagueMenos(nomeProduto,inicio, fim, hoje);
                        prom.add(prom1);
                    } else if (arr[0].equals("PAG3")) {
                        nomeProduto = arr[1];
                        aux2 = arr[2].split("-");
                        Data inicio = new Data(Integer.parseInt(aux2[0]), Integer.parseInt(aux2[1]), Integer.parseInt(aux2[2]));
                        aux3 = arr[3].split("-");
                        Data fim = new Data(Integer.parseInt(aux3[0]), Integer.parseInt(aux3[1]), Integer.parseInt(aux3[2]));
                        Pague3Leve4 promx = new Pague3Leve4(nomeProduto,inicio, fim, hoje);
                        prom.add(promx);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro ao criar objeto");
            } catch (FileNotFoundException ex) {
                System.out.println("Erro ao abrir ficheiro");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro não existe");
        }
        return prom;
    }

    public ArrayList<Produto> readFichProducts(File f) {
        ArrayList<Produto> product;
        product = new ArrayList<>();
        if (f.exists() && f.isFile()) {
            try {

                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String line;
                String[] arr;

                while ((line = br.readLine()) != null) {
                    arr = line.split(",");
                    if (arr[0].equals("LIMP") && arr.length == 6) {
                        ProdutoLimpeza limpeza = new ProdutoLimpeza(Integer.parseInt(arr[1]), arr[2], Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]));
                        product.add(limpeza);
                    } else if (arr[0].equals("MOB") && arr.length == 7) {
                        ProdutoMobiliario mobiliario = new ProdutoMobiliario(Integer.parseInt(arr[1]), arr[2], Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]), Integer.parseInt(arr[6]));
                        product.add(mobiliario);
                    } else if (arr[0].equals("ALI") && arr.length == 7) {
                        ProdutoAlimentar alimento = new ProdutoAlimentar(Integer.parseInt(arr[1]), arr[2], Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5]), Integer.parseInt(arr[6]));
                        product.add(alimento);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro ao criar objeto");
            } catch (FileNotFoundException ex) {
                System.out.println("Erro ao abrir ficheiro");
            } catch (IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");
            }
        } else {
            System.out.println("Ficheiro não existe");
        }
        return product;
    }

    public void makeFichClients(File ftext, File fobj) {
        ArrayList<BaseDados> list = readFichClients(ftext);
        try {
            FileOutputStream fos = new FileOutputStream(fobj);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    public void makeFichPromocao(File ftext, File fobj, Data hoje) {
        ArrayList<Promocao> list = readFichProm(ftext,hoje);
        try {
            FileOutputStream fos = new FileOutputStream(fobj);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    public void makeFichProducts(File ftext, File fobj) {
        ArrayList<Produto> list = readFichProducts(ftext);
        try {
            FileOutputStream fos = new FileOutputStream(fobj);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    public void leFicheiroPromocao(ArrayList<Promocao> listaPromocao, File fobj) {
        try {
            FileInputStream fis = new FileInputStream(fobj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Promocao> x = (ArrayList<Promocao>) ois.readObject();
            for (Promocao a : x) {
                listaPromocao.add(a);
            }
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a ler ficheiro.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto.");
        }
    }

    public void leFicheiroClients(ArrayList<BaseDados> listaClientes, File fobj) {
        try {
            FileInputStream fis = new FileInputStream(fobj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<BaseDados> x = (ArrayList<BaseDados>) ois.readObject();
            for (BaseDados c : x) {
                listaClientes.add(c);
            }
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro.");
        } catch (IOException ex) {
            System.out.println("bbbb");
            System.out.println("Erro a ler ficheiro.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto.");
        }
    }

    public void leFicheiroProducts(ArrayList<Produto> listaProdutos, File fobj) {
        try {
            FileInputStream fis = new FileInputStream(fobj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Produto> x = (ArrayList<Produto>) ois.readObject();
            for (Produto c : x) {
                listaProdutos.add(c);
            }
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a ler ficheiro.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto.");
        }
    }

    public void ficheiros(Data hoje) {
        File fclientes = new File("ClientesText.txt");
        File fprom = new File("PromoText.txt");
        File fproducts = new File("ProdutosText.txt");
        File fObjProm = new File("Promocoes.obj");
        File fObjClients = new File("Clientes.obj");
        File fObjProducts = new File("Produtos.obj");

        if (!fObjProm.exists()||!fObjClients.exists() || !fObjProducts.exists()) {
            makeFichClients(fclientes, fObjClients);
            makeFichPromocao(fprom, fObjProm, hoje);
            makeFichProducts(fproducts, fObjProducts);
            leFicheiroClients(clientes, fObjClients);
            leFicheiroPromocao(promocao, fObjProm);
            leFicheiroProducts(produtos, fObjProducts);
        } else {
            leFicheiroClients(clientes, fObjClients);
            leFicheiroPromocao(promocao, fObjProm);
            leFicheiroProducts(produtos, fObjProducts);
        }
    }
}
