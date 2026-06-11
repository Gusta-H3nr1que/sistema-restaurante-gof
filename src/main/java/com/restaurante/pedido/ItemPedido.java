package com.restaurante.pedido;

/**
 * Representa um item de consumo específico adicionado ao pedido de uma mesa.
 * Esta classe é responsável por armazenar os dados individuais do produto,
 * como o nome, a quantidade solicitada e o preço por unidade, além de
 * calcular o seu próprio subtotal.
 * * @author Marcelo
 * @version 1.0
 */
public class ItemPedido {
    private String nomeProduto;
    private int quantidade;
    private double precoUnitario;

    /**
     * Construtor completo para inicializar um item de consumo no pedido.
     * * @param nomeProduto Nome descritivo do produto (ex: "Hambúrguer", "Refrigerante").
     * @param quantidade Quantidade total deste item solicitada pelos clientes da mesa.
     * @param precoUnitario Preço de uma única unidade do produto.
     */
    public ItemPedido(String nomeProduto, int quantidade, double precoUnitario) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    /**
     * Calcula o valor subtotal deste item multiplicando a quantidade pedida
     * pelo seu preço unitário. É a operação base para o cálculo do total geral da comanda.
     * * @return double Valor total acumulado deste item específico.
     */
    public double calcularSubtotal() {
        return this.quantidade * this.precoUnitario;
    }

    public String getNomeProduto() { return nomeProduto; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }
}