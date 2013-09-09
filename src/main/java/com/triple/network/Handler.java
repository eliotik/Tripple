package com.triple.network;

import com.triple.game.player.Player;
import com.triple.game.player.Players;

public class Handler {
    private Player player;
    private Players players = new Players();

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Players getPlayers() {
        return players;
    }

    public void addPlayers(Player player) {
        players.add(player);
    }
}
