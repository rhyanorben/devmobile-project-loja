package com.example.loja.model

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class Produto (
    val id: Int,
    val nome: String,
    var preco: Double,
    var estoque: Int
) {

    @Composable
    fun exibirDetalhes() {
        Text(
            text = "$id - $nome | Preço: R$ ${getPrecoFormatado()}. Estoque: $estoque."
        )
    }

    fun getPrecoFormatado(): String {
        return this.preco.toString().replace(".", ",");
    }

}

@Preview(showBackground = true)
@Composable
fun test_exibirDetalhes() {
    val p = Produto(1, "Mouse", 748.80, 10)

    p.exibirDetalhes()
}