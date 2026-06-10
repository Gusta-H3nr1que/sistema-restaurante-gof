package com.restaurante.autenticacao;

/**
 * Classe de Proxy responsável por interceptar ações no cardápio
 * e validar o nível de permissão do usuário.
 *
 * @author Gustavo
 */

public class CardapioServiceProxy {

    /**
     * Altera o preço de um item se o usuário possuir o perfil de GERENTE.
     *
     * @param usuario O usuário que está tentando realizar a ação
     * @param nomeItem O nome do prato/bebida a ser alterado
     * @param novoPreco O novo valor do item
     * @return true se a alteração for realizada com sucesso
     * @throws SecurityException se o usuário for um garçom ou nulo
     */

    private CardapioService service;

    public CardapioServiceProxy(CardapioService service) {
        this.service = service;
    }

    // Método que o Proxy intercepta para validar a segurança
    public boolean alterarPrecoItem(Usuario usuario, String nomeItem, double novoPreco) {

        // Regra do Proxy: Se o usuário não for Gerente, o acesso é negado!
        if (usuario == null || !"GERENTE".equalsIgnoreCase(usuario.getPerfil())) {
            throw new SecurityException("Acesso negado: Apenas gerentes podem alterar o cardápio.");
        }

        // Se passou da validação acima, significa que é GERENTE.
        // Aqui simularia a alteração real salvando no sistema
        System.out.println("Preço do item " + nomeItem + " alterado com sucesso para R$ " + novoPreco);
        return true;
    }
}