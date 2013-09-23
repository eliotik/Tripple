package com.triple.network;

import com.triple.game.Game;
import com.triple.game.elements.Element;
import com.triple.game.player.Player;
import com.triple.game.player.Players;

import java.util.HashMap;

public class Network {
    private Players players = new Players();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Players getPlayers() {
        return players;
    }

    public void setGrid(HashMap<String, Element> elements){
        Game.grid.setElements(elements);
        Game.grid.refreshJoinableCells();
    }




}
