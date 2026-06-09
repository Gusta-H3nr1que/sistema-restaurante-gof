package com.restaurante.pedidos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoServiceTest {

    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        pedidoService = new PedidoService();
    }

    @Test
    void testCT04_FazerPedidoComSucesso() {
        ItemPedido novoPrato = new ItemPedido("Filé a Cavalo", 45.0);

        boolean resultado = pedidoService.fazerPedido(novoPrato);

        assertTrue(resultado, "O pedido válido deveria ser enviado à cozinha com sucesso.");
        assertEquals("Pendente", novoPrato.getStatus(), "O status inicial na cozinha deve ser Pendente.");
    }

    @Test
    void testCT07_TransferirItemComSucesso() {
        ItemPedido pratoAindaNaoPronto = new ItemPedido("Suco de Laranja", 8.0);
        pratoAindaNaoPronto.setStatus("Em Preparo"); // Não foi entregue ainda

        // Usando listas reais puras em vez de Mockito.spy para evitar o erro do Java 25
        List<ItemPedido> comandaMesa01 = new ArrayList<>();
        List<ItemPedido> comandaMesa02 = new ArrayList<>();

        comandaMesa01.add(pratoAindaNaoPronto);

        boolean transferiu = pedidoService.transferirItem(pratoAindaNaoPronto, comandaMesa01, comandaMesa02);

        assertTrue(transferiu, "A transferência de um item 'Em Preparo' deveria ser permitida.");
        assertFalse(comandaMesa01.contains(pratoAindaNaoPronto), "O item deveria sair da comanda de origem.");
        assertTrue(comandaMesa02.contains(pratoAindaNaoPronto), "O item deveria entrar na comanda de destino.");
    }

    @Test
    void testCT10_UnirMesasSomaValoresCorretamente() {
        ItemPedido item1 = new ItemPedido("Hambúrguer", 35.0);
        ItemPedido item2 = new ItemPedido("Refrigerante", 7.0);

        // Listas reais e imutáveis prontas para o cálculo
        List<ItemPedido> listaRealA = List.of(item1);
        List<ItemPedido> listaRealB = List.of(item2);

        double totalSoma = pedidoService.unirMesas(listaRealA, listaRealB);

        assertEquals(42.0, totalSoma, 0.001, "A soma dos valores ao unir as mesas está incorreta.");
    }

    @Test
    void testCT11_ImpedirTransferenciaDeItemEntregue() {
        ItemPedido pratoJaConsumido = new ItemPedido("Picanha", 90.0);
        pratoJaConsumido.setStatus("Entregue"); // Restrição crítica do Guia Oficial

        List<ItemPedido> comandaMesa01 = new ArrayList<>();
        List<ItemPedido> comandaMesa02 = new ArrayList<>();
        comandaMesa01.add(pratoJaConsumido);

        boolean transferiu = pedidoService.transferirItem(pratoJaConsumido, comandaMesa01, comandaMesa02);

        // Garante que o sistema barrou a operação
        assertFalse(transferiu, "O sistema não pode permitir transferir um item com status 'Entregue'.");
        assertTrue(comandaMesa01.contains(pratoJaConsumido), "O item deve continuar na mesa de origem.");
        assertFalse(comandaMesa02.contains(pratoJaConsumido), "O item não pode ter entrado na mesa de destino.");
    }
}