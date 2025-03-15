package com.example.loja.model

class Loja (
    private val produtos: List<Produto>
) {
    fun listarProdutos(): String {
        if(produtos.isEmpty()){
           return "Nenhum produto disponível"
        }
        val builder = StringBuilder("Produtos Disponíveis:\n");
        for(produto in produtos) {
            builder.append("${produto.nome} - ${produto.getPrecoFormatado()}.\n")
        }

        fun finalizarCompra(cliente: Cliente, carrinho: CarrinhoCompras) {
            validarCarrinho(carrinho)
            validarSaldoCliente(cliente, carrinho.calcularTotal())

            diminuirSaldoEstoque(carrinho.itens)
            diminuirSaldoCliente(cliente, carrinho.calcularTotal())
            println("Compra finalizada, muito obrigado cliente ${cliente.nome}. Total: R$ ${carrinho.calcularTotal()}\n\n")
            carrinho.limparCarrinho();

        }

        fun diminuirSaldoCliente(cliente: Cliente, total: Double) {
            cliente.saldo -= total
        }

        fun validarCarrinho(carrinho: CarrinhoCompras) {
            if(carrinho.itens.isEmpty()) {
                throw Exception("Seu carrinho está vazio!")
                return
            }

            for((produto, quantidade) in carrinho.itens) {
                if (produto.estoque < quantidade) {
                    throw Exception("${produto.nome} não possui estoque suficiente!")
                    return
                }
            }
        }

        fun validarSaldoCliente(cliente: Cliente, total: Double) {
            if(cliente.saldo < total) {
                throw Exception("Saldo do ${cliente.nome} inferior ao total da compra")
                return
            }
        }

        fun diminuirSaldoEstoque(itens: MutableMap<Produto, Int>) {
            for((produto, quantidade) in itens) {
                produto.estoque -= quantidade
            }
        }
    }
}