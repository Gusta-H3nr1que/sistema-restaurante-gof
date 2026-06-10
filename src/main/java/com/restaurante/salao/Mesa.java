package com.restaurante.salao;

public class Mesa {
    private MesaState state;

    public Mesa() {
        this.state = new MesaLivreState();
    }

    public void setState(MesaState state) {
        this.state = state;
    }

    public MesaState getState() {
        return state;
    }

    public void ocupar() {
        state.ocupar(this);
    }

    // O MÉTODO QUE ESTÁ FALTANDO NO TESTE:
    public void lancarPedido() {
        state.lancarPedido(this);
    }

    public void liberar() {
        state.liberar(this);
    }
}