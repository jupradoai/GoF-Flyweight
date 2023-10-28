// Flyweight Factory
class FabricaFlyweight{
    constructor() {
        this.flyweights = {};
    }

    obterFlyweight(codigo){
        if (!this.flyweights[codigo]){
            this.flyweights[codigo] = new ProdutoFlyweight(codigo);
        }
        return this.flyweights[codigo];
    }
}

// Flyweight
class ProdutoFlyweight{
    constructor(codigo){
        this.codigo = codigo;
    }

    exibirDetalhes(nome, preco){
        console.log(`Produto: ${nome}, Preço: R$ ${preco.toFixed(2)}, Código: ${this.codigo}`);
    }
}

// Cliente 
class Cliente{
    constructor(){
        this.fabricaFlyweight = new FabricaFlyweight();
        this.carrinho = [];
    }

    adicionarProduto(codigo, nome, preco){
        const flyweight = this.fabricaFlyweight.obterFlyweight(codigo);
        this.carrinho.push({flyweight, nome, preco});
    }

    exibirCarrinho(){
        console.log("Itens do Carrinho:");
        this.carrinho.forEach(item => {
            item.flyweight.exibirDetalhes(item.nome, item.preco);
        });
    }
}

// USO DO CLIENTE ###################
const cliente = new Cliente();
cliente.adicionarProduto("001", "Camiseta", 39.90);
cliente.adicionarProduto("002", "Calça Jeans", 89.90);
cliente.adicionarProduto("001", "Camiseta", 39.90);         // Reutilizando o flyweight existente
cliente.exibirCarrinho();
