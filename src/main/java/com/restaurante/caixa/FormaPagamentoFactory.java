package com.restaurante.caixa;

/**
 * Fábrica responsável por criar objetos do tipo FormaPagamento.
 *
 * Padrão de Projeto Aplicado: Simple Factory (Criacional)
 *
 * Problema identificado: o Caixa precisava criar diferentes formas de
 * pagamento com regras distintas. Criar esses objetos diretamente geraria
 * alto acoplamento e dificultaria adicionar novas formas no futuro.
 *
 * Solução proposta: centralizar a criação em uma fábrica. O Caixa passa
 * apenas uma String e recebe o objeto correto pronto para uso.
 *
 * Vantagens: baixo acoplamento, fácil de estender, código mais limpo.
 * Limitações: a fábrica conhece todas as implementações concretas.
 *
 * @author Integrante 4
 * @version 1.0
 */
public class FormaPagamentoFactory {

    /**
     * Cria e retorna a forma de pagamento correspondente ao tipo informado.
     *
     * Tipos aceitos: "DINHEIRO", "CARTAO", "PIX"
     *
     * @param tipo o tipo da forma de pagamento desejada
     * @return instância concreta de FormaPagamento
     * @throws IllegalArgumentException se o tipo não for reconhecido
     */
    public static FormaPagamento criar(String tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de pagamento não pode ser nulo.");
        }

        switch (tipo.toUpperCase().trim()) {
            case "DINHEIRO":
                return new PagamentoDinheiro();
            case "CARTAO":
            case "CARTÃO":
                return new PagamentoCartao();
            case "PIX":
                return new PagamentoPix();
            default:
                throw new IllegalArgumentException("Forma de pagamento desconhecida: " + tipo);
        }
    }
}