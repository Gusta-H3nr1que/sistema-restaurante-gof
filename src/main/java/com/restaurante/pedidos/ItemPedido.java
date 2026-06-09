package com.restaurante.pedidos;

public class ItemPedido {
    private String nome;
    private double preco;
    private String status; // Pendente, Em Preparo, Entregue

    public ItemPedido(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
        this.status = "Pendente"; // Todo pedido começa como Pendente
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }
}