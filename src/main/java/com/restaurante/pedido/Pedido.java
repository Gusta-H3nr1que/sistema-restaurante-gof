package com.restaurante.pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de Contexto do padrão State que gerencia o consumo e ciclo de vida de uma comanda.
 * Delega as regras de mudança de status para o objeto polimórfico de estado ativo (StatusPedidoState).
 * * @author Marcelo
 * @version 1.0
 */
public class Pedido {
    private int numeroMesa;
    private List<ItemPedido> itens;
    private StatusPedidoState estadoAtual;

    /**
     * Construtor que inicializa a comanda associada a uma mesa do restaurante.
     * Por padrão de fluxo (CT04), todo pedido nasce com o status "PENDENTE".
     * * @param numeroMesa Identificador numérico da mesa correspondente.
     */
    public Pedido(int numeroMesa) {
        this.numeroMesa = numeroMesa;
        this.itens = new ArrayList<>();
        this.estadoAtual = new StatusPendente();
    }

    /**
     * Adiciona um novo item de consumo (produto, quantidade e preço) à comanda da mesa.
     * * @param item Instância de ItemPedido.
     */
    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
    }

    /**
     * Calculates the overall financial value by summing up the subtotal of all registered items.
     * * @return double Accumulated total order value.
     */
    public double calcularTotalPedido() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    /**
     * Avança o status do pedido delegando a execução para a instância do estado ativo.
     * Aplicação pura do Padrão GoF State para cobrir as transições do CT10 e CT11.
     */
    public void avancarEstado() {
        this.estadoAtual.proximo(this);
    }

    public String getStatus() {
        return this.estadoAtual.getStatusNome();
    }

    /**
     * Altera internamente a instância do estado polimórfico do pedido.
     * Possui visibilidade protected para permitir alterações apenas pelas classes do mesmo pacote.
     * * @param novoEstado Nova instância derivada de StatusPedidoState.
     */
    protected void setEstadoAtual(StatusPedidoState novoEstado) {
        this.estadoAtual = novoEstado;
    }

    public int getNumeroMesa() { return numeroMesa; }
    public List<ItemPedido> getItens() { return itens; }
}