package com.restaurante.pedido;

/**
 * Implementação do estado terminal: FINALIZADO.
 * Representa o encerramento do ciclo de vida produtivo do pedido.
 * Como é o último estágio possível, qualquer tentativa de avanço deve ser bloqueada.
 * * @author Marcelo
 */
public class StatusFinalizado implements StatusPedidoState {

    /**
     * Impede qualquer tentativa de avanço a partir do estado terminal.
     * Garante a segurança das regras de negócio mapeadas no CT11.
     * * @throws IllegalStateException Sempre lançada, pois um pedido finalizado não pode avançar.
     */
    @Override
    public void proximo(Pedido pedido) {
        throw new IllegalStateException("O pedido já está finalizado. Não é possível avançar o estado.");
    }

    @Override
    public String getStatusNome() {
        return "FINALIZADO";
    }
}