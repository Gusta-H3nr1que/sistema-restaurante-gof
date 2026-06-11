package com.restaurante.pedido;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de Serviço que gerencia o mapa de controle das comandas ativas em memória.
 * Atua isolando as validações de abertura de mesas e gerenciamento das ordens de serviço.
 * * @author Marcelo
 * @version 1.0
 */
public class PedidoService {
    // Mapa responsável por garantir o controle de comandas indexadas pelo número da mesa
    private Map<Integer, Pedido> pedidosAtivos = new HashMap<>();

    /**
     * Registra e abre um novo pedido ativo no sistema do salão.
     * Contém a validação de restrição mapeada no Caso de Teste CT07.
     * * @param pedido Instância do pedido configurado com a mesa desejada.
     * @throws IllegalArgumentException Se o pedido for nulo ou se a mesa já possuir uma comanda aberta.
     */
    public void criarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }
        // CT07: Impede a duplicação bloqueando abertura em mesas que já possuem consumo ativo
        if (pedidosAtivos.containsKey(pedido.getNumeroMesa())) {
            throw new IllegalArgumentException("Já existe um pedido aberto para a mesa " + pedido.getNumeroMesa());
        }
        pedidosAtivos.put(pedido.getNumeroMesa(), pedido);
    }

    /**
     * Localiza a comanda ativa mapeada para a mesa e dispara a ordem de avanço de estado.
     * * @param numeroMesa Identificador numérico da mesa que terá o status atualizado.
     * @throws IllegalArgumentException Se nenhuma comanda ativa for encontrada para o número informado.
     */
    public void avancarStatusPedido(int numeroMesa) {
        if (!pedidosAtivos.containsKey(numeroMesa)) {
            throw new IllegalArgumentException("Nenhum pedido ativo encontrado para a mesa " + numeroMesa);
        }
        pedidosAtivos.get(numeroMesa).avancarEstado();
    }

    public Pedido buscarPedidoPorMesa(int numeroMesa) {
        return pedidosAtivos.get(numeroMesa);
    }
}