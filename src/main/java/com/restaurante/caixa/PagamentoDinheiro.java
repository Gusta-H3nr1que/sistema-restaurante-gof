package com.restaurante.caixa;

/**
 * Implementação de pagamento em dinheiro.
 * Aplica desconto de 5% sobre o valor original da conta.
 *
 * Regra de negócio (CT05): pagamentos em dinheiro recebem 5% de desconto.
 *
 * @author Integrante 4
 * @version 1.0
 */
public class PagamentoDinheiro implements FormaPagamento {

    /** Percentual de desconto aplicado em pagamentos em dinheiro. */
    private static final double DESCONTO = 0.05;

    /**
     * Calcula o valor final aplicando 5% de desconto.
     *
     * @param valorOriginal o valor bruto da conta
     * @return valor com 5% de desconto aplicado
     */
    @Override
    public double calcularValorFinal(double valorOriginal) {
        return valorOriginal * (1 - DESCONTO);
    }

    /**
     * Retorna o nome desta forma de pagamento.
     *
     * @return "Dinheiro"
     */
    @Override
    public String getNome() {
        return "Dinheiro";
    }
}