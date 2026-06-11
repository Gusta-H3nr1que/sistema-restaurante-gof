package com.restaurante.pedido;

/**
 * Interface que define a abstração para os estados de um pedido (Padrão GoF State).
 * Cada classe que implementar esta interface representará um estágio específico
 * do ciclo de vida interno da produção do pedido (PENDENTE, EM PREPARO, PRONTO, FINALIZADO).
 * * @author Marcelo
 * @version 1.0
 */
public interface StatusPedidoState {

    /**
     * Transiciona o pedido para o próximo estado lógico do fluxo de produção.
     * * @param pedido O contexto do pedido que terá o seu estado alterado.
     */
    void proximo(Pedido pedido);

    /**
     * Retorna o nome do estado de forma textual para exibição no sistema e validação de testes.
     * * @return String Nome legível do estado do pedido.
     */
    String getStatusNome();
}