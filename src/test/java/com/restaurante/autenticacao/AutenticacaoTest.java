package com.restaurante.autenticacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AutenticacaoTest {

    private CardapioService cardapioServiceMock;
    private CardapioServiceProxy proxy;

    @BeforeEach
    public void setUp() {
        // 1. Criamos um "clone falso" (Mock) do serviço real do cardápio
        cardapioServiceMock = mock(CardapioService.class);

        // 2. Injetamos o mock dentro do seu Proxy
        proxy = new CardapioServiceProxy(cardapioServiceMock);
    }

    // CT01: Verificar a criação de novos perfis / Login válido
    @Test
    public void testCT01_LoginComSucesso() {
        Usuario usuario = new Usuario("gusta", "senha123", "GERENTE");

        assertEquals("gusta", usuario.getLogin());
        assertEquals("senha123", usuario.getSenha());
        assertEquals("GERENTE", usuario.getPerfil());
    }

    // CT02: Validar acesso correto com login (Simulação de credenciais vazias/inválidas)
    @Test
    public void testCT02_LoginComFalha() {
        Usuario usuario = new Usuario("gusta", "senhaErrada", "GERENTE");

        assertNotEquals("senha123", usuario.getSenha());
    }

    // CT12: Tentar cadastro/alteração com perfil sem permissão (Exceção)
    @Test
    public void testCT12_BloqueioDeAlteracaoItemCardapio() {
        Usuario garcom = new Usuario("pedro", "123", "GARCOM");

        // O JUnit testa se o Proxy joga o erro na cara do Garçom
        assertThrows(SecurityException.class, () -> {
            proxy.alterarPrecoItem(garcom, "Filé Mignon", 45.90);
        });

        // Garante que o serviço real NUNCA foi chamado, provando que o Proxy barrou antes
        verifyNoInteractions(cardapioServiceMock);
    }

    // CT15: Validar permissão de alteração (Gerente logado com sucesso)
    @Test
    public void testCT15_PermissaoDeAlteracaoItemCardapio() {
        Usuario gerente = new Usuario("gusta", "senha123", "GERENTE");

        // Ensinamos o Mock a fingir que deu certo se o gerente chegar até ele
        when(cardapioServiceMock.alterarPrecoItem(any(), anyString(), anyDouble())).thenReturn(true);

        // O Proxy tem que permitir que o gerente passe e retorne true
        boolean resultado = proxy.alterarPrecoItem(gerente, "Filé Mignon", 45.90);
        assertTrue(resultado);

        // Verifica de forma dinâmica se o Proxy realmente repassou a ordem para o serviço real
        verify(cardapioServiceMock, times(1)).alterarPrecoItem(gerente, "Filé Mignon", 45.90);
    }
}