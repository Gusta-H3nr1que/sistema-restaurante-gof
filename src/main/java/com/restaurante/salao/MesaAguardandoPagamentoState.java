package com.restaurante.salao;

public class MesaAguardandoPagamentoState implements MesaState {
    @Override
    public void ocupar(Mesa mesa) {
        throw new IllegalStateException("A mesa está aguardando pagamento.");
    }

    @Override
    public void lancarPedido(Mesa mesa) {
        throw new IllegalStateException("Não é possível lançar novos pedidos em uma mesa fechando!");
    }

    @Override
    public void liberar(Mesa mesa) {
        mesa.setState(new MesaLivreState());
    }
}
