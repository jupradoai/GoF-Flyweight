import java.util.HashMap;
import java.util.Map;

// Flyweight Factory
class FabricaFlyweight {
    private Map<String, ProdutoFlyweight> flyweights;

    public FabricaFlyweight(){
        this.flyweights = new HashMap<>();
    }

    public ProdutoFlyweight obterFlyweight(String codigo){
        if (!flyweights.containsKey(codigo)){
            flyweights.put(codigo, new ProdutoFlyweight(codigo));
        }
        return flyweights.get(codigo);
    }
}

// Flyweight
class ProdutoFlyweight{
    private String codigo;

    public ProdutoFlyweight(String codigo){
        this.codigo = codigo;
    }

    public void exibirDetalhes(String nome, double preco){
        System.out.printf("Produto: %s, Preço: R$%.2f, Código: %s%n", nome, preco, codigo);
    }
}

// Cliente
class Cliente{
    private FabricaFlyweight fabricaFlyweight;
    private ItemCarrinho[] carrinho;
    private int itemCount;

    public Cliente(){
        fabricaFlyweight = new FabricaFlyweight();
        carrinho = new ItemCarrinho[10];                // Inicializado com um tamanho
        itemCount = 0;
    }

    public void adicionarProduto(String codigo, String nome, double preco){
        ProdutoFlyweight flyweight = fabricaFlyweight.obterFlyweight(codigo);
        carrinho[itemCount] = new ItemCarrinho(flyweight, nome, preco);
        itemCount++;
    }

    public void exibirCarrinho(){
        System.out.println("Itens do Carrinho");
        for (int i = 0; i < itemCount; i++){
            carrinho[i].getFlyweight().exibirDetalhes(carrinho[i].getNome(), carrinho[i].getPreco());
        }
    }
}

class ItemCarrinho{
    private ProdutoFlyweight flyweight;
    private String nome;
    private double preco;

    public ItemCarrinho(ProdutoFlyweight flyweight, String nome, double preco){
        this.flyweight = flyweight;
        this.nome = nome;
        this.preco = preco;
    }
        
    public ProdutoFlyweight getFlyweight(){
        return flyweight;
    }

    public String getNome(){
        return nome;
    }
        
    public double getPreco(){
        return preco;
    }
}

// USO CLIENTE ####
public class Flyweight01 {
    public static void main(String[] args){
        Cliente cliente = new Cliente();
        cliente.adicionarProduto("001", "Camiseta", 39.90);
        cliente.adicionarProduto("002", "Calça Jeans", 89.90);
        cliente.adicionarProduto("001", "Camiseta", 39.90);             // Reutilizando o flyweight existente
        cliente.exibirCarrinho();
    }
}
