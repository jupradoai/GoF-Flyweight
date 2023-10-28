#Flyweight
class ProdutoFlyweight:
    def __init__(self, codigo):
        self.codigo = codigo
 
    def exibirDetalhes(self, nome, preco):
        print(f"Produto: {nome}, Preço: R$ {preco:.2f}, Código: {self.codigo}")
 
 #Flyweight Factory
class FabricaFlyweight:
    def __init__(self):
        self.flyweights = {}
 
    def obterFlyweight(self, codigo):
        if codigo not in self.flyweights:
            self.flyweights[codigo] = ProdutoFlyweight(codigo)
        return self.flyweights[codigo]
 
#Cliente 
class Cliente:
    def __init__(self):
        self.fabricaFlyweight = FabricaFlyweight()
        self.carrinho = []
 
    def adicionarProduto(self, codigo, nome, preco):
        flyweight = self.fabricaFlyweight.obterFlyweight(codigo)
        self.carrinho.append({"flyweight": flyweight, "nome": nome, "preco": preco})
 
    def exibirCarrinho(self):
        print("Itens do Carrinho:")
        for item in self.carrinho:
            item["flyweight"].exibirDetalhes(item["nome"], item["preco"])
 
# USO DO CLIENTE
cliente = Cliente()
cliente.adicionarProduto("001", "Camiseta", 39.90)
cliente.adicionarProduto("002", "Calça Jeans", 89.90)
cliente.adicionarProduto("001", "Camiseta", 39.90)
cliente.exibirCarrinho()