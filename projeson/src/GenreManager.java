import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenreManager {

    public static List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();


        Genre pop = new Genre("Pop");
        pop.addSong(new Song("Üç Kalp", "Hepsi", 3.2, LocalDate.of(2023, 5, 10), "patates",  "/Users/hazalnurdagli/Desktop/proje/pop/Hepsi - Üç Kalp.wav"));
        pop.addSong(new Song("Kan ve Gül", "Kıraç", 4.1,LocalDate.of(2023, 5, 10) ,"kıraç","/Users/hazalnurdagli/Desktop/proje/pop/Kan ve Gul.wav"));
        pop.addSong(new Song("Ben Varım", "Ayten Alpman", 4.1,LocalDate.of(2023, 5, 10) ,"y","/Users/hazalnurdagli/Desktop/proje/pop/mp3indirdur-Ayten-Alpman-Ben-Varim.wav"));
        pop.addSong(new Song("Bir Zamanlar Deli Gönlüm", "Sezen Aksu", 4.1,LocalDate.of(2023, 5, 10) ,"","/Users/hazalnurdagli/Desktop/proje/pop/mp3indirdur-Sezen-Aksu-Bir-Zamanlar-Deli-Gonlum.wav"));
        pop.addSong(new Song("Sevda Sinemalarda", "Yaşar", 4.1,LocalDate.of(2023, 5, 10) ,"y","/Users/hazalnurdagli/Desktop/proje/pop/Yaşar - Sevda Sinemalarda.wav"));
        pop.addSong(new Song("Seviyorum Sevmiyorum", "Nil Karaibrahimgil", 4.1,LocalDate.of(2023, 5, 10) ,"","/Users/hazalnurdagli/Desktop/proje/pop/Nil Karaibrahimgil - Seviyorum Sevmiyorum (Video).wav"));
        pop.addSong(new Song("Sen Benim Şarkılarımsın", "Grup Gündoğarken", 4.1,LocalDate.of(2023, 5, 10) ,"","/Users/hazalnurdagli/Desktop/proje/pop/Sen Benim Şarkılarımsın - Gündoğarken (Official Audio).wav"));


        genres.add(pop);


        Genre rock = new Genre("Rock");
        rock.addSong(new Song("Comfortably Numb", "Pink Floyd", 5.0, LocalDate.of(2020,11,11), "", "Comfortably Numb.mp3"));
        rock.addSong(new Song("Hey You", "Pink Floyd", 5.0, LocalDate.of(2020,11,11), "", "/Users/hazalnurdagli/Desktop/proje/rock/Hey You.mp3"));
        rock.addSong(new Song("Come As You Are", "Nirvana", 5.0, LocalDate.of(2020,11,11), "", "/Users/hazalnurdagli/Desktop/proje/rock/Nirvana - Come As You Are (Official Music Video).mp3"));
        rock.addSong(new Song("No Surprises", "Radiohead", 5.0, LocalDate.of(2020,11,11), "", "/Users/hazalnurdagli/Desktop/proje/rock/Radiohead - No Surprises.mp3"));
        rock.addSong(new Song("Heaven Knows I'm Miserable Now", "Nirvana", 5.0, LocalDate.of(2020,11,11), "", "/Users/hazalnurdagli/Desktop/proje/rock/The Smiths - Heaven Knows I'm Miserable Now (Official Music Video).mp3"));
        rock.addSong(new Song("Delinin Yıldızı", "Vega", 5.0, LocalDate.of(2020,11,11), "", "/Users/hazalnurdagli/Desktop/proje/rock/Vega - Delinin Yıldızı (Official Video).mp3"));
        rock.addSong(new Song("Bu Sabahların Bir Anlamı Olmalı", "Vega", 5.0, LocalDate.of(2020,11,11), "vega", "/Users/hazalnurdagli/Desktop/proje/rock/mp3indirdur-Vega-Bu-Sabahlarin-Bir-Anlami-Olmali.wav"));

        genres.add(rock);


        Genre indie = new Genre("Indie");
        indie.addSong(new Song("Eden", "Hooverphonic", 6.3, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/indie/Hooverphonic - Eden (Official Video).wav"));
        indie.addSong(new Song("Mad About You", "Hooverphonic", 6.3, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/indie/Hooverphonic - Mad About You (Official Video).wav"));
        indie.addSong(new Song("Olsun İstemezdim", "Lin Pesto", 6.3, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/indie/Lin Pesto - Olsun İstemezdim.wav"));
        indie.addSong(new Song("End Of An Era", "Michelle Gurevich", 6.3, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/indie/Michelle Gurevich - End of An Era.wav"));
        indie.addSong(new Song("First Six Months Of Love", "Michelle Gurevich", 6.3, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/indie/Michelle Gurevich - First Six Months of Love.wav"));
        indie.addSong(new Song("Surrender", "Ólöf Arnalds", 6.3, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/indie/Ólöf Arnalds - Surrender (featuring Björk) [Official Video].wav"));
        indie.addSong(new Song("Rhineland", "Beirut", 6.3, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/indie/Rhineland (Heartland).wav"));

        genres.add(indie);


        Genre klasikmuzik = new Genre("Klasik Müzik");
        klasikmuzik.addSong(new Song("Moonlight Sonata", "Beethoven", 3.8, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/klasik/BEETHOVEN - Moonlight Sonata, 1st Movement - 432 Hz - (Piano Rendition).wav"));
        klasikmuzik.addSong(new Song("Clair de Lune", "Claude Debussy", 3.8, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/klasik/Clair de Lune (Studio Version).wav"));
        klasikmuzik.addSong(new Song("Gnossienne No.1", "Erik Satie", 3.8, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/klasik/Erik Satie - Gnossienne No.1.wav"));
        klasikmuzik.addSong(new Song("Valse", "Evgeny Grinko", 3.8, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/klasik/Evgeny Grinko - Valse.wav"));
        klasikmuzik.addSong(new Song("By the Sea", "Eleni Karaindrou", 3.8, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/klasik/Karaindrou Eleni Eternity and a day By the sea.wav"));
        klasikmuzik.addSong(new Song("To Vals To Gamou", "Eleni Karaindrou", 3.8, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/klasik/To Vals Tou Gamou - Eleni Karaindrou.wav"));
        klasikmuzik.addSong(new Song("Turkish March", "Mozart", 3.8, LocalDate.of(2020,11,11),"", "/Users/hazalnurdagli/Desktop/proje/klasik/Turkish March Mozart - Rondo Alla Turca.wav"));

        genres.add(klasikmuzik);

        return genres;
    }

    public static List<Podcast> getAllPodcasts() {
        List<Podcast> podcasts = new ArrayList<>();

        // Örnek podcastler
        podcasts.add(new Podcast("Sanat", "Gülendam Dinç", 5.0, LocalDate.of(2023, 3, 5), 1, "/Users/hazalnurdagli/Desktop/proje/podcasts/Sanatta Estetik ve GÃ¼zellik Ã\u009Czerine.wav"));
        podcasts.add(new Podcast("Staying All Night", "TED-Ed", 4.30, LocalDate.of(2023, 5, 12), 2, "/Users/hazalnurdagli/Desktop/proje/podcasts/What staying up all night does to your brain - Anna Rothschild.wav"));
        podcasts.add(new Podcast("3 Tips On How To Study Effectively", "Ted-Ed", 4.33, LocalDate.of(2023, 6, 15), 3, "/Users/hazalnurdagli/Desktop/proje/podcasts/3 tips on how to study effectively.wav.download"));

        return podcasts;
    }
}