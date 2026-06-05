package Classes;

public class RemoverMusicaComando implements ComandoMusica {
    private PlaylistCommand playlist;
    private Musica musica;

    public RemoverMusicaComando(PlaylistCommand playlist, Musica musica) {
        this.playlist = playlist;
        this.musica   = musica;
    }

    @Override
    public void executar() {
        playlist.removerMusica(musica);
    }

    @Override
    public void cancelar() {
        playlist.adicionarMusica(musica);
    }
}