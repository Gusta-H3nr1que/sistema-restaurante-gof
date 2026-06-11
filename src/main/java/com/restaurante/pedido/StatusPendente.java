package com.restaurante.pedido;

/**
 * Implementação do estado inicial de um pedido: PENDENTE.
 * Indica que a comanda foi aberta pela mesa, mas os itens ainda não começaram
 * a ser confeccionados pela cozinha.
 * * @author Marcelo
 */
public class StatusPendente implements StatusPedidoState {

    /**
     * Transiciona o pedido do estado PENDENTE para o estado EM PREPARO.
     * Acionado quando a cozinha assume a produção dos itens.
     */
    @Override
    public void proximo(Pedido pedido) {
        pedido.setEstadoAtual(new StatusEmPreparo());
    }

    @Override
    public String getStatusNome() {
        return "PENDENTE";
    }
}