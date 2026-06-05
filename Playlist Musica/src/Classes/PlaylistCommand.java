package Classes;

import java.util.ArrayList;
import java.util.List;

public class PlaylistCommand {
    private String nome;
    private List<Musica> musicas = new ArrayList<>();

    public PlaylistCommand(String nome) {
        this.nome = nome;
    }

    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
        System.out.println("Adicionada: " + musica.getTitulo()
                + " — " + musica.getArtista());
    }

    public void removerMusica(Musica musica) {
        musicas.remove(musica);
        System.out.println("Removida: " + musica.getTitulo()
                + " — " + musica.getArtista());
    }

    public String getNome()            { return nome; }
    public List<Musica> getMusicas()   { return musicas; }
    public int getTotalMusicas()       { return musicas.size(); }
}