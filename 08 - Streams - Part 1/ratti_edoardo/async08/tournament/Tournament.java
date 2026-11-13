package async08.tournament;

import java.util.List;
import java.util.stream.Collectors;

class Coach {
    private final String name;
    private final int experienceYears;
    private final String country;

    Coach(String name, int experienceYears, String country) {
        this.name = name;
        this.experienceYears = experienceYears;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return name + " (" + experienceYears + " yrs, " + country + ")";
    }
}

class Team {
    private final String name;
    private final int ranking;
    private final double winRate; // 0.0 to 1.0
    private final Coach coach;
    private final boolean champion;

    Team(String name, int ranking, double winRate, Coach coach, boolean champion) {
        this.name = name;
        this.ranking = ranking;
        this.winRate = winRate;
        this.coach = coach;
        this.champion = champion;
    }

    public String getName() {
        return name;
    }

    public int getRanking() {
        return ranking;
    }

    public double getWinRate() {
        return winRate;
    }

    public Coach getCoach() {
        return coach;
    }

    public boolean isChampion() {
        return champion;
    }

    @Override
    public String toString() {
        return name + " (Rank " + ranking + ", WinRate " + winRate + ", Coach: " + coach.getName() + ")";
    }
}

public class Tournament {
    public static void main(String[] args) {
        List<Team> teams = List.of(
                new Team("Red Hawks", 1, 0.9, new Coach("Alex Johnson", 15, "USA"), true),
                new Team("Blue Tigers", 3, 0.75, new Coach("Maria Silva", 20, "Brazil"), false),
                new Team("Green Wolves", 2, 0.85, new Coach("Kenji Sato", 12, "Japan"), true),
                new Team("Golden Lions", 5, 0.65, new Coach("Fatima Noor", 18, "India"), false),
                new Team("Silver Foxes", 4, 0.78, new Coach("Liam O’Connor", 10, "USA"), false),
                new Team("Black Panthers", 6, 0.6, new Coach("Elena Petrova", 14, "Russia"), false),
                new Team("White Eagles", 7, 0.55, new Coach("Carlos Mendes", 11, "Brazil"), false),
                new Team("Orange Bulls", 8, 0.5, new Coach("Sakura Tanaka", 9, "Japan"), false)
        );
        System.out.println("\n1. Print all coach names using method references.");
        teams.stream()
                .map(Team::getCoach)
                .map(Coach::getName)
                .forEach(System.out::println);

        System.out.println("\n2. Collect all champion teams into a list using method references.");
        List<Team> champions = teams.stream()
                .filter(Team::isChampion)
                .collect(Collectors.toList());
        System.out.println(champions);

        System.out.println("\n3. Print the names of teams ranked 5 or lower using lambdas.");
        teams.stream()
                .filter(team -> team.getRanking() <= 5)
                .map(team -> team.getName())
                .forEach(teamName -> System.out.println(teamName));
        System.out.println("\n4. Collect all coaches from the USA using lambdas.");
        List<Coach> usaCoaches = teams.stream()
                .map(team -> team.getCoach())
                .filter(coach -> coach.getCountry().equals("USA"))
                .collect(Collectors.toList());
        System.out.println(usaCoaches);

    }
}
