package Classes;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<ComandoMusica> comandos = new ArrayList<>();

    public void executarComando(ComandoMusica comando) {
        comando.executar();
        comandos.add(comando);
    }

    public void desfazerUltimoComando() {
        if (!comandos.isEmpty()) {
            ComandoMusica ultimo = comandos.remove(comandos.size() - 1);
            ultimo.cancelar();
        } else {
            System.out.println("Nenhum comando para desfazer.");
        }
    }

    public int getTotalComandos() { return comandos.size(); }
}