package Classes;

public class AdicionarMusicaComando implements ComandoMusica {
    private PlaylistCommand playlist;
    private Musica musica;

    public AdicionarMusicaComando(PlaylistCommand playlist, Musica musica) {
        this.playlist = playlist;
        this.musica   = musica;
    }

    @Override
    public void executar() {
        playlist.adicionarMusica(musica);
    }

    @Override
    public void cancelar() {
        playlist.removerMusica(musica);
    }
}