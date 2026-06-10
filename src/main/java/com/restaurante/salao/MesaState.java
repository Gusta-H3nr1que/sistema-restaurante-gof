package com.restaurante.salao;

public interface MesaState {
    void ocupar(Mesa mesa);
    void lancarPedido(Mesa mesa);
    void liberar(Mesa mesa);
}
