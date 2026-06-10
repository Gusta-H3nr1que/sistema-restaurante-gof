package com.restaurante.salao;

import com.restaurante.salao.Mesa;
import com.restaurante.salao.MesaOcupadaState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalaoTest {
    private Mesa mesa;

    @BeforeEach
    public void setup() {
        // Inicializa uma nova mesa antes de cada teste para garantir isolamento
        mesa = new Mesa();
    }

    @Test
    public void testCT03_MudancaDeStatusParaOcupada() {
        // Valida se a mesa, ao ser ocupada, muda seu estado corretamente
        mesa.ocupar();
        assertTrue(mesa.getState() instanceof MesaOcupadaState, "A mesa deveria estar no estado Ocupada.");
    }

    @Test
    public void testCT13_ImpedirPedidoEmMesaVaga() {
        // Valida se o sistema bloqueia pedidos em mesas livres (lançando exceção)
        assertThrows(IllegalStateException.class, () -> {
            mesa.lancarPedido();
        }, "Deveria lançar IllegalStateException ao tentar pedir em mesa livre.");
    }
}
