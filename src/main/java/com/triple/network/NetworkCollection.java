package com.triple.network;

import com.triple.game.elements.Element;
import com.triple.game.player.Player;

import java.util.HashMap;

public class NetworkCollection {
    public Player player;
    public HashMap<String, Element> elements;

    public NetworkCollection(Player player, HashMap<String, Element> elements){
        this.player = player;
        this.elements = elements;
    }


}
