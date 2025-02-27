import java.util.ArrayList;
import java.util.List;

public class UserPlaylist {
    private String name;            // Playlist adı
    private List<Song> songs;       // Playlist içindeki şarkılar

    public UserPlaylist(String name) {
        this.name = name;
        this.songs = new ArrayList<>(); // Listeyi başlat
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void listSongs() {
        if (songs.isEmpty()) {
            System.out.println("Bu playlist'te henüz şarkı yok.");
            return;
        }
        System.out.println("Playlist '" + name + "' içindeki şarkılar:");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ") " + songs.get(i).getTitle() + " - " + songs.get(i).getCreator());
        }
    }
}