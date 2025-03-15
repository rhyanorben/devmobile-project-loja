package com.example.loja.model

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class CarrinhoCompras {

    val itens = mutableMapOf<Produto, Int>()

    fun adicionarProduto(produto: Produto, quantidade: Int) {
        if (quantidade > 0) {
            itens[produto] = (itens[produto] ?: 0) + quantidade
        } else {
            throw Exception("Quantidade do produto deve ser maior do que zero!")
        }
    }

    fun removerProduto(produto: Produto) {
        if (itens.containsKey(produto)) {
            itens.remove(produto)
        } else {
            throw Exception("Produto não está no carrinho!")
        }
    }

    fun calcularTotal(): Double {
        return itens.entries.sumOf{ (produto, quantidade) -> produto.preco * quantidade }
    }

    fun mostrarCarrinho(): String {
        if (itens.isEmpty()) {
            return "O carrinho está vazio."
        }

        val builder = StringBuilder("Carrinho de compras:\n")
        for ((produto, quantidade) in itens) {
            builder.append("${produto.nome} - $quantidade. R$ ${produto.getPrecoFormatado()}.\n")
        }
        builder.append("Total: R$ ${calcularTotal()}")

        return builder.toString();
    }

    fun limparCarrinho() {
        itens.clear()
    }
}

@Preview(showBackground = true)
@Composable
fun mostrarCarrinho() {
    val p1 = Produto(1, "NomeProd", 500.00, 1)
    val p2 = Produto(2, "Segundo", 200.00, 5)
    val carrinho = CarrinhoCompras()
    carrinho.adicionarProduto(p1, 1)
    carrinho.adicionarProduto(p2, 5)

    Text(text = carrinho.mostrarCarrinho())
}