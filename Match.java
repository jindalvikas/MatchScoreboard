import java.util.*;

class Match {
    private Scoreboard[] scoreboards = new Scoreboard[2];

    void setScoreboard(Scoreboard scoreboard, int i) {
        scoreboards[i] = scoreboard;
    }

    Scoreboard getScoreboard(int i) {
        return scoreboards[i];
    }

    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("No. of players for each team: ");
        int playersCount = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("No. of overs: ");
        int overs = Integer.parseInt(scanner.nextLine().trim());

        Match match = new Match();
        int lastscore = 0;

        for (int t=0; t<2; t++) {

            List<Player> players = new LinkedList<>();

            System.out.println("Batting Order for team " + (t+1) + ": ");
            for (int i = 0; i < playersCount; i++) {
                String name = scanner.nextLine().trim();
                Player player = new Player(name);
                players.add(player);
            }


            Team team = new Team(players);
            Scoreboard scoreboard = new Scoreboard(playersCount, overs, team);

            match.setScoreboard(scoreboard, t);

            for (int i = 0; i < scoreboard.getOversCount(); i++) {
                System.out.println("Over " + (scoreboard.getOversCompleted() + 1));

                for (int j = 0; j < 6; ) {
                    scoreboard.setCurrOverBalls(j+1);
                    String input = scanner.nextLine().trim();

                    if (input.equalsIgnoreCase(Constants.WICKET)) {

                        scoreboard.playerOut();
                        j++;
                        if (scoreboard.isBattingOver()) {
                            break;
                        }

                    } else if (input.equalsIgnoreCase(Constants.WIDE)) {

                        scoreboard.updateWideBall();

                    } else {

                        int run = Integer.parseInt(input);
                        boolean updateScore = scoreboard.updateScoreboard(run);
                        if (updateScore) {
                            j++;
                        }
                    }


                    if (j == 5) {
                        scoreboard.incrementOversCompleted();
                    }

                    if (t == 1) {
                        if (scoreboard.getScore() > lastscore) {
                            break;
                        }
                    }
                }
                scoreboard.printScoreboard();

                if (t == 1) {
                    if (scoreboard.getScore() > lastscore) {
                        break;
                    }
                }

                if (scoreboard.isBattingOver()) {
                    break;
                }

                scoreboard.changeStrike();
            }
            lastscore = scoreboard.getScore();
        }

        int score1 = match.getScoreboard(0).getScore();
        int score2 = match.getScoreboard(1).getScore();

        if (score1 > score2) {
            System.out.println("Result: Team 1 won match by " + (score1-score2) + " runs");
        } else if (score2 > score1) {
            System.out.println("Result: Team 2 won match by " + (score2-score1) + " runs");
        } else {
            System.out.println("Result: Match Tie!");
        }

    }

}