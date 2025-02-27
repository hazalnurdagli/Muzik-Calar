import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.time.LocalDate;

//AudioItem'den türetilmiş ve Playable arayüzünü uygulayan Song sınıfı
public class Song extends AudioItem implements Playable {
    private String album; //albüm adı
    private String filePath; //dosya adresi
    private boolean isPlaying = false; // Şarkı çalma durumu
    private Clip clip; // Şarkının oynatıldığı ses klibi
    private long pausePosition = 0; // Duraklatma konumu

    public Song(String title, String creator, double duration, LocalDate releaseDate, String album, String filePath) {
        super(title, creator, duration, releaseDate);
        this.album = album;
        this.filePath = filePath;
    }

    //Getter ve Setter Metodları
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    //AudioItem'deki soyut metodu override ederek uygular.
    @Override
    public void printInfo() {
        System.out.println("Şarkı: " + getTitle() +
                " | Sanatçı: " + getCreator() +
                " | Albüm: " + album +
                " | Süre: " + getDuration() + "dk" +
                " | Yayın Tarihi: " + getReleaseDate());
    }

    @Override
    public void play() {
        try {
            // Zaten oynuyorsa tekrar başlatma
            if (clip != null && isPlaying) {
                System.out.println(getTitle() + " zaten çalıyor.");
                return;
            }

            // Daha önce başlatılmış ama duraklatılmışsa devam et
            if (clip != null && !isPlaying) {
                clip.setMicrosecondPosition(pausePosition);
                clip.start();
                isPlaying = true;
                System.out.println(getTitle() + " devam ettiriliyor.");
                return;
            }

            // Yeni başlatma
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.out.println("Dosya bulunamadı: " + filePath);
                return;
            }

            //Ses dosyasını yükleme ve oynatma
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            isPlaying = true;

            System.out.println(getTitle() + " çalınıyor...");
        } catch (Exception e) {
            System.out.println("Şarkıyı çalarken hata oluştu: " + e.getMessage());
        }
    }

    //Şarkıyı duraklatma
    @Override
    public void pause() {
        if (clip != null && isPlaying) {
            pausePosition = clip.getMicrosecondPosition();
            clip.stop();
            isPlaying = false;
            System.out.println(getTitle() + " duraklatıldı.");
        } else {
            System.out.println("Şarkı zaten duraklatılmış veya çalmıyor.");
        }
    }

    //şarkıyı devam ettirme
    public void resume() {
        if (clip != null && !isPlaying) {
            clip.setMicrosecondPosition(pausePosition);
            clip.start();
            isPlaying = true;
            System.out.println(getTitle() + " devam ettiriliyor...");
        } else {
            System.out.println("Şarkı zaten çalıyor veya başlatılmadı.");
        }
    }

    //Şarkıyı durdurma
    @Override
    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
            isPlaying = false;
            pausePosition = 0; // Duraklatma konumunu sıfırla
            System.out.println(getTitle() + " durduruldu.");
        }
    }

    //Şarkının çalınıp çalınmadığını kontrol eder
    public boolean isPlaying() {
        return isPlaying;
    }
}