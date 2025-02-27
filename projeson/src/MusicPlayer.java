import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * MusicPlayer
 * Kullanıcı etkileşimli menüde çalınacak öğeleri yönetir.
 */
public class MusicPlayer {
    private List<AudioItem> playlist;
    private int currentIndex = -1;

    // Statik alan
    private static String version = "1.0";

    // Statik metot
    public static void displayWelcomeMessage() {
        System.out.println("Müzik Çalara Hoş Geldiniz! (Sürüm: " + version + ")");
    }

    public MusicPlayer() {
        playlist = new ArrayList<>();
    }

    public void addAudioItem(AudioItem item) {
        playlist.add(item);
    }

    public void listAudioItems() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist boş.");
            return;
        }
        System.out.println("== Mevcut Öğeler ==");
        for (int i = 0; i < playlist.size(); i++) {
            System.out.print((i + 1) + ") ");
            playlist.get(i).printInfo();
        }
    }

    public void playItem(int index) throws CustomMusicException {
        if (index < 0 || index >= playlist.size()) {
            throw new CustomMusicException("Geçersiz indeks: " + index);
        }
        // Çalan şarkı varsa durdur
        currentIndex = index;
        AudioItem item = playlist.get(currentIndex);

        // Polymorphism: item bir Song da olabilir, Podcast de.
        if (item instanceof Playable) {
            ((Playable)item).play();
        } else {
            System.out.println("Bu öğe oynatılamaz!");
        }
    }


    // Dosya yazma/okuma (basit bir örnek)
    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (AudioItem item : playlist) {
                // basit bir format: type|title|creator|duration|releaseDate|extra
                if (item instanceof Song) {
                    Song s = (Song) item;
                    writer.write("SONG|" + s.getTitle() + "|" + s.getCreator() + "|"
                            + s.getDuration() + "|" + s.getReleaseDate() + "|" + s.getAlbum());
                } else if (item instanceof Podcast) {
                    Podcast p = (Podcast) item;
                    writer.write("PODCAST|" + p.getTitle() + "|" + p.getCreator() + "|"
                            + p.getDuration() + "|" + p.getReleaseDate() + "|" + p.getEpisodeNumber());
                }
                writer.newLine();
            }
            System.out.println("Liste dosyaya kaydedildi: " + fileName);
        } catch (IOException e) {
            System.out.println("Kaydetme hatası: " + e.getMessage());
        }
    }

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    String type = parts[0];
                    String title = parts[1];
                    String creator = parts[2];
                    double duration = Double.parseDouble(parts[3]);
                    LocalDate date = LocalDate.parse(parts[4]);
                    String album = parts[5];
                    String filePath = parts[6];

                    switch (type) {
                        case "SONG":
                            Song s = new Song(title, creator, duration, date, album, filePath);
                            addAudioItem(s);
                            break;
                        case "PODCAST":
                            int episodeNumber = Integer.parseInt(album);
                            Podcast p = new Podcast(title, creator, duration, date, episodeNumber, filePath);
                            addAudioItem(p);
                            break;
                    }
                }
            }
            System.out.println("Dosyadan yüklendi: " + fileName);
        } catch (IOException e) {
            System.out.println("Yükleme hatası: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Veri okuma hatası: " + e.getMessage());
        }
    }
}