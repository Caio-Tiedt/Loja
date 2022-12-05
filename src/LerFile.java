import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LerFile {
    public static List<Produto> lerProduto(String path){
        List<Produto> produtos = new ArrayList<>();

        File file = new File(path);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String st;
        String nome;
        int quantidade;
        double preco;

        String[] parsed;

        while (true){
            try {
                if ((st = br.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            parsed = st.split(",");
            nome = parsed[0];
            quantidade = Integer.parseInt(parsed[1]);
            preco = Double.parseDouble(parsed[2]);
            produtos.add(new Produto(nome,quantidade,preco));
        }

        return produtos;
    }
}
