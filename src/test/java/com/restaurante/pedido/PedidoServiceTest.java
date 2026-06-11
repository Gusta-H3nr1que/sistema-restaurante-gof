package com.restaurante.pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários focados estritamente nos Casos de Teste do Aluno:
 * CT04, CT07, CT10 e CT11 de forma compatível com Java 25.
 * * @author Marcelo
 * @version 4.0
 */
public class PedidoServiceTest {

    private PedidoService pedidoService;
    private Pedido pedidoMesa5;

    @BeforeEach
    public void setUp() {
        pedidoService = new PedidoService();
        pedidoMesa5 = new Pedido(5);
        pedidoMesa5.adicionarItem(new ItemPedido("Hambúrguer", 2, 35.0));
    }

    /**
     * CT04: Validar lançamento simples de item e status inicial PENDENTE.
     */
    @Test
    public void testCT04_CriarPedidoComSucesso() {
        pedidoService.criarPedido(pedidoMesa5);
        Pedido salvo = pedidoService.buscarPedidoPorMesa(5);

        assertNotNull(salvo);
        assertEquals("PENDENTE", salvo.getStatus(), "O status inicial deve ser PENDENTE.");
        assertEquals(70.0, salvo.calcularTotalPedido());
    }

    /**
     * CT07: Validar que o sistema impede a criação de pedido para mesa já ocupada.
     */
    @Test
    public void testCT07_ErroAoCriarPedidoParaMesaComPedidoAtivo() {
        pedidoService.criarPedido(pedidoMesa5);
        Pedido duplicated = new Pedido(5);

        assertThrows(IllegalArgumentException.class, () -> {
            pedidoService.criarPedido(duplicated);
        }, "Deveria barrar a duplicação de pedido para a mesma mesa.");
    }

    /**
     * CT10: Validar o fluxo completo de transição de estados do Pedido (State).
     */
    @Test
    public void testCT10_FluxoDeTransicaoDeEstadosSucesso() {
        pedidoService.criarPedido(pedidoMesa5);
        assertEquals("PENDENTE", pedidoMesa5.getStatus());

        // PENDENTE -> EM PREPARO
        pedidoService.avancarStatusPedido(5);
        assertEquals("EM PREPARO", pedidoMesa5.getStatus());

        // EM PREPARO -> PRONTO
        pedidoService.avancarStatusPedido(5);
        assertEquals("PRONTO", pedidoMesa5.getStatus());

        // PRONTO -> FINALIZADO
        pedidoService.avancarStatusPedido(5);
        assertEquals("FINALIZADO", pedidoMesa5.getStatus());
    }

    /**
     * CT11: Testar barramento de avanço de estado de pedido já finalizado.
     */
    @Test
    public void testCT11_ErroAoAvancarEstadoDePedidoJaFinalizado() {
        pedidoService.criarPedido(pedidoMesa5);

        // Leva o pedido até o final do fluxo
        pedidoService.avancarStatusPedido(5); // EM PREPARO
        pedidoService.avancarStatusPedido(5); // PRONTO
        pedidoService.avancarStatusPedido(5); // FINALIZADO

        assertThrows(IllegalStateException.class, () -> {
            pedidoService.avancarStatusPedido(5);
        }, "Não deve permitir avançar o status após o pedido ser FINALIZADO.");
    }
}