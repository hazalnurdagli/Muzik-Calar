/**
 * Playable
 * Oynatılabilir bir ses dosyası için gereken temel metotları belirler.
 */
public interface Playable {
    void play();   // Oynat
    void pause();  // Duraklat
    void stop();   // Durdur

    // Metod Overloading
    default void play(boolean showMessage) {
        if (showMessage) {
            System.out.println("Oynatma bilgisi gösteriliyor...");
        }
        play();
    }
}