package com.restaurante.caixa;

/**
 * Implementação de pagamento via PIX.
 * Não aplica desconto nem taxa — valor final igual ao original.
 *
 * Regra de negócio: PIX não tem custo adicional para o estabelecimento.
 *
 * @author Integrante 4
 * @version 1.0
 */
public class PagamentoPix implements FormaPagamento {

    /**
     * Retorna o valor original sem nenhum ajuste.
     *
     * @param valorOriginal o valor bruto da conta
     * @return o mesmo valor original sem desconto ou taxa
     */
    @Override
    public double calcularValorFinal(double valorOriginal) {
        return valorOriginal;
    }

    /**
     * Retorna o nome desta forma de pagamento.
     *
     * @return "PIX"
     */
    @Override
    public String getNome() {
        return "PIX";
    }
}