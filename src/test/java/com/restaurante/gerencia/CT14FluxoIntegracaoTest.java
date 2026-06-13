package com.restaurante.gerencia;

import com.restaurante.salao.Mesa;
import com.restaurante.salao.MesaOcupadaState;
import com.restaurante.salao.MesaAguardandoPagamentoState;
import com.restaurante.salao.MesaLivreState;

import com.restaurante.pedido.ItemPedido;
import com.restaurante.pedido.Pedido;
import com.restaurante.pedido.PedidoService;

import com.restaurante.caixa.Caixa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CT14FluxoIntegracaoTest {

    // CT14: Fluxo geral de integração do sistema
    @Test
    public void testCT14_FluxoGeralDeIntegracao() {
        // 1. Cliente ocupa uma mesa
        Mesa mesa = new Mesa();
        mesa.ocupar();

        assertTrue(mesa.getState() instanceof MesaOcupadaState);

        // 2. Pedido é criado para a mesa
        PedidoService pedidoService = new PedidoService();
        Pedido pedido = new Pedido(1);

        pedido.adicionarItem(new ItemPedido("Hambúrguer", 2, 25.00));
        pedido.adicionarItem(new ItemPedido("Refrigerante", 2, 8.00));

        pedidoService.criarPedido(pedido);

        assertEquals(pedido, pedidoService.buscarPedidoPorMesa(1));
        assertEquals(66.00, pedido.calcularTotalPedido());

        // 3. Pedido avança no fluxo da cozinha
        assertEquals("PENDENTE", pedido.getStatus());

        pedidoService.avancarStatusPedido(1);
        assertEquals("EM PREPARO", pedido.getStatus());

        pedidoService.avancarStatusPedido(1);
        assertEquals("PRONTO", pedido.getStatus());

        pedidoService.avancarStatusPedido(1);
        assertEquals("FINALIZADO", pedido.getStatus());

        // 4. Mesa vai para aguardando pagamento
        mesa.liberar();

        assertTrue(mesa.getState() instanceof MesaAguardandoPagamentoState);

        // 5. Conta é fechada no caixa
        Caixa caixa = new Caixa();

        double totalPedido = pedido.calcularTotalPedido();
        double valorFinal = caixa.fecharConta(totalPedido, "PIX");

        assertEquals(66.00, valorFinal);

        // 6. Conta é dividida entre duas pessoas
        double valorPorPessoa = caixa.dividirConta(valorFinal, 2);

        assertEquals(33.00, valorPorPessoa);

        // 7. Após pagamento, mesa é liberada
        mesa.liberar();

        assertTrue(mesa.getState() instanceof MesaLivreState);
    }
}