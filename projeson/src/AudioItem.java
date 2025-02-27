import java.time.LocalDate;


public abstract class AudioItem {
    private String title;          // Ad
    private String creator;        // Sanatçı
    private double duration;       // Süre
    private LocalDate releaseDate;// Yayınlanma tarihi
    private String dosyaYolu;

    public AudioItem(String title, String creator, double duration, LocalDate releaseDate) {
        this.title = title;
        this.creator = creator;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.dosyaYolu = dosyaYolu;
    }

    // Getter / Setter Metodları

    //Ses ögesinin adını döndürme
    public String getTitle() {
        return title;
    }

    //Ses ögesinin adını ayarlama.
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Başlık boş olamaz");
        }
        this.title = title;
    }

    //Ses ögesinin sanatçısını döndürme.
    public String getCreator() {
        return creator;
    }

    //Ses ögesinin sanatçısını ayarlama.
    public void setCreator(String creator) {
        if (creator == null || creator.trim().isEmpty()) {
            throw new IllegalArgumentException("Sanatçı/Creator boş olamaz");
        }
        this.creator = creator;
    }

    //Süreyi döndürme
    public double getDuration() {
        return duration;
    }

    //Süreyi ayarlama
    public void setDuration(double duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("Süre negatif olamaz");
        }
        this.duration = duration;
    }

    //Yayınlanma tarihini döndürme
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    //Yayınlanma tarihini ayarlama
    public void setReleaseDate(LocalDate releaseDate) {
        if (releaseDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Gelecekteki bir tarih belirlenemez");
        }
        this.releaseDate = releaseDate;
    }


    public abstract void printInfo();
}