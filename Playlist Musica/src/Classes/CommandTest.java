package Classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    private PlaylistCommand playlist;
    private Player player;
    private Musica m1, m2, m3;

    @BeforeEach
    void setUp() {
        playlist = new PlaylistCommand("Minha Playlist");
        player   = new Player();
        m1 = new Musica("In the End", "Linkin Park");
        m2 = new Musica("Numb",  "Linkin Park");
        m3 = new Musica("What I've Done","Linkin Park");
    }

    @Test
    void adicionarMusicaDeveAumentarTotalDaPlaylist() {
        player.executarComando(new AdicionarMusicaComando(playlist, m1));
        assertEquals(1, playlist.getTotalMusicas());
    }

    @Test
    void adicionarTresMusicasDeveResultarEmTotalTres() {
        player.executarComando(new AdicionarMusicaComando(playlist, m1));
        player.executarComando(new AdicionarMusicaComando(playlist, m2));
        player.executarComando(new AdicionarMusicaComando(playlist, m3));
        assertEquals(3, playlist.getTotalMusicas());
    }

    @Test
    void desfazerAdicionarDeveRemoverMusicaDaPlaylist() {
        player.executarComando(new AdicionarMusicaComando(playlist, m1));
        player.executarComando(new AdicionarMusicaComando(playlist, m2));
        player.desfazerUltimoComando();
        assertEquals(1, playlist.getTotalMusicas());
        assertFalse(playlist.getMusicas().contains(m2));
    }

    @Test
    void removerMusicaDeveReduzirTotalDaPlaylist() {
        player.executarComando(new AdicionarMusicaComando(playlist, m1));
        player.executarComando(new AdicionarMusicaComando(playlist, m2));
        player.executarComando(new RemoverMusicaComando(playlist, m1));
        assertEquals(1, playlist.getTotalMusicas());
    }

    @Test
    void desfazerRemoverDeveRestaurarMusicaNaPlaylist() {
        player.executarComando(new AdicionarMusicaComando(playlist, m1));
        player.executarComando(new RemoverMusicaComando(playlist, m1));
        player.desfazerUltimoComando();
        assertEquals(1, playlist.getTotalMusicas());
        assertTrue(playlist.getMusicas().contains(m1));
    }

    @Test
    void desfazerSemComandosNaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> player.desfazerUltimoComando());
    }

    @Test
    void executarComandoDeveRegistrarNoHistorico() {
        player.executarComando(new AdicionarMusicaComando(playlist, m1));
        player.executarComando(new AdicionarMusicaComando(playlist, m2));
        assertEquals(2, player.getTotalComandos());
    }

    @Test
    void desfazerDeveRemoverComandoDoHistorico() {
        player.executarComando(new AdicionarMusicaComando(playlist, m1));
        player.executarComando(new AdicionarMusicaComando(playlist, m2));
        player.desfazerUltimoComando();
        assertEquals(1, player.getTotalComandos());
    }

    @Test
    void desfazerMultiplasVezesDeveRestaurarEstadoInicial() {
        player.executarComando(new AdicionarMusicaComando(playlist, m1));
        player.executarComando(new AdicionarMusicaComando(playlist, m2));
        player.executarComando(new AdicionarMusicaComando(playlist, m3));
        player.desfazerUltimoComando();
        player.desfazerUltimoComando();
        player.desfazerUltimoComando();
        assertEquals(0, playlist.getTotalMusicas());
        assertEquals(0, player.getTotalComandos());
    }
}