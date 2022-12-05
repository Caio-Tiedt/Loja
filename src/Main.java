import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        CreateFile.criar("Produtos.txt");

        List<Produto> produtos = LerFile.lerProduto("Produtos.txt");

        listarProdutos(produtos);
        List<Produto> produtosCompras = new ArrayList<>();
        List<Produto> produtosClone = new ArrayList<>();
        Scanner sc = new Scanner(System.in);


        String nome;
        int quantidade;
        double preco;
        double precoCompras=0;
        int indice;
        int comprarMais;
        String pesquisa;



        System.out.println("Olá");
        System.out.println("Digite 0 para listar os produtos");
        System.out.println("Digite 1 para adicionar um produto");
        System.out.println("Digite 2 para alterar um produto");
        System.out.println("Digite 3 para excluir um produto");
        System.out.println("Digite 4 para pesquisar por um produto");
        System.out.println("Digite 5 para vender um produto");
        System.out.println("Digite 6 para sair do programa");
        System.out.print("Digite aqui: ");

        int input = sc.nextInt();
        sc.nextLine();


        switch (input){
            case 0:
                listarProdutos(produtos);
                break;
            case 1:
                System.out.print("Digite aqui o nome do novo produto: ");
                nome = sc.nextLine();
                System.out.print("Digite aqui a quantidade do novo produto: ");
                quantidade = sc.nextInt();
                System.out.print("Digite aqui o preco do novo produto (separe centavos por vírgula): ");
                preco = sc.nextDouble();
                produtos.add(adicionarProdutos(nome, quantidade, preco));
                System.out.println("Nova lista de produtos");
                listarProdutos(produtos);
                break;
            case 2:
                System.out.println("Digite aqui o índice do produto que deseja alterar");
                indice = sc.nextInt();
                sc.nextLine();
                System.out.print("Digite aqui o nome do produto alterado: ");
                nome = sc.nextLine();
                System.out.print("Digite aqui a quantidade do produto alterado: ");
                quantidade = sc.nextInt();
                System.out.print("Digite aqui o preco do produto alterado (separe centavos por vírgula): ");
                preco = sc.nextDouble();
                produtos.get(indice).setNome(nome);
                produtos.get(indice).setQuantidade(quantidade);
                produtos.get(indice).setPreco(preco);
                System.out.println("Nova lista de produtos");
                listarProdutos(produtos);
                break;
            case 3:
                System.out.println("Digite aqui o índice do produto que deseja excluir");
                indice = sc.nextInt();
                sc.nextLine();
                produtos.remove(indice);
                System.out.println("Nova lista de produtos");
                listarProdutos(produtos);
                break;
            case 4:
                System.out.println("Digite aqui o item que você pretende pesquisar: ");
                pesquisa = sc.nextLine();
                System.out.println("Índice, Produto,    Quantidade, Preço");
                for (int i = 0; i < produtos.size(); i++){
                    if (produtos.get(i).nome.toLowerCase().contains(pesquisa.toLowerCase())){
                        listarProduto(i, produtos.get(i));
                    }
                }
                break;
            case 5:
                System.out.println("Digite aqui o produto que você pretende vender: ");
                indice = sc.nextInt();
                System.out.println("Digite aqui a quantidade que você ira vender");
                quantidade = sc.nextInt();

                while (produtos.get(indice).quantidade < quantidade){
                    System.out.println("Só temos " + produtos.get(indice).quantidade + " deste produto.");
                    System.out.println("Digite aqui a quantidade que você ira vender");
                    quantidade = sc.nextInt();
                }

                produtosCompras.add(new Produto(produtos.get(indice).nome, quantidade, produtos.get(indice).preco));
                precoCompras = precoCompras + produtos.get(indice).preco*quantidade;

                System.out.println("Produtos da sua compra");
                listarProdutos(produtosCompras);
                System.out.println("Valor todal da compra: "+precoCompras);

                produtosClone = ClonarProdutos.clonar(produtos);

                venderProduto(indice,produtos,quantidade);
                System.out.println("Deseja compra mais? (0 para sim)");
                comprarMais = sc.nextInt();

                while (comprarMais==0){
                    System.out.println("Digite aqui o produto que você pretende vender: ");
                    indice = sc.nextInt();
                    System.out.println("Digite aqui a quantidade que você ira vender");
                    quantidade = sc.nextInt();

                    while (produtos.get(indice).quantidade < quantidade){
                        System.out.println("Só temos " + produtos.get(indice).quantidade + " deste produto.");
                        System.out.println("Digite aqui a quantidade que você ira vender");
                        quantidade = sc.nextInt();
                    }
                    produtosCompras.add(new Produto(produtos.get(indice).nome, quantidade, produtos.get(indice).preco));
                    precoCompras = precoCompras + produtos.get(indice).preco*quantidade;

                    System.out.println("Produtos da sua compra");
                    listarProdutos(produtosCompras);
                    System.out.println("Valor todal da compra: "+precoCompras);
                    venderProduto(indice,produtos,quantidade);
                    System.out.println("Deseja compra mais? (0 para sim)" );
                    comprarMais = sc.nextInt();
                }


                System.out.println("Deseja Confirmar sua compra? (0 para sim)" );
                comprarMais = sc.nextInt();
                if (comprarMais != 0){
                    System.out.println("Compra desfeita");
                    produtos.removeAll(produtos);
                    produtos = ClonarProdutos.clonar(produtosClone);
                }
                System.out.println("Nova lista de produtos");
                listarProdutos(produtos);
                break;
            default:
                System.out.println("Input errado");
        }


        File myfile = new File("Produtos.txt");
        FileOutputStream fooStream = new FileOutputStream(myfile, false);

        String stoutput = "";

        for (int i =0; i<produtos.size();i++){
            stoutput = stoutput + (produtos.get(i).nome+","+produtos.get(i).quantidade+","+produtos.get(i).preco);
            stoutput = stoutput + "\n";
        }

        byte[] output = stoutput.getBytes();

        fooStream.write(output);
        fooStream.close();

    }

    public static void listarProdutos(List<Produto> produtos){
        System.out.println("Índice, Produto,    Quantidade, Preço");
        for (int i = 0; i < produtos.size(); i++){
            System.out.println(i+ "       "+ Formatar.firstCharactes(produtos.get(i).nome,10)+ "  " + produtos.get(i).quantidade + "          "+ produtos.get(i).preco);
        }
    }

    public static void listarProduto(int i, Produto produto){
        System.out.println(i+ "       "+ Formatar.firstCharactes(produto.nome,10)+ "  " + produto.quantidade + "          "+ produto.preco);
    }

    public static Produto adicionarProdutos(String nome, Integer quantidade, Double preco){
        return new Produto(nome,quantidade,preco);
    }

    public static void venderProduto(int indice, List<Produto> produtos, int quantVenda){
        if (produtos.get(indice).quantidade >= quantVenda){
            produtos.get(indice).setQuantidade(produtos.get(indice).quantidade - quantVenda);
        } else{
            System.out.println("Algo deu errado");
        }
    }

}
