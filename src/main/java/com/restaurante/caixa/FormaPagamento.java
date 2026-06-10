package com.restaurante.caixa;

/**
 * Interface que representa uma forma de pagamento no restaurante.
 * Cada forma de pagamento tem sua própria regra de cálculo do valor final.
 *
 * Padrão aplicado: Simple Factory — esta interface é o "produto" criado pela fábrica.
 *
 * @author Integrante 4
 * @version 1.0
 */
public interface FormaPagamento {

    /**
     * Calcula o valor final aplicando as regras da forma de pagamento.
     *
     * @param valorOriginal o valor bruto da conta
     * @return o valor final após desconto ou taxa
     */
    double calcularValorFinal(double valorOriginal);

    /**
     * Retorna o nome da forma de pagamento.
     *
     * @return nome (ex: "Dinheiro", "Cartão", "PIX")
     */
    String getNome();
}