package com.restaurante.pedido;

/**
 * Implementação do estado de serviço: PRONTO.
 * Indica que os itens foram finalizados e estão aguardando o garçom realizar
 * a entrega na mesa correspondente.
 * * @author Marcelo
 */
public class StatusPronto implements StatusPedidoState {

    /**
     * Transiciona o pedido do estado PRONTO para o estado FINALIZADO.
     * Acionado quando o cliente recebe e consome os itens da mesa.
     */
    @Override
    public void proximo(Pedido pedido) {
        pedido.setEstadoAtual(new StatusFinalizado());
    }

    @Override
    public String getStatusNome() {
        return "PRONTO";
    }
}