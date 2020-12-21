import java.util.Queue;
import java.util.List;
import java.util.LinkedList;

class Team {
    private List<Player> players;
    private Queue<Player> battingOrder;

    Team (List<Player> players) {
        this.players = players;
        battingOrder = new LinkedList<>();

        for (Player player: players) {
            battingOrder.add(player);
        }

    }

    void setPlayerOut(Player player) {
        player.setIsBatting(false);
    }

    Player getNextPlayer() {
        Player player = battingOrder.remove();
        player.setIsBatting(true);
        return player;
    }

    List<Player> getPlayers() {
        return players;
    }
}