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
        bomDia.menu();

    }

    /**
     * method that asks the user to introduce an email and checks if it's valid,
     * if it's valid it returns the user's email otherwise it asks the user's to
     * introduce email again
     *
     * @return String
     */
    public String login() {
        String nova = "", auxiliar[] = {};
        int aux = 0;
        Boolean correct = false;

        do {
            Scanner scDados = new Scanner(System.in);
            System.out.println("Insira o seu email");
            if (scDados.hasNext()) {
                auxiliar = scDados.nextLine().split("\s");
            }
            while (auxiliar[aux].isBlank()) {
                aux++;
            }
            nova = auxiliar[aux];
            for (BaseDados c : clientes) {
                if (nova.equals(c.getCliente().getEmail())) {
                    correct = true;
                }
            }
            if (correct == false) {
                System.out.println("O email não existe ou está errado");
                System.out.println("Tente outra vez");
                System.out.println("----------------------------------\n");
            }

        } while (!correct);
        return nova;
    }

    /**
     * Menu of the programme where you can login and perform all the
     * operations of the programme, make a purchase, see the previous purchases,
     * change the date, view the products in promotion and exit the programme
     */
    public void menu() {
        Data hoje = new Data(5, 12, 2021);
        ficheiros(hoje);
        ficheiroPromocao(hoje);
        int escolha = 0;
        String email = login();
        Cliente cliente = cliente(email);

        do {
            System.out.println("\n------------------------------");
            System.out.println("Bem Vindo     Data: " + hoje + "\n");
            System.out.println("1-efetuar compra");
            System.out.println("2-Consultas compras efetuadas");
            System.out.println("3-Mudar data");
            System.out.println("4-Promoções");
            System.out.println("5-Sair");
            System.out.println("------------------------------\n");
            Scanner scDados = new Scanner(System.in);
            if (scDados.hasNextInt()) {
                escolha = scDados.nextInt();
            } else {
                System.out.println("erro");
                escolha = 0;
            }
            switch (escolha) {
                case 1 -> {
                    Compra compra = compra(hoje, cliente);
                    atualizaBaseDados(compra, email);
                }
                case 2 ->
                    consultaCompra(email, hoje);

                case 3 -> {
                    hoje = hoje.mudaData(hoje);
                    atualizaPromocoes();
                    ficheiroPromocao(hoje);
                }
                case 4 -> {
                    mostraPromo();

                }
                case 5 -> {
                    updateFich();
                    System.exit(0);
                }
            }
        } while (escolha != 5);
    }

    /**
     * method that remove the promotion of the arrayList
     */
    public void atualizaPromocoes() {
        for (int i = 0; i < promocao.size(); i++) {
            promocao.remove(i);
        }
    }

    /**
     * method that given an email, searches the list of clients, and returns the
     * client that has the given email
     *
     * @param email email
     * @return client
     */
    public Cliente cliente(String email) {
        Cliente cliente = new Cliente();
        for (BaseDados bd : clientes) {
            if (bd.getCliente().getEmail().equals(email)) {
                cliente = bd.getCliente();
            }
        }
        return cliente;
    }

    /**
     * method that, given as parameters the current date and a customer, shows
     * the products available for purchase. The purchase is made by requesting
     * the name of the product and the desired quantity, being possible to buy
     * several products in a single purchase.At the end it returns a purchase,
     * with the total price and the products bought
     *
     * @param hoje current date
     * @param cliente cliente
     * @return purchase
     */
    public Compra compra(Data hoje, Cliente cliente) {
        ArrayList<Produto> produtosComprados = new ArrayList<>();
        System.out.println("*****************************");
        for (Produto p : produtos) {
            System.out.println(p.getNome() + "  Preço: " + p.getPrecoUnitario() + " Stock: " + p.getStock());
        }
        System.out.println("******************************\n");
        int quantidade = 0, i = 0, aux = 0;
        double custo = 0.0;
        double custoTotal;
        double precoTransporte = 0.0;
        double custoExtra = 0.0;
        String simNao = "";
        while (aux == 0) {
            i = 0;
            Produto novoProduto = pedeProduto(produtos);
            quantidade = pedeQuantidade(novoProduto);

            if (produtosComprados.isEmpty()) {
                produtosComprados.add(novoProduto);
            } else {
                if (!produtosComprados.contains(novoProduto)) {
                    produtosComprados.add(novoProduto);
                }
            }

            for (Promocao prom : promocao) {
                if (prom.getNomeProduto().equals(novoProduto.getNome())) {
                    if (prom.getAtiva() == 1) {
                        custo += quantidade * novoProduto.getPrecoUnitario();
                        custo -= prom.promocao(quantidade, novoProduto.getPrecoUnitario());
                        i++;
                    }
                }
            }

            if (i == 0) {
                custo += quantidade * novoProduto.getPrecoUnitario();
            }
            custoExtra += custoExtraMobiliario(novoProduto, quantidade);
            Scanner scDados = new Scanner(System.in);
            System.out.println("Deseja continuar a adicionar produtos ao carrinho?");
            System.out.println("Sim - digite sim");
            System.out.println("Não - digite 'nao'\n");
            if (scDados.hasNext()) {
                simNao = scDados.nextLine();
            }
            if (simNao.equals("nao")) {
                aux = 1;
            }
        }

        precoTransporte += custoTransporte(cliente, custo);
        precoTransporte += custoExtra;
        System.out.println("O preço é " + custo + " euros");
        System.out.println("O custo do transporte é " + precoTransporte + " euros");
        custoTotal = custo + precoTransporte;
        Compra novaCompra = new Compra(custoTotal, produtosComprados, hoje);
        return novaCompra;
    }

    /**
     * method that asks the user for the name of a product, and checks if it is
     * available in the list of products, otherwise it asks again to insert
     * another name, until the name of the product inserted is in the list of
     * products
     *
     * @param produtosEmStock ArrayList of produtcts
     * @return product
     */
    public Produto pedeProduto(ArrayList<Produto> produtosEmStock) {
        String nomeProduto = " ", auxiliar[] = {};

        int i = 0, aux = 0;

        Produto produto = new Produto();
        Produto produtoAux = new Produto();

        System.out.println("Que produto deseja comprar?");
        Scanner scDados = new Scanner(System.in);
        if (scDados.hasNext()) {
            auxiliar = scDados.nextLine().split("\s");
        }
        while (auxiliar[aux].isBlank()) {
            aux++;
        }
        nomeProduto = auxiliar[aux];

        for (Produto p : produtosEmStock) {
            if (p.getNome().equals(nomeProduto)) {
                produto = p;
            } else {
                i++;
            }
        }

        if (i == produtosEmStock.size()) {
            System.out.println("O produto que deseja não se encontra disponível em stock");
            produtoAux = pedeProduto(produtosEmStock);
            return produtoAux;
        }

        return produto;
    }

    /**
     * method that asks the user for the desired quantity of the product, and
     * checks if the quantity is available in stock, otherwise it will ask again
     * for a quantity that is available
     *
     * @param produto product
     * @return amount
     */
    public int pedeQuantidade(Produto produto) {
        int quantidade = 0, stock;
        int quantidadeAux = 0;
        Scanner scDados = new Scanner(System.in);
        System.out.println("Qual a quantidade que deseja?");
        if (scDados.hasNext()) {
            quantidade = scDados.nextInt();
        }
        if (produto.getStock() < quantidade) {
            System.out.println("Não há a quantidade disponível em stock");
            quantidadeAux = pedeQuantidade(produto);
            return quantidadeAux;
        } else {
            stock = produto.getStock() - quantidade;
            produto.setStock(stock);
            if (produto.getStock() == 0) {
                produtos.remove(produto);
            }
        }
        return quantidade;
    }

    /**
     * method that receives as parameters a product and the quantity and checks
     * if the product is of type ProdutoMobiliario, if so returns the extra
     * value for each product, otherwise returns the extra price equal to 0
     *
     * @param produto product
     * @param quantidade amount
     * @return price
     */
    public double custoExtraMobiliario(Produto produto, int quantidade) {
        double custoExtra = 0;
        try {
            ProdutoMobiliario mob = (ProdutoMobiliario) produto;
            if (mob.getPeso() > 15) {
                custoExtra = quantidade * 10.0;
            } else {
                return 0;
            }
        } catch (ClassCastException x) {
            return 0.0;
        }

        return custoExtra;
    }

    /**
     * method that receives as parameters a customer and the price of the
     * purchase made, and checks if it is regular or not. If regular and the
     * price is over 40 euros, returns 0 otherwise returns 15. If it's not
     * regular, returns 20.
     *
     * @param cliente client
     * @param preçoCompra price of purchase
     * @return price
     */
    public double custoTransporte(Cliente cliente, double preçoCompra) {
        int regularidade = cliente.getRegularidade();
        double custoTransporte;
        if (regularidade == 1) {
            if (preçoCompra > 40.0) {
                custoTransporte = 0.0;
            } else {
                custoTransporte = 15.0;
            }
        } else {
            custoTransporte = 20.0;
        }

        return custoTransporte;
    }

    /**
     * method that receives as parameters the email and the purchase made, and
     * adds to the shopping list of the customer who has the email, the purchase
     * made
     *
     * @param compra purchase
     * @param email email
     * @return true or false
     */
    public boolean atualizaBaseDados(Compra compra, String email) {
        for (BaseDados c : clientes) {
            if (c.getCliente().getEmail().equals(email)) {
                c.addCompras(compra);
                return true;
            }
        }
        return false;
    }

    /**
     * method that receives as parameters an email and current date, and shows
     * the purchases previously made by the customer who has the email
     *
     * @param email email
     * @param dataHoje current date
     */
    public void consultaCompra(String email, Data dataHoje) {
        for (BaseDados c : clientes) {
            if (c.getCliente().getEmail().equals(email)) {
                ArrayList<Compra> compras = c.getCompras();
                for (Compra d : compras) {
                    Data x = d.getData();
                    if (x.verificaData(dataHoje, x) == 0) {
                        System.out.println(d);
                    }
                }
            }
        }
    }

    /**
     * method that shows the promotions currently active
     */
    public void mostraPromo() {
        System.out.println("----------Promoções----------");
        for (Promocao p : promocao) {
            if (p.getAtiva() == 1) {
                System.out.println(p);
            }
        }
    }

    /**
     * method that takes as parameters an ArrayList and an object file, and
     * writes to the object file given as parameter the objects contained in the
     * ArrayList
     *
     * @param clientes ArrayList of clients
     * @param fobj database object file
     */
    public void updateFichClients(ArrayList<BaseDados> clientes, File fobj) {
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

    /**
     * method that takes as parameters an ArrayList and an object file, and
     * writes to the object file given as parameter the objects contained in the
     * ArrayList
     *
     * @param produtos ArrayList of products
     * @param fobj products object file
     */
    public void updateFichProducts(ArrayList<Produto> produtos, File fobj) {
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

    /**
     * method that uses the updateFichClients and updateFichProducts methods to
     * update the files
     */
    public void updateFich() {
        File fObjClients = new File("Clientes.obj");
        File fObjProducts = new File("Produtos.obj");
        updateFichProducts(produtos, fObjProducts);
        updateFichClients(clientes, fObjClients);

    }

    /**
     * method that takes as parameters a text file, reads it and creates an
     * ArrayList of objects.
     *
     * @param f text file
     * @return ArrayList of BaseDados
     */
    public ArrayList<BaseDados> readFichClientsTxt(File f) {
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

    /**
     * method that receives as parameters a text file and the current date,
     * reads and creates an ArrayList of objects.
     *
     * @param f text file
     * @param hoje current date
     * @return ArrayList of promotions
     */
    public ArrayList<Promocao> readFichPromTxt(File f, Data hoje) {
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
                        PagueMenos prom1 = new PagueMenos(nomeProduto, inicio, fim, hoje);
                        prom.add(prom1);
                    } else if (arr[0].equals("PAG3")) {
                        nomeProduto = arr[1];
                        aux2 = arr[2].split("-");
                        Data inicio = new Data(Integer.parseInt(aux2[0]), Integer.parseInt(aux2[1]), Integer.parseInt(aux2[2]));
                        aux3 = arr[3].split("-");
                        Data fim = new Data(Integer.parseInt(aux3[0]), Integer.parseInt(aux3[1]), Integer.parseInt(aux3[2]));
                        Pague3Leve4 promx = new Pague3Leve4(nomeProduto, inicio, fim, hoje);
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

    /**
     * method that takes as parameters a text file, reads it and creates an
     * ArrayList of objects.
     *
     * @param f text file
     * @return ArrayList of products
     */
    public ArrayList<Produto> readFichProductsTxt(File f) {
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

    /**
     * method that takes as parameters the current date, the text file and an
     * object file and creates the object file passed as parameter.
     *
     * @param ftext database text file
     * @param fobj database object file
     */
    public void makeFichClients(File ftext, File fobj) {
        ArrayList<BaseDados> list = readFichClientsTxt(ftext);
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

    /**
     * method that takes as parameters the current date, the text file and an
     * object file and creates the object file passed as parameter.
     *
     * @param fText promotion text file
     * @param fObj promotion objects file
     * @param hoje current date
     */
    public void makeFichPromocao(File fText, File fObj, Data hoje) {
        ArrayList<Promocao> list = readFichPromTxt(fText, hoje);
        try {
            FileOutputStream fos = new FileOutputStream(fObj);
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

    /**
     * method that takes as parameters the text file and an object file and
     * creates the object file passed as parameter.
     *
     * @param fText products
     * @param fObj products object file
     */
    public void makeFichProducts(File fText, File fObj) {
        ArrayList<Produto> list = readFichProductsTxt(fText);
        try {
            FileOutputStream fos = new FileOutputStream(fObj);
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

    /**
     * method that receives as parameters a promotion objects file and an
     * ArrayList of objects. Reads the object file and puts the read objects in
     * the ArrayList
     *
     * @param listaPromocao ArrayList of promotion
     * @param fObj promotion objects file
     */
    public void readFichPromocao(ArrayList<Promocao> listaPromocao, File fObj) {
        try {
            FileInputStream fis = new FileInputStream(fObj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Promocao> promocoesLidas = (ArrayList<Promocao>) ois.readObject();
            for (Promocao p : promocoesLidas) {
                listaPromocao.add(p);
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

    /**
     * method that receives as parameters a database object file and an
     * ArrayList of objects. Reads the object file and puts the read objects in
     * the ArrayList
     *
     * @param listaClientes ArrayList of BaseDados
     * @param fObj database object file
     */
    public void readFichClients(ArrayList<BaseDados> listaClientes, File fObj) {
        try {
            FileInputStream fis = new FileInputStream(fObj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<BaseDados> clientesLidos = (ArrayList<BaseDados>) ois.readObject();
            for (BaseDados c : clientesLidos) {
                listaClientes.add(c);
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

    /**
     * method that receives as parameters a products object file and an
     * ArrayList of objects. Reads the object file and puts the read objects in
     * the ArrayList
     *
     * @param listaProdutos ArrayList of produtcs
     * @param fObj products object file
     */
    public void readFichProducts(ArrayList<Produto> listaProdutos, File fObj) {
        try {
            FileInputStream fis = new FileInputStream(fObj);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Produto> produtosLidos = (ArrayList<Produto>) ois.readObject();
            for (Produto p : produtosLidos) {
                listaProdutos.add(p);
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

    /**
     * method that receives as parameter the current date, verifies if the
     * promotion objects file exists, if it exists just read it, otherwise
     * creates it and reads the files.
     *
     * @param hoje current date
     */
    public void ficheiroPromocao(Data hoje) {
        File fProm = new File("PromoText.txt");
        File fObjProm = new File("Promocoes.obj");
        makeFichPromocao(fProm, fObjProm, hoje);
        readFichPromocao(promocao, fObjProm);
    }

    /**
     * method that takes as parameter the current date, checks if the object
     * file exists, if it does, just reads it, otherwise creates and reads the .
     *
     * @param hoje current date
     */
    public void ficheiros(Data hoje) {
        File fClientes = new File("ClientesText.txt");
        File fProducts = new File("ProdutosText.txt");
        File fObjClients = new File("Clientes.obj");
        File fObjProducts = new File("Produtos.obj");

        if (!fObjProducts.exists() || !fObjClients.exists()) {
            makeFichClients(fClientes, fObjClients);
            makeFichProducts(fProducts, fObjProducts);
            readFichClients(clientes, fObjClients);
            readFichProducts(produtos, fObjProducts);
        } else {
            readFichClients(clientes, fObjClients);
            readFichProducts(produtos, fObjProducts);
        }
    }
}
