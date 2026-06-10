package com.restaurante.salao;

public class MesaOcupadaState implements MesaState {
    @Override
    public void ocupar(Mesa mesa) {
        throw new IllegalStateException("A mesa já está ocupada!");
    }

    @Override
    public void lancarPedido(Mesa mesa) {
        // Lógica para registrar pedido na mesa
        System.out.println("Pedido lançado com sucesso na mesa ocupada.");
    }

    @Override
    public void liberar(Mesa mesa) {
        mesa.setState(new MesaAguardandoPagamentoState());
    }
}
