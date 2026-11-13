package classroom08;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class Game {
    private final String title;
    private final String genre;
    private final int year;
    private final boolean multiplayer;
    private final boolean indie;
    private final double price;

    Game(String title, String genre, int year, boolean multiplayer, boolean indie, double price) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.multiplayer = multiplayer;
        this.indie = indie;
        this.price = price;
    }

    // Accessors used in map()
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public boolean isIndie() {
        return indie;
    }

    public boolean isClassic() {
        return year < 2005;
    }

    public boolean isExpensive() {
        return price > 50.0;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) - %s%s%s $%.2f", title, year, genre,
                multiplayer ? ", Multiplayer" : "SinglePlayer",
                indie ? ", Indie" : "",
                price);
    }
}

class Player {
    private final String name;
    private final int age;
    private final List<Game> library;
    private final boolean premium;

    Player(String name, int age, boolean premium, List<Game> library) {
        this.name = name;
        this.age = age;
        this.premium = premium;
        this.library = new ArrayList<>(library);
    }

    public String getName() {
        return name;
    }

    public List<Game> getLibrary() {
        return new ArrayList<>(library);
    }

    public int getAge() {
        return age;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    public boolean isPremium() {
        return premium;
    }

    @Override
    public String toString() {
        return String.format("%s (age %d%s)", name, age, premium ? ", Premium" : "");
    }
}

public class GameExercise {
    public static void main(String[] args) {
        List<Game> games = List.of(
                new Game("Elder Realms", "RPG", 2002, false, false, 39.99),
                new Game("Sky Arena", "Action", 2012, true, false, 59.99),
                new Game("Stardale", "Simulation", 2016, false, true, 14.99),
                new Game("CyberRun", "Action", 2020, true, true, 24.99),
                new Game("Mystic Valley", "Adventure", 1999, false, false, 19.99),
                new Game("Guild Heroes", "RPG", 2004, true, false, 49.99),
                new Game("Colonies", "Strategy", 2023, true, true, 54.99),
                new Game("Pixel Noir", "Adventure", 2018, false, true, 9.99)
        );

        List<Player> players = List.of(
                new Player("Ava", 17, false, List.copyOf(games.subList(0, 3))),
                new Player("Ben", 22, true, List.copyOf(games.subList(2, 6))),
                new Player("Chloe", 29, false, List.of(games.get(6), games.get(7))),
                new Player("Dan", 15, false, List.of(games.get(1), games.get(3))),
                new Player("Ella", 20, true, List.copyOf(games.subList(1, 4))),
                new Player("Finn", 28, false, List.of(games.get(4), games.get(5))),
                new Player("Grace", 14, true, List.of(games.get(0), games.get(2))),
                new Player("Henry", 16, false, List.copyOf(games.subList(3, 6)))
        );

        // region Part A - Players

        // Print player names
        System.out.println("\nPlayers:");
        for (Player player : players) {
            String name = player.getName();
            System.out.println(name);
        }
        System.out.println("\nPlayers:");
        players.stream()
                .map(Player::getName)
                .forEach(System.out::println);
        // Print adult players

        System.out.println("\nAdult players:");
        for (Player player : players) {
            if (player.isAdult()) {
                System.out.println(player);
            }
        }
        players.stream()
                .filter(Player::isAdult)
                .forEach(System.out::println);

        // Minor player names
        List<String> minorPlayerNames = new ArrayList<>();
        for (Player player : players) {
            if (!player.isAdult()) {
                final String playerName = player.getName();
                minorPlayerNames.add(playerName);
            }
        }
        System.out.println("\nminorPlayerNames: " + minorPlayerNames);

        List<String> minorPlayerNamesStream = players.stream()
                .filter(Predicate.not(Player::isAdult))
                .map(Player::getName)
                        .toList();
        // endregion
        System.out.println("\nminorPlayerNamesStream: " + minorPlayerNamesStream);
        // region Part B - Games

        // Print multiplayer games
        System.out.println("\nMultiplayer games:");
        for (Game game : games) {
            if (game.isMultiplayer()) {
                System.out.println(game);
            }
        }
        games.stream().filter(Game::isMultiplayer).forEach(System.out::println);

        // Uppercase titles of expensive games (price > 50)
        List<String> expensiveTitles = new ArrayList<>();
        for (Game game : games) {
            if (game.isExpensive()) {
                String title = game.getTitle();
                String upperCaseTitle = title.toUpperCase();
                expensiveTitles.add(upperCaseTitle);
            }
        }
        System.out.println("\nExpensive games uppercase: " + expensiveTitles);
        List<String> expensiveTitlesStream = games.stream()
                .filter(Game::isExpensive)
                .map(Game::getTitle)
                .map(String::toUpperCase)
                .toList();
        // endregion
    }
}
