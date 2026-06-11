package com.restaurante.pedido;

/**
 * Implementação do estado intermediário: EM PREPARO.
 * Indica que os pratos ou bebidas deste pedido já estão sendo confeccionados pelos cozinheiros.
 * * @author Marcelo
 */
public class StatusEmPreparo implements StatusPedidoState {

    /**
     * Transiciona o pedido do estado EM PREPARO para o estado PRONTO.
     * Acionado quando a cozinha finaliza a preparação do pedido.
     */
    @Override
    public void proximo(Pedido pedido) {
        pedido.setEstadoAtual(new StatusPronto());
    }

    @Override
    public String getStatusNome() {
        return "EM PREPARO";
    }
}