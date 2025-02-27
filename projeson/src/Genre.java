import java.util.ArrayList;
import java.util.List;

public class Genre {
    private String genreName;
    private List<Song> songs;

    public Genre(String genreName) {
        this.genreName = genreName;
        this.songs = new ArrayList<>();
    }

    public String getGenreName() {
        return genreName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }
}
