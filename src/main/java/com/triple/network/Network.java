package com.triple.network;

import com.triple.game.Game;
import com.triple.game.elements.Element;
import com.triple.game.grid.Cell;
import com.triple.game.player.Player;
import com.triple.game.player.Players;

import java.util.HashMap;

public class Network {
    private Players players = new Players();

    public void addPlayer(Player player) {

        if (getPlayers().get(player.getName()) == null) {
            players.add(player);
        }

    }

    public Players getPlayers() {
        return players;
    }

    public void setGrid(HashMap<String, Element> elements, String clickedCellCoordinate){
        Game.grid.setElements(elements, clickedCellCoordinate);
//        Game.grid.refreshJoinableCells();
    }

//    public void joinCells (String coordinates) {
//        int x, y;
//        String[] coord;
//        coord = coordinates.split(", ");
//        x = (int) Double.parseDouble(coord[0]);
//        y = (int) Double.parseDouble(coord[1]);
//        Cell cell = Game.grid.getCell(x, y);
//        cell.checkJoinables();
//    }

}
