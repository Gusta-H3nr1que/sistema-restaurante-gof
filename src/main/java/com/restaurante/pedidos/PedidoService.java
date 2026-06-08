package com.restaurante.pedidos;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    // CT04: Envia um novo item para a fila da cozinha
    public boolean fazerPedido(ItemPedido item) {
        if (item == null || item.getNome().isEmpty()) {
            return false;
        }
        item.setStatus("Pendente");
        System.out.println("[COZINHA] Novo pedido recebido: " + item.getNome() + " (Status: Pendente)");
        return true;
    }

    // CT07 e CT11: Transfere um item entre comandas se ele não tiver sido entregue
    public boolean transferirItem(ItemPedido item, List<ItemPedido> comandaOrigem, List<ItemPedido> comandaDestino) {
        if (item == null || comandaOrigem == null || comandaDestino == null) {
            return false;
        }

        // Regra de Negócio Crítica: não pode transferir um prato que os clientes já comeram (Entregue)
        if ("Entregue".equalsIgnoreCase(item.getStatus())) {
            System.out.println("[ERRO] Transferência bloqueada: O item '" + item.getNome() + "' já foi entregue!");
            return false;
        }

        if (comandaOrigem.contains(item)) {
            comandaOrigem.remove(item);
            comandaDestino.add(item);
            System.out.println("[SUCESSO] Item '" + item.getNome() + "' transferido com sucesso.");
            return true;
        }

        return false;
    }

    // CT10: Une o consumo de duas listas/mesas e retorna o valor total somado
    public double unirMesas(List<ItemPedido> itensMesaA, List<ItemPedido> itensMesaB) {
        double totalAcumulado = 0.0;

        if (itensMesaA != null) {
            for (ItemPedido item : itensMesaA) {
                totalAcumulado += item.getPreco();
            }
        }

        if (itensMesaB != null) {
            for (ItemPedido item : itensMesaB) {
                totalAcumulado += item.getPreco();
            }
        }

        System.out.println("[BALCÃO] Mesas unidas. Valor total recalculado: R$ " + totalAcumulado);
        return totalAcumulado;
    }
}