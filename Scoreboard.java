class Scoreboard {
    private int playersCount;
    private int oversCount;
    private int wickets = 0;
    private int oversCompleted = 0;
    private Team team;
    private Player strike;
    private Player nonStrike;
    private int score = 0;
    private int currOverBalls = 0;

    
    Scoreboard (int playersCount, int oversCount, Team team) {
        this.playersCount = playersCount;
        this.oversCount = oversCount;
        this.team = team;
        strike = team.getNextPlayer();
        nonStrike = team.getNextPlayer();
    }

    boolean isBattingOver() {
        return !((wickets < playersCount-1) && (oversCompleted < oversCount));
    }

    boolean isLastWicket() {
        return wickets == playersCount-2;
    }

    void playerOut() {
        strike.playedBall();
        team.setPlayerOut(strike);
        if (!isLastWicket()) {
            strike = team.getNextPlayer();
        }
        wickets++;
    }

    void changeStrike() {
        Player temp = strike;
        strike = nonStrike;
        nonStrike = temp;
    }

    void incrementOversCompleted() {
        oversCompleted++;
    }

    void printScoreboard() {
        System.out.println("Player Name\t" + "Score\t" + "4s\t" + "6s\t" + "Balls");
        for (Player player: team.getPlayers()) {
            System.out.println(player.toString());
        }
        System.out.println("Total: " + score + "/" + wickets);
        System.out.println("Overs: " + oversCompleted + (currOverBalls < 6 ? ("." + currOverBalls) : ""));
    }

    void updateScore(int change) {
        score += change;
    }

    // Getter functions
    int getScore() {
        return score;
    }

    int getOversCompleted() {
        return oversCompleted;
    }

    int getOversCount() {
        return oversCount;
    }

    int getWickets() {
        return wickets;
    }

    Team getTeam() {
        return team;
    }

    Player getStrike() {
        return strike;
    }

    Player getNonStrike() {
        return nonStrike;
    }

    // Setters
    void setScore(int score) {
        this.score = score;
    }

    void setOversCount(int oversCount) {
        this.oversCount = oversCount;
    }

    void setWickets(int wickets) {
        this.wickets = wickets;
    }

    void setTeam(Team team) {
        this.team = team;
    }

    void setStrike(Player strike) {
        this.strike = strike;
    }

    void setNonStrike(Player nonStrike) {
        this.nonStrike = nonStrike;
    }

    void setCurrOverBalls(int currOverBalls) {
        this.currOverBalls = currOverBalls;
    }

    boolean updateScoreboard(int run) {
        strike.playedBall();

        switch (run) {
            case Constants.RUNS_ZERO:
                break;

            case Constants.RUNS_ONE:
                strike.updateScore(Constants.RUNS_ONE);
                changeStrike();
                updateScore(Constants.RUNS_ONE);
                break;

            case Constants.RUNS_TWO:
                strike.updateScore(Constants.RUNS_TWO);
                updateScore(Constants.RUNS_TWO);
                break;

            case Constants.RUNS_THREE:
                strike.updateScore(Constants.RUNS_THREE);
                changeStrike();
                updateScore(Constants.RUNS_THREE);
                break;

            case Constants.RUNS_FOUR:
                strike.updateScore(Constants.RUNS_FOUR);
                strike.incrementFours();
                updateScore(Constants.RUNS_FOUR);
                break;

            case Constants.RUNS_SIX:
                strike.updateScore(Constants.RUNS_SIX);
                strike.incrementSixes();
                updateScore(Constants.RUNS_SIX);
                break;

            default:
                System.out.println("Invalid input!! Try again please!");
                return false;

        }

        return true;
    }
}