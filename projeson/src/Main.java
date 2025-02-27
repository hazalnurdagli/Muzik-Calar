import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // Ana menü için scanner nesnesi oluşturuldu.
    private static final Scanner scanner = new Scanner(System.in);

    // Oluşturulan çalma listelerini tutan
    private static final List<UserPlaylist> userPlaylists = new ArrayList<>();

    // Şarkılar ve podcastler için liste
    private static final List<AudioItem> musicLibrary = new ArrayList<>();

    // Tür Listesi
    private static final List<Genre> genres = new ArrayList<>();

    public static void main(String[] args) {

        boolean exit = false;

        System.out.println("=== Basit Müzik Çalar Uygulaması ===");

        while (!exit) {
            //Ana menü seçeneklerini ekrana yazdırma.
            System.out.println("\n--- ANA MENÜ ---");
            System.out.println("1) Tüm Şarkıları Göster");
            System.out.println("2) Türe Göre Müzik Çal");
            System.out.println("3) Yeni Playlist Oluştur");
            System.out.println("4) Mevcut Playlistleri Yönet");
            System.out.println("5) Podcastler");
            System.out.println("0) Çık");
            System.out.print("Seçiminiz: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    //Tüm şarkıları açan metodu çağırma
                    listAllSongs();
                    break;

                case "2":
                    //Tür seçerek şarkıları açan metodu çağırma
                    selectGenreAndPlay();
                    break;

                case "3":
                    //Çalma listesi oluşturma metodunu çağırma.
                    createNewPlaylist();
                    break;

                case "4":
                    //Mevcut çalma listelerini yönetmeyi sağlayan metodu çağırma.
                    managePlaylists();
                    break;

                case "5":
                    //Podcastleri açan metodu çağırma.
                    listAllPodcasts();
                    break;

                case "0":
                    //Döngüden çıkma
                    System.out.println("Programdan çıkılıyor...");
                    exit = true;
                    break;

                default:
                    System.out.println("Geçersiz seçim, tekrar deneyin!");
            }
        }
        //Kaynağı serbest bırakma
        scanner.close();
    }

    //Tüm türlerdeki şarkıları bir arada kullanııcıya sunan metod.
    private static void listAllSongs() {
        // GenreManager'dan tüm türler alınıyor.
        List<Genre> availableGenres = GenreManager.getAllGenres();

        // Tüm şarkıları toplamak için liste oluşturuluyor.
        List<Song> allSongs = new ArrayList<>();
        for (Genre genre : availableGenres) {
            allSongs.addAll(genre.getSongs());
        }

        if (allSongs.isEmpty()) {
            System.out.println("Müzik kütüphanesi boş!");
            return;
        }

        // Tüm şarkıları listele
        System.out.println("\n--- Tüm Şarkılar ---");
        for (int i = 0; i < allSongs.size(); i++) {
            Song song = allSongs.get(i);
            System.out.println((i + 1) + ") " + song.getTitle() + " - " + song.getCreator());
        }

        // Kullanıcıdan şarkı seçimi istenmesi
        System.out.print("\nOynatmak istediğiniz şarkının numarasını girin (çıkmak için 0): ");
        int songIndex;
        try {
            songIndex = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz giriş!");
            return;
        }

        //0 girilmesi durumunda ana menüye dönme
        if (songIndex == -1) {
            System.out.println("Ana menüye dönülüyor...");
            return;
        }

        if (songIndex < 0 || songIndex >= allSongs.size()) {
            System.out.println("Geçersiz şarkı numarası!");
            return;
        }

        // Seçilen şarkıyı oynatma
        Song chosenSong = allSongs.get(songIndex);
        chosenSong.play();

        // Şarkı çalarkenki seçenekleri gösterme
        showPlaybackMenu(chosenSong);
    }

    //Podcastleri kullanıcıya sunan metod.
    private static void listAllPodcasts() {
        // Podcastler GenreManager'dan alınıyor.
        List<Podcast> allPodcasts = GenreManager.getAllPodcasts();

        if (allPodcasts.isEmpty()) {
            System.out.println("Henüz podcast eklenmedi!");
            return;
        }

        // Podcastleri listeleme
        System.out.println("\n--- Tüm Podcastler ---");
        for (int i = 0; i < allPodcasts.size(); i++) {
            Podcast podcast = allPodcasts.get(i);
            System.out.println((i + 1) + ") " + podcast.getTitle() + " - " + podcast.getCreator() +
                    " (Bölüm: " + podcast.getEpisodeNumber() + ")");
        }

        // Kullanıcıdan seçim isteme
        System.out.print("\nOynatmak istediğiniz podcastin numarasını girin (çıkmak için 0): ");
        int podcastIndex;
        try {
            podcastIndex = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz giriş!");
            return;
        }

        //0 girilmesi durumunda ana menüye dönme
        if (podcastIndex == -1) {
            System.out.println("Ana menüye dönülüyor...");
            return;
        }

        if (podcastIndex < 0 || podcastIndex >= allPodcasts.size()) {
            System.out.println("Geçersiz podcast numarası!");
            return;
        }

        // Seçilen podcasti oynat
        Podcast chosenPodcast = allPodcasts.get(podcastIndex);
        chosenPodcast.play();

        // Podcast çalarkenki menüyü gösterme
        showPlaybackMenu(chosenPodcast);
    }

    //Şarkıları türlerine göre kullanıcıya sunan metod.
    private static void selectGenreAndPlay() {
        // Tüm türleri GenreManager'dan al
        List<Genre> availableGenres = GenreManager.getAllGenres();

        if (availableGenres.isEmpty()) {
            System.out.println("Tanımlı türler bulunamadı!");
            return;
        }

        //Mevcut türleri listeleme
        System.out.println("\n--- Mevcut Türler ---");
        for (int i = 0; i < availableGenres.size(); i++) {
            System.out.println((i + 1) + ") " + availableGenres.get(i).getGenreName());
        }

        //Kullanıcıdan seçim isteme
        System.out.print("Hangi müzik türünü seçmek istersiniz? (numara): ");
        int genreIndex;
        try {
            genreIndex = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz giriş!");
            return;
        }

        if (genreIndex < 0 || genreIndex >= availableGenres.size()) {
            System.out.println("Geçersiz tür numarası!");
            return;
        }

        // Seçilen tür
        Genre selectedGenre = availableGenres.get(genreIndex);
        List<Song> songsInGenre = selectedGenre.getSongs(); // Bu türdeki şarkılar

        if (songsInGenre.isEmpty()) {
            System.out.println("Bu türde şarkı bulunamadı!");
            return;
        }

        System.out.println("\nSeçilen tür: " + selectedGenre.getGenreName());
        for (int i = 0; i < songsInGenre.size(); i++) {
            System.out.println((i + 1) + ") " + songsInGenre.get(i).getTitle()
                    + " - " + songsInGenre.get(i).getCreator());
        }

        //Kullanıcıdan seçim isteme
        System.out.print("Oynatmak istediğiniz şarkının numarasını girin: ");
        int songIndex;
        try {
            songIndex = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz giriş!");
            return;
        }

        if (songIndex < 0 || songIndex >= songsInGenre.size()) {
            System.out.println("Geçersiz şarkı numarası!");
            return;
        }

        // Seçilen şarkıyı oynat
        Song chosenSong = songsInGenre.get(songIndex);
        chosenSong.play();

        // Şarkı çalarkenki menüyü gösterme
        showPlaybackMenu(chosenSong);
    }

    // Müzik çalınırken kullanıcıya seçenekler sunan metod.
    private static void showPlaybackMenu(AudioItem currentItem) {
        boolean backToPreviousMenu = false;

        while (!backToPreviousMenu) {
            //Menü seçeneklerini ekrana yazdır.
            System.out.println("\n--- Çalma Menüsü ---");
            System.out.println("1) Duraklat / Devam Et");
            System.out.println("2) Bilgileri Göster");
            System.out.println("3) Bir Önceki Menüye Dön");
            System.out.print("Seçiminiz: ");

            String choice = scanner.nextLine().trim();

            // currentItem çalınabilir bir nesne mi kontrol et (playable)
            if (currentItem instanceof Playable) {
                Playable playableItem = (Playable) currentItem;

                switch (choice) {
                    case "1": // Duraklat veya devam ettir
                        if (playableItem instanceof Song) {
                            Song song = (Song) playableItem;
                            if (song.isPlaying()) {
                                //duraklatma
                                song.pause();
                            } else {
                                //devamettirme
                                song.resume();
                            }
                        } else {
                            System.out.println("Podcastler için duraklatma/devam etme özelliği eklenmedi.");
                        }
                        break;

                    case "2": // Bilgileri gösterme
                        currentItem.printInfo();
                        break;

                    case "3": // Bir önceki menüye dönme
                        backToPreviousMenu = true;
                        break;

                    default:
                        System.out.println("Geçersiz seçim, tekrar deneyin!");
                }
            } else {
                System.out.println("Bu öğe oynatılamaz (Playable değil).");
                backToPreviousMenu = true;
            }
        }
    }

    //Kullanıcıdan çalma listesi adı alıp yeni çalma listesi ekleyen metod
    private static void createNewPlaylist() {
        System.out.print("Lütfen yeni playlist'in adını giriniz: ");
        String playlistName = scanner.nextLine().trim();
        if (playlistName.isEmpty()) {
            System.out.println("Geçersiz playlist adı!");
            return;
        }

        // Yeni çalma listesi oluşturma ve listeye ekleme
        UserPlaylist newPlaylist = new UserPlaylist(playlistName);
        userPlaylists.add(newPlaylist);

        System.out.println("'" + playlistName + "' adlı yeni playlist oluşturuldu!");
    }

    // Playlistleri yönetmek için alt menü
    private static void managePlaylists() {
        if (userPlaylists.isEmpty()) {
            System.out.println("Henüz hiçbir playlist oluşturulmamış!");
            return;
        }

        // Mevcut playlistleri listeleme
        listUserPlaylists();

        System.out.print("\nHangi playlist'i yönetmek istersiniz? (numara): ");
        int plChoice;
        try {
            plChoice = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz giriş!");
            return;
        }
        if (plChoice < 0 || plChoice >= userPlaylists.size()) {
            System.out.println("Geçersiz playlist numarası!");
            return;
        }


        UserPlaylist selectedPlaylist = userPlaylists.get(plChoice);

        // Alt menü
        boolean backToMain = false;
        while (!backToMain) {
            System.out.println("\n--- " + selectedPlaylist.getName() + " Yönetim Menüsü ---");
            System.out.println("1) Playlist'e Müzik Ekle");
            System.out.println("2) Playlist'i Listele ve Çal");
            System.out.println("3) Ana Menüye Dön");
            System.out.print("Seçiminiz: ");

            String subChoice = scanner.nextLine().trim();
            switch (subChoice) {
                case "1":
                    //Çalma listesine müzik ekleme metodunu çağır
                    addMusicToPlaylist(selectedPlaylist);
                    break;
                case "2":
                    //Çalma listesindeki müzikleri çalma metodunu çağır.
                    playFromPlaylist(selectedPlaylist);
                    break;
                case "3":
                    //Ana menüye dönme
                    backToMain = true;
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }

    //Mevcut çalma listelerini listeler.
    private static void listUserPlaylists() {
        System.out.println("\n--- Mevcut Playlistler ---");
        for (int i = 0; i < userPlaylists.size(); i++) {
            UserPlaylist p = userPlaylists.get(i);
            System.out.println((i + 1) + ") " + p.getName()
                    + " (Toplam müzik: " + p.getSongs().size() + ")");
        }
    }

    //Kullanıcıya tüm şarkılar listelenir ve çalma listesine eklemesi için seçim yapması istenir.
    private static void addMusicToPlaylist(UserPlaylist playlist) {
        // GenreManager'dan tüm türleri al
        List<Genre> availableGenres = GenreManager.getAllGenres();

        // Tüm türlerdeki şarkıları birleştirir
        List<Song> allSongs = new ArrayList<>();
        for (Genre genre : availableGenres) {
            allSongs.addAll(genre.getSongs());
        }

        if (allSongs.isEmpty()) {
            System.out.println("Müzik kütüphanesi boş!");
            return;
        }

        // Tüm şarkıları listele
        System.out.println("\n--- Mevcut Tüm Şarkılar ---");
        for (int i = 0; i < allSongs.size(); i++) {
            Song song = allSongs.get(i);
            System.out.println((i + 1) + ") " + song.getTitle() + " - " + song.getCreator());
        }

        System.out.println("\nPlaylist'e eklemek istediğiniz şarkıların numaralarını girin (bittiğinde 0):");

        while (true) {
            System.out.print("Şarkı numarası: ");
            int songIndex;
            try {
                songIndex = Integer.parseInt(scanner.nextLine()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş!");
                continue;
            }

            if (songIndex == -1) { // Kullanıcı 0 girerse ekleme işlemi biter
                System.out.println("Ekleme işlemi tamamlandı.");
                break;
            }

            if (songIndex < 0 || songIndex >= allSongs.size()) {
                System.out.println("Geçersiz şarkı numarası!");
                continue;
            }

            // Seçilen şarkıyı playlist'e ekleme
            Song selectedSong = allSongs.get(songIndex);
            playlist.addSong(selectedSong);
            System.out.println("'" + selectedSong.getTitle() + "' playlist'e eklendi.");
        }
    }

    //Kullanıcıya çalma listesindeki şarkıları listeleyip seçtiğini çaldıran metod.
    private static void playFromPlaylist(UserPlaylist playlist) {
        if (playlist.getSongs().isEmpty()) {
            System.out.println("Bu playlist'te henüz şarkı yok. Önce müzik ekleyiniz.");
            return;
        }

        // Çalma listesindeki şarkıları listeleme
        System.out.println("\n--- Playlist: " + playlist.getName() + " ---");
        for (int i = 0; i < playlist.getSongs().size(); i++) {
            Song song = playlist.getSongs().get(i);
            System.out.println((i + 1) + ") " + song.getTitle() + " - " + song.getCreator());
        }

        // Kullanıcıdan seçim isteme
        System.out.print("\nÇalmak istediğiniz şarkının numarasını giriniz: ");
        int songIndex;
        try {
            songIndex = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz giriş!");
            return;
        }

        if (songIndex < 0 || songIndex >= playlist.getSongs().size()) {
            System.out.println("Geçersiz şarkı numarası!");
            return;
        }

        // Seçilen şarkıyı oynat
        Song chosenSong = playlist.getSongs().get(songIndex);
        chosenSong.play();

        // Şarkı çalarken kullanılan alt menü
        showPlaybackMenu(chosenSong);
    }


    }



