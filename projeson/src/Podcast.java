import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.time.LocalDate;

/**
 * Podcast
 * Alt sınıf - bir podcast nesnesi.
 */
public class Podcast extends AudioItem implements Playable {
    private int episodeNumber; // Podcast'e özgü ek alan
    private String filePath;   // Podcast dosya yolu
    private Clip clip;         // Ses oynatıcı
    private boolean isPlaying = false; // Çalma durumu
    private long pausePosition = 0;    // Duraklatma konumu

    public Podcast(String title, String creator, double duration, LocalDate releaseDate, int episodeNumber, String filePath) {
        super(title, creator, duration, releaseDate);
        this.episodeNumber = episodeNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void printInfo() {
        System.out.println("Podcast: " + getTitle() +
                " | Konuşmacı: " + getCreator() +
                " | Bölüm No: " + episodeNumber +
                " | Süre: " + getDuration() + "dk" +
                " | Yayın Tarihi: " + getReleaseDate());
    }

    @Override
    public void play() {
        try {
            // Eğer podcast çalıyorsa, tekrar başlatma
            if (clip != null && isPlaying) {
                System.out.println(getTitle() + " zaten çalıyor.");
                return;
            }

            // Duraklatılmış bir podcast devam ettirilecekse
            if (clip != null && !isPlaying) {
                clip.setMicrosecondPosition(pausePosition);
                clip.start();
                isPlaying = true;
                System.out.println(getTitle() + " devam ettiriliyor.");
                return;
            }

            // Yeni bir podcast çalma
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.out.println("Dosya bulunamadı: " + filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            isPlaying = true;

            System.out.println(getTitle() + " podcast çalınıyor...");
        } catch (Exception e) {
        //    System.out.println("Podcast çalarken hata oluştu: " + e.getMessage());
        }
    }

    @Override
    public void pause() {
        if (clip != null && isPlaying) {
            pausePosition = clip.getMicrosecondPosition();
            clip.stop();
            isPlaying = false;
            System.out.println(getTitle() + " podcast duraklatıldı.");
        } else {
            System.out.println("Podcast zaten duraklatılmış veya çalmıyor.");
        }
    }

    public void resume() {
        if (clip != null && !isPlaying) {
            clip.setMicrosecondPosition(pausePosition);
            clip.start();
            isPlaying = true;
            System.out.println(getTitle() + " devam ettiriliyor...");
        } else {
            System.out.println("Podcast zaten çalıyor veya başlatılmadı.");
        }
    }

    @Override
    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
            isPlaying = false;
            pausePosition = 0; // Duraklatma konumunu sıfırla
            System.out.println(getTitle() + " podcast durduruldu.");
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}