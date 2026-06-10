package com.restaurante.salao;

public class MesaLivreState implements MesaState {
    @Override
    public void ocupar(Mesa mesa) {
        mesa.setState(new MesaOcupadaState());
    }

    @Override
    public void lancarPedido(Mesa mesa) {
        throw new IllegalStateException("Não é possível lançar pedido em uma mesa livre!");
    }

    @Override
    public void liberar(Mesa mesa) {
        // Já está livre
    }
}