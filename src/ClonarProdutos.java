import java.util.ArrayList;
import java.util.List;

public class ClonarProdutos {
    public static List<Produto> clonar (List<Produto> produtos){
        List<Produto> produtosClone = new ArrayList<>();
        for (int i=0;i<produtos.size();i++){
            produtosClone.add(new Produto(produtos.get(i).nome,produtos.get(i).quantidade,produtos.get(i).preco));
        }
        return produtosClone;
    }
}
