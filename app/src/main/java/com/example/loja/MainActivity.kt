package com.example.loja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.loja.model.CarrinhoCompras
import com.example.loja.model.Cliente
import com.example.loja.model.Loja
import com.example.loja.model.Produto
import com.example.loja.ui.theme.LojaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            minhaComposable()
        }
    }

    @Composable
    fun minhaComposable(){
        val notebook = Produto(1, "Notebook", 2599.99, 3)
        val fone = Produto(2, "Fone de Ouvido", 200.00, 1)

        val loja = Loja(listOf(notebook, fone))

        val joao = Cliente(1, "João", 2900.00)
        val maria = Cliente(2, "Maria", 250.00)

        val carrinho1 = CarrinhoCompras()
        val carrinho2 = CarrinhoCompras()
        val carrinho3 = CarrinhoCompras()

        Column {
            loja.listarProdutos()

            joao.adicionarSaldo(100.0)

            carrinho1.adicionarProduto(notebook, 1)
            carrinho1.adicionarProduto(fone, 1)

            carrinho2.adicionarProduto(fone, 5)

            carrinho3.adicionarProduto(notebook, 1)
            carrinho3.adicionarProduto(fone, 1)

            loja.finalizarCompra(maria, carrinho1)
            loja.finalizarCompra(joao, carrinho2)

            loja.finalizarCompra(joao, carrinho3)

            loja.listarProdutos()

            joao.exibirSaldo()
        }
    }
}