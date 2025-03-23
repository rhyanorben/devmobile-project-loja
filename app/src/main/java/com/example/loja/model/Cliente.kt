package com.example.loja.model

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class Cliente (
    val id: Int,
    val nome: String,
    var saldo: Double
) {

    @Composable
    fun exibirSaldo() {
        Text(text = "$nome. Saldo atual: ${getSaldoFormatado()}.")
    }

    fun getSaldoFormatado(): String {
        return this.saldo.toString().replace(".", ",");
    }

    fun adicionarSaldo(valor: Double) {
        this.saldo += valor
    }

}

@Preview(showBackground = true)
@Composable
fun test_exibirSaldo() {
    val pessoa = Cliente(1, "João", 700.0)

    pessoa.exibirSaldo()
}