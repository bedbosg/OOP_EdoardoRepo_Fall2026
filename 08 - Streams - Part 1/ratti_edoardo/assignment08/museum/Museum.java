package assignment08.museum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Museum {
    private static class Artist {
        private final String name;
        private final String country;
        private final int yearsActive;

        public Artist(String name, String country, int yearsActive) {
            this.name = name;
            this.country = country;
            this.yearsActive = yearsActive;
        }

        public String getName() {
            return name;
        }

        public String getCountry() {
            return country;
        }

        public int getYearsActive() {
            return yearsActive;
        }
    }

    private static class Artwork {
        private final String title;
        private final String category;
        private final int year;
        private final Artist artist;
        private final boolean onDisplay;

        public Artwork(String title, String category, int year,
                       Artist artist, boolean onDisplay) {
            this.title = title;
            this.category = category;
            this.year = year;
            this.artist = artist;
            this.onDisplay = onDisplay;
        }

        public String getTitle() {
            return title;
        }

        public String getCategory() {
            return category;
        }

        public int getYear() {
            return year;
        }

        public Artist getArtist() {
            return artist;
        }

        public boolean isOnDisplay() {
            return onDisplay;
        }

        @Override
        public String toString() {
            return title + " (" + category + ", " + year + ", " + artist.getName() + ")";
        }
    }

    private static void printAllArtworkTitles(List<Artwork> artworks) {
    }

    private static List<Artwork> findArtworksByCategory(List<Artwork> artworks, String category) {
        return new ArrayList<>();
    }

    private static List<String> getDisplayedArtworkTitles(List<Artwork> artworks) {
        return new ArrayList<>();
    }

    private static Set<String> getUniqueArtistCountries(List<Artwork> artworks) {
        return new HashSet<>();
    }

    private static List<String> getTitlesByExperiencedArtists(List<Artwork> artworks, int minYearsExclusive) {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Artist sofia = new Artist("Sofia Ricci", "Italy", 18);
        Artist liam = new Artist("Liam Carter", "USA", 12);
        Artist aiko = new Artist("Aiko Tanaka", "Japan", 24);
        Artist ana = new Artist("Ana Silva", "Brazil", 9);
        Artist marie = new Artist("Marie Dubois", "France", 30);

        List<Artwork> artworks = List.of(
                new Artwork("Winter Lights", "Painting", 2018, sofia, true),
                new Artwork("Blue Horizon", "Photography", 2021, liam, true),
                new Artwork("Silent Garden", "Sculpture", 2012, aiko, false),
                new Artwork("Golden Hour", "Painting", 2024, sofia, true),
                new Artwork("City Rhythms", "Photography", 2019, liam, false),
                new Artwork("Fragments", "Sculpture", 2004, marie, true),
                new Artwork("Rainforest Study", "Painting", 2022, ana, false),
                new Artwork("Night Bridge", "Painting", 2015, aiko, true)
        );

        System.out.println("1. All artwork titles:");
        printAllArtworkTitles(artworks);
        System.out.println("2. Paintings: " + findArtworksByCategory(artworks, "Painting"));
        System.out.println("3. On display: " + getDisplayedArtworkTitles(artworks));
        System.out.println("4. Artist countries: " + getUniqueArtistCountries(artworks));
        System.out.println("5. Titles by artists with >15 years: " + getTitlesByExperiencedArtists(artworks, 15));
    }
}
