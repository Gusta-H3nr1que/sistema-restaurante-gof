package com.restaurante.caixa;

/**
 * Serviço responsável pelo fechamento de contas no restaurante.
 *
 * Utiliza a FormaPagamentoFactory para obter a forma de pagamento correta
 * e aplica as regras de fechamento, divisão e validação da conta.
 *
 * @author Integrante 4
 * @version 1.0
 */
public class Caixa {

    /**
     * Fecha a conta aplicando a forma de pagamento informada.
     *
     * @param valorTotal    o valor bruto total da conta
     * @param tipoPagamento o tipo de pagamento ("DINHEIRO", "CARTAO", "PIX")
     * @return valor final após aplicar as regras da forma de pagamento
     * @throws IllegalArgumentException se o valor for zero ou negativo
     */
    public double fecharConta(double valorTotal, String tipoPagamento) {
        if (valorTotal <= 0) {
            throw new IllegalArgumentException(
                    "Não é possível fechar uma conta com valor zerado ou negativo.");
        }
        FormaPagamento pagamento = FormaPagamentoFactory.criar(tipoPagamento);
        return pagamento.calcularValorFinal(valorTotal);
    }

    /**
     * Divide o valor total igualmente entre o número de pessoas informado.
     *
     * @param valorTotal    o valor total da conta
     * @param numeroPessoas quantidade de pessoas que dividem a conta
     * @return valor individual de cada pessoa
     * @throws IllegalArgumentException se o número de pessoas for zero ou negativo
     */
    public double dividirConta(double valorTotal, int numeroPessoas) {
        if (numeroPessoas <= 0) {
            throw new IllegalArgumentException(
                    "O número de pessoas deve ser maior que zero.");
        }
        return valorTotal / numeroPessoas;
    }
}