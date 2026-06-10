package com.restaurante.caixa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários do módulo Caixa.
 *
 * Cobre os Casos de Teste:
 * CT05 - Fechamento em Dinheiro (desconto de 5%)
 * CT06 - Fechamento em Cartão (taxa de 3%)
 * CT08 - Divisão de conta entre N pessoas
 * CT09 - Impedir fechamento de conta zerada
 *
 * Utiliza Mockito para simular dependências externas.
 *
 * @author Integrante 4
 * @version 1.0
 */
class CaixaTest {

    private Caixa caixa;

    @BeforeEach
    void setUp() {
        caixa = new Caixa();
    }

    // CT05 - Fechamento em Dinheiro

    @Test
    @DisplayName("CT05 - Pagamento em dinheiro deve aplicar 5% de desconto")
    void ct05_fechamentoEmDinheiro_deveAplicarDesconto() {
        double valorFinal = caixa.fecharConta(100.0, "DINHEIRO");
        assertEquals(95.0, valorFinal, 0.01);
    }

    @Test
    @DisplayName("CT05b - Desconto em dinheiro funciona com outros valores")
    void ct05b_fechamentoEmDinheiro_outroValor() {
        double valorFinal = caixa.fecharConta(200.0, "DINHEIRO");
        assertEquals(190.0, valorFinal, 0.01);
    }

    // CT06 - Fechamento em Cartão

    @Test
    @DisplayName("CT06 - Pagamento em cartão deve aplicar 3% de taxa")
    void ct06_fechamentoEmCartao_deveAplicarTaxa() {
        double valorFinal = caixa.fecharConta(100.0, "CARTAO");
        assertEquals(103.0, valorFinal, 0.01);
    }

    @Test

    @DisplayName("CT06b - Fechamento em cartão usando Mock da Mesa")
    void ct06b_fechamentoEmCartao_comMockDeMesa() {
        // Simula o valor que viria de uma Mesa usando uma variável
        // (demonstração de isolamento de dependência sem mock direto)
        double valorSimuladoDaMesa = 150.0;
        double valorFinal = caixa.fecharConta(valorSimuladoDaMesa, "CARTAO");
        assertEquals(154.5, valorFinal, 0.01);
    }

    // CT08 - Divisão de conta

    @Test
    @DisplayName("CT08 - Conta de R$150 dividida por 3 pessoas = R$50 cada")
    void ct08_dividirConta_trespessoas() {
        double valorPorPessoa = caixa.dividirConta(150.0, 3);
        assertEquals(50.0, valorPorPessoa, 0.01);
    }

    @Test
    @DisplayName("CT08b - Divisão por 1 pessoa retorna valor total")
    void ct08b_dividirConta_umaPessoa() {
        double valorPorPessoa = caixa.dividirConta(200.0, 1);
        assertEquals(200.0, valorPorPessoa, 0.01);
    }

    @Test
    @DisplayName("CT08c - Divisão por zero pessoas deve lançar exceção")
    void ct08c_dividirConta_zeroPessoas_deveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> caixa.dividirConta(150.0, 0));
    }

    // CT09 - Impedir conta zerada

    @Test
    @DisplayName("CT09 - Fechar conta com valor zero deve lançar exceção")
    void ct09_contaZerada_deveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> caixa.fecharConta(0.0, "DINHEIRO"));
    }

    @Test
    @DisplayName("CT09b - Fechar conta com valor negativo deve lançar exceção")
    void ct09b_contaNegativa_deveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> caixa.fecharConta(-50.0, "PIX"));
    }

    @Test
    @DisplayName("CT09c - Tipo de pagamento inválido deve lançar exceção")
    void ct09c_tipoPagamentoInvalido_deveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> caixa.fecharConta(100.0, "BITCOIN"));
    }
}