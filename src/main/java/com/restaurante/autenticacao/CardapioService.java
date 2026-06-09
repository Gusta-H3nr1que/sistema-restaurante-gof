package com.restaurante.autenticacao;

/**
 * Serviço real responsável pelas regras de negócio e manipulação do Cardápio.
 * Esta é a classe real protegida pelo Proxy.
 *
 * @author Gustavo
 */
public class CardapioService {

    /**
     * Executa a alteração real do preço do item no sistema.
     *
     * @param usuario O usuário que requisitou a ação
     * @param nomeItem O nome do prato/bebida a ser alterado
     * @param novoPreco O novo valor do item
     * @return true se a operação for realizada com sucesso
     */
    public boolean alterarPrecoItem(Usuario usuario, String nomeItem, double novoPreco) {
        // Aqui seria a lógica real do core business do restaurante
        System.out.println("[CORE] Sucesso: Preço do item '" + nomeItem + "' alterado para R$ " + novoPreco);
        return true;
    }
}