package com.restaurante.caixa;

/**
 * Implementação de pagamento em cartão de crédito ou débito.
 * Aplica taxa de 3% sobre o valor original (taxa da maquininha).
 *
 * Regra de negócio (CT06): pagamentos em cartão incluem 3% de taxa.
 *
 * @author Integrante 4
 * @version 1.0
 */
public class PagamentoCartao implements FormaPagamento {

    /** Percentual de taxa cobrada em pagamentos por cartão. */
    private static final double TAXA = 0.03;

    /**
     * Calcula o valor final aplicando 3% de taxa.
     *
     * @param valorOriginal o valor bruto da conta
     * @return valor com 3% de taxa incluída
     */
    @Override
    public double calcularValorFinal(double valorOriginal) {
        return valorOriginal * (1 + TAXA);
    }

    /**
     * Retorna o nome desta forma de pagamento.
     *
     * @return "Cartão"
     */
    @Override
    public String getNome() {
        return "Cartão";
    }
}