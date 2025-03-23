package com.example.loja.model

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

class Loja (
    private val produtos: List<Produto>
) {
    @Composable
    fun listarProdutos() {
        if (produtos.isEmpty()) {
            Text("Nenhum produto disponível")
            return
        }

        val builder = StringBuilder("Produtos Disponíveis:\n");
        for (produto in produtos) {
            builder.append("${produto.nome} - ${produto.getPrecoFormatado()}.\n")
        }
        return Text(builder.toString())
    }

    @Composable
    fun finalizarCompra(cliente: Cliente, carrinho: CarrinhoCompras) {
        validarCarrinho(carrinho)
        validarSaldoCliente(cliente, carrinho.calcularTotal())

        diminuirSaldoEstoque(carrinho.itens)
        diminuirSaldoCliente(cliente, carrinho.calcularTotal())
        Text("Compra finalizada, muito obrigado cliente ${cliente.nome}. Total: R$ ${carrinho.calcularTotal()}")
        carrinho.limparCarrinho()
    }

    fun diminuirSaldoCliente(cliente: Cliente, total: Double) {
        cliente.saldo -= total
    }

    fun validarCarrinho(carrinho: CarrinhoCompras) {
        if (carrinho.itens.isEmpty()) {
            throw Exception("Seu carrinho está vazio!")
            return
        }

        for ((produto, quantidade) in carrinho.itens) {
            if (produto.estoque < quantidade) {
                throw Exception("${produto.nome} não possui estoque suficiente!")
                return
            }
        }
    }

    fun validarSaldoCliente(cliente: Cliente, total: Double) {
        if (cliente.saldo < total) {
            throw Exception("Saldo do ${cliente.nome} inferior ao total da compra")
            return
        }
    }

    fun diminuirSaldoEstoque(itens: MutableMap<Produto, Int>) {
        for ((produto, quantidade) in itens) {
            produto.estoque -= quantidade
        }
    }
}