public class Player {

    private String name;
    private int sixes = 0;
    private int fours = 0;
    private int score = 0;
    private int balls = 0;
    private boolean isBatting = false;

    Player (String name) {
        this.name = name;
    }

    int getScore() {
        return this.score;
    }

    int getSixes() {
        return this.sixes;
    }

    int getFours() {
        return this.fours;
    }

    int getBalls() {
        return this.balls;
    }

    void setName(String name) {
        this.name = name;
    }

    void incrementSixes() {
        this.sixes++;
    }

    void incrementFours() {
        this.fours++;
    }

    void updateScore(int change) {
        score += change;
    }

    void setBalls(int balls) {
        this.balls = balls;
    }

    void playedBall() {
        this.balls++;
    }

    void setIsBatting(boolean isBatting) {
        this.isBatting = isBatting;
    }

    @Override
    public String toString() {
        return this.name + (this.isBatting ? "*":"") + "\t\t"
                + this.score + "\t"
                + this.fours + "\t"
                + this.sixes + "\t"
                + this.balls;
    }
}